package com.yunsi.shop.dao;


import com.yunsi.shop.bean.UserBean;

public interface IShopDao {
	/**
	 * Uid登录
	 * @param user
	 * @return
	 */
	int loginByUid(UserBean user);
	
	/**
	 * 昵称登录
	 * @param user
	 * @return
	 */
	int loginByNickname(UserBean user);



}
