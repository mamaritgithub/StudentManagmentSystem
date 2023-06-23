package spec;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.*;



/**
 * Servlet implementation class ListStudents
 */
public class ListStudents extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListStudents() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			PrintWriter out = response.getWriter();
			String url = "jdbc:mysql://localhost:3306/spec",
					user = "root", password = "root";
			
			//Add this line for web applications before connecting to database
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM student_data");
			out.println("<table border = 1>");
			out.println("<tr><th>Roll Number </th> <th> Name </th> </tr>");
			while(rs.next()) {
				out.println("<tr>");
				out.println("<td>" + rs.getString("roll_number") + "</td>");
				out.println("<td>" + rs.getString("name") + "</td>");
				out.println("</tr>");
			} 
			out.println("</table>");
			out.println("<br /><br /><a href=\"index.html\"> Go Back </a> ");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response.getWriter().print(e.getMessage());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
