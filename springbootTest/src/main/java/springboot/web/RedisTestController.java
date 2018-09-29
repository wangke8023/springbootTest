package springboot.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.common.utils.RedisUtil;
@RestController
public class RedisTestController {
	@Autowired
	RedisUtil redisUtil;
	@RequestMapping("/redisTest")
	public String test1(String key,String value){
		redisUtil.add(key, value);
		return "ok";
	}
	@RequestMapping("/redisTest2")
	public String test2(String key,String value,Long time){
		redisUtil.set(key, value, time);
		return "ok";
	}
	@RequestMapping("/redisTest3")
	public String test3(String key,String value,Double scoure){
		redisUtil.zAdd(key, value, scoure);
		return "ok";
	}
	@RequestMapping("/redisTest4")
	public Object test4(String key,String value,Double scoure){
		redisUtil.setMembers(key);
		return "ok";
	}
}
