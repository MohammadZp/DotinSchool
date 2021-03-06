package view;

import model.CategoryElement;
import model.Leave;
import model.User;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

public class LeaveView {
    private Long id;
    private Long userRequesterId;
    private Long managerId;
    private boolean type;
    private String fromDate;
    private String toDate;
    private String fromTime;
    private String toTime;
    private String creationDate;
    private String modificationDate;
    private String leaveStatus;
    private String LeaveRequesterUsername;
    private String LeaveRequesterFirstName;
    private String LeaveRequesterLastName;
    private boolean active;
    private boolean enable;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getUserRequesterId() {
        return userRequesterId;
    }

    public void setUserRequesterId(Long userRequesterId) {
        this.userRequesterId = userRequesterId;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
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

    public void setToTime(String toTime) {
        this.toTime = toTime;
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

    public String getLeaveStatus() {
        return leaveStatus;
    }

    public void setLeaveStatus(String leaveStatus) {
        this.leaveStatus = leaveStatus;
    }

    public String getLeaveRequesterUsername() {
        return LeaveRequesterUsername;
    }

    public void setLeaveRequesterUsername(String leaveRequesterUsername) {
        LeaveRequesterUsername = leaveRequesterUsername;
    }

    public String getLeaveRequesterFirstName() {
        return LeaveRequesterFirstName;
    }

    public void setLeaveRequesterFirstName(String leaveRequesterFirstName) {
        LeaveRequesterFirstName = leaveRequesterFirstName;
    }

    public String getLeaveRequesterLastName() {
        return LeaveRequesterLastName;
    }

    public void setLeaveRequesterLastName(String leaveRequesterLastName) {
        LeaveRequesterLastName = leaveRequesterLastName;
    }

    public LeaveView getLeaveView(Leave leave){
        LeaveView leaveView=new LeaveView();
        leaveView.setId(leave.getId());
        if(leave.getUserLeaveRequester().getManager()!=null){
            leaveView.setManagerId(leave.getUserLeaveRequester().getManager().getId());}
        leaveView.setCreationDate(leave.getCreationDate());
        leaveView.setFromDate(leave.getFromDate());
        leaveView.setToDate(leave.getToDate());
        leaveView.setEnable(leave.isEnable());
        leaveView.setType(leave.isDaily());
        leaveView.setActive(leave.isActive());
        leaveView.setFromTime(leave.getFromTime());
        leaveView.setToTime(leave.getToTime());
        leaveView.setModificationDate(leave.getModificationDate());
        leaveView.setLeaveRequesterFirstName(leave.getUserLeaveRequester().getFirstName());
        leaveView.setLeaveRequesterLastName(leave.getUserLeaveRequester().getLastName());
        leaveView.setLeaveRequesterUsername(leave.getUserLeaveRequester().getUsername());
        leaveView.setLeaveStatus(leave.getLeaveStatus().getName());
        return  leaveView;
    }

    public List<LeaveView> getLeaveViewList(List<Leave> leaveList) {
        List<LeaveView> leaveViewList=new ArrayList<>();
        for(Leave leave:leaveList){
            LeaveView leaveView=new LeaveView();
            leaveView.setId(leave.getId());
            if(leave.getUserLeaveRequester().getManager()!=null){
                leaveView.setManagerId(leave.getUserLeaveRequester().getManager().getId());}
            leaveView.setCreationDate(leave.getCreationDate());
            leaveView.setFromDate(leave.getFromDate());
            leaveView.setToDate(leave.getToDate());
            leaveView.setUserRequesterId(leave.getUserLeaveRequester().getId());
            leaveView.setFromTime(leave.getFromTime());
            leaveView.setEnable(leave.isEnable());
            leaveView.setActive(leave.isActive());
            leaveView.setToTime(leave.getToTime());
            leaveView.setType(leave.isDaily());
            leaveView.setModificationDate(leave.getModificationDate());
            leaveView.setLeaveRequesterFirstName(leave.getUserLeaveRequester().getFirstName());
            leaveView.setLeaveRequesterLastName(leave.getUserLeaveRequester().getLastName());
            leaveView.setLeaveRequesterUsername(leave.getUserLeaveRequester().getUsername());
            leaveView.setLeaveStatus(leave.getLeaveStatus().getName());
            leaveViewList.add(leaveView);
        }
        return leaveViewList;
    }

    @Override
    public String toString() {
        return "LeaveView{" +
                "id=" + id +
                ", userRequesterId=" + userRequesterId +
                ", managerId=" + managerId +
 //               ", fromDate='" + fromDate + '\'' +
//                ", toDate='" + toDate + '\'' +
//                ", fromTime='" + fromTime + '\'' +
//                ", toTime='" + toTime + '\'' +
//                ", creationDate='" + creationDate + '\'' +
//                ", modificationDate='" + modificationDate + '\'' +
//                ", leaveStatus='" + leaveStatus + '\'' +
//                ", LeaveRequesterUsername='" + LeaveRequesterUsername + '\'' +
//                ", LeaveRequesterFirstName='" + LeaveRequesterFirstName + '\'' +
//                ", LeaveRequesterLastName='" + LeaveRequesterLastName + '\'' +
//                ", active=" + active +
//                ", enable=" + enable +
                '}';
    }
}
