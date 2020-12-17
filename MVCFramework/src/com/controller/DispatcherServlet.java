/*
	MVC�������� �����ϴٺ���, �þ�� ��Ʈ�ѷ��� ���� ������ ���ΰ����� �����ؾ� �Ѵ�
	�̶� �ʹ� ���� ������ �����ϱⰡ ��ٷӴ� ���� ������������ ��������
	���ǰ� �����ϰ�, ���ø����̼ǿ����� ���� ����ó���� Ŭ���̾�Ʈ�� ��û�� ��ٷ� �ش� ��Ʈ�ѷ����� ó���ϰ� ���� �ʰ�,
	�߰��� ���� ��Ʈ�ѷ��� �ΰ�, ��� ��û�� �� ������Ʈ�ѷ����� ó���Ͽ� ������ ���� ��Ʈ�ѷ����� �����Ű�� ����� �̿��Ѵ�

	�� ��Ʈ�ѷ��� �����ø����̼��� ��~~~�� everything ��û�� 1�������� �޴´�
*/
package com.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blood.controller.BloodController;
import movie.controller.MovieController;
import movie.model.MovieAdvisor;

public class DispatcherServlet extends HttpServlet{
	
	FileInputStream fis;
	Properties props;
	
	//init�� ������ �����ֱ⿡��, ���� �����ڿ� ���� ��Ĺ�� �ν��Ͻ��� �����ϸ�, �̿� ���ÿ� �ʱ�ȭ �޼���μ� ȣ��ȴ�
	public void init(ServletConfig config) throws ServletException {
		//getRealPath�� jsp�� ���尴ü �� application�� ���� ������ ���� application ���尴ü���� ������
		ServletContext context = config.getServletContext();
		String contextConfigLocation = config.getInitParameter("contextConfigLocation");	//web.xml���� ����
		String savePath = context.getRealPath(contextConfigLocation);
		System.out.println(savePath);
		try {
			fis = new FileInputStream(savePath);
			props = new Properties();
			props.load(fis); 	//��Ʈ���� ������Ƽ�� ����
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doRequest(request, response);
	}
	
	//get or post �������, ��� ��û�� �� �޼��忡�� ó������!
	public void doRequest(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");	//�Ķ���Ϳ� ���� ���ڵ�
		
		//1�ܰ� : ��û�� �޴´�!
		System.out.println("���� ��û�� �޾Ҿ��!");
		//Ŭ���̾�Ʈ�� ��ȭ�� ���ϸ�? -> ��ȭ��� ��Ʈ�ѷ����� ����
		//Ŭ���̾�Ʈ�� �������� ���ϸ�? -> ��������� ��Ʈ�ѷ����� ����
		
		//2�ܰ� : ��û�� �м��Ͽ�, �˸´� ��Ʈ�ѷ����� ��û�� ����
		//�۾���? ����? ������? ��ȭ? �ڵ�������? �� ���ϴ���...
		//Ŭ���̾�Ʈ�� ���ϴ°� ���������� �˾ƾ� �Ѵ�
		//�ش��� Ŭ���̾�Ʈ ��û ��ü�� �ִ�... �� ��û�� ���� URI�� �� ��û ���а��̴�!
		String uri = request.getRequestURI();
		System.out.println("���� ���� ��û�� "+uri);	
		
		Controller controller = null;
		
		String className = null;
		
		//if�� ���, ������Ƽ�� ��ü�� �̿��Ͽ� key������ �޸𸮿� �÷��� ��Ʈ�ѷ��� �̸��� value�� ��ȯ����
		className = props.getProperty(uri);	//uri �� key ���̴�!!! (key : /movie.do)
		
		/*
		if(uri.equals("/movie.do")) {	//��ȭ�� ���ϸ�
			className="movie.controller.MovieController";
			
			//System.out.println("��ȭ���� ��Ʈ�ѷ��� MovieController���� �����ҰԿ�");
			//���� ��Ʈ�ѷ� �����ϱ�
			//controller = new MovieController();
			//controller.execute(request, response);	//������� 2�ܰ� > movieController���� 3, 4�ܰ�
			
			//5�ܰ� : Ŭ���̾�Ʈ���� �˸´� ����� �����ش�
			//Ŭ���̾�Ʈ�� �Ͽ��� ������ url�� �������� ��������! �������
			//response.sendRedirect("/movie/movie_result.jsp");
			
		}else if(uri.equals("/blood.do")) {
			className="blood.controller.BloodController";
			
			//System.out.println("���������� ��Ʈ�ѷ��� BloodController���� �����ҰԿ�");			
			//controller = new BloodController();
			
			//5�ܰ� : �˸´� ��� �����ֱ�
			//Ŭ���̾�Ʈ�� �Ͽ��� ������ url�� �������� ��������! �������
			//response.sendRedirect("/blood/blood_result.jsp");
		}
		*/
		try {
			Class controllerClass = Class.forName(className);	//Ŭ���� �ε�
			//�ν��Ͻ� ����
			controller = (Controller)controllerClass.newInstance();
			
			
			//2�ܰ� : ���� ��Ʈ�ѷ����� ����
			controller.execute(request, response);	//���������� ����� (������)
			//5�ܰ� : �˸´� ��� �����ֱ�
			response.sendRedirect(controller.getViewPage());	//������Ʈ�ѷ����� ��� ��

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}		
	}
	//������ �����ֱ� �޼��� �� ������ �Ҹ��Ҷ� ȣ��Ǵ� �޼����� destroy()��
	//��Ʈ�� ���� �ڿ��� �ݴ� ó���� ����!
	@Override
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
