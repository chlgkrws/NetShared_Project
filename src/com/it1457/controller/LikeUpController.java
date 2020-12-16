package com.it1457.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it1457.service.BoardService;

public class LikeUpController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			String userId = (String) request.getParameter("id");
			Integer boardId = Integer.parseInt((String) request.getParameter("boardId"));
			
			BoardService service = BoardService.getInstance();

			// likeUp에 있는지 체크
			int check = service.checkLike(boardId, userId);
			
			//원래 0인 게시판이라면 +1을 할 수 있도록 함.
			
			// 이미 있다면 1 없으면 0
			if (check == 1) {
				String msg = "이미 추천하셨습니다.";
				String path = "boardview.do?boardId=" + boardId;
				service.deleteLikedUser(boardId, userId);
				service.updateLikeDown(boardId);
				//HttpUtil.alertToJsp(request, response, path, msg);
				HttpUtil.redirect(request, response, "/boardview.do?boardId=" + boardId);
			// 없다면 insert(계정정보), update(게시판 추천수) 후 추천 수 증가(redirect)
			} else { 
				service.insertLikedUser(boardId, userId);						
				service.updateLike(boardId);
				HttpUtil.redirect(request, response, "/boardview.do?boardId=" + boardId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("LikeUpController 에러");
		}
	}

}
