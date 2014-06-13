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
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	
	
     <h:sidebar />     

    <c:url value="/j_spring_security_logout" var="logoutUrl" />
 
	<!-- csrt for log out-->
	<form action="${logoutUrl}" method="post" id="logoutForm">
	  <input type="hidden" 
		name="${_csrf.parameterName}"
		value="${_csrf.token}" />
	</form>
 
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>
 
	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h5 style="float:right">
			User: ${pageContext.request.userPrincipal.name} | ${user.name}<a
				href="javascript:formSubmit()"> Logout</a>
		</h5>
	</c:if>
	<div class="container-fluid">
        <div class="col-sm-9 col-md-10 main">
			<div class="table-responsive"><br>
				<table class="table table-striped table-condensed" style="text-align: left;" >
					<thead>
						<tr>
							<td>User ID</td>
							<td>Name </td>
							<td>Email </td>
							<td>Devices (ID;MAC Addr;Status)</td>
						</tr>
					</thead>
					<c:forEach items="${userList}" var="user">	
						<tr>
							<td> ${user.userId} </td>
							<td> ${user.name} </td>
							<td> ${user.email} </td>
							<td>								
								<c:forEach items="${user.devices}" var="device">	
									${device.deviceId} ; ${device.macAddr} ; ${device.status} <br>
								</c:forEach>				
							</td>
						</tr>
					</c:forEach>
				</table>
				
				
			</div>
		</div>
	</div>
</body>
</html>