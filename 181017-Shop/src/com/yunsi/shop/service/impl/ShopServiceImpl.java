package com.yunsi.shop.service.impl;


import com.yunsi.shop.bean.UserBean;
import com.yunsi.shop.dao.IShopDao;
import com.yunsi.shop.dao.impl.ShopDaoImpl;
import com.yunsi.shop.service.IShopService;


public class ShopServiceImpl implements IShopService{
	private IShopDao isd;
	public ShopServiceImpl() {
		isd = new ShopDaoImpl();
	}
	@Override
	public int login(String username ,String password) {
		String regex = "\\d{6}";
		UserBean user = new UserBean();
		user.setPassword(password);
		
		if(username.matches(regex)) {
			System.out.println("UID登录");
			user.setUid(username);
			return isd.loginByUid(user);
		}else {
			System.out.println("Nickname登录");
			user.setUsername(username);
			return isd.loginByNickname(user);
		}
	}
	@Override
	public UserBean queryuser(String username) {
		// TODO Auto-generated method stub
		return null;
	}
	

	
	

}
