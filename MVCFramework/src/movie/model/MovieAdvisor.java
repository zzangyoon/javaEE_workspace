package movie.model;

public class MovieAdvisor {
	
	public String getAdvice(String movie) {
		String msg = null;
			
		if(movie.equals("�̼����ļ���5")) {
			msg = "��ũ���� �ֿ��� ���ۿ�ȭ";
		}else if(movie.equals("��Ÿ����")) {
			msg = "����� ������ ��ȭ�� ��Ÿ���� �Դϴ�";
		}else if(movie.equals("����3")) {
			msg = "����� ������ ��ȭ�� ����3 �Դϴ�";
		}else if(movie.equals("�г�������")) {
			msg = "����� ������ ��ȭ�� �г������� �Դϴ�";
		}
	return msg;
	}
}
