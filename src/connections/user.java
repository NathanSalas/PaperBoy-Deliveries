package src.connections;
import java.sql.ResultSet;
import java.nio.file.*;

public class user {
	private String name;
	private String companyName;
	private String pass;
	private String addr;
	private String city;
	private String state;
	private String zip;
	private String email;
	private String CSPhone;
	private String CSEmail;
	private String filePath;
	private LatLng myPoints;

	private connect cn = new connect();
	
	//add user data; sets name to null if failure to add to db or set file path
	public user(String n, String cN, String p, String a, String c, String s, String z, String e, String csp, String cse, String fP){
		String str = z + ", " + a +" "+ s;
		try{
			myPoints = computeLatLng.getLatLongPositions(str);

		}catch(Exception f){
			System.out.println("error getting user lat/lng values");
		}
		name = n;
		companyName = cN;
		pass = p;
		addr = a;
		city = c;
		state = s;
		zip = z;
		email = e;
		CSPhone = csp;
		CSEmail = cse;
		filePath = fP+"/PaperBoyPrints";
		if(!cn.userSetProfile(n, cN, p, a, c, s, z, e, csp, cse, filePath, myPoints.getLat(), myPoints.getLng())){
			name = null;
			System.out.println("FAILED");
		}
		try{
			Files.createDirectory(Paths.get(filePath));
		}
		catch(Exception E){
			
		}
	}
	
	//get user data; sets name to null if no data exists
	public user(){
		ResultSet r = cn.userGetProfile();
		try{
			while(r.next()){
				name = r.getString("Name");
				companyName = r.getString("CompanyName");
				pass = r.getString("Password");
				addr = r.getString("Address");
				city = r.getString("City");
				state = r.getString("State");
				zip = r.getString("Zip");
				email = r.getString("Email");
				CSPhone = r.getString("CSPhone");
				CSEmail = r.getString("CSEmail");
				filePath = r.getString("filePath");
				myPoints = new LatLng(r.getDouble("Latitude"), r.getDouble("Longitude"));
			}
			r.close();
		}
		catch(Exception e){
			name = null;
		}
	}
	
	public String toString(){
		return "Username: " + name + "\nPassword: " + pass + "\nAddress: " + addr + "\n" + city + ", " + state + " " + zip + "\nE-mail: " + email + "\nSave Location: " + filePath;
	}
	
	public boolean modUserName(String n){
		name = n;
		return cn.userModData("Name", n);
	}
	
	public boolean modCompanyName(String cN){
		companyName = cN;
		return cn.userModData("CompanyName", cN);
	}
	
	public boolean modUserPassword(String p){
		pass = p;
		return cn.userModData("Password", p);
	}
	
	public boolean modUserAddress(String a, String c, String s, String z){
		addr = a;
		city = c;
		state = s;
		zip = z;
		try{
			myPoints = computeLatLng.getLatLongPositions(z + ", " + a + " " + s);
		}catch(Exception f){
			return false;
		}
		return cn.userModAddress(a, c, s, z, myPoints.getLat(), myPoints.getLng());
	}
	
	public boolean modUserEmail(String e){
		email = e;
		return cn.userModData("Email", e);
	}
	
	public boolean modCSPhone(String csp){
		CSPhone = csp;
		return cn.userModData("CSPhone", csp);
	}
	
	public boolean modCSEmail(String cse){
		CSEmail = cse;
		return cn.userModData("CSEmail", cse);
	}
	
	public boolean modUserFilePath(String fP){
		filePath = fP +"/PaperBoyPrints";
		try{
			Files.createDirectory(Paths.get(filePath));
		}
		catch(Exception e){
			return false;
		}
		return cn.userModData("FilePath", fP);
	}
	
	public String getUserName(){
		return name;
	}
	
	public String getCompanyName(){
		return companyName;
	}
	
	public String getPassword(){
		return pass;
	}
	
	public String getHiddenPassword(){
		return pass.replaceAll("(?s).", "*");
	}
	
	public String getAddress(){
		return String.format("%s%n%s, %s %s", addr, city, state, zip);
	}
	
	public String getAddressLn1(){
		return addr;
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
	
	public String getEmail(){
		return email;
	}
	
	public String getCSPhone(){
		return CSPhone;
	}
	
	public String getCSEmail(){
		return CSEmail;
	}
	
	public String getFilePath(){
		return filePath;
	}
	
	public void closeUser(){
		cn.disconnect();
	}
}