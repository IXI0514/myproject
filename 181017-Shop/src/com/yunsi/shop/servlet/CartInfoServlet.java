package com.yunsi.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yunsi.shop.bean.ProductInfo;

/**
 * Servlet implementation class SaveInfoServlet
 */
public class CartInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//request.setCharacterEncoding("UTF-8");
		//response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		
		HttpSession session =request.getSession(); 
		//支付信息到会话中 银行bank  卡号cardid  密码 password
		String bank = request.getParameter("bank");
		String cardid = request.getParameter("cardid");
		String password = request.getParameter("password");
		
		
		
		session.setAttribute("checkpay", bank+"@"+cardid+"@"+password);
		
		
		
		//金额信息以及选择信息   checkvalue（选择结算的商品 @） gototal（金额）
		String checkvalue =(String) session.getAttribute("checkvalue");
		String gototal =(String) session.getAttribute("gototal");
		
		//bank = new String (bank.getBytes("iso-8859-1"),"Utf-8");
		//checkvalue = new String (checkvalue.getBytes("iso-8859-1"),"Utf-8");
		
		String[] strs = checkvalue.split("@");
		
		
		//邮寄信息  checkloc：收件人reuser  电话phone  邮编 postcode 地址 site

		String checkloc =(String) session.getAttribute("checkloc");
		//checkloc = new String (checkloc.getBytes("iso-8859-1"),"Utf-8");	
		String[] locs = checkloc.split("@");
		System.out.println("checkloc:"+checkloc);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println(
				"<!DOCTYPE html>" + 
				"<html >" + 
				"<head>" + 
				"    <title>结算信息</title>" + 
				"	 <link rel=\"stylesheet\" href=\"../css/main.css\">" + 
				"	 <script src=\"../js/main.js\"></script>" + 
				"</head>" + 
				"<body>" + 
				"<div id=\"detailbox\">" + 
				"    <form action=\"downinfo\" method=\"post\" onsubmit=\"savetableinfo()\">" + 
				"	 <span id=\"tablespan\">	"+
				"    <table id=\"detailtable\" cellspacing=\"0\">" + 
				"        <caption >购物详细清单</caption>" + 
				"        <tr>" + 
				"            <th colspan=\"4\" align=\"left\">商品清单：</th>" + 
				"        </tr>" + 
				"        <tr>" + 
				"            <td>商品名称</td><td>单价</td><td>数量</td><td>小计</td>" + 
				"        </tr>" );
		ServletContext sc = this.getServletContext();
		@SuppressWarnings("unchecked")
		HashMap<ProductInfo, Integer> cart = (HashMap<ProductInfo, Integer>) session.getAttribute("cart");
		@SuppressWarnings("unchecked")
		HashMap<String,ProductInfo> list  =(HashMap<String,ProductInfo>) sc.getAttribute("plist");
		
		
		for(String pid:strs) {
			ProductInfo p= list.get(pid);
			Integer num = cart.get(p);
			pw.println(
					"        <tr>" + 
					"            <td>"+p.getPname()+"</td><td>"+p.getPprice()+"</td><td>"+p.getPprice()+"</td>"+
					"			 <td>"+Double.parseDouble(p.getPid())*num+"</td>" + 
					"        </tr>"
					);
		}
		
		
				pw.println(
				"        <tr>" + 
				"            <td>合计：</td>" + 
				"            <td colspan=\"3\"><span>"+gototal+"￥</span></td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"            <th colspan=\"4\" align=\"left\">付款信息：</th>" + 
				"        </tr>" + 
				"        <tr>" + 
				"            <td>发卡行：</td>" + 
				"            <td colspan=\"3\"><span>"+bank+"</span></td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"            <td>卡号：</td>" + 
				"            <td colspan=\"3\"><span>"+cardid+"</span></td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"            <td>密码：</td>" + 
				"            <td colspan=\"3\"><span>"+password+"</span></td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"            <th colspan=\"4\" align=\"left\">寄件信息：</th>" + 
				"        </tr>" + 
				"        <tr>" + 
				"            <td>收件人：</td>" + 
				"            <td colspan=\"3\"><span>"+locs[0]+"</span></td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"            <td>电话号码：</td>" + 
				"            <td colspan=\"3\"><span>"+locs[1]+"</span></td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"            <td>邮编：</td>" + 
				"            <td colspan=\"3\"><span>"+locs[2]+"</span></td>" + 
				"        </tr>" + 
				"        <tr>" + 
				"            <td>地址：</td>" + 
				"            <td colspan=\"3\"><span>"+locs[3]+"</span></td>" + 
				"        </tr>" + 
				"    </table>" +
				"    </span><br/>" +
				
				"    <table>" +
				"        <tr>" + 
				"            <td><button onclick=\"javascript:location.href='logout'\">退出应用</button></td>" + 
				"			<input type=\"hidden\" name=\"tableinfo\" id=\"tableinfo\" value=\"\" />"+   
				"            <td><button type=\"submit\" >下载清单</button></td>" + 
				"        </tr>" + 
				"    <table>" +
				"</div>" + 
				"</body>" + 
				"</from>" + 
				"</html>");
		
		
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
