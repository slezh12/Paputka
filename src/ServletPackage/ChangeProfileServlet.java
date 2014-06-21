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

import JavaPackage.ParseInfo;
import JavaPackage.User;
import JavaPackage.UserParseInfo;

/**
 * Servlet implementation class ChangeProfileServlet
 */
@WebServlet("/ChangeProfileServlet")
public class ChangeProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeProfileServlet() {
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
		String about = request.getParameter("about");
		String tel = request.getParameter("tel");
		ServletContext context = getServletContext();
		DataSource source = (DataSource) context.getAttribute("connectionPool");
		HttpSession session = request.getSession(true);
		User current = (User) session.getAttribute("user");
		int id = current.getID();
		ParseInfo parse = new ParseInfo((BasicDataSource) source);
		UserParseInfo userParse = new UserParseInfo((BasicDataSource) source);
		RequestDispatcher dispatch = null;
		if (about.length() == 0 && tel.length() == 0) {
			PrintWriter out = response.getWriter();  
			out.println("<script type=\"text/javascript\">");  
			out.println("alert('Please fill one of the field or go to HomePage');");
			out.println("window.location='ChangePrivateInfo.jsp'"); 
			out.println("</script>"); 
		} else {
			if (about.length() != 0) {
				if (parse.getInfoAboutStatuses(id).length() == 0) {
					userParse.insertIntoStatuses(about, id);
				} else {
					userParse.updateStatuses(about, id);
				}
				dispatch = request.getRequestDispatcher("UserPage.jsp");
				dispatch.forward(request, response);
			}
			if (tel.length() != 0) {
				if (parse.getInfoAboutTel(id).length() == 0) {
					userParse.insertIntoTel(tel, id);
				} else {
					userParse.updateTel(tel, id);
				}
				dispatch = request.getRequestDispatcher("UserPage.jsp");
				dispatch.forward(request, response);
			}
		}
	}
}
