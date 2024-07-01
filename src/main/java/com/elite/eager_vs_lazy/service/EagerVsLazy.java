package com.elite.eager_vs_lazy.service;


import com.elite.eager_vs_lazy.entity.Course;
import com.elite.eager_vs_lazy.entity.Instructor;
import com.elite.eager_vs_lazy.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

public class EagerVsLazy {
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
            trx = session.beginTransaction();
            int instrId = 2;
            Instructor instructor = session.get(Instructor.class, instrId);
            System.out.println(instructor);
            System.out.println(instructor.getCourses());
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
