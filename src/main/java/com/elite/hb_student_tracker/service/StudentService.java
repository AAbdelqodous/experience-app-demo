package com.elite.hb_student_tracker.service;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.elite.hb_student_tracker.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Student.class)
										.buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();
		Transaction trx = null;
		Session openedSession = null;
		try {
			//create a new student
			/*List<Student> students = new ArrayList<>();
			students.add( new Student("Nihal",  "Eisa", "nihal@gmail.com"));
			students.add( new Student("Mohamed",  "Ahmed", "mohamed@gmail.com"));
			students.add( new Student("Assiya",  "Ahmed", "assiya@gmail.com"));

			trx = session.beginTransaction();
			for (Student student:students) {
				session.save(student);
			}
			trx.commit();
			System.out.println("Saved success..");*/


			//get a student
			/*Student student = new Student("Elsayed", "Abdelqodous", "elsayed@gmail.com");
			trx = session.beginTransaction();
			session.save(student);
			trx.commit();

			openedSession = sessionFactory.openSession();
			Student rstudent = openedSession.get(Student.class, student.getId());
			System.out.println(rstudent);*/


		} catch (HibernateException e) {
			if (trx != null) trx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
			if(openedSession != null) openedSession.close();
		}

		sessionFactory.close();
	}

}
