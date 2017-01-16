package src.com.nds.pkg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.connections.customer;

/**
 * Servlet implementation class CustEditSubSrvlt
 */
@WebServlet("/CustEditSubSrvlt")
public class CustEditSubSrvlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustEditSubSrvlt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String s = (String)request.getParameter("sub");
		if(s.equals("Cancel")){
			request.getRequestDispatcher("/CustomerEdit.jsp").forward(request, response);
		}
		else{
			String err="";
			boolean valid=true;
			if(s.equals("Submit Name Edit")){
				String first =((String)request.getParameter("fn")).trim();
				String last =((String)request.getParameter("ln")).trim();
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
				if(!valid){
					request.setAttribute("errorMsg", err);
					request.getRequestDispatcher("/CustomerEditName.jsp").forward(request, response);
				}
				else{
					HttpSession session = request.getSession();
					customer c = new customer((int)session.getAttribute("CID"));
					if(c.getCID()==0){
						request.setAttribute("errorMsg", "NO VALID CUSTOMER");
						request.getRequestDispatcher("/CustomerEditName.jsp").forward(request, response);
					}
					c.modFirstName(first);
					c.modLastName(last);
					c.close();
					session.setAttribute("firstName", first);
					session.setAttribute("lastName", last);
					request.setAttribute("msg", "Customer name successfully modified.");
					request.getRequestDispatcher("/CustomerEdit.jsp").forward(request, response);
				}
			}
			else{
				if(s.equals("Submit Number Edit")){
					String phone = ((String)request.getParameter("p")).trim();
					phone = phone.replaceAll("\\D", "");
					if(!phone.matches("\\d{10}")){
						request.setAttribute("errorMsg", "Invalid phone number (10 digits).<br>");
						request.getRequestDispatcher("/CustomerEditNum.jsp").forward(request, response);
					}
					else{
						HttpSession session = request.getSession();
						customer c = new customer((int)session.getAttribute("CID"));
						c.modPhoneNum(phone);
						c.close();
						session.setAttribute("phone", phone);
						request.setAttribute("msg", "Customer phone number successfully modified.");
						request.getRequestDispatcher("/CustomerEdit.jsp").forward(request, response);
					}
				}
				else{
					if(s.equals("Submit Address Edit")){
						String add1 =((String)request.getParameter("a1")).trim();
						String add2 =((String)request.getParameter("a2")).trim();
						String city =((String)request.getParameter("c")).trim();
						String state =(String)request.getParameter("s");
						String zip =((String)request.getParameter("z")).trim();
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
						if(!valid){
							request.setAttribute("errorMsg", err);
							request.getRequestDispatcher("/CustomerEditAddress.jsp").forward(request, response);
						}
						else{
							HttpSession session = request.getSession();
							customer c = new customer((int)session.getAttribute("CID"));
							if(add2.length()==0)
								c.modAddress(add1, city, state, zip);
							else
								c.modAddress(add1, add2, city, state, zip);
							c.close();
							session.setAttribute("addr", add1);
				            session.setAttribute("addr2", add2);
				            session.setAttribute("city", city);
				            session.setAttribute("state", state);
				            session.setAttribute("zip", zip);
							request.setAttribute("msg", "Customer name successfully modified.");
							request.getRequestDispatcher("/CustomerEdit.jsp").forward(request, response);
						}
					}
				}
			}
		}
	}
}
