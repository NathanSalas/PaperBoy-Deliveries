package src.com.nds.pkg;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import src.connections.user;

/**
 * Servlet implementation class EmpEditSubSrvlt
 */
@WebServlet("/EmpEditSubSrvlt")
public class EmpEditSubSrvlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpEditSubSrvlt() {
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
			request.getRequestDispatcher("/EmployeeCenter.jsp").forward(request, response);
		}
		else{
			if(s.equals("Submit Profile Edit")){
				String newUName = ((String)request.getParameter("name")).trim();
				String newPass = (String)request.getParameter("pass");
				String newConfirm = (String)request.getParameter("confirm");
				String newEmail = ((String)request.getParameter("em")).trim();
				if(!newPass.equals(newConfirm)||newPass.length()>16||newPass.length()<8){
					err+="Invalid password.<br>";
					valid=false;
				}	
				if(newUName.contains("\\s")||newUName.length()>16||newUName.length()<3){
					err+="Invalid username.<br>";
					valid=false;
				}
				if(!newEmail.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")){
					err+="E-mail address could not be confirmed.<br>";
					valid=false;
				}
				if(!valid){
					request.setAttribute("errorMsg", err);
					request.getRequestDispatcher("/EmployeeEdit.jsp").forward(request, response);
				}
				else{
					user u = new user();
					HttpSession session = request.getSession();
					u.modUserName(newUName);
					u.modUserPassword(newPass);
					u.modUserEmail(newEmail);
					session.setAttribute("hidPass", u.getHiddenPassword());
					u.closeUser();			
					session.setAttribute("uName", newUName);
					session.setAttribute("pass", newPass);
					session.setAttribute("email", newEmail);
					request.setAttribute("msg", "Successfully modified user profile information.");
					request.getRequestDispatcher("/EmployeeCenter.jsp").forward(request, response);
				}
			}
			else{
				if(s.equals("Submit Address Edit")){
					//TRY TO USE GOOGLE API HERE
					String newComp = ((String)request.getParameter("cName")).trim();
					String newAddr = ((String)request.getParameter("addr")).trim();
					String newCity = ((String)request.getParameter("c")).trim();
					String newState = (String)request.getParameter("s");
					String newZip = ((String)request.getParameter("z")).trim();
					if(newComp.length()<1||newComp.length()>50){
						err+="Invalid company name (50 characters maximum).<br>";
						valid=false;
					}	
					if(!newAddr.matches("\\d+\\s+[a-zA-Z]+(\\s+[a-zA-Z]+)+")){
						err+="Invalid street name.<br>";
						valid=false;
					}
					if(!newCity.matches("^[a-zA-Z ]+$")){
						err+="Invalid city name.<br>";
						valid=false;
					}
					if(!newZip.matches("\\d{5}")){
						err+="Invalid zip code (5 digits).<br>";
						valid=false;
					}
					if(!valid){
						request.setAttribute("errorMsg", err);
						request.getRequestDispatcher("/EmployeeEditAddress.jsp").forward(request, response);
					}
					else{
						user u = new user();
						if(!u.modUserAddress(newAddr, newCity, newState, newZip)){
							u.closeUser();
							request.setAttribute("errorMsg", "Address could not be confirmed. Review and try again.");
							request.getRequestDispatcher("/EmployeeEditAddress.jsp").forward(request, response);
						}
						u.modCompanyName(newComp);
						u.closeUser();
						HttpSession session = request.getSession();
						session.setAttribute("company", newComp);
						session.setAttribute("uAddress", newAddr);
						session.setAttribute("uCity", newCity);
						session.setAttribute("uState", newState);
						session.setAttribute("uZip", newZip);
						request.setAttribute("msg", "Successfully modified company address.");
						request.getRequestDispatcher("/EmployeeCenter.jsp").forward(request, response);
					}
				}
				else{
					if(s.equals("Submit Contact Edit")){
						String newCSE = ((String)request.getParameter("cse")).trim();
						String newCSP = ((String)request.getParameter("csp")).trim();
						if(!newCSE.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")){
							err+="E-mail address could not be confirmed.<br>";
							valid=false;
						}
						newCSP = newCSP.replaceAll("\\D", "");
						if(!newCSP.matches("\\d{10}")){
							err+="Invalid phone number.<br>";
							valid=false;
						}
						if(!valid){
							request.setAttribute("errorMsg", err);
							request.getRequestDispatcher("/EmployeeEditContact.jsp").forward(request, response);
						}
						else{
							user u = new user();
							u.modCSEmail(newCSE);
							u.modCSPhone(newCSP);
							u.closeUser();
							HttpSession session = request.getSession();
							session.setAttribute("csEmail", newCSE);
							session.setAttribute("csPhone", newCSP);
							request.setAttribute("msg", "Successfully modified customer service contact information.");
							request.getRequestDispatcher("/EmployeeCenter.jsp").forward(request, response);
						}
					}
					else{
						if(s.equals("Submit File Path Edit")){
							String newFP = ((String)request.getParameter("fp")).trim();
							user u = new user();
							if(!u.modUserFilePath(newFP)){
								u.closeUser();
								request.setAttribute("errorMsg", "File Path could not be confirmed. Review and try again.");
								request.getRequestDispatcher("/EmployeeEditPath.jsp").forward(request, response);
							}
								u.closeUser();
								HttpSession session = request.getSession();
								session.setAttribute("path", newFP);
								request.setAttribute("msg", "Successfully modified file path.");
								request.getRequestDispatcher("/EmployeeCenter.jsp").forward(request, response);
						}
					}
				}
			}
		}
	}

}
