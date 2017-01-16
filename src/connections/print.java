package src.connections;
import java.io.*;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.nio.file.*;

public class print {
	private connect cn;
	private user u;
	private String filePath;
	private String billFilePath;
	private String billPeriod;
	private String header;
	private String today;
	//daily summary: 
	//daily route: list customer with address,
	public print(){
		u = new user();
		header = String.format("%s%n%s%n%s%n%s%n%n",
				u.getCompanyName(), u.getAddress(),
				"Phone: " + u.getCSPhone(), "E-mail: " +u.getCSEmail());
		today = DateTime.getDateNameFile();
		filePath = u.getFilePath()+"/"+DateTime.getDayFilePath();
		if(!Files.exists(Paths.get(filePath))){
			try{
				Files.createDirectories(Paths.get(filePath));
			}
			catch(Exception e){
				filePath = u.getFilePath();
			}
		}
		billFilePath = filePath + "/bills";
		u.closeUser();
	}
	
	//invoice number in name of text file
	public void printAllBills(){
		cn = new connect();
		if(!Files.exists(Paths.get(billFilePath))){
			try{
				Files.createDirectories(Paths.get(billFilePath));
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		billPeriod = DateTime.getBillPeriod();
		String invoiceNum;
		int tempID;
		double totalDue;
		try{
			ResultSet allSubs = cn.getBillInfo();
			allSubs.next();
			while(!allSubs.isAfterLast()){
				tempID = allSubs.getInt("CustomerID");
				invoiceNum = today + "-" +tempID;
				File f = new File(billFilePath, invoiceNum + ".txt");
				BufferedWriter w = new BufferedWriter(new FileWriter(f));
				w.write(String.format("%s%42s%20s%n%35c%s%n%n",
						header, "INVOICE", "#" + invoiceNum,
						' ', "BILL PERIOD: " + billPeriod));
				w.write(String.format("%s%n%s %s%n%s%n%n%-35s%-30s%s%n",
						"BILL/SHIP TO:",
						allSubs.getString("FirstName"), 
						allSubs.getString("LastName"),
						customer.getAddress(allSubs.getString("Address"), 
								allSubs.getString("AddressLineTwo"), 
								allSubs.getString("City"), 
								allSubs.getString("State"), 
								allSubs.getString("Zip")),
						"PUBLICATION INFORMATION", "SUBSCRIPTION PERIOD", "PRICE"));
				totalDue = 0;
				do{
				w.write(String.format("%-35s%-30s%.2f%n" ,
						allSubs.getString("PublicationTitle"),
						allSubs.getString("StartDate") + " to " + allSubs.getString("EndDate"),
						allSubs.getDouble("Price")));
				totalDue +=allSubs.getDouble("Price");
				}while(allSubs.next()&&allSubs.getInt("CustomerID")==tempID);
				w.write(String.format("%n%n%-15s%.2f%38s %s", "TOTAL DUE:", totalDue, "Date Printed:", today));
				w.close();
			}
			allSubs.close();
			cn.disconnect();
			System.out.println("Success");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
//	public ArrayList<String> printBills(){
//		ArrayList<String> s = new ArrayList<String>();
//		if(!Files.exists(Paths.get(billFilePath))){
//			try{
//				Files.createDirectories(Paths.get(billFilePath));
//			}
//			catch(Exception e){
//				e.printStackTrace();
//			}
//		}
//		billPeriod = DateTime.getBillPeriod();
//		String invoiceNum;
//		String tempInfo ="";
//		int tempID=0;
//		double totalDue;
//		try{
//			ResultSet r = cn.getBillInfo();
//			while(r.next()){
//				if(tempID!=r.getInt("CustomerID")){
//					if(tempInfo!=""){
//						s.add(tempInfo);
//					}
//					tempID=r.getInt("CustomerID");
//					s.add(r.getString("Address") + ", " + r.getString("City") + ", " + r.getString("State") + ", " + r.getString("Zip"));
//					if(r.getString("AddressLineTwo")!=null)
//						tempInfo = ("<b>" + r.getString("FirstName") + " " + r.getString("LastName") + "</b><br>" +
//								r.getString("Address") + "<br>" + r.getString("AddressLineTwo") + "<br>" +
//								r.getString("City") + ", " + r.getString("State") + " " + r.getString("Zip") + "<br><i>" + r.getString("PublicationTitle") + "</i><br>");
//					else
//						tempInfo = ("<b>" + r.getString("FirstName") + " " + r.getString("LastName") + "</b><br>" +
//								r.getString("Address") + "<br>" +
//								r.getString("City") + ", " + r.getString("State") + " " + r.getString("Zip") + "<br><i>" + r.getString("PublicationTitle") + "</i><br>");
//				}
//				else{
//					tempInfo+="<i>" + r.getString("PublicationTitle") + "</i><br>";
//				}
//			}
//			r.close();
//		}
//		catch(Exception e){
//			e.printStackTrace();
//		}
//	}
	
	//unfinished
	public void printSummary(){
		try{
			System.out.println(customer.getAddress("A", "", "c", "s", "z"));
			File f = new File(filePath, "Summary.txt");
			BufferedWriter w = new BufferedWriter(new FileWriter(f));
			w.write(String.format("%s%42s %s%n%n%nPUBLICATIONS TOTALS%n%n%-35sCOUNT%n", header, "SUMMARY", today, "TITLE"));
			ResultSet pubList = cn.getSummaryPublicationInfo(DateTime.getWeekday(), DateTime.getMonthday());
			int total = 0;
			while(pubList.next()){
				total+=pubList.getInt("Count");
				w.write(String.format("%-35s%s%n", pubList.getString("PublicationTitle"), pubList.getInt("Count")));
			}
			pubList.close();
			w.write(String.format("%n%-20s%d", "PUBLICATIONS DUE:", total));
			w.close();
			System.out.println("Success");
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static String [] getDeliveryCustomers(){
		connect c = new connect();
		ResultSet r = c.getDailyCustomerInfo(DateTime.getWeekday(), DateTime.getMonthday());
		ArrayList<String> daysDelivery = new ArrayList<>();
		int tempID=0;
		String tempInfo="";
		try{
			while(r.next()){
				if(tempID!=r.getInt("CustomerID")){
					if(tempInfo!=""){
						daysDelivery.add(tempInfo);
					}
					tempID=r.getInt("CustomerID");
					daysDelivery.add(r.getString("Address") + ", " + r.getString("City") + ", " + r.getString("State") + ", " + r.getString("Zip"));
					if(r.getString("AddressLineTwo")!=null)
						tempInfo = ("<b>" + r.getString("FirstName") + " " + r.getString("LastName") + "</b><br>" +
								r.getString("Address") + "<br>" + r.getString("AddressLineTwo") + "<br>" +
								r.getString("City") + ", " + r.getString("State") + " " + r.getString("Zip") + "<br><i>" + r.getString("PublicationTitle") + "</i><br>");
					else
						tempInfo = ("<b>" + r.getString("FirstName") + " " + r.getString("LastName") + "</b><br>" +
								r.getString("Address") + "<br>" +
								r.getString("City") + ", " + r.getString("State") + " " + r.getString("Zip") + "<br><i>" + r.getString("PublicationTitle") + "</i><br>");
				}
				else{
					tempInfo+="<i>" + r.getString("PublicationTitle") + "</i><br>";
				}
			}
			daysDelivery.add(tempInfo);
			r.close();
			c.disconnect();
			String[] arr = new String[daysDelivery.size()];
			arr = daysDelivery.toArray(arr);
			return arr;
		}catch(Exception e){
			c.disconnect();
			return null;
		}
	}
}