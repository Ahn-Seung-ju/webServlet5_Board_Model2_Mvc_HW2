package kr.or.bit.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.bit.action.Action;
import kr.or.bit.action.ActionFoward;
import kr.or.bit.dao.BoardDao;
import kr.or.bit.dto.Board;

public class board_writeok implements Action {

	@Override
	public ActionFoward execute(HttpServletRequest request, HttpServletResponse response) {

		//String subject = request.getParameter("subject");
		//String writer = request.getParameter("writer");
		//String email = request.getParameter("email");
		//String homepage = request.getParameter("homepage");
		//String content = request.getParameter("content");
		//String pwd = request.getParameter("pwd");
		//String filename = request.getParameter("filename");

		String realFolder = "";
		String saveFolder = "upload";
		int filesize = 10 * 1024 * 1024;// 10M
		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		//realFolder = request.getRealPath(saveFolder);

		Board boarddata = null;
		ActionFoward forward = new ActionFoward();
		
		try {
			MultipartRequest multi = null;
			multi = new MultipartRequest(
											request,
											realFolder,
											filesize,
											"UTF-8",
											new DefaultFileRenamePolicy()
										);
			//(0, writer, pwd, subject, content, null, 0, filename, 0, homepage, email, 0, 0, 0);
			
			String subject = multi.getParameter("subject");
			String writer = multi.getParameter("writer");
			String email = multi.getParameter("email");
			String homepage = multi.getParameter("homepage");
			String content = multi.getParameter("content");
			String pwd = multi.getParameter("pwd");
			String filename = multi.getFilesystemName((String) multi
					.getFileNames().nextElement());
			
			boarddata = new Board(0, writer, pwd, subject, content, null, 0, filename, 0, homepage, email, 0, 0, 0);
			
			
			BoardDao dao = new BoardDao();
			int result = dao.writeok(boarddata);

			String msg = "";
			String url = "";

			if (result > 0) {
				msg = "등록성공";
				url = "/Boardlist.board";
			} else {
				msg = "등록실패";
				url = "/Boardwrite.board";
			}
			System.out.println(url);
			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);

			forward.setPath("/board/redirect.jsp");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return forward;
	}

}
