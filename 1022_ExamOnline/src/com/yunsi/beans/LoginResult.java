package com.yunsi.beans;

public class LoginResult {
	private int code;
	private Examinee e;
	private int Itemsum;//总记录数
	
	
	public LoginResult() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoginResult(int code, Examinee e) {
		super();
		this.code = code;
		this.e = e;
	}
	
	
	public int getItemsum() {
		return Itemsum;
	}
	public void setItemsum(int itemsum) {
		Itemsum = itemsum;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Examinee getE() {
		return e;
	}
	public void setE(Examinee e) {
		this.e = e;
	}
	
	
}
