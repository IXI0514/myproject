package com.yunsi.shop.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DownInfoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//request.setCharacterEncoding("UTF-8");
			
			String filename = new String("账单.xls".getBytes(),"iso_8859_1");//设置文件名称的编码格式
			String tableinfo = request.getParameter("tableinfo");
			
			//tableinfo = new String (tableinfo.getBytes("iso-8859-1"),"Utf-8");
			StringBuffer sb = new StringBuffer(tableinfo);//将表格信息放入内存
			System.out.println(sb);
			
			
			response.setHeader("content-disposition","attachment; filename=账单信息.xls");
			
			response.setHeader("content-disposition", "attachment;filename="+filename);//设置导出的文件名称
			response.setContentType("application/vnd.ms-excel");//设置导出文件格式 
			
			response.resetBuffer();
			ServletOutputStream sos =null;
			try {
				sos= response.getOutputStream();
				sos.write(sb.toString().getBytes());
				sos.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
				if(sos!=null) {
					sos.close();
				}
			}
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
