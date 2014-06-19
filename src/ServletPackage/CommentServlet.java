package ServletPackage;

import java.io.IOException;

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

import JavaPackage.EventParseInfo;
import JavaPackage.User;

/**
 * Servlet implementation class CommentServlet
 */
@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentServlet() {
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
		String text = request.getParameter("comment");
		ServletContext context = getServletContext();
		DataSource source = (DataSource) context.getAttribute("connectionPool");
		String eventID = request.getParameter("eventID");
		System.out.println(eventID);
	    int EventID = Integer.parseInt(eventID);
	    HttpSession session = request.getSession(true);
		User current = (User) session.getAttribute("user");
		int id = current.getID();
		EventParseInfo info = new EventParseInfo((BasicDataSource) source);
		if(text.length() > 0) info.InsertIntoComments(EventID, id, text);
		RequestDispatcher dispatch = null;
		dispatch = request.getRequestDispatcher("Event.jsp?id="+ eventID);
		dispatch.forward(request, response);
	}

}
