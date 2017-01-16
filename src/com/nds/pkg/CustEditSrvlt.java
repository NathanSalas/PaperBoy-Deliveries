package src.com.nds.pkg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

import src.connections.publication;

/**
 * Servlet implementation class CustEditSrvlt
 */
public class CustEditSrvlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustEditSrvlt() {
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
		String choice = (String)(request.getParameter("subm"));
		System.out.println("choice " + choice);
		
		if(choice != null){
			HttpSession pubsess = request.getSession();
			request.getRequestDispatcher("/CustomerEdit.jsp").forward(request, response);
			fill(request, pubsess);
		}else{
			String c = request.getParameter("add");
			System.out.println("else" + c);
			handleBtn(c, request, response);
		}
	}
	private void fill(HttpServletRequest request, HttpSession pubsess){
		String[] name = request.getParameter("CustomerName").split("\\s");
		pubsess.setAttribute("CID", request.getParameter("cid"));
		pubsess.setAttribute("firstName", name[0]);
		pubsess.setAttribute("lastName", name[1]);
		pubsess.setAttribute("address", request.getParameter("Address"));
		pubsess.setAttribute("city", request.getParameter("City"));
		pubsess.setAttribute("state", request.getParameter("State"));	
		pubsess.setAttribute("zip", request.getParameter("Zip"));
		pubsess.setAttribute("phone", request.getParameter("Phone"));	
		
	}
	private void handleBtn(String c, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if(c.equals("Edit Customer Details")){
			
			request.getRequestDispatcher("/CustomerEditName.jsp").forward(request, response);
			
		}else if(c.equals("Edit Customer Subscriptions")){
			
			request.getRequestDispatcher("/SubAddSrvlt").forward(request, response);
		}
		
		
	}
}
