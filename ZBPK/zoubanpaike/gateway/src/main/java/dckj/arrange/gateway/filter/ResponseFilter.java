package dckj.arrange.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import dckj.arrange.gateway.entity.BaseUser;
import dckj.arrange.gateway.util.EmptyUtil;
import dckj.arrange.gateway.util.MD5;
import dckj.arrange.gateway.util.RedisUtil;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;
import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Date;

import static org.springframework.util.ReflectionUtils.rethrowRuntimeException;

/**
 * @Classname ResponseFilter
 * @Description API响应拦截处理
 * @Date 2019/4/25 10:12
 * @Created by JinPeng
 * @Version 1.0
 */
@Component
public class ResponseFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(ReturnFilter.class);

    private static final String WEBLOGINURL = "/zbpk-auth-service/baseUserController/login";

    @Resource
    RedisUtil redisUtil;

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 999;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run(){
        try {
            RequestContext ctx = RequestContext.getCurrentContext();
            InputStream stream = ctx.getResponseDataStream();
            HttpServletRequest request = ctx.getRequest();
            String body = StreamUtils.copyToString(stream, Charset.forName("UTF-8"));
            String login_token = "";
            String key = "";
            if (!EmptyUtil.isEmpty(body)) {
                JSONObject json = JSONObject.fromObject(body);
                if (WEBLOGINURL.equals(request.getRequestURI())) {
                    /** 这里我是先拦截登陆后的响应报文，根据返回情况再做下一步操作
                     * 1.登录成功
                     * 2.获取账号，密码，当前时间
                     * 3.将明文进行base64加密处理
                     * 4.将密文token放置请求头中返回给前端
                     */
                    Object o = json.get("data");
                    if (!(o instanceof JSONNull)) {
                        String date = json.get("data").toString();
                        JSONObject j = JSONObject.fromObject(date);
                        BaseUser du = (BaseUser) JSONObject.toBean(j, BaseUser.class);
                        String text = du.getPhone() + du.getPassword() + new Date();
                        byte[] b = text.getBytes();
                        String token = MD5.encryptBASE64(b);
                        logger.info("token: " + token);
                        login_token = token;
                        //token密钥
                        key = RandomStringUtils.randomAlphanumeric(5);
                        redisUtil.set("login_token_" + key, token, 604800L);
                    }
                }
            }
            HttpServletResponse response = ctx.getResponse();
            response.setHeader("login_token", login_token);
            response.setHeader("token_key", key);
            response.setHeader("Access-Control-Expose-Headers","login_token,token_key");
            ctx.setResponseBody(body);
            logger.info("body" + body);
        }
        catch (IOException e) {
            rethrowRuntimeException(e);
        }
        return null;
    }
}
