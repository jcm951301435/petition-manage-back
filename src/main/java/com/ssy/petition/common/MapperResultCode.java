package com.ssy.petition.common;

/**
 * DAO 操作结果枚举
 * 暂不使用
 *
 * @author jcm
 */
@Deprecated
public enum MapperResultCode {

    /* 操作成功 */
    SUCCESS(1, "成功"),

    /* 操作失败 */
    FAILED(0, "失败"),

    /* 已存在 */
    EXISTED(-2, "已存在");

    private long code;

    private String message;

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    MapperResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    //public static MapperResultCode getMapperResultCode(int code) {
    //    return MapperResultCode.
    //}

}
