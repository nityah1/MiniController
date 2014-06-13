
<script type="text/javascript">
 $(document).ready(function () {
	 //$('.navbar li').first().addClass('active');

	$('.nav li').click(function(){
	    $(this).addClass('active').siblings().removeClass('active');
	});


 });	
</script>
		        
<nav class="navbar navbar-default navbar-static-top" role="navigation">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">		
		 <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
	        <span class="sr-only">Toggle navigation</span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	        <span class="icon-bar"></span>
	      </button>
		  <a class="navbar-brand" href="${pageContext.request.contextPath}/main">Mini-Controller</a>
		  <ul class="nav navbar-nav">
		    <li><a href="${pageContext.request.contextPath}/main">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/manageDevices">Manage Devices</a></li>
            <li><a href="${pageContext.request.contextPath}/admin">User Admin</a></li>
		  </ul>
		</div>
		
		<!-- Collect the nav links, forms, and other content for toggling -
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		  <ul class="nav navbar-nav">
		    <li><a href="${pageContext.request.contextPath}/main">Home</a></li>
            <li><a href="${pageContext.request.contextPath}/manageDevices">Manage Devices</a></li>
            <li><a href="${pageContext.request.contextPath}/admin">User Admin</a></li>
		  </ul>
		</div>-->
	</div>	
</nav>