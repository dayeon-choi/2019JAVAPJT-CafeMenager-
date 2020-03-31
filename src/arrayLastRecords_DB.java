
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class arrayLastRecords_DB {
	public arrayLastRecords_DB() {
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
			
			String SQL4 = "delete from lastrecord where id = 0;";
			stmt.executeUpdate(SQL4);
			System.out.println("0");
			
			for(int i = 0; i <  LastRecords.recordsIndex; i++){
				
				String SQL = "select * from lastrecord where id="+i+";";
				rs = stmt.executeQuery(SQL);
				System.out.println("1");

				if(!rs.next()) {
					System.out.println("0.0");
					int j = i+1;
					for(; j <LastRecords.recordsIndex; j++) {
						String SQL1 = "select * from lastrecord where id="+j+";";
						rs = stmt.executeQuery(SQL1);
						System.out.println("2");
						if(rs.next()){
							String SQL2 = "insert into lastrecord value("+i+", '"+rs.getString("recordeddate")+"', "+rs.getInt("income")+","+rs.getInt("ordernum")+");";
							stmt.executeUpdate(SQL2);
							System.out.println("3");
							String SQL3 = "delete from lastrecord where id="+j+";";
							stmt.executeUpdate(SQL3);
							System.out.println("4");
							break;
						}
						
					}
					
				}
					
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
