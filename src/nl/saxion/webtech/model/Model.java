package nl.saxion.webtech.model;

import java.util.ArrayList;
import java.util.List;

public class Model {
	private final UserManager userManager ;
	private List<Room> rooms;
	private List<RoomReservation> reservations;
	
	private int timesVisited;
	private String lastVisited;
	
	public Model() {
		userManager = new UserManager();
		init();
	}
	
	private void init() {
		rooms = new ArrayList<Room>();
		reservations = new ArrayList<RoomReservation>();
		timesVisited = 0;
		lastVisited = "This is your first visit.";
	}
	
	public UserManager getUserManager() {
		return userManager;
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
	
	public List<Room> getRooms() {
		return rooms;
	}
}
