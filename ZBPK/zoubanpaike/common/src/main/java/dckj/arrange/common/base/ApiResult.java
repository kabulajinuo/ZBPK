

package dckj.arrange.common.base;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/***
 * ApiResult. http response object
 *

 *
 * @version 1.2 添加CODE_UNLOGINED=99标识未登录情况的ajax响应
 * 				添加CODE_UNTREATEDEXCEPTION=100标识未处理异常
 */
public class ApiResult implements Serializable {
    private static final long serialVersionUID = -6486606756679486803L;
    public static final Integer CODE_SUCCESS = 200;  // 响应成功
    public static final Integer CODE_FAILED = 204;  // 无内容
    public static final Integer PARAM_FAILED = 400;  // 参数错误
    public static final Integer SYSTEM_FAILED = 500;  //系统错误
    public static final Integer CODE_INVALID = 501;   //响应失效
    public static final Integer CODE_FEIGN_ERROR = 9;  // 响应feign调用失败
    public static final Integer CODE_UNLOGINED = 99; // 响应未登录
    public static final Integer CODE_UNTREATEDEXCEPTION = 100;// 响应未处理异常
    public static final String DEFAULT_FUNCTIONID = "default_function_id";
    public static final String SUCCESS_MESSAGE = "success";
    public static final String FAILED_MESSAGE = "failed";
    // 错误码统一定义到ErrorCode类里面

    private String functionID;

    private String message;

    private Integer code;

    private Object data;


    /**
     * Use net.sf.json-lib convert object for this to json string.<br>
     * <code>
     * net.sf.json.JSONObject.fromObject(this).toString()
     * <code>
     */
    public String toJsonString() {
        return JSON.toJSONString(this);
    }


    public String getFunctionID() {
        return functionID;
    }

    public ApiResult setFunctionID(String functionID) {
        this.functionID = functionID;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ApiResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getCode() {
        return code;
    }

    public ApiResult setCode(Integer code) {
        this.code = code;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ApiResult setData(Object data) {
        this.data = data;
        return this;
    }

}