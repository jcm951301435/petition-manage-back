package com.ssy.petition.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jcm95
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {

    /**
     * 字段名
     * @return
     */
    String[] text() default "";

    /**
     * 宽度
     * @return
     */
    int colWidth() default 20 * 256;

    /**
     * 字体颜色
     * @return
     */
    short color() default 1;

    int sort();

    ExcelFormatEnum format() default ExcelFormatEnum.NULL;

}
