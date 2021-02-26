package dckj.arrange.producer.MQReceiver;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Classname HelloReceiver
 * @Description TODO
 * @Date 2020/5/12 16:29
 * @Created by JinPeng
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Receiver  : " + hello + "现在是：" + new Date());
    }

}
