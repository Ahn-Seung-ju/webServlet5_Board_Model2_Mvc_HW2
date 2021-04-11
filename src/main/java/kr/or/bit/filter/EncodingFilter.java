package kr.or.bit.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(
		description = "어노테이션 활용 및 적용하기", 
		urlPatterns = { "/*" }, 
		initParams = { 
				@WebInitParam(name = "encoding", value = "UTF-8", description = "모든 페이지 한글처리")
		})
public class EncodingFilter implements Filter {
	
	private String enconding;
	
    public EncodingFilter() {
    	
    }
    
    public void init(FilterConfig fConfig) throws ServletException {
    	this.enconding = fConfig.getInitParameter("encoding");
    	System.out.println("Filter init: " + this.enconding);
	}
    
	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		if(request.getCharacterEncoding() == null) {
			request.setCharacterEncoding(this.enconding);
		}
		System.out.println(this.enconding);
		chain.doFilter(request, response);
	}

	

}
