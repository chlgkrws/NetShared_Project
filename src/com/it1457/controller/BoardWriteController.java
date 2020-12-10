package com.it1457.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it1457.service.BoardService;
import com.it1457.vo.BoardVO;

public class BoardWriteController implements Controller {

	/* 게시물 작성 컨트롤러 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String title = request.getParameter("title");
			String preStarRate = request.getParameter("star");
			String genre = request.getParameter("genre");
			String content = request.getParameter("modal_content");
			String id = request.getParameter("id");
			String userName = request.getParameter("username");

			// 글쓰기인지 수정인지 체크
			boolean isModify = request.getParameter("valid").equals("true") ? true : false;

			int starRate = Integer.parseInt(preStarRate);
			BoardVO boardVO = new BoardVO(); // BoardVO에 파라미터로 받은 데이터를 넣어줌
			boardVO.setUserId(id);
			boardVO.setWriter(userName);
			boardVO.setTitle(title);
			boardVO.setGenre(genre);
			boardVO.setStarRate(starRate);
			boardVO.setContent(content);
			boardVO.setValid(true);

			BoardService boardService = BoardService.getInstance();
			
			if (isModify) {
				int boardId = Integer.parseInt(request.getParameter("boardId"));
				String writer = request.getParameter("writer");
				boardVO.setBoardId(boardId);
				boardVO.setWriter(writer);
				boardService.boardUpdate(boardVO);
			} else {
				boardService.boardWrite(boardVO);
			}
			HttpUtil.redirect(request, response, "/boardlist.do?num=1"); // 게시물목록으로 이동시켜주는 컨트롤러로 이동
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("BoardWrite Controller 에러");
		}
	}

}
