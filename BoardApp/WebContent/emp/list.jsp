<%@page import="emp.model.Dept"%>
<%@page import="java.util.List"%>
<%@page import="emp.model.DeptDAO"%>
<%@ page contentType="text/html; charset=utf-8"%>
<% 
	//DAO 이용하여 부서정보 출력해보자!
	DeptDAO dao = new DeptDAO();

	List<Dept> list = dao.selectAll();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
</style>
<script>
</script>
</head>
<body>
<%for(int i=0; i<list.size(); i++){ %>
	<%Dept dept = list.get(i); %>
	부서번호 <%=dept.getDeptno() %>, 부서명 <%=dept.getDname() %>, 위치 <%=dept.getLoc() %><br>

<%} %>
</body>
</html>