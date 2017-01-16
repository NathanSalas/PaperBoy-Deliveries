package src.com.nds.pkg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
/**
 * Servlet implementation class PubEditSrvlt
 */
@WebServlet("/PubEditSrvlt")
public class PubEditSrvlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PubEditSrvlt() {
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
		// TODO Auto-generated method stub
		
		String choice = (String)(request.getParameter("subm"));
		if(choice.equals("Select Publication")){
			HttpSession pubsess = request.getSession();
			request.getRequestDispatcher("/PublicationModify.jsp").forward(request, response);
			fill(request, pubsess);
		}else{
			System.out.println("else");
			handleBtn(choice, request, response);
		}
	}
	
	private void fill(HttpServletRequest request, HttpSession pubsess){
		pubsess.setAttribute("PID", request.getParameter("pid"));
		pubsess.setAttribute("pName", request.getParameter("PublicationName"));
		pubsess.setAttribute("genre", request.getParameter("genre"));
		pubsess.setAttribute("price", request.getParameter("Price"));
		pubsess.setAttribute("frequency", request.getParameter("Frequency"));
		pubsess.setAttribute("status", request.getParameter("Status"));	
	}
	private void handleBtn(String c, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if(c.equals("Edit Name")){
			request.getRequestDispatcher("/PubEditName.jsp").forward(request, response);
		}else if(c.equals("Edit Number")){
			
			request.getRequestDispatcher("/PubEditDetails.jsp").forward(request, response);
		}else if(c.equals("Back to search")){
			
			request.getRequestDispatcher("/PublicationCenter.jsp");
		}
		
		
	}
}
