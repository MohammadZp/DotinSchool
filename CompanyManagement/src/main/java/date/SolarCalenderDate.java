package date;

import com.ibm.icu.util.PersianCalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SolarCalenderDate {
    private int year;
    private int month;
    private int day;


    public static String getSolarCalendarDate(Date date) {
        PersianCalendar persianCalendar = new PersianCalendar(date);
        int year = persianCalendar.get(Calendar.YEAR);
        int month = persianCalendar.get(Calendar.MONTH) + 1;
        int day = persianCalendar.get(Calendar.DAY_OF_MONTH);
        return year + "/" + month + "/" + day;
    }

    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy/MM/dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String getSolarDateStringFormat(String year, String month, String day) {
        return year + "/" + month + "/" + day;
    }

//    public static void main(String[] args) {
//        Date now = new Date();
//        String startDate = "1401/4/3";
//        String finishDate = "1401/4/5";
//        PersianCalendar p1 = new PersianCalendar();
//
//        //System.out.println(finishDate);
//        if (compareDate(startDate, finishDate) > 0) {
//            System.out.println(Message.CHECK_YOUR_DATE_ENTERED);
//        }
//    }


}