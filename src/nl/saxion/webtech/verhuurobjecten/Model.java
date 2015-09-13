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

	/**
	 * adds a registering user to the list of roomOwners to enable future logging in
	 * @param roomOwner the roomOwner object to be registered as user
	 * @return boolean true if the user does not exist already and will be registered false otherwise
	 */
	public boolean AddRoomOwner(RoomOwner roomOwner) {
		if((getUser(roomOwner.getUsername()) == null)){
		this.roomOwners.add(roomOwner);
		return true;
		}
		return false;
	}
	
	/**
	 * adds a registering user to the list of roomTentants to enable future logging in
	 * @param roomTentant the roomTentant object to be registered as user
	 * @return boolean true if the user does not exist already and will be registered false otherwise
	 */
	
	public boolean AddTentant(RoomTentant roomTentant) {
		if((getUser(roomTentant.getUsername()) == null)){
			this.roomTentants.add(roomTentant);
			return true;
		}
		return false;
	}
	
	/**
	 * adds a user to the list of Admins to enable future logging in
	 * @param admin the Admin object to be registered as user
	 */
	public void addAdmin(Admin admin){
		this.admins.add(admin);
	}
	
	/**
	 * returns a list of all registered users 
	 * @return returns an ArrayList<BasicUser> containing all registered users
	 */
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
	
	/**
	 * returns a roomOwner object which name equals the given username
	 * @param username username of the roomOwner to be looked up
	 * @return roomOwner object containing the given username
	 * @throws Exception throws an exception when the roomOwner is not found
	 */
	public RoomOwner getOwner(String username) throws Exception{
		for (RoomOwner owner : roomOwners) {
			if (owner.getUsername().equals(username)) {
				return owner;
			}
		}
		
		throw new Exception("room owner not found");
	}
	
	/**
	 * returns a BasicUser object which name equals the given username
	 * @param username username of the roomOwner to be looked up
	 * @return BasicUser object containing the given username
	 * 
	 */
	
	public BasicUser getUser(String username){
		for (BasicUser user : getAllUsers()) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		
		return null;
	}
	
}
