package com.lish.view;


import com.lish.domain.Employee;
import com.lish.domain.Worker;
import com.lish.util.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.Date;

/**
 * Created by InnoXYZ on 14-5-28.
 */
public class IndexView {


	public static void main(final String[] args) throws Exception {

		//addEmployee();


		//editEmployee();

		//deleteEmployee();

		//addWorker();

		getEmployee();

	}

	private static void getEmployee(){
		//获取一个会话。
		Session session = MySessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			//获取要需改的用户
			//load方法是可以通过主键来获取一个对象实例。和表的一条记录对应。
			Employee employee = (Employee) session.get(Employee.class, 4);

			System.out.println("Employee:::::"+employee);

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			throw new RuntimeException();

		} finally {
			if (session != null && session.isOpen()) {

				session.close();
			}
		}
	}

	private static void deleteEmployee() {
		//获取一个会话。
		Session session = MySessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			//获取要需改的用户
			//load方法是可以通过主键来获取一个对象实例。和表的一条记录对应。
			Employee employee = (Employee) session.load(Employee.class, 2);

			session.delete(employee);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			throw new RuntimeException();

		} finally {
			if (session != null && session.isOpen()) {

				session.close();
			}
		}


	}


	//添加用户
	private static void addEmployee() {
		System.out.println("HelloWorld");

		//添加一个雇员.
		Employee employee = new Employee("hanmeimei", "lish516@gg.com", new Date());
		//获取一个会话。
		Session session = MySessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		//Insert...
		//保存。=>insert into ... 被hibernate 封装起来了。

		session.save(employee);

		transaction.commit();
		session.close();
	}


	//修改用户
	private static void editEmployee() {
		//获取一个会话。
		Session session = MySessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		//获取要需改的用户
		//load方法是可以通过主键来获取一个对象实例。和表的一条记录对应。
		Employee employee = (Employee) session.load(Employee.class, 1);

		employee.setName("韩顺平");
		employee.setEmail("hanshunping@126.com");
		transaction.commit();
		session.close();

	}


	//添加工人
	private static void addWorker() {
		System.out.println("Adding worker");

		//添加一个工人.
		Worker worker = new Worker("workerLishuang", "lish516@126.com", new Date());
		//获取一个会话。
		Session session = MySessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		//Insert...
		//保存。=>insert into ... 被hibernate 封装起来了。

		session.save(worker);

		transaction.commit();
		session.close();
	}

}
