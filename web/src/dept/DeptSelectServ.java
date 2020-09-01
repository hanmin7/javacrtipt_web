package dept;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DeptSelectServ
 * 단건조회 
 */
@WebServlet("/dept/deptSelect") //url
public class DeptSelectServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//부서번호 파라미터로 조회한 결과를 select.jsp로 forward 넘겨줌.
		
		//파라미터를 Vo에 담기
		DeptVo paramVo = new DeptVo();
		int department_id = Integer.parseInt(request.getParameter("department_id"));
		paramVo.setDepartment_id(department_id);
		
		//단건조회
		DeptDAO dao = new DeptDAO();
		DeptVo dept = dao.selectOne(paramVo);
		
		//조회결과 request 저장
		request.setAttribute("dept", dept);
		
		//view 페이지로 이동( forward )
		request.getRequestDispatcher("deptSelect.jsp")
				.forward(request, response);
	}


}
