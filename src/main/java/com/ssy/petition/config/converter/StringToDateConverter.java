package com.ssy.petition.config.converter;


import com.ssy.petition.util.StringUtils;
import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.util.Date;

import static com.ssy.petition.util.DateUtils.parseDateAuto;

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
            return parseDateAuto(source);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
