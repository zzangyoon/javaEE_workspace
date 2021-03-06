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
		
		//생성
		t_author = new JTextField();
		t_title = new JTextField();
		area = new JTextArea();
		scroll = new JScrollPane(area);
		bt_list = new JButton("목록으로");
		bt_edit = new JButton("수정");
		bt_del = new JButton("삭제");
		noticeDAO = new NoticeDAO();	//DAO 생성하기
		
		//스타일
		t_author.setPreferredSize(new Dimension((int)this.getPreferredSize().getWidth()-10, 25));
		t_title.setPreferredSize(new Dimension((int)this.getPreferredSize().getWidth()-10, 25));
		scroll.setPreferredSize(new Dimension((int)this.getPreferredSize().getWidth()-10, 500));
		
		//조립
		add(t_author);
		add(t_title);
		add(scroll);
		
		add(bt_list);
		add(bt_edit);
		add(bt_del);
		
		//목록버튼에 이벤트 연결
		bt_list.addActionListener((e)->{
			boardMain.showPage(Pages.valueOf("BoardList").ordinal());	//ordinal : 정수형
		});
		
		//수정버튼에 리스너 연결
		bt_edit.addActionListener((e)->{
			//한번 물어보고 수정하자
			if(JOptionPane.showConfirmDialog(BoardContent.this, "수정하시겠습니까?")==JOptionPane.OK_OPTION) {
				edit();
			}
		});
		
		
		bt_del.addActionListener((e)->{
			//한번 물어보고 삭제하자
			if(JOptionPane.showConfirmDialog(BoardContent.this, "삭제하시겠습니까?")==JOptionPane.OK_OPTION) {
				del();
			}
		});
	}
	
	public void del() {
		//삭제하고 목록보여주기
		noticeDAO = new NoticeDAO();
		//System.out.println(notice_id);
		//int result = noticeDAO.del(notice_id);
		int result = noticeDAO.del(notice.getNotice_id());	//notice VO 갖고 있으므로
		
		if(result==0) {
			JOptionPane.showMessageDialog(BoardContent.this, "삭제 실패");
		}else {
			JOptionPane.showMessageDialog(BoardContent.this, "삭제  성공");
			
			BoardList boardList = (BoardList)boardMain.pageList[Pages.valueOf("BoardList").ordinal()];
			boardList.getList();//데이터 가져오기!!! (이걸 안해서 갱신이 안됐다!!!)
			boardList.table.updateUI();	//화면 갱신
			boardMain.showPage(Pages.valueOf("BoardList").ordinal());
		}
	}
	
	public void edit() {
		//DAO를 이용하여 수정작업 수행
		//작성자, 제목, 내용만 교체!!!
		//Notice notice = new Notice();	//New 할 필요가 없다!!! 재사용성 위해 위에서 
		notice.setAuthor(t_author.getText());
		notice.setTitle(t_title.getText());
		notice.setContent(area.getText());
		
		//noticeDAO = new NoticeDAO();	//얘도 위에서 해줬다
		int result = noticeDAO.edit(notice);
		
		if(result==0) {
			JOptionPane.showMessageDialog(BoardContent.this, "수정 실패");
		}else {
			JOptionPane.showMessageDialog(BoardContent.this, "수정 성공");
		}
	}
	
	//컴포넌트에 데이터 채워넣기!
	//이 메서드를 호출하는 者는 한건의 데이터를 VO에 담아서 호출하면 된다!
	public void setData(Notice notice) {
		this.notice = notice;	//나중에 써먹을 것을 대비해서 보관해놓음! (line-18)
		t_author.setText(notice.getAuthor());
		t_title.setText(notice.getTitle());
		area.setText(notice.getContent());
	}

}
