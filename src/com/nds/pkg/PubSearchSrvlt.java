package src.com.nds.pkg;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import src.connections.publication;

/**
 * Servlet implementation class PubSearchSrvlt
 */
@WebServlet("/PubSearchSrvlt")
public class PubSearchSrvlt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PubSearchSrvlt() {
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
				request.getRequestDispatcher("/PublicationLookup.jsp").forward(request, response);
			}
			String[] names = n.split("\\s+");
			if(Array.getLength(names)>2){
				request.setAttribute("names", n);
				request.setAttribute("errorMsg", "Please enter two name entries maximum.");
				request.getRequestDispatcher("/PublicationLookup.jsp").forward(request, response);
			}
			ArrayList<String> results;
			if(Array.getLength(names) == 1){
				results = publication.getSearchPublications(names[0], "");
			}else{
				String name = names[0] + " " + names[1];
				results = publication.getSearchPublications(name, "");
				
			}
			String r="";
			if(results != null){
				for(int i=0;i<results.size();i++){
					String[] id = results.get(i).substring(16, 18).split("[^0-9]");
					String[] rest = results.get(i).split("<br>");
					
					r+=results.get(i)
							+"<form action=\"PubEditSrvlt\" method=\"post\"> "
							+ " <input type=\"hidden\" name=\"pid\" value=\""+ id[0] + "\">"
							+ " <input type=\"hidden\" name=\"PublicationName\" value=\"" + rest[1] + "\">"
							+ " <input type=\"hidden\" name=\"genre\" value=\"" + rest[2] + "\">"
							+ " <input type=\"hidden\" name=\"Price\" value=\"" + rest[3] + "\">"
							+ " <input type=\"hidden\" name=\"Frequency\" value=\"" + rest[4] + "\">"
							+ " <input type=\"hidden\" name=\"Status\" value=\"" + rest[5] + "\">"
							+ " <input type=\"hidden\" name=\"DeliveryDays\" value=\"" + rest[6] + "\">"
							+ "<input type=\"submit\" value=\"Select Publication\" name = \"subm\"></form><br><br><br>";
				}
			}
			request.setAttribute("results", r);
			request.getRequestDispatcher("/PublicationLookup.jsp").forward(request, response);
		}
		else if(s.equals("Submit Genre")){
			String g  = ((String)request.getParameter("g")).trim();
			if(!g.matches("^[a-zA-Z ]+$")){
				request.setAttribute("genre", g);
				request.setAttribute("errorMsg", "Please enter genres only.");
				request.getRequestDispatcher("/PublicationLookup.jsp").forward(request, response);
			}
			String[] genres = g.split("\\s+");
			if(Array.getLength(genres)>2){
				request.setAttribute("genres", g);
				request.setAttribute("errorMsg", "Please enter two genres entries maximum.");
				request.getRequestDispatcher("/PublicationLookup.jsp").forward(request, response);
			}
			ArrayList<String> results;
			if(Array.getLength(genres)==1){
				results = publication.getSearchPublications("", genres[0]);
			}else{
				results = publication.getSearchPublications("", genres[0]);
			}
			String r="";
			if(results != null){
				for(int i=0;i<results.size();i++){
					String[] id = results.get(i).substring(16, 18).split("[^0-9]");
					String[] rest = results.get(i).split("<br>");
					
					
					r+=results.get(i)
							+"<form action=\"PubEditSrvlt\" method=\"post\"> "
							+ " <input type=\"hidden\" name=\"pid\" value=\""+ id[0] + "\">"
							+ " <input type=\"hidden\" name=\"PublicationName\" value=\"" + rest[1] + "\">"
							+ " <input type=\"hidden\" name=\"genre\" value=\"" + rest[2] + "\">"
							+ " <input type=\"hidden\" name=\"Price\" value=\"" + rest[3] + "\">"
							+ " <input type=\"hidden\" name=\"Frequency\" value=\"" + rest[4] + "\">"
							+ " <input type=\"hidden\" name=\"Status\" value=\"" + rest[5] + "\">"
							+ " <input type=\"hidden\" name=\"DeliveryDays\" value=\"" + rest[6] + "\">"
							+ "<input type=\"submit\" value=\"Select Publication\" name = \"subm\"></form><br><br><br>";
					
				}
			}
			
			request.setAttribute("results", r);
			request.getRequestDispatcher("/PublicationLookup.jsp").forward(request, response);

		}
	}
}


