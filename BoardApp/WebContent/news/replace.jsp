<%@page import="board.model.NewsDAO"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
	String news_id = request.getParameter("news_id");
	NewsDAO dao = new NewsDAO();
	int result  = dao.replace(Integer.parseInt(news_id));
	
	if(result==0){
		out.print(getMsgBack("삭제실패"));
	}else{
		out.print(getMsgURL("삭제되었습니다", "list.jsp"));
	}
%>