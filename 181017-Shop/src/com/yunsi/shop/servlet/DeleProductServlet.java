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


public class DeleProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("dele......");
		response.setContentType("text/html;charset=utf-8");
		String deleno =request.getParameter("deleno");
		System.out.println(deleno);
		String[] delenos = deleno.split("@");
		
		//获取当前购物车
		HttpSession session = request.getSession();
		@SuppressWarnings("unchecked")
		HashMap<ProductInfo, Integer> cart = (HashMap<ProductInfo, Integer>) session.getAttribute("cart");
		//获取物品信息
		ServletContext scont = this.getServletContext();
		@SuppressWarnings("unchecked")
		HashMap<String,ProductInfo> list  =(HashMap<String,ProductInfo>) scont.getAttribute("plist");
		if(delenos!=null&&delenos.length>0) {
			for(String key : delenos) {
				System.out.println("DELE:"+key);
				ProductInfo p =list.get(key);
				cart.remove(p);
			}
			
		}
		response.sendRedirect("cart");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
