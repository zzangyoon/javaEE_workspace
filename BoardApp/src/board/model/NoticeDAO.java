/*
	DAO��?
	- Data Access Object�� �ǹ��ϴ� ���ø����̼��� ���� �о� ���
	- Data Access�� �����ͺ��̽����� CRUD, �� Create(=insert) Read(=select)UD �۾��� �����Ѵٴ� �ǹ�
*/
package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.DBManager;

public class NoticeDAO {
	DBManager dbManager = new DBManager();
	
	//���뼺 ������� ���� swing ���� ���� �ۼ�
	//insert�� �� �Ѱ�!!! �ϳ��� VO
	public int regist(Notice notice) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;	//�� ��� �� �� ����� ����
		con = dbManager.getConnection();
		
		String sql = "insert into notice(author, title, content) values(?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, notice.getAuthor());	//�ۼ���	//t_author.getText()
																				//: �̰� JavaSE(����)���� ���̹Ƿ�(���� ������ �����ڵ尡 �� �� ����)
			pstmt.setString(2, notice.getTitle());		//����
			pstmt.setString(3, notice.getContent());//����
			
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}

	
	//��� ���ڵ� ��������!
	public ArrayList selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Notice> list = new ArrayList<Notice>();	//rs�� ��ü�� �༮
		
		con=dbManager.getConnection();
		String sql ="select * from notice order by notice_id desc";
		try {
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			//rs�� ���ڵ尡 ������ �̹Ƿ� VO ���� �������� �ʿ��ϴ�
			//�� VO�� �Ѳ����� ��Ƽ� ��ȯ�ؾ� �ϹǷ�, ������ �ڷ����� �ʿ��ϴ�!
			//��ü�� ��Ƴ��� ������ �����ϴ� �����ӿ��� CollectionFramework �̹Ƿ�, �� �� �ϳ��� api�� �̿��غ���
			while(rs.next()) {	//��ĭ�� ������Ű�鼭 ���پ� VO�� ����� �Űܽ���
				Notice notice = new Notice();	//�ֺ� empty ������ vo ���� (5�� �����ؾ� �ϹǷ� while�� ������ ������ �ȵ�)
				//notice�� rs�� ������ ��� �Űܽ���
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setAuthor(rs.getString("author"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));
				
				//notice������ �ױ����� �� ArrayList�� ����
				list.add(notice);	//5���� �����
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}
		return list;
	}
	
	
	//�Խù� 1�� ��������(�󼼺���)
	public Notice select(int notice_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Notice notice = null;	//rs��� ������ 1���� ���� ��ü
		
		String sql = "select * from notice where notice_id=?";
		
		con = dbManager.getConnection();	//���Ӱ�ü ���
		try {
			pstmt = con.prepareStatement(sql);	//�����غ�
			pstmt.setInt(1, notice_id);	//���ε� ������ ����
			rs=pstmt.executeQuery();
			
			//���� ź���� rs�� �� �״´�... ���� rs�� ��ü�� ��ü�� �ʿ��ϴ�
			//rs�� ���ڵ� �Ѱ��� ��� ��ü�̹Ƿ�, ���ڵ� 1���� ��� ���޿����� ���Ǵ� VO�� �̿�����!
			if(rs.next()) {
				notice = new Notice();	//�ֺ� empty ������ vo ����
				//notice�� rs�� ������ ��� �Űܽ���
				notice.setNotice_id(rs.getInt("notice_id"));
				notice.setAuthor(rs.getString("author"));
				notice.setTitle(rs.getString("title"));
				notice.setContent(rs.getString("content"));
				notice.setRegdate(rs.getString("regdate"));
				notice.setHit(rs.getInt("hit"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt, rs);
		}
		return notice;
	}
		
	//�Խù� 1�� �����ϱ�
	public int edit(Notice notice) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = "update notice set author=?, title=?, content=? where notice_id=?";
		
		con = dbManager.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, notice.getAuthor());
			pstmt.setString(2, notice.getTitle());
			pstmt.setString(3, notice.getContent());
			pstmt.setInt(4, notice.getNotice_id());

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt);
		}
		return result;
	}
	
	//�����ϱ�
	public int del(int notice_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result =0;
		
		String sql = "delete from notice where notice_id="+notice_id;
		
		con = dbManager.getConnection();
		
		try {
			pstmt = con.prepareStatement(sql);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			dbManager.release(con, pstmt);
		}
		return result;
		
		
	}
	
	
	
	
	
	
	
}

