package model;

import currentUser.CurrentUser;
import model.Revision;
import model.User;
import org.hibernate.envers.RevisionListener;

import javax.servlet.http.HttpServletRequest;


public class MyRevisionListener implements RevisionListener {
    @Override
    public void newRevision(Object revisionEntity) {
        Revision myRevision = (Revision) revisionEntity;
        myRevision.setUsername(CurrentUser.USERNAME);
        myRevision.setFirstname(CurrentUser.FIRSTNAME);
        myRevision.setLastname(CurrentUser.LASTNAME);
    }
}