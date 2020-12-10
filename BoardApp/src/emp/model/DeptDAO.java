/*
	���ݱ����� DAO�� �ڵ� ����� JDBC�� �̿��Ͽ��� ������ ���������� �� ���� �ڵ尡 �� ��Ȳ�߾���
	���� �̹� DAO������ mybatis �����ӿ��� �����Ͽ�, �ڵ带 ���� �����ϰ� �ۼ��غ��ڴ�
*/
package emp.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import db.MybatisManager;

public class DeptDAO {
	//DAO���� SQL���� ����ִ� xml�� ȣ������! 
	//�̶� � ���������� ���ϴ����� �����ϱ� ���ؼ��� xml �±׿� �ο��� id�� �̿��ϸ� �ȴ�!
	//xml �±׸� ȣ���ϱ� ���ؼ��� mybatis�� SqlSession �� �ʿ��ϴ� (pstmt�� ����)
	//����� MybatisManager Ŭ������ ��������� �ξ���
	
	MybatisManager manager = new MybatisManager();
	SqlSessionFactory factory;
	
	public DeptDAO() {	//������
		factory = manager.getSqlSessionFactory();
	}
	
	//��� ������ ��������
	public List selectAll() {
		SqlSession session = factory.openSession();	//������ ���ఴü ����
		return session.selectList("mybatis.config.Dept.selectAll");	//(namespace).(id)
	}
	
}
