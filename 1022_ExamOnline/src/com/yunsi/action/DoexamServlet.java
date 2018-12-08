package com.yunsi.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yunsi.beans.Examinee;
import com.yunsi.beans.PageBean;
import com.yunsi.beans.Question;
import com.yunsi.dao.IExamDao;
import com.yunsi.dao.impl.ExamDaoimpl;
import com.yunsi.service.IExamService;
import com.yunsi.service.impl.ExamServiceImpl;

/**
 * Servlet implementation class DoexamServlet
 */
@WebServlet("/exam/goexam")
public class DoexamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoexamServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//location.href="../exam/goexam?turnpage=back&answer="+answered;
		String turn =request.getParameter("turnpage");
		String answer =request.getParameter("answer");
		HttpSession session = request.getSession();
		PageBean pb = (PageBean) session.getAttribute("pagebean");
		int page=pb.getCurrentPage();
		System.out.println("map:"+page+":"+answer);
		System.out.println(answer);
		//存答案
		Map<Integer, String> map=pb.getAnswered();
		if(answer!=null&&!answer.equals("")) {
			map.put(page, answer);
		}
		//提交
		if(turn.equals("submit")) {
			
			int score=0;
			List<Question> list=pb.getList();
			System.out.println(list.size()+":"+map.size());
			for(int i=0;i<list.size();i++) {
				String str1 =list.get(i).getAnswer();
				String str2 =map.get(i);
				
				if(str1.equalsIgnoreCase(str2)) {
					
					score+=5;
				}
			}
			System.out.println("成绩："+score);
			pb.setScore(score);
			Examinee e =(Examinee) session.getAttribute("userinfo");
			int oldsc =e.getScore();
			if(oldsc<score) {
				pb.setBestscore(score);
				e.setScore(score);
				//更新成绩
				IExamService ies = new ExamServiceImpl();
				ies.setNewScore(e);
				
			}else {
				pb.setBestscore(oldsc);
			}
			response.sendRedirect("../jsp/score.jsp");
		}
		//下一题
		else {
			if( turn.equals("back")) {
				pb.setCurrentPage(page-1);
			}else if(turn.equals("go")) {
				pb.setCurrentPage(page+1);
			}
			session.setAttribute("pagebean", pb);
			response.sendRedirect("../jsp/exam.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//创建pagebean
		PageBean pb = new PageBean();
		pb.setCurrentPage(0);
		pb.setPageSize(1);
	
		String count =request.getParameter("requsetsum");
		pb.setItemsum(Integer.parseInt(count));	
		
		//获取随机题目
		IExamService ied = new ExamServiceImpl();
		List<Question> list= ied.getRandomExam(Integer.parseInt(count));
		//将题目存到session中
		pb.setList(list);
		HttpSession session = request.getSession();
		session.setAttribute("pagebean", pb);
		response.sendRedirect("../jsp/exam.jsp");
	}

}
