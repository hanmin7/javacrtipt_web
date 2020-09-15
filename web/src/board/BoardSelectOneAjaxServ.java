package board;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class BoardSelectOneAjaxServ
 */
@WebServlet("/BoardSelectOneAjaxServ")
public class BoardSelectOneAjaxServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시글번호를 파라미터 받아서 단건조회 결과를 json 변환해서 출력
		String no = request.getParameter("no");
		BoardVo boardVo = new BoardVo();
		boardVo.setNo(no);
		BoardDAO dao = new BoardDAO();
		boardVo = dao.selectOne(boardVo);
		String result = JSONObject.fromObject(boardVo).toString();
		
		response.getWriter().print(result);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
