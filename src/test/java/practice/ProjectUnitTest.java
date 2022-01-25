package practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mysql.jdbc.Driver;



public class ProjectUnitTest {
	
	@Test
	public void UnitTest () throws SQLException {
		String projName="proj_202";

		Connection conn=null;
		try {
			Driver driver=new Driver();
			DriverManager.deregisterDriver(driver);
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
			System.out.println("connection is done succesfully");
			Statement stmt=conn.createStatement();
			String query="Select * from project";
			ResultSet resultset=stmt.executeQuery(query);
			boolean flag=false;
			while(resultset.next()) {
				String actualprojName=resultset.getString(2);
				if(actualprojName.equals(projName)) {
					flag=true;
				}

			}
	Assert.assertTrue(flag);
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
