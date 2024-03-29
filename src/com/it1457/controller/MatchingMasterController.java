package com.it1457.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it1457.service.MatchingService;
import com.it1457.vo.LeaderVO;
import com.it1457.vo.MatchingVO;
import com.it1457.vo.MemberVO;

public class MatchingMasterController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 파티원인지 파티장인지
			String isMember = (String) request.getParameter("ismember");
			Integer headCount = getHeadCount((String) request.getParameter("headCount"), isMember);
			// String userId = (String) request.getSession().getAttribute("id"); 나중에는 사용하세요.
			String userId = (String) request.getParameter("session_id");
			String memberOk = "yes";

			String netId = "chlgkrwns";
			String netPassword = "1234";
			System.out.println(headCount);
			boolean isPossibleRegister;
			String isQuick; // 빠른 매칭?

			MatchingService service = MatchingService.getInstance();

			// 비 정상 종료 반환하기.
			if (headCount == -1) {
				HttpUtil.alertToJsp(request, response, "index.jsp", "비 정상 종료");
			}

			// 파티원 신청 (중복되는 부분이 있다면,
			if (isMember.equals(memberOk)) {
				isPossibleRegister = service.isPossibleToWaitMember(userId);// 신청할 수 있는지 없는지.
				// 파티원으로 신청할 수 있다면
				if (isPossibleRegister) {
					// 매칭테이블에 남는자리가 있는지 체크
					MatchingVO canIJoinYourMatching = service.canIJoinYourMatching();
					LeaderVO leaderVO = service.searchLeaderToMatching();
					String leaderId = null; 
					
					// 이미 존재하는 매칭에 참여
					if (canIJoinYourMatching != null) {
						int matchingId = canIJoinYourMatching.getMatching_id();
						int maxNumberOfMember = canIJoinYourMatching.getMaxNumberOfMember() - 1; // 4인 파티면 파티장을 제외하고
																									// 3명이므로, 범위(2~4)
						int presentNumberOfMember = service.getPresentMatchingMember(matchingId);
						leaderId = canIJoinYourMatching.getLeader_id();

						// 새로 들어온 파티원이 마지막(최대 인원 충족)이라면
						if (maxNumberOfMember == presentNumberOfMember + 1) {
							service.setIsFullTrue(matchingId); // matching isfull= true;
						}
						service.insertIswaitToFalseInMemberWait(userId); // member_wait_tbl에 is_wait,woulduyn를 false로 주고
						service.insertPartyMember(matchingId, leaderId, userId); // partymember테이블에 매칭테이블에 인원수 추가
						
						//매칭 알람(해야함)
						
						System.out.println("매칭 테이블에 남는 자리가 있어, 이미 존재하는 매칭에 참여");

					// 매칭테이블에 남는자리가 없다면 wait_tbl에 파티장의 대기가 있는지 확인한다.
					} else if (leaderVO != null) {
						
						//파티장이 있으면 매칭 테이블 추가 후 party_member추가 후 파티장 iswait false로 바꿈
						
						leaderId = leaderVO.getUserId();
						MatchingVO matchingVO = new MatchingVO();
						setMatchingInfo(matchingVO, leaderId, 0, headCount, leaderVO.getMaxNumberOfMember() == 2 ? true : false, true, 0, netId, netPassword);
						int matchingId = service.getMatchingId(matchingVO);
						service.updateIswaitToFalseInLeaderWait(leaderId);
						service.insertPartyMember(matchingId, leaderId, userId);
						
						//매칭 알람(해야함)
						System.out.println("매칭 테이블에 남는 자리가 없어, wait_tbl에 있는 파티장과 매칭");
						
					} else {
						// wait 테이블에 is_wait를 true로 값을 넣어 insert해준다.
						insertWaitMember(service, userId);
						
						System.out.println("매칭테이블에 자리와 wait_tbl에 대기중인 리더가 없어 member_wait에 insert");
					}

					// 신청 할 수 없다면
				} else {
					HttpUtil.alertToJsp(request, response, "views/matching/normal_matching.jsp", "이미 신청하셨습니다.");
					// 신청할 수 없다는 msg와 함께 return
				}

				// 파티장 신청
			} else {
				isPossibleRegister = service.isPossibleToWaitLeader(userId);
				// 신청할 수 있다면

				if (isPossibleRegister) {
					// 대기중인 파티원을 탐색
					ArrayList<MemberVO> waitingMember = service.searchMemberToMatching();
					int wantHeadCount = headCount - 1;
					boolean isFull = true;
					if (wantHeadCount > waitingMember.size()) { // 원하는 파티원만큼 매칭이 안됐을 때
						wantHeadCount = waitingMember.size(); // for문에서 null값을 참조하는 것을 방지
						isFull = false;
					}

					MatchingVO matchingVO = new MatchingVO();

					if (waitingMember.size() != 0) {
						// 매칭 정보 저장
						setMatchingInfo(matchingVO, userId, 0, headCount, isFull, true, 0, netId, netPassword);

						// 매칭테이블에 매칭 insert
						// 매칭 id 받아옴.
						int matchingId = service.getMatchingId(matchingVO);
						if (matchingId == -1) {
							System.out.println("matchingId 조회불가");
						}

						// 대기중인 파티원이 1~3이 있다면 있는 수 만큼 매칭.
						for (int i = 0; i < wantHeadCount; i++) {
							// 매칭된 파티원들을 party_member에 넣어주고 member_wait에 is_wait 0으로 바꿔줌
							MemberVO tempVO = waitingMember.get(i);
							service.insertPartyMember(matchingId, userId, tempVO);
							service.updateIswaitToFalseInMemberWait(tempVO);
							
							// 해당 인원들에게 매칭이 되었다고 알림(해야함)
						}

						// 매칭 테이블에 넣고, 매칭 중인 파티원 테이블에도 데이터를 넣어준다.(매칭아이디 값에 해당하는)
					} else {
						// 대기중인 파티원이 없다면 wait_tbl에 is_wait를 true로 주고 insert
						insertWaitLeader(service, userId, netId, netPassword, headCount);
					}
				// 신청할 수 없다면
				} else {
					HttpUtil.alertToJsp(request, response, "views/matching/normal_matching.jsp", "이미 신청하셨습니다.");
				}
			}
		} catch (Exception e) {
			System.out.println("MatchingMasterController 에러");
			HttpUtil.alertToJsp(request, response, "index.jsp", "알 수 없는 에러 발생");
		}

	}

	// 일반 매칭 - 파티원 insert
	public void insertWaitMember(MatchingService service, String userId) {
		service.insertMember(userId);
	}

	// 일반 매칭 - 파티장 insert
	public void insertWaitLeader(MatchingService service, String userId, String netId, String netPassword,
			int headCount) {
		service.insertLeader(userId, netId, netPassword, headCount);

	}

	// 빠른 매칭 시 파티장 insert
	public void insertQuickWaitLeader(MatchingService service, String userId, String netId, String netPassword,
			int dcPercent, int maxNOM, int howLongUse) {

	}

	// String headCount -> Integer headCount
	public Integer getHeadCount(String headCount, String isMember) {
		Integer HNOM;

		if (isMember.equals("yes")) {
			return 0;
		}

		if (headCount.equals("two")) {
			HNOM = 2;
		} else if (headCount.equals("three")) {
			HNOM = 3;
		} else if (headCount.equals("four")) {
			HNOM = 4;
		} else {
			HNOM = -1;
		}
		return HNOM;
	}

	// 매칭 테이블에 넣어줄 리더 데이터 set
	public void setMatchingInfo(MatchingVO matchingVO, String userId, int dcPercent, int headCount, boolean isFull,
			boolean isNormal, int howLongUse, String netId, String netPassword) {
		matchingVO.setLeader_id(userId);
		matchingVO.setDcPercent(0);
		matchingVO.setMaxNumberOfMember(headCount);
		matchingVO.setFull(isFull);
		matchingVO.setNormal(true);
		matchingVO.setHowLongUse(0);
		matchingVO.setNet_id(netId);
		matchingVO.setNet_password(netPassword);
	}
}
