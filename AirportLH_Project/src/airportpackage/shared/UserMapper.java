package airportpackage.shared;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

/**
 * @author Mikhail Telegin
 * Class UserMapper
 */ 

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {

	/**
	 * Get all users from table USERS, database AIRPORT
	 * @return User list
	 */
	@Results({
		@Result(property = "userId", column = "USER_ID"),
		@Result(property = "login", column = "LOGIN"),
		@Result(property = "password", column = "PASSWORD"),
		@Result(property = "rights", column = "RIGHTS"),

	})
	@Select("SELECT LOGIN, PASSWORD, RIGHTS FROM USERS WHERE 1")
	List<User> getUsers();

	/**
	 * Insert user into table USERS, database AIRPORT
	 * @param user - object User
	 */
	@Insert("INSERT INTO USERS (LOGIN, PASSWORD, RIGHTS) VALUES(#{login}, #{password}, #{rights})")
	void insertUser(User user);
	
}
