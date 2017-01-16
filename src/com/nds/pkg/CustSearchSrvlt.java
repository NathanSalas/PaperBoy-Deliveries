package src.com.nds.pkg;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import src.connections.customer;

/**
 * Servlet implementation class CustSearchSrvlt
 */
@WebServlet("/CustSearchSrvlt")
public class CustSearchSrvlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustSearchSrvlt() {
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
		String s = ((String)request.getParameter("sub"));
		if(s.equals("Submit Name")){
			String n = ((String)request.getParameter("n")).trim();
			if(!n.matches("^[a-zA-Z ]+$")){
				request.setAttribute("names", n);
				request.setAttribute("errorMsg", "Please enter names only.");
				request.getRequestDispatcher("/CustomerLookup.jsp").forward(request, response);
			}
			String[] names = n.split("\\s+");
			if(Array.getLength(names)>2){
				request.setAttribute("names", n);
				request.setAttribute("errorMsg", "Please enter two name entries maximum.");
				request.getRequestDispatcher("/CustomerLookup.jsp").forward(request, response);
			}
			ArrayList<String> results;
			if(Array.getLength(names)==1)
				results = customer.getSearchCustomers(names[0], "");
			else
				results = customer.getSearchCustomers(names[0], names[1]);
			String r="";
			for(int i=0;i<results.size();i++){
				String[] id = results.get(i).substring(13, 16).split("[^0-9]");
				String[] rest = results.get(i).split("<br>");
				System.out.println(results.get(i));
				System.out.println(id[0]);
				r+=results.get(i)
						+"<form action=\"CustEditSrvlt\" method=\"post\"> "
						+ " <input type=\"hidden\" name=\"cid\" value=\""+ id[0] + "\">"
						+ " <input type=\"hidden\" name=\"CustomerName\" value=\"" + rest[1] + "\">"
						+ " <input type=\"hidden\" name=\"Address\" value=\"" + rest[2] + "\">"
						+ " <input type=\"hidden\" name=\"City\" value=\"" + rest[3] + "\">"
						+ " <input type=\"hidden\" name=\"State\" value=\"" + rest[4] + "\">"
						+ " <input type=\"hidden\" name=\"Zip\" value=\"" + rest[5] + "\">"
						+ " <input type=\"hidden\" name=\"Phone\" value=\"" + rest[6] + "\">"
						+ "<input type=\"submit\" value=\"Select Customer\" name = \"subm\"></form><br><br><br>";
			}
			request.setAttribute("results", r);
			request.getRequestDispatcher("/CustomerLookup.jsp").forward(request, response);
		}
		else{
			if(s.equals("Submit ID")){
				
			}
		}
	}

}
