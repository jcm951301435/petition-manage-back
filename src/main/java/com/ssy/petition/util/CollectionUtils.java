package com.ssy.petition.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * 集合处理工具类
 *
 * @author jcm
 */
public class CollectionUtils extends org.springframework.util.CollectionUtils {

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static List<String> arrayStrToList(String source) {
        List<String> result = new ArrayList<String>();
        if (source == null) {
            return result;
        }
        source = source.replaceAll("\\[", "");
        source = source.replaceAll("]", "");
        if (source.length() <= 0) {
            return result;
        }
        String[] array = source.split(",");
        Collections.addAll(result, array);
        return result;
    }

    public static String[] listToArray(List<String> list) {
        if (isEmpty(list)) {
            return new String[0];
        }
        String[] array = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public static String listToString(List<String> list) {
        if (isEmpty(list)) {
            return "[]";
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (String str : list) {
            stringBuilder.append(str);
            stringBuilder.append(",");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

}
