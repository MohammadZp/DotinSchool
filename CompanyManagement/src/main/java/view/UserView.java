package view;

import model.CategoryElement;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class UserView {
    private Long id;
    private String nationalCode;
    private String creationDate;
    private String modificationDate;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String emailAddress;
    private String manager;
    private String role;
    private boolean enable;
    private boolean active;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserView userView(User user) {
        UserView userView = new UserView();
        userView.setId(user.getId());
        userView.setUsername(user.getUsername());
        userView.setCreationDate(user.getCreationDate());
        userView.setEmailAddress(user.getEmailAddress());
        userView.setFirstName(user.getFirstName());
        userView.setLastName(user.getLastName());
        userView.setPassword(user.getPassword());
        if (user.getManager() != null) {
            userView.setManager(user.getManager().getUsername().toString());
        } else {
            userView.setManager("");
        }
        userView.setNationalCode(user.getNationalCode().toString());
        userView.setRole(user.getRole().getName().toString());
        userView.setModificationDate(user.getModificationDate());
        userView.setActive(user.isActive());
        userView.setEnable(user.isEnable());
        return userView;
    }

    public List<UserView> userViewList(List<User> userList) {
        List<UserView> userViews = new ArrayList<>();
        for (User user : userList) {
            UserView userView = new UserView();
            userView.setId(user.getId());
            userView.setUsername(user.getUsername());
            userView.setCreationDate(user.getCreationDate());
            userView.setEmailAddress(user.getEmailAddress());
            userView.setFirstName(user.getFirstName());
            userView.setPassword(user.getPassword());
            userView.setLastName(user.getLastName());
            if (user.getManager() != null) {
                userView.setManager(user.getManager().getUsername());
            } else {
                userView.setManager("");
            }
            userView.setNationalCode(user.getNationalCode().toString());
            userView.setRole(user.getRole().getName().toString());
            userView.setModificationDate(user.getModificationDate());
            userView.setActive(user.isActive());
            userView.setEnable(user.isEnable());
            userViews.add(userView);
        }
        return userViews;
    }
}
