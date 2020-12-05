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
}
