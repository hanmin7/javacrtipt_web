package test;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletContextTestServ
 */
@WebServlet("/ServletContextTestServ")
public class ServletContextTestServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = this.getServletContext();
		
		//
		response.setContentType("text/html; charset=UTF-8");
		response.getWriter()
				.append("서블릿 버전: " + application.getMajorVersion() + "." + application.getMinorVersion())
				.append("<br>서버정보: " + application.getServerInfo())
				.append("<br>컨텍스트(어플리케이션) 경로: " + application.getContextPath())
				.append("<br>실제 경로: " + application.getRealPath("/member/memberInsert.jsp"));
				//이클립스가 가상의 톰캣 서버 만들어서 ...     리얼패스는 나중에 파일업로드시....
				//realpath => D:\Dev\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0
				//
	}

}
