package board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import common.db.PoolManager;

public class BoardDAO {
	//PoolManager pool = new PoolManager(); ���� �Ұ���!!!
	PoolManager pool = PoolManager.getInstance();	//�̱��� ��ü ȣ��
	
	public int insert(Board board) {
		Connection con = null;
		PreparedStatement pstmt = null;	
		int result=0;
		String sql = "insert into board(board_id, title, writer, content, filename) values(seq_board.nextval,?,?,?,?)";
		
		con = pool.getConnection();	//�뿩! ���ο� ������ �ƴϴ�(�ӵ��� ������)
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getFilename());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt);
		}
		return result;
	}
	
	//���� ��������
	public List selectAll() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		String sql = "select * from board order by board_id desc";
	
		con = pool.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Board board = new Board();
				board.setBoard_id(rs.getInt("board_id"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getString("regdate"));
				board.setHit(rs.getInt("hit"));
				board.setFilename(rs.getString("filename"));
				
				list.add(board);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt, rs);
		}
		return list;
	}
	
	//�Ѱ� ��������
	public Board select(int board_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Board board = null;
		String sql = "select * from board where board_id=?";
		
		con = pool.getConnection();	//Ǯ�κ��� Ŀ�ؼ� �뿩!
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new Board();
				board.setBoard_id(rs.getInt("board_id"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegdate(rs.getString("regdate"));
				board.setHit(rs.getInt("hit"));
				board.setFilename(rs.getString("filename"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt, rs);
		}
		return board;
	}
	
	//����
	public int delete(int board_id) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "delete from board where board_id=?";
		
		con = pool.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, board_id);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt);
		}
		return result;
	}
	
	//�� 1�� ����
	public int update(Board board) {
		Connection con = null;
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "update board set title=?, writer=?, content=?, filename=? where board_id=?";
		
		con = pool.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getWriter());
			pstmt.setString(3, board.getContent());
			pstmt.setString(4, board.getFilename());
			pstmt.setInt(5, board.getBoard_id());
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.release(con, pstmt);
		}
		return result;
	}
}
