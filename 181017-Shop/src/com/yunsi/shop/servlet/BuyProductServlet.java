package com.yunsi.shop.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yunsi.shop.bean.ProductInfo;

public class BuyProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BuyProductServlet() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		/**
		 * 获取session中的userinfo
		 * 	验证是否有用户登录 防止非法访问
		 * 	
		 */
		HttpSession session = request.getSession();
		String userinfo = (String) session.getAttribute("userinfo");
		if(userinfo!=null) {
			/**
			 * 获取(建立)购物车(会话)
			 * 		HttpSession.setAttribute("cart",cart)
			 * 			HttpSession.getAttribute("cart")
			 * 				cart HashMap<ProductInfo, Integer>
			 */
			
			@SuppressWarnings("unchecked")
			HashMap<ProductInfo, Integer> cart = (HashMap<ProductInfo, Integer>) session.getAttribute("cart");
			//System.out.println(cart==null);
			if(cart==null) {
				cart = new HashMap<ProductInfo, Integer>();
				session.setAttribute("cart",cart);	
			}
			
			
			/**
			 * 处理请求：
			 * 		请求type1：Get-buy?pid=
			 * 		请求type2：Get-buy?pid=&pnum=   数量（更改数量）>0
			 * 	
			 */
			
			String pid =request.getParameter("pid");
			String pnum =request.getParameter("pnum");
			System.out.println("购买类型？：["+pnum+"]");
			
			ServletContext scont = this.getServletContext();
			@SuppressWarnings("unchecked")
			HashMap<String,ProductInfo> plist  =(HashMap<String,ProductInfo>) scont.getAttribute("plist");
			
			/**
			 * type1
			 * 		?购物车中是否有该物品 num+1 1
			 */
			if(pnum==null) {
				//System.out.println("存在吗？："+cart.containsKey(buyp));
				ProductInfo buyp= plist.get(pid);
				System.out.println("buyp:"+buyp);
				Integer num= cart.get(buyp);
				if(num!=null) {
					cart.put(buyp, num+1);
				}else {
					cart.put(buyp, 1);
				}
				response.sendRedirect("../shop");
			//type2
			}else {
				response.sendRedirect("cart");
			}
		}else {
			response.getWriter().println ("<script>alert('当前无用户登录,无法完成购物...');window.location='../shop'</script>");
		}
		

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
