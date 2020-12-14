<%@page import="board.model.MybatisBoardDAO"%>
<%@page import="board.model.BoardDAO"%>
<%@page import="common.file.FileManager"%>
<%@page import="board.model.Board"%>
<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ include file="/inc/lib.jsp" %>
<%
	//multipart/form-data 방식으로 전송된 파라미터는 업로드 컴포넌트를 통해서 처리해야 한다
	
	//업로드 설정은 DiskFileItemFactory에 한다!
	DiskFileItemFactory factory = new DiskFileItemFactory();
	//자바기반의 웹어플리케이션은 어떤 OS건 중립적 실행이 보장되어야 하므로, 특정시스템에 의존하는 경로를 사용해서는 안된다
	//해결책? 개발자가 경로를 넣으려고 하지말고, 프로그래밍에서 시스템의 경로를 동적으로 얻어와서 이용한다
	//이때 사용할 jsp의 내장객체가 바로 application 내장객체이다!
	//application 내장객체는, 현재 어플리케이션의 전역적 정보를 가진 객체이므로, 
	//어떤 디렉토리에서 사이트가 실행되는지 조차 스스로 알아낼 수 있다
	//request, response, out, !!!application!!!
	String realPath = application.getRealPath("/data");	//웹사이트의 루트(WebContent)를 기준으로 특정 파일이나, 디렉토리를 명시하면 
																			//스스로 현재 웹사이트가 얹혀진 os로부터 풀경로를 구해준다
	//out.print(realPath);
																			
	factory.setRepository(new File(realPath));	//임시적으로 사용할 경로
	factory.setSizeThreshold(2*1024*1024);	//2M
	factory.setDefaultCharset("utf-8");
	
	//아래의 객체가 업로드된 정보를 가지고 있으므로, 파라미터 등도 뽑아낼 수 있다
	ServletFileUpload upload = new ServletFileUpload(factory);
	List<FileItem> items = upload.parseRequest(request);	//요청 객체로부터 업로드 정보 뽑기!
	
	Board board = new Board();
	
	boolean flag = false;	//업로드가 완료되었는지 여부를 알 수 있는 변수
	
	//BoardDAO dao = new BoardDAO();
	MybatisBoardDAO dao = new MybatisBoardDAO();
	
	for(FileItem item: items){
		if(item.isFormField()){	//text 입력기반의 컴포넌트라면...
			//out.print("getName:"+item.getName()+",<br>getFieldName:"+item.getFieldName());
			if(item.getFieldName().equals("title")){
				board.setTitle(item.getString());
			}else if(item.getFieldName().equals("writer")){
				board.setWriter(item.getString());
			}else if(item.getFieldName().equals("content")){
				board.setContent(item.getString());
			}
		}else{	//파일이라면...
			out.print(item.getName());	//이름을 알고있으므로, 우리가 원하는 경로에 파일을 생성하자!
			/* 원하는 파일명 생성! */
			long time = System.currentTimeMillis();	//시간구하기
			String newName = time+"."+FileManager.getExtend(item.getName());	//시간.확장자
			board.setFilename(newName);
			
			File file = new File(realPath+"/"+newName);	
			item.write(file);	//하드디스크에 파일 생성
			
			//out.print("업로드 완료");
			flag=true;
		}
	}
	if(flag){	//업로드가 성공되면 insert !!!
		int result = dao.insert(board);
	
		if(result==0){
			out.print(getMsgBack("등록실패"));
		}else{
			out.print(getMsgURL("등록성공", "/board/list.jsp"));
		}
	
	}
%>