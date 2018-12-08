package com.yunsi.shop.bean;

public class ProductInfo {
	@Override
	public String toString() {
		return "ProductInfo [pid=" + pid + ", pname=" + pname + ", pprice=" + pprice + ", pstore=" + pstore + "]";
	}
	private String pid;
	private String pname;
	private String pprice;
	private String pstore;
	

	public ProductInfo(String pid, String pname, String pprice, String pstore) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.pprice = pprice;
		this.pstore = pstore;
	}
	public ProductInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getPprice() {
		return pprice;
	}
	public void setPprice(String pprice) {
		this.pprice = pprice;
	}
	public String getPstore() {
		return pstore;
	}
	public void setPstore(String pstore) {
		this.pstore = pstore;
	}
	
	
	
	
	
}
