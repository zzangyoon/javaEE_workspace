/*
	�丮�縦 �����Ѵ�
*/
package food;

public class Cook {
	//���� �ڷ������� has a ���踦 ��������� ��� ����?
	//	- ���� �ڷ����� �����ϰų�, ��ȭ�� ������� ���� Ŭ������ has a ���迡 �ִ� Ŭ������
	//	   �������� ��ȭ���ױ� ������ ������������ ������
	
	private Pan pan;	//��Ȯ�� �ڷ������� has a ���踦 ǥ������ ����! FriPan > Pan
	
	//�ܺηκ��� �ʿ��� ��ü�� ���Թޱ� ���� setter�޼���
	public void setPan(Pan pan) {
		this.pan = pan;
	}
	
	/*
	public Cook() {
		//pan = new FriPan();	//new�� �ִ��� ������ �������� ������ �ذ�� �� ����
										//�ذ�å? new�� ��������!
	}
	*/
	public void makeFood() {
		pan.boil();
	}
	
}
