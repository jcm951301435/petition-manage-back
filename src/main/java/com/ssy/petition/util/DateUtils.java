package com.ssy.petition.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author jcm
 */
public class DateUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);

    private final static String HYPHEN_LONG_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private final static String HYPHEN_SHORT_PATTERN = "yyyy-MM-dd";

    private final static String HYPHEN = "-";

    private final static String SLASH = "/";

    private final static String COLON = ":";

    private final static String SLASH_LONG_PATTERN = "yyyy/MM/dd HH:mm:ss";

    private final static String SLASH_SHORT_PATTERN = "yyyy/MM/dd";

    public static Date parseDate(String source, String pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.parse(source);
    }

    public static Date parseDate(String source) throws ParseException {
        return parseDate(source, HYPHEN_LONG_PATTERN);
    }

    public static Date parseDateAuto(String source) throws ParseException {
        String pattern = null;
        if (checkHyphen(source)) {
            if (checkColon(source)) {
                pattern = HYPHEN_LONG_PATTERN;
            } else {
                pattern = HYPHEN_SHORT_PATTERN;
            }
        } else if (checkSlash(source)) {
            if (checkColon(source)) {
                pattern = SLASH_LONG_PATTERN;
            } else {
                pattern = SLASH_SHORT_PATTERN;
            }
        }
        if (pattern == null) {
            LOGGER.error("there is no such pattern to parse[" + source + "}");
            return null;
        } else {
            return parseDate(source, pattern);
        }
    }

    public static Boolean checkHyphen(String source) {
        return source.contains(HYPHEN);
    }

    public static Boolean checkSlash(String source) {
        return source.contains(SLASH);
    }

    public static Boolean checkColon(String source) {
        return source.contains(COLON);
    }

    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    public static String formatDate(Date date) {
        return formatDate(date, HYPHEN_LONG_PATTERN);
    }

    public static Date now() {
        return new Date();
    }

    public static String nowStr() {
        return formatDate(now());
    }

    public static Date today() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.getTime();
        return calendar.getTime();
    }

    public static Date add(Date date, int amount, int field) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    public static Date addDay(Date date, int days) {
        return add(date, days, Calendar.DAY_OF_MONTH);
    }

}
