package controller;

import manager.EmailManager;
import model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class EmailControllerServlet extends HttpServlet {
    EmailManager emailManager=new EmailManager();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();
        switch (action) {
            case "/email/send":
               sendEmail(req, resp);
                break;
            case "/email/inboxEmail":
                listInboxEmails(req, resp);
                break;
            case "/email/sentEmail":
                listSentEmails(req, resp);
                break;
            case "/email/read":
                readEmail(req, resp);
                break;
        }
    }

    private void sendEmail(HttpServletRequest request, HttpServletResponse response) {
        List<String> errors = null;
        try {
            errors = emailManager.sendEmail(request, response);
            if (errors.isEmpty()) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("errorMessages", errors);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/errors.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    private void readEmail(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        emailManager.getEmailById(req,resp);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/emailRead.jsp");
        dispatcher.forward(req, resp);

    }
    private void listInboxEmails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        emailManager.readInbox(req,resp);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/inbox.jsp");
        dispatcher.forward(req, resp);
    }

    private void listSentEmails(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        emailManager.readSentEmails(req,resp);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/sentEmails.jsp");
        dispatcher.forward(req, resp);
    }
}