package manager;

import dao.RevisionDao;
import service.AuditLogService;
import view.LeaveAuditLogView;
import view.UserAuditLogView;

import java.util.List;

public class AuditLogManager {

    public List<UserAuditLogView> getUserAuditLogViews(){
        AuditLogService auditLogService=new AuditLogService();
        return auditLogService.getUserAuditLogViews();
    }

    public List<LeaveAuditLogView> getLeaveAuditLogViews() {
        AuditLogService auditLogService=new AuditLogService();
        return auditLogService.getLeaveAuditLogViews();
    }
}
