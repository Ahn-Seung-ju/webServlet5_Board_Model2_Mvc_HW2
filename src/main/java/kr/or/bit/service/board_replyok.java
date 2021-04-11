package kr.or.bit.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionFoward;
import kr.or.bit.dao.BoardDao;

public class board_replyok implements Action {

	@Override
	public ActionFoward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String writer = request.getParameter("reply_writer");
		String content = request.getParameter("reply_content");
		String pwd = request.getParameter("reply_pwd");
		int idx_fk = Integer.parseInt(request.getParameter("idx"));
		String userid = "empty";
		//service 객체 생성
		
		BoardDao dao;
		ActionFoward forward = new ActionFoward();
		
		try {
			dao = new BoardDao();
			int result = dao.replywrite(idx_fk, writer, userid, content, pwd);
			//처리하는 코드
		 	String msg="";
		    String url="";
		    
		    if(result > 0){
		    	msg ="댓글 입력 성공";
		    	url ="/Boardcontent.board?idx="+idx_fk;
		    }else{
		    	msg="댓글 입력 실패";
		    	url="/Board_content.board?idx="+idx_fk;
		    }
		    
		    request.setAttribute("board_msg",msg);
		    request.setAttribute("board_url", url);
		    
		    forward.setPath("/board/redirect.jsp");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		return forward;
	}

}
