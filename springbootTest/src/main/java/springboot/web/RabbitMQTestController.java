package springboot.web;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.eventListener.EventListenerTest;

@RestController
public class RabbitMQTestController implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback{
	private static final Logger logger = LoggerFactory.getLogger(EventListenerTest.class);
	@Autowired
	private RabbitTemplate rabbitTemplate;
	@RequestMapping("/rabbitMQ")
	public String test(String msg){
		logger.info("rabbitMQtest进来了");
		rabbitTemplate.setConfirmCallback(this);
		 //发送消息
        String uuid = UUID.randomUUID().toString();
        CorrelationData correlationId = new CorrelationData(uuid);
        rabbitTemplate.convertAndSend("EXCHANGE_NAME", "ROUTING_KEY", msg,correlationId);
		return "ok";
		
	}
	@Override
	public void confirm(CorrelationData correlationData, boolean ack, String cause) {
		 	logger.info(" 消息id:" + correlationData);
	        if (ack) {
	            logger.info("消息发送确认成功");
	        } else {
	            logger.info("消息发送确认失败:" + cause);

	        }
	}
	@Override
	public void returnedMessage(Message msg, int arg1, String arg2, String arg3, String arg4) {
		logger.info("returnedMessage:"+msg.getBody());
	}
}
