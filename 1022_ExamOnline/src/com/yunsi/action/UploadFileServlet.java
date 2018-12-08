package com.yunsi.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.yunsi.beans.Question;
import com.yunsi.service.IExamService;
import com.yunsi.service.impl.ExamServiceImpl;

 
@WebServlet("/exam/upload")
@MultipartConfig
public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UploadFileServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		Collection<Part> parts = request.getParts();
		
		System.out.println("请求上传文件！！"+(parts==null)+parts.size());
		if(parts!=null) {
			for(Part part : parts) {
				String name = part.getName();//name属性值
				System.out.println("name="+name);
				if(part!=null) {
					String contentType = part.getHeader("content-type");
					if(contentType!=null &&!("".equals(contentType.trim()))) {
						//上传文件
						String content = part.getHeader("content-disposition");
						System.out.println("content:"+content);
						String str = "filename=\"";
						
						int idx =content.indexOf(str);
						String filename = content.substring(idx+str.length(),content.length()-1);
						
						System.out.println("文件名称："+filename+" 大小："+part.getSize());
						InputStream in =part.getInputStream();
						BufferedReader br = new BufferedReader(new InputStreamReader(in,"GBK"));
						String line = null;
						Question q = new Question();
						int result=0;
						int linenum=0;
						IExamService ies = new ExamServiceImpl();
						while((line=br.readLine())!=null) {
							//System.out.println(line);
							line=line.trim();

							if(line.matches("\\d{1,2}.*")) {
								String question= line.substring(line.indexOf(".")+1).trim();
								q.setQuestion(question);
							}else if(line.matches("^A.*")){
								//line= line.substring(line.indexOf("A")+1).trim();
								q.setOptiona(line);
							}else if(line.matches("^B.*")){
								q.setOptionb(line);
							}else if(line.matches("^C.*")){
								q.setOptionc(line);
							}else if(line.matches("^D.*")){
								q.setOptiond(line);
							}else if(line.matches("^[a-d]$")){
								linenum++;
								q.setAnswer(line);
								if(q.getOptionc()!=null&&q.getOptiond()!=null) {
									q.setType("选择题");
								}else {
									q.setType("判断题");
								}
								//存入数据库
								/*int a=ies.saveQuestion(q);
								if(a>0) {
									result++;
								}else {
									System.out.println("存入错误！！");
								}*/
								q = new Question();	
							}	
						}
						System.out.println("共"+linenum+"行，成功添加"+result+"行");
						
						//part.write("F:/temp"+filename);
					}
				}
			}
		}
		IExamService ies = new ExamServiceImpl();
		int itemsum =ies.getCount();
		HttpSession session = request.getSession();
		session.setAttribute("itemsum", itemsum);
		response.sendRedirect("../jsp/index.jsp");;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
