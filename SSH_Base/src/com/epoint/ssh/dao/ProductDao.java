package com.epoint.ssh.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.epoint.ssh.domain.Product;

/**
 * 	商品管理的Dao层
 * @author LEGION
 *
 */
public class ProductDao extends HibernateDaoSupport{
	
	/**
	 * DAO保存商品的方法 ：save
	 */
	public void save(Product product) {
		// TODO Auto-generated method stub
		System.out.println("Dao 中的Save。。。");
		this.getHibernateTemplate().save(product);
	}

}
