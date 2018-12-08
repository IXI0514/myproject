package com.yunsi.beans;

public class Question {
	private String qid;
	private String type;
	private String question;
	private String options;
	private String answer;
	private String optiona;
	private String optionb;
	private String optionc;
	private String optiond;
	
	
	
	
	
	
	public Question() {
		super();
	}
	public Question(String qid, String type, String question, String optiona, String optionb, String optionc,
			String optiond, String answer) {
		super();
		this.qid = qid;
		this.type = type;
		this.question = question;
		this.optiona = optiona;
		this.optionb = optionb;
		this.optionc = optionc;
		this.optiond = optiond;
		this.answer = answer;
	}	
	
	@Override
	public String toString() {
		return "Question [qid=" + qid + ", type=" + type + ", question=" + question + ", optiona=" + optiona
				+ ", optionb=" + optionb + ", optionc=" + optionc + ", optiond=" + optiond + ", answer=" + answer + "]";
	}
	
	
	public String getOptions() {
	
		if(this.type.equals("选择题")) {
			this.options=this.optiona+"#"+this.optionb+"#"+this.optionc+"#"+this.optiond;
		} 
		else if(this.type.equals("判断题")) {
			this.options=this.optiona+"#"+this.optionb;
		}
		return options;
	}
	
	//用于数据库查询时调用
	public void setOptions(String options) {
		String[] strs = options.split("#");
		if(strs!=null&&this.type.equals("选择题")&&strs.length==4) {
			this.optiona = strs[0];
			this.optionb = strs[1];
			this.optionc = strs[2];
			this.optiond = strs[3];
		} 
		else if(strs!=null&&this.type.equals("判断题")&&strs.length==2) {
			this.optiona = strs[0];
			this.optionb = strs[1];
		}
		this.options = options;
	}
	
	
	public String getQid() {
		return qid;
	}
	public void setQid(String qid) {
		this.qid = qid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getOptiona() {
		return optiona;
	}
	public void setOptiona(String optiona) {
		this.optiona = optiona;
	}
	public String getOptionb() {
		return optionb;
	}
	public void setOptionb(String optionb) {
		this.optionb = optionb;
	}
	public String getOptionc() {
		return optionc;
	}
	public void setOptionc(String optionc) {
		this.optionc = optionc;
	}
	public String getOptiond() {
		return optiond;
	}
	public void setOptiond(String optiond) {
		this.optiond = optiond;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	
	
}
