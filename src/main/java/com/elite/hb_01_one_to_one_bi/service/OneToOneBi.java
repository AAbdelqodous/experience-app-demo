package com.elite.hb_01_one_to_one_bi.service;

import com.elite.hb_01_one_to_one_bi.entity.Instructor;
import com.elite.hb_01_one_to_one_bi.entity.InstructorDetail;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class OneToOneBi {
    public static void main(String[] args) {

        SessionFactory sessionFactory = new Configuration()
                                            .configure("hibernate.cfg.xml")
                                            .addAnnotatedClass(Instructor.class)
                                            .addAnnotatedClass(InstructorDetail.class)
                                            .buildSessionFactory();

        Session session = sessionFactory.getCurrentSession();;
        Transaction trx = null;

        try{
            //get instructor from instructor_detail records
            trx = session.beginTransaction();
            int andrewDetailId = 3;
            InstructorDetail andrewDetail = session.get(InstructorDetail.class, andrewDetailId);

            if (andrewDetail != null) {
                Instructor andrew = session.get(Instructor.class, andrewDetail.getInstructor().getId());
                System.out.println(andrew);
                System.out.println(andrewDetail);
                //the key is to remove the associated reference (InstructorDetail)
                //break one-to-one-bi relationship
                andrew.setInstructorDetail(null);
            }

            session.delete(andrewDetail);
            trx.commit();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(session != null)
                session.close();
        }
    }
}
