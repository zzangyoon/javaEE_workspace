package blood.model;

public class BloodAdvisor {

	public String getAdvice(String blood) {
		String msg=null;
		
		if(blood.equals("A")){
			msg="�Ĳ��ϰ� �����ϴ� ���ϰ� �����ϴ�, �׷��� ���� �ҽ��ϴ�";
		}else if(blood.equals("B")){
			msg="���ڴ� ���׸ŷ� ���ϰ� Ȱ���ϴ�, �׷��� ���ڴ� ������ ���";
		}else if(blood.equals("O")){
			msg="�米�� �ְ�, �ձ۵ձ��ϴ�, �׷��� �������� �������� �д�";
		}else if(blood.equals("AB")){
			msg="������ �ڲ� �ٲ��, �����´�, 4�������̴�";
		}
		return msg;
	}
	
}
