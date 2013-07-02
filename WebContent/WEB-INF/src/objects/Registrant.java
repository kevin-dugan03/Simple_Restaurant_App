package objects;

public class Registrant {
	
	private String email;
	private String username;
	private String password;
	
	public Registrant()
	{
		email = "";
		username = "";
		password = "";
	}

	/**
	 * Sets the email address.
	 * @param emailAddress employee's email address.
	 */
	public void setEmail(String emailAddress)
	{
		email = emailAddress;
	}
	
	/**
	 * Gets the email address.
	 * @return the email address
	 */
	public String getEmail()
	{
		return email;
	}
	
	/**
	 * Sets the username.
	 * @param userName
	 */
	public void setUsername(String userName)
	{
		username = userName;
	}
	
	/**
	 * Gets the username
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}
	
	/**
	 * Sets the password.
	 * @param pword
	 */
	public void setPassword(String pword)
	{
		password = pword;
	}
	
	/**
	 * Gets the password
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}
}
