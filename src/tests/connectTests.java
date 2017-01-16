package src.tests;

import static org.junit.Assert.*;

import java.sql.ResultSet;

import org.junit.AfterClass;
import org.junit.Test;

public class connectTests {
	//con.addCustomer("Buddy","Bear", "1520 Garnet Ave", "", "San Diego", "CA", "92109", "4766666656");
//	ResultSet r = con.searchCustomer(0, "Buddy", "");
//		try{
//		while(r.next()){
//			testCustID = r.getInt("CustomerID");
//			}
//		}catch(Exception e){
// 			e.printStackTrace();
//		}
//		
//	@Test
//	public void modFirstNameTest(){
//		String newFName= "";
//		con.modCustomerInfo(testCustID,"FirstName" , "Matt");
//		ResultSet r = con.searchCustomer(testCustID, "", "");
//			try{
//				while(r.next()){
//					newFName= r.getString("FirstName");
//					}}
//			catch(Exception e){}
//			
//		assertEquals(newFName, "Matt");
//		
//	}
//	@Test
//	public void modLastNameTest(){
//		String newLName= "";
////		con.modCustomerInfo(testCustID,"LastName" , "Salas");
////		ResultSet r = con.searchCustomer(testCustID, "", "");
////		try{
////			while(r.next()){
////				newLName= r.getString("LastName");
////			}
////		}catch(Exception e){
//// 			e.printStackTrace();
////		}
//		
//		assertEquals(newLName, "Salas");		
//	}
//	@Test
//	public void modAddressTest(){
//		String newAddress= "";
//		con.modCustomerInfo(testCustID,"Address" , "211 Yacht Club way");
//		ResultSet r = con.searchCustomer(testCustID, "", "");
//		try{
//			while(r.next()){
//				newAddress = r.getString("Address");
//
//			}
//		}catch(Exception e){
// 			e.printStackTrace();
//		}
//		assertEquals(newAddress, "211 Yacht Club way");
//		
//	}
//	
//	 @AfterClass
//	    public static void oneTimeTearDown() {
//	        con.deleteColumn("customers",testCustID);
//	    }
//public static void setUpBeforeClass() throws Exception {
//		
//    	con.addPublication("Runner Magazine","Sports", 9.80, "Monthly", 5);
//       	ResultSet r = con.searchPublication(0, "Runner Magazine");
//   			try{
//    			while(r.next()){
//    				testPubID = r.getInt("PublicationID");    				
//    				}
//
//    			}catch(Exception e){
//    	 			e.printStackTrace();
//    			}
//    }
//	@Test
//    public void addPubTest(){
//    	ResultSet r = con.searchPublication(0,"Runner Magazine");
//		assertNotNull(r);
//	}
//    
//	@Test
//	public void modPublicationInfoTest(){
//		double newPrice = 0.0;
//		con.modPublicationInfo(testPubID,10.40);
//	    final double DELTA = 1e-15;
//
//		ResultSet r = con.searchPublication(testPubID, "");
//			try{
//				while(r.next()){
//					newPrice = r.getDouble("Price");
//
//					}}
//			catch(Exception e){
//			}
//			
//		assertEquals(newPrice , 10.40, DELTA);
//		
//	}
//	@Test
//	public void getPublicationIDTest(){
//		int anId = con.getPublicationID("Runner Magazine");
//		assertEquals(anId, testPubID);
//	}
//	@AfterClass
//	 public static void oneTimeTearDown() {
//        con.deleteColumn("publications",testPubID);
//    }
	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
