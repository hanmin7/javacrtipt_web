package dept;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import common.ConnectionManager;

public class EmpDAO {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	//싱글톤  : static 필드
	static EmpDAO instance;	
	public static EmpDAO getInstance() {
		if (instance == null)
			instance = new EmpDAO();
		return instance;
	}
	
	//등록
	public int insert(EmpVO dto) {
		int r = 0;
		try {
			//1.DB연결
			conn = ConnectionManager.getConnnect();
			
			//트랜잭션 처리
			conn.setAutoCommit(false);
			//2.sql구문 준비
			String sql = "insert into hr.employees (employee_id, last_name, email,"
					+ " hire_date, job_id, first_name, manager_id, department_id ) values (?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			//3. 실행
			pstmt.setString(1, dto.getEmployee_id());
			pstmt.setString(2, dto.getLast_name());
			pstmt.setString(3, dto.getEmail());
			pstmt.setString(4, dto.getHire_date());
			pstmt.setString(5, dto.getJob_id());
			pstmt.setString(6, dto.getFirst_name());
			pstmt.setString(7, dto.getManager_id());
			pstmt.setString(8, dto.getDepartment_id());
			r = pstmt.executeUpdate();
			conn.commit();
			//4. 결과처리
			System.out.println(r + " 건이 등록됨");
		} catch(Exception e) {
			e.printStackTrace(); 
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			} 
		} finally {
			//5. 연결해제 
			ConnectionManager.close(conn);
		}
		return r;
	}
	
	//수정
	public int update(EmpVO dto) {
		int r = 0;
		try {
			//1.DB연결
			conn = ConnectionManager.getConnnect();
			
			//2.sql구문 준비
			String sql = "update employees set  last_name=?, email=?,"
					+ " hire_date=?, job_id=? where employee_id = ?";
			pstmt = conn.prepareStatement(sql);
			//3. 실행
			pstmt.setString(1, dto.getLast_name());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getHire_date());
			pstmt.setString(4, dto.getJob_id());
			pstmt.setString(5, dto.getEmployee_id());
			r = pstmt.executeUpdate();
			//4. 결과처리
			System.out.println(r + " 건이 수정됨");
		} catch(Exception e) {   e.printStackTrace(); }	
		finally {
			//5. 연결해제 
			ConnectionManager.close(conn);
		}
		return r;
	}	
	//삭제
	public int delete(EmpVO dto) {
		int r = 0;
		try {
			//1.DB연결
			conn = ConnectionManager.getConnnect();			
			//2.sql구문 준비
			String sql = "delete employees where employee_id = ?";
			pstmt = conn.prepareStatement(sql);
			//3. 실행
			pstmt.setString(1, dto.getEmployee_id());
			r = pstmt.executeUpdate();
			//4. 결과처리
			System.out.println(r + " 건이 삭제됨");
		} catch(Exception e) {   e.printStackTrace(); }	
		finally {
			//5. 연결해제 
			ConnectionManager.close(conn);
		}
		return r;
	}
	//부서별 급여합계  부서번호,부서명,급여합계 List<Map>
	public List<Map<String,Object>> selectDeptSal(){
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try {
			conn = ConnectionManager.getConnnect();	
			String sql = "select a.department_id, department_name,"
					+ " sum(salary) sal, count(*) cnt, avg(salary) avgsal " + 
					" from departments a, employees b" + 
					" where a.department_id = b.department_id" + 
					" group by a.department_id, department_name";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("department_id", rs.getString("department_id"));
				map.put("department_name", rs.getString("department_name"));
				map.put("salary", rs.getString("sal"));
				map.put("cnt", rs.getString("cnt"));
				map.put("avgsal", rs.getString("avgsal"));
				list.add(map);
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn);
		}	
		return list;
	}
	
	//단건조회
	public EmpVO selectOne(EmpVO dto) {
		EmpVO emp = new EmpVO();
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "select * from employees where employee_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getEmployee_id());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				emp.setCommission_pct(rs.getString("commission_pct"));
				emp.setDepartment_id(rs.getString("department_id"));
				emp.setEmail(rs.getString("email"));
				emp.setEmployee_id(rs.getString("employee_id"));
				emp.setFirst_name(rs.getString("first_name"));
				emp.setHire_date(rs.getString("hire_date"));
				emp.setJob_id(rs.getString("job_id"));
				emp.setLast_name(rs.getString("last_name"));
				emp.setManager_id(rs.getString("manager_id"));
				emp.setPhone_number(rs.getString("phone_number"));
				emp.setSalary(rs.getString("salary"));
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(rs, pstmt, conn);
		}		
		return emp;
	}
	
	//전체조회
	public List<EmpVO> selectAll() {
		List<EmpVO> datas = new ArrayList<EmpVO>();
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "select * from hr.employees order by employee_id";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpVO emp = new EmpVO();
				emp.setCommission_pct(rs.getString("commission_pct"));
				emp.setDepartment_id(rs.getString("department_id"));
				emp.setEmail(rs.getString("email"));
				emp.setEmployee_id(rs.getString("employee_id"));
				emp.setFirst_name(rs.getString("first_name"));
				emp.setHire_date(rs.getString("hire_date"));
				emp.setJob_id(rs.getString("job_id"));
				emp.setLast_name(rs.getString("last_name"));
				emp.setManager_id(rs.getString("manager_id"));
				emp.setPhone_number(rs.getString("phone_number"));
				emp.setSalary(rs.getString("salary"));
				datas.add(emp);   //dto를 리스트에 추가
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionManager.close(conn);
		}
		return datas;
	}
	//전체 건수 
	public int count() {
		int cnt = 0;
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "select count(*) from employees";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();
			cnt = rs.getInt(1);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionManager.close(conn);
		}
		return cnt;
	}
	
	public List<EmpVO> selectPage(int first, int last) {
		List<EmpVO> datas = new ArrayList<EmpVO>();
		try {
			conn = ConnectionManager.getConnnect();
			String sql = " select *    from (   select rownum rn, a.*   from ( "
					   + " select * from employees order by employee_id "
					   + " )  a ) b  where  rn between ? and ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, first);
			pstmt.setInt(2, last);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				EmpVO emp = new EmpVO();
				emp.setCommission_pct(rs.getString("commission_pct"));
				emp.setDepartment_id(rs.getString("department_id"));
				emp.setEmail(rs.getString("email"));
				emp.setEmployee_id(rs.getString("employee_id"));
				emp.setFirst_name(rs.getString("first_name"));
				emp.setHire_date(rs.getString("hire_date"));
				emp.setJob_id(rs.getString("job_id"));
				emp.setLast_name(rs.getString("last_name"));
				emp.setManager_id(rs.getString("manager_id"));
				emp.setPhone_number(rs.getString("phone_number"));
				emp.setSalary(rs.getString("salary"));
				datas.add(emp);   //dto를 리스트에 추가
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			ConnectionManager.close(conn);
		}
		return datas;
	}	
}





