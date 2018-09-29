package springboot.web;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.common.utils.RedisUtil;
import springboot.service.impl.userServiceImpl;
@RestController
public class RedisTestController {
	private static final Logger logger = LoggerFactory.getLogger(RedisTestController.class);
	@Autowired
	RedisUtil redisUtil;
	@RequestMapping("/redisTest")
	public String test1(String key,String value){
		logger.info("/redisTest调入成功");
		redisUtil.add(key, value);
		return "ok";
	}
	@RequestMapping("/redisTest2")
	public String test2(String key,String value,Long time){
		logger.info("/redisTest2调入成功");
		redisUtil.set(key, value, time);
		return "ok";
	}
	@RequestMapping("/redisTest3")
	public String test3(String key,String value,Double scoure){
		logger.info("/redisTest3调入成功");
		redisUtil.zAdd(key, value, scoure);
		return "ok";
	}
	@RequestMapping("/redisTest4")
	public Object test4(String key,Double start,Double end,String orderBy){
		logger.info("/redisTest4调入成功");
		Set<Object> rangeByScore = redisUtil.rangeByScore(key, start, end,orderBy);
		return rangeByScore;
	}
	@RequestMapping("/redisTest5")
	public Object test5(String key,long start,long end,String orderBy){
		logger.info("/redisTest5调入成功");
		Set<Object> rangeByScore = redisUtil.zRevRange(key, start, end,orderBy);
		return rangeByScore;
	}
}
