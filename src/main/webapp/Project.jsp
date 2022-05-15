<%@page import="com.Project"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script src="Components/jquery-3.6.0.js" type="text/javascript"></script>
<script src="Components/project.js" type="text/javascript"></script>

<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
<title>Project Management</title>
</head>
<body>
<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Project Management</h1>
				
				<form id="formProject" name="formProject" action="">
						
					Project Name: <input id="projectName" name="projectName" type="text" class="form-control form-control-sm"> <br> 
						
					Project Description: <input id="projectDescription" name="projectDescription" type="text" class="form-control form-control-sm"> <br> 
						
					Project Start Date: <input id="projectStartdate" width="276" name="projectStartdate" type="text" class="form-control form-control-sm"> <br>
					
					Project End Date: <input id="projectEnddate" width="276" name="projectEnddate" type="text" class="form-control form-control-sm"> <br>
					
					Project Budget: <input id="projectBudget" name="projectBudget" type="text" class="form-control form-control-sm"> <br>
					
					Project Price: <input id="projectPrice" name="projectPrice" type="text" class="form-control form-control-sm"> <br>
					
					Project SponserId: <input id="projectSponserId" name="projectSponserId" type="text" class="form-control form-control-sm"> <br>
					 
					<input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary"> 
					<input type="hidden" id="hidProjectIDSave" name="hidProjectIDSave" value="">
					
				</form>
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divProjectGrid">
					<%
					Project projectObj = new Project();
					out.print(projectObj.readProject());
					%>
				</div> 
			</div>
		</div>
	</div>
	
	<script>
        $('#projectStartdate').datepicker({
            uiLibrary: 'bootstrap4'
        });
        
        $('#projectEnddate').datepicker({
            uiLibrary: 'bootstrap4'
        });
    </script>
</body>
</html>