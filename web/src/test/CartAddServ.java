package test;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/test/cartAdd")
public class CartAddServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	int cnt = 0;
	
   
	//상품조회
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("goodsList.jsp").forward(request, response);
		
	}
	
	//장바구니 등록
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		//세션에서 장바구니 목록 조회
		ArrayList<String> cartList = (ArrayList<String>) request.getSession().getAttribute("cartList");
		
		//cartList 가 null이면 String[] 초기화하고 session에 추가
		if(cartList == null) {
			cartList = new ArrayList<String>();
			request.getSession().setAttribute("cartList", cartList);
		}
		
		//선택한상품(파라미터)을 cartList배열에 추가 getParameter
		if(cartList.size() < 10) {
			//cartList = request.getParameter("goods");
			cartList.add(request.getParameter("goods"));
		}
		
		//상품조회 페이지로 이동
		response.sendRedirect("cartAdd");
		
	}


}
