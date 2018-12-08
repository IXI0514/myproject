package com.yunsi.dao;

import java.util.List;

import com.yunsi.beans.Examinee;
import com.yunsi.beans.LoginResult;
import com.yunsi.beans.Question;

public interface IExamDao {



	LoginResult VerifyLoginById(Examinee e, String string);

	LoginResult VerifyLoginByNick(Examinee e, String string);

	int saveQuestion(Question q);
	int getCount();

	List<Question> getRandomExam(int num);

	void setNewScore(Examinee e);

}
