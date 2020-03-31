import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/*지난 기록 보기 부분의 데이터를 저장하는 클래스*/
public class addLastRecords_DB {	
		public addLastRecords_DB(String sid, String sdate, String sincome, String sordernum) {
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
				//데이터 형 DB에 맞게 변경
				int id=Integer.parseInt(sid);
				int income = Integer.parseInt(sincome);
				int ordernum = Integer.parseInt(sordernum);
				
				int i = 0;
				for(; i < LastRecords.recordsIndex; i++)
				{   //recordsIndex의 인덱스
					String SQL1 = "select * from lastrecord where id="+i+";";
					rs = stmt.executeQuery(SQL1);
					if(!rs.next())
						break;
				}//end of for
				
				
				
				
				String SQL = "insert into lastrecord(id, recordeddate, income, ordernum) values(" + id + ","+sdate +","+income+","+ ordernum + ");";
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
