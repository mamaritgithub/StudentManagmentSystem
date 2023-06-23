package spec;

import jakarta.servlet.ServletException;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.sql.*;

/**
 * Servlet implementation class FindStudent
 */
public class FindStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String rno=request.getParameter("find_rno");
		System.out.println(rno);
		try {
			PrintWriter out = response.getWriter();
			String url = "jdbc:mysql://localhost:3306/spec",
					user = "root", password = "root";
			
			//Add this line for web applications before connecting to database
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM student_data WHERE roll_number = '" + rno + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				out.print("Name : "+rs.getString("name"));
			}
			else {
				out.print("There is no Roll number");
			}
			
		}
		catch (Exception e) {
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
