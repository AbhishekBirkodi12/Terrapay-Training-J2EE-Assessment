<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Bug Management Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: tomato">
			<div>
				<a href="" class="navbar-brand"> Bug Management App </a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">Bug</a></li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${bug != null}">
					<form action="update" method="post">
				</c:if>
				<c:if test="${bug == null}">
					<form action="insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${bug != null}">
            			Edit Bug
            		</c:if>
						<c:if test="${bug == null}">
            			Add New Bug
            		</c:if>
					</h2>
				</caption>

				<c:if test="${bug != null}">
					<input type="hidden" name="id" value="<c:out value='${bug.id}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Email</label> <input type="text"
						value="<c:out value='${bug.email}' />" class="form-control"
						name="email" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Priority</label> <input type="text"
						value="<c:out value='${bug.priority}' />" class="form-control"
						name="priority">
				</fieldset>

				<fieldset class="form-group">
					<label>Status</label> <input type="text"
						value="<c:out value='${bug.status}' />" class="form-control"
						name="status">
				</fieldset>
				<fieldset class="form-group">
					<label>Project Owner</label> <input type="text"
						value="<c:out value='${bug.projectOwner}' />" class="form-control"
						name="projectOwner">
				</fieldset>
				<fieldset class="form-group">
					<label>Creation Date</label> <input type="text"
						value="<c:out value='${bug.creationDate}' />" class="form-control"
						name="creationDate">
				</fieldset>
				<fieldset class="form-group">
					<label>Completion Date</label> <input type="text"
						value="<c:out value='${bug.completionDate}' />" class="form-control"
						name="completionDate">
				</fieldset>
				<fieldset class="form-group">
					<label>Description</label> <input type="text"
						value="<c:out value='${bug.description}' />" class="form-control"
						name="description">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>