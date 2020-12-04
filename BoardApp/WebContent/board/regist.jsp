<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="board.model.Notice"%>
<%@ page import="board.model.NoticeDAO"%>
<%@ include file="/inc/lib.jsp" %>

<%
	NoticeDAO noticeDAO = new NoticeDAO();

	//클라이언트가 전송한 파라미터를 받아서 mysql에 넣을 예정이므로,
	//별도의 디자인 코드는 필요하지 않음
	out.print("이 페이지에서 클라이언트가 전송한 파라미터들을 데이터베이스에 넣을 예정");

	//파라미터가 영문이 아닌경우 깨진다... 따라서 파라미터를 대상으로 한 인코딩을 원하는 언어로 지정하면된다
	//주의!!! 파라미터를 받기 전에 미리 세팅해야한다!!!
	request.setCharacterEncoding("utf-8");	//한국어, 중국어, 아랍어 전세계 모든언어가 안깨짐

	//jsp가 지원하는 내장객체 중, request객체를 이용하여 클라이언트의 전송 파라미터를 받아보자
	//String 원하는 변수 = request.getParameter("변수명");
	String author = request.getParameter("author");	//작성자
	String title = request.getParameter("title");	//제목
	String content = request.getParameter("content");	//내용

	out.print("전송한 author : "+author);
	out.print("전송한 title : "+title);
	out.print("전송한 content : "+content);

	//등록
	Notice notice = new Notice();
	notice.setAuthor(author);
	notice.setTitle(title);
	notice.setContent(content);
	
	int result = noticeDAO.regist(notice);	//vo, dto
	
	if(result==0){
		//욕하고 뒤로가기
		out.print(getMsgBack("등록실패"));
	}else{
		//메시지 출력 후 list.jsp요청
		out.print(getMsgURL("등록성공", "/board/list.jsp"));
	}
%>