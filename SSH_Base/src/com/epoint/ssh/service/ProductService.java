package com.epoint.ssh.service;
/**
 * 	商品管理的业务层
 * @author LEGION
 *
 */

import org.springframework.transaction.annotation.Transactional;

import com.epoint.ssh.dao.ProductDao;
import com.epoint.ssh.domain.Product;

@Transactional
public class ProductService {
	
	//业务层 注入Dao的类
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	/**
	 * 保存商品的方法 ：save
	 */
	public void save(Product product) {
		System.out.println("Service 中的Save");
		productDao.save(product);
		
	}
	
}
