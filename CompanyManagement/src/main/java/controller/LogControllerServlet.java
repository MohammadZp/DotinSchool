package controller;

import manager.AuditLogManager;
import view.LeaveAuditLogView;
import view.UserAuditLogView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.LogManager;

public class LogControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/log/user":
                getUserLog(req, resp);
                break;
            case "/log/leave":
                getLeaveLog(req, resp);
                break;
        }
    }

    private void getLeaveLog(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuditLogManager auditLogManager=new AuditLogManager();
        List<LeaveAuditLogView> leaveAuditLogViews=auditLogManager.getLeaveAuditLogViews();
        req.setAttribute("leaveAuditLogViews",leaveAuditLogViews);
        RequestDispatcher requestDispatcher=req.getRequestDispatcher("/leaveLog.jsp");
        requestDispatcher.forward(req,resp);
    }


    private void getUserLog(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        AuditLogManager auditLogManager=new AuditLogManager();
        List<UserAuditLogView> userAuditLogViews=auditLogManager.getUserAuditLogViews();
        req.setAttribute("userAuditLogViews",userAuditLogViews);
        RequestDispatcher requestDispatcher=req.getRequestDispatcher("/userLog.jsp");
        requestDispatcher.forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
