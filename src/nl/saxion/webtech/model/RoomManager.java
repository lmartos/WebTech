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
	
	public List<Room> getRooms(RoomOwner owner) {
		List<Room> roomsFromOwner = new ArrayList<Room>();
		for (Room room : rooms) {
			if (room.getRoomOwner().equals(owner)) {
				roomsFromOwner.add(room);
			}
		}
		return rooms;
	}
	
	public List<Room> getSpecifiedRooms(String location, double maxPrice, double surface) {
		List<Room> specifiedRooms = new ArrayList<Room>();
		if(!location.isEmpty()){
			for (Room room : rooms) {
				if (room.getCity().equals(location) && room.getMaxRentPrice() <= maxPrice && room.getSurface() >= surface) {
					specifiedRooms.add(room);
				}
			}	
		}else{
			for (Room room : rooms) {
				if (room.getMaxRentPrice() <= maxPrice && room.getSurface() >= surface) {
					specifiedRooms.add(room);
				}
			}
		}
		
		return specifiedRooms;
	}
}
