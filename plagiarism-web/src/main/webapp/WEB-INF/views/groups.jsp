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
function convertFormToJSON(form) {
    var array = jQuery(form).serializeArray();
	
    var group = {};
    
    if (array[0].value != '') {
    	group['private_id'] = array[0].value;
    }
    group['name'] = array[1].value;
    
    if (array[2].value != '') {
    	group['parent_group'] = {"id" : array[2].value};
    }
    
    return JSON.stringify(group);
}

function submitForm(form) {	
	var jsonData = convertFormToJSON($(form));
	$.ajax({
		type: "POST",
		url: '<c:url value="/api/groups" />',
		contentType : 'application/json',
		data: jsonData,		  
		success: function() { 
			clearForm();
			refreshGroups();
		},
		error: function(data) {
			alert(data.responseText);
		}
	});
}

function refreshGroups() {
	$.getJSON('<c:url value="/api/groups" />', function(data) {
		$('#groups_table').empty();
		var items = [];
		var count = 1;
		$.each(data, function(key, val) {
			var row = $('<tr/>');
			$('<td/>').html(count++).appendTo(row);
			$('<td/>').html($('<a/>').attr('href', '<c:url value="/groups" />/' + val.id).html(val.name)).appendTo(row);
			$('<td/>').html(val.private_id).appendTo(row);
			
			items.push(row);
		});
		 
		$('#groups_table').html(items);
	});
}

function clearForm() {
	$('#private_id').val('');
	$('#group_name').val('');
	var selectList = $("select#parent_group_id");
	selectList[0].selectedIndex = 0;
}

setInterval(function() {
	refreshGroups();
}, 5000);
</script>
</head>
<body>
	<div id="groupForm" class="modal fade">
		<div class="modal-dialog">
    		<div class="modal-content">
      			<div class="modal-header">
        			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
        			<h4 class="modal-title">New Group</h4>
      			</div>
				<form role="form" id="group_form" class="form-horizontal">
	      			<div class="modal-body">
	      				<div class="form-group">
							<label for="file" class="col-sm-2 control-label">Private Id:</label>
							<div class="col-sm-10">
								<input id="private_id" class="form-control" name="private_id" type="text" />
							</div>
						</div>
						<div class="form-group">
							<label for="group_name" class="col-sm-2 control-label">Name:</label>
							<div class="col-sm-10">
								<input id="group_name" name="group_name" class="form-control" type="text" />
							</div>
						</div>
					</div>
	      			<div class="modal-body">      			
						<div class="form-group">
							<label for="parent_group_id" class="col-sm-2 control-label">Parent Group:</label>
							<div class="col-sm-10">
								<select class="form-control" id="parent_group_id" name="parent_group_id">
									<option></option>
									<c:forEach var="group" items="${groups}">
										<option value="${group.id}">${group.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="modal-footer">
	        			<button class="btn btn-default" data-dismiss="modal">Close</button>
	        			<button class="btn btn-primary" data-dismiss="modal" onclick="submitForm('#group_form');">Submit</button>
	      			</div>
				</form>
			</div>
		</div>				
	</div>
	<div class="panel panel-default">
		<div class="panel-heading"><a href="<c:url value="/groups" />">Groups</a></div>
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
					<c:forEach varStatus="status" var="group" items="${groups}">
						<tr>
							<td>${status.index + 1 + param.offset}</td>
							<td><a href="<c:url value="/groups/${group.id}" />">${group.name}</a></td>
							<td>${group.privateId}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:if test="${totalOfPages > 1}">
				<ul class="pagination">
					<li><a href="<c:url value="/groups" />">&laquo;</a></li>
					<c:forEach begin="0" end="${totalOfPages - 1}" varStatus="loop">
						<c:choose>
							<c:when test="${param.offset == (loop.index * 30) || (param.offset == null && loop.index == 0)}">
								<li class="active"><a href="<c:url value="/groups"><c:param name="offset" value="${loop.index * 30}" /></c:url>">${loop.index + 1}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="<c:url value="/groups"><c:param name="offset" value="${loop.index * 30}" /></c:url>">${loop.index + 1}</a></li>
							</c:otherwise>
						</c:choose>					
					</c:forEach>
					<li><a href="<c:url value="/groups"><c:param name="offset" value="${(totalOfPages - 1) * 30}" /></c:url>">&raquo;</a></li>
				</ul>
			</c:if>
		</div>
		<div class="panel-footer">
			<button class="btn btn-primary" data-toggle="modal" data-target="#groupForm">New Group</button>
		</div>
	</div>
</body>
</html>