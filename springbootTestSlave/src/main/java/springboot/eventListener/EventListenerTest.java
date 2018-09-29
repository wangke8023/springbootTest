package springboot.eventListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import springboot.web.UserController;

@Component
public class EventListenerTest {
	private static final Logger logger = LoggerFactory.getLogger(EventListenerTest.class);
	@EventListener
	public void test(String events){
		logger.info("事件监听成功啦："+events);
	}
}
