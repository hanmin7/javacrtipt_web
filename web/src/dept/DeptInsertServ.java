package dept;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
	
	//get : 부서등록페이지로 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//지역정보
		//LocationDAO daoL = new LocationDAO();
		//ArrayList<HashMap<String, String>> listL = daoL.selectAll();
		ArrayList<HashMap<String, String>> listL = LocationDAO.getInstance().selectAll();
		request.setAttribute("locationList", listL);
		
		//사원(매니저)정보
		EmpDAO dao = new EmpDAO();
		ArrayList<EmpVO> list = (ArrayList<EmpVO>) dao.selectAll();
		request.setAttribute("managerList", list);
		
		request.getRequestDispatcher("deptInsertForm.jsp")
				.forward(request, response);
	}

	//post : 부서등록처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("dept 등록 실행");
		//1. 파라미터를 Vo에 담기
		DeptVo deptVo = new DeptVo();
		deptVo.setDepartment_id( Integer.parseInt(request.getParameter("department_id")));
		deptVo.setDepartment_name(request.getParameter("department_name"));
		deptVo.setLocation_id( Integer.parseInt(request.getParameter("location_id")));
		deptVo.setManager_id( Integer.parseInt(request.getParameter("manager_id")));
		//2. 등록 처리
		DeptDAO dao = new DeptDAO();
		dao.insert(deptVo);
		//3. 결과 처리 (생략)
		
		//전체 조회 서블릿 페이지로 이동 (send)
		response.sendRedirect("deptSelectAll");
		
	}

}
