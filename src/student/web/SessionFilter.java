/*package student.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class SessionFilter implements Filter {

    public SessionFilter() {

    }

	
	public void destroy() {

	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httprequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse=(HttpServletResponse)response;
		if(!avoidUrl(httprequest.getRequestURI()))
		{
		String userid=(String) httprequest.getSession().getAttribute("userid");
		if (userid != null) {
			chain.doFilter(request, response);
		} else {
			httpServletResponse.sendRedirect("/login");
		}

		}
		else
		{
			chain.doFilter(request, response);
		}
	}

	private boolean avoidUrl(String requestURI) {
		boolean val=false;
		if(requestURI.contains("login")||requestURI.contains("register"))
		{
			val=true;
		}
		return val;
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
*/