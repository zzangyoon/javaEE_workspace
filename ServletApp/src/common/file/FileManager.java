/*
	파일과 관련된 유용한 기능을 모아놓은 클래스
*/
package common.file;

import java.io.File;

public class FileManager {
	//확장자만 추출하기
	public static String getExtend(String path) {
		int lastIndex = path.lastIndexOf(".");
		String ext = path.substring(lastIndex+1, path.length());
		
		//System.out.println(ext);
		return ext;
	}
	
	//파일삭제
	public static boolean deleteFile(String path) {
		File file = new File(path);
		return file.delete();
	}
	
	
	/*
	//미리 단위테스트 해보기 위함
	public static void main(String[] args) {
		//예) d:\\photo\\summer\\2010\\지난여름에놀러갔던사진.jpg
		String filename="d:\\photo\\summer\\2010\\지난.여름에.놀러.갔던.사진.jpg";
		getExtend(filename);
	}
	*/
}
