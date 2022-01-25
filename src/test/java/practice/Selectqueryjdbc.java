package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.Statement;
import com.mysql.jdbc.Driver;

public class Selectqueryjdbc {
public static  void main(String[] args) throws SQLException  {
	Connection conn=null;
	try
	{
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
		System.out.println("connection is done");
		Statement stmt=(Statement) conn.createStatement();
		String Query="select * from project";
		ResultSet resultset=stmt.executeQuery(Query);
		while(resultset.next()) {
			System.out.println(resultset.getString(1)+"\t"+resultset.getString(2)+"\t"+resultset.getString(3)+"\t"+resultset.getString(4));
		}
			}
	catch(Exception e) 
	{
		
	}
	finally {
		conn.close();
		System.out.println("===========close db connection===========");
		
	}
}
} 




