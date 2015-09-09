package nl.saxion.webtech.verhuurobjecten;

import java.util.ArrayList;
import java.util.List;

public class Model {

	private List<Room> rooms;
	private List<RoomReservation> reservations;
	private List<RoomOwner> roomOwners;
	private List<RoomTentant> roomTentants;
	
	public Model() {
		rooms = new ArrayList<Room>();
		reservations = new ArrayList<RoomReservation>();
		roomOwners = new ArrayList<RoomOwner>();
		roomTentants = new ArrayList<RoomTentant>();
	}
	public void AddRoom(Room room) {
		this.rooms.add(room);
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
	
	public List<BasicUser> getAllUsers(){
			List<BasicUser> allUsers = new ArrayList<BasicUser>();
			allUsers.addAll(roomTentants);
			allUsers.addAll(roomOwners);
		return allUsers;
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
