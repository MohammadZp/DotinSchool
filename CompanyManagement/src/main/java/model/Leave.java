package model;

import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

import static org.hibernate.envers.RelationTargetAuditMode.NOT_AUDITED;

@Entity
@Table(name = "t_leaves")
@Audited(targetAuditMode = NOT_AUDITED)
public class Leave extends model.Entity {
    @Column(name = "c_fromDate")
    private String fromDate;
    @Column(name = "c_toDate")
    private String toDate;
    @Column(name = "c_fromTime")
    private String fromTime;
    @Column(name = "c_toTime")
    private String toTime;
    @Column(name = "c_type")
    @NotAudited
    private boolean Daily;
    @ManyToOne
    @JoinColumn(name = "c_leaveStatus")
    private CategoryElement leaveStatus;
    @ManyToOne
    @JoinColumn(name = "c_userLeaveRequsterId")
    private User userLeaveRequester;

    public Leave() {
    }

    public CategoryElement getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(CategoryElement leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public User getUserLeaveRequester() {
        return userLeaveRequester;
    }

    public void setUserLeaveRequester(User userLeaveRequester) {
        this.userLeaveRequester = userLeaveRequester;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public boolean isDaily() {
        return Daily;
    }

    public void setDaily(boolean daily) {
        this.Daily = daily;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
