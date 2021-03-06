package service;

import dao.RevisionDao;
import view.LeaveAuditLogView;
import view.UserAuditLogView;

import java.util.List;

public class AuditLogService {
    public List<UserAuditLogView> getUserAuditLogViews() {
        RevisionDao revisionDao = new RevisionDao();
        return revisionDao.getUserAuditLogViews();
    }

    public List<LeaveAuditLogView> getLeaveAuditLogViews() {
        RevisionDao revisionDao = new RevisionDao();
        return revisionDao.getLeaveAuditLogViews();
    }
}
