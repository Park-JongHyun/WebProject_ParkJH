package servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import membership.MemberDAO;
import membership.MemberDTO;

public class LoginServlet extends HttpServlet{
	private static final long serialVersionUID =  1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userId = req.getParameter("user_id");
		String userPwd = req.getParameter("user_pw");
	
	MemberDAO dao = new MemberDAO(
		getServletContext().getInitParameter("OracleDriver"),
		getServletContext().getInitParameter("OracleURL"),
		getServletContext().getInitParameter("OracleId"),
		getServletContext().getInitParameter("OraclePwd")
	);
	
	boolean idExists = dao.isIdExists(userId);
	
	MemberDTO memberDTO = null;
	if (idExists) {
		memberDTO = dao.getMemberDTO(userId, userPwd); 		
	}
	dao.close();
	
	if (memberDTO != null && memberDTO.getId() != null) {
		HttpSession session = req.getSession();
		session.setAttribute("UserId", memberDTO.getId());
		session.setAttribute("UserName", memberDTO.getName());
		resp.sendRedirect(req.getContextPath() + "/Login/LoginMain.jsp");
	}
	else if (idExists) {
		req.setAttribute("LoginErrMsg", "비밀번호가 일치하지 않습니다.");
		req.getRequestDispatcher("/Login/LoginMain.jsp").forward(req, resp);
	}
	else {
		req.setAttribute("idExists", false);
		req.getRequestDispatcher("/Login/LoginMain.jsp").forward(req, resp);
	}
	
	
	}
}
