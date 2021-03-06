package controller;

import manager.LoginManager;
import systemMessages.Message;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UserLoginServlet extends HttpServlet {
    LoginManager loginManager=new LoginManager();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
               if (loginManager.loginCheck(req,resp)){
                resp.sendRedirect("index");
       }else{
                   req.setAttribute("error",Message.INVALID_LOGIN);
                   RequestDispatcher requestDispatcher=req.getRequestDispatcher("/loginPage.jsp");
                   requestDispatcher.forward(req,resp);
       }
    }
}
