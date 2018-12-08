package com.yunsi.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yunsi.shop.service.IShopService;
import com.yunsi.shop.service.impl.ShopServiceImpl;



/**
 * 登录响应的servlet
 * @author ShenBL
 *
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("用户名："+username+"密码："+password);
		
		
		PrintWriter pw = response.getWriter();
		
		if(username!=null&&password!=null) {
			IShopService iss = new ShopServiceImpl();
			int result =iss.login(username, password);;
		
			//result= 1 登录成功 跳转界面 将用户信息存到session中  userinfo
			if(result==1) {
				HttpSession session = request.getSession();
				session.setAttribute("userinfo", username);
				pw.println ("<script>alert('登录成功...');window.location='shop'</script>");
				
			}
			//result= 0 登录失败 
			else if (result ==0) {
				pw.println ("<script>alert('用户名密码错误...');window.location='index.html'</script>");
			}
			//result = -1 登录错误
			else {
				pw.println ("<script>alert('服务端出错...');window.location='index.html'</script>");
			}
		
		}else {
			pw.println("<script>alert('请登录...');window.location='index.html'</script>");
		}
		pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
