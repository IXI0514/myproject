package com.yunsi.shop.servlet;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yunsi.shop.bean.ProductInfo;

/**
 * Servlet implementation class ShopServlet
 */
public class ShopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ShopServlet() {
		super();

	}

	@Override
	public void init() throws ServletException {

		/**
		 * 读取文件 获得商品列表 并存放在context中 ServletContext.setAttribute("plist", plist)
		 * plist=HashMap<String,ProductInfo> 商品id 商品对象
		 */
		ServletConfig sconf = this.getServletConfig();
		ServletContext scont = this.getServletContext();
		String filepath = sconf.getInitParameter("filepath");
		String realpath = scont.getRealPath(filepath);
		System.out.println(realpath);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(realpath));
			String line = null;
			HashMap<String, ProductInfo> plist = new HashMap<String, ProductInfo>();
			while ((line = br.readLine()) != null) {
				String[] strs = line.trim().split("\\s+");
				if (strs.length == 4) {
					ProductInfo p = new ProductInfo(strs[0], strs[1], strs[2], strs[3]);
					plist.put(strs[0], p);
				}
			}
			scont.setAttribute("plist", plist);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter pw = response.getWriter();
		
		//表头
		pw.println(
				"<!DOCTYPE html>" + 
				"<html lang=\"en\">" + 
				"<head>" + 
				"    <meta charset=\"UTF-8\">" + 
				"    <title>购物商场</title>" + 
				"    <link rel=\"stylesheet\" href=\"./css/main.css\">" + 
				"    <script src=\"./js/main.js\" ></script>" + 
				"</head>" + 
				"<body>" + 
				"    <div id=\"tablebox\">" + 
				"        <table id=\"carttable\">" );
		
		HttpSession session = request.getSession();
		String userinfo = (String) session.getAttribute("userinfo");
		if(userinfo!=null) {
			pw.println("<tr align=\"left\"id=\"shopnav\"><td colspan=\"5\">欢迎用户！！"+userinfo+"</td>" );
			pw.println("<td align=\"center\" colspan=\"1\"><button onclick=\"javascript:location.href='servlet/logout'\">退出登录</button></td></tr>");
		}else {
			pw.println("<tr id=\"shopnav\"><td align=\"right\" colspan=\"6\"><button onclick=\"javascript:location.href='index.html'\">登录&nbsp&nbsp</button></td></tr>");
		}
		pw.println(
				"            <tr>" + 
				"                <td>商品详情</td>" + 
				"                <td>商品ID</td>" + 
				"                <td>商品名</td>" + 
				"                <td>单价</td>" + 
				"                <td>库存</td>" + 
				"                <td>购买</td>" + 
				"            </tr>"
				);
		
		/**
		 * 从Servlet中获取商品目录 
		 * 		ServletContext.getAttribute("plist")
		 */
		ServletContext scont = this.getServletContext();
		HashMap<String,ProductInfo> list  =(HashMap<String,ProductInfo>) scont.getAttribute("plist");
		Collection<ProductInfo> keys=list.values();
		System.out.println("加载商品数："+list.size());
		if(list!=null&&list.size()>0) {
			for(ProductInfo p:keys) {
					pw.println("<tr>" + 
							"<td><img alt=\"商品详情\" src=\"./image/dog.png\"></td>" + 
							"<td>"+p.getPid()+"</td>" + 
							"<td>"+p.getPname()+"</td>" + 
							"<td>"+p.getPprice()+"</td>" + 
							"<td>"+p.getPstore()+"</td>" + 
							"<td><a href=\"servlet/buy?pid="+p.getPid()+"\" >购买</a></td>" + 
							"</tr> ");
			}
			pw.println("<tr><td colspan=\"6\" align = \"right\"><a href=\"servlet/cart\">购物车&nbsp</a></td></tr>");
		}else {
			pw.println("<tr><td colspan=\"6\" align = \"right\">当前没有商品...</td></tr>");
		}
		pw.println("</table></div></body>");
		pw.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
