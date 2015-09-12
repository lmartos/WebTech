package nl.saxion.webtech.model;

import java.util.ArrayList;
import java.util.List;

public class RoomManager {
	private final List<Room> rooms;
	
	public RoomManager() {
		rooms = new ArrayList<Room>();
	}
	
	public void addRoom(Room room) {
		this.rooms.add(room);
	}
	
	public List<Room> getRooms() {
		return rooms;
	}
}
