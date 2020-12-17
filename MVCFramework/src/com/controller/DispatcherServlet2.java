/*
	MVC�������� �����ϴٺ���, �þ�� ��Ʈ�ѷ��� ���� ������ ���ΰ����� �����ؾ� �Ѵ�
	�̶� �ʹ� ���� ������ �����ϱⰡ ��ٷӴ� ���� ������������ ��������
	���ǰ� �����ϰ�, ���ø����̼ǿ����� ���� ����ó���� Ŭ���̾�Ʈ�� ��û�� ��ٷ� �ش� ��Ʈ�ѷ����� ó���ϰ� ���� �ʰ�,
	�߰��� ���� ��Ʈ�ѷ��� �ΰ�, ��� ��û�� �� ������Ʈ�ѷ����� ó���Ͽ� ������ ���� ��Ʈ�ѷ����� �����Ű�� ����� �̿��Ѵ�

	�� ��Ʈ�ѷ��� �����ø����̼��� ��~~~�� everything ��û�� 1�������� �޴´�
*/
package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blood.controller.BloodController;
import movie.controller.MovieController;
import movie.model.MovieAdvisor;

public class DispatcherServlet2 extends HttpServlet{
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
		if(uri.equals("/movie.do")) {	//��ȭ�� ���ϸ�
			System.out.println("��ȭ���� ��Ʈ�ѷ��� MovieController���� �����ҰԿ�");
			//���� ��Ʈ�ѷ� �����ϱ�
			controller = new MovieController();
			controller.execute(request, response);	//������� 2�ܰ� > movieController���� 3, 4�ܰ�
			
			//5�ܰ� : Ŭ���̾�Ʈ���� �˸´� ����� �����ش�
			//Ŭ���̾�Ʈ�� �Ͽ��� ������ url�� �������� ��������! �������
			//response.sendRedirect("/movie/movie_result.jsp");
			
		}else if(uri.equals("/blood.do")) {
			System.out.println("���������� ��Ʈ�ѷ��� BloodController���� �����ҰԿ�");			
			controller = new BloodController();
			
			//2�ܰ� : ���� ��Ʈ�ѷ����� ����
			controller.execute(request, response);
			
			//5�ܰ� : �˸´� ��� �����ֱ�
			//Ŭ���̾�Ʈ�� �Ͽ��� ������ url�� �������� ��������! �������
			//response.sendRedirect("/blood/blood_result.jsp");
		}
		
		
	}
	
}
