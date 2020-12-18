/*
	공지게시판의 목록요청을 처리하는 하위 ~
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
		//3단계 : 알맞는 로직객체에게 일시킨다
		List list = noticeDAO.selectAll();
		
		//4단계 : 클라이언트가 봐야할 결과가 있다면, 결과를 저장하자 (그래야 DispatcherServlet 컨트롤러가 사용할수있으니)
		HttpSession session = request.getSession();
		//session.setAttribute("noticeList", list);
		request.setAttribute("noticeList", list);	//50
	
		//5단계 : 알맞는 뷰를 보여준다 > DispatcherServlet의 역할
	}

	@Override
	public String getResultView() {
		return "/view/notice/list";	//key값만 넘긴다
	}
	
	@Override
	public boolean isForward() {
		return true;	//요청이 유지되게끔!! 형님한테 부탁!
	}
	
}
