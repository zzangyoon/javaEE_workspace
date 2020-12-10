<%@page import="board.model.QnADAO"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/inc/lib.jsp"%>

<%
	String qna_id = request.getParameter("qna_id");

	QnADAO dao = new QnADAO();
	
	
	//int result = dao.delete(Integer.parseInt(qna_id));
	
	

%>
