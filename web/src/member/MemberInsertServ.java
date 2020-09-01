package member;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
 * Servlet implementation class MemberInsertServ
 */
@WebServlet("/member/memberInsert.do")
public class MemberInsertServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

    //등록페이지
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("memberInsert.jsp")
				.forward(request, response);
	}

	//등록 처리
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //한글인코딩 insert 페이지에서 값이랑 (utf-8이면 utf-8) 같은값으로
		// 1. 파라미터를 Vo에 담기
		MemberVo memberVo = new MemberVo();
/*		memberVo.setId(request.getParameter("id"));
		memberVo.setPw(request.getParameter("pw"));
		memberVo.setGender(request.getParameter("gender"));
		memberVo.setJob(request.getParameter("job"));
		memberVo.setReason(request.getParameter("reason"));
		memberVo.setMailyn(request.getParameter("mailYn"));
		//mailyn null값 처리?
*/		
		
		//위에꺼가 불편하니까 apache.org에서 beanutils 참고. 파일을 lib에 넣고 활용.
		try {
			BeanUtils.copyProperties(memberVo, request.getParameterMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if(memberVo.getMailyn()==null) {
			memberVo.setMailyn("N");
		}
		
		System.out.println("=========map=========");
		Map<String, String[]> map = request.getParameterMap();
		System.out.println("id= " + map.get("id")[0]);
		
		System.out.println("=========names=========");
		Enumeration<String> pnames = request.getParameterNames();
		while(pnames.hasMoreElements()) {
			System.out.println(pnames.nextElement());
		}
		
		//chechbox (hobby) 150p
		System.out.println("=========hobby vlues=========");
		String strHobby = "";
		String[] hobby = request.getParameterValues("hobby");
		System.out.println(Arrays.toString(hobby));  //[read, game..] 이렇게 나오게됨.
		if(hobby != null) {
			for(String temp : hobby) {
				strHobby += temp + "/";
			}
		}
		memberVo.setHobby(strHobby);
		
		
		//System.out.println(memberVo);
		
		// 2. DB 등록 처리
		MemberDAO dao = new MemberDAO();
		dao.insert(memberVo);
		// 3. 결과 처리 (생략)
		
		// 조회 서블릿 페이지로 이동 (send)
		response.sendRedirect("memberSelectAll.do");
	}

}
