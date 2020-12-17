/*
	�ڹ��� �÷��������ӿ��� ��ü �� �����Ͱ� key-value�� ������ �Ǿ��ִ� ������
	�����Ͽ� ó���� �� �ִ� ��ü�� Properties�� �Ѵ�!
	�� ������ ���� ������ ������ ���� �� ���� key-value�� �� �ָ��� �����Ѵ�!!!
*/
package study;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropTest {

	public PropTest() {
		//mapping.properties�� �ִ� key(zerg, terran ...���) �� ������ value(terrible.. )��°� ��ǥ!
		//�������� �ڹ��ڵ忡�� Ư�� ���丮�� ����ִ� ���������� ���� �����ؾ���
		FileInputStream fis = null; //���� �ϳ� �����
		Properties props = new Properties();
		
		try {
			fis = new FileInputStream("C:/workspace/javaee_workspace/MVCFramework/WebContent/WEB-INF/mapping/mapping.properties");
			props.load(fis);	//������Ƽ�� ��ü�� ��Ʈ�� ����
			
			//���ݺ��ʹ� key������ �˻��� �����ϴ�
			String value = props.getProperty("/movie.do");
			System.out.println(value);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fis!=null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		new PropTest();
	}
}
