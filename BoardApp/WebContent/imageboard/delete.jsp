<%@page import="board.model.ImageBoardDAO"%>
<%@page import="java.io.File"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>

<%
	//넘겨받은 board_id 를 이용하여 삭제
	String board_id = request.getParameter("board_id");
	String filename = request.getParameter("filename");

	out.print("지우게될 게시물 board_id는 "+board_id);
	
	//삭제업무(DB삭제 + 파일삭제)
	
	//파일삭제(파일을 다룰 수 있는 클래스 : java.io.File)
	File file = new File("C:/workspace/javaee_workspace/BoardApp/WebContent/data/"+filename);
	if(file.delete()){	//file.delete()가 참이라면 : 파일 삭제한다면 > db삭제
		//db삭제
		ImageBoardDAO dao = new ImageBoardDAO();
		dao.delete(Integer.parseInt(board_id));
		out.print(getMsgURL("삭제되었습니다", "/imageboard/list.jsp"));
	}else{
		out.print(getMsgBack("삭제 처리에 실패했습니다"));
	}
	
	
	//DB삭제
	
%>