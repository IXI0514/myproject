package com.yunsi.service.impl;

import java.util.List;

import com.yunsi.beans.Examinee;
import com.yunsi.beans.LoginResult;
import com.yunsi.beans.Question;
import com.yunsi.dao.IExamDao;
import com.yunsi.dao.impl.ExamDaoimpl;
import com.yunsi.service.IExamService;

public class ExamServiceImpl implements IExamService {
	private IExamDao ied;
	
	public ExamServiceImpl() {
		ied = new ExamDaoimpl();
	}
	
	
	@Override
	public LoginResult VerifyLogin(String username, String password) {
		String regex = "\\d{12}";
		Examinee e = new Examinee();
		e.setPassword(password);
		if(username.matches(regex)) {
			System.out.println("UID登录");
			e.setExamid(username);
			//user.setUid(username);
			return ied.VerifyLoginById(e,"examid");
		}else {
			System.out.println("Nickname登录");
			e.setEname(username);
			return ied.VerifyLoginByNick(e,"ename");
		}
	}


	@Override
	public int saveQuestion(Question q) {
		return ied.saveQuestion(q);
	}


	@Override
	public int getCount() {
		return ied.getCount();
	}


	@Override
	public List<Question> getRandomExam(int num) {
		return ied.getRandomExam(num);
	}


	@Override
	public void setNewScore(Examinee e) {
		ied.setNewScore(e);
		
	}

}
