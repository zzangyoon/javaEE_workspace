/*
	������ ��� Ŭ���̾�Ʈ�� ��û�� �ް�, ������ �����ϴ� ��Ʈ�ѷ� ����
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
	FileReader fis; //3. ��Ʈ�ѷ� ���� ���������� �б����� ��Ʈ��
	JSONObject controllerMap;	//6. ��Ʈ�ѷ��� �������� ����ִ� �� (��������� ������)
	JSONObject viewMap;	//10-1
	
	public void init(ServletConfig config) throws ServletException {
		String contextConfigLocation = config.getInitParameter("contextConfigLocation");
		String realPath = config.getServletContext().getRealPath(contextConfigLocation);
		System.out.println(realPath);
		
		try {
			fis = new FileReader(realPath);
			JSONParser jsonParser = new JSONParser();
			
			//4. �Ľ�!
			JSONObject json = (JSONObject)jsonParser.parse(fis);		//>���� �ٱ��� {} (json ���Ͽ���)
			
			//5. �����Ϳ� ����!
			controllerMap = (JSONObject)json.get("controller");	//>json�� {}���� "controller"�κ�!
			viewMap = (JSONObject)json.get("view");	//10-2
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	
	}
	
	
	//1. doXXX�� �޼��带 �����Ͽ� ��û�� ����
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	protected void doRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//2. 2�ܰ� : ��û�� �м��Ѵ�
		String uri = request.getRequestURI();	//Ŭ���̾�Ʈ�� ��û�� ����� .uri ��ü�� ��û�� ���а����� ���� �� �ִ�
		
		//7. if���� ����� ����ȭ�� �����͸� ��������! (xml, json, properties)
		String controllerName = (String)controllerMap.get(uri);
		System.out.println("���� ���� ��û�� ó���� ��Ʈ�ѷ� Ŭ������ "+controllerName);
		
		//9. ���� ���� ��Ʈ�ѷ��� �̸��� �˾�����, �ν��Ͻ��� �����, execute(), getResultView() ȣ��
		Class controllerClass = null;
		Controller controller = null;
		try {
			controllerClass = Class.forName(controllerName);	//String, �� ���ڿ��� ������ Ŭ������ ���� ���� Ŭ������ ��ȯ
			controller = (Controller)controllerClass.newInstance();	//���� ��Ʈ�ѷ��� �ν��Ͻ� ���� > ������ �¾�� ������ 2�ܰ�
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		controller.execute(request, response);	//3�ܰ� ���� > ������ �� ��Ű�� ��� �����س���(getResultView�� �����س���)
		//���� ��Ʈ�ѷ��κ��� �Ѱܹ��� �信 ���� ������ �̿��Ͽ� Ŭ���̾�Ʈ���� �˸´� �並 ��������
		//String resultView = controller.getResultView();
		//response.sendRedirect(resultView);
		String resultKey = controller.getResultView();
		System.out.println("���� ��Ʈ�ѷ����� �Ѱܹ��� Ű���� "+resultKey);
		
		//10-3 ������Ʈ�ѷ��κ��� �Ѱܹ��� Ű���� �̿��Ͽ� ���� �������� �˻��ϰ�, �� ����� Ŭ���̾�Ʈ���� �����ֱ�
		String viewPage = (String)viewMap.get(resultKey);	//jsp ��Ī ��ȯ!
		
		//11. ����� sendRedirect�� ó���ؾ��� ��찡 �ְ�,
		//(���ۼ� �� ����Ʈ, ���� �ٸ� �������� �������� �õ��ϰ� �Ҷ�)
		//response.sendRedirect(viewPage);	//���� �ϰ� ��Ұ� ����	> �ּ�â�� list.jsp�� �����ִ�
		//�������ص� ���� list.jsp�� �Ǿ��־ ����Ʈ������ �ȵȴ�
		
		//12. ���δ� forwarding ó���ؾ� �� ��찡 �ִ�
		//(�����͸� �����ϰ� ������)
		//RequestDispatcher dis = request.getRequestDispatcher(viewPage);
		//dis.forward(request, response);	//�������, �������� �Ǵٸ� �ڿ����� ��û�� ����! >�ּ�â�� list.do�� �����ִ�
		
		//�޸�61. �������� �����
		if(controller.isForward()) {//���δ� forwarding ó���ؾ� �� ��찡 �ִ�. �����͸� �����ϰ� ������
			RequestDispatcher dis = request.getRequestDispatcher(viewPage);
			dis.forward(request, response);		//�������, �������� �Ǵٸ� �ڿ����� ��û�� ����! >�ּ�â�� list.do�� �����ִ�
		}else {
			response.sendRedirect(viewPage);//Ŭ���̾�Ʈ�� �Ͽ��� ���ο� ������ �õ��ϰ� �� ���
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
