package com.rgh.constant;

import java.util.Arrays;
import java.util.List;

public class Constant {
    public static final String PATH_PREFIX = "/rgh/api";
    public static final String PATH_LOGIN = PATH_PREFIX + "/login";
    public static final String PATH_LOGOUT = PATH_PREFIX + "/logout";
    public static final String PATH_SWAGGER_UI = "/swagger-ui/**";
    public static final String PATH_CAPTCHA = "/captcha";

    public static final String TABLE_CORE_PREFIX = "rgh_";

    //elementPlus tags css
    public static final List<String> El_PLUS_TAGS = Arrays.asList("", "success", "info", "danger", "warning");
    public static final List<String> TAG_COLOR = Arrays.asList("#006666", "#009999", "#333333", "#660099",
            "#990066", "#CC0099", "#CC3300", "#FF9900", "#006633");

    /**
     * 状态码
     */
    public static final int SUCCESS_CODE = 200;
    public static final int SERVER_ERROR_CODE = 500;
    public static final int BAD_REQUEST_CODE = 400;
    public static final int REQUEST_ERROR_CODE = 401;
    public static final int MOVE_ERROR_CODE = 301;
    public static final int FORBIDDEN_CODE = 403;
    public static final int NOT_FOUND_CODE = 404;
    public static final int FOUND_CODE = 302;

    /**
     * 状态信息
     */
    public static final String SERVER_ERROR_MSG = "服务器异常";
    public static final String OPERATION_SUCCESS = "操作成功";
    public static final String OPERATION_ADD_SUCCESS = "添加成功";
    public static final String OPERATION_EDIT_SUCCESS = "修改成功";
    public static final String OPERATION_DELETE_SUCCESS = "删除成功";
    public static final String OPERATION_FIND_SUCCESS = "查询成功";
    public static final String LOGIN_AUTHORIZATION = "登录状态失效，请重新登录";

    /**
     * 属性格式化
     */
    public static final String TIME_ZONE = "GMT+8";
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_FORMAT1 = "MM-dd";
    public static final String DATE_TIME_FORMAT2 = "yyyy-MM-dd";
    /**
     * 分页默认值
     */
    public static final Integer DEFAULT_PAGE_NUM = 1;
    public static final Integer DEFAULT_PAGE_SIZE = 10;

    public static final String DEFAULT_LANG = "zh";

}
