/*	
	��� ��Ͽ�û�� ó���ϴ� ��Ʈ�ѷ�
	��� ����� Ŭ���̾�Ʈ���� ������ �������� �����ϸ� �ȵǸ�, 
	Ŭ���̾�Ʈ�� �޾Ƽ� ȭ�鿡 ����� �����͸� ������ �Ѵ�!!!
*/
package com.model2.comment.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.comment.model.CommentDAO;
import com.model2.controller.Controller;

public class ListController implements Controller{
	CommentDAO commentDAO = new CommentDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3�ܰ� : �ڸ�Ʈ ��� ��������
		String board_id = request.getParameter("board_id");
		List commentList = commentDAO.selectAll(Integer.parseInt(board_id));
		
		//4�ܰ� : ������ ���� �ִٸ� ��� ����(request ��)
		request.setAttribute("commentList", commentList);
		
	}

	public String getResultView() {
		return "/view/comment/list";
	}

	public boolean isForward() {
		return true;
	}

}
