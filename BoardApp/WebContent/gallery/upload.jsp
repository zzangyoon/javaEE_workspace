<%@page import="java.io.File"%>
<%@page import="common.FileManager"%>
<%@page import="java.io.IOException"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ page import="com.oreilly.servlet.MultipartRequest"%>
<%
	/*
		클라이언트가 전송한 제목 텍스트 및 바이너리 파일을 서버의 특정 디렉토리에 저장해보자
		이런 처리를  '업로드'라 한다!!!
	*/
	
	request.setCharacterEncoding("utf-8");	//파라미터에 한글깨지지 않도록 인코딩처리
	//String msg = request.getParameter("msg");	//String 메시지 받기
	
	//이미지는 글씨가 아닌 바이너리 파일이므로, request.getParameter로는 받을 수 없다!
	//따라서 IO, 네트워크 등의 처리를 해야되는데, 이 자체만으로도 하나의 개발 프로젝트일 것이다
	//해결책? 누군가가 만든 라이브러리를 이용해서 개발시간을 단축하자!
	//현재 우리가 선택한 라이브러리는 Oreilly 출판사에서 제작한 cos.jar 라는 컴포넌트이다!
	String saveDirectory="C:/workspace/javaee_workspace/BoardApp/WebContent/data";	//하드디스크의 물리적 full 경로를 명시해야 한다
	int maxSize=2*1024*1024;		//2M byte 를 용량제한으로 걸었다
	String encoding="utf-8";
	
	//FileRenamePolicy policy : 업로드시, 동일한 파일을 업로드 했을때? 자동으로 이름을 부여한다
	//예) p.jpg / p(1).jpg / p(2).jpg		(파일명은 개발자가 주도하여 명명하므로, policy를 굳이 이용할 필요 없다)
	try{
		//doc/index.html 에서 api문서 보기
		MultipartRequest multi = new MultipartRequest(request, saveDirectory, maxSize, encoding);	//(요청객체, 저장경로)	//업로드 발생!
																																			//4번째꺼 이용
		//업로드 컴포넌트를 이용할 경우, 스트링 파라미터도 업로드 컴포넌트를 이용해야 한다!
		String msg = multi.getParameter("msg");
		out.print("님이 전송한 메시지는 "+msg);
		//업로드가 완료된 후, 즉 서버의 저장소에서 파일이 존재하게 된 후 해야할일
		//파일명을 개발자가 정한 규칙으로 변경해야 한다... 현재시간의 밀리세컨즈까지 구해보자
		long time = System.currentTimeMillis();	//160704795953
		//구한 시간에 확장자를 붙이면 최종적으로 160704795953.jpg
		out.print(time);
		
		//방금 업로드한 파일명 알아맞히기 (업로드 컴포넌트가 알고있다... api 조사)
		String ori = multi.getOriginalFileName("photo");
		out.print("당신이 업로드한 로컬 원래 파일명은 "+ori);
		
		String ext = FileManager.getExtend(ori);
		
		String filename = time+"."+ext;
		
		out.print("내가 조작한 파일명은 "+filename);
		
		//조작한 이름으로 파일명을 바꿔야함
		//결국 파일을 다루어야 하므로 javaSE의 File 클래스를 이용하면 된다
		//File 클래스의 .api문서를 찾아서, 파일명을 바꾸는 메서드를 찾아보기
		//renameTo(File dest) : 매개변수로는 새롭게 이름을 주고싶은 대상을 넣으면 된다!
		//dest : 데스트네이션 - 목적파일을 말한다
		//기존 File클래스의.renameTo(새롭게 만들파일);
		File savedFile = multi.getFile("photo");
		savedFile.renameTo(new File(saveDirectory+"/"+filename));	//파일명 교체!
		
		//클라이언트에게 전송할 응답정보를 가진 객체
		//클라이언트의 브라우저로 하여금, 지정한 URL로 재접속을 시도하게 만듬
		//response.sendRedirect("/gallery/photo_list.jsp");
	}catch(IOException e){
		e.printStackTrace();	//콘솔로그에 에러출력, 관리자에게 이메일, sms... 복구는 안된다! 원인만 출력
		out.print("업로드 용량이 너무 큽니다");	//써블릿 쓰레드 에러... (servlet 클래스를 다뤄야 함...)
	}
%>