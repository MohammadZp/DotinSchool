package manager;

import currentUser.CurrentUser;
import model.User;
import service.LoginService;
import service.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginManager {
    LoginService loginService=new LoginService();
    UserService userService=new UserService();
    public boolean loginCheck(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username=request.getParameter("username");
        String password=request.getParameter("password");
        if(loginService.checkLogin(username,password)) {
            User user = userService.getUser(username, password);
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("user", user);
            CurrentUser.USERNAME=user.getUsername();
            CurrentUser.LASTNAME=user.getLastName();
            CurrentUser.FIRSTNAME=user.getFirstName();
            return true;
        }
            return false;

    }
}
