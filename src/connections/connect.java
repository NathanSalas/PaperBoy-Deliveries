package src.connections;
import java.sql.*;
import java.util.Date;

/**
 * Establishes connection between the database and classes. May add, view, and modify fields in the database.
 *
 * @author Lee Katsumata
 * @version 1
 */
public class connect{
	//private static final String computeLatLng = null;
	private Connection con;
	private Statement stmt;
	
	/**
	 * Constructor for the connection.
	 * Initializes the necessary connection to the database and creates statement(s) needed to extract information.
	 */
	public connect(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
<<<<<<< HEAD
			con = DriverManager.getConnection("jdbc:mysql://localhost:3308/saturdays_db", "root", "12345");

=======
<<<<<<< HEAD
<<<<<<< HEAD
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/NewDB", "root", "");
=======
			con = DriverManager.getConnection("jdbc:mysql://localhost:3308/saturdays_db", "root", "12345");
>>>>>>> master
=======
			con = DriverManager.getConnection("jdbc:mysql://localhost:3308/saturdays_db", "root", "12345");
=======
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/NewDB", "root", "");
>>>>>>> Nathan_Branch
>>>>>>> master
>>>>>>> master
			stmt = con.createStatement();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * Get method returns the Connection 
	 * Note: This is likely not going to be used.
	 * 
	 * @return Connection
	 */
	public Connection getConnection(){
		return con;
	}
	
	/**
	 * Add subscriptions now additionally needs two more parameters String start and String end (strings in form "yyyy-MM-dd")
	 * 
	 * @param CID	The integer that identifies the customer of the subscription.
	 * @param PID	The integer that identifies the publication that is being subscribed to.
	 * @param start	The date that the subscription began on.
	 * @param end	The date that the subscription is to end on.
	 */
	public boolean addSubscriptions(int CID, int PID,String start ,String end){
		try{
			stmt.executeUpdate("insert into SUBSCRIPTIONS (CustomerID, PublicationID, StartDate, EndDate) values (\"" + CID + "\",\"" + PID+"\",  \"" + start+ "\", \"" + end + "\")");
			return true;
		}
		
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Returns the SubscriptionID that matches the given CustomerID in the database.
	 * Used to get the automatically generated SubscriptionID from the database when adding a new subscription
	 *
	 * @return int
	 * 
	 * @param CID	The integer that identifies the customer to find corresponding SubscriptionID
	 **/
	public int getSubscriptionID(int CID){
		try{
			ResultSet rs = stmt.executeQuery("select * from subscriptions where CustomerID = " + CID);
			if(rs.next()){
				return rs.getInt("ItemID");
			}
			return 0;
		}
		catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	
	public ResultSet searchCustomerWhoSubscribeTo(int PID){
		ResultSet rs;
		try{
			 rs = stmt.executeQuery("select * from coor_cust_pub_scrip where PublicationID = " + PID);
			return rs;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Returns the ResultSet containing the subscription information for the given CustomerID.
	 *
	 * @return ResultSet
	 * 
	 * @param CID	The int that identifies the customer who the caller would like to get the subscription information for
	 * @param PID   The int that identifies a single subscription of a customers to a publication
	 **/
	public ResultSet getSubscriptions(int CID, int PID){
		ResultSet subs;
		try{
			if(PID== 0){
				subs = stmt.executeQuery("select * from subscriptions where CustomerID = " + CID);
			}
			else{
				subs = stmt.executeQuery("select * from subscriptions where CustomerID = " + CID + " AND PublicationID = " + PID);

			}
			return subs;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	public ResultSet getOneSubscription(int SID){
		ResultSet rs;
		try{
			rs = stmt.executeQuery("select * from subscriptions where ItemID = " + SID);
			return rs;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Adds a new customer with the given descriptive information.
	 * 
	 * @param fN		The customer's first name
	 * @param lN		The customer's last name
	 * @param pN		The customer's phone number
	 * @param addLn1	The customer's address
	 * @param addLn2	The customer's address line two (optional)
	 * @param c			The customer's city
	 * @param st		The customer's state
	 * @param z			The customer's zip code
	 **/
	public boolean addCustomer(String fN, String lN,  String addLn1, String addLn2, String c, String st, String z,String pN){
		String add;
		try{
			if(addLn2.length()>0){
				add = "insert into CUSTOMERS (FirstName, LastName, Address, AddressLineTwo, City, State, Zip, Phone)" + " values (\"" + fN + "\", \"" + lN + "\", \"" + addLn1 +  "\", \"" + addLn2 + "\", \"" + c + "\", \"" + st + "\", \"" + z + "\", \"" + pN +"\")";
				
			}
			
			else{
				add = "insert into CUSTOMERS (FirstName, LastName, Address, City, State, Zip, Phone)" + " values (\"" + fN + "\", \"" + lN + "\", \"" + addLn1 +  "\", \"" + c + "\", \"" + st + "\", \"" + z + "\", \"" + pN +"\")";
			}
			System.out.println(add);
			stmt.executeUpdate(add);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	/**
	 * function populates the coordinates database with generated CID from customer database
	 * lat and long.
	 */
	public void addLatLngToCustomer(int CID){
		ResultSet rs;
		String addCoordinates;
		String upsformattedAdd = "";
		try{
			if(CID != 0){
				rs = stmt.executeQuery("select * from customers where CustomerID = " + CID);
					while(rs.next()){
						upsformattedAdd = rs.getString("Zip") +", "+ rs.getString("Address") + " " + rs.getString("State");
				}
			LatLng points = computeLatLng.getLatLongPositions(upsformattedAdd);
	     	addCoordinates = "insert into COORDINATES (CustomerID, Latitude, Longitude)" + " values (\"" + CID + "\",\"" + points.getLat() + "\", \"" + points.getLng() + "\")";
			System.out.println(addCoordinates);
			stmt.executeUpdate(addCoordinates);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();

		}
	}
	
	//This is in the testing stage for now, modified Ashley's lat and long stuff
	public LatLng addLatLngToCustomer(int CID, String z, String addr, String s){
		String addCoordinates;
		String upsformattedAdd = "";
		try{
			upsformattedAdd = z +", "+ addr + " " + s;
			LatLng points = computeLatLng.getLatLongPositions(upsformattedAdd);
	     	addCoordinates = "insert into COORDINATES (CustomerID, Latitude, Longitude)" + " values (\"" + CID + "\",\"" + points.getLat() + "\", \"" + points.getLng() + "\")";
			System.out.println(addCoordinates);
			stmt.executeUpdate(addCoordinates);
			return points;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet getLatLngValues(int CID){
		ResultSet rs;
		try{
		    rs = stmt.executeQuery("select * from coordinates where CustomerID = \"" + CID + "\"");
		    return rs;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean modLatLngValues(int CID, double lat, double lng){
		try{
			stmt.executeUpdate("update coordinates set Latitude = " + lat + ", Longitude = " + lng + " where CustomerID = " + CID);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	public ResultSet searchCustomerCoordinates(int CID){
		ResultSet rs;
		try{
			if(CID!=0){
				rs = stmt.executeQuery("select * from coor_cust_pub_scrip where CustomerID = " + CID);
			}
				
			else {
				rs = null;
			}
			return rs;
		}
		catch(Exception e){
				e.printStackTrace();
				return null;
		}
	}
	
	/*
	 * Retrieves all customers who have the status == "ACTIVE"
	 */
	public ResultSet getAllCustomers(){
		ResultSet rs;
		try{
			rs = stmt.executeQuery("select * from customers where Status =\"" + "ACTIVE" + "\""  );
			return rs;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultSet getAllPublications(){
		ResultSet rs;
		try{
			rs = stmt.executeQuery("select * from coor_cust_pub_scrip where PublicationStatus =\"" + "ACTIVE" + "\""  );
			return rs;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
		
	/**
	 * Returns the ResultSet containing the set of customers that match the given information. 
	 * This method is to accept a CustomerID which is an integer
	 * One customer with the given ID will be in the ResultSet. 
	 *
	 * @return ResultSet
	 * @param CID	The int that identifies the customer to search for
	 **/
	public ResultSet searchForCustomerInView(int CID, int PID){
		ResultSet rs;
		try{
			if(PID == 0){
				rs = stmt.executeQuery("select * from coor_cust_pub_scrip where CustomerID = " + CID);
				return rs;
			}
			else{
				rs = stmt.executeQuery("select * from coor_cust_pub_scrip where CustomerID = " + CID +" AND PublicationID = "+ PID);
				return rs;
			}		
		}
		catch(Exception e){
				e.printStackTrace();
				return null;
		}
	}
		
	/**
	 * Returns the ResultSet containing the set of customers that match the given information. This method is to accept either a CustomerID or a name. 
	 * If the CustomerID is provided, one customer with the given ID will be in the ResultSet. 
	 * If a name (first, last, or both) is provided, all customers that match the given information will be in the ResultSet.
	 *
	 * @return ResultSet
	 * 
	 * @param CID	The int that identifies the customer to search for
	 * @param fN	The first name of the customer to search for
	 * @param lN	The last name of the customer to search for
	 **/
	public ResultSet searchCustomer(int CID, String n1, String n2){
		ResultSet rs;
		try{
			if(CID!=0){
				rs = stmt.executeQuery("select * from customers where CustomerID = " + CID);
			}
			else{
				if(n2.length()==0)
					rs = stmt.executeQuery("select * from customers where LastName = \"" + n1 + "\" OR FirstName = \"" + n1 + "\"");
				else{
					rs = stmt.executeQuery("select * from customers where (FirstName = \"" + n1 + "\" AND LastName = \"" + n2 + "\") OR (FirstName = \"" + n2 + "\" AND LastName = \"" + n1 + "\")");
				}
			}
			return rs;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//gets information required to print out bill; see customerpublications view in db
	/**
	 * 
	 * @return
	 */
	public ResultSet getBillInfo(){
		try{
			return stmt.executeQuery("select * from customerpublications order by CustomerID, PublicationTitle");
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * NEEDS TO BE SEEN!!
	 * 
	 * Gets the information on customers due for delivery on the given day.
	 * Includes customer address information and their subscriptions.
	 * Received in order of...
	 * 
	 * @param wk	The current day of the week 1-6(Su-Sa)
	 * @param mn	The current day of the month starting at 1
	 * @return ResultSet
	 */
	public ResultSet getDailyCustomerInfo(int wk, int mn){
		try{
<<<<<<< HEAD
			return stmt.executeQuery("select * from customerpublications where (DeliveryDays = " + wk + " AND Frequency = \"weekly\") OR (DeliveryDays = " + mn + " AND Frequency = \"monthly\") OR Frequency = \"daily\" order by Latitude, Longitude, CustomerID");

=======
<<<<<<< HEAD
<<<<<<< HEAD
=======
			return stmt.executeQuery("select * from customerpublications where (DeliveryDays = " + wk + " AND Frequency = \"weekly\") OR (DeliveryDays = " + mn + " AND Frequency = \"monthly\") OR Frequency = \"daily\" order by Latitude, Longitude, CustomerID");
=======
>>>>>>> master
			return stmt.executeQuery("select * from customerpublications where "
					+ "(DeliveryDays = " + wk + " AND Frequency = \"weekly\") OR "
							+ "(DeliveryDays = " + mn + " AND Frequency = \"monthly\") "
									+ "OR Frequency = \"daily\" order by CID");
<<<<<<< HEAD
=======
			return stmt.executeQuery("select * from customerpublications where (DeliveryDays = " + wk + " AND Frequency = \"weekly\") OR (DeliveryDays = " + mn + " AND Frequency = \"monthly\") OR Frequency = \"daily\" order by Latitude, Longitude, CustomerID");
>>>>>>> master
=======
>>>>>>> Nathan_Branch
>>>>>>> master
>>>>>>> master
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Gets a list of publications due for delivery on the given day.
	 * List includes the name of each publication and a count of how many due.
	 * 
	 * @param wk	The current day of the week 1-6(Su-Sa)
	 * @param mn	The current day of the month starting at 1
	 * @return ResultSet
	 */
	public ResultSet getSummaryPublicationInfo(int wk, int mn){
		try{
			return stmt.executeQuery("SELECT PublicationTitle, count(*) as 'Count' FROM customerpublications where DeliveryDays = " + wk + " OR DeliveryDays = " + mn + " OR Frequency = \"daily\" GROUP BY PublicationTitle ORDER BY PublicationTitle");
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Modifies a specified field of the customer's information. Returns whether the modification was successfully made.
	 *
	 * @return boolean
	 * 
	 * @param CID	The int that identifies the customer to be modified
	 * @param type	The type of information to be modified (FirstName, LastName, Address, etc.)
	 * @param to	The string that the specified information will be modified to
	 **/
	public boolean modCustomerInfo(int CID, String type, String to){
		try{
			stmt.executeUpdate("update customers set " + type + " = \"" + to + "\" where CustomerID = " + CID);
			System.out.println("CID: " + CID + ", type: " + type );

			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	/**
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> a6dcfcfcbca8a4c07535ca5f7ef92de01fa8f224
	 * NEEDS TO BE SEEN!!!
	 * 
	 * 
	 * 
	 * Modifies all of the customer's information at once in the database. Returns whether the modification was successfully made.
	 * Although it is not likely that all fields will be modified, any combination of them may be modified using this method.
	 * Does not modify customer status.
	 * 
	 * @param CID		The integer that identifies the customer to be modified
	 * @param type
	 * @param to
	 * @return boolean
	 **/
	public boolean modCustomerInfo(int CID, String type, Date to){
		try{
			stmt.executeUpdate("update customers set " + type + " = \"" + to + "\" where CustomerID = " + CID);

			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	/**
<<<<<<< HEAD
=======
=======
>>>>>>> master
>>>>>>> a6dcfcfcbca8a4c07535ca5f7ef92de01fa8f224
	 * Returns the customer's ID based off of the phone number (unique).
	 * This is necessary because the customer ID is automatically generated within the database.
	 * 
	 * @param pN	The phone number to search for the customer ID by
	 * @return int
	 **/
	public int getCustomerID(String pN){
		try{
			ResultSet rs = stmt.executeQuery("select * from customers where Phone = \"" + pN + "\"");
			if(rs.next()){
				return rs.getInt("CustomerID");
			}
			return 0;
		}
		catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Adds a new publication to the database.
	 * 
	 * @param title		The title of the publication
	 * @param genre		The genre of the publication
	 * @param price		The price of the publication
	 * @param frequency	The frequency of the publication (Daily, Weekly, Monthly)
	 * @param issuedOn	The date that the publication was first issued	
	 **/
	public void addPublication(String title, String genre, double price, String frequency, int day){
		try{
			stmt.executeUpdate("insert into publications (PublicationName, Genre, Price, Frequency, DeliveryDays) values (\"" + title + "\", \"" + genre + "\", \"" + price + "\", \"" + frequency + "\", " + day + ")");
		}
		catch(Exception e){
			e.printStackTrace();

			
		}
	}
	
	/**
	 * Returns the publication ID based off of the title (unique).
	 * This is necessary because the PunlicationID is automatically generated in the database.
	 * 
	 * @param t		The title of the publication to get the ID for
	 * @return int
	 **/
	public int getPublicationID(String t){

		int pid = 0;
		try{
			ResultSet rs = stmt.executeQuery("select * from publications where PublicationName = \"" + t + "\"");
			if(rs.next()){
				pid =rs.getInt("PublicationID");

				return pid;
			}
			return pid;
			
		}
		catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * Returns the ResultSet containing publications that fall under the criteria searched for.
	 * If the caller provides a PublicationID, one publication will be in the ResultSet.
	 * If the caller provides a title, all publications that contain the given title will be in the ResultSet
	 * 
	 * @param PID	The int that identifies the publication to search for
	 * @param t		The title of the publication to search for
	 * @return ResultSet
	 **/
	public ResultSet searchPublication(int PID, String t, String genre){
		ResultSet rs;
		try{
<<<<<<< HEAD
=======
<<<<<<< HEAD
<<<<<<< HEAD
=======
			if(PID!=0){
				rs = stmt.executeQuery("select * from publications where PublicationID = " + PID);
			}
			else{
				rs = stmt.executeQuery("select * from publications where PublicationName = \"" + t + "\"");
=======
>>>>>>> master
>>>>>>> master
			if(PID != 0){
				rs = stmt.executeQuery("select * from publications where PublicationID = " + PID);
			}else if(t.length() != 0 && genre.length() == 0){
				rs = stmt.executeQuery("select * from publications where PublicationName like \'%" + t + "%\'");
			}else if(genre.length() != 0 && t.length() == 0){
				rs = stmt.executeQuery("select * from publications where genre like \'%" + genre + "%\'");
			}else {
<<<<<<< HEAD
				rs = stmt.executeQuery("select * from publications");

=======
				rs = stmt2.executeQuery("select * from publications");
<<<<<<< HEAD
=======
			if(PID!=0){
				rs = stmt.executeQuery("select * from publications where PublicationID = " + PID);
			}
			else{
				rs = stmt.executeQuery("select * from publications where PublicationName = \"" + t + "\"");
>>>>>>> master
=======
>>>>>>> Nathan_Branch
>>>>>>> master
>>>>>>> master
			}
			return rs;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Modifies publication price. Returns whether the modification was successfully made.
	 *
	 * @return boolean
	 * 
	 * @param PID		The int that identifies the publication to modify
	 * @param newPrice	The publication's new price 
	 **/
	public boolean modPublicationInfo(int PID, double newPrice){
		try{
			stmt.executeUpdate("update publications set Price = " + newPrice + " where PublicationID = " + PID);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	/**
	 * Modifies the publication status. Returns whether the modification was successfully made.
	 * 
	 * @param PID	The in that identifies the publication to modify
	 * @param st	The new status of the publication
	 * @return boolean
	 **/
	public boolean modPublicationInfo(int PID, String type, String to){
		try{
			System.out.println("update publications set \""+ type + "\" = \"" + to + "\" where PublicationID = " + PID);
			//stmt.executeUpdate("update publications set \""+ type + "\" = \"" + to + "\" where PublicationID = " + PID);
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	/**
	 * Sets the user profile for the first time.
	 * This will only happen once during the system setup.
	 * 
	 * @param N		The login name chosen by the user
	 * @param cN	The company name
	 * @param P		The login password chosen by the user
	 * @param Addr	The company address
	 * @param C		The company city
	 * @param S		The company state
	 * @param Z		The company zip code
	 * @param E		The e-mail address to be used if password reset is necessary
	 * @param CSP	The company customer service phone number
	 * @param CSE	The company customer service e-mail address
	 * @param FP	The file path chosen by the user
	 * @return boolean
	 */
	public boolean userSetProfile(String N, String cN, String P, String Addr, String C, String S, String Z, String E, String CSP, String CSE, String FP, double la, double lo){
		try{
			stmt.executeUpdate("insert into USERPROFILE (UserID, Name, CompanyName, Password, Address, City, State, Zip, Email, CSPhone, CSEmail, FilePath, Latitude, Longitude) values (1, \"" + N + "\", \"" + cN + "\", \"" + P + "\", \"" + Addr + "\", \"" + C + "\", \"" + S + "\", \"" + Z + "\", \"" + E + "\", \"" + CSP + "\", \"" + CSE + "\", \"" + FP + "\", " + la + ", " + lo + ")");
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	/**
	 * Gets all of the user information from the database.
	 * User information is stored in one row.
	 * 
	 * @return ResultSet
	 */
	public ResultSet userGetProfile(){
		ResultSet rs;
		try{
			rs = stmt.executeQuery("select * from userprofile where UserID = 1");
			return rs;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	//Created to just get user's lat or long
	public double userGetLatLng(String type){
		ResultSet rs;
		try{
			rs = stmt.executeQuery("select Latitude, Longitude from userprofile where UserID = 1");
			return rs.getDouble(type);
		}
		catch(Exception e){
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * Gets, from the database, the file path for the text documents to be sent to when generated.
	 * Text documents are summaries, driver routes, and bills.
	 * 
	 * @return ResultSet
	 */
	public ResultSet userGetfilePath(){
		ResultSet rs;
		try{
			rs = stmt.executeQuery("select FilePath from userprofile where UserID = 1");
			return rs;
		}
		catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Modifies the user data in the database.
	 * User data is initialized when the system is set up and can be changed after a login.
	 * 
	 * @param type	The name for the column in the database corresponding to the data that is to be changed
	 * @param to	The value that the data is to be
	 * 
	 * @return boolean
	 */
	public boolean userModData(String type, String to){
		try{
			stmt.executeUpdate("update userprofile set " + type + " = \"" + to + "\" where UserID = 1");
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	public boolean userModAddress(String addr, String c, String s, String z, double la, double lo){
		try{
			stmt.executeUpdate("update userprofile set Address = \"" + addr + "\", City = \"" + c + "\", State = \"" + s + "\", Zip = \"" + z + "\", Latitude = " + la + ", Longitude = " + lo + " where UserID = 1");
			return true;
		}
		catch(Exception e){
			return false;
		}
	}
	
	/**
	 * Closes any open statement and disconnects the connection to the database.
	 * Each time the user logs in to the system, a new connection is made and closed.
	 */
	public void disconnect(){
		try{
			stmt.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void deleteColumn(String tableName, int ID){
		try{
			if(tableName == "publications"){
	    		stmt.executeUpdate( "DELETE from publications where PublicationID = \""+ ID + "\"");
			}
			else if( tableName == "customers"){
	    		stmt.executeUpdate( "DELETE from customers where CustomerID = \""+ ID + "\"");
			}else if(tableName == "subscriptions"){
	    		stmt.executeUpdate( "DELETE from subscriptions where ItemID = \""+ ID + "\"");
			}else{
	    		stmt.executeUpdate( "DELETE from userprofile where UserID = \""+ ID + "\"");

			}
    	}catch(Exception e){
 			e.printStackTrace();
 			System.out.println("in delete");
		}
	}
}