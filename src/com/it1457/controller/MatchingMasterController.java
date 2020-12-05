package com.it1457.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it1457.service.MatchingService;

public class MatchingMasterController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//파티원인지 파티장인지
		String isMember = (String) request.getParameter("ismember");
		String headCount = (String) request.getParameter("headCount");
		//String userId = (String) request.getSession().getAttribute("id");		나중에는 사용하세요.
		String userId = (String) request.getParameter("session_id");
		String memberOk = "yes";
		
		
		boolean isPossibleRegister;
		String isQuick; 							//빠른 매칭?
		
		
		MatchingService service = MatchingService.getInstance();
		
		//파티원 신청 (중복되는 부분이 있다면, 
		if(isMember.equals(memberOk)) {
			isPossibleRegister = service.isPossibleToWaitMember(userId);// 신청할 수 있는지 없는지.
			//신청할 수 있다면
			if(isPossibleRegister) {
				
				//파티장이 있는지 없는지 체크
				//매칭테이블에 남는자리가 있는지 체크
				//매칭테이블에 남는자리가 있다면 wait_tbl에 is_wait를 false로 주고 매칭테이블에 인원수 추가+partymember테이블에 추가
				//매칭테이블에 남는자리가 없다면 wait_tbl에 파티장의 대기가 있는지 확인한다.
				//만약 파티장이 대기 한다면 매칭, 없다면 
				//wait 테이블에 is_wait를 true로 값을 넣어 insert해준다.
			//신청 할 수 없다면
			}else {
				HttpUtil.alertToJsp(request, response, "views/matching/normal_matching.jsp", "이미 신청하셨습니다.");
				//신청할 수 없다는 msg와 함께 return
			}
			
		//파티장 신청
		}else {
			isPossibleRegister = service.isPossibleToWaitLeader(userId);
			//신청할 수 있다면
			
			if(isPossibleRegister) {
				//대기중인 파티원을 탐색
				//대기중인 파티원이 1~3이 있다면 있는 수 만큼 매칭.
				//대기중인 파티원이 없다면 wait_tbl에 is_wait를 true로 주고 insert
			//신청할 수 없다면 
			}else {
				HttpUtil.alertToJsp(request, response, "views/matching/normal_matching.jsp", "이미 신청하셨습니다.");
			}
		}
		
	}
	
	

}
