package src.tests;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import java.sql.*;
import src.connections.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.DEFAULT)
public class customerTests {
	
	private static int testCustID;
	private static connect con = new connect();
	private static Statement stmt;
	private static customer testCust;
	
	@BeforeClass
	public static void test1_setUpBeforeClass() throws Exception {
		testCust = new customer("Buddy","Bear", "1520 Garnet Ave", "#17", "San Diego", "CA", "92109", "4766666656");
		testCustID = con.getCustomerID("4766666656");
    	
    }
	@Test
    public void test2_addCustomerTest(){
    	ResultSet r = con.searchCustomer(0, "Buddy", "");
		assertNotNull(r);
    }
	@Test
	public void test3_searchCustomerWithIDTest(){
		customer aCust = new customer(testCustID);

		String custPhone = aCust.getPhone();
		assertEquals(custPhone, "4766666656");
	}
	@Test
	public void test4_modFirstNameTest(){
       customer cust = new customer(testCustID);
		cust.modFirstName("Ashley");
		String newFName = cust.getFirstName();
		assertEquals(newFName, "Ashley");
		
	}
	@Test
	public void test5_modLastNameTest(){
		customer cust = new customer(testCustID);
		cust.modLastName("Salas");
		String newFName = cust.getLastName();
		assertEquals(newFName, "Salas");	
	}
	@Test
	public void test6_modAddressTest(){
		customer cust = new customer(testCustID);
		cust.modAddress("211 Yacht club Way", "Redondo Beach", "CA", "90277");
		assertNull(cust.getAddLn2());
		assertEquals("211 Yacht club Way",cust.getAddLn1());
	}
//	@Test
//	public void test7_setStatusTest(){
//		customer cust = new customer(testCustID);
//		cust.setStatus("INACTIVE");
//		assertEquals("INACTIVE",cust.getStatus());
//	}
	 @AfterClass
	    public static void oneTimeTearDown() {
	        con.deleteColumn("customers",testCustID);
	    }
	}
	


