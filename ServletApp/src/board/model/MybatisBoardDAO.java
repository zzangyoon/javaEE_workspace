/*
	���� �������� JDBC ������� �ۼ��ߴ� DAO�� .CRUD�޼��带 
	mybatis �����ӿ��� �̿��Ͽ� �ڵ带 ����ȭ���Ѻ���!
 */
package board.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import mybatis.config.MybatisConfigManager;

public class MybatisBoardDAO {
	MybatisConfigManager configManager = MybatisConfigManager.getInstance();
	
	public int insert(Board board) {
		int result=0;
		SqlSession sqlSession = configManager.getSqlSession();
		result = sqlSession.insert("Board.insert", board);
		sqlSession.commit();	//DML�� ��� ������
		configManager.close(sqlSession);
		return result;
	}

	public List selectAll() {
		List list = null;
		SqlSession sqlSession = configManager.getSqlSession();	//�������� ��ü ���
		list = sqlSession.selectList("Board.selectAll");	
		configManager.close(sqlSession);
		return list;
	}
	
	public Board select(int board_id) {
		Board board = null;
		SqlSession sqlSession = configManager.getSqlSession();
		board = sqlSession.selectOne("Board.select", board_id);
		configManager.close(sqlSession);
		return board;
	}
	
	public int update(Board board) {
		int result = 0;
		SqlSession sqlSession = configManager.getSqlSession();
		result = sqlSession.update("Board.update", board);
		sqlSession.commit();	//DML�̹Ƿ�!
		configManager.close(sqlSession);
		return result;
	}
	
	public int delete(int board_id) {
		int result=0;
		SqlSession sqlSession = configManager.getSqlSession();
		result = sqlSession.delete("Board.delete", board_id);
		sqlSession.commit();	//DML�̹Ƿ�!
		configManager.close(sqlSession);
		return result;
	}
}
