package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model2.mvcboard.MVCBoardDAO;
import model2.mvcboard.MVCBoardDTO;


public class BoardServlet extends HttpServlet {
   
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		

    	MVCBoardDAO dao = new MVCBoardDAO();
        String searchField = req.getParameter("searchField");
        String searchWord = req.getParameter("searchWord");

        Map<String,Object> map = new HashMap<>();
        map.put("boardType", "free");  
        map.put("searchField", searchField);
        map.put("searchWord", searchWord);

        int totalCount = dao.selectCount(map);
        map.put("totalCount", totalCount);

        List<MVCBoardDTO> boardLists = dao.selectList(map);

        req.setAttribute("boardLists", boardLists);
        req.setAttribute("map", map);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/mvcboard/List.jsp");
        dispatcher.forward(req, resp);
    }
}



