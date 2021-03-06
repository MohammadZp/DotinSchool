package model;

import model.Attachment;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;
import javax.persistence.Table;

@Entity
@Table(name = "t_emails")
public class Email extends model.Entity {
    @Column(name = "c_subject")
    private String subject;
    @Lob
    @Column(name = "c_emailtext")
    private String text;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(nullable = false)
    private List<Attachment> attachments;
    @ManyToOne
    @JoinColumn(name = "c_emailstatus")
    private CategoryElement emailStatus;
    @ManyToOne
    @JoinColumn(name = "c_userSender")
    private User userSender;
    @ManyToMany(targetEntity = User.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "t_userRecievedEmails",
            joinColumns = {@JoinColumn(name = "c_emailId")},
            inverseJoinColumns = {@JoinColumn(name = "c_userId")}
    )
    private List<User> userRecieved;

    public Email() {
    }

    public User getUserSender() {
        return userSender;
    }

    public void setUserSender(User userSender) {
        this.userSender = userSender;
    }

    public List<User> getUserRecieved() {
        return userRecieved;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public void setUserRecieved(List<User> userRecieved) {
        this.userRecieved = userRecieved;
    }

    public CategoryElement getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(CategoryElement emailStatus) {
        this.emailStatus = emailStatus;
    }

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

}
