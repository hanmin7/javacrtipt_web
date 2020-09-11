package board;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;

import common.FileRenamePolicy;


/**
 * Servlet implementation class BoardInsertServ
 */

//파라미터 스트림 값을 바운드리(구분기호)로 잘라서 part배열로 만들어줌.
@MultipartConfig(location = "c:/upload",
				maxRequestSize = 1024*1024*10) // 파일업로드 102*1024 *10메가로 제한
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
		
			
		
		//첨부파일 처리
		Part part = request.getPart("filename");
		//String filename = Long.toString(System.currentTimeMillis());  //getFileName(part);
		String filename = getFileName(part);
		//파일명 중복체크
		String path = request.getServletContext().getRealPath("/images"); //"c:/upload";
		System.out.println(path);
		File renameFile = FileRenamePolicy.rename(new File(path, filename));
		part.write(path +"/" + renameFile.getName());
		boardVo.setFilename(renameFile.getName());
		//boardVo.setFilename(filename);
		
		
		// 2. DB 등록 처리
		BoardDAO dao = new BoardDAO();
		dao.insert(boardVo);
		// 3. 결과 처리 (생략)
		
		
		// 조회 서블릿 페이지로 이동 (send)
		response.sendRedirect("boardSelectAll.do");
	
	}
	
	private String getFileName(Part part) throws UnsupportedEncodingException {
		for (String cd : part.getHeader("Content-Disposition").split(";")) {
			if (cd.trim().startsWith("filename")) {
				return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
			}
		}
		return null;
	}

	

}
