package dept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import common.ConnectionManager;

public class DeptDAO {
	// 전역변수. 모든 메서드에 공통으로 사용되는 변수
	Connection conn;
	PreparedStatement pstmt;
	
	
	//전체조회
	public ArrayList<DeptVo> selectAll(DeptVo deptVo) {
		DeptVo resultVo = null;
		ResultSet rs = null;
		ArrayList<DeptVo> list = new ArrayList<DeptVo>(); //여러건 담아와야하니까 list에 담음.
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "SELECT DEPARTMENT_ID, DEPARTMENT_NAME, MANAGER_ID as mgr_id, LOCATION_ID "
						+ "FROM DEPARTMENTS "
						+ "ORDER BY DEPARTMENT_ID";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) { //list니까 while문 사용
				resultVo = new DeptVo();
				resultVo.setDepartment_id((rs.getInt(1)));
				resultVo.setDepartment_name(rs.getString("department_name"));
				resultVo.setManager_id(rs.getInt("mgr_id"));
				resultVo.setLocation_id(rs.getInt("location_id"));
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
	public DeptVo selectOne(DeptVo deptVo) {
		DeptVo resultVo = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "SELECT DEPARTMENT_ID, DEPARTMENT_NAME, MANAGER_ID as mgr_id, LOCATION_ID "
						+ "FROM DEPARTMENTS "
						+ "WHERE DEPARTMENT_ID=?"; //ctr+shi+x :대문자, y:소문자로 변경 단축키
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deptVo.getDepartment_id());
			rs = pstmt.executeQuery();
			if(rs.next()) { //커서가 BOF에서 다음으로 넘어가야..
				resultVo = new DeptVo();
				resultVo.setDepartment_id((rs.getInt(1)));
				resultVo.setDepartment_name(rs.getString("department_name")); //대소 상관x
				resultVo.setManager_id(rs.getInt("mgr_id")); //별칭 쓰면 별칭을 넣어야함.
				resultVo.setLocation_id(rs.getInt("location_id"));
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
	
	
	
	public void delete(DeptVo deptVo) {
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "delete departments where department_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, deptVo.getDepartment_id());
			int r = pstmt.executeUpdate();
			System.out.println(r + "건이 수정됨");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(null, pstmt, conn);
		}
	} //삭제
	
	

	public void update(DeptVo deptVo) {
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "update departments set department_name = ? where department_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, deptVo.getDepartment_name()); //? 첫번째 자리 값
			pstmt.setInt(2, deptVo.getDepartment_id());
			int r = pstmt.executeUpdate(); // 이때는 executeUpdate()에 sql안들어감.
			System.out.println(r + "건이 수정됨");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn);
			//ConnectionManager.close(null, pstmt, conn);
		}
	} //업뎃

	
	public void insert(DeptVo deptVo) {
		try {
			// 1. DB연결
			conn = ConnectionManager.getConnnect();

			// 2. sql 구문 실행
			String sql = "insert into departments (department_id, department_name)" + "values("
					+ deptVo.getDepartment_id() + ", '" + deptVo.getDepartment_name() + "')";
			Statement stmt = conn.createStatement();
			int r = stmt.executeUpdate(sql); // 여기서는 sql 넣어줘야함.

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
