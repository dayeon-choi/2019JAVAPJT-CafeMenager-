

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

import javax.swing.JTextField;

public class addMenu_DB {
		
		
	public addMenu_DB(String j1, String j2, String j3) {
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
			//메뉴 넣기
			int id=Integer.parseInt(j1);
			int price = Integer.parseInt(j3); 
			int i = 0;
			for(; i < 12; i++)
			{
				String SQL1 = "select * from menu where id="+i+";";
				rs = stmt.executeQuery(SQL1);
				if(!rs.next())
					break;
			}
			
			String SQL = "insert into menu(id, name, price) values(" + i + ",'"+j2 +"',"+ price + ");";
			stmt.executeUpdate(SQL);
				
				
				
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

