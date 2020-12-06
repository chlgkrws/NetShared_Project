package com.it1457.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.HashSet;

import com.it1457.vo.UserVO;

public class MatchingDAO {

	private static MatchingDAO dao = new MatchingDAO();

	private MatchingDAO() {
	}

	public static MatchingDAO getInstance() {
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

	// 파티원 신청자가 신청 가능한지 판별.
	public boolean isPossibleToWaitMember(String userId) {
		Connection conn = null;
		PreparedStatement CheckAlreadyUsedPstmt = null;
		ResultSet rs = null;
		String tableName = "member_wait_tbl";
		try {
			conn = connect();
			boolean isExist = isExistWaiting(userId, tableName); // true면 값이 있음, false면 값이 없음 || 값이 있으면 대기, 없으면 가능
			System.out.println("(DAO - isPossibleToWaitMember)isExist : " + isExist);

			// 이미 대기열에 존재한다면
			if (isExist) {
				// 대기열에 존재하지만 이미 매칭된 사람의 매칭 취소인지, 사용중인지 판별
				CheckAlreadyUsedPstmt = conn
						.prepareStatement("select wouldUYN from party_member_info_tbl where member_id = ?");
				CheckAlreadyUsedPstmt.setString(1, userId);
				rs = CheckAlreadyUsedPstmt.executeQuery();

				HashSet<Integer> checkTF = new HashSet<>();
				boolean isUsed = rs.next();
				System.out.println("(DAO - isPossibleToWaitMember)isUsed : " + isUsed);
				// 여기12-03했습니다.
				// 반환된 값이 있으면
				if (isUsed) {
					checkTF.add(rs.getInt(1));
					while (rs.next()) {
						checkTF.add(rs.getInt(1));
					}
					// 과거 기록 + 현재 진행 매칭 = 2 = 현재 사용 중
					if (checkTF.size() == 2) {
						System.out.println("(DAO - isPossibleToWaitMember)이미 대기열 존재, 이미 매칭된 유저");
						return false;
						// 결과값 1 = 현재 매칭된 상태 or 파티탈퇴(이후 재 매칭 안한 경우)
					} else {
						// v == 0은 파티 탈퇴한 상황
						for (Integer v : checkTF) {
							if (v == 0) {
								System.out.println(
										"(DAO - isPossibleToWaitMember)이미 대기열에 존재하지만 이전 파티를 탈퇴하고 다시 매칭을 신청하는 유저");
								// update 쿼리 필요
								return true;
							}
						}
					}
					System.out.println(
							"(DAO - isPossibleToWaitMember)이미 대기열에 존재하고, 이전 매칭 기록도 있음. 논리적 오류(wouldUYN : 1, size : 1인경우)");
					return false;
				} else {
					// 예외 케이스
					// 대기열에 존재하는 사람은 이전에 서비스를 이용했거나, 지금 대기중인가 인데,
					// 현재 조건문에 들어온 이유는 이미 대기열에 존재한다는 것이지만
					// 매칭을 한적이 없다는 것이다. 비정상 부분
					System.out.println("(DAO - isPossibleToWaitMember)예외 케이스 비정상 종료");
					return false;
				}
				// 대기열에 존재하지 않으면 신청 가능 true반환
			} else {
				// 삽입 쿼리 넣기
				System.out.println("(DAO - isPossibleToWaitMember)대기열에 존재하지 않는 유저 ");
				return true;
			}

			// 현재 메서드는 신청해도 되는지 안되는지 판별한다.
			// 신청할 수 있으면 true 반환, 신청할 수 없으면 false 반환
			// 신청할 수 있는 사람은 db에 (wait에 데이터가 아예 없거나, [**있어도, is_wait가 false여야한다.) 만약 is_wait가
			// true면 이미 신청한 것이기 때문에 신청할 수 없다.]
			// **is_wait가 false인 사람이라면, 현재 서비스를 사용하는지 확인해야한다.
			// 그럼 하나의 로우를 가져와서 없는 결과라면 true를 반환해주고, 있다면 is_wait의 값을 확인한다.
			// is wait가 true(대기상태)이면 false(신청불가/중복신청)를 반환

		} catch (Exception ex) {
			System.out.println("(DAO - isPossibleToWaitMember)오류 발생 : " + ex);
			return false;
		} finally {
			close(conn, CheckAlreadyUsedPstmt);
		}
	}

	// 파티장 신청자가 신청 가능한지.
	public boolean isPossibleToWaitLeader(String userId) {
		Connection conn = null;
		PreparedStatement CheckAlreadyUsedPstmt = null;
		String tableName = "leader_wait_tbl";
		
		ResultSet rs = null;
		try {
			conn = connect();
			boolean isExist = isExistWaiting(userId, tableName);
			
			System.out.println("(DAO - isPossibleToWaitLeader) isExist : " + isExist);
			// 이미 대기열에 존재한다면
			if (isExist) {
				// 대기열에 존재하지만 이미 매칭된 리더의 파티 탈퇴인지, 파티 중인지 판별
				CheckAlreadyUsedPstmt = conn
						.prepareStatement("select wouldUYN from matching_tbl where leader_id = ? and wouldUYN = 1");
				CheckAlreadyUsedPstmt.setString(1, userId);
				rs = CheckAlreadyUsedPstmt.executeQuery();

				// 매칭 되어 있지 않음.
				if (!rs.next()) {
					System.out.println("(DAO - isPossibleToWaitLeader)대기열에 이미 존재하지만, 매칭 중이진 않는 파티장");
					return true;
				}
			} else {
				System.out.println("대기열에 존재하지 않는 파티장");
				return true;
			}
		} catch (Exception ex) {
			System.out.println("오류 발생 : " + ex);
			return false;
		} finally {
			close(conn, CheckAlreadyUsedPstmt);
		}
		System.out.println("(DAO - isPossibleToWaitLeader)대기열 존재, 매칭 중(실패)");
		return false;
	}

	//신청자가 wait테이블에 존재하는지 체크
	public boolean isExistWaiting(String userId, String tableName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("select user_id, is_wait from " + tableName + " where user_id = ?");
			pstmt.setString(1, userId);
			rs = pstmt.executeQuery();
			//값이 있으면 기다릴 수 없음.(= 업데이트를 해야함)
			if (rs.next()) {
				return true;
			}
			
			return false;
		} catch (Exception ex) {
			System.out.println("오류 발생 : " + ex);
		} finally {
			close(conn, pstmt);
		}
		return false;
	}
	
	// 일반 매칭 - 파티원 insert
	public void insertWaitMember(String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = connect();
			pstmt = conn.prepareStatement("INSERT INTO member_wait_tbl(user_id) VALUES (?)");
			pstmt.setString(1, userId);
			pstmt.executeUpdate();
			
			System.out.println("(DAO - insertWaitMember)"+userId+" 파티원으로 insert");
		} catch (Exception ex) {
			System.out.println("오류 발생 : " + ex);
		} finally {
			close(conn, pstmt);
		}
	}

	// 일반 매칭 - 파티원 update
	public void updateWaitMember(String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		Timestamp now = new Timestamp(System.currentTimeMillis());

		try {
			conn = connect();
			pstmt = conn.prepareStatement(
					"update member_wait_tbl set is_wait = 1, waiting_time = ?, update_time = ?,  wouldUYN = 1 where user_id = ?");
			pstmt.setTimestamp(1, now);
			pstmt.setTimestamp(2, now);
			pstmt.setString(3, userId);
			pstmt.executeUpdate();
			System.out.println("(DAO - insertWaitMember)"+userId+" 파티원으로 update");
		} catch (Exception ex) {
			System.out.println("오류 발생 : " + ex);
		} finally {
			close(conn, pstmt);
		}
	}

}
