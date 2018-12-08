package com.yunsi.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yunsi.beans.Examinee;
import com.yunsi.beans.LoginResult;
import com.yunsi.service.IExamService;
import com.yunsi.service.impl.ExamServiceImpl;
/**
 * 负责用户登录验证的servlet
 * @author ShenBL
 *
 */
@WebServlet("/exam/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public LoginServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getParameter("username");
		request.getParameter("password");
		
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println("用户名："+username+"密码："+password);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		
		if(username!=null&&password!=null) {
			IExamService ies = new ExamServiceImpl();
			LoginResult result =ies.VerifyLogin(username, password);;
			int resultcode = result.getCode();
			//result= 1 登录成功 跳转界面 将用户信息存到session中  userinfo
			if(resultcode==1) {
				Examinee e=result.getE();
				int itemsum =result.getItemsum();
				
				HttpSession session = request.getSession();
				session.setAttribute("itemsum", itemsum);
				session.setAttribute("userinfo", e);
				System.out.println("登录成功："+e);
				pw.println ("<script>alert('登录成功...');window.location='../jsp/index.jsp'</script>");
				
			}
			//result= 0 登录失败 
			else if (resultcode ==0) {
				pw.println ("<script>alert('用户名密码错误...');window.location='../login.html'</script>");
			}
			//result = -1 登录错误
			else {
				pw.println ("<script>alert('服务端出错...');window.location='../login.html'</script>");
			}
		
		}else {
			pw.println("<script>alert('请登录...');window.location='login.html'</script>");
		}
		pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
