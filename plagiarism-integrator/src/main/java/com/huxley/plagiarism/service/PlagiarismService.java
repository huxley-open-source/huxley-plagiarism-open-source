package com.huxley.plagiarism.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ObjectNode;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PlagiarismService {
	
	private static final Logger logger = LoggerFactory.getLogger(PlagiarismService.class);
	
	@Value("${service.uri}")
	private String uri;
	
	@Value("${service.username}")
	private String username;
	
	@Value("${service.password}")
	private String password;
	
	@Value("${data.dir}")
	private String filePath;
	
	private Map<String, Map<String, Long>> groups = new HashMap<>();	
	
	private Long verifyGroup(Map<String, String> groupInformation) {
		if (!this.groups.containsKey(this.username)) {
			this.groups.put(this.username, new HashMap<String, Long>());
		}
		
		Long groupId = null;
		Long parentGroupId = null;
		
		for (String groupPrivateId : groupInformation.keySet()) {			
			groupId = this.groups.get(this.username).get(groupPrivateId);
			if (groupId == null) {
				groupId = this.groupExists(groupPrivateId);
				if (groupId == null) {
					groupId = this.createGroup(groupPrivateId, groupInformation.get(groupPrivateId), parentGroupId);
				}
				
				this.groups.get(this.username).put(groupPrivateId, groupId);
			}			
			
			parentGroupId = groupId;
		}
		
		return groupId;
	}
	
	public void send(Map<String, String> groupInformation, Long submissionId, String filename, String languageName) {			
		Long groupId = this.verifyGroup(groupInformation);
		
		StringBuilder submissionPrivateId = new StringBuilder();
		for (String groupPrivateId : groupInformation.keySet()) {
			submissionPrivateId.append(groupPrivateId);
		}
		
		//FIXME refactoring
		String groupFullPath = null;
		for (String groupPrivateId : groupInformation.keySet()) {			
			groupFullPath = groupPrivateId;
		}
		
		ClientConfig clientConfig = new ClientConfig()
			.register(HttpAuthenticationFeature.basic(this.username, this.password))
			.register(MultiPartFeature.class);

		Client client = ClientBuilder.newClient(clientConfig);
		
		WebTarget resourceWebTarget = client.target(this.uri).path("/plagiarism/api/submissions");
		
		Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_JSON);
		
		final FileDataBodyPart filePart = new FileDataBodyPart("file", new File(filePath + filename));
		
		FormDataMultiPart multiPartEntity = new FormDataMultiPart();
		multiPartEntity.field("group_id", groupId.toString());
		multiPartEntity.field("private_id", groupFullPath + "/" + submissionId.toString());
		multiPartEntity.field("language", languageName);
		multiPartEntity.bodyPart(filePart);
		
		Response response = invocationBuilder.post(Entity.entity(multiPartEntity, multiPartEntity.getMediaType()));
		
		if (response.getStatus() != 201 && response.getStatus() != 302) {
			logger.error("Não foi possivel submeter. submission.id = " + submissionId);
			throw new RuntimeException("Não foi possível submeter.");
		}
	}

	public String getStatus(String privateSubmissionId) {
		ClientConfig clientConfig = new ClientConfig()
			.register(HttpAuthenticationFeature.basic(this.username, this.password));
	
		Client client = ClientBuilder.newClient(clientConfig);
		
		WebTarget resourceWebTarget = client.target(this.uri).path("/plagiarism/api/submissions")
				.queryParam("private_id", privateSubmissionId.toString());;
		
		Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_JSON);
		
		Response response = invocationBuilder.get();
		String result = response.readEntity(String.class);	
		
		try {
			JsonNode rootNode = new ObjectMapper().readTree(result);
			
			Iterator<JsonNode> iterator = rootNode.getElements();
			
			while (iterator.hasNext()) {
				JsonNode node = iterator.next();
				return node.path("status").getTextValue();				
			}
			
			return null;
		} catch (IOException e) {
			logger.error("Não foi possivel verificar status do processamento. submission.id = " + privateSubmissionId, e);
			throw new RuntimeException("Não foi possivel verificar status do processamento. submission.id = " + privateSubmissionId, e);
		}
	}
	
	public Long groupExists(String problemPrivateId) {
		ClientConfig clientConfig = new ClientConfig()
			.register(HttpAuthenticationFeature.basic(this.username, this.password));
		
		Client client = ClientBuilder.newClient(clientConfig);
		
		WebTarget resourceWebTarget = client.target(this.uri).path("/plagiarism/api/groups")
				.queryParam("private_id", problemPrivateId);
				
		Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);
		
		Response response = invocationBuilder.get();
		
		String result = response.readEntity(String.class);		
		
		try {
			JsonNode rootNode = new ObjectMapper().readTree(result);
			
			Iterator<JsonNode> iterator = rootNode.getElements();
			
			while (iterator.hasNext()) {
				JsonNode node = iterator.next();
				return node.path("id").getLongValue();				
			}
			
			return null;
		} catch (IOException e) {
			logger.error("Não foi possivel verificar existência do grupo. group.private_id = " + problemPrivateId, e);
			throw new RuntimeException("Não foi possivel verificar existência do grupo. group.private_id = " + problemPrivateId, e);
		}
	}
	
	public Long createGroup(String groupPrivateId, String groupName, Long parentGroupId) {
		ClientConfig clientConfig = new ClientConfig()
			.register(HttpAuthenticationFeature.basic(this.username, this.password));

		Client client = ClientBuilder.newClient(clientConfig);
		
		WebTarget resourceWebTarget = client.target(this.uri).path("/plagiarism/api/groups");
		
		Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_JSON);
		
		ObjectNode rootNode = new ObjectMapper().createObjectNode();
		rootNode.put("private_id", groupPrivateId);
		rootNode.put("name", groupName);
		
		if (parentGroupId != null) {
			ObjectNode parentGroupNode = new ObjectMapper().createObjectNode();
			parentGroupNode.put("id", parentGroupId);
			
			rootNode.put("parent_group", parentGroupNode);
		}
		
		Response response = invocationBuilder.post(Entity.entity(rootNode.toString(), MediaType.APPLICATION_JSON));
		
		String result = response.readEntity(String.class);
		
		try {
			JsonNode rootNodeResult = new ObjectMapper().readTree(result);
			
			return rootNodeResult.path("id").getLongValue();
		} catch (IOException e) {
			logger.error("Não foi possível criar o grupo. group.private_id = " + groupPrivateId, e);
			throw new RuntimeException("Não foi possível criar o grupo. group.private_id = " + groupPrivateId, e);
		}
	}
	
	public List<Map<String, Object>> list(Map<String, String> groupInformation, BigDecimal threshold, Integer maxResults) {
		Long group = this.verifyGroup(groupInformation);
				
		ClientConfig clientConfig = new ClientConfig()
			.register(HttpAuthenticationFeature.basic(this.username, this.password));

		Client client = ClientBuilder.newClient(clientConfig);
		
		WebTarget resourceWebTarget = client.target(this.uri).path("/plagiarism/api/comparisons/plagiarism")
				.queryParam("group_id", group)
				.queryParam("threshold", threshold)
				.queryParam("max_results", maxResults);
		
		Invocation.Builder invocationBuilder = resourceWebTarget.request(MediaType.APPLICATION_JSON);
		
		Response response = invocationBuilder.get();
		
		String result = response.readEntity(String.class);
		
		List<Map<String, Object>> plagiarismResult = new ArrayList<>();
		try {
			JsonNode rootNode = new ObjectMapper().readTree(result);
			
			Iterator<JsonNode> iterator = rootNode.getElements();
			
			while (iterator.hasNext()) {
				JsonNode node = iterator.next();
				
				String status = node.path("status").getTextValue();
				BigDecimal similarity = node.path("similarity").getDecimalValue();
				String sourceSubmission = node.path("source_submission").path("private_id").getTextValue();
				String targetSubmission = node.path("target_submission").path("private_id").getTextValue();
				
				Map<String, Object> row = new HashMap<>();
				row.put("status", status);
				row.put("similarity", similarity);
				row.put("source_submission", Long.valueOf(sourceSubmission.substring(sourceSubmission.lastIndexOf("/") + 1)));
				row.put("target_submission", Long.valueOf(targetSubmission.substring(targetSubmission.lastIndexOf("/") + 1)));
				
				plagiarismResult.add(row);
			}
			
			return plagiarismResult;
		} catch (IOException e) {
			logger.error("Não foi possível extrair o resultado.");
			throw new RuntimeException("Não foi possível extrair o resultado.", e);
		}
	}

}
