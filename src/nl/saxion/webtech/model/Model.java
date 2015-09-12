package nl.saxion.webtech.model;

public class Model {
	private final UserManager userManager ;
	private final RoomManager roomManager ;
	
	public Model() {
		userManager = new UserManager();
		roomManager = new RoomManager();
	}
	
	public RoomManager getRoomManager() {
		return roomManager;
	}
	
	public UserManager getUserManager() {
		return userManager;
	}
}
