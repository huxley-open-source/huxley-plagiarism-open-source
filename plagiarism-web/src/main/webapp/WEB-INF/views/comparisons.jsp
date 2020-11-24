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
		function refreshComparisons() {
			$.getJSON('<c:url value="/api/submissions/${submission.id}/comparisons" />', function(data) {
				$('#comparisons_table').empty();
				var items = [];
				var count = 1;
				$.each(data, function(key, val) {
					var row = $('<tr/>');
					$('<td/>').html(count++).appendTo(row);
					$('<td/>').html($('<a/>').attr('href', '<c:url value="/comparisons" />/' + val.id).html(val.target_submission.filename)).appendTo(row);
					$('<td/>').html(val.target_submission.private_id).appendTo(row);
					$('<td/>').html(val.creation_date).appendTo(row);
					$('<td/>').html(val.processing_date).appendTo(row);
					
					var label = 'label label-';
					if (val.status == 'WAITING') {
						label += 'default'; 
					} else if (val.status == 'PARSE_ERROR') {
						label += 'danger'; 
					} else if (val.status == 'PROCESSING') {
						label += 'primary'; 
					} else if (val.status == 'OK') {
						label += 'success'; 				
					}
					
					$('<td/>').html($('<span/>').attr('class', label).html(val.status)).appendTo(row);
					$('<td/>').html(parseFloat(val.similarity * 100).toFixed(2) + '%').appendTo(row);
					
					var label = 'label label-';
					var value = null;
					if (val.plagiarism == true) {
						label += 'danger';
						value = 'PLAGIARISM';
					} else if (val.plagiarism == false) {
						label += 'success';
						value = 'NOT PLAGIARISM';
					} else {
						label += 'default'; 				
						value = 'PENDING';
					}
					
					$('<td/>').html($('<span/>').attr('class', label).html(value)).appendTo(row);
					
					
					items.push(row);
				});
				 
				$('#comparisons_table').html(items);
			});
		}
		
		setInterval(function() {
			refreshComparisons();
		}, 5000);
	</script>
</head>
<body>
	<div id="tokenForm" class="modal fade">
		<div class="modal-dialog">
    		<div class="modal-content">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        			<h4 class="modal-title">Source Code</h4>
      			</div>
      			<div class="modal-body" id="source_code">
				</div>
				<div class="modal-footer">
        			<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      			</div>
			</div>
		</div>
	</div>
	<div class="panel panel-default">
		<div class="panel-heading"><a href="<c:url value="/groups" />">Groups</a><c:forEach var="path" items="${groupFullPath}"> / <a href="<c:url value="/groups/${path[0]}" />">${path[1]}</a></c:forEach> / <a href="<c:url value="/submissions/${submission.id}" />">${submission.originalFilename}</a></div>
		<div class="panel-body">
			<div class="panel panel-default">
				<div class="panel-heading">Comparisons of submission ${submission.originalFilename}</div>
				<div class="panel-body">
					<table class="table">
						<thead>				
							<tr>
								<th>#</th>
								<th>Target Submission</th>
								<th>Private Id</th>
								<th>Creation Date</th>
								<th>Processing Date</th>
								<th>Status</th>
								<th>Similarity</th>
								<th>Manual Review</th>
							</tr>
						</thead>
						<tbody id="comparisons_table">
							<c:forEach varStatus="status" var="comparison" items="${submission.comparisons}">
								<tr>
									<td>${status.index + 1}</td>
									<td><a href="<c:url value="/comparisons/${comparison.id}" />">${comparison.targetSubmission.originalFilename}</a></td>
									<td>${comparison.targetSubmission.privateId}</td>
									<td><fmt:formatDate value="${comparison.creationDate}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
									<td><fmt:formatDate value="${comparison.processingDate}" pattern="dd/MM/yyyy HH:mm:ss" /></td>
									<td>
										<c:choose>
											<c:when test="${comparison.comparisonStatus == 'WAITING'}">
												<span class="label label-default">WAITING</span>
											</c:when>
											<c:when test="${comparison.comparisonStatus == 'PROCESSING'}">
												<span class="label label-primary">PROCESSING</span>
											</c:when>
											<c:otherwise>
												<span class="label label-success">OK</span>
											</c:otherwise>
										</c:choose>
									</td>
									<td>${comparison.similarity * 100}%</td>
									<td>
										<c:choose>
											<c:when test="${comparison.plagiarism == true}"><span class="label label-danger">PLAGIARISM</span></c:when>
											<c:when test="${comparison.plagiarism == false}"><span class="label label-success">NOT PLAGIARISM</span></c:when>
											<c:otherwise><span class="label label-default">PENDING</span></c:otherwise>
										</c:choose>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="panel-footer">
			<button class="btn btn-primary" onclick="window.history.back();">Back</button>
		</div>
	</div>	
</body>
</html>