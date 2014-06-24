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

import JavaPackage.WantToGoConnection;

/**
 * Servlet implementation class DeleteWantToGoServlet
 */
@WebServlet("/DeleteWantToGoServlet")
public class DeleteWantToGoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteWantToGoServlet() {
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
		ServletContext context = getServletContext();
		DataSource source = (DataSource) context.getAttribute("connectionPool");
		WantToGoConnection connect = new WantToGoConnection((BasicDataSource) source);
		String id = request.getParameter("wantToGoID");
		String bool = request.getParameter("type");
		int wantToGoID = Integer.parseInt(id);
		boolean type = false;
		if (bool.equals("true"))
			type = true;
		connect.deleteWantToGo(wantToGoID, type);
		connect.CloseConnection();
		RequestDispatcher dispatch = null;
		dispatch = request.getRequestDispatcher("MyWantToGos.jsp");
		dispatch.forward(request, response);
	}

}
