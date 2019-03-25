<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
			<a class="navbar-brand" href="/Computer-Database/"><i
				class="fa fa-database"></i> <spring:message code="home.title" /> </a>
		</div>



	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">
				<i class="fa fa-desktop "></i>
				<c:out value="     ${cpNumber}"></c:out>
				<spring:message code="computers.found" />
			</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="/Computer-Database/"
						method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control"
							placeholder="<spring:message code="search.hint" />"
							value="${search}" />
						<button type="submit" id="searchsubmit" class="btn btn-primary">
							<i class="fa fa-search"></i>
						</button>
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer"
						href="/Computer-Database/AddComputer"><i class="fa fa-plus"></i>
						<spring:message code="add.computer" /></a> <a class="btn btn-default"
						id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message
							code="edit.list" /></a> <a class="btn btn-danger" id="deleteCompany"
						href="/Computer-Database/DeleteCompany"><spring:message
							code="delete.company" /></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="/Computer-Database/DeleteComputer"
			method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">


			<table class="table table-striped table-bordered">
				<thead>

					<tr>
						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"><a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"><i
									class="fa fa-trash-o fa-lg"></i></a> </span></th>

						<th><spring:message code="computer.name" /><a
							style="float: right;" class="btn btn-default"
							href="/Computer-Database/?sortOption=nameDesc&search=${search}">&#9660;</a>
							<a style="float: right;" class="btn btn-default"
							href="/Computer-Database/?sortOption=name&search=${search}">&#9650;</a></th>

						<th><spring:message code="introduced.date" /><a
							style="float: right;" class="btn btn-default"
							href="/Computer-Database/?sortOption=introdateDesc&search=${search}">&#9660;</a>
							<a style="float: right;" class="btn btn-default"
							href="/Computer-Database/?sortOption=introdate&search=${search}">&#9650;</a></th>
						<th><spring:message code="discontinued.date" /><a
							style="float: right;" class="btn btn-default"
							href="/Computer-Database/?sortOption=discondateDesc&search=${search}">&#9660;</a>
							<a style="float: right;" class="btn btn-default"
							href="/Computer-Database/?sortOption=discondate&search=${search}">&#9650;</a></th>
						<th><spring:message code="company" /><a
							style="float: right;" class="btn btn-default"
							href="/Computer-Database/?sortOption=companyDesc&search=${search}">&#9660;</a>
							<a class="btn btn-default" style="float: right;"
							href="/Computer-Database/?sortOption=company&search=${search}">&#9650;</a></th>

					</tr>

				</thead>

				<tbody id="results">

					<c:forEach items="${computers}" var="c">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value="${c.id}"></td>
							<td><a
								href="EditComputer?cpEditId=<c:out value="${c.id}" />"
								onclick=""><c:out value="${c.name}" /></a></td>
							<td><c:out value="${c.dateIntroduced}" /></td>
							<td><c:out value="${c.dateDiscontinued}" /></td>
							<td><c:out value="${c.companyName}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

		</div>
	</section>


	<footer class="navbar-fixed-bottom">
		<span style="float: left; margin-top: 17px; margin-left: 10px">
			<a href="?lang=en"><img alt="" src="images/flag_en.png"> <spring:message code="lang.english" /></a> | 
			<a href="?lang=fr"><img alt="" src="images/flag_fr.png"> <spring:message code="lang.french" /></a>
		</span>

		<div class="container text-center">
			<ul class="pagination">
				<li <c:if test="${pageId < 2}">style="visibility: hidden;" </c:if>><a
					aria-label="Previous"
					href="/Computer-Database/?pageId=${pageId - 1}&search=${search}&sortOption=${sortOption}&objPerPage=${objPerPage}">&laquo;</a></li>
				<li <c:if test="${pageId < 3}">style="visibility: hidden;" </c:if>><a
					href="/Computer-Database/?pageId=${pageId - 2}&search=${search}&sortOption=${sortOption}&objPerPage=${objPerPage}">${pageId - 2}</a></li>
				<li <c:if test="${pageId < 2}">style="visibility: hidden;" </c:if>><a
					href="/Computer-Database/?pageId=${pageId - 1}&search=${search}&sortOption=${sortOption}&objPerPage=${objPerPage}">${pageId - 1}</a></li>
				<li><a
					href="/Computer-Database/?pageId=${pageId}&search=${search}&sortOption=${sortOption}&objPerPage=${objPerPage}">${pageId}</a></li>
				<li
					<c:if test="${pageId - 2 >= pagesCount}">style="visibility: hidden;" </c:if>><a
					href="/Computer-Database/?pageId=${pageId + 1}&search=${search}&sortOption=${sortOption}&objPerPage=${objPerPage}">${pageId + 1}</a></li>
				<li
					<c:if test="${pageId - 1 >= pagesCount}">style="visibility: hidden;" </c:if>><a
					href="/Computer-Database/?pageId=${pageId + 2}&search=${search}&sortOption=${sortOption}&objPerPage=${objPerPage}">${pageId + 2}</a></li>
				<li
					<c:if test="${pageId - 2 >= pagesCount}">style="visibility: hidden;" </c:if>><a
					aria-label="Next"
					href="/Computer-Database/?pageId=${pageId + 1}&search=${search}&sortOption=${sortOption}&objPerPage=${objPerPage}">&raquo;</a></li>
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<a type="submit" class="btn btn-default"
					href="/Computer-Database/?objPerPage=10&search=${search}&pageId=${pageId}&sortOption=${sortOption}">10</a>
				<a type="submit" class="btn btn-default"
					href="/Computer-Database/?objPerPage=50&search=${search}&pageId=${pageId}&sortOption=${sortOption}">50</a>
				<a type="submit" class="btn btn-default"
					href="/Computer-Database/?objPerPage=100&search=${search}&pageId=${pageId}&sortOption=${sortOption}">100</a>
			</div>
		</div>
	</footer>

	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>

</body>
</html>