package objects;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Date;
import java.util.logging.Filter;


public class LogFilter implements Filter{
	
	private FilterConfig filter;
	

	public void init(FilterConfig filterConfig)
	{
		filter = filterConfig;
	}
	
	public void doFilter(ServletRequest request,
			ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		ServletContext sc = filter.getServletContext();
		
		HttpSession session = req.getSession();
		String user = (String) session.getAttribute("currentUser");
		
		Writer out = new BufferedWriter(new FileWriter("C:/tomcat/webapps/OnlinePizzaApp/logFilter.txt", true));
		
		//show log message in console
		sc.log(user + " accessed " + req.getServletPath() +  " on " + new Date());
		
		//write the log message to a file
		out.write(user + " accessed " + req.getServletPath() +  " on " + new Date() + "\n");
		
		chain.doFilter(req, res);
		out.close();
	}
	
	public void destroy()
	{
		filter = null;
	}
	
}
