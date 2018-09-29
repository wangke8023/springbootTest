package springboot.service;

import springboot.dto.User;

public interface userService {
	public String queryUser(String id);
	public User queryUserObj(String id);
}
