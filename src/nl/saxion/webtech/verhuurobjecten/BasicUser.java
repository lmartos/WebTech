package nl.saxion.webtech.verhuurobjecten;

public class BasicUser {

	public BasicUser(String username, String password){
		this.username = username;
		this.password = password;
		this.id = staticId;
		staticId++;
	}
	
	public String username,password;
	private int id;
	private static int staticId = 1;
	
	
	public int getId() {
		return id;
	}
	
	public String getUsername(){
		return username;
	}
	
	public String getPassword(){
		return password;
	}
}
