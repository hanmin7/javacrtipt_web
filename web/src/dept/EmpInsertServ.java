package dept;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EmpInsertServ
 */
@WebServlet("/dept/empInsert")
public class EmpInsertServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//job정보
		ArrayList<JobVO> jdao =  (ArrayList<JobVO>) JobDAO.getInstance().selectAll();
		request.setAttribute("jobList", jdao);
		
		//매니저정보
		EmpDAO mdao = new EmpDAO();
		ArrayList<EmpVO> mlist = (ArrayList<EmpVO>) mdao.selectAll();
		request.setAttribute("managerList", mlist);
		
		//dept_id 정보
		DeptDAO dao = new DeptDAO();
		ArrayList<DeptVo> dlist = dao.selectAll(null);
		request.setAttribute("list", dlist);
		
	
		request.getRequestDispatcher("employeeInsert.jsp")
				.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("employee 등록 실행");
		EmpVO empVo = new EmpVO();
		empVo.setEmployee_id(request.getParameter("employeeId"));
		empVo.setFirst_name(request.getParameter("firstName"));
		empVo.setLast_name(request.getParameter("lastName"));
		empVo.setEmail(request.getParameter("email"));
		empVo.setHire_date(request.getParameter("hireDate"));
		empVo.setDepartment_id(request.getParameter("dept"));
		empVo.setJob_id(request.getParameter("jobId"));
		empVo.setManager_id(request.getParameter("manager_id"));
		
		//2 등록처리
		EmpDAO dao = new EmpDAO();
		dao.insert(empVo);
		
		response.sendRedirect("empSelectAllServ");
	}

}
