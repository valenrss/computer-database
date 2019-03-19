<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
			<a class="navbar-brand" href="/Computer-Database/Dashboard"><i class = "fa fa-database"></i> <spring:message code="home.title" /></a>
        </div>
    </header>

    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <h1><spring:message code="add.computer" /></h1>
                    <form action="/Computer-Database/AddComputer" method="POST">
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName"><spring:message code="computer.name" /></label>
                                <input type="text" class="form-control" id="computerName" name="computerName" placeholder="Computer name" required>
                            </div>
                            <div class="form-group">
                                <label for="introduced"><spring:message code="introduced.date" /></label>
                                <input type="date" class="form-control" id="introduced" name="introduced" placeholder="?">
                            </div>
                            <div class="form-group">
                                <label for="discontinued"><spring:message code="discontinued.date" /></label>
                                <input type="date" class="form-control" id="discontinued" name="discontinued" placeholder="?">
                            </div>
                            <div class="form-group">
                                <label for="companyId"><spring:message code="company" /></label>
                                <select class="form-control" id="companyId" name="companyId" >
                                	<c:forEach items="${companies}" var="c">
                                    	<option value="${c.id}"><c:out value="${c.name}" /></option>
                                    </c:forEach>
                                </select>
                            </div>                  
                        </fieldset>
                        <div class="actions pull-right" >
                            <input type="submit" value="<spring:message code="add.computer" />"  class="btn btn-primary" >
                              
                            <a href="/Computer-Database/Dashboard" class="btn btn-default"><spring:message code="cancel" /></a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>