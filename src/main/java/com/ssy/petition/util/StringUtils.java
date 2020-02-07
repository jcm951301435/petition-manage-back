package com.ssy.petition.util;

/**
 * @author jcm
 */
public class StringUtils extends org.springframework.util.StringUtils {

    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }

}
