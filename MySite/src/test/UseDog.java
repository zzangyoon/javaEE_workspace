package test;

public class UseDog {
	public static void main(String[] args) {
		//new ���� �ʰ� Dog�� ����
		Dog d = Dog.getInstance();
		System.out.println(d);
		
		Dog d2 = Dog.getInstance();
		System.out.println(d2);
		
		Dog d3 = Dog.getInstance();
		System.out.println(d3);
	}
}
