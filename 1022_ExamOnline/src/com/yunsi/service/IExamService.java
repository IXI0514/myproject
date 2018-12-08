package com.yunsi.service;


import java.util.List;

import com.yunsi.beans.Examinee;
import com.yunsi.beans.LoginResult;
import com.yunsi.beans.Question;

public interface IExamService {

	LoginResult VerifyLogin(String username, String password);

	int saveQuestion(Question q);

	int getCount();

	List<Question> getRandomExam(int num);

	void setNewScore(Examinee e);

}
