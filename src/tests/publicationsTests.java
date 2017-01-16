package src.tests;

import static org.junit.Assert.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import java.sql.*;
import src.connections.*;

public class publicationsTests {
	public static int testPubID;
	public static connect con = new connect();
	public static Statement stmt;
	private static publication testPub;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testPub = new publication( "Runner Magazine","Sports", 9.80, "Monthly", 5);
		testPubID = testPub.getPID();
    }
	@Test
    public void addPubTest(){

		publication pub = new publication( testPubID);
		assertEquals("Runner Magazine",pub.getTitle());
	}
    
	@Test
	public void modPublicationPriceTest(){
		final double DELTA = 1e-15;

		publication pub = new publication(testPubID);
		pub.modPrice(6.50);
		double newPrice = pub.getPrice();
		assertEquals(newPrice , 6.50, DELTA);
		
	}

	@AfterClass
	 public static void oneTimeTearDown() {
        con.deleteColumn("publications",testPubID);
    }

}
