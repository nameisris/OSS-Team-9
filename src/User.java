
public class User {
	private String uID;
	private String password;
	private String name;
	
	public User() {
		
	}
	
	public User(String uID, String password, String name) {
		this.uID = uID;
		this.password = password;
		this.name = name;
	}
	
	public String getUID() {
		return uID;
	}
	
	public void setUID(String uID) {
		this.uID = uID;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
