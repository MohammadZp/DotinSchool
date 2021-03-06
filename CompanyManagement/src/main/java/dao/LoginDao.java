package dao;

import hibernateConfig.HibernateUtil;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class LoginDao {
    public boolean checkLogin(String username, String password) {
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            user = (User) session.createQuery("FROM User U WHERE U.username = :username").setParameter("username", username)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        if (user != null && user.getPassword().equals(password)) return true;
        return false;
    }
}