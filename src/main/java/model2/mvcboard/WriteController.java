package model2.mvcboard;

import java.io.IOException;

import fileupload.FileUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utils.JSFunction;

public class WriteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		HttpSession session = req.getSession();
		if(session.getAttribute("UserId")==null) {
			JSFunction.alertLocation(resp,"로그인 후 이용해주세요.", "../Login/LoginMain.jsp");
		return;
		}
	
		String boardType = req.getParameter("boardType");
        req.setAttribute("boardType", boardType);
		
		req.getRequestDispatcher("/MVCBoard/Write.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	HttpSession session = req.getSession();
	if(session.getAttribute("UserId") == null) {
		JSFunction.alertLocation(resp, "로그인후 이용해주세요.", "../Login/LoginMain.jsp");
		return;
	}
	
	String boardType = req.getParameter("boardType");
	
	String saveDirectory = req.getServletContext().getRealPath("/Uploads");
	String originalFileName = "";
	
	if("data".equals(boardType)) {
	try {
		String uploadedFile = FileUtil.uploadFile(req, saveDirectory);
		if(uploadedFile == null || uploadedFile.isEmpty()) {
			JSFunction.alertLocation(resp, "자료게시판은 파일 첨부가 필요합니다.", "../mvcboard/write.do?boardType="+boardType);
			return;
		}
		originalFileName = uploadedFile;
	}
	catch (Exception e) {
		JSFunction.alertLocation(resp, "파일 업로드 오류입니다.", "../mvcboard/write.do?boardType="+boardType);
		return;
	}
	}
	
	MVCBoardDTO dto = new MVCBoardDTO();
	dto.setId(session.getAttribute("UserId").toString());
	dto.setTitle(req.getParameter("title"));
	dto.setContent(req.getParameter("content"));
	
	if ("data".equals(boardType) && !originalFileName.isEmpty()) {
		String saveFileName = FileUtil.renameFile(saveDirectory, originalFileName);
		
		dto.setOfile(originalFileName);
		dto.setSfile(saveFileName);
	}
	
	MVCBoardDAO dao = new MVCBoardDAO();
	int result = dao.insertWrite(dto, boardType);
	dao.close();
	
	if (result == 1) {
		resp.sendRedirect("../mvcboard/list.do?boardType="+boardType);
	}
	else { JSFunction.alertLocation(resp, "글쓰기에 실패합니다.", "../mvcboard/write.do");
	
	}
  }
}
