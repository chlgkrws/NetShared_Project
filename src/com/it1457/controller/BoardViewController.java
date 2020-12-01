package com.it1457.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it1457.service.BoardService;
import com.it1457.vo.BoardVO;

public class BoardViewController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String preBoardId = request.getParameter("boardId"); // 파라미터로 게시물id값을 받아옴
			int boardId = Integer.parseInt(preBoardId);

			BoardService boardService = BoardService.getInstance();
			BoardVO boardVO = boardService.getView(boardId);
			request.setAttribute("board", boardVO);
			HttpUtil.forward(request, response, "views/board/post.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("BoardViewController 에러");
		}
	}

}
