package dept;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.Paging;

/**
 * Servlet implementation class DeptSelectAllServ
 */
@WebServlet("/dept/deptSelectAll")
public class DeptSelectAllServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeptSelectAllServ() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DeptDAO dao = new DeptDAO();
		
		//파라미터받기 (페이지번호)
		String p = request.getParameter("p");
		String department_name = request.getParameter("department_name");
		//유효성 체크
		int page = 1;
		if(p != null) {
			page = Integer.parseInt(p);
		}
		
		Paging paging = new Paging();
		paging.setPageUnit(5);
		paging.setPageSize(3);
		paging.setPage(page);
		//paging.setTotalRecord(dao.count());
		
		//vo에 담기
		/*int page_unit = 5;
		int first = (page-1) * page_unit +1;
		int last = first + page_unit -1;
		DeptVo dept = new DeptVo();
		dept.setFirst(first);
		dept.setLast(last); */
		
		//vo에 담기
		DeptVo dept = new DeptVo();
		dept.setDepartment_name(department_name);
		
		paging.setTotalRecord(dao.count(dept));
		dept.setFirst(paging.getFirst());
		dept.setLast(paging.getLast());
		
		//전체조회
		System.out.println("dept 전체조회 실행");
		ArrayList<DeptVo> list = dao.selectAll(dept);
		request.setAttribute("list", list);
		request.setAttribute("paging", paging); //페이지번호도 넘김.
		request.getRequestDispatcher("deptSelectAll.jsp") //조회페이지로 넘김
				.forward(request, response);
		//request정보 가져와야함 (forward)
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
