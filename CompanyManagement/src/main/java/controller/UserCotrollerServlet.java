package controller;

import exception.UpdateException;
import lombok.SneakyThrows;
import manager.CategoryElementManager;
import manager.UserManager;
import model.CategoryElement;
import model.User;
import service.UserService;
import systemMessages.Message;
import view.UserView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserCotrollerServlet extends HttpServlet {
    private UserService userService;
    private UserManager userManager;
    private CategoryElementManager categoryElementManager;
    public void init() {
        userService = new UserService();
        userManager = new UserManager();
        categoryElementManager=new CategoryElementManager();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        doGet(request, response);
    }

    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getServletPath();
        switch (action) {
            case "/user/register":
                List<String> roles=categoryElementManager.getUserRoles();
                List<UserView> userViews=userManager.readManagers();
                request.setAttribute("userViews",userViews);
                request.setAttribute("roles",roles);
                RequestDispatcher requestDispatcher=request.getRequestDispatcher("/register.jsp");
                requestDispatcher.forward(request,response);
                break;
            case "/user/add":
                addUser(request, response);
                break;
            case "/user/delete":
                deleteUser(request, response);
                break;
            case "/user/update":
                updateUser(request, response);
                break;
            case "/user/edit":
                editFormPage(request, response);
                break;
            case "/user/activate":
                activateUser(request, response);
                break;
            case "/user/deactive":
                deactiveUser(request, response);
                break;
            case "/user/list":
                listUser(request, response);
                break;
        }
    }

    private void deactiveUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userManager.deactiveUser(request,response);
        response.sendRedirect("list");
    }


    private void activateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userManager.activateUser(request,response);
        response.sendRedirect("list");
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) {
        List<String> errors = null;
        try {
            errors = userManager.addUser(request, response);
            if (errors.isEmpty()) {
                response.sendRedirect("list");
            } else {
                List<String> roles=categoryElementManager.getUserRoles();
                List<UserView> userViews=userManager.readManagers();
                request.setAttribute("userViews",userViews);
                request.setAttribute("roles",roles);
                request.setAttribute("errors",errors);
                RequestDispatcher requestDispatcher=request.getRequestDispatcher("/register.jsp");
                requestDispatcher.forward(request,response);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        userManager.delete(request, response);
        response.sendRedirect("list");
    }

    private void editFormPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        UserView userView =new UserView();
        RequestDispatcher dispatcher = request.getRequestDispatcher("/edit.jsp");
        request.setAttribute("userView", userView.userView(userService.getUser(id)));
        List<String> roles=categoryElementManager.getUserRoles();
        List<UserView> userViews=userManager.readManagers();
        request.setAttribute("userViews",userViews);
        request.setAttribute("roles",roles);
        dispatcher.forward(request, response);
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<String> errors = null;
        try {
            errors = userManager.updateUser(request, response);
            if (errors.isEmpty()) {
                response.sendRedirect("list");
            } else {
                Long id = Long.parseLong(request.getParameter("id"));
                UserView userView =new UserView();
                request.setAttribute("userView", userView.userView(userService.getUser(id)));
                List<String> roles=categoryElementManager.getUserRoles();
                List<UserView> userViews=userManager.readManagers();
                request.setAttribute("userViews",userViews);
                request.setAttribute("roles",roles);
                request.setAttribute("errors",errors);
                RequestDispatcher requestDispatcher=request.getRequestDispatcher("/edit.jsp");
                requestDispatcher.forward(request,response);
            }
        } catch (Exception | UpdateException exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        userManager.readUsers(request, response);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list.jsp");
        dispatcher.forward(request, response);
    }
}
