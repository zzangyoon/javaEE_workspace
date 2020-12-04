package board.gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import board.model.Notice;
import board.model.NoticeDAO;

public class BoardWrite extends Page{
	JTextField t_author;
	JTextField t_title;
	JTextArea area;
	JScrollPane scroll;
	JButton bt;
	NoticeDAO noticeDAO;	//CRUD ���� ��ü ����(has a)
	
	public BoardWrite(BoardMain boardMain) {
		super(boardMain);
		
		//����
		t_author = new JTextField();
		t_title = new JTextField();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		bt = new JButton("���");
		noticeDAO = new NoticeDAO();
		
		//��Ÿ��
		t_author.setPreferredSize(new Dimension((int)this.getPreferredSize().getWidth()-10, 25));
		t_title.setPreferredSize(new Dimension((int)this.getPreferredSize().getWidth()-10, 25));
		scroll.setPreferredSize(new Dimension((int)this.getPreferredSize().getWidth()-10, 500));
		
		//����
		add(t_author);
		add(t_title);
		add(scroll);
		add(bt);
		
		//���
		bt.addActionListener((e)->{
			//ȣ������ �Ű������� VO�ϼ��Ͽ� ����
			Notice notice = new Notice();	//empty ��ü����
			
			notice.setAuthor(t_author.getText());
			notice.setTitle(t_title.getText());
			notice.setContent(area.getText());
			
			int result = noticeDAO.regist(notice);	//DAO���� ����� �ñ���
			
			if(result==0) {
				JOptionPane.showMessageDialog(BoardWrite.this, "��Ͻ���");
			}else {
				JOptionPane.showMessageDialog(BoardWrite.this, "��ϼ���");		
				//BoardList boardList = (BoardList)boardMain.pageList[Pages.valueOf("BoardList").ordinal()];
				//boardList.table.updateUI();
				boardMain.showPage(Pages.valueOf("BoardList").ordinal());
	
			}
		});
		
	}
}

