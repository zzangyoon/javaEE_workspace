/*
	자바의 컬렉션프레임웍의 객체 중 데이터가 key-value의 쌍으로 되어있는 형식을
	전담하여 처리할 수 있는 객체를 Properties라 한다!
	이 세상의 여러 형태의 데이터 형식 중 오직 key-value로 된 쌍만을 이해한다!!!
*/
package study;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropTest {

	public PropTest() {
		//mapping.properties에 있는 key(zerg, terran ...등등) 을 넣으면 value(terrible.. )얻는게 목표!
		//실행중인 자바코드에서 특정 디렉토리에 들어있는 문서파일을 먼저 접근해야함
		FileInputStream fis = null; //빨대 하나 만든거
		Properties props = new Properties();
		
		try {
			fis = new FileInputStream("C:/workspace/javaee_workspace/MVCFramework/WebContent/WEB-INF/mapping/mapping.properties");
			props.load(fis);	//프로퍼티스 객체와 스트림 연결
			
			//지금부터는 key값으로 검색이 가능하다
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
