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

	//댓글의 등록은 비동기요청으로 들어오기 때문에, 응답정보는 페이지를 보여주는게 아니라, 데이터를 전송해야함!!!
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 값이 넘어오는지부터 확인해보자!
		String msg = request.getParameter("msg");
		String author=request.getParameter("author");
		String board_id = request.getParameter("board_id");
		
		//System.out.println("msg : "+msg);
		//System.out.println("author : "+author);
		
		//vo에 담기
		Comment comment = new Comment();
		
		comment.setMsg(msg);
		comment.setAuthor(author);
		comment.setBoard_id(Integer.parseInt(board_id));
		
		//등록메서드 호출!	memo32
		int result = commentDAO.insert(comment);
		
		//4단계 생략 (하려했으나): dml 수행결과를 저장하겠다 (할게없어서)
		request.setAttribute("result", result);	//boxing... result는 객체아 아니라 원래 못들어가지만.. 자동 boxing
	}

	public String getResultView() {
		return "/view/comment/regist";
	}

	public boolean isForward() {
		return true;
	}

}
