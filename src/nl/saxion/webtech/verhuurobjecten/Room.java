package nl.saxion.webtech.verhuurobjecten;

public class Room {

	private RoomOwner owner;
	private String surface;
	private String maxRentPrice; 
	private String city;
	
	public Room(RoomOwner owner, String surface, String maxRentPrice, String city){
		this.id = staticId;
		this.owner = owner;
		this.surface = surface;
		this.maxRentPrice = maxRentPrice;
		this.city = city;
		
		staticId++;
	}
	
	
	private int id;
	private static int staticId = 1;
	
	public int getId(){
		return id;
	}
	
	public String getSurface() {
		return surface;
	}
	
	public String getCity() {
		return city;
	}
	
	public String getMaxRentPrice() {
		return maxRentPrice;
	}
	
	public RoomOwner getRoomOwner() {
		return owner;
	}
	
	
}
