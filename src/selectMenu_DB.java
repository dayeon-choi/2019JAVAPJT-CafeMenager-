import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import javax.swing.JTextField;

public class selectMenu_DB {
		String name;
		String price;
	public selectMenu_DB(String i1) {
		String url = "jdbc:mysql://localhost:3306/javaproject?serverTimezone=UTC&user=root&password=2399";
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs = null;
		
		try
		{
			//드라이버 연결
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("드라이버 연결 성공!");
			//mySQL접속
			conn = DriverManager.getConnection(url);
			System.out.println("데이터 베이스 접속 성공!");
			//DB 접속
			stmt = conn.createStatement();
			String usejavaproject = "use javaproject;";
			stmt.executeUpdate(usejavaproject);
			//메뉴 불러오기
			int i=Integer.parseInt(i1);
			String SQL = "select name, price from menu where id="+i+";";
			rs=stmt.executeQuery(SQL);
			
			//DB에서 불러온 메뉴 이름과 값 추가
			while(rs.next()){
				CafeTest.menu[i].reMenu(rs.getString("name"),rs.getInt("price"));

			}
	
							
		}
		catch(ClassNotFoundException cnfe){
			cnfe.printStackTrace();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		finally{
			if(conn != null) {
				try {
					conn.close();
				}
				catch(SQLException se){
						
				}
			}
				
			if(stmt != null){
				try{
					stmt.close();
				}
				catch(SQLException se){
						
				}
			}
				
			if(rs != null){
				try
				{
					rs.close();
				}
				catch(SQLException se)
				{
						
				}
			}//end of if
			
		}//end of finally
			
	}
		
		
	


	}

