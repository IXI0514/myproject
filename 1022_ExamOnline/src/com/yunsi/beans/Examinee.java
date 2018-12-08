package com.yunsi.beans;

public class Examinee {
	private String examid;
	private String ename;
	private String password;
	private int score;
	
	
	public Examinee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public Examinee(String examid, String ename, String password, int score) {
		super();
		this.examid = examid;
		this.ename = ename;
		this.password = password;
		this.score = score;
	}


	@Override
	public String toString() {
		return "Examinee [examid=" + examid + ", ename=" + ename + ", password=" + password + ", score=" + score + "]";
	}


	public String getExamid() {
		return examid;
	}
	public void setExamid(String examid) {
		this.examid = examid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
	
}
