package service;

import dao.LeaveDao;
import dao.UserDao;
import exception.UpdateException;
import exception.UpdateLeaveException;
import model.Leave;
import model.User;

import java.util.List;

public class LeaveService {
    private LeaveDao leaveDao = new LeaveDao();

    public void create(Leave leave) {
        leaveDao.create(leave);
    }

    public List<Leave> read() {
        return leaveDao.read();
    }

    public void update(Leave leave) throws UpdateException {
        leaveDao.update(leave);
    }

    public void delete(Long id) {
        leaveDao.delete(id);
    }

    public Leave getLeave(Long id) {
        return leaveDao.getLeave(id);
    }

    public List<Leave> getLeaves(Long id) {
        return leaveDao.getLeaves(id);
    }

    public void activate(Long id) {
        leaveDao.activate(id);
    }

    public void deActivate(Long id) {
        leaveDao.deActivate(id);
    }

    public void reject(Long id) {
        leaveDao.reject(id);
    }

    public void accept(Long id) {
        leaveDao.accept(id);
    }

    public List<Leave> readTeamLeaves(Long managerId) {
        return leaveDao.readTeamLeaves(managerId);
    }

    public List<Leave> readLeavesByAdmin() {
        return leaveDao.readLeavesByAdmin();
    }
}
