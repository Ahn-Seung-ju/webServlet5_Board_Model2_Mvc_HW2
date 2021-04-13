package kr.or.bit.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.dao.BoardDao;

@WebServlet("/Replyok")
public class board_replyOk_Service extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public board_replyOk_Service() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String writer = request.getParameter("reply_writer");
		String content = request.getParameter("reply_content");
		String pwd = request.getParameter("reply_pwd");
		int idx_fk = Integer.parseInt(request.getParameter("idx"));
		String userid = "empty";
		
		BoardDao dao = null;
		try {
			dao = new BoardDao();
			
			int result = dao.replywrite(idx_fk, writer, userid, content, pwd);
			
			PrintWriter out = response.getWriter();
			
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
