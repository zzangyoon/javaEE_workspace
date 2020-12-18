/*
	웹상의 모든 클라이언트의 요청을 받고, 응답을 전담하는 컨트롤러 정의
*/
package com.model2.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DispatcherServlet extends HttpServlet{
	FileReader fis; //3. 컨트롤러 매핑 설정파일을 읽기위한 스트림
	JSONObject controllerMap;	//6. 컨트롤러의 정보들이 들어있는 맵 (멤버변수로 빼야함)
	JSONObject viewMap;	//10-1
	
	public void init(ServletConfig config) throws ServletException {
		String contextConfigLocation = config.getInitParameter("contextConfigLocation");
		String realPath = config.getServletContext().getRealPath(contextConfigLocation);
		System.out.println(realPath);
		
		try {
			fis = new FileReader(realPath);
			JSONParser jsonParser = new JSONParser();
			
			//4. 파싱!
			JSONObject json = (JSONObject)jsonParser.parse(fis);		//>제일 바깥쪽 {} (json 파일에서)
			
			//5. 데이터에 접근!
			controllerMap = (JSONObject)json.get("controller");	//>json의 {}안의 "controller"부분!
			viewMap = (JSONObject)json.get("view");	//10-2
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
	}
	
	
	//1. doXXX형 메서드를 정의하여 요청을 받자
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//2. 2단계 : 요청을 분석한다
		String uri = request.getRequestURI();	//클라이언트가 요청시 사용한 .uri 자체가 요청의 구분값으로 사용될 수 있다
		
		//7. if문을 대신할 구조화된 데이터를 선택하자! (xml, json, properties)
		String controllerName = (String)controllerMap.get(uri);
		System.out.println("지금 들어온 요청을 처리할 컨트롤러 클래스는 "+controllerName);
		
		//9. 동생 하위 컨트롤러의 이름을 알았으니, 인스턴스를 만들고, execute(), getResultView() 호출
		Class controllerClass = null;
		Controller controller = null;
		try {
			controllerClass = Class.forName(controllerName);	//String, 즉 문자열로 지정한 클래스에 대한 실제 클래스를 반환
			controller = (Controller)controllerClass.newInstance();	//하위 컨트롤러의 인스턴스 생성 > 동생이 태어났다 아직도 2단계
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		controller.execute(request, response);	//3단계 업무 > 동생이 일 시키고 결과 저장해놓음(getResultView에 저장해놓음)
		//하위 컨트롤러로부터 넘겨받은 뷰에 대한 정보를 이용하여 클라이언트에게 알맞는 뷰를 보여주자
		//String resultView = controller.getResultView();
		//response.sendRedirect(resultView);
		String resultKey = controller.getResultView();
		System.out.println("동생 컨트롤러에게 넘겨받은 키값은 "+resultKey);
		
		//10-3 동생컨트롤러로부터 넘겨받은 키값을 이용하여 실제 페이지를 검색하고, 그 결과를 클라이언트에게 보여주기
		String viewPage = (String)viewMap.get(resultKey);	//jsp 명칭 반환!
		
		//11. 응답시 sendRedirect로 처리해야할 경우가 있고,
		//(글작성 후 리스트, 전혀 다른 페이지로 재접속을 시도하게 할때)
		//response.sendRedirect(viewPage);	//세션 믿고 까불고 있음	> 주소창에 list.jsp가 남아있다
		//재접속해도 전의 list.jsp로 되어있어서 리스트갱신이 안된다
		
		//12. 때로는 forwarding 처리해야 할 경우가 있다
		//(데이터를 유지하고 싶을때)
		//RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		//dis.forward(request, response);	//응답없이, 서버상의 또다른 자원으로 요청을 전달! >주소창에 list.do가 남아있다
		
		//메모61. 동생한테 물어보기
		if(controller.isForward()) {//때로는 forwarding 처리해야 할 경우가 있다. 데이터를 유지하고 싶을때
			RequestDispatcher dis = request.getRequestDispatcher(viewPage);
			dis.forward(request, response);		//응답없이, 서버상의 또다른 자원으로 요청을 전달! >주소창에 list.do가 남아있다
		}else {
			response.sendRedirect(viewPage);//클라이언트로 하여금 새로운 접속을 시도하게 할 경우
		}
	}
	
	//8
	public void destroy() {
		if(fis!=null) {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
