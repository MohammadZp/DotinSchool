package dao;

import exception.UpdateException;
import exception.UpdateLeaveException;
import hibernateConfig.HibernateUtil;
import model.CategoryElement;
import model.Leave;
import org.hibernate.Session;
import org.hibernate.Transaction;
import systemMessages.Message;

import java.util.ArrayList;

import java.util.List;

public class LeaveDao {
    public void create(Leave leave) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(leave);
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

    public List<Leave> read() {
        Session session = null;
        List<Leave> leaveList = new ArrayList<>();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            leaveList = session.createQuery("FROM Leave leave where leave.enable=true").list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return leaveList;
    }

    public void update(Leave leave) throws UpdateException {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            if (!leave.equals(leave)) {
                throw new UpdateException(Message.UPDATE_EXCEPTION_MESSAGE);
            } else {
                session.update(leave);
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
        Leave leave = getLeave(id);
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            leave.setEnable(false);
            session.update(leave);
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

    public Leave getLeave(Long id) {
        Session session = null;
        Leave leave = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            leave = session.get(Leave.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return leave;
    }

    public List<Leave> getLeaves(Long id) {
        Session session = null;
        List<Leave> leaveList = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            leaveList = session.createQuery("FROM Leave leave where leave.userLeaveRequester.id=:id").setParameter("id", id).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return leaveList;
    }

    public void activate(Long id) {
        Transaction transaction = null;
        Session session = null;
        Leave leave = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            leave = session.get(Leave.class, id);
            leave.setActive(true);
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

    public void deActivate(Long id) {
        Transaction transaction = null;
        Session session = null;
        Leave leave = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            leave = session.get(Leave.class, id);
            leave.setActive(false);
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

    public void reject(Long id) {
        Transaction transaction = null;
        Session session = null;
        Leave leave = null;
        CategoryElementDao categoryElementDao = new CategoryElementDao();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            leave = session.get(Leave.class, id);
            CategoryElement leaveStatus = categoryElementDao.ObjectOfname(Message.REJECTED);
            leave.setLeaveStatus(leaveStatus);
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

    public void accept(Long id) {
        Transaction transaction = null;
        Session session = null;
        Leave leave = null;
        CategoryElementDao categoryElementDao = new CategoryElementDao();
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            leave = session.get(Leave.class, id);
            CategoryElement leaveStatus = categoryElementDao.ObjectOfname(Message.ACCEPTED);
            leave.setLeaveStatus(leaveStatus);
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

    public List<Leave> readTeamLeaves(Long managerId) {
        Transaction transaction = null;
        Session session = null;
        List<Leave> leaveList = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            leaveList = session.createNativeQuery("select * from companymanagement.t_leaves inner join companymanagement.t_users where companymanagement.t_users.c_managerId=:managerId AND companymanagement.t_leaves.c_userLeaveRequsterId=t_users.c_id AND companymanagement.t_leaves.c_enable=true AND companymanagement.t_leaves.c_active=true", Leave.class).setParameter("managerId", managerId).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return leaveList;
    }

    public List<Leave> readLeavesByAdmin() {
        Transaction transaction = null;
        Session session = null;
        List<Leave> leaveList = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            leaveList = session.createNativeQuery("select * from companymanagement.t_leaves where companymanagement.t_leaves.c_enable=true", Leave.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return leaveList;
    }
}
