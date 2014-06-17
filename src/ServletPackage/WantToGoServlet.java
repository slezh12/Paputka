package ServletPackage;

import java.io.IOException;

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
import JavaPackage.UserParseInfo;
import JavaPackage.WantToGoConnection;

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
		if (type) {
			
		} else {
			for (int i = 0; i < 6; i++) {
				
			}
		}
		connection.CloseConnection();
	}

}
