package service;

import dao.UserDao;
import exception.UpdateException;
import model.User;
import password.MD5Password;

import java.util.List;

public class UserService {
    private UserDao userDao = new UserDao();

    public void create(User user) {
        userDao.create(user);
    }

    public List<User> read() {
        return userDao.read();
    }

    public void update(User user) throws UpdateException {
        userDao.update(user);
    }

    public void delete(Long id) {
        userDao.delete(id);
    }

    public User getUser(Long id) {
        return userDao.getUser(id);
    }

    public User getUser(String username, String password) {
        return userDao.getUser(username,password);
    }

    public void deactivate(Long id) {
         userDao.deactivate(id);
    }
    public void activate(Long id) {
        userDao.activate(id);
    }

    public boolean checkDistinctUsername(String username) {
        return userDao.checkDistinctUsername(username);
    }

    public boolean checkUsernameValidationforUpdate(String username, Long id) {
       return userDao.checkUsernameValidationforUpdate(username,id);
    }
}
