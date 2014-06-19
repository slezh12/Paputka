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
 * Servlet implementation class GoogleServlet
 */
@WebServlet("/GoogleServlet")
public class GoogleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GoogleServlet() {
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
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String mail = request.getParameter("mail");
		String date = request.getParameter("datetime");
		if(date.substring(2, 3).equals("-")){
			date = TimeChange.getCorrectDate(date);
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
			if(currentUser==null){
				PrintWriter out = response.getWriter();  
				out.println("<script type=\"text/javascript\">");  
				out.println("alert('Your Google+ Mail is in Used .Please Try Again');");
				out.println("window.location='index.jsp'"); 
				out.println("</script>"); 
			}else{
				HttpSession session = request.getSession(true);
				session.setAttribute("user", currentUser);
				dispatch = request.getRequestDispatcher("UserPage.jsp");
				dispatch.forward(request, response);
			}
		}	
	}
}