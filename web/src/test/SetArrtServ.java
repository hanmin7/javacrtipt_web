package test;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/SetArrtServ")
public class SetArrtServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ServletContext application = this.getServletContext();
		
		ShareObject obj1 = new ShareObject();
		obj1.setStr("req");
		request.setAttribute("data", obj1);

		ShareObject obj2 = new ShareObject();
		obj2.setStr("ses");
		session.setAttribute("data", obj2);
		
		ShareObject obj3 = new ShareObject();
		obj3.setStr("app");
		application.setAttribute("data", obj3);
		
		request.getRequestDispatcher("/test/getAttr.jsp?name=choi")
				.forward(request, response);
	}

}
