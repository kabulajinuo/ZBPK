package dckj.arrange.producer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Classname RabbitConfig
 * @Description TODO
 * @Date 2020/5/12 16:17
 * @Created by JinPeng
 * @Version 1.0
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue Queue() {
        return new Queue("hello");
    }
}
