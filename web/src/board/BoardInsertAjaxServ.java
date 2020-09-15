package board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class BoardInsertAjaxServ
 */
@WebServlet("/BoardInsertAjaxServ")
public class BoardInsertAjaxServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터 받아서 vo
		BoardVo boardVo = new BoardVo();
		String poster = request.getParameter("poster");
		String subject = request.getParameter("subject");
		String contents = request.getParameter("contents");
		boardVo.setPoster(poster);
		boardVo.setSubject(subject);
		boardVo.setContents(contents);
		BoardDAO dao = new BoardDAO();
		dao.insert(boardVo);
		
		//vo를 json str 변환해서 출력
		String result = JSONObject.fromObject(boardVo).toString();
		response.getWriter().print(result);
		
		
	}

}
