package com.elite.hb_student_tracker.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.elite.hb_student_tracker.entity.Student;

public class StudentService {

	public static void main(String[] args) {
		SessionFactory sessionFactory = new Configuration()
										.configure()
										.addAnnotatedClass(Student.class)
										.buildSessionFactory();

		Session session = sessionFactory.getCurrentSession();
		
		try {
			Student student = new Student("Ahmed",  "Abdelqodous", "ahmed.abdelqodous@gmail.com");
			session.beginTransaction();
			session.save(student);
			session.getTransaction().commit();
			System.out.println("Saved success..");
			
		}finally {
			session.close();
		}
	}

}
