package com.it1457.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.it1457.vo.UserVO;

public class UserDAO {
	private static UserDAO dao = new UserDAO();

	private UserDAO() {
	}

	public static UserDAO getInstance() {
		return dao;
	}

	public Connection connect() {
		Connection conn = null;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mariadb://gsitm-intern2020.c5tdqadv8vmd.ap-northeast-2.rds.amazonaws.com/it1457", "it1457",
					"it1457");
		} catch (Exception ex) {
			System.out.println("오류 발생 : " + ex);
		}
		return conn;
	}

	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception ex) {
				System.out.println("오류 발생 : " + ex);
			}
		}
		close(conn, ps);
	} // close

	public void close(Connection conn, PreparedStatement ps) {
		if (ps != null) {
			try {
				ps.close();
			} catch (Exception ex) {
				System.out.println("오류 발생 : " + ex);
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception ex) {
				System.out.println("오류 발생 : " + ex);
			}
		}
	} // close

	// 회원가입
	public void userInsert(UserVO userVO) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("insert into user_tbl values(?,?,?,?,?,?,?,?)");
			pstmt.setString(1, userVO.getId());
			pstmt.setString(2, userVO.getPassword());
			pstmt.setString(3, userVO.getUserName());
			pstmt.setString(4, userVO.getIdentity());
			pstmt.setString(5, userVO.getPhone());
			pstmt.setString(6, userVO.getAccount());
			pstmt.setString(7, "0");
			pstmt.setBoolean(8, true);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			System.out.println("오류 발생 : " + ex);
		} finally {
			close(conn, pstmt);
		}
	}

	// 로그인
	public UserVO userSearch(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserVO userVO = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select * from user_tbl where user_id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				userVO = new UserVO();
				userVO.setId(rs.getString(1));
				userVO.setPassword(rs.getString(2));
				userVO.setUserName(rs.getString(3));
				userVO.setIdentity(rs.getString(4));
				userVO.setPhone(rs.getString(5));
				userVO.setAccount(rs.getString(6));
				userVO.setAuth(rs.getString(7));
				userVO.setWouldUYN(rs.getBoolean(8));
			}
		} catch (Exception ex) {
			System.out.println("오류 발생 : " + ex);
		} finally {
			close(conn, pstmt, rs);
		}

		return userVO;
	}

	// 회원탈퇴
	public int withdrawUser(String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("Update `it1457`.`user_tbl` set wouldUYN = ? where user_id = ?");
			pstmt.setBoolean(1, false);
			pstmt.setString(2, userId);
			pstmt.executeUpdate();
			
			return 1; // true
		} catch (Exception ex) {
			System.out.println("오류 발생 : " + ex);
			return 0;
		} finally {
			close(conn, pstmt);
		}
	}
}
