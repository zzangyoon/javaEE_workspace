/*
	�۾��� ��û�� ó���ϴ� ���� ��Ʈ�ѷ�
*/
package com.model2.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.controller.Controller;
import com.model2.domain.Notice;
import com.model2.notice.model.NoticeDAO;

public class RegistController implements Controller{
	NoticeDAO noticeDAO = new NoticeDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3�ܰ� : �˸´� ���� ��ü���� ���� ��Ų��
		//�Ķ���� vo�� ���
		Notice notice = new Notice(); //create empty vo
		//�ѱ�ó���� �̹� �����Ŀ��� ����⶧���� �����൵ �ȴ�!!!
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		int result = noticeDAO.insert(notice);	//DAO���� ���ϰ� ����
		
		//4. ������ ���� �ִٸ� ����, �������μ��� ������ ���� ����
		// ���� �������°�, �Ѱǰ�������, ��� �������� �������� ������ �ʿ�! ���� 4�ܰ� ���� ����!
	}

	public String getResultView() {
		return "/view/notice/regist";
	}
	
	public boolean isForward() {
		return false;
	}
}
