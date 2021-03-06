package service;

import dao.LoginDao;

public class LoginService {
    LoginDao loginDao=new LoginDao();
    public boolean checkLogin(String username, String password) {
        return loginDao.checkLogin(username,password);
    }
}
