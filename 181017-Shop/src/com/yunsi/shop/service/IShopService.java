package com.yunsi.shop.service;



import java.util.List;

import com.yunsi.shop.bean.ProductInfo;
import com.yunsi.shop.bean.UserBean;

public interface IShopService {
	/**
	 * 用户登录验证
	 * @param user
	 * @return
	 * 		1	登录成功
	 * 		0	登录失败
	 * 	 	-1	 登录错误
	 */
	int login(String username, String password);

	UserBean queryuser(String username);
	


}
