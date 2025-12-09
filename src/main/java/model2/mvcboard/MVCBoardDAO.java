package model2.mvcboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

public class MVCBoardDAO extends DBConnPool {
	public MVCBoardDAO() {
		super();
	}
	
	public int selectCount(Map<String,Object>map) {
		int totalCount = 0;
		String query = " SELECT COUNT(*) FROM mvcboard";
		if(map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField")+" "+" LIKE '%"+map.get("searchWord")+"%'";
		}
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			rs.next();
			totalCount = rs.getInt(1);
		}
		catch (Exception e) {
			System.out.println("게시물 카운트 중 예외발생");
			e.printStackTrace();
		}
		return totalCount;
	}
	
	public List<MVCBoardDTO> selectList(Map <String,Object> map){
		List<MVCBoardDTO> board = new Vector<MVCBoardDTO>();
		String query = " SELECT * FROM mvcboard ";
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField")+" LIKE '%" + map.get("searchWord")+"%' ";
		}
		query += " ORDER BY idx DEXC ";
		
		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				MVCBoardDTO dto = new MVCBoardDTO();
				
				dto.setIdx(rs.getString(1));
				dto.setId(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setVisitcount(rs.getInt(9));
				
				board.add(dto);
				
			}
		}
		catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return board;
	}
	
	
	
	public int insertWrite(MVCBoardDTO dto) {
		int result = 0;
		try {
			String query = "INSERT INTO mvcboard ( "
					+ " idx, id, title, content, ofile, sfile)"
					+ " VALUES ( "
					+ " seq_board_num.NEXTVAL,?,?,?,?,?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getId());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			result = psmt.executeUpdate();
		}
		catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
