package com.lish.view;


import com.lish.domain.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import java.util.Date;

/**
 * Created by InnoXYZ on 14-5-28.
 */
public class IndexView {


	public static void main(final String[] args) throws Exception {
		addEmployee();

	}



	//添加用户
	private static void addEmployee() {
		System.out.println("HelloWorld");

		//添加一个雇员.
		Employee employee=new Employee("liuxiang","lish516@qq.com",new Date());


		//1.创建Configuration,该对象用于读取hiberante.cfg.xml并完成初始化。
		Configuration configuration=new Configuration().configure();
		//2.创建sessionFactory，这是一个重量级的类。应该做出单例。
		SessionFactory sessionFactory=configuration.buildSessionFactory();
		//3.创建session,相当于jdbc Connection
		Session session=sessionFactory.openSession();
		//4.对hibernate而言，要求程序员，在进行 增加 删除 修改的时候必须要使用事务提交。
		Transaction transaction=session.beginTransaction();


		//Insert...
		//保存。=>insert into ... 被hibernate 封装起来了。
		session.save(employee);

		transaction.commit();
		session.close();
	}

	//修改用户


}
