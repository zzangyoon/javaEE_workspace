/*
	요리사를 정의한다
*/
package food;

public class Cook {
	//상위 자료형으로 has a 관계를 명시했을때 얻는 장점?
	//	- 하위 자료형을 삭제하거나, 변화가 생기더라도 현재 클래스와 has a 관계에 있는 클래스간
	//	   의존성을 약화시켰기 때문에 유지보수성이 좋아짐
	
	private Pan pan;	//정확한 자료형으로 has a 관계를 표시하지 말자! FriPan > Pan
	
	//외부로부터 필요한 객체를 주입받기 위한 setter메서드
	public void setPan(Pan pan) {
		this.pan = pan;
	}
	
	/*
	public Cook() {
		//pan = new FriPan();	//new가 있는한 영원히 유지보수 문제는 해결될 수 없다
										//해결책? new를 제거하자!
	}
	*/
	public void makeFood() {
		pan.boil();
	}
	
}
