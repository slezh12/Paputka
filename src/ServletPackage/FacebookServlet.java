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

import JavaPackage.TimeChange;
import JavaPackage.User;
import JavaPackage.UserConnection;
import JavaPackage.UserParseInfo;

/**
 * Servlet implementation class FacebookServlet
 */
@WebServlet("/FacebookServlet")
public class FacebookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FacebookServlet() {
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
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String mail = request.getParameter("mail");
		String date1 = request.getParameter("datetime");
		String date;
		if(date1.substring(2, 3).equals("-")){
			date = TimeChange.getCorrectDate(date1);
		}else{
			date = TimeChange.getCorrectDateForFacebook(date1);

		}
		String radio = request.getParameter("gender");
		boolean gender = false;
		if (radio.equals("Male")) {
			gender = true;
		}
		String password = request.getParameter("password");
		ServletContext context = getServletContext();
		DataSource source = (DataSource) context.getAttribute("connectionPool");
		UserParseInfo info = new UserParseInfo((BasicDataSource) source);
		RequestDispatcher dispatch;
		if (mail == null || mail.equals("undefined")) {
			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Your Facebook Mail is not confirmed. Please first confirm your Facebook mail and than register!');");
			out.println("window.location='index.jsp'");
			out.println("</script>");
		} else {
			if (!info.isUserAlreadyInBase(mail)) {
				HttpSession session = request.getSession(true);
				UserConnection connect = new UserConnection(
						(BasicDataSource) source);
				connect.insertIntoUsers(firstname, lastname, mail, password,
						gender, date);
				User currentUser = info.getUserFBGoogle(mail, password);
				session.setAttribute("user", currentUser);
				connect.CloseConnection();
				dispatch = request.getRequestDispatcher("UserPage.jsp");
				dispatch.forward(request, response);
			} else {
				User currentUser = info.getUserFBGoogle(mail, password);
				if (currentUser == null) {
					PrintWriter out = response.getWriter();
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Your Facebook Mail is in Used .Please Try Again');");
					out.println("window.location='index.jsp'");
					out.println("</script>");
				} else {
					HttpSession session = request.getSession(true);
					session.setAttribute("user", currentUser);
					dispatch = request.getRequestDispatcher("UserPage.jsp");
					dispatch.forward(request, response);
				}
			}
		}
	}
}
