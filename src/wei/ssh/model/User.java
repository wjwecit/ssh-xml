package wei.ssh.model;

public class User {

	private String name;
	private String password;
	
	public User(){
		name="noname";
		password="123";
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "user: name="+name+",password="+password;
	}
}
