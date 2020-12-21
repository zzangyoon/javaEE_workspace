package com.model2.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.board.model.BoardDAO;
import com.model2.controller.Controller;
import com.model2.domain.Board;

public class DetailController implements Controller{
	BoardDAO boardDAO = new BoardDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//3�ܰ� : �˸´� ���� ��ü�� �� ��Ű��
		String board_id = request.getParameter("board_id");
		Board board = boardDAO.select(Integer.parseInt(board_id));
		
		//4�ܰ�:Ŭ���̾�Ʈ�� ���Ե� ����� ����(�޸�:��û ��ü�� ����...)
		request.setAttribute("board", board);
		//System.out.println("�󼼺��� ��Ʈ�ѷ����� �Ѱܹ��� ��ü "+request);	//memo19
		//System.out.println("�󼼺��� ��Ʈ�ѷ����� board "+board);	//memo21	> detail.jsp�� ���� ���´�!!!
	}

	public String getResultView() {
		return "/view/board/detail";
	}

	public boolean isForward() {
		return true;
	}

}
