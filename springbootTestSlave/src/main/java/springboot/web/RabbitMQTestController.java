package springboot.web;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.eventListener.EventListenerTest;

@RestController
public class RabbitMQTestController {
	private static final Logger logger = LoggerFactory.getLogger(EventListenerTest.class);

	@Autowired
    private AmqpTemplate rabbitTemplate;
	@RequestMapping("/rabbitMQ")
	public String test(String msg){
		logger.info("rabbitMQtest进来了");
		rabbitTemplate.convertAndSend(msg);
		return "ok";
		
	}
}
