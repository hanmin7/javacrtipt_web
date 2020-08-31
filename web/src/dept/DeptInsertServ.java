package dept;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class DeptInsertServ
 */
@WebServlet("/dept/deptInsert") //deptInsertForm에서 action명 일치해야함
public class DeptInsertServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DeptDAO dao = new DeptDAO();
		
		DeptVo deptVo = new DeptVo();
		deptVo.setDepartment_id( Integer.parseInt(request.getParameter("department_id")));
		deptVo.setDepartment_name(request.getParameter("department_name"));
		dao.insert(deptVo);
		
		//전체 조회 서블릿 페이지로 이동 (send)
		response.sendRedirect("deptSelectAll");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
