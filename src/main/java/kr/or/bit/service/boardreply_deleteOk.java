package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionFoward;
import kr.or.bit.dao.BoardDao;

public class boardreply_deleteOk implements Action {

	@Override
	public ActionFoward execute(HttpServletRequest request, HttpServletResponse response) {
		String idx_fk = request.getParameter("idx");// 댓글의 원본 게시글 번호
		String no = request.getParameter("no");// 댓글의 순번(PK)
		String pwd = request.getParameter("delPwd");// 댓글의 암호
		
		String msg = "";
		String url = "";
		
		if (idx_fk == null || no == null || pwd == null || no.trim().equals("")) {
			msg = "글 번호 및 비밀 번호가 잘못되었습니다";
			url = "/Boardcontent.board?idx=" + idx_fk; 
		}

		// parameter 정상인 경우
		ActionFoward forward = new ActionFoward();
		BoardDao dao;
		
		try {
			dao = new BoardDao();
			int result = dao.replyDelete(no, pwd);
			
			
			if (result > 0) {
				msg = "댓글 삭제 성공";
				url = "/Boardcontent.board?idx=" + idx_fk;
			} else {
				msg = "댓글 삭제 실패";
				url = "/Boardcontent.board?idx=" + idx_fk;
			}

			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
			
			forward.setPath("/board/redirect.jsp");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		// 처리하는 코드
		

		
		return forward;
	}

}
