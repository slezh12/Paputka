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

import JavaPackage.EventConnection;
import JavaPackage.User;

/**
 * Servlet implementation class RequestServlet
 */
@WebServlet("/RequestServlet")
public class RequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestServlet() {
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
		String text = request.getParameter("requesttext");
		
		ServletContext context = getServletContext();
		DataSource source = (DataSource) context.getAttribute("connectionPool");
		String eventID = request.getParameter("EventID");
		int EventID = Integer.parseInt(eventID);
	    HttpSession session = request.getSession(true);
		User current = (User) session.getAttribute("user");
		int id = current.getID();
		EventConnection ev = new EventConnection((BasicDataSource) source);
		ev.InsertIntoRequsets(id, EventID, text);
		ev.CloseConnection();
		RequestDispatcher dispatch = request.getRequestDispatcher("Event.jsp?id="+ EventID);
		dispatch.forward(request, response);
	}

}
