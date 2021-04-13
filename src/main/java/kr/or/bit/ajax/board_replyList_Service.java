package kr.or.bit.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Reply;

@WebServlet("/Replylist")
public class board_replyList_Service extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public board_replyList_Service() {
		super();
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	response.setContentType("text/html;charset=UTF-8");
    	
		String idx = request.getParameter("idx");
		BoardDao dao;
		try {
			dao = new BoardDao();
			
			PrintWriter out = response.getWriter();
			
			List<Reply> replylist = dao.replylist(idx);
			
			String tr = "";
			
			for(Reply list : replylist) {
				
				tr += "<tr align='left'>";
				tr += "<td width='80%'>" + "[ " + list.getWriter()+ " ] : " + list.getContent() + "<br>" + " 작성일: " + list.getWritedate() + "</td>";
				tr += "<td width='20%'><form action='' method='POST' name='replyDel'><input type='hidden' name='no'value="+ list.getNo() 
						+"><input type='hidden' name='idx' value= "
						+ list.getIdx_fk() + ">password :<input type='password' name='delPwd' size='4'><input type='button' value='삭제' onclick = 'reply_del(this.form)'>"
						+"</form></td></tr>";
			}
			
			
			out.print(tr);
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
