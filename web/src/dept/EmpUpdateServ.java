package dept;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

/**
 * Servlet implementation class EmpUpdateServ
 */
@WebServlet("/dept/empUpdate")
public class EmpUpdateServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	//수정페이지로 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//사번으로 단건조회
		//String e = request.getParameter("employee_id");
		//System.out.println(e);
		EmpVO empVo = new EmpVO();
		empVo.setEmployee_id(request.getParameter("employee_id"));
		empVo = EmpDAO.getInstance().selectOne(empVo);
		request.setAttribute("empVo", empVo);  //(넘길변수이름, 실제값이저장된변수)
		
		//job정보
		ArrayList<JobVO> jdao = (ArrayList<JobVO>) JobDAO.getInstance().selectAll();
		request.setAttribute("jobList", jdao);

		// 매니저정보 (사원리스트)
		EmpDAO mdao = new EmpDAO();
		ArrayList<EmpVO> mlist = (ArrayList<EmpVO>) mdao.selectAll();
		request.setAttribute("managerList", mlist);
		
		//부서리스트
		DeptDAO dao = new DeptDAO();
		ArrayList<DeptVo> dlist = dao.selectAll(null);
		request.setAttribute("list", dlist);
		//request에 저장
		//request.setAttribute("emp", o);
		
		
		//수정페이지로 이동  request값을 다음에 또 사용할때
		request.getRequestDispatcher("empUpdate.jsp").forward(request, response);
		
	}

	//수정처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		EmpVO empVo = new EmpVO();
		
		try {
			BeanUtils.copyProperties(empVo, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		EmpDAO.getInstance().update(empVo);
		
		//send  단순 페이지 이동
		response.sendRedirect("empSelectAllServ");
		
		
	}

}
