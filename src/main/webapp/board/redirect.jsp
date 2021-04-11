
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  String msg = (String)request.getAttribute("board_msg");
  String url = (String)request.getAttribute("board_url");
  
  
  
  if(msg != null && url != null){
%>
	<script>
		alert('<%= msg%>');		
	    location.href='<%=request.getContextPath() + url%>';
	</script>
	
<%	  
  }else if(msg == null && url == null){
%>
	<script>
		location.href='<%=request.getContextPath() +"/Boardlist.board"%>'
	</script>
<%
	  
  }
%>