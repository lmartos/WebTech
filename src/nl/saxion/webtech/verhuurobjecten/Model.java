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
	
	public Model() {
		rooms = new ArrayList<Room>();
		reservations = new ArrayList<RoomReservation>();
		roomOwners = new ArrayList<RoomOwner>();
		roomTentants = new ArrayList<RoomTentant>();
		admins = new ArrayList<Admin>();
		timesVisited = 0;
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

	public void AddReservation(RoomReservation reseveration) {
		this.reservations.add(reseveration);
	}

	public void AddRoomOwner(RoomOwner roomOwner) {
		this.roomOwners.add(roomOwner);
	}

	public void AddTentant(RoomTentant roomTentant) {
		this.roomTentants.add(roomTentant);
	}
	
	public void addAdmin(Admin admin){
		this.admins.add(admin);
	}
	
	public List<BasicUser> getAllUsers(){
			List<BasicUser> allUsers = new ArrayList<BasicUser>();
			allUsers.addAll(roomTentants);
			allUsers.addAll(roomOwners);
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
	
}
