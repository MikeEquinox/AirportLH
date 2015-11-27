package airportpackage.shared;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * @author Mikhail Telegin Class UserDAO
 */

public class UserDAO {

	private static SqlSessionFactory sqlSessionFactory;
	
	/**
	 * Create DAO logger
	 */
	Logger daoLogger = Logger.getLogger("daoLogger");

	/**
	 * Get all users from table USERS, database AIRPORT
	 * @return User list
	 */
	public List<User> getUsers() {
		SqlSession session = getSqlSessionFactory().openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		daoLogger.log(Level.INFO, "UserDAO calls for UserMapper, method getUsers()");
		List<User> userList = mapper.getUsers();
		session.close();
		return userList;
	}

	/**
	 * Insert user into table USERS, database AIRPORT
	 * @param login - user login
	 * @param password - user password
	 */
	public void newUser(String login, String password) {
		SqlSession session = getSqlSessionFactory().openSession();
		UserMapper mapper = session.getMapper(UserMapper.class);
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		user.setRights("DIS");
		daoLogger.log(Level.INFO, "UserDAO calls for UserMapper, method newUser(), method insertUser(user)");
		daoLogger.log(Level.INFO, "UserDAO, newUser(), login: " + user.getLogin());
		daoLogger.log(Level.INFO, "UserDAO newUser(), rights: " + user.getRights());
		mapper.insertUser(user);
		session.commit();
		session.close();
		return;
	}

	public SqlSessionFactory getSqlSessionFactory() {
		try {
			Reader resourceReader = Resources.getResourceAsReader("airportpackage/config.xml");
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceReader);

			// Для xml mapper не используется
			sqlSessionFactory.getConfiguration().addMapper(UserMapper.class);

		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSessionFactory;
	}
}
