package board.gui;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import board.model.Notice;
import board.model.NoticeDAO;

public class BoardContent extends Page{
	JTextField t_author;
	JTextField t_title;
	JTextArea area;
	JScrollPane scroll;
	JButton bt_list, bt_edit, bt_del;
	Notice notice;	//VO
	NoticeDAO noticeDAO;	//DAO
	
	public BoardContent(BoardMain boardMain) {
		super(boardMain);
		
		//����
		t_author = new JTextField();
		t_title = new JTextField();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		bt_list = new JButton("�������");
		bt_edit = new JButton("����");
		bt_del = new JButton("����");
		noticeDAO = new NoticeDAO();	//DAO �����ϱ�
		
		//��Ÿ��
		t_author.setPreferredSize(new Dimension((int)this.getPreferredSize().getWidth()-10, 25));
		t_title.setPreferredSize(new Dimension((int)this.getPreferredSize().getWidth()-10, 25));
		scroll.setPreferredSize(new Dimension((int)this.getPreferredSize().getWidth()-10, 500));
		
		//����
		add(t_author);
		add(t_title);
		add(scroll);
		
		add(bt_list);
		add(bt_edit);
		add(bt_del);
		
		//��Ϲ�ư�� �̺�Ʈ ����
		bt_list.addActionListener((e)->{
			boardMain.showPage(Pages.valueOf("BoardList").ordinal());	//ordinal : ������
		});
		
		//������ư�� ������ ����
		bt_edit.addActionListener((e)->{
			//�ѹ� ����� ��������
			if(JOptionPane.showConfirmDialog(BoardContent.this, "�����Ͻðڽ��ϱ�?")==JOptionPane.OK_OPTION) {
				edit();
			}
		});
		
		
		bt_del.addActionListener((e)->{
			//�ѹ� ����� ��������
			if(JOptionPane.showConfirmDialog(BoardContent.this, "�����Ͻðڽ��ϱ�?")==JOptionPane.OK_OPTION) {
				del();
			}
		});
	}
	
	public void del() {
		//�����ϰ� ��Ϻ����ֱ�
		noticeDAO = new NoticeDAO();
		//System.out.println(notice_id);
		//int result = noticeDAO.del(notice_id);
		int result = noticeDAO.del(notice.getNotice_id());	//notice VO ���� �����Ƿ�
		
		if(result==0) {
			JOptionPane.showMessageDialog(BoardContent.this, "���� ����");
		}else {
			JOptionPane.showMessageDialog(BoardContent.this, "����  ����");
			
			BoardList boardList = (BoardList)boardMain.pageList[Pages.valueOf("BoardList").ordinal()];
			boardList.getList();//������ ��������!!! (�̰� ���ؼ� ������ �ȵƴ�!!!)
			boardList.table.updateUI();	//ȭ�� ����
			boardMain.showPage(Pages.valueOf("BoardList").ordinal());
		}
	}
	
	public void edit() {
		//DAO�� �̿��Ͽ� �����۾� ����
		//�ۼ���, ����, ���븸 ��ü!!!
		//Notice notice = new Notice();	//New �� �ʿ䰡 ����!!! ���뼺 ���� ������ 
		notice.setAuthor(t_author.getText());
		notice.setTitle(t_title.getText());
		notice.setContent(area.getText());
		
		//noticeDAO = new NoticeDAO();	//�굵 ������ �����
		int result = noticeDAO.edit(notice);
		
		if(result==0) {
			JOptionPane.showMessageDialog(BoardContent.this, "���� ����");
		}else {
			JOptionPane.showMessageDialog(BoardContent.this, "���� ����");
		}
	}
	
	//������Ʈ�� ������ ä���ֱ�!
	//�� �޼��带 ȣ���ϴ� �� �Ѱ��� �����͸� VO�� ��Ƽ� ȣ���ϸ� �ȴ�!
	public void setData(Notice notice) {
		this.notice = notice;	//���߿� ����� ���� ����ؼ� �����س���! (line-18)
		t_author.setText(notice.getAuthor());
		t_title.setText(notice.getTitle());
		area.setText(notice.getContent());
	}

}
