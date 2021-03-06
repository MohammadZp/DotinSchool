package controller;

import manager.CategoryElementManager;
import manager.UserManager;
import model.CategoryElement;
import view.UserView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PageController extends HttpServlet {
    CategoryElementManager categoryElementManager=new CategoryElementManager();
    UserManager userManager=new UserManager();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        request.setCharacterEncoding("UTF-8");
        switch (action) {
            case "/register":
                RequestDispatcher requestDispatcher=request.getRequestDispatcher("/register.jsp");
                List<String> roles=categoryElementManager.getUserRoles();
                List<UserView> userViews=userManager.readManagers();
                request.setAttribute("userViews",userViews);
                request.setAttribute("roles",roles);
                requestDispatcher.forward(request,response);
                break;
            case "/leaveRequest":
                RequestDispatcher dispatcher = request.getRequestDispatcher("/leaveRequest.jsp");
                dispatcher.forward(request, response);
                break;
            case "/composeEmail":
                RequestDispatcher rd = request.getRequestDispatcher("/composeEmail.jsp");
                List<UserView> userviewList=userManager.readUsers(request,response);
                request.setAttribute("userViews",userviewList);
                rd.forward(request, response);
                break;
            case "/LoginPage":
               response.sendRedirect("loginPage.jsp");
                break;
            case "/index":
                response.sendRedirect("index.jsp");
                break;
        }
    }
}



