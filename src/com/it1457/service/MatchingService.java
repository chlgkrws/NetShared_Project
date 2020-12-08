package com.it1457.service;

import java.util.ArrayList;

import com.it1457.dao.MatchingDAO;
import com.it1457.vo.LeaderVO;
import com.it1457.vo.MemberVO;

public class MatchingService {
	private static MatchingService service = new MatchingService();
	public MatchingDAO dao = MatchingDAO.getInstance();

	private MatchingService() {
	}

	public static MatchingService getInstance() {
		return service;
	}

	// 파티원으로 신청가능 한지 체크
	public boolean isPossibleToWaitMember(String userId) {
		return dao.isPossibleToWaitMember(userId);
	}

	// 파티장으로 신청가능 한지 체크
	public boolean isPossibleToWaitLeader(String userId) {
		return dao.isPossibleToWaitLeader(userId);
	}

	// 파티원 insert
	public void insertMember(String userId) {
		boolean isExist = dao.isExistWaiting(userId, "member_wait_tbl");

		if (!isExist) {
			dao.insertWaitMember(userId);
		} else {
			dao.updateWaitMember(userId);
		}
	}

	// 파티장 insert
	public void insertLeader(String userId, String netId, String netPassword, int HNOM) {
		boolean isExist = dao.isExistWaiting(userId, "leader_wait_tbl");

		if (!isExist) {
			dao.insertWaitLeader(userId, netId, netPassword, HNOM);
		} else {
			dao.updateWaitLeader(userId, netId, netPassword, HNOM);
		}
	}

	//대기 중인 파티장 조회
	public LeaderVO searchLeaderToMatching() {
		return dao.searchLeaderToMatching();
	}
	
	//대기 중인 파티원 조회
	public ArrayList<MemberVO> searchMemberToMatching() {
		return dao.serachMemberToMatching();
	}


}
