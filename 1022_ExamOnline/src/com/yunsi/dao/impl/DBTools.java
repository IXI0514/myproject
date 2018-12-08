package com.yunsi.dao.impl;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;


public class DBTools {
	private static  QueryRunner qr;
	
	static {
		Context con;
		try {
			con = new InitialContext();
			DataSource ds = (DataSource) con.lookup("java:comp/env/ordb");//META-INF/context.xml
			qr = new QueryRunner(ds);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static QueryRunner getQr() {
		return qr;
	}
	
}
