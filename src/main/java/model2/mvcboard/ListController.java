package model2.mvcboard;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		MVCBoardDAO dao = new MVCBoardDAO();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		String searchField = req.getParameter("searchField");
		String searchWord = req.getParameter("serachWord");
		if (searchWord != null) {
			map.put("searchField",searchField);
			map.put("searchWord", searchWord);
		}
		int totalCount = dao.selectCount(map);
		
		List<MVCBoardDTO> boardLists = dao.selectList(map);
		dao.close();
		
		map.put("totalCount", totalCount);
		
		req.setAttribute("boardLists", boardLists);
		req.setAttribute("map", map);
		req.getRequestDispatcher("/MVCBoard/List.jsp").forward(req, resp);
	}
}





































