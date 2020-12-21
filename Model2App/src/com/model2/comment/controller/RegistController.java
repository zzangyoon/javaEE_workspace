package com.model2.comment.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.comment.model.CommentDAO;
import com.model2.controller.Controller;
import com.model2.domain.Comment;

public class RegistController implements Controller{
	CommentDAO commentDAO = new CommentDAO();

	//����� ����� �񵿱��û���� ������ ������, ���������� �������� �����ִ°� �ƴ϶�, �����͸� �����ؾ���!!!
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�Ķ���� ���� �Ѿ���������� Ȯ���غ���!
		String msg = request.getParameter("msg");
		String author=request.getParameter("author");
		String board_id = request.getParameter("board_id");
		
		//System.out.println("msg : "+msg);
		//System.out.println("author : "+author);
		
		//vo�� ���
		Comment comment = new Comment();
		
		comment.setMsg(msg);
		comment.setAuthor(author);
		comment.setBoard_id(Integer.parseInt(board_id));
		
		//��ϸ޼��� ȣ��!	memo32
		int result = commentDAO.insert(comment);
		
		//4�ܰ� ���� (�Ϸ�������): dml �������� �����ϰڴ� (�ҰԾ��)
		request.setAttribute("result", result);	//boxing... result�� ��ü�� �ƴ϶� ���� ��������.. �ڵ� boxing
	}

	public String getResultView() {
		return "/view/comment/regist";
	}

	public boolean isForward() {
		return true;
	}

}
