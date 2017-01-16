package src.com.nds.pkg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmpEditSrvlt
 */
@WebServlet("/EmpEditSrvlt")
public class EmpEditSrvlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpEditSrvlt() {
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
		String e = (String)request.getParameter("edit");
		request.setAttribute("edit", e);
		if(e.equals("Edit User Info"))
			request.getRequestDispatcher("/EmployeeEdit.jsp").forward(request, response);
		else{
			if(e.equals("Edit Address")){
				request.getRequestDispatcher("/EmployeeEditAddress.jsp").forward(request, response);
			}
			else{
				if(e.equals("Edit Contact Info")){
					request.getRequestDispatcher("/EmployeeEditContact.jsp").forward(request, response);
				}
				else{
					if(e.equals("Edit Path")){
						request.getRequestDispatcher("/EmployeeEditPath.jsp").forward(request, response);
					}
				}
			}
		}
	}
}
