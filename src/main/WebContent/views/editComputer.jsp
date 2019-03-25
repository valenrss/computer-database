<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
    <header class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">
			<a class="navbar-brand" href="/Computer-Database/Dashboard"><i class = "fa fa-database"></i> Computer Database </a>
        </div>
    </header>
    <section id="main">
        <div class="container">
            <div class="row">
                <div class="col-xs-8 col-xs-offset-2 box">
                    <div class="label label-default pull-right">
                        id: ${cpEditId}
                    </div>
                    <h1>Edit Computer</h1>

                    <form action="/Computer-Database/EditComputer" method="POST">
                        <input type="hidden" value="${cpEditId}" id="id" name = "cpEditId"/> 
                        <fieldset>
                            <div class="form-group">
                                <label for="computerName">Computer name</label>
                                <input type="text" class="form-control" id="computerName" name = "name" value="${cpEdit.name}" required>
                            </div>
                            <div class="form-group">
                                <label for="introduced">Introduced date</label>
                                <input type="date" class="form-control" id="introduced" name = "dateintroduced" value='${fn:substringBefore(cpEdit.dateIntroduced," 00:00:00.0")}'>
                            </div>
                            <div class="form-group">
                                <label for="discontinued">Discontinued date</label>
                                <input type="date" class="form-control" id="discontinued" name = "datediscontinued" value='${fn:substringBefore(cpEdit.dateDiscontinued," 00:00:00.0")}'>
                            </div>
                            <div class="form-group">
                                <label for="companyId">Company</label>
								<select class="form-control" id="companyId" name="companyid">
									<c:forEach items="${companies}" var="c">
										<c:choose>
											<c:when test="${c.id == cpEdit.company.id}">
												<option value="${c.id = cpEdit.company.id}"
													selected="selected"><c:out
														value="${c.name = cpEdit.company.name}" /></option>
											</c:when>
											<c:otherwise>
												<option value="${c.id}"><c:out value="${c.name}" /></option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>            
                        </fieldset>
                        <div class="actions pull-right">
                            <input type="submit" value="Edit" class="btn btn-primary">
                              
                            <a href="/Computer-Database/Dashboard" class="btn btn-default">Cancel</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </section>
</body>
</html>