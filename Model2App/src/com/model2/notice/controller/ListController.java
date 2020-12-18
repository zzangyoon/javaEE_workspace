/*
	�����Խ����� ��Ͽ�û�� ó���ϴ� ���� ~
*/
package com.model2.notice.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.controller.Controller;
import com.model2.notice.model.NoticeDAO;

public class ListController implements Controller{
	NoticeDAO noticeDAO = new NoticeDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3�ܰ� : �˸´� ������ü���� �Ͻ�Ų��
		List list = noticeDAO.selectAll();
		
		//4�ܰ� : Ŭ���̾�Ʈ�� ������ ����� �ִٸ�, ����� �������� (�׷��� DispatcherServlet ��Ʈ�ѷ��� ����Ҽ�������)
		HttpSession session = request.getSession();
		//session.setAttribute("noticeList", list);
		request.setAttribute("noticeList", list);	//50
	
		//5�ܰ� : �˸´� �並 �����ش� > DispatcherServlet�� ����
	}

	@Override
	public String getResultView() {
		return "/view/notice/list";	//key���� �ѱ��
	}
	
	@Override
	public boolean isForward() {
		return true;	//��û�� �����ǰԲ�!! �������� ��Ź!
	}
	
}
