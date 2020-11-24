<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
$(document).ready(function() {
	searchPlagiarismSuspects();
});

var options = { 
	type : 'POST',
	beforeSend: function() {
	    
	},
	uploadProgress: function(event, position, total, percentComplete) {
	    	
	},
	success: function() {
		refreshSubmissions();
		clearForm();
	},
	complete: function(response) {

	},
	error: function(data) {
		alert(data.responseText);
	}
};

function submitForm(form) {
	$(form).ajaxForm(options);
	$('#submissionForm').modal('hide');
}

function searchPlagiarismSuspects() {
	$.getJSON('<c:url value="/api/comparisons/plagiarism"><c:param name="group_id" value="${group.id}" /><c:param name="threshold" value="0.8" /></c:url>', function(data) {
		$('#suspects_table').empty();
		var items = [];
		var count = 1;
		if (data.length > 0) {
			$.each(data, function(key, val) {
				var row = $('<tr/>');
				$('<td/>').html(count++).appendTo(row);
				$('<td/>').html(val.source_submission.filename).appendTo(row);
				$('<td/>').html(val.target_submission.filename).appendTo(row);
				$('<td/>').html(parseFloat(val.similarity * 100).toFixed(2) + '%').appendTo(row);
				$('<td/>').html($('<a/>').attr('href', '<c:url value="/comparisons" />/' + val.id).html('Show')).appendTo(row);
				
				items.push(row);
			});
			
			$('#suspects_table').html(items);
			$('#suspects_panel').show()
		}
	});
}

function refreshSubmissions() {
	$.getJSON('<c:url value="/api/groups/${group.id}/submissions" />', function(data) {
		$('#submissions_table').empty();
		var items = [];
		var count = 1;
		$.each(data, function(key, val) {
			var row = $('<tr/>');
			$('<td/>').html(count++).appendTo(row);
			$('<td/>').html($('<a/>').attr('href', '<c:url value="/submissions" />/' + val.id).html(val.filename)).appendTo(row);
			$('<td/>').html(val.private_id).appendTo(row);
			
			var status = '';
			var label = 'label label-';
			
			if (val.status == 'WAITING') {
				label += 'default';
				status = 'WAITING';
			} else if (val.status == 'PARSE_ERROR') {
				label += 'danger'; 
				status = 'PARSE ERROR';
			} else if (val.status == 'PROCESSING') {
				label += 'primary'; 
				status = 'PROCESSING';
			} else if (val.status == 'OK') {
				label += 'success'; 				
				status = 'OK';
			}
			$('<td/>').html($('<span/>').attr('class', label).html(status)).appendTo(row);
			$('<td/>').html(val.submission_date).appendTo(row);
			$('<td/>').html(val.processing_date).appendTo(row);
			
			items.push(row);
		});
		 
		$('#submissions_table').html(items);
	});
}

function clearForm() {
	$('#private_id').val('');
	$('#file').val('');
	var selectList = $("select#language");
	selectList[0].selectedIndex = 0;
}

setInterval(function() {
	refreshSubmissions();
}, 5000);
</script>
</head>
<body>
	<div id="submissionForm" class="modal fade">
		<div class="modal-dialog">
    		<div class="modal-content">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        			<h4 class="modal-title">New Submission</h4>
      			</div>
				<form role="form" id="submission_form" class="form-horizontal" enctype="multipart/form-data" action="<spring:url value="/api/submissions" />">
	      			<div class="modal-body">
						<div class="form-group">
							<label for="private_id" class="col-sm-2 control-label">Private Id:</label>
							<div class="col-sm-10">
								<input id="private_id" class="form-control" name="private_id" type="text" />
							</div>
						</div>
						<div class="form-group">
							<label for="file" class="col-sm-2 control-label">File:</label>
							<div class="col-sm-10">
								<input id="file" class="form-control" name="file" type="file" />
								<input name="group_id" type="hidden" value="${group.id}" />
							</div>
						</div>
		      			<div class="modal-body">      			
							<div class="form-group">
								<label for="language" class="col-sm-2 control-label">Language:</label>
								<div class="col-sm-10">
									<select class="form-control" id="language" name="language">
										<option></option>
										<c:forEach var="language" items="${languages}">
											<option value="${language.name}">${language.name}</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
	        			<button class="btn btn-default" data-dismiss="modal">Close</button>
	        			<button class="btn btn-primary" onclick="submitForm('#submission_form');">Submit</button>
	      			</div>
				</form>
			</div>
		</div>
	</div>
	
	<div class="panel panel-default">
		<div class="panel-heading"><a href="<c:url value="/groups" />">Groups</a><c:forEach var="path" items="${groupFullPath}"> / <a href="<c:url value="/groups/${path[0]}" />">${path[1]}</a></c:forEach></div>
		<div class="panel-body">
			
			<c:if test="${fn:length(childrenGroups) gt 0}">
				<div class="panel panel-default">
					<div class="panel-heading">Subgroups</div>
					<div class="panel-body">
						<table class="table">
							<thead>				
								<tr>
									<th>#</th>
									<th>Name</th>
									<th>Private Id</th>
								</tr>
							</thead>
							<tbody id="groups_table">
								<c:forEach varStatus="status" var="child" items="${childrenGroups}">
									<tr>
										<td>${status.index + 1 + param.offset}</td>
										<td><a href="<c:url value="/groups/${child.id}" />">${child.name}</a></td>
										<td>${child.privateId}</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<c:if test="${totalOfPages > 1}">
							<ul class="pagination">
								<li><a href="<c:url value="/groups/${group.id}" />">&laquo;</a></li>
								<c:forEach begin="0" end="${totalOfPages - 1}" varStatus="loop">
									<c:choose>
										<c:when test="${param.offset == (loop.index * 30) || (param.offset == null && loop.index == 0)}">
											<li class="active"><a href="<c:url value="/groups/${group.id}"><c:param name="offset" value="${loop.index * 30}" /></c:url>">${loop.index + 1}</a></li>
										</c:when>
										<c:otherwise>
											<li><a href="<c:url value="/groups/${group.id}"><c:param name="offset" value="${loop.index * 30}" /></c:url>">${loop.index + 1}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								<li><a href="<c:url value="/groups/${group.id}"><c:param name="offset" value="${(totalOfPages - 1) * 30}" /></c:url>">&raquo;</a></li>
							</ul>
						</c:if>
					</div>					
				</div>
			</c:if>
			
			<div id="suspects_panel" class="panel panel-danger" style="display: none;">
				<div class="panel-heading">Plagiarism Suspects</div>
				<div class="panel-body">
					<table class="table">
						<thead>
							<tr>
								<th>#</th>
								<th>Submission 1</th>
								<th>Submission 2</th>
								<th>Similarity</th>
								<th></th>
							</tr>
						</thead>
						<tbody id="suspects_table"></tbody>
					</table>
				</div>
			</div>
			
			<c:if test="${fn:length(group.submissions) gt 0}">
				<div class="panel panel-default">
					<div class="panel-heading">Submissions</div>
					<div class="panel-body">
						<table class="table">
							<thead>
								<tr>
									<th>#</th>
									<th>Filename</th>
									<th>Private Id</th>
									<th>Status</th>
									<th>Submission Date</th>
									<th>Processing Date</th>
								</tr>
							</thead>
							<tbody id="submissions_table">
								<c:forEach varStatus="status" var="submission" items="${group.submissions}">
									<tr>
										<td>${status.index + 1}</td>
										<td><a href="<spring:url value="/submissions/${submission.id}" />">${submission.originalFilename}</a></td>
										<td>${submission.privateId}</td>
										<td>
											<c:choose>
												<c:when test="${submission.submissionStatus == 'WAITING'}">
													<span class="label label-default">WAITING</span>
												</c:when>
												<c:when test="${submission.submissionStatus == 'PARSE_ERROR'}">
													<span class="label label-danger">PARSE ERROR</span>
												</c:when>
												<c:when test="${submission.submissionStatus == 'PROCESSING'}">
													<span class="label label-primary">PROCESSING</span>
												</c:when>
												<c:otherwise>
													<span class="label label-success">OK</span>
												</c:otherwise>
											</c:choose>
										</td>
										<td><fmt:formatDate value="${submission.submissionDate}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
										<td><fmt:formatDate value="${submission.processingDate}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>	
			</c:if>
		</div>
		<div class="panel-footer">
			<button class="btn btn-primary" onclick="window.history.back();">Back</button>
			<button class="btn btn-primary" data-toggle="modal" data-target="#submissionForm">New Submission</button>
		</div>
	</div>
</body>
</html>