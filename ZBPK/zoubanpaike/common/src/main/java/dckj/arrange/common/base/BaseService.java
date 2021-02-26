

package dckj.arrange.common.base;

import java.util.HashMap;
import java.util.Map;

/***
 * BaseService父类。 用于延续ApiResult对象的传递
 *
 * @author icefrog.su@qq.com
 */
public class BaseService {

    protected ApiResult apiResult = new ApiResult();

    /**
     * Clean and rebuild ApiResult object
     */
    protected void clear() {
        apiResult = new ApiResult();
    }

    protected ApiResult success(String functionid, String message, String key, Object value) {
        Map<String, Object> data = new HashMap<String, Object>(1);
        data.put(key, value);
        return callme(ApiResult.CODE_SUCCESS, functionid, message, data);
    }

    protected ApiResult success(String functionid, String message, Map<String, Object> data) {
        return callme(ApiResult.CODE_SUCCESS, functionid, message, data);
    }

    protected ApiResult success(String functionid, String message, Object data) {
        return callme(ApiResult.CODE_SUCCESS, functionid, message, data);
    }

    protected ApiResult success(String functionid, Object data) {
        return callme(ApiResult.CODE_SUCCESS, functionid, ApiResult.SUCCESS_MESSAGE, data);
    }

    protected ApiResult success(String functionid, String message) {
        return callme(ApiResult.CODE_SUCCESS, functionid, message, null);
    }

    protected ApiResult success(String functionid, Map<String, Object> data) {
        return callme(ApiResult.CODE_SUCCESS, functionid, ApiResult.SUCCESS_MESSAGE, data);
    }

    protected ApiResult success(String functionid) {
        return callme(ApiResult.CODE_SUCCESS, functionid, ApiResult.SUCCESS_MESSAGE, null);
    }

    protected ApiResult error(String functionid, String message, String key, Object value) {
        Map<String, Object> data = new HashMap<String, Object>(1);
        data.put(key, value);
        return callme(ApiResult.CODE_FAILED, functionid, message, data);
    }

    protected ApiResult error(String functionid, String message, Map<String, Object> data) {
        return callme(ApiResult.CODE_FAILED, functionid, message, data);
    }

    protected ApiResult error(String functionid, String message) {
        return callme(ApiResult.CODE_FAILED, functionid, message, null);
    }

    protected ApiResult error(String functionid, Map<String, Object> data) {
        return callme(ApiResult.CODE_FAILED, functionid, ApiResult.SUCCESS_MESSAGE, data);
    }

    protected ApiResult error(String functionid) {
        return callme(ApiResult.CODE_FAILED, functionid, ApiResult.FAILED_MESSAGE, null);
    }


    /**
     * 错误码定义在ErrorCode类
     *
     * @param functionid
     * @param errorCode
     * @return
     */
    protected ApiResult error(String functionid, ErrorCode errorCode) {
        return callme(errorCode.getValue(), functionid, errorCode.getName(), null);
    }

    protected ApiResult error(String functionid, ErrorCode errorCode, Object data) {
        return callme(errorCode.getValue(), functionid, errorCode.getName(), null);
    }

    protected ApiResult error(String functionid, int code, String message) {
        return callme(code, functionid, message, null);
    }

    protected ApiResult custome(String functionid, String message, int code, Map<String, Object> data) {
        return callme(code, functionid, message, data);
    }

    protected ApiResult custome(String functionid, String message, int code) {
        return callme(code, functionid, message, null);
    }

    protected ApiResult custome(String functionid, int code, Map<String, Object> data) {
        return callme(code, functionid, ApiResult.SUCCESS_MESSAGE, data);
    }

    protected ApiResult custome(String functionid, int code) {
        return callme(code, functionid, ApiResult.SUCCESS_MESSAGE, null);
    }

    private ApiResult callme(Integer code, String functionid, String message, Object params) {
        return apiResult.setCode(code).setFunctionID(functionid).setMessage(message).setData(params);
    }
}
