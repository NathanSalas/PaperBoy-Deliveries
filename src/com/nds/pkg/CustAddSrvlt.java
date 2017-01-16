package src.com.nds.pkg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.connections.customer;

/**
 * Servlet implementation class CustAddSrvlt
 */
public class CustAddSrvlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustAddSrvlt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(((String)request.getParameter("add")).equals("Cancel")){
			request.getRequestDispatcher("/CustomerCenter.jsp").forward(request, response);
		}
		else{
			String first =((String)request.getParameter("first")).trim();
			String last =((String)request.getParameter("last")).trim();
			String add1 =((String)request.getParameter("add1")).trim();
			String add2 =((String)request.getParameter("add2")).trim();
			String city =((String)request.getParameter("c")).trim();
			String state =(String)request.getParameter("s");
			String zip =((String)request.getParameter("z")).trim();
			String phone = ((String)request.getParameter("p")).trim();
			String err ="";
			boolean valid = true;
			if(first.length()==0||first.length()>30||last.length()==0||last.length()>30){
				err+="Invalid first or last name (1-30 characters each).<br>";
				valid=false;
			}
			else{
				first=Character.toUpperCase(first.charAt(0)) + (first.substring(1)).toLowerCase();
				last=Character.toUpperCase(last.charAt(0)) + (last.substring(1)).toLowerCase();
				if(!first.matches("^[a-zA-Z]+$")){
					err+="Invalid first name.<br>";
					valid=false;
				}
				if(!last.matches("^[a-zA-Z]+$")){
					err+="Invalid last name.<br>";
					valid=false;
				}
			}
			if(!add1.matches("\\d+\\s+[a-zA-Z]+(\\s+[a-zA-Z]+)+")){
				err+="Invalid street name.<br>";
				valid=false;
			}
			if(add2.length()!=0&&!add1.matches("[\\w+\\s*]+")&&add1.length()<30){
				err+="Invalid optional address (30 characters maximum).<br>";
				valid=false;
			}
			if(!city.matches("^[a-zA-Z ]+$")){
				err+="Invalid city name.<br>";
				valid=false;
			}
			if(!zip.matches("\\d{5}")){
				err+="Invalid zip code (5 digits).<br>";
				valid=false;
			}
			phone = phone.replaceAll("\\D", "");
			if(!phone.matches("\\d{10}")){
				err+="Invalid phone number (10 digits).<br>";
				valid=false;
			}
			if(!valid){
				request.setAttribute("errorMsg", err);
				request.setAttribute("first", first);
				request.setAttribute("last", last);
				request.setAttribute("add1", add1);
				request.setAttribute("add2", add2);
				request.setAttribute("c", city);
				request.setAttribute("s", state);
				request.setAttribute("z", zip);
				request.setAttribute("p", phone);
				request.getRequestDispatcher("/CustomerAdd.jsp").forward(request, response);
			}
			else{
				customer c;
				if(add2.length()==0){
					//c = new customer(first, last, add1, city, state, zip, phone); 
					HttpSession sess = request.getSession();
					//sess.setAttribute("CID", c.getCID());
					sess.setAttribute("CID", 1);
					//c.close();
					request.getRequestDispatcher("/SubAddSrvlt").forward(request, response);
				}else{
					//c = new customer(first, last, add1, add2, city, state, zip, phone); 
					HttpSession session = request.getSession();
					//session.setAttribute("CID", c.getCID());
					//c.close();
					session.setAttribute("CID", 1);
					session.setAttribute("firstName", first);
		            session.setAttribute("lastName", last);
		            session.setAttribute("addr", add1);
		            session.setAttribute("addr2", add2);
		            session.setAttribute("city", city);
		            session.setAttribute("state", state);
		            session.setAttribute("zip", zip);
		            session.setAttribute("phone", phone);
					request.getRequestDispatcher("/SubAddSrvlt").forward(request, response);
			    }	
			}
		}
	}
}
