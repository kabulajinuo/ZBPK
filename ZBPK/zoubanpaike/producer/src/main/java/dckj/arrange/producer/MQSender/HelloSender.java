package dckj.arrange.producer.MQSender;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @Classname HelloSender
 * @Description TODO
 * @Date 2020/5/12 16:21
 * @Created by JinPeng
 * @Version 1.0
 */
@Component
public class HelloSender {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send() {
        String context = "hello！这是我第一个消息推送 " + new Date();
        System.out.println("Sender : " + context);
        this.rabbitTemplate.convertAndSend("hello", context);
    }

}
