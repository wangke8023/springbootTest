package springboot.eventListener;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class RabbitMQListenerTest {
	@RabbitHandler
	public void process(String hello) {
		System.err.println("Receiver  : " + hello);
	}

}
