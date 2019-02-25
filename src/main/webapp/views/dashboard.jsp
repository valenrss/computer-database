<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta charset="utf-8">
<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet" media="screen">
<link href="css/font-awesome.css" rel="stylesheet" media="screen">
<link href="css/main.css" rel="stylesheet" media="screen">
</head>
<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="/Computer-Database/Dashboard"> Computer Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle"><c:out value="${cpNumber}"></c:out> Computers found</h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="/Computer-Database/SearchComputer" method="GET" class="form-inline">

						<input type="search" id="searchbox" name="search"
							class="form-control" placeholder="Search name" /> <input
							type="submit" id="searchsubmit" value="Filter by name"
							class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="/Computer-Database/AddComputer">Add
						Computer</a> <a class="btn btn-default" id="editComputer" href="#"
						onclick="$.fn.toggleEditMode();">Edit</a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="/Computer-Database/DeleteComputer" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th>Computer name</th>
						<th>Introduced date</th>
						<!-- Table header for Discontinued Date -->
						<th>Discontinued date</th>
						<!-- Table header for Company -->
						<th>Company</th>

					</tr>
				</thead>
				<!-- Browse attribute -->

				<tbody id="results">

					<c:forEach items="${computers}" var="c">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb" class="cb" value="${c.id}"></td>
							<td><a href="EditComputer" onclick=""><c:out value="${c.name}" /></a></td>
							<td><c:out value="${c.dateIntroduced}" /></td>
							<td><c:out value="${c.dateDiscontinued}" /></td>
							<td><c:out value="${companies[c.companyId - 1].name}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</section>

	<form id="changePage" action="/Computer-Database/Dashboard" method="GET">
	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
			<ul class="pagination">
				<li><a  aria-label="Previous"><button  type="submit"  name="pageId" value="${pageId - 1}" aria-hidden="true">&laquo;</button></a></li>
			    <li <c:if test="${pageId < 3}">style="visibility: hidden;" </c:if>><a><button type="submit"  name="pageId" value="${pageId - 2}">${pageId - 2}</button></a></li>
			    <li <c:if test="${pageId < 2}">style="visibility: hidden;" </c:if>><a><button type="submit"  name="pageId" value="${pageId - 1}">${pageId - 1}</button></a></li>
				<li><a><button type="submit" class="btn btn-default" name="pageId" value="${pageId + 0}">${pageId + 0}</button></a></li>
				<li <c:if test="${pageId - 2 >= pagesCount}">style="visibility: hidden;" </c:if>><a><button  type="submit"  name="pageId" value="${pageId + 1}">${pageId + 1}</button></a></li>
				<li <c:if test="${pageId - 1 >= pagesCount}">style="visibility: hidden;" </c:if>><a><button type="submit" name="pageId" value="${pageId + 2}">${pageId + 2}</button></a></li>
				<li><a aria-label="Next"><button type="submit" name="pageId" value="${pageId + 1}" aria-hidden="true">&raquo;</button></a></li>
			</ul>

			<div class="btn-group btn-group-sm pull-right" role="group">
				<button type="submit" class="btn btn-default" name="objPerPage" value="10">10</button>
				<button type="submit" class="btn btn-default" name="objPerPage" value="50">50</button>
				<button type="submit" class="btn btn-default" name="objPerPage" value="100">100</button>
			</div>
		</div>
	</footer>
	</form>
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/dashboard.js"></script>

</body>
</html>