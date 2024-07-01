package com.elite.hb_01_one_to_many.service;


import com.elite.hb_01_one_to_many.entity.Course;
import com.elite.hb_01_one_to_many.entity.Instructor;
import com.elite.hb_01_one_to_many.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class OneToMany {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                                            .configure("hibernate.cfg.xml")
                                            .addAnnotatedClass(Instructor.class)
                                            .addAnnotatedClass(InstructorDetail.class)
                                            .addAnnotatedClass(Course.class)
                                            .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();;
        Transaction trx = null;

        try{
            //create instructor, instructor_detail records
            /*trx = session.beginTransaction();
            ArrayList<Instructor> instructors = getInstructors();

            for (Instructor instructor:instructors) {
                //save instructor, as well as instructor detail(cascade = CascadeType.ALL)
                session.save(instructor);
            }
            trx.commit();*/

            /*trx = session.beginTransaction();
            int instrId = 2;
            Instructor instructor = session.get(Instructor.class, instrId);
            System.out.println(instructor);

            Course springBootTutorialForBeginners = new Course("Spring Boot Tutorial for Beginners");
            Course AWSSolutionArchitectCertification = new Course("AWS Solution Architect Certification");
            List<Course> courses = new ArrayList<>();
            courses.add(springBootTutorialForBeginners);
            courses.add(AWSSolutionArchitectCertification);
            for(Course course:courses){
                instructor.add(course);
                session.save(course);
            }
            trx.commit();*/

            trx = session.beginTransaction();
            int courseId = 10;
            Course course = session.get(Course.class, courseId);
            System.out.println(course);
            session.delete(course);
            trx.commit();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session != null)
                session.close();
        }
    }

    private static ArrayList<Instructor> getInstructors() {
        Instructor khalil = new Instructor("Khalil", "Ahmad", "Khalil@28minutes.com");
        InstructorDetail khalilDetail = new InstructorDetail("www.youtube.com/28minutes", "swimming");
        //associate the two objects(@OneToOne)
        khalil.setInstructorDetail( khalilDetail);

        ArrayList<Instructor> instructors = new ArrayList<>();
        instructors.add(khalil);
        return instructors;
    }
}
