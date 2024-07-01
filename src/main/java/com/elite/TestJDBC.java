package com.elite;

import java.sql.DriverManager;

public class TestJDBC {

	public static void main(String[] args) {
//		String url = "jdbc:mysql://localhost:3306/hb_student_tracker?useSSL=false";
//		String url = "jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false";
		String url = "jdbc:mysql://localhost:3306/hb-03-one-to-many?useSSL=false";
		String user = "hbstudent";
		String password = "P@ssw0rd";
		
		try {
			System.out.println("Connecting to database: " + url);
			
			DriverManager.getConnection(url, user, password);
			
			System.out.println("Connection successfull!");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
