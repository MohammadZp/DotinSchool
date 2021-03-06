package view;

import model.Attachment;
import model.Email;

import java.util.ArrayList;
import java.util.List;

public class EmailView {
    private Long id;
    private boolean Enable;
    private boolean active;
    private String creationDate;
    private String modificationDate;
    private String subject;
    private String text;
    private List<Attachment> attachments;
    private String emailStatus;
    private String senderUsername;
    private String senderFirstname;
    private String senderLastname;
    private List<UserView> userViewRecieved;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(String emailStatus) {
        this.emailStatus = emailStatus;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getSenderFirstname() {
        return senderFirstname;
    }

    public void setSenderFirstname(String senderFirstname) {
        this.senderFirstname = senderFirstname;
    }

    public String getSenderLastname() {
        return senderLastname;
    }

    public void setSenderLastname(String senderLastname) {
        this.senderLastname = senderLastname;
    }

    public List<UserView> getUserViewRecieved() {
        return userViewRecieved;
    }

    public void setUserViewRecieved(List<UserView> userViewRecieved) {
        this.userViewRecieved = userViewRecieved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEnable() {
        return Enable;
    }

    public void setEnable(boolean enable) {
        Enable = enable;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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

    public EmailView getEmailView(Email email){
        EmailView emailView=new EmailView();
        UserView userView=new UserView();
        emailView.setId(email.getId());
        emailView.setSubject(email.getSubject());
        emailView.setText(email.getText());
        emailView.setCreationDate(email.getCreationDate());
        emailView.setModificationDate(email.getModificationDate());
        emailView.setActive(email.isActive());
        emailView.setEnable(email.isEnable());
        emailView.setSenderFirstname(email.getUserSender().getFirstName());
        emailView.setSenderUsername(email.getUserSender().getUsername());
        emailView.setSenderLastname(email.getUserSender().getLastName());
        emailView.setAttachments(email.getAttachments());
        emailView.setEmailStatus(email.getEmailStatus().getName());
        emailView.setUserViewRecieved(userView.userViewList(email.getUserRecieved()));
        return emailView;
    }
    public List<EmailView> getEmailViewList(List<Email> emails){
        List<EmailView> emailViewList=new ArrayList<>();
        for(Email email:emails) {
            EmailView emailView = new EmailView();
            UserView userView = new UserView();
            emailView.setId(email.getId());
            emailView.setSubject(email.getSubject());
            emailView.setText(email.getText());
            emailView.setCreationDate(email.getCreationDate());
            emailView.setModificationDate(email.getModificationDate());
            emailView.setActive(email.isActive());
            emailView.setEnable(email.isEnable());
            emailView.setAttachments(email.getAttachments());
            emailView.setEmailStatus(email.getEmailStatus().getName());
            emailView.setUserViewRecieved(userView.userViewList(email.getUserRecieved()));
            emailView.setSenderFirstname(email.getUserSender().getFirstName());
            emailView.setSenderUsername(email.getUserSender().getUsername());
            emailView.setSenderLastname(email.getUserSender().getLastName());
            emailViewList.add(emailView);
        }
        return emailViewList;
    }
}
