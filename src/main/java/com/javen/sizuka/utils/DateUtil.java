package com.javen.sizuka.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by javen on 2017/12/2.
 */
public class DateUtil {
    public static Map<String, Date> processOneDate(Date date) {
        Map<String, Date> map = new HashMap<String, Date>();
        Calendar start = Calendar.getInstance();
        start.setTime(date);
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        map.put("start", start.getTime());
        Calendar end = Calendar.getInstance();
        end.setTime(date);
        end.set(Calendar.HOUR_OF_DAY, 23);
        end.set(Calendar.MINUTE, 59);
        end.set(Calendar.SECOND, 59);
        map.put("end", end.getTime());
        return map;
    }
}
