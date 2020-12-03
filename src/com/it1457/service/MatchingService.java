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

	// 글 작성
	public boolean isPossibleToWaitMember(String userId) {
		return dao.isPossibleToWaitMember(userId);
	}
}
