package ServletPackage;

import java.awt.Event;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import JavaPackage.EventConnection;
import JavaPackage.EventParseInfo;
import JavaPackage.UserConnection;

/**
 * Servlet implementation class OtherRequestsServlet
 */
@WebServlet("/OtherRequestsServlet")
public class OtherRequestsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OtherRequestsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String accept = request.getParameter("acc");
		String requestID = request.getParameter("request");
		String event = request.getParameter("event");
		String fromUser = request.getParameter("fromUserID");
		int ID = Integer.parseInt(requestID);
		int eventID = Integer.parseInt(event);
		int fromUserID = Integer.parseInt(fromUser);
		RequestDispatcher dispatch = null;
		if (accept == null) {
			PrintWriter out = response.getWriter();  
			out.println("<script type=\"text/javascript\">");  
			out.println("alert('Please check one of the fields');");
			out.println("window.location='OthersRequests.jsp'"); 
			out.println("</script>"); 
		} else {
			ServletContext context = getServletContext();
			DataSource source = (DataSource) context
					.getAttribute("connectionPool");
			EventParseInfo parse = new EventParseInfo((BasicDataSource) source);
			JavaPackage.Event currentEvent = parse.getEventByID(eventID);
			int acceptance = 0;
			boolean updateRequest = false;
			if (accept.equals("yes")) {
				EventConnection eventConnect = new EventConnection(
						(BasicDataSource) source);
				int check = currentEvent.getPlaces()
						- eventConnect.getParticipantsByEventID(eventID);
				if (check > 0) {
					eventConnect.insertIntoParticipants(eventID, fromUserID);
					acceptance = 1;
					updateRequest = true;
					dispatch = request
							.getRequestDispatcher("OthersRequests.jsp");
					eventConnect.CloseConnection();
				} else {
					acceptance = 0;
					eventConnect.CloseConnection();
					PrintWriter out = response.getWriter();  
					out.println("<script type=\"text/javascript\">");  
					out.println("alert('No more free places');");
					out.println("window.location='OthersRequests.jsp'"); 
					out.println("</script>"); 
				}
			} else if (accept.equals("no")) {
				updateRequest = true;
				acceptance = 2;
			}
			if (updateRequest) {
				UserConnection connect = new UserConnection(
						(BasicDataSource) source);
				connect.updateRequests(ID, acceptance);
				connect.CloseConnection();
				dispatch = request.getRequestDispatcher("OthersRequests.jsp");
				dispatch.forward(request, response);
			}
		}
	}
}
