package net.sotree.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by lims on 2015. 8. 24..
 * DateUtil 클래스
 */
public class DateUtil {

    public static Date toDate(String strDate) {
        if (strDate == null) {
            return null;
        }

        if (strDate.length() == 10) {
            return toDate(strDate, "yyyy-MM-dd");
        } else if (strDate.length() == 16) {
            return toDate(strDate, "yyyy-MM-dd HH:mm");
        } else if (strDate.length() == 19) {
            return toDate(strDate, "yyyy-MM-dd HH:mm:ss");
        }

        return null;
    }

    public static Date toDate(String strDate, String strDateFormat) {
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat, Locale.US);

        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date;
    }
}
