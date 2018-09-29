package springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import springboot.eventListener.EventListenerTest;
@Configuration
public class RabbitMQConfig {
	private static final Logger logger = LoggerFactory.getLogger(EventListenerTest.class);

	@Value("${spring.rabbitmq.host}")
    private String host;
	@Value("${spring.rabbitmq.port}")
	private int port;
	@Value("${spring.rabbitmq.username}")
    private String username;
	@Value("${spring.rabbitmq.password}")
    private String password;
	@Bean
	public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
	}
	@Bean
    //@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }
	@Bean
	public DirectExchange defaultExchange() {
	    return new DirectExchange("EXCHANGE_NAME");
	}
	@Bean
    public Queue Queue() {
        return new Queue("hello",true);
    }
	@Bean
	public Binding binding() {
	    return BindingBuilder.bind(Queue()).to(defaultExchange()).with("ROUTING_KEY");
	}
	/**
	 * 将ACK修改为手动确认，避免消息在处理过程中发生异常造成被误认为已经成功消费的假象。
	 * @return
	 */
	//@Bean
	public SimpleMessageListenerContainer messageContainer() {
	    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory());
	    container.setQueues(Queue());
	    container.setExposeListenerChannel(true);
	    container.setMaxConcurrentConsumers(1);
	    container.setConcurrentConsumers(1);
	    container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
	    container.setMessageListener(new ChannelAwareMessageListener() {

	        public void onMessage(Message message, com.rabbitmq.client.Channel channel) throws Exception {
	            byte[] body = message.getBody();
	            logger.info("消费端接收到消息 : " + new String(body));
	            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
	        }
	    });
	    return container;
	}
}
