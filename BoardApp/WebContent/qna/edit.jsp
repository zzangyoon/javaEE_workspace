<%@page import="board.model.QnADAO"%>
<%@page import="board.model.QnA"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>

<%
request.setCharacterEncoding("utf-8");
String writer = request.getParameter("writer");
String title = request.getParameter("title");
String content = request.getParameter("content");
String qna_id = request.getParameter("qna_id");

QnA qna = new QnA();
qna.setWriter(writer);
qna.setTitle(title);
qna.setContent(content);
qna.setQna_id(Integer.parseInt(qna_id));

QnADAO dao = new QnADAO();
int result = dao.update(qna);

if(result==0){
	out.print(getMsgBack("수정실패"));
}else{
	out.print(getMsgURL("수정성공", "/qna/detail.jsp?qna_id="+qna_id));
}


%>
