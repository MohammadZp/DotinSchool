package dao;

import exception.UpdateException;
import hibernateConfig.HibernateUtil;
import model.User;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import systemMessages.Message;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class UserDao {
    public void create(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
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


    public List<User> read() {
        Transaction transaction = null;
        Session session = null;
        List<User> userList = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            userList = session.createQuery("FROM User u where u.enable=true").list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userList;
    }

    public void update(User user) throws UpdateException {
        User old = getUser(user.getId());
        old.setUsername(user.getUsername());
        old.setPassword(user.getPassword());
        old.setCreationDate(user.getCreationDate());
        old.setEmailAddress(user.getEmailAddress());
        old.setManager(user.getManager());
        old.setNationalCode(user.getNationalCode());
        old.setVersion(user.getVersion());
        old.setRole(user.getRole());
        old.setFirstName(user.getFirstName());
        old.setLastName(user.getLastName());
        old.setModificationDate(user.getModificationDate());
        old.setActive(user.isActive());
        old.setEnable(user.isEnable());
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            if (!user.equals(old)) {
                throw new UpdateException(Message.UPDATE_EXCEPTION_MESSAGE);
            } else {
                session.update(old);
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
        User user = getUser(id);
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            user.setEnable(false);
            session.update(user);
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

    public User getUser(Long id) {
        Transaction transaction = null;
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            user = session.get(User.class, id);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    public User getUser(String username, String password) {
        Transaction transaction = null;
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            user = (User) session.createQuery("FROM User U WHERE U.username = :username AND U.password=:password").setParameter("username", username).setParameter("password", password)
                    .uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }

    public void deactivate(Long id) {
        Transaction transaction = null;
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            user = (User) session.get(User.class, id);
            user.setActive(false);
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

    public void activate(Long id) {
        Transaction transaction = null;
        Session session = null;
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            user = (User) session.get(User.class, id);
            user.setActive(true);
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

    public boolean checkDistinctUsername(String username) {
        Transaction transaction = null;
        Session session = null;
        List<User> userList = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            userList = session.createNativeQuery("select * from t_users where c_username=:username", User.class).setParameter("username", username).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        if (userList.size() == 0) return true;
        return false;
    }

    public boolean checkUsernameValidationforUpdate(String username, Long id) {

        Transaction transaction = null;
        Session session = null;
        List<User> userList = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            userList = session.createNativeQuery("select * from t_users where c_username=:username", User.class).setParameter("username", username).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        if (userList.size() == 0) {
            return true;
        }
        if (userList.size() == 1) {
            if (id == userList.get(0).getId()) {
                return true;
            }
        }
        return false;
    }
}