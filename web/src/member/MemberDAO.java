package member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.ConnectionManager;

public class MemberDAO {
	Connection conn;
	PreparedStatement pstmt;
	
	
	//전체조회
	public ArrayList<MemberVo> selectAll(MemberVo MemberVo) {
		MemberVo resultVo = null;
		ResultSet rs = null;
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "SELECT ID, PW, GENDER, JOB, REASON, MAILYN, HOBBY "
						+ "FROM MEMBER "
						+ "ORDER BY ID";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) { //list니까 while문 사용
				resultVo = new MemberVo();
				resultVo.setId((rs.getString("id")));
				resultVo.setPw(rs.getString("pw"));
				resultVo.setGender(rs.getString("gender"));
				resultVo.setJob(rs.getString("job"));
				resultVo.setReason(rs.getString("reason"));
				resultVo.setMailyn(rs.getString("mailyn"));
				resultVo.setHobby(rs.getString("hobby"));
				list.add(resultVo); //resultVo를 list에 담음
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(rs, pstmt, conn);
		}
		return list;  //담은 list를 리턴.
	} //selectAll
	
	
	//단건조회
	public MemberVo selectOne(MemberVo memberVo) {
		MemberVo resultVo = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "SELECT ID, PW, GENDER, JOB, REASON, MAILYN "
						+ "FROM MEMBER "
						+ "WHERE ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberVo.getId());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				resultVo = new MemberVo();
				resultVo.setId((rs.getString("id")));
				resultVo.setPw(rs.getString("pw"));
				resultVo.setGender(rs.getString("gender"));
				resultVo.setJob(rs.getString("job"));
				resultVo.setReason(rs.getString("reason"));
				resultVo.setMailyn(rs.getString("mailyn"));
			} else {
				System.out.println("no data");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(rs, pstmt, conn);
		}
		return resultVo; // 리턴값 필요!
	} //selectOne
	
	
	//메일수신회원수 : select count(id) from member where mailyn=''
	public int getMailynCnt() {
		int cnt = 0;
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "select count(id) from member where mailyn='Y'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			cnt = rs.getInt(1); //count는 값이 없어도 0이 나오니까 if 안돌려도 괜찮. 
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionManager.close(conn);
		}
		return cnt;
	}
	
	
	//성별인원수 : select gender, count(id) cnt from member group by gender
	//조인이나 이런거.. map이 편함.
	public List<HashMap<String, Object>> getGenderCnt() {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "select gender, count(id) cnt from member group by gender";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("gender", rs.getString("gender"));
				map.put("cnt", rs.getInt("cnt"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionManager.close(conn);
		}
		return list;
	}
	
	
	//직업별회원수 : select job, count(id) cnt from member group by job
	public List<HashMap<String, Object>> getJobCnt() {
		List<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "select job, count(id) cnt from member group by job";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("job", rs.getString("job"));
				map.put("cnt", rs.getInt("cnt"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			ConnectionManager.close(conn);
		}
		return list;
	}
	
	
	
	
	public void delete(MemberVo memberVo) {
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "DELETE MEMBER WHERE ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberVo.getId());
			int r = pstmt.executeUpdate();
			System.out.println(r + "건이 수정됨");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(null, pstmt, conn);
		}
	} //삭제
	
	

	public void update(MemberVo memberVo) {
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "UPDATE MEMBER SET PW = ? WHERE ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberVo.getPw()); //? 첫번째 자리 값
			pstmt.setString(2, memberVo.getId());
			int r = pstmt.executeUpdate(); // 이때는 executeUpdate()에 sql안들어감.
			System.out.println(r + "건이 수정됨");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn);
		}
	} //업뎃

	
	public void insert(MemberVo memberVo) {
		try {
			// 1. DB연결
			conn = ConnectionManager.getConnnect();
			
			// 2. sql 구문 실행
			String sql = "insert into member(id, pw, job, reason, gender, mailyn, hobby, regdate)"
					 	+ "values (?,?,?,?,?,?,?, sysdate)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberVo.getId());		
			pstmt.setString(2, memberVo.getPw());		
			pstmt.setString(3, memberVo.getJob());		
			pstmt.setString(4, memberVo.getReason());		
			pstmt.setString(5, memberVo.getGender());		
			pstmt.setString(6, memberVo.getMailyn());		
			pstmt.setString(7, memberVo.getHobby());		
					
			int r = pstmt.executeUpdate();

			// 3. 결과 처리
			if (r == 1) {
				System.out.println(r + "건이 처리됨");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 4. 연결 해제 (연결횟수제한으로인해 해제까지 해줘야함)
			ConnectionManager.close(conn);
		}
	} //인서트
	
	
	
	
}
