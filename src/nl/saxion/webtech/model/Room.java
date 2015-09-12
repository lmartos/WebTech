package nl.saxion.webtech.model;

public class Room {

	private RoomOwner owner;
	private double surface;
	private double maxRentPrice; 
	private String city;
	
	public Room(RoomOwner owner, double surface, double maxRentPrice, String city){
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
	
	public double getSurface() {
		return surface;
	}
	
	public String getCity() {
		return city;
	}
	
	public double getMaxRentPrice() {
		return maxRentPrice;
	}
	
	public RoomOwner getRoomOwner() {
		return owner;
	}
	
	
}
