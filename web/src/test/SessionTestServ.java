package test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/session")
public class SessionTestServ extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ShareObject obj1 = new ShareObject();
		obj1.setCount(1);
		obj1.setStr("클라이언트공유");
		
		HttpSession session = req.getSession();
		session.setAttribute("data", obj1);
		
		resp.getWriter().append("session data set");
	}
	
}
