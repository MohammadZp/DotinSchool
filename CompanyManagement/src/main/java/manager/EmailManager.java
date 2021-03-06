package manager;

import dao.CategoryElementDao;
import exception.RecieversEmailException;
import exception.SenderEmailNotFoundException;
import model.*;
import org.apache.commons.io.IOUtils;
import service.EmailService;
import systemMessages.Message;
import model.Attachment;
import view.EmailView;

import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class EmailManager {
    CategoryElementManager categoryElementManager = new CategoryElementManager();
    EmailService emailService = new EmailService();

    public List<String> sendEmail(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        String subject = request.getParameter("subject");
        String EmailRecievers = request.getParameter("EmailRecievers");
        List<String> recievers = EmailRecieversList(EmailRecievers);
        User senderEmail = (User) request.getSession().getAttribute("user");
        String text = request.getParameter("emailText");
        Email email = new Email();
        List<Attachment> attachments = new ArrayList<>();
        List<String> errors = new ArrayList<>();
        for (Part part : request.getParts()) {
            if (part.getName().equals("attachments")) {
                byte[] file = IOUtils.toByteArray(part.getInputStream());
                Attachment attachment = new Attachment();
                attachment.setAttachment(file);
                attachment.setFileName(part.getName());
                attachment.setFormat(part.getContentType());
                attachments.add(attachment);
            }
        }
        CategoryElement emailStatus = categoryElementManager.objectOfName(Message.EMAIL_NOT_SEEN_YET);
        try {
            checkRecieversEmailValidation(recievers);
            List<User> recieverUsers = getUserRecievers(recievers);
            email.setUserSender(senderEmail);
            email.setUserRecieved(recieverUsers);
            email.setEnable(true);
            email.setActive(true);
            email.setSubject(subject);
            email.setAttachments(attachments);
            email.setText(text);
            email.setCreationDate(Message.getSolarDate(new Date()));
            email.setEmailStatus(emailStatus);
        } catch (Exception exception) {
            errors.add(exception.getMessage());
        }

        if (errors.isEmpty()) {
            emailService.create(email);
        }
        return errors;
    }

    private List<String> EmailRecieversList(String emailRecievers) {
        String[] str = emailRecievers.split(",");
        return Arrays.asList(str);
    }

    private void checkRecieversEmailValidation(List<String> recievers) throws RecieversEmailException {
        System.out.println(recievers);
        if (getUserRecievers(recievers).isEmpty()) {
            throw new RecieversEmailException(Message.RECIEVERS_EMAIL_NOT_FOUND_MESSAGE);
        }
    }

    public List<User> getUserRecievers(List<String> recievers) {
        List<User> userRecievers = new ArrayList<>();
        for (String s : recievers) {
            userRecievers.add(emailService.getUserByUsername(s));
        }
        return userRecievers;
    }

    public User getUserByEmail(String s) {
        return emailService.getUserByEmail(s);
    }


    public void update(Email email) {
        emailService.update(email);
    }

    public List<Email> readInbox(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        List<Email> inboxEmailList = emailService.getInboxEmails(user.getId());
        EmailView emailView = new EmailView();
        List<EmailView> inboxEmailViewList = emailView.getEmailViewList(inboxEmailList);
        req.setAttribute("inboxEmailViewList", inboxEmailViewList);
        return inboxEmailList;
    }

    public List<Email> readSentEmails(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        List<Email> sentEmailList = emailService.getSentEmails(user.getId());
        EmailView emailView = new EmailView();
        List<EmailView> sentEmailViewList = emailView.getEmailViewList(sentEmailList);
        req.setAttribute("sentEmailViewList", sentEmailViewList);
        return sentEmailList;
    }

    public Email getEmailById(HttpServletRequest req, HttpServletResponse resp) {
        String emailId = req.getParameter("id");
        Email email = emailService.getEmail(Long.parseLong(emailId));
        long senderId = email.getUserSender().getId();
        User user = (User) req.getSession().getAttribute("user");
        long userId = user.getId();
        if (userId != senderId) {
            CategoryElementDao categoryElementDao = new CategoryElementDao();
            CategoryElement emailStatus = categoryElementDao.ObjectOfname("خوانده شد");
            email.setEmailStatus(emailStatus);
            emailService.update(email);
        }
        EmailView emailView = new EmailView();
        req.setAttribute("emailView", emailView.getEmailView(email));
        return email;
    }
}
