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

import JavaPackage.TimeChange;
import JavaPackage.User;
import JavaPackage.WantToGoConnection;
import JavaPackage.WantToGoParseInfo;

/**
 * Servlet implementation class WantToGoServlet
 */
@WebServlet("/WantToGoServlet")
public class WantToGoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WantToGoServlet() {
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
		String title = request.getParameter("title");
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
		WantToGoConnection connection = new WantToGoConnection((BasicDataSource) source);
		connection.insertIntoWantToGo(user.getID(), title, fLong, fLat, tLong, tLat, type);
		WantToGoParseInfo info = new WantToGoParseInfo((BasicDataSource) source);
		int wantToGoID = info.getLastID(user.getID());
		RequestDispatcher dispatch;
		if (type) {
			String startDate = request.getParameter("datetimeStart");
			startDate = TimeChange.getCorrectDate(startDate);
			String endDate = request.getParameter("datetimeFinish");
			endDate = TimeChange.getCorrectDate(endDate);
			String startTime = request.getParameter("startTime") + ":00";
			String endTime = request.getParameter("endTime") + ":00";
			connection.insertIntoWantToGoDates(wantToGoID, startDate+" "+startTime, endDate+" "+endTime);
		} else {
			for (int i = 0; i < 6; i++) {
				String isMarked = request.getParameter("0");
				if (isMarked.equals("0")) {
					String startTime = request.getParameter("start"+i) + ":00";
					String endTime = request.getParameter("end"+i) + ":00";
					connection.insertIntoWantToGoEveryday(wantToGoID, startTime, endTime, i);
				}
			}
		}
		connection.CloseConnection();
		dispatch = request.getRequestDispatcher("SuccessfullyAddedWantToGo.jsp");
		dispatch.forward(request, response);
	}
}
