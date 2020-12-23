package com.springmvc.board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.model2.board.model.BoardDAO;

public class ListController implements Controller{
	BoardDAO boardDAO = new BoardDAO();
	
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//3단계 : 로직 객체에 일시킨다
		List boardList = boardDAO.selectAll();
		
		//4단계 : 결과 저장
		ModelAndView mav = new ModelAndView();
		mav.addObject("boardList", boardList);
		mav.setViewName("board/list");	//(/board/list.jsp 에서 '/' 까지를 접두어로! '.jsp'를 접미어로!)
		//dispatcher-servlet.xml 으로 가서 매핑해주기! props /bean
		
		return mav;
	}

}
