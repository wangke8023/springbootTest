package springboot.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import springboot.dao.queryUserDAO;
import springboot.dto.User;
import springboot.service.userService;

@Service
public class userServiceImpl implements userService{
	private static final Logger logger = LoggerFactory.getLogger(userServiceImpl.class);
	@Autowired
	private queryUserDAO queryUserDAO;
	@Override
	public String queryUser(String id) {
		logger.info("调进来哈哈哈");
		return queryUserDAO.queryUserById("");
	}
	@Override
	@Async("asyncServiceExecutor")
	public User queryUserObj(String id) {
		logger.info("查询user对象,先睡会....");
		try {
			Thread.sleep(5000);
			logger.info("查询user对象,睡醒了....");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return queryUserDAO.queryUserObj(id);
	}

}
