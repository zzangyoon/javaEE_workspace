/*
	�ڹٿ��� Ŭ������ �ν��Ͻ��� �����ϴ� ������� new �����ڸ� �ִ°��� �ƴϴ�!
*/
package study;

import java.lang.reflect.Method;

public class InstanceTest {

	public static void main(String[] args) {
		try {
			Class dogClass= Class.forName("study.Dog");	//Ŭ���� �ε�
			System.out.println("�ε� ����");
			Method[] methods = dogClass.getMethods();
			for(Method m:methods) {
				System.out.println(m.getName());
			}
			
			//DogŬ������ new�����ڸ� ���� �ʰ� �÷�����
			Dog dog = (Dog)dogClass.newInstance();
			System.out.println(dog.getName());
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("�ε� ����");
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	
}
