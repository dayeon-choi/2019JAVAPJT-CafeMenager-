import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class updateMenu_DB {
	public updateMenu_DB(String sid, String sname, String sprice) {
		String url = "jdbc:mysql://localhost:3306/javaproject?serverTimezone=UTC&user=root&password=2399";
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs = null;
		Menu s;
		
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
			int price=Integer.parseInt(sprice);
			int id = Integer.parseInt(sid);
			String SQL = "update menu set name='"+sname+"', price="+price+" where id="+id+";";
			stmt.executeUpdate(SQL);
						
			SortDB sort = new SortDB();
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
