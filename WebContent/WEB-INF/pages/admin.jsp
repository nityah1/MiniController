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
	<meta name="viewport" content="width=80%, initial-scale=0.6">
	<title>Mini Controller</title>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
	<link href="//maxcdn.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
	<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
	
	
     <h:sidebar />
	<div class="container-fluid">
		<div class="col-sm-9 col-md-10 main">
		        <h4 class="page-header">Edit User Details</h4>
		
				<div class="table-responsive">	 
					<form:form method="POST" commandName="user" action="${pageContext.request.contextPath}/admin/update">
						<form:input type="hidden" path="id" />
						<table class="table-striped table-condensed">  
							<tbody>
								<tr>  
							        <td><form:label path="userId">User ID:</form:label></td>  
							        <td><form:input path="userId" readonly="true" cssStyle="backgroundColor:grey"></form:input></td>  
							    </tr>  
							    <tr>  
							        <td><form:label path="password">Password:</form:label></td>  
							        <td><form:password path="password"></form:password></td>  
							    </tr> 
								<tr>  
							        <td><form:label path="name">Name:</form:label></td>  
							        <td><form:input path="name"></form:input></td>  
							    </tr>  
							    <tr>  
							        <td><form:label path="email">Email address:</form:label></td>  
							        <td><form:input path="email"></form:input></td>  
							    </tr> 
							    <tr>  
							        <td colspan="2"> 
								            <input type="submit" class="button" name="action" value="Submit" />   
								            <input type="submit" class="button" name="action" value="Cancel" />
							        </td>  
							        <td></td>  
							        <td></td>  
							    </tr>  
							</tbody>
						</table>    
					</form:form>  
				</div>
			</div>
		</div>
</body>
</html>