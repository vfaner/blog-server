package com.rgh.util;

import com.rgh.constant.Constant;
import lombok.Data;
/**
 * @author: rgh  mail:817094@qq.com
 * @description: 封装返回值
 */
@Data
public class ResultUtil {
    private int code;
    private String msg;
    private Object data;

    public ResultUtil() {
    }

    public ResultUtil(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
    public ResultUtil(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResultUtil success(String msg, Object data) {
        return new ResultUtil(Constant.SUCCESS_CODE, msg, data);
    }
    public static ResultUtil success(String msg) {
        return new ResultUtil(Constant.SUCCESS_CODE, msg);
    }
    public static ResultUtil success() {
        return new ResultUtil(Constant.SUCCESS_CODE, Constant.OPERATION_SUCCESS);
    }

    public static ResultUtil fail(int code, String msg, Object data) {
        return new ResultUtil(code, msg, data);
    }
    public static ResultUtil fail(int code, String msg) {
        return new ResultUtil(code, msg);
    }

}
