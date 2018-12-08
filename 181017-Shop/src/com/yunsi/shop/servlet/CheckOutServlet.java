package com.yunsi.shop.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CheckOutServlet() {
        super();
    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type=request.getParameter("checktype");
		//地址 var url = "CheckOut?checktype=checkloc&checkvalue="+check;
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		
		String username = (String) session.getAttribute("userinfo");
		PrintWriter pw = response.getWriter();
		
		/*用户信息
		 * IShopService iss = new ShopServiceImpl();
		UserBean userinfo =iss.queryuser(username);*/
		
		
		System.out.println("checktype"+type);
		System.out.println("checktype"+request.getParameter("user"));
		
		
		
		if(type!=null) {
			if(type.equals("checkloc")) {
				//保存金额信息以及选择信息   checkvalue（选择结算的商品 @） gototal（金额）
				System.err.println("保存金额信息以及选择信息 "+request.getParameter("checkvalue")+":"+request.getParameter("gototal"));
				
				session.setAttribute("checkvalue", request.getParameter("checkvalue"));
				session.setAttribute("gototal", request.getParameter("gototal"));
				
				
				
				pw.println("<html >" + 
						"<head>" + 
						"    <title>结算信息</title>" + 
						"	 <link rel=\"stylesheet\" href=\"../css/main.css\">" + 
						"	 <script src=\"../js/main.js\"></script>" + 
						"</head>" + 
						"<body>" + 
						"    <div id=\"locbox\">" + 
						"        <form action=\"checkout\" method=\"post\"  onsubmit=\"return checkloc()\">" + 
						"			<input type=\"hidden\" name=\"checktype\" id=\"checktype\" value=\"payment\" />"+ 
						"            <table id=\"loctable\">" + 
						"                <caption >确认邮寄信息</caption>" +
						"                <tr>" + 
						"                    <td>收件人:</td>" + 
						"                    <td><input name=\"reuser\" id=\"reuser\" type=\"text\" value=\""+username+"\"></td>" + 
						"                </tr>" + 
						"                <tr>" + 
						"                    <td>电话:</td>" + 
						"                    <td><input name=\"phone\" id=\"phone\"  type=\"text\" ></td>" + 
						"                </tr>" + 
						"                <tr>" + 
						"                    <td>邮编:</td>" + 
						"                    <td><input name=\"postcode\" id=\"postcode\"  type=\"text\" ></td>" + 
						"                </tr>" + 
						"                <tr>" + 
						"                    <td>地址:</td>" + 
						"                    <td><input name=\"site\" id=\"site\" type=\"text\" ></td>" + 
						"                </tr>" + 
						"                <tr>" + 
						"                    <td colspan=\"2\" align=\"center\"><button type=\"submit\" >下一步</button></td>" + 
						"                </tr>" + 
						"            </table>" + 
						"        </form>" + 
						"    </div>" + 
						"</body>" + 
						"</html>");
				
			}
			//付款 CheckOut checktype=payment&checkvalue="+check
			else if(type.equals("payment")) {
				//获取并保存邮寄信息到会话中 收件人reuser  电话phone  邮编 postcode 地址 site
				request.getParameter("reuser");
				request.getParameter("phone");
				request.getParameter("postcode");
				request.getParameter("site");
				String checkloc=request.getParameter("reuser")+"@"+request.getParameter("phone")
				+"@"+request.getParameter("postcode")+"@"+request.getParameter("site");
				session.setAttribute("checkloc", checkloc);
				
				String money =(String) session.getAttribute("gototal");
				System.out.println("a:"+money);
				pw.println(
						"<html >" + 
						"<head>" + 
						"    <title>结算信息</title>" + 
						"	 <link rel=\"stylesheet\" href=\"../css/main.css\">" + 
						"	 <script src=\"../js/main.js\"></script>" + 
						"</head>" + 
						"<body>" + 
						"    <div id=\"locbox\">" + 
						"        <form action=\"cartinfo\" method=\"post\" onsubmit=\"return checkpay()\">" + 
						"            <table id=\"loctable\">" + 
						"                <caption>付款信息</caption>" + 
						"                <tr>" + 
						"                    <td>发卡行</td>" + 
						"                    <td>" + 
						"                        <select name=\"bank\" id=\"bank\">" + 
						"                            <option value=\"中国银行\">中国银行</option>" + 
						"                            <option value=\"中国农业银行\">中国农业银行</option>" + 
						"                            <option value=\"中国建设银行\">中国建设银行</option>" + 
						"                            <option value=\"中国工商银行\">中国工商银行</option>" + 
						"                            <option value=\"中国招商银行\">中国招商银行</option>" + 
						"                        </select>" + 
						"                    </td>" + 
						"                </tr>" + 
						"                <tr>" + 
						"                    <td>卡号</td>" + 
						"                    <td><input id=\"cardid\" name=\"cardid\" type=\"text\" ></td>" + 
						"                </tr>" + 
						"                <tr>" + 
						"                    <td>密码</td>" + 
						"                    <td><input id=\"password\" name=\"password\" type=\"password\" ></td>" + 
						"                </tr>" + 
						"                <tr>" + 
						"                    <td>金额</td>" + 
						"                    <td><sapn>"+money+"￥</sapn></td>" + 
						"                </tr>" + 
						"                <tr>" + 
						"                    <td colspan=\"2\" align=\"center\"><button type=\"submit\" >下一步</button></td>" + 
						"                </tr>" + 
						"            </table>" + 
						"        </form>" + 
						"    </div>" + 
						"</body>" + 
						"</html>");
				

			}else {
				response.sendError(404);
			}
			pw.close();
		}
		

	}

	
	
	
}
