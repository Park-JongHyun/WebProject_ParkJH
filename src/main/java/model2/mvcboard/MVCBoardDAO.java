package model2.mvcboard;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

public class MVCBoardDAO extends DBConnPool {
	public MVCBoardDAO() {
		super();
	}
	
    private String getTableName(String boardType) {
        if (boardType == null) return "MVCBOARD_FREE"; 
        switch (boardType) {
            case "free":
                return "MVCBOARD_FREE";   
            case "data":
                return "MVCBOARD_DATA";   
            case "notice":
                return "MVCBOARD_NOTICE";      
            default:	return "MVCBOARD_FREE";
        }
    }
	
	public int selectCount(Map<String,Object>map) {
		int totalCount = 0;
		
		 String boardType = (String) map.get("boardType");
	     String table = getTableName(boardType);
		
		String query = " SELECT COUNT(*) FROM " +table;
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
		List<MVCBoardDTO> board = new Vector<>();
		
		String boardType = (String) map.get("boardType");
        String table = getTableName(boardType);
		
		String query = " SELECT * FROM " + table;
		if (map.get("searchWord") != null) {
			query += " WHERE " + map.get("searchField")+" LIKE '%" + map.get("searchWord")+"%' ";
		}
		query += " ORDER BY idx DESC ";
		
		try {
			psmt = con.prepareStatement(query);
			rs = psmt.executeQuery();
			
			while (rs.next()) {
				MVCBoardDTO dto = new MVCBoardDTO();
				
				dto.setIdx(rs.getString("IDX"));
				dto.setId(rs.getString("ID"));
				dto.setTitle(rs.getString("TITLE"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setPostdate(rs.getDate("POSTDATE"));
				try { dto.setOfile(rs.getString("OFILE")); } catch (Exception ignore) {};
                try { dto.setSfile(rs.getString("SFILE")); } catch (Exception ignore) {};
				try {dto.setDowncount(rs.getInt("DOWNCOUNT"));} catch (Exception ignore) {};
				try {dto.setVisitcount(rs.getInt("VISITCOUNT"));} catch (Exception ignore) {};
				
				board.add(dto);
				
			}
		}
		catch (Exception e) {
			System.out.println("게시물 조회 중 예외 발생");
			e.printStackTrace();
		}
		return board;
	}
	
	
	
	public int insertWrite(MVCBoardDTO dto, String boardType) {
	    int result = 0;
	    String table = getTableName(boardType); 

	    String query;
	    if ("data".equals(boardType)) {
	        query = " INSERT INTO " + table + " (idx, id, title, content, ofile, sfile) "
	              + " VALUES (seq_board_num.NEXTVAL, ?, ?, ?, ?, ?) ";
	    } else {
	        query = " INSERT INTO " + table + " (idx, id, title, content) "
	              + " VALUES (seq_board_num.NEXTVAL, ?, ?, ?) ";
	    }

	    try {
	        psmt = con.prepareStatement(query);
	        psmt.setString(1, dto.getId());
	        psmt.setString(2, dto.getTitle());
	        psmt.setString(3, dto.getContent());

	        if ("data".equals(boardType)) {
	            psmt.setString(4, dto.getOfile());
	            psmt.setString(5, dto.getSfile());
	        }

	        result = psmt.executeUpdate();
	    } catch (Exception e) {
	        System.out.println("게시물 입력 중 예외 발생");
	        e.printStackTrace();
	    }

	    return result;
	}


}
