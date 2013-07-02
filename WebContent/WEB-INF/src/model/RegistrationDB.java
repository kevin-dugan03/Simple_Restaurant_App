package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import objects.Registrant;

public class RegistrationDB {
	Registrant registrant;
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet result = null;
	String message = "";
	
	public RegistrationDB(Registrant regUser)
	{
		registrant = regUser;
	}
	
	/**
	 * Adds the registrant to the DB.
	 */
	public boolean addRegistrant() throws SQLException
	{
		String findDuplicate = "SELECT emailAddress FROM APP.Registrant WHERE emailAddress=?";
		String addUserPass = "INSERT INTO APP.UserPass (username, password) VALUES (?,?)";
		String addUserRole = "INSERT INTO APP.UserRole (username, rolename) VALUES (?,?)";
		String addEmail = "INSERT INTO APP.Registrant (emailAddress) VALUES (?)";
		
		try
		{
			InitialContext ctx = new InitialContext();
			DataSource data = (DataSource) ctx.lookup("java:comp/env/jdbc/PizzaRestaurantDB");
			conn = data.getConnection();
			
			ps = conn.prepareStatement(findDuplicate);
			ps.setString(1, registrant.getEmail());
			result = ps.executeQuery();
			
			while (result.next())
			{
				return false;
			}
			
			ps = conn.prepareStatement(addUserPass);
			ps.setString(1, registrant.getUsername());
			ps.setString(2, registrant.getPassword());
			ps.executeUpdate();
			
			ps = conn.prepareStatement(addUserRole);
			ps.setString(1, registrant.getUsername());
			ps.setString(2, "employee");
			ps.executeUpdate();
			
			ps = conn.prepareStatement(addEmail);
			ps.setString(1, registrant.getEmail());
			ps.executeUpdate();
			
		}
		catch (Exception e)
		{
		    e.printStackTrace();
		}
		finally
		{
		    conn.close();
		}
		
		return true;
	}

}
