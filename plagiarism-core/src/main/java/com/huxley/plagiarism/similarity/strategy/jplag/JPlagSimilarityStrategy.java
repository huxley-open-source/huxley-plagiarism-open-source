package com.huxley.plagiarism.similarity.strategy.jplag;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huxley.plagiarism.similarity.SimilarityDetectionStrategy;
import com.huxley.plagiarism.tokenizer.Language;

public class JPlagSimilarityStrategy implements SimilarityDetectionStrategy {
	
	private static final Logger logger = LoggerFactory.getLogger(JPlagSimilarityStrategy.class);
	
	private Map<Language, String> supportedLanguages = new HashMap<Language, String>();
	
	private String jplagJar = "jplag-2.11.8-SNAPSHOT-jar-with-dependencies.jar";
	
	private Language language;

	public JPlagSimilarityStrategy() {
		super();
		logger.debug("Using JPlag strategy");
		this.supportedLanguages.put(Language.C, "c/c++");
		this.supportedLanguages.put(Language.CPP, "c/c++");
		this.supportedLanguages.put(Language.JAVA, "java17");
	}
	
	private InputStream convertToInputStream(List<String> source) {
		StringBuilder builder = new StringBuilder();

		for (String line : source) {
			builder.append(line).append("\n");
		}

		return new ByteArrayInputStream(builder.toString().getBytes(Charset.defaultCharset()));
	}
	
	@Override
	public BigDecimal score(List<String> source, List<String> target, Integer granularity) {
		InputStream sourceInputStream = this.convertToInputStream(source);
		InputStream targetInputStream = this.convertToInputStream(target);
		
		String path;
		do {
			path = System.getProperty("java.io.tmpdir") + File.separator + UUID.randomUUID();
		} while (new File(path).exists());
		
		File tmpDirectory = new File(path);
		
		BufferedReader reader = null;
		try {
			tmpDirectory.mkdirs();
		
			FileOutputStream sourceOutputStream = new FileOutputStream(String.format("%s/source.%s", path, this.language));
			FileOutputStream targetOutputStream = new FileOutputStream(String.format("%s/target.%s", path, this.language));
			
			try {
				IOUtils.copy(sourceInputStream, sourceOutputStream);
				IOUtils.copy(targetInputStream, targetOutputStream);
			} finally {
				sourceInputStream.close();
				sourceOutputStream.close();	
				targetInputStream.close();
				targetOutputStream.close();	
			}
			
			String javaExecutable = System.getenv("JRE_HOME") + File.separator + "bin" + File.separator + "java";
			
			String[] params = {
					javaExecutable, "-jar", this.jplagJar,  
					"-t", "1", 
					"-l", this.supportedLanguages.get(this.language), 
					"-s", path, 
					"-r", path
			};

			Process process = Runtime.getRuntime().exec(params);
			
			reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			
			String line = null;
			while ((line = reader.readLine()) != null) {
				logger.debug(line);
			    if (line.startsWith("Comparing")) {
			    	break;
			    }
			}
			
			try {
				process.waitFor();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			
			return this.parseResult(line);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} finally {
			if (reader != null) {
				try { reader.close(); } catch (IOException e) {}
			}

			if (tmpDirectory.exists()) {
				try { 
					FileUtils.cleanDirectory(tmpDirectory);
					tmpDirectory.delete();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private BigDecimal parseResult(String line) {
		String[] result = line != null ? line.split(":") : new String[] {};
		
		if (result.length == 2) {
			return new BigDecimal(line.split(":")[1].trim()).divide(new BigDecimal("100"));
		} else {
			return new BigDecimal("0");
		}
	}

	public void setLanguage(String language) {
		this.language = Language.valueOf(language.toUpperCase());
	}

}
