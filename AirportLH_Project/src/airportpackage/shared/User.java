package airportpackage.shared;

import java.io.Serializable;

/**
 * @author Mikhail Telegin 
 * Класс пользователи системы 
 * User class
 */

/**
 * userID - идентификатор пользователя
 * login - имя
 * password - пароль
 * rights - права
 */
public class User implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int userId;
	private String login;
	private String password;
	private String rights;
	
	// Default constructor

	// Getters and setters
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRights() {
		return rights;
	}
	
	public void setRights(String rights) {
		this.rights = rights;
	}
}
