package jm.task.core.jdbc.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class SessionUtil {

    private static Session session = null;
    private static Transaction transaction = null;

    public static Session getSession() {
		return session;
    }
    
    public static Transaction getTransaction() {
		return transaction;
    }

    private static Session openSession(){

        try {

            return Util.getSessionFactory().openSession();
            
        } catch (HibernateException e) {

            transaction = getTransaction();

            if(transaction != null){
                transaction.rollback();
            }

            Util.getLogger().warning("Faled transaction!");
        }

        throw new RuntimeException();
    }
    
    public static Session openTransactionSession(){
        
        session = openSession();
        transaction = session.beginTransaction();
        
        return session;
    }

    public static void closeSession(){
        session.close();
    }

    public static void closeTransactionSession(){
        transaction.commit();
        session.close();
    }
}

