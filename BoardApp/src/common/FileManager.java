/*
	���ϰ� ���õ� ������ ����� ��Ƴ��� Ŭ����
*/
package common;

public class FileManager {
	//Ȯ���ڸ� �����ϱ�
	public static String getExtend(String path) {
		int lastIndex = path.lastIndexOf(".");
		String ext = path.substring(lastIndex+1, path.length());
		
		//System.out.println(ext);
		return ext;
	}

	/*
	//�̸� �����׽�Ʈ �غ��� ����
	public static void main(String[] args) {
		//��) d:\\photo\\summer\\2010\\�������������������.jpg
		String filename="d:\\photo\\summer\\2010\\����.������.�.����.����.jpg";
		getExtend(filename);
	}
	*/
}
