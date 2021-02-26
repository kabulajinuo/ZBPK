package dckj.arrange.modules;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * 模块开发API启动类
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
@EnableEurekaClient
@EnableFeignClients
public class ModulesApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ModulesApplication.class, args);
	}

}
