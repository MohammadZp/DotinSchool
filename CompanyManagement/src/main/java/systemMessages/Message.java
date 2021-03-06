package systemMessages;

import com.ghasemkiani.util.icu.PersianCalendar;
import model.CategoryElement;
import model.User;
import view.UserView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Message {
    public final static String START_WORK_TIME="8:00";
    public final static String FINISH_WORK_TIME="16:00";
    public final static String LEAVE_DATE_EXCEPTION="LEAVE DATE SHOULD BE IN FUTURE!NOT PAST!";
    public static final String NEVER_MODIFIED_MESSAGE = "تا به حال به روز رسانی نشده است";
    public static final String UPDATE_EXCEPTION_MESSAGE = "Update failed! try again!";
    public static final String SENDER_EMAIL_NOT_FOUND_MESSAGE ="Sender Email Not found! check your email";
    public static final String RECIEVERS_EMAIL_NOT_FOUND_MESSAGE="invalid recievers email";
    public static final String INVALID_LOGIN ="کاربری با این مشخصات یافت نشد" ;
    public static final String CHECK_YOUR_DATE_ENTERED ="تاریخ های وارد شده صحیح نمی باشد";
    public static final String CHECK_YOUR_TIME_ENTERED = "ساعت های ورودی صحیح نمی باشد";
    public static final String INVALID_NATIONAL_CODE ="کد ملی معتبر نمی باشد.لطفا کد ملی را بدون صفر وارد کنید";
    public static final String BAD_USERNAME ="این نام کاربری قبلا توسط کاربر دیگری ثبت شده است لطفا نام کاربری دیگری ثبت کنید";
    public static final String NOT_ACCESSIBLE = "دسترسی شما  به این صفحه مجاز نیست";
    public static String SOFTWARE_TESTER="software tester";
    public static String FRONT_END_DEVELOPER="Front-End Developer";
    public static String BACK_END_DEVELOPER="Back-End Developer";
    public static String NETWORK_ENGINEER="Network Enginner";
    public static String SECURITY_EXPERT="security Expert";
    public static String DATA_MINER="Data miner";
    public static String DATABASE_EXPERT="DataBase Expert";
    public static String ANDROID_DEVELOPER="Android Developer";
    public static String DATA_SCIENTIST="Data scientist";
    public static String ADMIN="Admin";
    public static String INTERN="intern";
    public static String NOT_SEEN="هنوز دیده نشده";
    public static String SEEN="دیده شده (در حال بررسی)";
    public static String REJECTED="با این درخواست موافقت نشد";
    public static String ACCEPTED="با این درخواست موافقت شد";
    public static String EMAIL_NOT_SEEN_YET="هنوز خوانده نشده";
    public static String EMAIL_SEEN="خوانده شد";
    public static String INVALID_PASSWORD="کلمه ی عبور باید حداقل دارای 8 کاراکتر باشد.\n" +
            "کلمه ی عبور باید  حداقل دارای یک رقم عدد باشد.\n" +
            "کلمه ی عبور باید حداقل دارای یک حرف بزرگ لاتین باشد\n" +
            "کلمه ی عبور باید حداقل دارای یک حرف کوچک لاتین باشد.\n" +
            "در کلمه ی عبور خود از کاراکتر های ویژه مانند@ و % ,.. استفاده کنید.\n" +
            "استفاده از فضای خالی در بین کاراکتر های کلمه ی عبور مجاز نیست.";

    public static String deActive(String id) {
        return "the user with id="+id+" is not active!";
    }

    public static Object disable(String id) {
        return "the user with id="+id+" is disabled!";
    }

    public static String showListOfRecievers(List<UserView> userViewRecieved) {
        String s="";
        for(UserView userView:userViewRecieved){
            s=s+userView.getUsername()+",";
        }
        return s;
    }

    public static String getSolarDate(Date date){
        PersianCalendar persianCalendar=new PersianCalendar(date);
        int year= persianCalendar.get(Calendar.YEAR);
        int month = persianCalendar.get(Calendar.MONTH) + 1;
        int day = persianCalendar.get(Calendar.DAY_OF_MONTH);
        return year+"/"+month+"/"+day;
    }
}
