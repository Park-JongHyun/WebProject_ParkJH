package model2.mvcboard;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/mvcboard/view.do")
public class ViewController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String idx = req.getParameter("idx");
		String boardType = req.getParameter("boardType");
		
		if(boardType == null) boardType = "free"; 
		req.setAttribute("boardType", boardType);
		
		
		MVCBoardDAO dao = new MVCBoardDAO();
		dao.updateVisitCount(idx, boardType);
		MVCBoardDTO dto = dao.selectView(idx, boardType);
		dao.close();
		

		dto.setContent(dto.getContent() == null ? "": dto.getContent().replaceAll("\r\n", "<br />"));
		
		
		req.setAttribute("dto", dto);
		req.setAttribute("boardType", boardType);
		
		req.getRequestDispatcher("/MVCBoard/View.jsp").forward(req, resp);
	}
}
