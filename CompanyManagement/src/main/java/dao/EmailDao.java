package dao;

import exception.UpdateException;
import hibernateConfig.HibernateUtil;
import model.Email;
import model.User;
import org.hibernate.*;
import systemMessages.Message;

import java.util.List;

public class EmailDao {
    public void create(Email email) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(email);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();

        }
    }

    public List<Email> read() {
        Session session = null;
        List<User> userList = null;
        List<Email> emailList = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            emailList = session.createQuery("FROM Email").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return emailList;
    }

    public void update(Email email) throws UpdateException {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            if (!email.equals(email)) {
                throw new UpdateException(Message.UPDATE_EXCEPTION_MESSAGE);
            } else {
                session.update(email);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void delete(Long id) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Email email = (Email) session.get(Email.class, id);
            session.delete(email);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    public Email getEmail(Long id) {
        Email email = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            email = session.get(Email.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return email;
    }

    public List<Email> getInboxEmails(Long id) {
        Session session = null;
        List<Email> emailList = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            emailList = session.createNativeQuery("select * FROM t_emails INNER JOIN companymanagement.t_userrecievedemails where t_userrecievedemails.c_userId=:id AND t_emails.c_id=t_userrecievedemails.c_emailId", Email.class).setParameter("id", id).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return emailList;
    }

    public List<Email> getSentEmails(Long id) {
        Session session = null;
        List<Email> emailList = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            emailList = session.createQuery("from Email email where email.userSender.id=:id", Email.class).setParameter("id", id).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return emailList;
    }

    public User getUserByUsername(String s) {
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            user = (User) session.createQuery("FROM User u where u.username=:s").setParameter("s", s).uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }
}
