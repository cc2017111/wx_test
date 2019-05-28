package com.xidian.faceToChild.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

public class D {
	private static SqlSessionFactory sqlsessionfactory;
	private static ThreadLocal<SqlSession> threadLocal = new ThreadLocal<SqlSession>();
//	private static Logger log = LogManager.addLogger(D.class);
	static{
		try {
			String resource = "mybatis-config.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlsessionfactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException("创建连接失败");
		}
	}
	
	/**
	 * 建立数据库连接
	 * @return
	 * @throws IOException
	 */
	public static SqlSession getConn() {
//		log.info("创建数据库连接...");
		SqlSession sqlSession = threadLocal.get();
		if (sqlSession == null) {
			sqlSession = sqlsessionfactory.openSession();
			threadLocal.set(sqlSession);
		}
		
		return sqlSession;
	}
	
	/**
	 * 关闭数据库连接 
	 */
	public static void closeConn() {
		
		SqlSession sqlSession = threadLocal.get();
//		log.info("关闭数据库连接:..." + sqlSession);
		if(sqlSession != null) {
			sqlSession.commit();
			sqlSession.close();
			threadLocal.remove();
		}
	}
	
}
