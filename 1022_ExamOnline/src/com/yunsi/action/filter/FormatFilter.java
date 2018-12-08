package com.yunsi.action.filter;

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

/**
 * 调整请求的编码格式
 * @author ShenBL
 *
 */

public class FormatFilter implements Filter {
	private String encoding;

    public FormatFilter() {
    }
    public void init(FilterConfig fConfig) throws ServletException {
		this.encoding = fConfig.getInitParameter("encoding");
		System.out.println("获取统一编码格式："+encoding);
		
	}
	public void destroy() {
		
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		System.out.println("[F:Formatfilter]");
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		HttpServletResponse req = (HttpServletResponse)response;
		//设置响应头：cache-control,expires,pragma
		req.setHeader("cache-control", "no-cache");
		req.setHeader("cache-control", "no-store");
		req.setHeader("pragma", "no-cache");
		chain.doFilter(request, response);
	}
	
	
	

}
