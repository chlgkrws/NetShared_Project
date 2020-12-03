package com.it1457.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;

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
	
	//파티원 신청자가 신청 가능한지.
	public boolean isPossibleToWaitMember(String userId) {
		Connection conn = null;
		PreparedStatement CheckWaitPstmt = null;
		PreparedStatement CheckAlreadyUsedPstmt = null;
		ResultSet rs = null;
		try {
			conn = connect();
			CheckWaitPstmt = conn.prepareStatement("select user_id, is_wait from member_wait_tbl where user_id = ?");
			CheckWaitPstmt.setString(1, userId);
			CheckWaitPstmt.execute();						
			boolean isExist = CheckWaitPstmt.getResultSet().next();  //true면 값이 있음, false면 값이 없음 || 값이 있으면 대기, 없으면 가능
			System.out.println("isExist : "+ isExist);
			
			//이미 대기열에 존재한다면
			if(isExist) {
				//대기열에 존재하지만 이미 매칭된 사람의 매칭 취소인지, 사용중인지 판별
				CheckAlreadyUsedPstmt = conn.prepareStatement("select wouldUYN from party_member_info_tbl where member_id = ?");
				CheckAlreadyUsedPstmt.setString(1, userId);
				rs = CheckAlreadyUsedPstmt.executeQuery();
				
				HashSet<Integer> checkTF = new HashSet<>();
				boolean isUsed = rs.next();				
				System.out.println("isUsed : " + isUsed);
				//여기12-03했습니다.
				//반환된 값이 있으면
				if(isUsed) {
					checkTF.add(rs.getInt(1));
					while(rs.next()) {
						System.out.println("여기에 값이있다궁?");
						checkTF.add(rs.getInt(1));
					}
					//과거 기록 + 현재 진행 매칭 = 2 = 현재 사용 중
					if(checkTF.size() == 2) {
						System.out.println("이미 대기열 존재, 이미 매칭된 유저");
						return false;
					//결과값 1 = 현재 매칭된 상태 or 파티탈퇴(이후 재 매칭 안한 경우)
					}else { 
						//v == 0은 파티 탈퇴한 상황
						for(Integer v : checkTF) {
							if(v == 0) {
								System.out.println("이미 대기열에 존재하지만 이전 파티를 탈퇴하고 다시 매칭을 신청하는 유저");
								return true;
							}
						}
					}
					System.out.println("이미 대기열에 존재하고, 이전 매칭 기록도 있지만, 비정상 종료(wouldUYN : 1, size : 1인경우");
					return false;
				}else {
					//예외 케이스
					//대기열에 존재하는 사람은 이전에 서비스를 이용했거나, 지금 대기중인가 인데, 
					//현재 조건문에 들어온 이유는 이미 대기열에 존재한다는 것이고
					//이 부분으로 들어왔다면 매칭을 한적이 없다는 것이다. 비정상 부분
					System.out.println("예외 케이스 비정상 종료");
					return false;
				}
			//대기열에 존재하지 않으면 신청 가능 true반환
			}else {
				System.out.println("대기열에 존재하지 않는 유저 ");
				return true;
			}
			
			//현재 메서드는 신청해도 되는지 안되는지 판별한다.
			//신청할 수 있으면 true 반환, 신청할 수 없으면 false 반환
			//신청할 수 있는 사람은 db에 (wait에 데이터가 아예 없거나, [**있어도, is_wait가 false여야한다.) 만약 is_wait가 true면 이미 신청한 것이기 때문에 신청할 수 없다.]
			//**is_wait가 false인 사람이라면, 현재 서비스를 사용하는지 확인해야한다. 
			//그럼 하나의 로우를 가져와서 없는 결과라면 true를 반환해주고, 있다면 is_wait의 값을 확인한다.
			//is wait가 true(대기상태)이면 false(신청불가/중복신청)를 반환

		} catch (Exception ex) {
			System.out.println("오류 발생 : " + ex);
			return false;
		} finally {
			close(conn, CheckWaitPstmt);
		}
	}
	
	/*//파티장 신청자가 신청 가능한지.
	public boolean isPossibleToWaitLeader(String userId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = connect();
			pstmt = conn.prepareStatement("select user_id, is_wait from leader_wait_tbl where user_id = ?");
			pstmt.setString(1, userId);
			boolean isExist = pstmt.execute();		//값이 있으면 대기 없으면 false
			//여기서 해야할게 이 사람은 신청해도 되는지 안되는지 판별한다.
			//신청할 수 있으면 true 반환, 신청할 수 없으면 false 반환
			//신청할 수 있는 사람은 db에 (wait에 데이터가 아예 없거나, [**있어도, is_wait가 false여야한다.) 만약 is_wait가 true면 이미 신청한 것이기 때문에 신청할 수 없다.]
			//**is_wait가 false인 사람이라면, 현재 서비스를 사용하는지 확인해야한다. 
			//그럼 하나의 로우를 가져와서 없는 결과라면 true를 반환해주고, 있다면 is_wait의 값을 확인한다.
			//is wait가 true(대기상태)이면 true(신청가능)를 반환하고
			if(!isExist) {
				return false;
			}
		} catch (Exception ex) {
			System.out.println("오류 발생 : " + ex);
			return false;
		} finally {
			close(conn, pstmt);
		}
	}*/
}
