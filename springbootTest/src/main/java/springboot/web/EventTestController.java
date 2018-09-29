package springboot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.eventListener.EventListenerTest;

@RestController
public class EventTestController {
	private static final Logger logger = LoggerFactory.getLogger(EventListenerTest.class);
	@Autowired
	private ApplicationEventPublisher tradeEventPublisher;
	@RequestMapping("/eventTest")
	public String eventTest(String event){
		logger.info("EventTestController进来啦");
		tradeEventPublisher.publishEvent(event);
		tradeEventPublisher.publishEvent("只要是string我就监听");
		logger.info("EventTestController时间已发布");
		return "ok";
	}
}
