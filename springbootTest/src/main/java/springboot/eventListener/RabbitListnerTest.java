package springboot.eventListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

//@Component
//@RabbitListener(queues = "hello")
public class RabbitListnerTest {
	private static final Logger logger = LoggerFactory.getLogger(EventListenerTest.class);
	//@RabbitHandler
    public void process(String hello) {
		logger.info("消费消息："+hello);
    }
	
}
