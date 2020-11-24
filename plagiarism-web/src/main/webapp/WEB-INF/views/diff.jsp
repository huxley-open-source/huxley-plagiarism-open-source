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
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/jsdifflib/diffview.css" />" />
	<script type="text/javascript" src="<c:url value="/resources/js/jsdifflib/difflib.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resources/js/jsdifflib/diffview.js" />"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			diffUsingJS(0);
		});

		function diffUsingJS(viewType) {
			"use strict";
			var byId = function (id) { return document.getElementById(id); },
				base = difflib.stringAsLines(byId("source_submission").value),
				newtxt = difflib.stringAsLines(byId("target_submission").value),
				sm = new difflib.SequenceMatcher(base, newtxt),
				opcodes = sm.get_opcodes(),
				diffoutputdiv = byId("comparison"),
				contextSize = null;

			diffoutputdiv.innerHTML = "";

			diffoutputdiv.appendChild(diffview.buildView({
				baseTextLines: base,
				newTextLines: newtxt,
				opcodes: opcodes,
				baseTextName: '${comparison.sourceSubmission.originalFilename}',
				newTextName: '${comparison.targetSubmission.originalFilename}',
				contextSize: null,
				viewType: viewType
			}));
		}
	</script>
</head>
<body>
	<input type="hidden" id="source_submission" value="<c:out value="${comparison.sourceSubmission.fileContent}" escapeXml="true" />" />
	<input type="hidden" id="target_submission" value="<c:out value="${comparison.targetSubmission.fileContent}" escapeXml="true" />" />
	<div class="panel panel-default">
		<div class="panel-heading"><a href="<c:url value="/groups" />">Groups</a><c:forEach var="path" items="${groupFullPath}"> / <a href="<c:url value="/groups/${path[0]}" />">${path[1]}</a></c:forEach></div>
		<div class="panel-body">
			<h4>
				<span>Submission 1: <a href="<c:url value="/submissions/${comparison.sourceSubmission.id}" />">${comparison.sourceSubmission.originalFilename}</a></span>
				<span>Submission 2: <a href="<c:url value="/submissions/${comparison.targetSubmission.id}" />">${comparison.targetSubmission.originalFilename}</a></span>
				<br />
				<br />
				Similarity: ${comparison.similarity * 100}%
				<span class="pull-right">
					Is there plagiarism?
					<c:choose>
						<c:when test="${comparison.plagiarism == true}">
							<button class="btn btn-danger btn-xs" onclick="window.location = '<c:url value="/comparisons/${comparison.id}/plagiarism"><c:param name="value" value="yes" /></c:url>'">Yes</button>
							<button class="btn btn-default btn-xs" onclick="window.location = '<c:url value="/comparisons/${comparison.id}/plagiarism"><c:param name="value" value="no" /></c:url>'">No</button>
						</c:when>
						<c:when test="${comparison.plagiarism == false}">
							<button class="btn btn-default btn-xs" onclick="window.location = '<c:url value="/comparisons/${comparison.id}/plagiarism"><c:param name="value" value="yes" /></c:url>'">Yes</button>
							<button class="btn btn-success btn-xs" onclick="window.location = '<c:url value="/comparisons/${comparison.id}/plagiarism"><c:param name="value" value="no" /></c:url>'">No</button>
						</c:when>
						<c:otherwise>
							<button class="btn btn-default btn-xs" onclick="window.location = '<c:url value="/comparisons/${comparison.id}/plagiarism"><c:param name="value" value="yes" /></c:url>'">Yes</button>
							<button class="btn btn-default btn-xs" onclick="window.location = '<c:url value="/comparisons/${comparison.id}/plagiarism"><c:param name="value" value="no" /></c:url>'">No</button>
						</c:otherwise>
					</c:choose>
				</span>
			</h4>
			<div id="comparison"></div>
		</div>
		<div class="panel-footer">
			<button class="btn btn-primary" onclick="window.history.back();">Back</button>
		</div>
	</div>	
</body>
</html>