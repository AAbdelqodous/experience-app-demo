package com.elite.hb_student_tracker.service;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.elite.hb_student_tracker.entity.Student;

public class StudentService {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration()
										.configure("hibernate.cfg.xml")
										.addAnnotatedClass(Student.class)
										.buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();
		Transaction tx = null;
		try {
			Student student = new Student("Ahmed",  "Abdelqodous", "ahmed.abdelqodous@gmail.com");
			tx = session.beginTransaction();

			session.save(student);

			session.getTransaction().commit();
			System.out.println("Saved success..");
			
		} catch (HibernateException e) {
			if (tx != null) tx.rollback();
			e.printStackTrace();
		} finally {
			if (session != null) session.close();
		}

		sessionFactory.close();

	}

}
