package com.epoint.ssh.action;

import com.epoint.ssh.domain.Product;
import com.epoint.ssh.service.ProductService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 	商品管理的Action类
 * @author LEGION
 *
 */
public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//^extends ActionSupport implements ModelDriven<Product> (模型驱动的接口)
	//模型驱动使用的类
	private Product product = new Product();
	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	
	/*
	 * 	注入Service  struts2-spring-plugin-2.3.36.jar > struts-plugin.xml > struts.objectFactory
			>struts.objectFactory.spring.autoWire = name 自动装配
	 */	
	//Struts 和Spring 整合过程中按名字自动注入业务层的类
	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	/**
	 * 保存商品的方法 ：save
	 */
	public String save() {
		System.out.println("ACtion中的Save。。。。");
		System.out.println(product);
		
		if (product.getPname()!=null&&product.getPname().length()!=0) {
			productService.save(product);
		}else {
			System.err.println("不能为空！！");
		}
		return NONE;
	}
}
