package src.com.nds.pkg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.connections.publication;

/**
 * Servlet implementation class PubEditSubSrvlt
 */
@WebServlet("/PubEditSubSrvlt")
public class PubEditSubSrvlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PubEditSubSrvlt() {
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
		String err = "";
		boolean valid = true;
		if(s.equals("Cancel")){
			request.getRequestDispatcher("/PublicationCenter.jsp").forward(request, response);
		}
		else{
			if(s.equals("Submit Name Edit")){
				String newPName = ((String)request.getParameter("pName")).trim();
				String newGenre = (String)request.getParameter("genre");
				
				
				if(!valid){
					request.setAttribute("errorMsg", err);
					request.getRequestDispatcher("/EmployeeEdit.jsp").forward(request, response);
				}
				else{
					publication p = new publication(Integer.parseInt(request.getParameter("PID")));
					HttpSession session = request.getSession();
					
					p.close();			
					session.setAttribute("pName", newPName);
					session.setAttribute("genre", newGenre);
					;
					request.setAttribute("msg", "Successfully modified user profile information.");
					request.getRequestDispatcher("/PublicationModify.jsp").forward(request, response);
				}
			}
			else{
				if(s.equals("Submit Detail Edit")){
					
					String newPrice = ((String)request.getParameter("price")).trim();
					String newFreq = ((String)request.getParameter("frequency")).trim();
					String newDDays = ((String)request.getParameter("ddays")).trim();
					String newStatus = (String)request.getParameter("status");
					
					if(newPrice.matches("[^\\d+.\\d+]")){
						err+="Invalid price value (Must be a decimal number).<br>";
						valid=false;
					}	
					
					if(!valid){
						request.setAttribute("errorMsg", err);
						request.getRequestDispatcher("/PubEditDetails.jsp").forward(request, response);
					}else{
						publication p = new publication(Integer.parseInt(request.getParameter("PID")));
						p.modPrice(Double.parseDouble(newPrice));
						p.modFrequency(newFreq);
						p.modDeliveryDays(Integer.parseInt(newDDays));
						p.modStatus(newStatus);
						p.close();
						HttpSession session = request.getSession();
						session.setAttribute("price", newPrice);
						session.setAttribute("frequency", newFreq);
						session.setAttribute("ddays", newDDays);
						session.setAttribute("status", newStatus);
						
						request.setAttribute("msg", "Successfully modified company address.");
						request.getRequestDispatcher("/PublicationModify.jsp").forward(request, response);
					}
				}
			}
		}
	}

}
