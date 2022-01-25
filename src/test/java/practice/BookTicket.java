package practice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BookTicket {
	
	@Test(dataProvider="DataProvider_bookTicketTest")
public void bookTicket(String src,String dest) {
		System.out.println("book ticket from"+src +" to"+dest);
		
	}
	@DataProvider
	public Object[][] DataProvider_bookTicketTest(){
		Object[][] objArr=new Object[5][2];
		
		objArr[0][0]="Banglore";
		objArr[0][1]="Mysore";
		
		objArr[1][0]="Banglore";
		objArr[1][1]="Goa";
		
		objArr[2][0]="Banglore";
		objArr[2][1]="Manglore";
		
		objArr[3][0]="Banglore";
		objArr[3][1]="Hyderabad";
		
		objArr[4][0]="Banglore";
		objArr[4][1]="MP";
		return objArr;
		
		

}
}
