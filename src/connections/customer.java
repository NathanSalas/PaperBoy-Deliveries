<<<<<<< HEAD

package src.connections;
=======
<<<<<<< HEAD
<<<<<<< HEAD
=======
 package connections;


import java.sql.*;
import java.util.ArrayList;

public class customer {

	private String firstName;
	private String lastName;
	private String addrLineOne;
	private String addrLineTwo;
	private String city;
	private String state;
	private String zip;
	private String phoneNum;
	private int CID;
	protected String publicationName;
	private String status;
	private LatLng myPoints;
	private connect cn = new connect();
	

	// create new customer AddTypeOne
	public customer(String fN, String lN, String addLn1, String c, String st, String z, String pN) {
		cn.addCustomer(fN, lN, addLn1, "", c, st, z, pN);
		CID = cn.getCustomerID(pN);
		myPoints = new LatLng(cn, CID, z, addLn1, st);
		status = "ACTIVE";
		firstName = fN;
		lastName = lN;
		addrLineOne = addLn1;
		addrLineTwo = null;
		city = c;
		state = st;
		zip = z;
		phoneNum = pN;
		int pid = cn.getPublicationID("Bill");
		subcriptions billSub = subscription(cn, CID, pid);
	}

	// create new customer AddTypeTwo
	public customer(String fN, String lN, String addLn1, String addLn2, String c, String st, String z, String pN) {
		cn.addCustomer(fN, lN, addLn1, addLn2, c, st, z, pN);
		CID = cn.getCustomerID(pN);
		myPoints = new LatLng(cn, CID, z, addLn1, st);
		status = "ACTIVE";
		firstName = fN;
		lastName = lN;
		addrLineOne = addLn1;
		addrLineTwo = null;
		city = c;
		state = st;
		zip = z;
		phoneNum = pN;
		int pid = cn.getPublicationID("Bill");
		subcriptions billSub = subscription(cn, CID, pid);
	}
	
	// select customer with specified customer ID
	public customer(int ID) {
		ResultSet r = cn.searchCustomer(ID, "", "");
		try {
			while (r.next()) {
				CID = r.getInt("CustomerID");
				System.out.println("cust id " + CID);
				status = r.getString("Status");
				System.out.println("cust status " + CID);

				firstName = r.getString("FirstName");
				lastName = r.getString("LastName");
				addrLineOne = r.getString("Address");
				addrLineTwo = r.getString("AddressLineTwo");

				city = r.getString("City");
				state = r.getString("State");
				zip = r.getString("Zip");
				phoneNum = r.getString("Phone");
			}

			r.close();

		}catch (Exception e){
			CID = 0;
		}
	}
	
	//this constructor creates a customer object for search results. No access to points
	public customer(int ID, String fN, String lN, String addLn1, String addLn2, String c, String s, String z, String p, String stat){
		CID = ID;
		firstName = fN;
		lastName = lN;
		addrLineOne = addLn1;
		addrLineTwo = addLn2;
		city =c;
		state = s;
		zip = z;
		phoneNum = p;
		status = stat;
		myPoints = null;
	}

	public LatLng getLatLng() {
		return myPoints;
	}

	public String toString() {
		if (addrLineTwo != null) {
			return "Customer ID: " + CID + "\nName: " + firstName + " " + lastName + "\nAddress: " + addrLineOne + "\n"
					+ addrLineTwo + "\n" + city + ", " + state + " " + zip + "\nPhone Number: " + phoneNum
					+ "\nStatus: " + status;}

		else {
			return "Customer ID: " + CID + "\nName: " + firstName + " " + lastName + "\nAddress: " + addrLineOne + "\n"
					+ city + ", " + state + " " + zip + "\nPhone Number: " + phoneNum + "\nStatus: " + status;
		}
	}

	public boolean modFirstName(String fN) {
		firstName = fN;
		return cn.modCustomerInfo(CID, "FirstName", fN);
	}

	public boolean modLastName(String lN) {
		lastName = lN;
		return cn.modCustomerInfo(CID, "LastName", lN);
	}

	public boolean modAddress(String addLn1, String c, String s, String z){
        addrLineOne = addLn1;
        city = c;
        state = s;
        zip = z;
        addrLineTwo = null;
        try{
        	myPoints = computeLatLng.getLatLongPositions(z + ", " + addLn1 + " " + s);
        }catch(Exception f){
        	return false;
    	}
        return (cn.modCustomerInfo(CID, "Address", addLn1)&&cn.modCustomerInfo(CID, "City", c)&&cn.modCustomerInfo(CID, "State", s)&&cn.modCustomerInfo(CID, "Zip", z));
    }
    
    public boolean modAddress(String addLn1, String addLn2, String c, String s, String z){
        addrLineOne = addLn1;
        addrLineTwo = addLn2;
        city = c;
        state = s;
        zip = z;
        try{
        	myPoints = computeLatLng.getLatLongPositions(z + ", " + addLn1 + " " + s);
        }catch(Exception f){
        	return false;
        }
        return (cn.modCustomerInfo(CID, "Address", addLn1)&&cn.modCustomerInfo(CID, "AddressLineTwo", addLn2)&&cn.modCustomerInfo(CID, "City", c)&&cn.modCustomerInfo(CID, "State", s)&&cn.modCustomerInfo(CID, "Zip", z));
    }
	public boolean modPhoneNum(String pN) {
		phoneNum = pN;
		return cn.modCustomerInfo(CID, "Phone", pN);
	}
	
	public boolean setStatus(String st){
		if(status.equals(st)){
			return false;
		}
		else{
			status = st;
			return cn.modCustomerInfo(CID, "Status", st);
		}
	}
	public String getStatus(){
		return status;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getCID(){
		return CID;
	}
	    
	public String getFullName(){
		return firstName + " " + lastName;
	}
	
	public String getAddLn1(){
		return addrLineOne;
	}
	
	public String getAddLn2(){
		return addrLineTwo;
	}
	
	public String getCity(){
		return city;
	}
	
	public String getState(){
		return state;
	}
	
	public String getZip(){
		return zip;
	}
	
	public String getPhone(){
		return phoneNum;
	}
	    
	public String getAddress(){
		if(addrLineTwo!=null){
			return String.format("%s%n%s%n%s, %s %s", addrLineOne, addrLineTwo, city, state, zip);
		}
		else{
			return String.format("%s%n%s, %s %s", addrLineOne, city, state, zip);
		}
	}
	
	public void close(){
		cn.disconnect();
	}
	    	
	//for use without creating a customer object
	public static String getAddress(String addLn1, String addLn2, String c, String s, String z){
		if(addLn2!=null&&addLn2!=""){
			return String.format("%s%n%s%n%s, %s %s", addLn1, addLn2, c, s, z);
		}
		else{
			return String.format("%s%n%s, %s %s", addLn1, c, s, z);
		}
	}
	
	public static ArrayList<String> getSearchCustomers(String fN, String lN){
		connect c = new connect();
		ResultSet r = c.searchCustomer(0, fN, lN);
		ArrayList<String> allResults = new ArrayList<>();
		try{
			while(r.next()){
				if(r.getString("AddressLineTwo")!=null)
					allResults.add("Customer ID: "+r.getInt("CustomerID") + "<br>" + r.getString("FirstName") + " " + r.getString("LastName") + "<br>" +
							r.getString("Address") + "<br>" + r.getString("AddressLineTwo") + "<br>" +
							r.getString("City") + ", " + r.getString("State") + " " + r.getString("Zip") + "<br>" +
							r.getString("Phone"));
				else
					allResults.add("Customer ID: "+r.getInt("CustomerID") + "<br>" + r.getString("FirstName") + " " + r.getString("LastName") + "<br>" +
							r.getString("Address") + "<br>" +
							r.getString("City") + ", " + r.getString("State") + " " + r.getString("Zip") + "<br>" +
							r.getString("Phone"));
			
			}
			r.close();
			c.disconnect();
			return allResults;
		}catch(Exception e){
			c.disconnect();
			return null;
		}
	}
=======
>>>>>>> master
package connections;
>>>>>>> master


import java.sql.*;
import java.util.ArrayList;

public class customer {

	private String firstName;
	private String lastName;
	private String addrLineOne;
	private String addrLineTwo;
	private String city;
	private String state;
	private String zip;
	private String phoneNum;
	protected int CID;
	protected int PID;
	protected String publicationName;
	private String status;
	private LatLng myPoints;
	private connect cn = new connect();

	// create new customer AddTypeOne
	public customer(String fN, String lN, String addLn1, String c, String st, String z, String pN) {
		cn.addCustomer(fN, lN, addLn1, "", c, st, z, pN);
		CID = cn.getCustomerID(pN);
		myPoints = new LatLng(cn, CID, z, addLn1, st);
		status = "ACTIVE";
		firstName = fN;
		lastName = lN;
		addrLineOne = addLn1;
		addrLineTwo = null;
		city = c;
		state = st;
		zip = z;
		phoneNum = pN;
	}

	// create new customer AddTypeTwo
	public customer(String fN, String lN, String addLn1, String addLn2, String c, String st, String z, String pN) {
		cn.addCustomer(fN, lN, addLn1, addLn2, c, st, z, pN);
		CID = cn.getCustomerID(pN);
		myPoints = new LatLng(cn, CID, z, addLn1, st);
		status = "ACTIVE";
		firstName = fN;
		lastName = lN;
		addrLineOne = addLn1;
		addrLineTwo = null;
		city = c;
		state = st;
		zip = z;
		phoneNum = pN;
	}
	
	// select customer with specified customer ID
	public customer(int ID) {
		ResultSet r = cn.searchCustomer(ID, "", "");
		try {
			while (r.next()) {
				CID = r.getInt("CustomerID");
				status = r.getString("Status");
				firstName = r.getString("FirstName");
				lastName = r.getString("LastName");
				addrLineOne = r.getString("Address");
				if ((addrLineTwo = r.getString("AddressLineTwo")).length() == 0)
					addrLineTwo = null;
				city = r.getString("City");
				state = r.getString("State");
				zip = r.getString("Zip");
				phoneNum = r.getString("Phone");
			}
			r.close();
			ResultSet points = cn.getLatLngValues(ID);
			while(points.next()){
				myPoints = new LatLng(points.getDouble("Latitude"), points.getDouble("Longitude"));
			}
			points.close();
		}catch (Exception e){
			CID = 0;
		}
	}
	
	//this constructor creates a customer object for search results. No access to points
	public customer(int ID, String fN, String lN, String addLn1, String addLn2, String c, String s, String z, String p, String stat){
		CID = ID;
		firstName = fN;
		lastName = lN;
		addrLineOne = addLn1;
		addrLineTwo = addLn2;
		city =c;
		state = s;
		zip = z;
		phoneNum = p;
		status = stat;
		myPoints = null;
	}

	public LatLng getLatLng() {
		return myPoints;
	}

	public String toString() {
		if (addrLineTwo != null) {
			return "Customer ID: " + CID + "\nName: " + firstName + " " + lastName + "\nAddress: " + addrLineOne + "\n"
					+ addrLineTwo + "\n" + city + ", " + state + " " + zip + "\nPhone Number: " + phoneNum
					+ "\nStatus: " + status;}

		else {
			return "Customer ID: " + CID + "\nName: " + firstName + " " + lastName + "\nAddress: " + addrLineOne + "\n"
					+ city + ", " + state + " " + zip + "\nPhone Number: " + phoneNum + "\nStatus: " + status;
		}
	}

	public boolean modFirstName(String fN) {
		firstName = fN;
		return cn.modCustomerInfo(CID, "FirstName", fN);
	}

	public boolean modLastName(String lN) {
		lastName = lN;
		return cn.modCustomerInfo(CID, "LastName", lN);
	}

	public boolean modAddress(String addLn1, String c, String s, String z){
        addrLineOne = addLn1;
        city = c;
        state = s;
        zip = z;
        try{
        	myPoints = computeLatLng.getLatLongPositions(z + ", " + addLn1 + " " + s);
        }catch(Exception f){
        	return false;
    	}
        return (cn.modCustomerInfo(CID, "Address", addLn1)&&cn.modCustomerInfo(CID, "City", c)&&cn.modCustomerInfo(CID, "State", s)&&cn.modCustomerInfo(CID, "Zip", z));
    }
    
    public boolean modAddress(String addLn1, String addLn2, String c, String s, String z){
        addrLineOne = addLn1;
        addrLineTwo = addLn2;
        city = c;
        state = s;
        zip = z;
        try{
        	myPoints = computeLatLng.getLatLongPositions(z + ", " + addLn1 + " " + s);
        }catch(Exception f){
        	return false;
        }
        return (cn.modCustomerInfo(CID, "Address", addLn1)&&cn.modCustomerInfo(CID, "AddressLineTwo", addLn2)&&cn.modCustomerInfo(CID, "City", c)&&cn.modCustomerInfo(CID, "State", s)&&cn.modCustomerInfo(CID, "Zip", z));
    }
	public boolean modPhoneNum(String pN) {
		phoneNum = pN;
		return cn.modCustomerInfo(CID, "Phone", pN);
	}
	
	public boolean setStatus(String st){
		if(status.equals(st)){
			return false;
		}
		else{
			status = st;
			return cn.modCustomerInfo(CID, "Status", st);
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getCID(){
		return CID;
	}
	    
	public String getFullName(){
		return firstName + " " + lastName;
	}
	
	public String getAddLn1(){
		return addrLineOne;
	}
	
	public String getAddLn2(){
		return addrLineTwo;
	}
	
	public String getCity(){
		return city;
	}
	
	public String getState(){
		return state;
	}
	
	public String getZip(){
		return zip;
	}
	
	public String getPhone(){
		return phoneNum;
	}
	    
	public String getAddress(){
		if(addrLineTwo!=null){
			return String.format("%s%n%s%n%s, %s %s", addrLineOne, addrLineTwo, city, state, zip);
		}
		else{
			return String.format("%s%n%s, %s %s", addrLineOne, city, state, zip);
		}
	}
	
	public void close(){
		cn.disconnect();
	}
	    	
	//for use without creating a customer object
	public static String getAddress(String addLn1, String addLn2, String c, String s, String z){
		if(addLn2!=null&&addLn2!=""){
			return String.format("%s%n%s%n%s, %s %s", addLn1, addLn2, c, s, z);
		}
		else{
			return String.format("%s%n%s, %s %s", addLn1, c, s, z);
		}
	}
	
	public static ArrayList<String> getSearchCustomers(String fN, String lN){
		connect c = new connect();
		ResultSet r = c.searchCustomer(0, fN, lN);
		ArrayList<String> allResults = new ArrayList<>();
		try{
			while(r.next()){
				if(r.getString("AddressLineTwo")!=null)
					allResults.add("Customer ID: "+r.getInt("CustomerID") + "<br>" + r.getString("FirstName") + " " + r.getString("LastName") + "<br>" +
							r.getString("Address") + "<br>" + r.getString("AddressLineTwo") + "<br>" +
							r.getString("City") + ", " + r.getString("State") + " " + r.getString("Zip") + "<br>" +
							r.getString("Phone"));
				else
					allResults.add("Customer ID: "+r.getInt("CustomerID") + "<br>" + r.getString("FirstName") + " " + r.getString("LastName") + "<br>" +
							r.getString("Address") + "<br>" +
							r.getString("City") + "<br>" + r.getString("State") + "<br>" + r.getString("Zip") + "<br>" +
							r.getString("Phone"));
			
			}
			r.close();
			c.disconnect();
			return allResults;
		}catch(Exception e){
			c.disconnect();
			return null;
		}
	}
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
 package connections;


import java.sql.*;
import java.util.ArrayList;

public class customer {

	private String firstName;
	private String lastName;
	private String addrLineOne;
	private String addrLineTwo;
	private String city;
	private String state;
	private String zip;
	private String phoneNum;
	private int CID;
	protected String publicationName;
	private String status;
	private LatLng myPoints;
	private connect cn = new connect();
	

	// create new customer AddTypeOne
	public customer(String fN, String lN, String addLn1, String c, String st, String z, String pN) {
		cn.addCustomer(fN, lN, addLn1, "", c, st, z, pN);
		CID = cn.getCustomerID(pN);
		myPoints = new LatLng(cn, CID, z, addLn1, st);
		status = "ACTIVE";
		firstName = fN;
		lastName = lN;
		addrLineOne = addLn1;
		addrLineTwo = null;
		city = c;
		state = st;
		zip = z;
		phoneNum = pN;
		int pid = cn.getPublicationID("Bill");
		subcriptions billSub = subscription(cn, CID, pid);
	}

	// create new customer AddTypeTwo
	public customer(String fN, String lN, String addLn1, String addLn2, String c, String st, String z, String pN) {
		cn.addCustomer(fN, lN, addLn1, addLn2, c, st, z, pN);
		CID = cn.getCustomerID(pN);
		myPoints = new LatLng(cn, CID, z, addLn1, st);
		status = "ACTIVE";
		firstName = fN;
		lastName = lN;
		addrLineOne = addLn1;
		addrLineTwo = null;
		city = c;
		state = st;
		zip = z;
		phoneNum = pN;
		int pid = cn.getPublicationID("Bill");
		subcriptions billSub = subscription(cn, CID, pid);
	}
	
	// select customer with specified customer ID
	public customer(int ID) {
		ResultSet r = cn.searchCustomer(ID, "", "");
		try {
			while (r.next()) {
				CID = r.getInt("CustomerID");
				System.out.println("cust id " + CID);
				status = r.getString("Status");
				System.out.println("cust status " + CID);

				firstName = r.getString("FirstName");
				lastName = r.getString("LastName");
				addrLineOne = r.getString("Address");
				addrLineTwo = r.getString("AddressLineTwo");

				city = r.getString("City");
				state = r.getString("State");
				zip = r.getString("Zip");
				phoneNum = r.getString("Phone");
			}

			r.close();

		}catch (Exception e){
			CID = 0;
		}
	}
	
	//this constructor creates a customer object for search results. No access to points
	public customer(int ID, String fN, String lN, String addLn1, String addLn2, String c, String s, String z, String p, String stat){
		CID = ID;
		firstName = fN;
		lastName = lN;
		addrLineOne = addLn1;
		addrLineTwo = addLn2;
		city =c;
		state = s;
		zip = z;
		phoneNum = p;
		status = stat;
		myPoints = null;
	}

	public LatLng getLatLng() {
		return myPoints;
	}

	public String toString() {
		if (addrLineTwo != null) {
			return "Customer ID: " + CID + "\nName: " + firstName + " " + lastName + "\nAddress: " + addrLineOne + "\n"
					+ addrLineTwo + "\n" + city + ", " + state + " " + zip + "\nPhone Number: " + phoneNum
					+ "\nStatus: " + status;}

		else {
			return "Customer ID: " + CID + "\nName: " + firstName + " " + lastName + "\nAddress: " + addrLineOne + "\n"
					+ city + ", " + state + " " + zip + "\nPhone Number: " + phoneNum + "\nStatus: " + status;
		}
	}

	public boolean modFirstName(String fN) {
		firstName = fN;
		return cn.modCustomerInfo(CID, "FirstName", fN);
	}

	public boolean modLastName(String lN) {
		lastName = lN;
		return cn.modCustomerInfo(CID, "LastName", lN);
	}

	public boolean modAddress(String addLn1, String c, String s, String z){
        addrLineOne = addLn1;
        city = c;
        state = s;
        zip = z;
        addrLineTwo = null;
        try{
        	myPoints = computeLatLng.getLatLongPositions(z + ", " + addLn1 + " " + s);
        }catch(Exception f){
        	return false;
    	}
        return (cn.modCustomerInfo(CID, "Address", addLn1)&&cn.modCustomerInfo(CID, "City", c)&&cn.modCustomerInfo(CID, "State", s)&&cn.modCustomerInfo(CID, "Zip", z));
    }
    
    public boolean modAddress(String addLn1, String addLn2, String c, String s, String z){
        addrLineOne = addLn1;
        addrLineTwo = addLn2;
        city = c;
        state = s;
        zip = z;
        try{
        	myPoints = computeLatLng.getLatLongPositions(z + ", " + addLn1 + " " + s);
        }catch(Exception f){
        	return false;
        }
        return (cn.modCustomerInfo(CID, "Address", addLn1)&&cn.modCustomerInfo(CID, "AddressLineTwo", addLn2)&&cn.modCustomerInfo(CID, "City", c)&&cn.modCustomerInfo(CID, "State", s)&&cn.modCustomerInfo(CID, "Zip", z));
    }
	public boolean modPhoneNum(String pN) {
		phoneNum = pN;
		return cn.modCustomerInfo(CID, "Phone", pN);
	}
	
	public boolean setStatus(String st){
		if(status.equals(st)){
			return false;
		}
		else{
			status = st;
			return cn.modCustomerInfo(CID, "Status", st);
		}
	}
	public String getStatus(){
		return status;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getCID(){
		return CID;
	}
	    
	public String getFullName(){
		return firstName + " " + lastName;
	}
	
	public String getAddLn1(){
		return addrLineOne;
	}
	
	public String getAddLn2(){
		return addrLineTwo;
	}
	
	public String getCity(){
		return city;
	}
	
	public String getState(){
		return state;
	}
	
	public String getZip(){
		return zip;
	}
	
	public String getPhone(){
		return phoneNum;
	}
	    
	public String getAddress(){
		if(addrLineTwo!=null){
			return String.format("%s%n%s%n%s, %s %s", addrLineOne, addrLineTwo, city, state, zip);
		}
		else{
			return String.format("%s%n%s, %s %s", addrLineOne, city, state, zip);
		}
	}
	
	public void close(){
		cn.disconnect();
	}
	    	
	//for use without creating a customer object
	public static String getAddress(String addLn1, String addLn2, String c, String s, String z){
		if(addLn2!=null&&addLn2!=""){
			return String.format("%s%n%s%n%s, %s %s", addLn1, addLn2, c, s, z);
		}
		else{
			return String.format("%s%n%s, %s %s", addLn1, c, s, z);
		}
	}
	
	public static ArrayList<String> getSearchCustomers(String fN, String lN){
		connect c = new connect();
		ResultSet r = c.searchCustomer(0, fN, lN);
		ArrayList<String> allResults = new ArrayList<>();
		try{
			while(r.next()){
				if(r.getString("AddressLineTwo")!=null)
					allResults.add("Customer ID: "+r.getInt("CustomerID") + "<br>" + r.getString("FirstName") + " " + r.getString("LastName") + "<br>" +
							r.getString("Address") + "<br>" + r.getString("AddressLineTwo") + "<br>" +
							r.getString("City") + ", " + r.getString("State") + " " + r.getString("Zip") + "<br>" +
							r.getString("Phone"));
				else
					allResults.add("Customer ID: "+r.getInt("CustomerID") + "<br>" + r.getString("FirstName") + " " + r.getString("LastName") + "<br>" +
							r.getString("Address") + "<br>" +
							r.getString("City") + ", " + r.getString("State") + " " + r.getString("Zip") + "<br>" +
							r.getString("Phone"));
			
			}
			r.close();
			c.disconnect();
			return allResults;
		}catch(Exception e){
			c.disconnect();
			return null;
		}
	}
>>>>>>> master
=======
>>>>>>> Nathan_Branch
>>>>>>> master
>>>>>>> master
}