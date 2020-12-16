/*
	기존 전통적인 JDBC 방식으로 작성했던 DAO의 .CRUD메서드를 
	mybatis 프레임웍을 이용하여 코드를 간략화시켜보자!
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
		sqlSession.commit();	//DML의 경우 해주자
		configManager.close(sqlSession);
		return result;
	}

	public List selectAll() {
		List list = null;
		SqlSession sqlSession = configManager.getSqlSession();	//쿼리수행 객체 얻기
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
		sqlSession.commit();	//DML이므로!
		configManager.close(sqlSession);
		return result;
	}
	
	public int delete(int board_id) {
		int result=0;
		SqlSession sqlSession = configManager.getSqlSession();
		result = sqlSession.delete("Board.delete", board_id);
		sqlSession.commit();	//DML이므로!
		configManager.close(sqlSession);
		return result;
	}
}
