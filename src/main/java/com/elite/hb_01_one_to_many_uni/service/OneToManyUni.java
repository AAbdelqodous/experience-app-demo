package com.elite.hb_01_one_to_many_uni.service;


import com.elite.hb_01_one_to_many_uni.entity.Instructor;
import com.elite.hb_01_one_to_many_uni.entity.InstructorDetail;
import com.elite.hb_01_one_to_many_uni.entity.Course;
import com.elite.hb_01_one_to_many_uni.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class OneToManyUni {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                                            .configure("hibernate.cfg.xml")
                                            .addAnnotatedClass(Instructor.class)
                                            .addAnnotatedClass(InstructorDetail.class)
                                            .addAnnotatedClass(Course.class)
                                            .addAnnotatedClass(Review.class)
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
            int instrId = 1;
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
            Course course = new Course("Web-logic standard");

            List<Review> reviews = new ArrayList<>();
            reviews.add( new Review("Awesome... that is a pretty course"));
            reviews.add( new Review("cool.. It is a simple and direct.."));
            reviews.add( new Review("Not bad!!"));
            for (Review review:reviews){
                course.add(review);
            }
            session.save(course);
            System.out.println(course);
            System.out.println(course.getReviews());
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
