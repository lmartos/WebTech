package nl.saxion.webtech.verhuurobjecten;

import java.util.ArrayList;
import java.util.List;

public class Model {

	// Kamers
	private List<Room> rooms = new ArrayList<Room>();

	public void AddRoom(Room room) {
		this.rooms.add(room);
	}

	// Reservaties
	private List<RoomReservation> reservations = new ArrayList<RoomReservation>();

	public void AddReservation(RoomReservation reseveration) {
		this.reservations.add(reseveration);
	}

	// Verhuurders
	private List<RoomOwner> roomOwners = new ArrayList<RoomOwner>();

	public void AddRoomOwner(RoomOwner roomOwner) {
		this.roomOwners.add(roomOwner);
	}

	// Huurders
	private List<RoomTentant> roomTentants = new ArrayList<RoomTentant>();

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
