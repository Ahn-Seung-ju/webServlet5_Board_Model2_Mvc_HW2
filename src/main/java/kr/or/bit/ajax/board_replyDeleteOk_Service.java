package kr.or.bit.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.dao.BoardDao;

@WebServlet("/Replydeleteok")
public class board_replyDeleteOk_Service extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public board_replyDeleteOk_Service() {
        super();
    }
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String idx_fk = request.getParameter("idx");// 댓글의 원본 게시글 번호
		String no = request.getParameter("no");// 댓글의 순번(PK)
		String pwd = request.getParameter("delPwd");// 댓글의 암호
		PrintWriter out = response.getWriter();
		System.out.println(idx_fk);
		if (idx_fk == null || no == null || pwd == null || no.trim().equals("")) {
			out.print("fasle");
		}
		
		BoardDao dao;
		try {
			dao = new BoardDao();
			
			int result = dao.replyDelete(no, pwd);
			
			if(result > 0) {
				out.print("true");
			}else {
				out.print("false");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
