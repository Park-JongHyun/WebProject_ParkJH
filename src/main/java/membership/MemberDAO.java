package membership;

import common.JDBConnect;
import jakarta.servlet.ServletContext;


public class MemberDAO extends JDBConnect{
	
	public MemberDAO(String drv, String url, String id, String pw) {
		super(drv, url, id, pw);
	}
	
	public MemberDAO(ServletContext application) {
		super (application);
	}
	
	public MemberDTO getMemberDTO(String uid, String upass) {
		MemberDTO dto = new MemberDTO();
		
		String query = "SELECT * FROM WEBPROJECT_DB.MEMBER WHERE ID =? AND PASS=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, uid);
			psmt.setString(2, upass);
			rs = psmt.executeQuery();
			if(rs.next()) {
				dto.setId(rs.getString("ID"));
				dto.setPass(rs.getString("PASS"));
				dto.setName(rs.getString("NAME"));
				dto.setRegidate(rs.getString("REGIDATE"));
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return dto;
	}
	
	public boolean isIdExists(String uid) {
	    String sql = "SELECT COUNT(*) FROM WEBPROJECT_DB.MEMBER WHERE ID = ?";
	    try {
	        psmt = con.prepareStatement(sql);
	        psmt.setString(1, uid);
	        rs = psmt.executeQuery();
	        if(rs.next()) {
	            return rs.getInt(1) > 0;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}
	
	public boolean insertMember(String id, String pass, String name, String email, String phone) {
	    String sql = "INSERT INTO WEBPROJECT_DB.MEMBER (ID, PASS, NAME, REGIDATE, EMAIL, PHONE) VALUES (?, ?, ?,SYSDATE, ?, ?)";
	    try {
	        psmt = con.prepareStatement(sql);
	        psmt.setString(1, id);
	        psmt.setString(2, pass);
	        psmt.setString(3, name);
	        psmt.setString(4, email);
	        psmt.setString(5, phone);
	        int n = psmt.executeUpdate();
	        return n > 0;
	    } catch(Exception e) {
	        e.printStackTrace();
	    }
	    return false;
	}

}
