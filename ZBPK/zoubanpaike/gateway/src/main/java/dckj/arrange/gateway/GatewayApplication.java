package dckj.arrange.gateway;

import dckj.arrange.gateway.filter.ReturnFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;


/**
 * 网关服务启动类
 */
@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class GatewayApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

	/**
	 * 加载过滤器
	 * @return
	 */
	@Bean
	public ReturnFilter accessFilter() {
		return new ReturnFilter();
	}

}
