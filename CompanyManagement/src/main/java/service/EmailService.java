package service;

import dao.EmailDao;
import dao.LeaveDao;
import exception.UpdateException;
import model.Email;
import model.Leave;
import model.User;

import java.util.List;

public class EmailService {
    private EmailDao emailDao = new EmailDao();

    public void create(Email email) {
        emailDao.create(email);
    }

    public List<Email> read() {
        return emailDao.read();
    }

    public void update(Email email) throws UpdateException {
        emailDao.update(email);
    }

    public void delete(Long id) {
        emailDao.delete(id);
    }

    public Email getEmail(Long id) {
        return emailDao.getEmail(id);
    }
    public List<Email> getInboxEmails(Long id) {
        return emailDao.getInboxEmails(id);
    }

    public List<Email> getSentEmails(Long id) {
        return emailDao.getSentEmails(id);
    }

    public User getUserByUsername(String s) {
        return emailDao.getUserByUsername(s);
    }
}

