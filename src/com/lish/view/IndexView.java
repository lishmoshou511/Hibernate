package com.lish.view;


import com.lish.domain.*;
import com.lish.util.MySessionFactory;
import org.hibernate.Hibernate;
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

		//studyHQL4();
		//threeStates();
		//many2one();
		//lazyLoad();

//		Person person=getPerson();
//		System.out.println("姓名："+person.getName());
//		System.out.println("所在部门："+person.getDept().getName());


		//one2many();
		one2manyAdd();

	}

	private static void one2manyAdd(){
		//获取一个会话。
		Session session = MySessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Department department= (Department) session.get(Department.class,1);

			Person person1=new Person();
			person1.setName("笑面虎");
			Person person2=new Person();
			person2.setName("时迁");

			department.getPersonSet().add(person1);
			department.getPersonSet().add(person2);

			for(Person person:department.getPersonSet()){
				System.out.println("学生名字："+person.getName());
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



	private static void one2many(){
		//获取一个会话。
		Session session = MySessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Department department= (Department) session.get(Department.class,1);

			for(Person person:department.getPersonSet()){
				System.out.println("学生名字："+person.getName());
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

	private static Person getPerson(){
		//获取一个会话。
		Session session = MySessionFactory.getCurrentSession();
		Transaction transaction = null;
		Person person=null;
		try {
			transaction = session.beginTransaction();

			//查询3号学生
			person= (Person) session.get(Person.class,2);

			//迫使将与person绑定的department 查询出来。
			//Hibernate.initialize(person.getDept());
			//person.getDept().getName();

			//System.out.println("所在部门："+person.getDept().getName());

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
		return person;
	}

	private static void lazyLoad(){
		//获取一个会话。
		Session session = MySessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			//查询3号学生
			Person person= (Person) session.get(Person.class,3);
			System.out.println("姓名："+person.getName());
			System.out.println("所在部门："+person.getDept().getName());


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

	private static void many2one(){
		//获取一个会话。
		Session session = MySessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			//添加一个人和一个部门，而且要把人分配到该部门中去。
			Person person=new Person();
			person.setName("宋江");
			Department department=new Department();
			department.setName("财务部");
			person.setDept(department);

			session.save(department);
			session.save(person);

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

	//对象的三种状态
	private static void threeStates(){

		//c1在这里就是瞬时态。
		Course c1=new Course();
		c1.setCname("JS");
		c1.setCcredit(5);
		c1.setCid(2);

		//获取一个会话。
		Session session = MySessionFactory.getCurrentSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();

			//c1这时既处于sssion管理下，同时c1对象已经被保存到了数据库中。
			//因此c1就是持久态了。
			//这里会产生一个Insert语句
			session.save(c1);

			//接下来会产生一个update语句
			c1.setCname(".Net");

			System.out.println("*****************************");
			c1.setCcredit(100);

			session.delete(c1);

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

		//这是c1被保存到了数据库中，同时没有处于session管理下
		//c1就是脱管态（游离态)

		//这个地方的变化不会被hibernate检测到，因此也不会反应到数据库中
		c1.setCname("ASP.Net");


		System.out.println("名称："+c1.getCname()+" 学分："+c1.getCcredit());


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
