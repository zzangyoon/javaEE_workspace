/*
	������ jsp�� ����ϰ� �־��� ��Ʈ�ѷ��μ��� ������ ���� Ŭ������ �и���Ű��!
	�׷��� jsp�� ������ �������� �Ǳ� ������ ���������� ��ü���� �����ϴ�
*/
package blood.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.Controller;

import blood.model.BloodAdvisor;

public class BloodController implements Controller{

	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�Ķ���� �ޱ�
		String blood = request.getParameter("blood");
		BloodAdvisor advisor = new BloodAdvisor();
		String msg = advisor.getAdvice(blood);
	
		//����� ���� ����� �������� View�� ����ϹǷ�, �� �������� ó���ϸ� �ȵȴ�!
		//��� jsp�� �޽����� �����ַ���, ������ �޸𸮿� �ӽ������� �����س��� �ʿ䰡 �ִ�
		//����μ��� ���ǿ� ����
		HttpSession session = request.getSession();
		session.setAttribute("msg", msg);
		
	}
	
	@Override
	public String getViewPage() {
		return "/blood/blood_result.jsp";
	
	}
}
