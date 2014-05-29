package com.lish.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


/**
 * Created by lishuang on 2014/5/28.
 */

//在使用项目开发的时候一个数据库对应一个sessionFactory,一般而言，一个项目只有一个sessionFactory.
final public class MySessionFactory {
	private static final SessionFactory sessionFactory;
	private static final ServiceRegistry serviceRegistry;
	static {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();

			serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
			sessionFactory = configuration.buildSessionFactory(serviceRegistry);
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError(ex);
		}
	}


	public static Session openSession() throws HibernateException {
		System.out.println("SessionFactory 类型："+sessionFactory);
		return sessionFactory.openSession();
	}

	//在session事务提交后会自动关闭。但是建议使用完毕后手动关闭。
	public static Session getCurrentSession() throws HibernateException {

		return sessionFactory.getCurrentSession();
	}
	private MySessionFactory(){

	}

}
