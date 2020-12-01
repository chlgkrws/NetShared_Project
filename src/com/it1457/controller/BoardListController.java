package com.it1457.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.it1457.service.BoardService;
import com.it1457.vo.BoardVO;
import com.it1457.vo.Page;

public class BoardListController implements Controller {

	/* 게시글 목록 관리 컨트롤러 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			BoardService boardService = BoardService.getInstance();
			int num = Integer.parseInt(request.getParameter("num"));
			
			Page page = new Page();
			page.setNum(num);
			page.setCount(boardService.getBoardCount());
			
			ArrayList<BoardVO> list = boardService.getBoardList(page.getDisplayPost(), page.getPostNum());

			request.setAttribute("list", list);
			request.setAttribute("select", num);
			request.setAttribute("page", page);
			HttpUtil.forward(request, response, "views/board/community.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("BoardListController 에러");
		}
	}

}
