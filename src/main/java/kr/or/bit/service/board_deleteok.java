package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionFoward;
import kr.or.bit.dao.BoardDao;

public class board_deleteok implements Action {

	@Override
	public ActionFoward execute(HttpServletRequest request, HttpServletResponse response) {
		String idx = request.getParameter("idx");
		String pwd = request.getParameter("pwd");
		
		BoardDao dao;
		
		
		int result;
		try {
			dao = new BoardDao();
			result = dao.deleteOk(idx, pwd);//service.board_Delete(idx, pwd);
			String msg="";
			String url="";
			if(result > 0){
				msg="삭제 성공";
				url="/Boardlist.board";
			}else{
				msg="삭제 실패";
				url="/Boardlist.board";
			}
			request.setAttribute("board_msg",msg);
			request.setAttribute("board_url",url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ActionFoward forward = new ActionFoward();
		forward.setRedirect(false);
		forward.setPath("/board/redirect.jsp");
		
		
		return forward;
	}

}
