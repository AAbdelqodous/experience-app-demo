package com.elite.eager_vs_lazy.service;


import com.elite.eager_vs_lazy.entity.Course;
import com.elite.eager_vs_lazy.entity.Instructor;
import com.elite.eager_vs_lazy.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class LazyResolveHQL {
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
            int instructorId = 2;
            Instructor instructor = session.get(Instructor.class, instructorId);
            System.out.println(instructor);

//            System.out.println(instructor.getCourses());

            Query<Instructor> query = session.createQuery("select i from Instructor i " +
                                                                    "JOIN FETCH i.courses " +
                                                                    "where i.id = :instructorId",
                                                                    Instructor.class);
            query.setParameter("instructorId", instructorId);
            Instructor theInstructor= query.getSingleResult();


            trx.commit();
            session.close();

            System.out.println(theInstructor.getCourses());
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
