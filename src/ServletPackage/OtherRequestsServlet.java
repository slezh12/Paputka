package ServletPackage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

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
		String accept = request.getParameter("acc");
		String requestID = request.getParameter("request");
		int ID = Integer.parseInt(requestID);
		RequestDispatcher dispatch;
		if (accept == null) {
			dispatch = request.getRequestDispatcher("InvalidOtherRequests.jsp");
		} else {
			int acceptance = 0;
			if (accept.equals("yes")) {
				acceptance = 1;
			} else if (accept.equals("no")) {
				acceptance = 2;
			}
			ServletContext context = getServletContext();
			DataSource source = (DataSource) context
					.getAttribute("connectionPool");
			UserConnection connect = new UserConnection(
					(BasicDataSource) source);
			connect.updateRequestss(ID, acceptance);
			connect.CloseConnection();
			dispatch = request.getRequestDispatcher("OthersRequests.jsp");
		}
		dispatch.forward(request, response);
	}

}
