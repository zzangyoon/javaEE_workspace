<%@ page contentType="text/html; charset=utf-8"%>
<html>
<head>
<%@ page
import="java.sql.*,
javax.sql.*,
java.io.*,
javax.naming.InitialContext,
javax.naming.Context" %>
</head>
<body>
<h1>JDBC JNDI Resource Test</h1>

<%
/*
JNDI 란?
- Java Naming Directory Interface
	: 어떤 정보를 프로그래밍 언어인 자바코드에 넣지 말고, 외부의 xml과 같은 자원으로 관리하는 방법
		(즉, 자바코드안에 설정정보를 넣지 말고, 코드 밖으로 빼서 유지관리하자!)
	
*/

InitialContext initCtx = new InitialContext();	//검색객체

DataSource ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/myoracle");	//java:comp/env 이건 정해진거... 암기!

//새로운 접속이 아니라, 이미 풀에 존재하는 접속 객체를 대여하는 것!!!!!!!
Connection conn = ds.getConnection();	//커넥션풀로부터 하나의 커넥션을 얻는 작업! (튜브를 빌리러 가는...)
Statement stmt = conn.createStatement();
ResultSet rset = stmt.executeQuery("select * from board");
while (rset.next()) {
	out.println("title=="+rset.getString("title")+"<br>");
}
rset.close();
stmt.close();
conn.close();	//여기서 풀로 돌려보낸다 (빌린 튜브반납)
initCtx.close();
%>
</body>
</html>