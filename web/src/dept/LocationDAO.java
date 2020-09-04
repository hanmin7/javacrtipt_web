package dept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import common.ConnectionManager;


public class LocationDAO {

	//싱글톤
	private static LocationDAO instance = new LocationDAO();
	public static LocationDAO getInstance() {
		return instance;
	}
	//전체조회
	public ArrayList<HashMap<String, String>> selectAll() {
		ArrayList<HashMap<String, String>> list = 
				new  ArrayList<HashMap<String, String>>();	
		Connection conn = null;
		try {			
			conn=ConnectionManager.getConnnect();
 			//3.Statement 			
			String sql=" select location_id, city "
				 	 + "   from hr.locations  order by city ";       
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();			
			while(rs.next()) {
				HashMap<String, String> map = new HashMap<String, String>();
				map.put("location_id", rs.getString("location_id"));
				map.put("city",rs.getString("city"));
				list.add(map);
			}			
		} catch (Exception e) { e.printStackTrace(); } 	
		System.out.println(list);
		return list;
	}
}
