package board.gui;

import java.awt.FlowLayout;

import javax.swing.JFrame;

import db.DBManager;

public class BoardMain extends JFrame{
	//����� ������ ����
	Page[] pageList = new Page[Pages.values().length];	//enum�� ��ϵ� ������ ��ŭ ����
	DBManager dbManager;
	
	public BoardMain() {
		//������ ����
		pageList[0] = new BoardList(this);
		pageList[1] = new BoardWrite(this);
		pageList[2] = new BoardContent(this);
		dbManager = new DBManager();
		
		setLayout(new FlowLayout());	//�����ӿ� �������� ������Ʈ�� �����Ƿ��� �÷ο��!
		
		//�������� ����
		for(Page page:pageList) {
			add(page);
			page.setVisible(false);	//��� �ϴ� �Ⱥ��̰� ó��
		}
		
		//����Ʈ�� �����ְ� ���� ������
		showPage(Pages.valueOf("BoardList").ordinal());	//Pages.valueOf("BoardList").ordinal() > 0�̴�
		
		setVisible(true);
		setSize(900,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public void showPage(int viewPage) {	//�����ְ� ���� ������ �μ��� ����
		for(int i=0; i<pageList.length; i++) {
			if(viewPage==i) {
				pageList[i].setVisible(true);
			}else {
				pageList[i].setVisible(false);
				
			}
		}
	}
	
	public static void main(String[] args) {
		new BoardMain();
	}
}
