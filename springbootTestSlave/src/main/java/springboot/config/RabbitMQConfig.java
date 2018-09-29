package springboot.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitMQConfig {
	public Queue  Queue(){
		return new Queue("world");
	}
}
