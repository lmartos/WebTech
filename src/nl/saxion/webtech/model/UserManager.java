package nl.saxion.webtech.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
	private Map<String, User> users;
	
	public UserManager() {
		users = new HashMap<>();
		addUser(new Admin("admin","admin"));
	}
	
	public <T extends User> void addUser(T user) {
		String username = user.getUsername();
		if (users.containsKey(username)) {
			throw new UserAlreadyExistsException("User " + username + " already exists");
		}
		users.put(user.getUsername(), user);
	}
	
	public boolean verifyAccount(String username, String password) {
		User user = users.get(username);
		
		if (user == null) {
			return false;
		}
		
		if (!user.getPassword().equals(password)) {
			return false;
		}
		
		return true;
	}
	
	public Collection<User> getUsers() {
		return users.values();
	}
	
	
	public <T extends User> boolean userExists(String username, Class<T> clazz) {
		if (!users.containsKey(username)) {
			return false;
		}
		
		if (!users.get(username).getClass().equals(clazz)) {
			return false;
		}
		
		return true;
	}
	
	/**
	 * Get the user from userlist.
	 * @param username the name of the user.
	 * @param clazz subtype of User.
	 * @return a specified subtype of User.
	 * @throws UserNotFoundException 
	 */
	@SuppressWarnings("unchecked")
	public <T extends User> T getUser(String username, Class<T> clazz){
		User user = users.get(username);
		
		if (user == null) {
			throw new UserNotFoundException("User " + username + " does not exist" );
		} else {
			return (T) user;
		}
				
	}
}
