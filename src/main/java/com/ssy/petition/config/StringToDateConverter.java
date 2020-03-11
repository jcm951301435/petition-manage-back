package com.ssy.petition.config;


import com.ssy.petition.util.DateUtils;
import com.ssy.petition.util.StringUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

/**
 * @author jcm
 */
public class StringToDateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String source) {
        if (StringUtils.isEmpty(source)) {
            return null;
        }
        source = source.trim();
        try {
            return DateUtils.parseDateAuto(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
