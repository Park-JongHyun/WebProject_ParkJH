package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import membership.MemberDAO;


public class RegistServlet extends HttpServlet{
	private static final long serialVersion = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String userId= req.getParameter("user_id");
		String userPw= req.getParameter("user_pw");
		String userPw2= req.getParameter("user_pw2");
		String userName=req.getParameter("user_name");
		String userEmail = req.getParameter("user_email");
		String userPhone = req.getParameter("user_phone");
		
		if(!userPw.equals(userPw2)) {
			req.setAttribute("error", "비밀번호가 일치하지 않습니다.");
			req.getRequestDispatcher("/Account/NewAccount.jsp").forward(req, resp);
			return;
		}
		
		
		MemberDAO dao = new MemberDAO(
			getServletContext().getInitParameter("OracleDriver"),
			getServletContext().getInitParameter("OracleURL"),
			getServletContext().getInitParameter("OracleId"),
			getServletContext().getInitParameter("OraclePwd")
			);
		
		
		if(dao.isIdExists(userId)) {
			req.setAttribute("error", "아이디가 일치하지 않습니다");
			req.setAttribute("user_id", userId);
			req.setAttribute("user_name", userName);
			req.setAttribute("user_email", userEmail);
			req.setAttribute("user_phone", userPhone);
			req.getRequestDispatcher("/Account/NewAccount.jsp").forward(req, resp);
			return;
		}
		
		boolean result = dao.insertMember(userId, userPw, userName, userEmail, userPhone);
		dao.close();
		
		
		if (result) {
			 HttpSession session = req.getSession();
			    session.setAttribute("user_id", userId);  
			    session.setAttribute("user_name", userName);
			    
			    resp.sendRedirect(req.getContextPath() +"/index.jsp");
		}
		else {
			req.setAttribute("error", "로그인중 오류가 발생하였습니다");
			req.getRequestDispatcher("/Account/NewAccount.jsp").forward(req, resp);
			return;
		}
	}
}
