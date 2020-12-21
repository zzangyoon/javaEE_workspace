/*	�۾��� ��û�� ó���ϴ� ��Ʈ�ѷ�	*/
package com.model2.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.board.model.BoardDAO;
import com.model2.controller.Controller;
import com.model2.domain.Board;

public class RegistController implements Controller{
	BoardDAO boardDAO = new BoardDAO();
	
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		Board board = new Board();
		board.setTitle(title);
		board.setWriter(writer);
		board.setContent(content);
		boardDAO.insert(board);	//3�ܰ� : �˸´� ���� ��ü�� �� ��Ű��!
		
		//4�ܰ� : ������ ���� ����!!!!! (Ŭ���̾�Ʈ�� �Ͽ���, ������ url�� �������� ��������)
	}

	public String getResultView() {
		return "/view/board/regist";
	}

	public boolean isForward() {
		return false;
	}

}
