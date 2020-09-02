package member;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServ
 */
@WebServlet({"/member/login", "/member/logout"})
public class LoginServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	//logout
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().invalidate();
		response.sendRedirect("../index.jsp");
		
	}

	//login
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 파라미터 VO
		MemberVo memberVo = new MemberVo();
		memberVo.setId(request.getParameter("id"));
		memberVo.setPw(request.getParameter("pw"));
		
		//2. 서비스 처리(DB)
		MemberVo resultVo = MemberDAO.getInstance().selectOne(memberVo);
		
		//3. 결과 저장
		String page = "";
		if(resultVo == null) { //id가 없음
			request.setAttribute("errormsg", "해당 ID가 없습니다.");
			page = "login.jsp";
		} else { //있으면 패스워드 일치 확인
			if(memberVo.getPw().equals(resultVo.getPw())) { //로그인성공
				request.getSession().setAttribute("login", resultVo);
				request.getSession().setAttribute("id", resultVo.getId());
				page = "../index.jsp";
			} else { //패스워드 불일치
				request.setAttribute("errormsg", "패스워드 불일치");
				page = "login.jsp";
			}
		}
		
		//4. 뷰페이지 이동(redirect, forward) 또는 뷰페이지 출력
		request.getRequestDispatcher(page).forward(request, response);
		
	}

}
