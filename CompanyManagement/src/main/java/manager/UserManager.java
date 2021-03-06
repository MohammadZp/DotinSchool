package manager;

import com.ghasemkiani.util.icu.PersianCalendar;
import exception.*;
import hibernateConfig.HibernateUtil;
import model.CategoryElement;
import model.User;
import org.hibernate.Session;
import password.PasswordValidation;
import service.UserService;
import systemMessages.Message;
import view.UserView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class UserManager {
    private UserService userService = new UserService();
    private CategoryElementManager categoryElementManager = new CategoryElementManager();

    public List<String> addUser(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        long nationalcode = Long.parseLong(request.getParameter("nationalcode"));
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String managerId = request.getParameter("managerId");
        String role = request.getParameter("role");
        CategoryElement categoryElement = categoryElementManager.objectOfName(role);
        List<String> errors = new ArrayList<>();
        User user = new User();
        errors.add(checkNationalCodeValidation(nationalcode));
        errors.add(checkUsernameValidation(username));
        errors.add(checkPasswordValidation(password));
        user.setNationalCode(nationalcode);
        user.setEmailAddress(email);
        user.setPassword(password);
        user.setUsername(username);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setRole(categoryElement);
        user.setEnable(true);
        user.setActive(true);
        user.setCreationDate(Message.getSolarDate(new Date()));
        user.setModificationDate(Message.NEVER_MODIFIED_MESSAGE);
        User manager = userService.getUser(Long.parseLong(managerId));
        user.setManager(manager);
        List<String> arr = new ArrayList<>();
        arr.add("");
        errors.removeAll(arr);
        if (errors.isEmpty()) userService.create(user);
        return errors;
    }

    private String checkUsernameValidation(String username) {
        if (!userService.checkDistinctUsername(username)) return Message.BAD_USERNAME;
        return "";
    }

    private String checkPasswordValidation(String password) {
        if (!PasswordValidation.isValidPassword(password)) return Message.INVALID_PASSWORD;
        return "";
    }

    private String checkNationalCodeValidation(long nationalcode) {
        String s = nationalcode + "";
        if (s.startsWith("0") || s.length() > 8) return Message.INVALID_NATIONAL_CODE;
        return "";
    }

    private void checkManagerIdValidation(String managerId) throws Exception {
        Long id = Long.parseLong(managerId);
        User manager = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        manager = session.get(User.class, id);
        session.getTransaction().commit();
        session.close();
        if (manager == null) {
            throw new ManagerNotFoundException("there is no user with id=" + id + " (check manager id)");
        }
    }

    public void delete(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("id"));
        userService.delete(id);
    }

    public List<String> updateUser(HttpServletRequest request, HttpServletResponse response) throws UpdateException {
        Long id = Long.parseLong(request.getParameter("id"));
        long nationalcode = Long.parseLong(request.getParameter("nationalcode"));
        String username = request.getParameter("username");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String managerId = request.getParameter("managerId");
        String role = request.getParameter("role");
        CategoryElement categoryElementRole = categoryElementManager.objectOfName(role);
        List<String> errors = new ArrayList<>();
        User user = new User();
        errors.add(checkNationalCodeValidation(nationalcode));
        errors.add(checkUsernameValidationforUpdate(username, id));
        errors.add(checkPasswordValidation(password));
        user.setId(id);
        user.setNationalCode(nationalcode);
        user.setEmailAddress(email);
        user.setPassword(password);
        user.setUsername(username);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setRole(categoryElementRole);
        user.setEnable(true);
        user.setActive(true);
        PersianCalendar persianCalendar = new PersianCalendar(new Date());
        user.setModificationDate(Message.getSolarDate(new Date()));
        User manager = userService.getUser(Long.parseLong(managerId));
        user.setManager(manager);
        List<String> arr = new ArrayList<>();
        arr.add("");
        errors.removeAll(arr);
        if (errors.isEmpty()) userService.update(user);
        return errors;
    }

    private String checkUsernameValidationforUpdate(String username, Long id) {
        if (!userService.checkUsernameValidationforUpdate(username, id)) {
            return Message.BAD_USERNAME;
        }
        return "";
    }

    public List<UserView> readUsers(HttpServletRequest request, HttpServletResponse response) {
        UserView userView = new UserView();
        List<UserView> userViewsList = userView.userViewList(userService.read());
        request.setAttribute("userViewsList", userViewsList);
        return userViewsList;
    }

    public List<UserView> readManagers() {
        UserView userView = new UserView();
        List<UserView> userViewsList = userView.userViewList(userService.read());
        return userViewsList;
    }


    public void deactiveUser(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("id"));
        userService.deactivate(id);
    }

    public void activateUser(HttpServletRequest request, HttpServletResponse response) {
        Long id = Long.parseLong(request.getParameter("id"));
        userService.activate(id);
    }

}


