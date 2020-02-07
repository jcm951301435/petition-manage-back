package com.ssy.petition.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json 工具类
 *
 * @author jcm
 */
public class JsonUtils {

    private static ObjectMapper MAPPER = new ObjectMapper();

    /**
     * object -> string
     * @param object 源
     * @return 结果
     */
    public static String objectToJson(Object object) {
        String resultStr = null;
        try {
            resultStr = MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return resultStr;
    }

}
