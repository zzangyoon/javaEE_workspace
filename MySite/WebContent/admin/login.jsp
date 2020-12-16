<%@page import="admin.member.Admin"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
	String admin_id="scott";
	String admin_pass="1234";
	
	/*
		원래는 데이터베이스에서 조회를 해야 하지만, 추후 하기로 하고 일단은 스트링으로 비교해본다
	*/
	
	String mid = request.getParameter("mid");
	String password = request.getParameter("password");
	
	//아이디가 같고, 비번까지 같다면
	if(mid.equals(admin_id)&&password.equals(admin_pass)){
		//로그인 성공에 대한 보상! 관리자 페이지 보여주기
		//js의 location.href와 동일한 기능의 jsp기능을 이용해보자
		Admin admin = new Admin();
		//관리자 정보를 VO에 담자!
		admin.setMid(mid);
		admin.setPassword(password);
		
		//jsp의 내장객체인 session 객체는 클라이언트가 신규접속이라고 생각할때, 새로운 session 인스턴스를 생성하고
		//session 아이디도 생성하여, 세션에 부여한다
		//이 세션은 클라이언트가 브라우저를 종료하지 않거나, 일정시간 내에 재접속을 할 경우 계속 사용할 수 있다
		//따라서 웹은 stateless 기반이지만, 서버측의 메모리에 생성된 세션을 이용하면 마치 연결이 유지된 것처럼 보여질 수 있다
		//주 용도) 로그인 후 회원정보를 모든 페이지에서 사용할 수 있는 기능, 장바구니 등에 사용
		session.setAttribute("ad", admin);	//("개발자가 부여하고픈 key", "심고싶은 데이터")
		System.out.println("로그인 요청시 사용중인 세션은 "+session);
		
		response.sendRedirect("/admin/index.jsp");	//클라이언트로 하여금 지정한 url로 요청을 시도
	}else{
		//로그인 실패에 대한 욕!
		out.print(getMsgBack("로그인 정보가 올바르지 않습니다"));
	}
	
%>