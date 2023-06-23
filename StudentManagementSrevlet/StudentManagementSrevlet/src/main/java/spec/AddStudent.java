package spec;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class AddStudent
 */
public class AddStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spec","root","root");
			Statement stmt = conn.createStatement();
			String rno = request.getParameter("add_rno");
			String name = request.getParameter("add_name");
			String sql = "INSERT INTO student_data (roll_number,name) VALUES (?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, rno);
			ps.setString(2, name);
			int res =ps.executeUpdate();
			String isSP = request.getParameter("special_needs");
			if(isSP != null) {
				String needsWC = request.getParameter("needs_wc") != null ? "Y" : "N";
				String isVI = request.getParameter("is_vi") != null ? "Y" : "N";
				sql = "SELECT * FROM student_data WHERE roll_number = '" + rno + "';";	
				ResultSet rs = stmt.executeQuery(sql);
				rs.next();
				int id = rs.getInt("id");
				sql = "INSERT INTO special_needs (student_id,needs_wc,is_vi) VALUES (?,?,?)";
				ps = conn.prepareStatement(sql);
				ps.setInt(1,id);
				ps.setString(2, needsWC);
				ps.setString(3, isVI);
				res = ps.executeUpdate();
			}
			response.sendRedirect("index.jsp");
		}
		catch(Exception e) {
			response.getWriter().println(e);
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
