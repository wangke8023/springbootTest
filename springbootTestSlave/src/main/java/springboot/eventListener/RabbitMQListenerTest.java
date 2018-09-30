package springboot.eventListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties.Cache.Channel;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "world")
public class RabbitMQListenerTest {
	private static final Logger logger = LoggerFactory.getLogger(EventListenerTest.class);

	@RabbitHandler
	public void process(String message,Channel channel) {
		logger.info("消费者消费消息："+message);
	}

}
