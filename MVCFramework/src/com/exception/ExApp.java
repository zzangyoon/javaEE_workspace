package com.exception;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExApp {

	//1. throws�� ���� �޼��忡�� �ش� ���ܸ� ó������ �ʰ�, �� �޼��带 ȣ���� �ڿ��� ���ѱ�°�!
	public void insert() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		//����ó�����ص� �����ȳ� �״�� �Ʒ��� ȣ���Ҷ� ������!
		pstmt = con.prepareStatement("insert~~~");
		pstmt.executeUpdate();

	}
	
	public static void main(String[] args) throws SQLException{
		ExApp app = new ExApp();
		app.insert();	//2. ���⼭�� ����ó���ϰ� ���� �ʴٸ� ���ο��� throws �ϱ�! �׷��� main�� ȣ���ϴ� JVM�� �Ѵ�
		/*
		try {
			app.insert();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
	}
}
