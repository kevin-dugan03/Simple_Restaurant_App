package objects;

public class RegisteredUser {

	private String username;
	private String password;
	
	public RegisteredUser()
	{
		username = "";
		password = "";
	}
	
	public void setUsername(String user)
	{
		username = user;
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setPassword(String pword)
	{
		password = pword;
	}
	
	public String getPassword()
	{
		return password;
	}
}
