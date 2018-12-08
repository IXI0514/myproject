package com.yunsi.dao.impl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.yunsi.beans.Examinee;
import com.yunsi.beans.LoginResult;
import com.yunsi.beans.PageBean;
import com.yunsi.beans.Question;
import com.yunsi.dao.IExamDao;

public class ExamDaoimpl implements IExamDao {

	@Override
	public LoginResult VerifyLoginById(Examinee examinee, String str) {
		String sql = "select count(*) from examinee where examid=? and password =?";
		LoginResult result = new LoginResult();
		try {
			BigDecimal db= DBTools.getQr().query(sql, new ScalarHandler<BigDecimal>(),examinee.getExamid(),examinee.getPassword());
			System.out.println(db.intValue());
			int code = db.intValue();
			result.setCode(code);
			if(code==1) {
				String sql2 = "select examid,ename,password,score from examinee where examid=?";
				Examinee info = DBTools.getQr().query(sql2, new BeanHandler<Examinee>(Examinee.class),examinee.getExamid());
				System.out.println("DAo:"+info);
				result.setE(info);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			result.setCode(-1);
		} 	
		return result;
	}

	@Override
	public LoginResult VerifyLoginByNick(Examinee examinee, String str) {
		String sql = "select count(*) from examinee where ename=? and password =?";
		LoginResult result = new LoginResult();
		try {
			BigDecimal db= DBTools.getQr().query(sql, new ScalarHandler<BigDecimal>(),examinee.getEname(),examinee.getPassword());
			
			System.out.println(db.intValue());
			
			int code = db.intValue();
			result.setCode(code);
			if(code==1) {
				//获取总记录数
				int count =this.getCount();
				result.setItemsum(count);
				//获取登录用户信息
				String sql2 = "select examid,ename,password,score from examinee where ename=?";
				Examinee info = DBTools.getQr().query(sql2, new BeanHandler<Examinee>(Examinee.class),examinee.getEname());
				System.out.println("DAo:"+info);
				result.setE(info);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			result.setCode(-1);
		} 	
		return result;
	}
	@Override
	public int getCount() {
		String sql1 = "select count(*) from examquestion";
		BigDecimal count;
		try {
			count = DBTools.getQr().query(sql1, new ScalarHandler<BigDecimal>());
			System.out.println("Dao:题库大小"+count);
			return count.intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;	
		
		
	}

	@Override
	public int saveQuestion(Question q) {
		String sql = "insert into examquestion(type,question,options,answer) values(?,?,?,?)";
		try {
			int result=DBTools.getQr().update(sql, q.getType(),q.getQuestion(),q.getOptions(),q.getAnswer());
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public List<Question> getRandomExam(int num) {
		String sql ="select qid,type,question,options,answer from ( select * from examquestion order by dbms_random.value ) where rownum<=?";
		try {
			List<Question> result = DBTools.getQr().query(sql,new BeanListHandler<Question>(Question.class),num);
			//System.out.println(result.size()+":"+result);
			return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setNewScore(Examinee e) {
		String sql ="update  examinee set score=? where examid=? ";
		try {
			DBTools.getQr().update(sql,e.getScore(),e.getExamid());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	

}
