package dckj.arrange.producer.MQReceiver;

import dckj.arrange.producer.MQSender.HelloSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Classname MqTest
 * @Description TODO
 * @Date 2020/5/12 16:31
 * @Created by JinPeng
 * @Version 1.0
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MqTest {

    @Autowired
    private HelloSender helloSender;


    @Test
    public void hello() throws Exception {
        helloSender.send();
    }


}
