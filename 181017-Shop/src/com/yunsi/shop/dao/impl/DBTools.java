package com.yunsi.shop.dao.impl;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

public class DBTools {
	private static QueryRunner qr;
	
	static {
		try {
			Context ctx = new InitialContext();
			DataSource dataSource = (DataSource)ctx.lookup("java:comp/env/o11g");//META-INF/context.xml
			qr = new QueryRunner(dataSource);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static QueryRunner getQr() {
		return qr;
	}
	
	
}
