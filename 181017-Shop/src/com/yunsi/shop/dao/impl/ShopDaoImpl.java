package com.yunsi.shop.dao.impl;



import java.math.BigDecimal;
import java.sql.SQLException;

import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.yunsi.shop.bean.UserBean;
import com.yunsi.shop.dao.IShopDao;



public class ShopDaoImpl implements IShopDao{

	@Override
	public int loginByNickname(UserBean user) {
			String sql = "select count(*) from logininfo where username =? and password =?";
			try {
				BigDecimal db= DBTools.getQr().query(sql, new ScalarHandler<BigDecimal>(),user.getUsername(),user.getPassword());
				System.out.println(db.intValue());
				return db.intValue();
			} catch (SQLException e) {
				e.printStackTrace();
			} 	
			return -1;
	}

	@Override
	public int loginByUid(UserBean user) {
		String sql = "select count(*) from logininfo where userid =? and password =?";
		try {
			/*System.out.println(Integer.parseInt(user.getUid()));
			BigDecimal db= DBTools.getQr().query(sql, new ScalarHandler<BigDecimal>(),Integer.parseInt(user.getUid()),user.getPassword());*/
			BigDecimal db= DBTools.getQr().query(sql, new ScalarHandler<BigDecimal>(),user.getUid(),user.getPassword());
			System.out.println(db.intValue());
			return db.intValue();
		} catch (SQLException e) {
			e.printStackTrace();
		} 	
		return -1;
	}

}
