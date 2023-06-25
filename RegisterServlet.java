

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    final String query = "INSERT INTO BOOKDATA(BOOKNAME,EDITION,PRICE) VALUES(?,?,?)";

		  PrintWriter pw = response.getWriter();
	        //set content type
	        response.setContentType("text/html");
	        //GET THE book info
	        String bookName = request.getParameter("bookName");
	        String bookEdition = request.getParameter("bookEdition");
	        float bookPrice = Float.parseFloat(request.getParameter("bookPrice"));
	        //LOAD jdbc driver
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException cnf) {
	            cnf.printStackTrace();
	        }
	        //generate the connection
	        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","Selva@1234"); PreparedStatement ps = con.prepareStatement(query);) {
	        	ps.setString(1, bookName);
	            ps.setString(2, bookEdition);
	            ps.setFloat(3, bookPrice);
	            int count = ps.executeUpdate();
	            if (count == 1) {
	                pw.println("<h2>Record Is Registered Sucessfully</h2>");
	            } else {
	                pw.println("<h2>Record not Registered Sucessfully");
	            }
	        } catch (SQLException se) {
	            se.printStackTrace();
	            pw.println("<h1>" + se.getMessage() + "</h2>");
	        } catch (Exception e) {
	            e.printStackTrace();
	            pw.println("<h1>" + e.getMessage() + "</h2>");
	        }
	        pw.println("<a href='Index.html'>Home</a>");
	        pw.println("<br>");
	        pw.println("<a href='BookListServlet'>Book List</a>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
