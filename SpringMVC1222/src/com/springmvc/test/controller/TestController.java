package com.springmvc.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/*	이 클래스는 요청을 실제적으로 처리하는 컨트롤러 임을 명시 @주석을 이용!!! 주석을 읽어들여서 controller로 만들어버림
	추구하는 목표 : POJO(Plain Old Java Object) 기반으로 가려는 경향이 강함!
*/

public class TestController implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//3단계 : 알맞는 로직 객체에 일시킨다
		String msg = "안녕";
		
		//4단계 : 저장할 것이 있다면 request 객체에 저장! (request에 저장하고싶은게 있다면 mav 안에 넣으면 된다!)
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", msg);
		
		//형님 컨트롤러가 어떤 페이지를 보여줘야할지에 대한 정보는 여전히
		mav.setViewName("test/result");	//키값을 준것이고, 밸류는 dispatcher-servlet에!
		return mav;
	}
	
}
