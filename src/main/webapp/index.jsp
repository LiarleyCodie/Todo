<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<JavaBeans> tasks = (ArrayList<JavaBeans>) request.getAttribute("tasks");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="preconnect" href="https://fonts.gstatic.com/" />
<link rel="preconnect" href="https://fonts.googleapis.com/" />

<link rel="stylesheet" href="css/styles.css" />
<title>Todo</title>
</head>
<body>
	<div class="container">
		<h1 class="title">Tasks</h1>
		<table>
			<tr>
				<th>Id</th>
				<th>Title</th>
				<th>Status</th>
				<th>Options</th>
			</tr>
			<%
			for (JavaBeans task : tasks)
			{
			%>
			<%
			String isDone = task.getIsDone() ? "Done" : "In progress";
			%>
			<%
			String checkBtn = task.getIsDone() ? "Uncheck" : "Check";
			%>
			<tr>
				<td><%=task.getId()%></td>
				<td><%=task.getTitle()%></td>
				<td><%=isDone%></td>
				<td class="options">
					<a href="check?id=<%= task.getId() %>" class="btn <%= checkBtn %>"> <%= checkBtn %> </a>
					<a href="delete?id=<%= task.getId() %>" class="btn delete">Delete</a>
				</td>
			</tr>
			<%
			}
			%>
		</table>
		<a href="add" class="btn">Add New Task</a>
	</div>
</body>
</html>