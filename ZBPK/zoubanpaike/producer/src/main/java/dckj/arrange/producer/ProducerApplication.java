package dckj.arrange.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableAsync;


/**
 * 资源服务启动类
 */
@SpringBootApplication
@EnableEurekaClient
@EnableAsync
public class ProducerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ProducerApplication.class, args);
	}

}
