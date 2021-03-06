import dao.CategoryElementDao;
import hibernateConfig.HibernateUtil;
import model.*;
import org.hibernate.Session;
import systemMessages.Message;

import java.util.Date;


public class Test {
    public static void main(String[] args) {
        Category category1 = new Category("Email");
        Category category2 = new Category("user");
        Category category3 = new Category("leave");
        CategoryElementDao categoryElementDao = new CategoryElementDao();

        //user
        CategoryElement categoryElement1 = new CategoryElement("تست کننده ی نرم افزار", Message.SOFTWARE_TESTER, category2);
        CategoryElement categoryElement2 = new CategoryElement("برنامه نویس فرانت اند", Message.FRONT_END_DEVELOPER, category2);
        CategoryElement categoryElement3 = new CategoryElement("برنامه نویس سرور", Message.BACK_END_DEVELOPER, category2);
        CategoryElement categoryElement4 = new CategoryElement("مهندس شبکه", Message.NETWORK_ENGINEER, category2);
        CategoryElement categoryElement5 = new CategoryElement("متخصص امنیت", Message.SECURITY_EXPERT, category2);
        CategoryElement categoryElement6 = new CategoryElement("کاوشگر داده", Message.DATA_MINER, category2);
        CategoryElement categoryElement7 = new CategoryElement("متخصص بانک اطلاعات", Message.DATABASE_EXPERT, category2);
        CategoryElement categoryElement8 = new CategoryElement("برنامه نویس اندورید", Message.ANDROID_DEVELOPER, category2);
        CategoryElement categoryElement9 = new CategoryElement("متخصص علم داده", Message.DATA_SCIENTIST, category2);
        CategoryElement categoryElement10 = new CategoryElement("مدیر سایت", Message.ADMIN, category2);
        CategoryElement categoryElement11 = new CategoryElement("نیروی کارآموز", Message.INTERN, category2);
//leave
        CategoryElement categoryElement12 = new CategoryElement("هنوز دیده نشده", Message.NOT_SEEN, category3);
        CategoryElement categoryElement13 = new CategoryElement("دیده شده (در حال بررسی)", Message.SEEN, category3);
        CategoryElement categoryElement14 = new CategoryElement("با این درخواست موافقت نشد", Message.REJECTED, category3);
        CategoryElement categoryElement15 = new CategoryElement("با این درخواست موافقت شد", Message.ACCEPTED, category3);
        //email

        CategoryElement categoryElement16 = new CategoryElement("هنوز خوانده نشده", Message.EMAIL_NOT_SEEN_YET, category1);
        CategoryElement categoryElement17 = new CategoryElement("خوانده شد", Message.EMAIL_SEEN, category1);

        User user = new User();
        user.setActive(true);
        user.setEnable(true);
        user.setCreationDate(Message.getSolarDate(new Date()));
        user.setRole(categoryElement1);
        user.setNationalCode((long) 12345);
        user.setUsername("Mohammad");
        user.setFirstName("Mohammad");
        user.setLastName("Zahedipour");
        user.setPassword("1");
        user.setEnable(true);
        user.setModificationDate(Message.NEVER_MODIFIED_MESSAGE);
        user.setEmailAddress("zahedipourmohammad@gmail.com");
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(category2);
        session.save(category3);
        session.save(category1);
        session.save(categoryElement1);
        session.save(categoryElement2);
        session.save(categoryElement3);
        session.save(categoryElement4);
        session.save(categoryElement5);
        session.save(categoryElement6);
        session.save(categoryElement7);
        session.save(categoryElement8);
        session.save(categoryElement9);
        session.save(categoryElement10);
        session.save(categoryElement11);
        session.save(categoryElement12);
        session.save(categoryElement13);
        session.save(categoryElement14);
        session.save(categoryElement15);
        session.save(categoryElement16);
        session.save(categoryElement17);
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }
}