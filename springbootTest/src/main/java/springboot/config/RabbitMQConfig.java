package springboot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RabbitMQConfig {
	@Value("${spring.rabbitmq.host}")
    private String host;
	@Value("${spring.rabbitmq.port}")
	private int port;
	@Value("${spring.rabbitmq.username}")
    private String username;
	@Value("${spring.rabbitmq.password}")
    private String password;
	/**
	 * 创建连接工厂
	 * @return
	 */
	@Bean
	public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host,port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);
        return connectionFactory;
	}
	/**
	 * 创建通道模板
	 * @return
	 */
	@Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RabbitTemplate  amqpt() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }
	/**
	 * 声明交换机(topic模式)
	 * @return
	 */
	@Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("EXCHANGE_NAME_TOPIC");
    }
	/**
	 * 声明队列
	 * @return
	 */
	@Bean
	public Queue queue() {
		return new Queue("world",true);
	}
	/**
	 * 绑定交换机和队列
	 * @return
	 */
	@Bean
	public Binding binding(Queue queue,TopicExchange topicExchange) {
	    return BindingBuilder.bind(queue).to(topicExchange).with("topic.#");
	}
}
