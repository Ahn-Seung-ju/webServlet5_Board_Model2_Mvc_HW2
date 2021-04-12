package kr.or.bit.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionFoward;
import kr.or.bit.service.board_deleteok;
import kr.or.bit.service.board_editok;
import kr.or.bit.service.board_replyok;
import kr.or.bit.service.board_rewriteok;
import kr.or.bit.service.board_writeok;
import kr.or.bit.service.boardreply_deleteOk;

@WebServlet("*.board")
public class boardFrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public boardFrontController() {
        super();
    }
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
    	
    	
    	Action action = null;
    	ActionFoward forward = null;
    	
    	
    	
    	
    	//게시판 리스트
    	if(url_Command.equals("/Boardlist.board")) {
    		//UI 이동
    		forward = new ActionFoward();
    		forward.setPath("/board/board_list.jsp");
    		
    	//글쓰기 페이지 이동
    	}else if(url_Command.equals("/Boardwrite.board")) {
    		forward = new ActionFoward();
    		forward.setPath("/board/board_write.jsp");
    		
    	//글쓰기 확인
    	}else if(url_Command.equals("/Boardwriteok.board")) {
    		action = new board_writeok();
    		forward = action.execute(request, response);
    	}
    	
    	//게시판 상세보기
    	else if(url_Command.equals("/Boardcontent.board")) {
    		forward = new ActionFoward();
    		forward.setPath("/board/board_content.jsp");
    	}
    	
    	//편집하기
    	else if(url_Command.equals("/Boardedit.board")) {
    		System.out.println("편집");
    		forward = new ActionFoward();
    		forward.setPath("/board/board_edit.jsp");
    	}
    	
    	//편집확인하기
    	else if(url_Command.equals("/Boardeditok.board")) {
    		System.out.println("편집확인");
    		action = new board_editok();
    		forward = action.execute(request, response);
    	}
    	
    	//삭제하기
    	else if(url_Command.equals("/Boarddelete.board")) {
    		forward = new ActionFoward();
    		forward.setRedirect(false);
    		forward.setPath("/board/board_delete.jsp");
    	
    		//삭제 확인하기
    	}else if(url_Command.equals("/Boarddeleteok.board")) {
    		action = new board_deleteok();
    		forward = action.execute(request, response);
    	}
    	
    	//답글달기
    	else if(url_Command.equals("/Boardrewrite.board")) {
    		forward = new ActionFoward();
    		forward.setPath("/board/board_rewrite.jsp");
    	}
    	
    	//답글 확인하기
    	else if(url_Command.equals("/Boardrewriteok.board")) {
    		action = new board_rewriteok();
    		forward = action.execute(request, response);
    		
    	}
    	
    	//댓글달기
    	else if(url_Command.equals("/Boardreplyok.board")) {
    		action = new board_replyok();
    		forward = action.execute(request, response);
    	}
    	
    	//댓글삭제
    	else if(url_Command.equals("/Boardreplydeletok.board")) {
    		action = new boardreply_deleteOk();
    		forward = action.execute(request, response);
    	}
    	
    	
    	System.out.println(forward.getPath());
    	
    	if(forward != null) {
    		if(forward.isRedirect()) { //true 
    			response.sendRedirect(forward.getPath());
    		}else {
    			RequestDispatcher dis  = request.getRequestDispatcher(forward.getPath());
    			dis.forward(request, response);
    		}
    	}
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
