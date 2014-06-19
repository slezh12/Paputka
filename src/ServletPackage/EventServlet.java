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
import JavaPackage.EventParseInfo;
import JavaPackage.TimeChange;
import JavaPackage.User;

/**
 * Servlet implementation class EventServlet
 */
@WebServlet("/EventServlet")
public class EventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventServlet() {
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
		String fromPlace = request.getParameter("from");
		String toPlace = request.getParameter("to");
		String feeString =(String) request.getParameter("fee");
		double fee = Double.parseDouble(feeString);
		String count =(String) request.getParameter("places");
		int places = Integer.parseInt(count);
		String fromLongitude =(String) request.getParameter("fromLongitude");
		double fLong = Double.parseDouble(fromLongitude);
		String fromLatitude =(String) request.getParameter("fromLatitude");
		double fLat = Double.parseDouble(fromLatitude);
		String toLongitude =(String) request.getParameter("toLongitude");
		double tLong = Double.parseDouble(toLongitude);
		String toLatitude =(String) request.getParameter("toLatitude");
		double tLat = Double.parseDouble(toLatitude);
		String typ = request.getParameter("type");
		boolean type = false;
		if (typ.equals("oneway"))
			type= true;
		ServletContext context = getServletContext();
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		DataSource source = (DataSource) context.getAttribute("connectionPool");
		EventConnection connection = new EventConnection((BasicDataSource) source);
		connection.insertIntoEvents(user.getID(), places, fee, fLong, fLat, tLong, tLat, fromPlace, toPlace, type, true);
		EventParseInfo info = new EventParseInfo((BasicDataSource) source);
		int eventID = info.getLastID(user.getID());
		RequestDispatcher dispatch;
		if (type) {
			String startDate = request.getParameter("date");
			startDate = TimeChange.getCorrectDate(startDate);
			String startTime = request.getParameter("time") + ":00";
			connection.insertIntoDates(eventID, startDate+" "+startTime);
		} else {
			for (int i = 0; i < 7; i++) {
				String isMarked = request.getParameter(""+i);
				if (isMarked!=null && isMarked.equals(""+i)) {
					String startTime = request.getParameter("time"+i) + ":00";
					connection.insertIntoEveryday(eventID, startTime, i);
				}
			}
		}
		connection.CloseConnection();
		dispatch = request.getRequestDispatcher("SuccessfullyAddedEvent.jsp");
		dispatch.forward(request, response);
	}

}
