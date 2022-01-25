package practice;


import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class SampleTest_dataProvider {
	
	@Test(dataProvider="bookTicketDataProvider")
public void bookTicket(String src,String dest,int ticket) {
		System.out.println("execute source =>"+src +",Destination==>"+dest);
		
	}
	@DataProvider
	public Object[][] bookTicketDataProvider(){
		Object[][] objArr=new Object[5][3];
		objArr[0][0]="Banglore";
		objArr[0][1]="Mysore";
		objArr[0][2]=10;
		
		objArr[1][0]="Banglore";
		objArr[1][1]="Goa"; 
		objArr[1][2]=10;
		
		objArr[2][0]="Banglore";
		objArr[2][1]="Manglore";
		objArr[2][2]= 10;
		
		objArr[3][0]="Banglore";
		objArr[3][1]="Mumbai";
		objArr[3][2]=10;
		
		objArr[4][0]="Banglore";
		objArr[4][1]="Kerela";
		objArr[4][2]=10;
		return objArr;
	}
	
	
	

}
