package nl.saxion.webtech.verhuurobjecten;

import java.util.ArrayList;
import java.util.List;

public class Model {

	private List<Room> rooms;
	private List<RoomReservation> reservations;
	private List<RoomOwner> roomOwners;
	private List<RoomTentant> roomTentants;
	private List<Admin> admins;
	
	private int timesVisited;
	private String lastVisited;
	
	public Model() {
		init();
	}
	
	private void init() {
		rooms = new ArrayList<Room>();
		reservations = new ArrayList<RoomReservation>();
		roomOwners = new ArrayList<RoomOwner>();
		roomTentants = new ArrayList<RoomTentant>();
		admins = new ArrayList<Admin>();
		admins.add(new Admin("admin", "admin"));
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

	public boolean AddRoomOwner(RoomOwner roomOwner) {
		if(!roomOwners.contains(roomOwner)){
		this.roomOwners.add(roomOwner);
		return true;
		}
		return false;
	}

	public boolean AddTentant(RoomTentant roomTentant) {
		if(!roomTentants.contains(roomTentant)){
			this.roomTentants.add(roomTentant);
			return true;
		}
		return false;
	}
	
	public void addAdmin(Admin admin){
		this.admins.add(admin);
	}
	
	public List<BasicUser> getAllUsers(){
			List<BasicUser> allUsers = new ArrayList<BasicUser>();
			allUsers.addAll(roomTentants);
			allUsers.addAll(roomOwners);
			allUsers.addAll(admins);
		return allUsers;
	}
	
	public List<Room> getRooms() {
		return rooms;
	}
	
	public RoomOwner getOwner(String username) throws Exception{
		for (RoomOwner owner : roomOwners) {
			if (owner.getUsername().equals(username)) {
				return owner;
			}
		}
		
		throw new Exception("room owner not found");
	}
	
	public BasicUser getUser(String username){
		for (BasicUser user : getAllUsers()) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		
		return null;
	}
	
}
