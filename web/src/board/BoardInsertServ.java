package board;

import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;


/**
 * Servlet implementation class BoardInsertServ
 */
@WebServlet("/board/boardInsert.do")
public class BoardInsertServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("boardInsert.jsp")
		.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //한글인코딩 insert 페이지에서 값이랑 (utf-8이면 utf-8) 같은값으로
		// 1. 파라미터를 Vo에 담기
		BoardVo boardVo = new BoardVo();
		
		//위에꺼가 불편하니까 apache.org에서 beanutils 참고. 파일을 lib에 넣고 활용.
		try {
			BeanUtils.copyProperties(boardVo, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
			
		
		// 2. DB 등록 처리
		BoardDAO dao = new BoardDAO();
		dao.insert(boardVo);
		// 3. 결과 처리 (생략)
		
		// 조회 서블릿 페이지로 이동 (send)
		response.sendRedirect("boardSelectAll.do");
	
	}

}
