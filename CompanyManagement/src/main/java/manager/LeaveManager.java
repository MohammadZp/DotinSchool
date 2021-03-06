package manager;

import com.ghasemkiani.util.icu.PersianCalendar;
import dao.CategoryElementDao;
import date.SolarCalenderDate;
import exception.*;
import hibernateConfig.HibernateUtil;
import model.CategoryElement;
import model.Leave;
import systemMessages.Message;
import model.User;
import org.hibernate.Session;

import service.LeaveService;
import service.UserService;
import view.LeaveView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class LeaveManager {
    LeaveService leaveService = new LeaveService();
    CategoryElementManager categoryElementManager = new CategoryElementManager();

    public List<String> addLeaveRequest(HttpServletRequest request, HttpServletResponse response) {
        User userLeaveRequester = (User) request.getSession().getAttribute("user");
        String startTime = "";
        String finishTime = "";
        Long id = userLeaveRequester.getId();
        String startDate = request.getParameter("startDate");
        String finishDate = request.getParameter("finishDate");
        String checkboxHourLeave = request.getParameter("checkboxHourLeave");
        String checkboxDailyLeave = request.getParameter("checkboxDailyLeave");
        boolean leaveDaily = true;
        if (checkboxHourLeave == null) {
            checkboxHourLeave = "";
        }
        if (checkboxDailyLeave == null) {
            checkboxDailyLeave = "";
        }
        if (checkboxHourLeave.equals("on")) {
            leaveDaily = false;
            startTime = request.getParameter("startTime");
            finishTime = request.getParameter("finishTime");
        } else if (checkboxDailyLeave.equals("on")) {
            startTime = Message.START_WORK_TIME;
            finishTime = Message.FINISH_WORK_TIME;
        }
        List<String> errors = new ArrayList<>();
        Leave leave = new Leave();
        CategoryElement status = categoryElementManager.objectOfName(Message.NOT_SEEN);
        errors.add(checkDateValidation(startDate, finishDate));
        errors.add(checkTimeValidation(startTime, finishTime));
        leave.setUserLeaveRequester(userLeaveRequester);
        leave.setFromTime(startTime);
        leave.setToTime(finishTime);
        leave.setFromDate(startDate);
        leave.setToDate(finishDate);
        leave.setLeaveStatus(status);
        leave.setActive(true);
        leave.setDaily(leaveDaily);
        leave.setEnable(true);
        leave.setCreationDate(Message.getSolarDate(new Date()));
        leave.setModificationDate(Message.NEVER_MODIFIED_MESSAGE);
        List<String> arr = new ArrayList<>();
        arr.add("");
        errors.removeAll(arr);
        if (errors.isEmpty()) leaveService.create(leave);
        return errors;
    }

    private String checkTimeValidation(String startTime, String finishTime) {
        String[] s = startTime.split(":");
        String[] f = finishTime.split(":");
        if (Integer.parseInt(s[0]) > Integer.parseInt(f[0])) {
            return Message.CHECK_YOUR_TIME_ENTERED;
        } else if (Integer.parseInt(s[0]) == Integer.parseInt(f[0])) {
            if (Integer.parseInt(s[1]) > Integer.parseInt(f[1])) {
                return Message.CHECK_YOUR_TIME_ENTERED;
            }
        }

        return "";
    }

    private String checkDateValidation(String startDate, String finishDate) {
        PersianCalendar persianCalendar = new PersianCalendar(new Date());
        int year = persianCalendar.get(Calendar.YEAR);
        int month = persianCalendar.get(Calendar.MONTH) + 1;
        int day = persianCalendar.get(Calendar.DAY_OF_MONTH);
        String nowDate = year + "/" + month + "/" + day;
        if (compareDate(nowDate, startDate) < 0 && compareDate(nowDate, finishDate) < 0) {
            return Message.CHECK_YOUR_DATE_ENTERED;
        } else if (compareDate(startDate, finishDate) < 0) {
            return Message.CHECK_YOUR_DATE_ENTERED;
        }
        if (startDate.equals(finishDate)) {
            return "";
        }
        return "";

    }

    private static int compareDate(String startDate, String finishDate) {
        String[] s = startDate.split("/");
        String[] f = finishDate.split("/");
        if (Integer.parseInt(f[0]) > Integer.parseInt(s[0])) {
            return 1;
        } else if (Integer.parseInt(f[0]) == Integer.parseInt(s[0])) {
            if (Integer.parseInt(f[1]) > Integer.parseInt(s[1])) {
                return 1;
            } else if (Integer.parseInt(f[1]) == Integer.parseInt(s[1])) {
                if (Integer.parseInt(f[2]) > Integer.parseInt(s[2])) {
                    return 1;
                }
            }
        }

        return -1;
    }

    private void checkUserIdValidation(Long userId) throws Exception {
        long id = userId;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        User manager = session.get(User.class, id);
        session.getTransaction().commit();
        session.close();
        if (manager == null) {
            throw new Exception("there is no user with id=" + id + " (check manager id)");
        }
    }

    private void checkUserActivation(Long id) throws ActiveUserException {
        UserService userService = new UserService();
        User user = userService.getUser(id);
        if (!user.isActive()) throw new ActiveUserException(Message.deActive(id.toString()));
    }

    public List<LeaveView> readLeaves(HttpServletRequest request, HttpServletResponse response) {
        LeaveView leaveView = new LeaveView();
        List<LeaveView> leaveViewList = leaveView.getLeaveViewList(leaveService.read());
        User user = (User) request.getSession().getAttribute("user");
        Long managerId = user.getId();
        List<LeaveView> leaveViewAsManager = leaveView.getLeaveViewList(leaveService.readTeamLeaves(managerId));
        request.setAttribute("leaveViewAsManager", leaveViewAsManager);
        request.setAttribute("leaveViewList", leaveViewList);
        return leaveViewList;
    }

    public void deleteLeave(HttpServletRequest request, HttpServletResponse response) {
        long id = Integer.parseInt(request.getParameter("id"));
        leaveService.delete(id);
    }

    public Leave getLeave(Long id) {
        return leaveService.getLeave(id);
    }

    public List<String> updateLeaveRequest(HttpServletRequest request, HttpServletResponse response) throws UpdateLeaveException {
        User userLeaveRequester = (User) request.getSession().getAttribute("user");
        long leaveId = Long.parseLong(request.getParameter("leaveId"));
        String startTime = "";
        String finishTime = "";
        String startDate = request.getParameter("startDate");
        String finishDate = request.getParameter("finishDate");
        String checkboxHourLeave = request.getParameter("checkboxHourLeave");
        String checkboxDailyLeave = request.getParameter("checkboxDailyLeave");
        boolean leaveDaily = true;
        if (checkboxHourLeave == null) {
            checkboxHourLeave = "";
        }
        if (checkboxDailyLeave == null) {
            checkboxDailyLeave = "";
        }
        if (checkboxHourLeave.equals("on")) {
            leaveDaily = false;
            startTime = request.getParameter("startTime");
            finishTime = request.getParameter("finishTime");
        } else if (checkboxDailyLeave.equals("on")) {
            startTime = Message.START_WORK_TIME;
            finishTime = Message.FINISH_WORK_TIME;
        }
        List<String> errors = new ArrayList<>();
        Leave leave = leaveService.getLeave(leaveId);
        leave.setId(leaveId);
        errors.add(checkDateValidation(startDate, finishDate));
        errors.add(checkTimeValidation(startTime, finishTime));
        leave.setUserLeaveRequester(userLeaveRequester);
        leave.setFromTime(startTime);
        leave.setToTime(finishTime);
        leave.setDaily(leaveDaily);
        leave.setFromDate(startDate);
        leave.setToDate(finishDate);
        leave.setActive(true);
        leave.setEnable(true);
        leave.setModificationDate(Message.getSolarDate(new Date()));
        List<String> arr = new ArrayList<>();
        arr.add("");
        errors.removeAll(arr);
        if (errors.isEmpty()) leaveService.update(leave);
        return errors;
    }

    public void viewLeave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UpdateLeaveException {
        Long id = Long.parseLong(request.getParameter("id"));
        Leave leave = leaveService.getLeave(id);
        String leaveStatusCode = Message.SEEN;
        CategoryElementDao categoryElementDao = new CategoryElementDao();
        CategoryElement leaveStatus = categoryElementDao.ObjectOfname(leaveStatusCode);
        leave.setLeaveStatus(leaveStatus);
        leave.setModificationDate(Message.getSolarDate(new Date()));
        leaveService.update(leave);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/viewLeave.jsp");
        LeaveView leaveView = new LeaveView();
        LeaveView LeaveView = leaveView.getLeaveView(leave);
        request.setAttribute("LeaveView", LeaveView);
        requestDispatcher.forward(request, response);
    }

    public void deActivate(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("id"));
        leaveService.deActivate(id);
    }

    public void getUserLeaveList(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");
        Long id = user.getId();
        List<Leave> leaveList = leaveService.getLeaves(id);
        LeaveView leaveView = new LeaveView();
        List<LeaveView> leaveViewList = leaveView.getLeaveViewList(leaveList);
        request.setAttribute("leaveViewList", leaveViewList);
    }

    public void activate(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("id"));
        leaveService.activate(id);
    }

    public void reject(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("id"));
        CategoryElementDao categoryElementDao = new CategoryElementDao();
        CategoryElement leaveStatus = categoryElementDao.ObjectOfname(Message.REJECTED);
        Leave leave = leaveService.getLeave(id);
        leave.setLeaveStatus(leaveStatus);
        leave.setModificationDate(Message.getSolarDate(new Date()));
        leaveService.update(leave);
    }

    public void accept(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("id"));
        CategoryElementDao categoryElementDao = new CategoryElementDao();
        CategoryElement leaveStatus = categoryElementDao.ObjectOfname(Message.ACCEPTED);
        Leave leave = leaveService.getLeave(id);
        leave.setLeaveStatus(leaveStatus);
        leave.setModificationDate(Message.getSolarDate(new Date()));
        leaveService.update(leave);
    }

    public List<LeaveView> adminViewListLeaves(HttpServletRequest request, HttpServletResponse response) {
        LeaveView leaveView = new LeaveView();
        List<LeaveView> leaveAdminViewList = leaveView.getLeaveViewList(leaveService.readLeavesByAdmin());
        request.setAttribute("leaveAdminViewList", leaveAdminViewList);
        return leaveAdminViewList;
    }
}
