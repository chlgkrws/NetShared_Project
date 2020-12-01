package com.it1457.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it1457.service.BoardService;

public class BoardDeleteController implements Controller {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String preBoardId = request.getParameter("boardId");
			int boardId = Integer.parseInt(preBoardId);

			BoardService service = BoardService.getInstance();
			service.boardDelete(boardId);

			HttpUtil.redirect(request, response, "/boardList.do?num=1");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("BoardDeleteController 에러");
		}
	}

}
