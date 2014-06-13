<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="h" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=50%, initial-scale=0.5">
	<title>Mini Controller</title>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
   	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
</head>
<body>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" ></script>
	
	<script type="text/javascript">
		var selStatus="";
		$(function(){
			$('select').on("change",function(){
				$('#selStatus').val($(this).val());
				selStatus = $(this).val();
				var title = $(this).attr("title");
				var url = "${pageContext.request.contextPath}/manageDevices/save/" + title + "/" + selStatus;
				var id = "#saveUrl" + title;
				$(id).attr({href:url});
				$(id).removeAttr("hidden");
				//alert($(this).val());
			});
		});
	
	</script>
	
    <h:sidebar />
	<div class="container-fluid">
        <div class="col-sm-9 col-md-10 main">
		        <h4 class="page-header">Manage Registered Devices</h4>
			<div class="table-responsive">
				<form:form method="POST" commandName="user" >
				<table class="table table-striped table-condensed" style="text-align: left;" >
					<thead style="font-weight: bold">
						<tr>
							<td>Device ID</td>
							<td>MAC Address </td>
							<td>Status </td>
							<td>Actions</td>
						</tr>
					</thead>
					<c:forEach items="${user.devices}" var="device" varStatus="loopStatus">	
						<tr>
							<td> ${device.deviceId} </td>
							<td> ${device.macAddr} </td>
							<td> <form:select title="${loopStatus.index}" id="statuses" path="devices[${loopStatus.index}].status"> 
									<form:option value="ON" />
									<form:option value="OFF" />
								</form:select>
							</td>
							<td>			
								<a id="saveUrl${loopStatus.index}" href="${pageContext.request.contextPath}/manageDevices/save/${loopStatus.index}" hidden="true">Save</a>
							</td>
						</tr>
					</c:forEach>
				</table>
				</form:form>
				
			</div>
		</div>
	</div>
</body>
</html>