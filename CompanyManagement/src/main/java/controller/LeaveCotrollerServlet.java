package controller;

import exception.UpdateLeaveException;
import lombok.SneakyThrows;
import manager.CategoryElementManager;
import manager.LeaveManager;
import manager.UserManager;
import model.Leave;
import service.LeaveService;
import view.LeaveView;
import view.UserView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class LeaveCotrollerServlet extends HttpServlet {
    private LeaveService leaveService;
    private LeaveManager leaveManager;
    private UserManager userManager;
    private CategoryElementManager categoryElementManager;

    public void init() {
        leaveService = new LeaveService();
        leaveManager = new LeaveManager();
        userManager = new UserManager();
        categoryElementManager = new CategoryElementManager();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }


    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getServletPath();
        switch (action) {
            case "/leave/add":
                addLeaveRequest(request, response);
                break;
            case "/leave/delete":
                deleteLeave(request, response);
                break;
            case "/leave/update":
                updateLeave(request, response);
                break;
            case "/leave/edit":
                editFormPage(request, response);
                break;
            case "/leave/list":
                listLeave(request, response);
                break;
            case "/leave/viewLeave":
                viewLeave(request, response);
                break;
            case "/leave/activate":
                activate(request, response);
                break;
            case "/leave/deactivate":
                deActivate(request, response);
                break;
            case "/leave/accept":
                accept(request, response);
                break;
            case "/leave/reject":
                reject(request, response);
                break;
            case "/leave/adminViewlist":
                adminViewLeaveList(request, response);
                break;
        }
    }

    private void adminViewLeaveList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        leaveManager.adminViewListLeaves(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/adminLeaveList.jsp");
        dispatcher.forward(request, response);
    }

    private void reject(HttpServletRequest request, HttpServletResponse response) throws IOException {
        leaveManager.reject(request, response);
        response.sendRedirect("list");

    }


    private void accept(HttpServletRequest request, HttpServletResponse response) throws IOException {
        leaveManager.accept(request, response);
        response.sendRedirect("list");
    }

    private void deActivate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        leaveManager.deActivate(request, response);
        response.sendRedirect("list");
    }


    private void activate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        leaveManager.activate(request, response);
        response.sendRedirect("list");
    }

    private void viewLeave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, UpdateLeaveException {
        leaveManager.viewLeave(request, response);
        response.sendRedirect("list");
    }

    private void addLeaveRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> errors = null;
        try {
            errors = leaveManager.addLeaveRequest(request, response);
            if (errors.isEmpty()) {
                response.sendRedirect("list");
            } else {
                request.setAttribute("errors", errors);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/leaveRequest.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void deleteLeave(HttpServletRequest request, HttpServletResponse response) throws IOException {
        leaveManager.deleteLeave(request, response);
        response.sendRedirect("adminViewlist");
    }

    private void editFormPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        Leave leave = leaveService.getLeave(id);
        LeaveView leaveView = new LeaveView();
        request.setAttribute("leaveView", leaveView.getLeaveView(leave));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/editLeave.jsp");
        dispatcher.forward(request, response);
    }

    private void updateLeave(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        List<String> errors = null;
        try {
            errors = leaveManager.updateLeaveRequest(request, response);
            if (errors.isEmpty()) {
                response.sendRedirect("list");
            } else {
                long leaveId = Long.parseLong(request.getParameter("leaveId"));
                Leave leave = leaveService.getLeave(leaveId);
                LeaveView leaveView = new LeaveView();
                request.setAttribute("leaveView", leaveView.getLeaveView(leave));
                request.setAttribute("errors", errors);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/editLeave.jsp");
                requestDispatcher.forward(request, response);

            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void listLeave(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        leaveManager.readLeaves(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/LeaveList.jsp");
        dispatcher.forward(request, response);
    }
}
