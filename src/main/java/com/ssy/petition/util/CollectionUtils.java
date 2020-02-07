package com.ssy.petition.util;


import java.util.Collection;

/**
 * 集合处理工具类
 *
 * @author jcm
 */
public class CollectionUtils extends org.springframework.util.CollectionUtils {

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

}
