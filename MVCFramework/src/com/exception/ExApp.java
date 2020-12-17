package com.exception;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ExApp {

	//1. throws는 현재 메서드에서 해당 예외를 처리하지 않고, 이 메서드를 호출한 자에게 떠넘기는것!
	public void insert() throws SQLException{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		//예외처리안해도 오류안남 그대신 아래서 호출할때 오류가!
		pstmt = con.prepareStatement("insert~~~");
		pstmt.executeUpdate();

	}
	
	public static void main(String[] args) throws SQLException{
		ExApp app = new ExApp();
		app.insert();	//2. 여기서도 예외처리하고 싶지 않다면 메인에다 throws 하기! 그러면 main을 호출하는 JVM이 한다
		/*
		try {
			app.insert();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		*/
	}
}
