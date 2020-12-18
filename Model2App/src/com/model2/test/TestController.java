/*
	이 클래스는
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
		//3단계 : 지금 테스트이므로, 그냥 일을 이렇게 시킨다
		String msg="테스트입니다";
		
		//4단계 : 결과 저장
		HttpSession session = request.getSession();
		session.setAttribute("result", msg);
	}

	@Override
	public String getResultView() {
		return "/view/test/result";	//test폴더 만들고, 그안에 result.jsp 만들자!
												//파일명이 하드코딩 되어있다 > mapping.json에서 처리하자!
	}

	
}
