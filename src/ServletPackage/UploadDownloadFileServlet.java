package ServletPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;

import JavaPackage.ParseInfo;
import JavaPackage.User;
import JavaPackage.UserConnection;
import JavaPackage.UserParseInfo;

@WebServlet("/UploadDownloadFileServlet")
public class UploadDownloadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ServletFileUpload uploader = null;
	@Override
	public void init() throws ServletException{
		DiskFileItemFactory fileFactory = new DiskFileItemFactory();
		File filesDir = (File) getServletContext().getAttribute("FILES_DIR_FILE");
		fileFactory.setRepository(filesDir);
		this.uploader = new ServletFileUpload(fileFactory);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fileName = request.getParameter("fileName");
		  //your image servlet code here
		
        response.setContentType("image/jpeg");

        // Set content size
        File file = new File("C:\\img\\null\\"+fileName);
        response.setContentLength((int)file.length());

        // Open the file and output streams
        FileInputStream in = new FileInputStream(file);
        OutputStream out = response.getOutputStream();

        // Copy the contents of the file to the output stream
        byte[] buf = new byte[1024];
        int count = 0;
        while ((count = in.read(buf)) >= 0) {
            out.write(buf, 0, count);
        }
        in.close();
        out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(!ServletFileUpload.isMultipartContent(request)){
			throw new ServletException("Content type is not multipart/form-data");
		}
		try {
			List<FileItem> fileItemsList = uploader.parseRequest(request);
			Iterator<FileItem> fileItemsIterator = fileItemsList.iterator();
			while(fileItemsIterator.hasNext()){
				FileItem fileItem = fileItemsIterator.next();
				System.out.println("FieldName="+fileItem.getFieldName());
				System.out.println("FileName="+fileItem.getName());
				System.out.println("ContentType="+fileItem.getContentType());
				System.out.println("Size in bytes="+fileItem.getSize());
				
				File file = new File(request.getServletContext().getAttribute("FILES_DIR")+File.separator+fileItem.getName());
				System.out.println("Absolute Path at server="+file.getAbsolutePath());
				fileItem.write(file);
				ServletContext context = getServletContext();
				DataSource source = (DataSource) context.getAttribute("connectionPool");
				HttpSession session = request.getSession(true);
				User current = (User) session.getAttribute("user");
				int id = current.getID();
				RequestDispatcher dispatch = null;
				UserParseInfo userParse = new UserParseInfo((BasicDataSource) source);
				PrintWriter out = response.getWriter();
				if(userParse.selectFromAvatars(id).equals("")){
					userParse.insertIntoAvatars(file.getName(), id);
					  
					out.println("<script type=\"text/javascript\">");  
					out.println("alert('Avatar has been successfully added');");
					out.println("window.location='ChangePrivateInfo.jsp'"); 
					out.println("</script>");
				}else{
					userParse.updateAvatars(file.getName(), id);
					
					out.println("<script type=\"text/javascript\">");  
					out.println("alert('Avatar has been successfully changed');");
					out.println("window.location='ChangePrivateInfo.jsp'"); 
					out.println("</script>");
				} 
			}
		} catch (FileUploadException e) {
		} catch (Exception e) {
		}
	}

}
