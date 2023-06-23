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
 * Servlet implementation class EditStudent
 */
public class EditStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String rno=request.getParameter("edit_rno");
		String nm=request.getParameter("edit_name");
		
		try {
			PrintWriter out = response.getWriter();
			String url = "jdbc:mysql://localhost:3306/spec",
					user = "root", password = "root";
			
			//Add this line for web applications before connecting to database
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			Statement stmt = conn.createStatement();
			String sql = "UPDATE student_data SET name = '" + nm + "' WHERE roll_number LIKE '" + rno +  "'";
//			PreparedStatement p = conn.prepareStatement(sql);
//			p.execute();
			int res = stmt.executeUpdate(sql);
			
//			if(rs.next()) {
//				out.print("Name : "+rs.getString("name"));
//				out.print("rno : "+rs.getString("roll_number"));
//			}
//			else {
//				out.print("There is no Roll number");
//			}
			
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
