package ServletPackage;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import JavaPackage.BaseConnection;
import JavaPackage.Hash;
import JavaPackage.MyDBInfo;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		String name = request.getParameter("name");
		String password = request.getParameter("pass");
		ServletContext context = getServletContext();
		BaseConnection base = new BaseConnection(context);
		try {
			ResultSet rs = base.getInfoByMail(name);
			RequestDispatcher dispatch;
			if (rs.isBeforeFirst() && rs.getString("Password").equals(password)) {
				password = Hash.calculateHashCode(password);
				HttpSession session = request.getSession(true);
				Integer id = rs.getInt("ID");
				session.setAttribute("id", id);
				dispatch = request.getRequestDispatcher("UserPage.jsp");
			} else {
				dispatch = request.getRequestDispatcher("InvalidLogin.html");
			}
			base.CloseConnection();
			dispatch.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
