/*
	글쓰기 요청을 처리하는 전담 컨트롤러
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
		//3단계 : 알맞는 로직 객체에게 일을 시킨다
		//파라미터 vo에 담기
		Notice notice = new Notice(); //create empty vo
		//한글처리는 이미 디스패쳐에서 해줬기때문에 안해줘도 된다!!!
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		notice.setTitle(title);
		notice.setWriter(writer);
		notice.setContent(content);
		
		int result = noticeDAO.insert(notice);	//DAO가서 일하고 오기
		
		//4. 저장할 것이 있다면 저장, 지금으로서는 저장할 것이 없다
		// 뭔가 가져오는것, 한건가져오기, 모두 가져오기 같은것이 저장이 필요! 따라서 4단계 생략 가능!
	}

	public String getResultView() {
		return "/view/notice/regist";
	}
	
	public boolean isForward() {
		return false;
	}
}
