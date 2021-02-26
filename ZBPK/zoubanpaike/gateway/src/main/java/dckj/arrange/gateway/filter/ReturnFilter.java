package dckj.arrange.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import dckj.arrange.gateway.util.EmptyUtil;
import dckj.arrange.gateway.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 * @Classname ReturnFilter
 * @Description API请求拦截处理
 * @Date 2019/4/25 10:12
 * @Created by JinPeng
 * @Version 1.0
 */
public class ReturnFilter extends ZuulFilter {

    private static final Logger logger = LoggerFactory.getLogger(ReturnFilter.class);

    private static final String WEBLOGINURL = "/zbpk-auth-service/baseUserController/login";

    @Resource
    RedisUtil redisUtil;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        logger.info("request.getRequestURI():" + request.getRequestURI());
        //不需要权限校验URL
        if (WEBLOGINURL.equals(request.getRequestURI())) {
            return false;
        }
        return true;
    }

    @Override
    public Object run(){
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String login_token = request.getHeader("login_token");
        String key = request.getHeader("token_key");
        logger.info("login_token: " + login_token);
        logger.info("token_key: " + key);
        String redis_account_token = "";
        if (!EmptyUtil.isEmpty(login_token)){
            if (EmptyUtil.isEmpty(redisUtil.get("login_token_" + key))) {
                logger.info("请求token认证失败");
                ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
                ctx.setResponseStatusCode(504);// 返回错误码--token超时
                ctx.setResponseBody("{result:Request illegal!the token is Time expired}");// 返回错误内容
                ctx.set("isSuccess", false);
                return null;
            }else {
                redis_account_token = redisUtil.get("login_token_" + key).toString();

                if (login_token.equals(redis_account_token)) {//暂时简单化测试
                    logger.info("请求token认证成功");
                    ctx.setSendZuulResponse(true);// 对该请求进行路由
                    ctx.setResponseStatusCode(200);
                    ctx.set("isSuccess", true);// 设值，可以在多个过滤器时使用
                    return null;
                } else {
                    logger.info("请求token认证失败");
                    ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
                    ctx.setResponseStatusCode(401);// 返回错误码--未通过授权
                    ctx.setResponseBody("{result:Request illegal!the token is unauthorized}");// 返回错误内容
                    ctx.set("isSuccess", false);
                    return null;
                }

            }
        }else {
            logger.info("请求token认证失败");
            ctx.setSendZuulResponse(false);// 过滤该请求，不对其进行路由
            ctx.setResponseStatusCode(403);// 返回错误码--token为空
            ctx.setResponseBody("{result:Request illegal!the token is null}");// 返回错误内容
            ctx.set("isSuccess", false);
            return null;
        }
    }
}
