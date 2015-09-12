package nl.saxion.webtech.verhuurobjecten;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class Model {

	private List<Room> rooms;
	private List<RoomReservation> reservations;
	private HashMap<String, User> users;
	
	private int timesVisited;
	private String lastVisited;
	
	public Model() {
		init();
	}
	
	private void init() {
		users = new HashMap<>();
		rooms = new ArrayList<Room>();
		reservations = new ArrayList<RoomReservation>();
		addUser(new Admin("admin","admin"));
		timesVisited = 0;
		lastVisited = "This is your first visit.";
	}
	
	public void AddRoom(Room room) {
		this.rooms.add(room);
	}
	
	public void incrementTimesVisited(){
		timesVisited++;
	}
	
	public int getTimesVisited(){
		return timesVisited;
	}
	
	public void setLastVisited(String time){
		lastVisited = time;
	}
	
	public String getLastVisited(){
		return lastVisited;
	}

	public void AddReservation(RoomReservation reseveration) {
		this.reservations.add(reseveration);
	}
	
	public <T extends User> void addUser(T user) {
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
			//TODO: create specific exception
			throw new UserNotFoundException("User " + username + " does not exist" );
		} else {
			return (T) user;
		}
				
	}
	
	public List<Room> getRooms() {
		return rooms;
	}
}
