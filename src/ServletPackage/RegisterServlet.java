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

import JavaPackage.Hash;
import JavaPackage.TimeChange;
import JavaPackage.User;
import JavaPackage.UserConnection;
import JavaPackage.UserParseInfo;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
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
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String mail = request.getParameter("mail");
		String date1 = request.getParameter("datetime");
		String date = TimeChange.getCorrectDate(date1);
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
		if (!info.isUserAlreadyInBase(mail)) {
			HttpSession session = request.getSession(true);
			UserConnection connect = new UserConnection(
					(BasicDataSource) source);
			String hashPass = Hash.calculateHashCode(password);
			connect.insertIntoUsers(firstname, lastname, mail, hashPass,
					gender, date);
			User currentUser = info.getUser(mail, password);
			session.setAttribute("user", currentUser);
			connect.CloseConnection();
			dispatch = request.getRequestDispatcher("UserPage.jsp");
		} else {
			dispatch = request.getRequestDispatcher("InvalidRegistration.jsp");
		}
		dispatch.forward(request, response);
	}

}
