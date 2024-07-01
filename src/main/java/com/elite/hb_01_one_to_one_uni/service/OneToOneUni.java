package com.elite.hb_01_one_to_one_uni.service;

import com.elite.hb_01_one_to_one_uni.entity.Instructor;
import com.elite.hb_01_one_to_one_uni.entity.InstructorDetail;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

public class OneToOneUni {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                                            .configure("hibernate.cfg.xml")
                                            .addAnnotatedClass(Instructor.class)
                                            .addAnnotatedClass(InstructorDetail.class)
                                            .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();
        Transaction trx = null;

        try{
            //create instructor, instructor_detail records
            trx = session.beginTransaction();
            ArrayList<Instructor> instructors = getInstructors();

            for (Instructor instructor:instructors) {
                //save instructor, as well as instructor detail(cascade = CascadeType.ALL)
                session.save(instructor);
            }
            trx.commit();

            //delete instructor, instructor_detail records
            /*trx = session.beginTransaction();
            int chadId = 1;
            Instructor chad = session.get(Instructor.class, chadId);

            if (chad != null) {
                session.delete(chad);
                trx.commit();
            }*/

        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            if(session != null)
                session.close();
        }
    }

    private static ArrayList<Instructor> getInstructors() {
        Instructor chad = new Instructor("Chad", "Derby", "derb@luv2code.com");
        InstructorDetail chadDetail = new InstructorDetail("www.luve2code.com/youtube", "soccer");
        //associate the two objects(@OneToOne)
        chad.setInstructorDetail(chadDetail);

        Instructor andrew = new Instructor("Andrew", "Mg", "andrew@coursera.com");
        InstructorDetail andrewDetail = new InstructorDetail("www.coursera.com", "ai");
        //associate the two objects(@OneToOne)
        andrew.setInstructorDetail(andrewDetail);

        ArrayList<Instructor> instructors = new ArrayList<>();
        instructors.add(chad);
        instructors.add(andrew);
        return instructors;
    }
}
