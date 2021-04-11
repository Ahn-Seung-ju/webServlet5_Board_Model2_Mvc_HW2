package kr.or.bit.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionFoward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;

public class board_writeok implements Action{

	@Override
	public ActionFoward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String subject = request.getParameter("subject");
		String writer = request.getParameter("writer");
		String email = request.getParameter("email");
		String homepage = request.getParameter("homepage");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		String filename = request.getParameter("filename");
		
		Board boarddata = new Board(0, writer, pwd, subject, content, null, 0, filename, 0, homepage, email, 0, 0, 0);
		
		ActionFoward forward = new ActionFoward();
		
		
		try {
			
			
			
			
			BoardDao dao = new BoardDao();
			int result = dao.writeok(boarddata);
			
			
				String msg="";
			    String url="";
			    
			    if(result > 0){
			    	msg = "등록성공";
			    	url = "/Boardlist.board";
			    }else{
			    	msg="등록실패";
			    	url="/Boardwrite.board";
			    }
			    System.out.println(url);
			    request.setAttribute("board_msg",msg);
			    request.setAttribute("board_url", url);
			    
			    forward.setPath("/board/redirect.jsp");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
