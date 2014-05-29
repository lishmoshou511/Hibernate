package com.lish.view;


import com.lish.domain.Employee;
import com.lish.domain.Student;
import com.lish.domain.Worker;
import com.lish.util.MySessionFactory;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import java.util.Date;
import java.util.List;

/**
 * Created by InnoXYZ on 14-5-28.
 */
public class IndexView {


	public static void main(final String[] args) throws Exception {
		//addEmployee();
		//editEmployee();
		//deleteEmployee();
		//addWorker();
		//getEmployee();
		//testGetCurrentSession();
		//testQuery();

		studyHQL4();

	}

	//4.参数注入
	private static void studyHQL4(){
		//获取一个会话。
		Session session = MySessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();


			//用?的方式注入
			//List list = session.createQuery("from Student where sdept=? and sage>?").setString(0,"计算机系").setString(1,"20").list();
			//用:name的方式注入
			//这里可以连贯着写，也可以分开写。
			List list = session.createQuery("from Student where sdept=:sdept and sage>:sage").setString("sdept","计算机系").setString("sage","20").list();

			for(Object obj:list){
				Student student=(Student)obj;
				System.out.println(student.getSname()+student.getSage());
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			throw new RuntimeException(e.getMessage());

		} finally {
			if (session != null && session.isOpen()) {

				session.close();
			}
		}
	}

	//3.分页查询
	private static void studyHQL3(){
		//获取一个会话。
		Session session = MySessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			//按照学生的年龄从小到大取出第一个到第三个学生。相当于limit的两个参数了。
			List list = session.createQuery("from Student order by sage").setFirstResult(2).setMaxResults(3).list();

			for(Object obj:list){
				Student student=(Student)obj;
				System.out.println(student.getSname()+student.getSage());
			}
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			throw new RuntimeException(e.getMessage());

		} finally {
			if (session != null && session.isOpen()) {

				session.close();
			}
		}
	}

	//2.检索学生的名字和系别。
	private static void studyHQL2(){
		//获取一个会话。
		Session session = MySessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();


			//检索学生的名字和所在系别。
			//uniqueResult如果是多条记录，那么会抛异常的。
			Student s=(Student)session.createQuery("from Student where sid=20050003").uniqueResult();
			//Student s=(Student)session.get(Student.class,20050003);
			System.out.println("学生姓名："+s.getSname());

			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			throw new RuntimeException(e.getMessage());

		} finally {
			if (session != null && session.isOpen()) {

				session.close();
			}
		}
	}
	//1.检索所有的学生。<Student>
	private static void studyHQL1(){
		//获取一个会话。
		Session session = MySessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();


			List list = session.createQuery("from Student").list();

			for(Object obj:list){
				Student student=(Student)obj;
				System.out.println(student.getSname()+" "+student.getSaddress());
			}





			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				transaction.rollback();
			}
			throw new RuntimeException(e.getMessage());

		} finally {
			if (session != null && session.isOpen()) {

				session.close();
			}
		}
	}


	//官方推荐的是query，同时还有criteria
	private static void testQuery(){
		//获取一个会话。
		Session session = MySessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			//获取一个query引用[注意：Employee不是表，而是domain类名]where后面的条件可以是一个类的属性名，也可以是表的字段。
			Query query=session.createQuery("from Employee where name='hanmeimei2' or name='hanmeimei1'");
			//通过list方法获取结果，这个list会自动的将结果封装成对应的domain对象。
			List<Employee> list=query.list();
			for(Employee e:list){
				System.out.println(e.getId()+e.getName()+e.getEmail()+e.getHiredate());

			}

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

	private static void testGetCurrentSession(){
		Session s1=MySessionFactory.getCurrentSession();
		Session s2=MySessionFactory.getCurrentSession();

		System.out.println("Hash1:"+s1.hashCode()+" Hash2:"+s2.hashCode());

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

			System.out.println("Employee1:::::"+employee);

			Employee employee2 = (Employee) session.get(Employee.class, 4);

			System.out.println("Employee2:::::"+employee2);

			if(employee==employee2){
				System.out.println("对象相同");
			}else{
				System.out.println("对象不同");
			}

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
