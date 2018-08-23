package com.jp.app.utils;

import org.joda.time.DateTime;
import org.joda.time.Instant;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class StringUtils {

    static boolean isStringBlank(String str) {
        if (str == null) {
            return true;
        }
        return str.trim().length() == 0;
    }

    public static boolean isBlank(String str) {
        return isStringBlank(str);
    }

    public static  String parseTime(String time) {
        Instant instant = Instant.parse(time);
        DateTime dt = instant.toDateTime();
        DateTimeFormatter fmt = DateTimeFormat.forPattern(Constants.DATE_PATTERN);
        return fmt.print(dt);
    }

}
