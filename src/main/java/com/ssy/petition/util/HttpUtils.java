package com.ssy.petition.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.EnumSet;

public class HttpUtils {

    public enum BrowserType {
        /*浏览器类型*/
        FIRE_FOX("FIREFOX"),
        IE("MSIE"),
        CHROME("CHROME");

        private String code;

        BrowserType(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }

    public static BrowserType getBrowserType(HttpServletRequest request) {
        String userAgent = request.getHeader("User-Agent").toUpperCase();
        EnumSet<BrowserType> enumSet = EnumSet.allOf(BrowserType.class);
        BrowserType result = null;
        for (BrowserType type : enumSet) {
            if (userAgent.contains(type.getCode())) {
                result = type;
                break;
            }
        }
        return result;
    }

    public static void downLoadResponse(String title, HttpServletResponse response) {
        response.setHeader("Content-type", "application/vnd.ms-excel");
        response.setCharacterEncoding("UTF-8");
        try {
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(title, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
