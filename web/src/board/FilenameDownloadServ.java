package board;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;


/**
 * Servlet implementation class FileDownloadServlet
 */
@WebServlet("/board/filenameDownload.do")
public class FilenameDownloadServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파라미터를 VO에 담기
		String filename = request.getParameter("filename");
		String realPath = request.getServletContext().getRealPath("/images")+ "/" + filename;
		File file = new File(realPath);
		String downName = new String(filename.getBytes("utf-8"), "iso-8859-1");
		
		//파일이 없으면 종료
		if(! file.exists() ) {
			return;
		}
		// 응답 헤더를 다운로드로 설정
		response.reset();
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + downName+"\"");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expires", "-1;");
		
		FileUtils.copyFile(file, response.getOutputStream());
		
		response.getOutputStream().close();
	}
}
