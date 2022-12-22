package jdbc.ex2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoticeService {
	private String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";  
	private String id = "DOHYUN";
	private String pwd = "ASD123";
	
	public int getCount (String field, String query) {
		String sql = "SELECT COUNT(id) CNT FROM NOTICE WHERE " + field + " Like ? ";
		
		int count = 0;
		
		try {
	    	Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, id, pwd);
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + query + "%");
			ResultSet rs = st.executeQuery();
		    
		    if(rs.next()) {
		       count = rs.getInt("CNT"); 
		    }
		   
		    rs.close();
		    st.close();
		    con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
	
	public List<Notice> getList(int page, String field, String query){
	    String sql = "SELECT * FROM (select rownum num, noti.* from (select * from notice WHERE " + field + " LIKE ? order by regdate desc) noti) WHERE NUM BETWEEN ? AND ?";
	    
	    // System.out.println(sql);
	    
	    List<Notice> list = new ArrayList<Notice>();
	    
	    int start = (page -1) *10 + 1;  
	    int end = page * 10;
	    		
	    try {
	    	Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, id, pwd);
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, "%" + query + "%");
			st.setInt(2, start);
			st.setInt(3, end);
		    ResultSet rs = st.executeQuery();
		    
		    while(rs.next()) {
		        int nid = rs.getInt("ID");
		        String title = rs.getString("TITLE");
		        String writeId = rs.getString("WRITER_ID");
		        String content = rs.getString("CONTENT");
		        Date regDate = rs.getDate("REGDATE");
				int hit = rs.getInt("HIT");
				String files = rs.getString("FILES");
				int pub = rs.getInt("PUB");
				
		        Notice n = new Notice(nid, title, writeId, content, regDate, hit, files, pub);
		        
		        list.add(n);
		    }
		   
		    rs.close();
		    st.close();
		    con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	    return list;
	}

	public void setHitUp(int nid) {
		String sql = "UPDATE NOTICE SET HIT = HIT+1 WHERE ID=?";
		
	    try {
	    	Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, id, pwd);
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, nid);
			int rs = st.executeUpdate();
		    
		    st.close();
		    con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public void insertData(String title, String writeId, String files, String content) {
	    String sql = "INSERT INTO notice ("
	                    + "    title,"
	                    + "    writer_id,"
	                    + "    content,"
	                    + "    files"
	                    + ") VALUES (?,?,?,?)";
	    
		try {
		    Class.forName("oracle.jdbc.driver.OracleDriver");
		    Connection con = DriverManager.getConnection(url, id, pwd);
		    PreparedStatement st = con.prepareStatement(sql);
		    st.setString(1, title);
		    st.setString(2, writeId);
		    st.setString(3, content);
		    st.setString(4, files);

		    st.executeUpdate();
		    
		    st.close();
		    con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    		
	}

	public void deleteData(int nid) {
		String sql = "DELETE NOTICE WHERE ID=?";

	    try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection(url, id, pwd); // id, pwd »ý·«
		    PreparedStatement st = con.prepareStatement(sql);
		    st.setInt(1, nid);
		    
		    st.executeUpdate();

		    st.close();
		    con.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
