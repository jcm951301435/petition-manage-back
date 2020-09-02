package com.ssy.petition.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author jcm95
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelBook {

    /**
     * 标题
     * @return
     */
    String title();

    /**
     * 是否显示序号
     * @return
     */
    boolean showIndex() default true;

    /**
     * 导入模板起始行标
     * @return
     */
    int importRowBeginCount() default 3;

    /**
     * 导入列起始
     * @return
     */
    int importColBeginCount() default 0;

}
