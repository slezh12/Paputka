package ServletPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import JavaPackage.User;
import JavaPackage.UserConnection;
import JavaPackage.UserParseInfo;

/**
 * Servlet implementation class RatingServlet
 */
@WebServlet("/RatingServlet")
public class RatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RatingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stars = request.getParameter("star");
		int rating = 0;
		if (stars != null)
			rating = Integer.parseInt(stars);
		ServletContext context = getServletContext();
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		DataSource source = (DataSource) context.getAttribute("connectionPool");
		UserParseInfo parse = new UserParseInfo((BasicDataSource) source);
		int userID = Integer.parseInt(request.getParameter("userID"));
		Integer id = parse.getRatingID(user.getID(), userID);
		UserConnection connect = new UserConnection((BasicDataSource) source);
		if (id == null) {			
			connect.insertIntoRatings(user.getID(), userID, rating);
		} else {
			connect.updateRatings(id, rating);
		}
		connect.CloseConnection();
		PrintWriter out = response.getWriter();  
		out.println("<script type=\"text/javascript\">");  
		out.println("alert('User has been successfully rated!');");
		out.println("window.location='SuccessfullyRated.jsp?id=" + userID +"'"); 
		out.println("</script>"); 
	}

}
