package practice;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;

	import java.sql.Statement;
	import com.mysql.jdbc.Driver;
	public class Nonselectquery {
	public static  void main(String[] args) throws SQLException  {
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		Connection conn=null;
		try
		{
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
			
		Statement stmt=(Statement) conn.createStatement();
			
				}
				catch(SQLException e) 
		{
			
		}
		finally {
			conn.close();
			System.out.println("===========close db connection===========");
			
		}
	}
	} 



