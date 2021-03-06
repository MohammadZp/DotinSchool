package model;

import com.sun.istack.*;
import org.hibernate.envers.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Table(name = "t_users")
@Audited(targetAuditMode = NOT_AUDITED)
public class User extends model.Entity {
    @Column(name = "c_nationalCode")
    @NotAudited
    private Long nationalCode;
    @Column(name = "c_username")
    private String username;
    @Column(name = "c_password")
    @NotAudited
    private String password;
    @Column(name = "c_emailAddress")
    @NotAudited
    private String emailAddress;
    @Nullable
    @ManyToOne
    @JoinColumn(name = "c_managerId")
    private User manager;
    @ManyToOne
    @JoinColumn(name = "c_role")
    private CategoryElement role;
    @Column(name = "c_firstname")
    private String firstName;
    @Column(name = "c_lastname")
    private String lastName;

    public User() {
    }

    public Long getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(Long nationalCode) {
        this.nationalCode = nationalCode;
    }

    public CategoryElement getRole() {
        return role;
    }

    public void setRole(CategoryElement role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

}
