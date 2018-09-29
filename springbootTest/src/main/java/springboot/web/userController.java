package springboot.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.dto.User;
import springboot.service.userService;

@RestController
public class userController {
	private static final Logger logger = LoggerFactory.getLogger(userController.class);

	@Autowired
	private userService userService;
	@RequestMapping("/userTest")
	public String queryUser(String id){
		logger.info("进来了");
		userService.queryUserObj(id);
		userService.queryUser(id);
		return "异步成功";
		
	}
	@RequestMapping("/userObjTest")
	public User queryUserObj(String id){
		return userService.queryUserObj(id);
	}
}
