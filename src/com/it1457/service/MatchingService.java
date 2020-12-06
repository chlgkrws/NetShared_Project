package com.it1457.service;

import com.it1457.dao.MatchingDAO;

public class MatchingService {
	private static MatchingService service = new MatchingService();
	public MatchingDAO dao = MatchingDAO.getInstance();

	private MatchingService() {
	}

	public static MatchingService getInstance() {
		return service;
	}

	//파티원으로 신청가능 한지 체크
	public boolean isPossibleToWaitMember(String userId) {
		return dao.isPossibleToWaitMember(userId);
	}
	
	//파티장으로 신청가능 한지 체크
	public boolean isPossibleToWaitLeader(String userId) {
		return dao.isPossibleToWaitLeader(userId);
	}
	
	//파티원 insert
	public void insertMember(String userId) {
		boolean isExist = dao.isExistWaiting(userId, "member_wait_tbl");
		
		if(!isExist) {
			dao.insertWaitMember(userId);
		}else {
			dao.updateWaitMember(userId);
		}
	}
}
