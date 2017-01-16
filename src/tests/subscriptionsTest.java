package src.tests;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import java.sql.*;
import src.connections.*;


public class subscriptionsTest {
	public static int testPubID;
	public static int testCustID;
	public static int testSubID;

	public static connect con = new connect();
	public static Statement stmt;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		con.addCustomer("Buddy","Bear", "1520 Garnet Ave", "", "San Diego", "CA", "92109", "4766666656");
    	con.addPublication("Runner Magazine","Sports", 9.80, "Monthly", 5);

       	ResultSet r = con.searchCustomer(0, "Buddy", "");
   			try{
    			while(r.next()){
    				testCustID = r.getInt("CustomerID");

    				}
       			r.close();

    			}
   			catch(Exception e){
    	 			e.printStackTrace();
    			}
	
   	       	ResultSet rs = con.searchPublication(0, "","Runner Magazine");

   			try{
    			while(rs.next()){
    				testPubID = rs.getInt("PublicationID");    				

    				}
    			}catch(Exception e){
    	 			e.printStackTrace();
    			}
   			subscriptions newSub = new subscriptions(testCustID, testPubID);
	}
	@Test
	public void addSubcriptionTest(){
		ResultSet r = con.getSubscriptions(testCustID, testPubID);
		assertNotNull(r);

	}
	

}
