package dckj.arrange.eureka.adapter;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 禁用SpringCloud2.0新增的csrf校验。默认此校验会导致其他服务连接到此注册中心失败，或者无法进入管理页面
 * 这是使用security进行连接鉴权导致的后果
 */
@EnableWebSecurity
public class WebSecurityConfigurerProvider extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //SpringCloud 2.0 默认启用csrf校验。此处关闭此校验
        http.csrf().disable();
        super.configure(http);
    }


}
