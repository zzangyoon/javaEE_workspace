/*
	�� Ŭ������
*/
package com.model2.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.controller.Controller;

public class TestController implements Controller{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3�ܰ� : ���� �׽�Ʈ�̹Ƿ�, �׳� ���� �̷��� ��Ų��
		String msg="�׽�Ʈ�Դϴ�";
		
		//4�ܰ� : ��� ����
		HttpSession session = request.getSession();
		session.setAttribute("result", msg);
	}

	@Override
	public String getResultView() {
		return "/view/test/result";	//test���� �����, �׾ȿ� result.jsp ������!
												//���ϸ��� �ϵ��ڵ� �Ǿ��ִ� > mapping.json���� ó������!
	}

	
}
