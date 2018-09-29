package springboot.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
@Async
public class TaskTest {
	private static final Logger logger = LoggerFactory.getLogger(TaskTest.class);
	@Scheduled(cron = "0/5 * * * * *")
	public void test1(){
		logger.info("TaskTest1开始执行咯");
	}
	@Scheduled(cron = "0/5 * * * * *")
	public void test2(){
		logger.info("TaskTest2开始执行咯");
	}
	@Scheduled(cron = "0/5 * * * * *")
	public void test3(){
		logger.info("TaskTest3开始执行咯");
	}
	@Scheduled(cron = "0/5 * * * * *")
	public void test4(){
		logger.info("TaskTest4开始执行咯");
	}
	@Scheduled(cron = "0/5 * * * * *")
	public void test5(){
		logger.info("TaskTest5开始执行咯");
	}
}
