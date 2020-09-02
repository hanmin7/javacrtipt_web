package member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;


@WebServlet("/member/memberUpdate")
public class MemberUpdateServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//수정페이지로 이동
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("memberUpdate.jsp").forward(request, response);
		
	}

	//수정 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //한글인코딩 insert 페이지에서 값이랑 (utf-8이면 utf-8) 같은값으로
		// 1. 파라미터를 Vo에 담기
		MemberVo memberVo = new MemberVo();
		
		try {
			BeanUtils.copyProperties(memberVo, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		// 2. DB 등록 처리
		MemberDAO dao = new MemberDAO();
		dao.update(memberVo);
		// 3. 결과 처리 (생략)
		
		// 조회 서블릿 페이지로 이동 (send)
		response.sendRedirect("memberSelectAll.do");
	}

}
