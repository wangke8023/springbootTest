package springboot.dao;
import springboot.dto.User;
public interface queryUserDAO {
	public String queryUserById(String id);
	public User queryUserObj(String id);
}
