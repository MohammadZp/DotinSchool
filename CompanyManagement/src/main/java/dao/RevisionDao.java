package dao;

import hibernateConfig.HibernateUtil;
import model.Leave;
import model.Revision;
import model.User;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import view.LeaveAuditLogView;
import view.UserAuditLogView;

import java.util.ArrayList;
import java.util.List;

public class RevisionDao {

    public List<UserAuditLogView> getUserAuditLogViews() {
        List<UserAuditLogView> userAuditLogViews = new ArrayList<>();
        List<User> users = getUsers();

        Transaction transaction = null;
        Session session = null;

        try {
            for (User user : users) {
                session = HibernateUtil.getSessionFactory().openSession();
                AuditReader auditReader = AuditReaderFactory.get(session);
                List<Number> revisionNumbers = auditReader.getRevisions(User.class, user.getId());
                for (Number n : revisionNumbers) {
                    List<Object[]> list = auditReader.createQuery()
                            .forRevisionsOfEntity(User.class, false, true)
                            .add(AuditEntity.revisionNumber().eq(n)).getResultList();
                    User auditedUser = auditReader.find(User.class, user.getId(), n);
                    Revision revision = auditReader.findRevision(Revision.class, n);
                    UserAuditLogView userAuditLogView = new UserAuditLogView();
                    userAuditLogView.setId(auditedUser.getId());
                    userAuditLogView.setFirstname(auditedUser.getFirstName());
                    userAuditLogView.setLastname(auditedUser.getLastName());
                    userAuditLogView.setUsername(auditedUser.getUsername());
                    if (auditedUser.getManager() != null) {
                        userAuditLogView.setManagerFirstname(auditedUser.getManager().getFirstName());
                        userAuditLogView.setManagerLastname(auditedUser.getManager().getLastName());
                        userAuditLogView.setManagerUsername(auditedUser.getManager().getUsername());
                    }
                    userAuditLogView.setCreationDate(auditedUser.getCreationDate());
                    userAuditLogView.setModificationDate(auditedUser.getModificationDate());
                    System.out.println("auditedUser.getVersion()="+auditedUser.getVersion());
                    String username="";
                    String firstname="";
                    String lastname="";
                   if (getRevTypeByRev((int)n).equals("ADD")) {
                            userAuditLogView.setCreatedBy(revision.getUsername());
                        } else {
                            userAuditLogView.setModifiedBy(revision.getUsername());
                  }
                    userAuditLogViews.add(userAuditLogView);
                }
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        for(UserAuditLogView auditLogView:userAuditLogViews){
           if(auditLogView.getModificationDate()==null){
               auditLogView.setModificationDate("");
           }
            if(auditLogView.getCreationDate()==null){
                auditLogView.setCreationDate("");
            }
            if(auditLogView.getManagerUsername()==null){
                auditLogView.setManagerUsername("");
            }
            if(auditLogView.getCreatedBy()==null){
                auditLogView.setCreatedBy("");
            }
            if(auditLogView.getModifiedBy()==null){
                auditLogView.setModifiedBy("");
            }
        }
        return userAuditLogViews;

    }

    private List<User> getUsers() {
        UserDao userDao = new UserDao();
        return userDao.read();
    }
   private String getRevTypeByRev(int id){
        Transaction transaction = null;
        Session session = null;
        String s="";
       session = HibernateUtil.getSessionFactory().openSession();
        try {
            AuditReader auditReader = AuditReaderFactory.get(session);
            List<Object[]> resultList = auditReader.createQuery()
                    .forRevisionsOfEntity(User.class, User.class.getName(), false, true)
                    .add(AuditEntity.revisionNumber().eq(id)).getResultList();
            s=resultList.get(0)[2].toString();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
       return s;
    }

    public List<LeaveAuditLogView> getLeaveAuditLogViews() {
        List<LeaveAuditLogView> leaveAuditLogViews = new ArrayList<>();
        List<Leave> leaves = getLeaves();

        Transaction transaction = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().openSession();
            for (Leave leave : leaves) {
                AuditReader auditReader = AuditReaderFactory.get(session);
                List<Number> revisionNumbers = auditReader.getRevisions(Leave.class, leave.getId());
                for (Number n : revisionNumbers) {
                    Leave auditedLeave = auditReader.find(Leave.class, leave.getId(), n);
                    LeaveAuditLogView leaveAuditLogView = new LeaveAuditLogView();
                    System.out.println(auditedLeave.getId());
                    leaveAuditLogView.setRequesterId(auditedLeave.getUserLeaveRequester().getId());
                    leaveAuditLogView.setId(auditedLeave.getId());
                    leaveAuditLogView.setFromDate(auditedLeave.getFromDate());
                    leaveAuditLogView.setToDate(auditedLeave.getToDate());
                    leaveAuditLogView.setFromTime(auditedLeave.getFromTime());
                    leaveAuditLogView.setToTime(auditedLeave.getToTime());
                    leaveAuditLogView.setFirstname(auditedLeave.getUserLeaveRequester().getFirstName());
                    leaveAuditLogView.setLastname(auditedLeave.getUserLeaveRequester().getLastName());
                    leaveAuditLogView.setUsername(auditedLeave.getUserLeaveRequester().getUsername());
                    leaveAuditLogView.setCreationDate(auditedLeave.getCreationDate());
                    leaveAuditLogView.setModificationDate(auditedLeave.getModificationDate());
                    leaveAuditLogViews.add(leaveAuditLogView);
                }
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return leaveAuditLogViews;
    }

    private List<Leave> getLeaves() {
        LeaveDao leaveDao=new LeaveDao();
        return leaveDao.read();
    }

}
