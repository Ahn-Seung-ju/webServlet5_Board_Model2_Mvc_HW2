package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionFoward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;

public class board_rewriteok implements Action {

	@Override
	public ActionFoward execute(HttpServletRequest request, HttpServletResponse response) {
		
		int idx = Integer.parseInt(request.getParameter("idx")) ;
		String writer = request.getParameter("writer");
		String subject = request.getParameter("subject");
		String email = request.getParameter("email");
		String homepage = request.getParameter("hompage");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		String filename = request.getParameter("filename");
		
		String cpage = request.getParameter("cp"); // current page
		String pagesize = request.getParameter("ps"); // pagesize
		
		Board boarddata = new Board(idx, writer, pwd, subject, content, null, 0, filename, 0, homepage, email, 0, 0, 0);
		
		ActionFoward forward = new ActionFoward();;
		BoardDao dao = null;
		
		
		
		try {
			dao = new BoardDao();
			int result = dao.reWriteOk(boarddata);

			// list 이동시 현재 pagesize , cpage
			
			// 코드는 필요에 따라서 url ="board_list.jsp?cp=<%=cpage";
			String msg = "";
			String url = "";
			if (result > 0) {
				msg = "답글 등록 성공";
				url = "/Boardlist.board?cp=" + cpage + "&ps=" + pagesize;
			} else {
				msg = "답글 등록 실패";
				url = "/Boardlist.board?cp=" + cpage + "&ps=" + pagesize;;
			}

			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
			
			forward.setPath("/board/redirect.jsp");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		

		return forward;
	}

}
