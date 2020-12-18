/*
	��� ���� ��Ʈ�ѷ��� �ݵ�� �����ؾ��� �޼��带 �����Ѵ�!
*/
package com.model2.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
	//�˸´� ����Ͻ� ��ü�� �� ��Ű��
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

	//� ���������� ������� ���� ����
	//���� ���� ��Ʈ�ѷ��� �� ������ ó������ ������, DispatcherServlet���� if������ ó���ؾ� �Ѵ�...
	public String getResultView();

	//�޸�59. ��û�� ����� ����, �����ؾ� ������ �����ϴ� �޼��� (forwarding ���� ����)
	public boolean isForward();
}
