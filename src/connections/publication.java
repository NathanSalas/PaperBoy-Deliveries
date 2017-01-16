package src.connections;

import java.sql.ResultSet;
import java.text.*;


import java.util.ArrayList;

public class publication {
	private String title;
	private String genre;
	private double price;
	private String frequency;
	private int PID;
	private String status;
	private int dayNum;
	private connect cn = new connect();
	NumberFormat fmatr = new DecimalFormat("$#.##"); 
	
	public publication(String tit, String gen, double prc, String freq, int dNum){
		cn.addPublication(tit, gen, prc, freq, dNum);
		PID = cn.getPublicationID(tit);
		dayNum = dNum;
		status = "ACTIVE";
		title = tit;
		genre = gen;
		price = prc;
		frequency = freq;
		dayNum = dNum;
	}
	
<<<<<<< HEAD
	public publication(int ID){
		ResultSet r = cn.searchPublication(ID, "", "");
=======
<<<<<<< HEAD
<<<<<<< HEAD
	public publication(int ID){
		ResultSet r = cn.searchPublication(ID, "", "");
=======
=======
>>>>>>> master
<<<<<<< HEAD
	public publication(connect con, int ID){

		cn = con;
=======
	public publication(int ID){
>>>>>>> master
		ResultSet r = cn.searchPublication(ID, "");

<<<<<<< HEAD
>>>>>>> master
=======
=======
	public publication(int ID){
		ResultSet r = cn.searchPublication(ID, "", "");
>>>>>>> Nathan_Branch
>>>>>>> master
>>>>>>> master
		try{
			while(r.next()){
				PID = r.getInt("PublicationID");
				status = r.getString("Status");
				title = r.getString("PublicationName");
				price = r.getDouble("Price");
				frequency = r.getString("Frequency");
				genre = r.getString("Genre");
				dayNum = r.getInt("DeliveryDays");

			}

		}
		catch(Exception e){
			PID = 0;

		}
	}
	
	public String toString(){
		return "[Publication: " + title + ", Genre: " + genre + "\nPrice: " + fmatr.format(price) 
		+ ", Frequency: " + frequency + ", DeliveryDay: " + dayNum+"]";
	}
	
	public boolean modPrice(double nPrice){
		price = nPrice;
		return cn.modPublicationInfo(PID, nPrice);
	}
	public boolean modName(String name){
		title = name;
		return cn.modPublicationInfo(PID, "name", name);
	}
	public boolean modGenre(String newgenre){
		genre = newgenre;
		return cn.modPublicationInfo(PID, "genre", newgenre);
	}
	public boolean modFrequency(String f){
		frequency = f;
		return cn.modPublicationInfo(PID, "frequency", f);
	}
	public boolean modDeliveryDays(int day){
		dayNum = day;
		String dday = String.valueOf(day);
		return cn.modPublicationInfo(PID, "Delivery Days", dday);
	}
	public boolean modStatus(String st){
		status = st;
		return cn.modPublicationInfo(PID, "Status", st);
	}
	/*
	 * This function calculates the next issue date by using the date the publication was originally issued 
	 * on (firstIssuedOn) and adding whatever increment that correlates to it (e.g. Monthly, weekly, daily)
	 * enough times to get to the next issue date. 
	 * */

	
	public boolean setStatus(String st){
		if(status.equals(st)){
			return false;
		}
		else{
			status = st;
			return cn.modPublicationInfo(PID, "status", st);
		}
	}

	 public double getPrice(){
<<<<<<< HEAD
		 return price;
	 }

=======
<<<<<<< HEAD
<<<<<<< HEAD
=======
	        return price;
	    }
>>>>>>> master
	    
	    public String getBillTitle(){
	        return title + " - " +frequency;
	    }
	  public int getPID(){
		  return PID;
	  }
	  public String getTitle(){
		  return title;
	  }
	

	public void close(){
		cn.disconnect();
	}
<<<<<<< HEAD
=======
>>>>>>> master
=======
>>>>>>> master
		 return price;
	 }

	 public String getBillTitle(){
		 return title + " - " +frequency;
	 }

>>>>>>> master

	 public static ArrayList<String> getSearchPublications(String name, String genre){
		 connect c = new connect();
		 ResultSet r = c.searchPublication(0, name, genre);
		 ArrayList<String> allResults = new ArrayList<>();
		 try{
			 while(r.next()){
				 
				 allResults.add("Publication ID: "+ r.getInt("PublicationID") + "<br>" +
						 r.getString("PublicationName") + "<br>" + 
						 r.getString("Genre") + "<br>" +
						 r.getDouble("Price") + "<br>" +
						 r.getString("Frequency") + "<br>" +
						 r.getString("Status") + "<br>" +
						 r.getString("DeliveryDays") + "<br><br>");
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
}
=======
	 public void close(){
		 cn.disconnect();
	 }
<<<<<<< HEAD
=======
	        return price;
	    }
	    
	    public String getBillTitle(){
	        return title + " - " +frequency;
	    }
<<<<<<< HEAD
	  public int getPID(){
		  return PID;
	  }
	  public String getTitle(){
		  return title;
	  }
	
=======

	public void close(){
		cn.disconnect();
	}
>>>>>>> master
>>>>>>> master
=======
>>>>>>> Nathan_Branch
>>>>>>> master
}
//public static String getNextIssueDate(String issuedDate, String freq){
//
//freq= freq.toLowerCase();
////System.out.println("freq: " + freq);
//Date today = new Date();
//Date nextDate = DateTime.strToDate(issuedDate);
// if(freq.equals("daily")){
//	 while(nextDate.before(today)){
//		 String str = DateTime.dateToStr(nextDate);
//		 String todayStr = DateTime.dateToStr(today);
//		 if(str.equals(todayStr)){
//			break;
//		}
//		 else{
//		    nextDate = DateTime.addOneDay(nextDate);
//			}
//		}
//	}
// else if(freq.equals("weekly")){
//	 while(nextDate.before(today)){
//		 String str = DateTime.dateToStr(nextDate);
//		 String todayStr = DateTime.dateToStr(today);
//		if(str.equals(todayStr)){
//		    break;
//		}else{
//			 nextDate = DateTime.addOneWeek(nextDate);
//			   // System.out.println("next date: "+ nextDate + " today : " + today);
//		}
//	}
//}
// else if(freq.equals("monthly")){
//	 while(nextDate.before(today)){
//		 String str = DateTime.dateToStr(nextDate);
//		 String todayStr = DateTime.dateToStr(today);
//		if(str.equals(todayStr)){
//		    break;
//		}else{
//		nextDate = DateTime.addOneMonth(nextDate);
//		}
//	 }
// }
// String next =  DateTime.dateToStr(nextDate);
//// setNextIssueDate(next);
// return next;
//}
>>>>>>> master
