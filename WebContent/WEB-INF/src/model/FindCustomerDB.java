package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import objects.Customer;
/**
 * Finds a customer in the DB.
 * @author KDugan
 *
 */
public class FindCustomerDB
{
    private String firstName, lastName, phone;
    private String email, street, city, state;
    private int zip, id;
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet result = null;

    /**
     * Constructor gets the search criteria.
     * @param first customer first name
     * @param last customer last name
     * @param phoneNum customer phone number
     */
    public FindCustomerDB(String first, String last, String phoneNum)
    {
        firstName = first;
        lastName = last;
        phone = phoneNum;
        email = "";
        street = "";
        city = "";
        state = "";
        zip = 0;
        id = 0;
    }
    
    /**
     * Find the customer in the DB.
     * @return the customer
     * @throws SQLException
     */
    public Customer find() throws SQLException
    {
        String findCustomer = "SELECT customerID, street, city, state, zip, email FROM" +
        		" APP.CustomerTable WHERE firstName=? AND lastName=? AND phone=?"; 
        Customer cust = new Customer();
        
        //access the database and look for the customer with specified data
        try
        {
        	InitialContext ctx = new InitialContext();
        	DataSource data = (DataSource) ctx.lookup("java:comp/env/jdbc/PizzaRestaurantDB");
        	conn = data.getConnection();
           
            //look for the customer using the query
            ps = conn.prepareStatement(findCustomer);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, phone);
            result = ps.executeQuery();
            
            while (result.next())
            {
                id = result.getInt(1);
                street = result.getString(2);
                city = result.getString(3);
                state = result.getString(4);
                zip = result.getInt(5);
                email = result.getString(6);
                
                cust = new Customer(firstName, lastName, email, street, city, state, zip, phone);
                cust.setId(id);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            conn.close();
        }

        //return the customer or a default customer
        return cust;
    }
}
