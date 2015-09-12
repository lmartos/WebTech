package nl.saxion.webtech.model;

public class Admin extends User {
	private String lastVisited;
	private int timesVisited;

	public Admin(String username, String password) {
		super(username, password);
		timesVisited = 0;
		lastVisited = "This is your first visit.";
	}
	
	public String getLastVisited() {
		return lastVisited;
	}
	
	public int getTimesVisited() {
		return timesVisited;
	}
	
	public void incrementTimesVisited(){
		timesVisited++;
	}
	
	public void setLastVisited(String time){
		lastVisited = time;
	}

}
