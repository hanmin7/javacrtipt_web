package board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class BoardSelectAllAjaxServ
 */
@WebServlet("/BoardSelectAllAjaxServ")
public class BoardSelectAllAjaxServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//전체조회 ajax 요청 처리 (json)
		BoardDAO dao = new BoardDAO();
		ArrayList<BoardVo> list = dao.selectAll(null);
		String result = JSONArray.fromObject(list).toString();
		response.getWriter().print(result);
		
	}

}
