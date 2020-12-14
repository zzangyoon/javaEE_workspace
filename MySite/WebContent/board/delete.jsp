<%@page import="board.model.MybatisBoardDAO"%>
<%@page import="common.file.FileManager"%>
<%@page import="board.model.BoardDAO"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>

<%
	//넘겨받은 board_id를 이용하여 글 한건 삭제
	String board_id = request.getParameter("board_id");
	String filename = request.getParameter("filename");
	
	//BoardDAO dao = new BoardDAO();
	MybatisBoardDAO dao = new MybatisBoardDAO();
	//이미지 삭제하고, db 레코드 지우기
	String path = application.getRealPath("/data");
	if(FileManager.deleteFile(path+"/"+filename)){	//(경로/파일명)
		int result = dao.delete(Integer.parseInt(board_id));
		if(result==0){
			out.print(getMsgBack("게시물 삭제실패"));
		}else{
			out.print(getMsgURL("삭제 성공", "list.jsp"));
		}	
	}else{
		out.print(getMsgBack("파일을 삭제할 수 없습니다."));
	}
	
	



%>
