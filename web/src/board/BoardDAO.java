package board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.ConnectionManager;

public class BoardDAO {

	Connection conn;
	PreparedStatement pstmt;
	
	
	//전체조회
	public ArrayList<BoardVo> selectAll(BoardVo boardVo) {
		BoardVo resultVo = null;
		ResultSet rs = null;
		ArrayList<BoardVo> list = new ArrayList<BoardVo>();
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "SELECT NO, POSTER, SUBJECT, CONTENTS, LASTPOST, VIEWS, FILENAME "
						+ "FROM BOARD "
						+ "ORDER BY NO";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) { 
				resultVo = new BoardVo();
				resultVo.setNo((rs.getString("no")));
				resultVo.setPoster(rs.getString("poster"));
				resultVo.setSubject(rs.getString("subject"));
				resultVo.setContents(rs.getString("contents"));
				resultVo.setLastpost(rs.getString("lastpost"));
				resultVo.setViews(rs.getString("views"));
				resultVo.setFilename(rs.getString("filename"));
				list.add(resultVo);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(rs, pstmt, conn);
		}
		return list;  //담은 list를 리턴.
	} //selectAll
	
	
	//단건조회
	public BoardVo selectOne(BoardVo boardVo) {
		BoardVo resultVo = null;
		ResultSet rs = null;
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "SELECT NO, POSTER, SUBJECT, CONTENTS, LASTPOST, VIEWS, FILENAME "
						+ "FROM BOARD "
						+ "WHERE NO=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getNo());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				resultVo = new BoardVo();
				resultVo.setNo((rs.getString("no")));
				resultVo.setPoster(rs.getString("poster"));
				resultVo.setSubject(rs.getString("subject"));
				resultVo.setContents(rs.getString("contents"));
				resultVo.setLastpost(rs.getString("lastpost"));
				resultVo.setViews(rs.getString("views"));
				resultVo.setFilename(rs.getString("filename"));
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
	
	
	
	public void delete(BoardVo boardVo) {
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "DELETE BOARD WHERE NO=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getNo());
			int r = pstmt.executeUpdate();
			System.out.println(r + "건이 삭제됨");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(null, pstmt, conn);
		}
	} //삭제
	
	

	public void update(BoardVo boardVo) {
		try {
			conn = ConnectionManager.getConnnect();
			String sql = "UPDATE BOARD SET SUBJECT= ? WHERE NO=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getSubject()); //? 첫번째 자리 값
			pstmt.setString(2, boardVo.getNo());
			int r = pstmt.executeUpdate(); // 이때는 executeUpdate()에 sql안들어감.
			System.out.println(r + "건이 수정됨");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ConnectionManager.close(conn);
		}
	} //업뎃

	
	public void insert(BoardVo boardVo) {
		try {
			// 1. DB연결
			conn = ConnectionManager.getConnnect();
			conn.setAutoCommit(false); //자동커밋꺼놓음

			//보드번호 조회
			String seqSql = "select no from seq where tablename = 'board'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(seqSql);
			rs.next();
			int no = rs.getInt(1);
			boardVo.setNo(Integer.toString(no));
			
			//보드번호 업데이트
			seqSql = "update seq set no = no + 1 where tablename='board'";
			stmt = conn.createStatement();
			stmt.executeUpdate(seqSql);
			
			
			//게시글 등록
			// 2. sql 구문 실행
			String sql = "INSERT INTO BOARD (NO, POSTER, SUBJECT, CONTENTS, LASTPOST, VIEWS, FILENAME)"
						+ " VALUES(?,?,?,?, sysdate, 0,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, no);
			pstmt.setString(2, boardVo.getPoster());
			pstmt.setString(3, boardVo.getSubject());
			pstmt.setString(4, boardVo.getContents());
			pstmt.setString(5, boardVo.getFilename());
			//게시글넘버 select max(no)+1 from board로도 쓸 수 있음
			
			pstmt.executeUpdate();
			conn.commit();
			
			// 3. 결과 처리

		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			// 4. 연결 해제 (연결횟수제한으로인해 해제까지 해줘야함)
			ConnectionManager.close(conn);
		}
		
	} //인서트
	
	
	
}
