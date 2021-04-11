package kr.or.bit.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionFoward;
import kr.or.bit.dao.BoardDao;

public class board_editok implements Action {

	@Override
	public ActionFoward execute(HttpServletRequest request, HttpServletResponse response) {
		String idx = request.getParameter("idx");

		String msg = "";
		String url = "";

		ActionFoward forward = new ActionFoward();

		BoardDao dao = null;
		
		if (idx == null || idx.trim().equals("")) {
			msg = "글번호를 잘못 일력하셨습니다";
			url = "/Boardlist.board";
		}
		try {
			
			dao = new BoardDao();
			int result = dao.boardEdit(request);
			System.out.println("편집중");
			if (result > 0) {
				msg = "편집 성공";
				url = "/Boardlist.board";
			} else {
				msg = "편집 실패";
				url = "/Boardedit.board?idx=" + idx;

			}
			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);

			
			forward.setPath("/board/redirect.jsp");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return forward;
	}

}
