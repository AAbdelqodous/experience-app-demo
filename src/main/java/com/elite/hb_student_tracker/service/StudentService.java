package com.elite.hb_student_tracker.service;

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
		Transaction trx = null;
		Session openedSession = null;
		try {
			trx = session.beginTransaction();

			//create a new student
			/*List<Student> students = new ArrayList<>();
			students.add( new Student("Nihal",  "Eisa", "nihal@gmail.com"));
			students.add( new Student("Mohamed",  "Ahmed", "mohamed@gmail.com"));
			students.add( new Student("Assiya",  "Ahmed", "assiya@gmail.com"));

			for (Student student:students) {
				session.save(student);
			}
			trx = session.getTransaction();
			trx.commit();
			System.out.println("Saved success..");*/


			//get a student
			/*Student student = new Student("Elsayed", "Abdelqodous", "elsayed@gmail.com");
			session.save(student);
			trx = session.getTransaction();
			trx.commit();

			openedSession = sessionFactory.openSession();
			Student rstudent = openedSession.get(Student.class, student.getId());
			System.out.println(rstudent);*/

			//query student table
			/*List<Student> students = session.createQuery("from Student").list();
			for (Student student:students){
				System.out.println(student);
			}*/
			//another approach
			/*Query query = session.createQuery("from Student s WHERE s.lastName = 'Abdelqodous'" +
					" OR s.email LIKE '%abdelqodous%'");
			List <Student>resultList = query.getResultList();
			for (Student student:resultList){
				System.out.println(student);
			}*/

			//update student table
			/*int studId = 1;
			Student student = session.get(Student.class, studId);
			student.setEmail("ml.ahmed1187@gmail.com");
			trx = session.getTransaction();
			trx.commit();*/
			//another approach
			/*session.createQuery("update Student s set s.email = 'nihal.eisa@gmail.com' " +
					"where s.lastName = 'eisa'").executeUpdate();*/

			//delete  a student
			/*int studId = 6;
			Student student = session.get(Student.class, studId);
			session.delete(student);
			trx = session.getTransaction();
			trx.commit();*/
			//another approach
			session.createQuery("delete from Student where id = 6").executeUpdate();
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
