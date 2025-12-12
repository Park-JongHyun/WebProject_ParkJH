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

@WebServlet("/BoardServlet")
public class BoardServlet extends HttpServlet {
   
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        MVCBoardDAO dao = new MVCBoardDAO();

        // 1) URL에서 boardType 받아오기
        String boardType = req.getParameter("boardType");
        if (boardType == null) {
            boardType = "free";  // 기본 게시판
        }

        // 2) 검색 파라미터
        String searchField = req.getParameter("searchField");
        String searchWord = req.getParameter("searchWord");

        Map<String, Object> map = new HashMap<>();
        map.put("boardType", boardType);
        map.put("searchField", searchField);
        map.put("searchWord", searchWord);

        // 3) 전체 게시물 수
        int totalCount = dao.selectCount(map);
        map.put("totalCount", totalCount);

        // 4) 게시물 목록
        List<MVCBoardDTO> boardLists = dao.selectList(map);

        // 5) JSP로 전달
        req.setAttribute("boardLists", boardLists);
        req.setAttribute("map", map);

        // 6) HTML5UP 템플릿 적용한 list.jsp 로 포워딩
        RequestDispatcher dispatcher =
                req.getRequestDispatcher("/mvcboard/List.jsp");
        dispatcher.forward(req, resp);
    }
}
