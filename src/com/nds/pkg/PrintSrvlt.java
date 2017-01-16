package src.com.nds.pkg;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import src.connections.print;

/**
 * Servlet implementation class RoutPrintSrvlt
 */
@WebServlet(name = "PrintSrvlt", urlPatterns = { "/PrintSrvlt" })
public class PrintSrvlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrintSrvlt() {
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
		String s = request.getParameter("sub");
		if(s.equals("Delivery Report")){
			int num = Integer.parseInt(request.getParameter("numDriver"));
			HttpSession session = request.getSession();
			int ctot = (int)session.getAttribute("cLength");
			if(num>ctot||num<1){
				request.setAttribute("errorMsg", "Number of drivers must be betwee 1 and " + ctot + ".");
				request.getRequestDispatcher("/PrintDelivery.jsp").forward(request, response);
			}
			else{			
//			String result = "[", result1 ="[";
//			if(num==1){
//				String [] c = print.getDeliveryCustomers();
//				for(int i = 0; i < c.length; i+=2) {
//					result += "\"" + c[i] + "\"";
//					if(i < c.length - 1) {
//						result += ", ";
//					}
//				}
//				result += "]";
//				for(int i = 1; i < c.length; i+=2) {
//					result1 += "\"" + c[i] + "\"";
//					if(i < c.length - 1) {
//						result1 += ", ";
//					}
//				}
//			}
//			result1 += "]";
			String dButton="<form action='PrintDeliverySrvlt' method='post'>";
		    for (int j = 0 ; j < num ; j++) {
		      dButton += "<input type='submit' value='Print Route "+ (j+1) +"' name='butt'><input type ='hidden' value = "+ (j+1) +" name ='add'><br><br>";
		    }
		    int stopsPer = (ctot/num)+1;
		    request.setAttribute("noo", stopsPer);
		    String [] c = print.getDeliveryCustomers();
		    String dStops[] = new String[num*2];
		    int startIndex=0;
		    for(int j=0; j<(num*2);j+=2){
		    	String result = "[", result1 ="[";
		    	for(int i=startIndex;i<(c.length-1);i+=2){
		    		if(i==(startIndex+(stopsPer*2))){
		    			startIndex = i+1;
		    			break;
		    		}
		    		result += "\"" + c[i] + "\"";
		    		if(i < (startIndex+(stopsPer*2)) - 2){
						result += ", ";
					}
		    		result1+= "\"" + c[i+1] + "\"";
		    		if(i < (startIndex+(stopsPer*2)) - 2){
						result1 += ", ";
					}
		    	}
		    	
		    	result+="]";
		    	result1+="]";
		    	dStops[j]=result;
		    	dStops[j+1]=result1;
		    }
		    for(int i=0;i<(dStops.length-1);i+=2){
		    	session.setAttribute("d"+(i+1), dStops[i]);
		    	session.setAttribute("dInfo"+(i+1), dStops[i+1]);
		    }
		    dButton+="</form>";
		    request.setAttribute("butt", dButton);
			session.setAttribute("numD", num);
//			request.setAttribute("cToday", result);
//			request.setAttribute("cInfo", result1);
			request.getRequestDispatcher("/PrintDelivery.jsp").forward(request, response);
			}
		}
		else{
			if(s.equals("Delivery")){
				String [] c = print.getDeliveryCustomers();
				HttpSession session = request.getSession();
	            session.setAttribute("cLength", c.length/2);
				request.getRequestDispatcher("/PrintDelivery.jsp").forward(request, response);
			}
			else{
				if(s.equals("Bills")){
					print p = new print();
					p.printAllBills();
					
					request.getRequestDispatcher("/NewFile.jsp").forward(request, response);
				}
			}
		}
	}
}
