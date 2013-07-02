package objects;

import java.io.Serializable;


/**
 * Represents a customer with all the pertinent data.
 * @author KDugan
 */
public class Customer implements Serializable{
    
	private static final long serialVersionUID = 1L;
	private String first;
    private String last;
    private String email;
    private String street;
    private String city;
    private String state;
    private int zip;
    private String phone;
    private int id;
    
    /**
     * explicit constructor.
     * @param firstName
     * @param lastName
     * @param emailAddress
     * @param streetAddress
     * @param cityName
     * @param stateAbbrev
     * @param zipCode
     * @param phoneNum
     */
    public Customer (String firstName, String lastName, String emailAddress,
            String streetAddress, String cityName, String stateAbbrev,
            int zipCode, String phoneNum)
    {
        first = firstName;
        last = lastName;
        email = emailAddress;
        street = streetAddress;
        city = cityName;
        state = stateAbbrev;
        zip = zipCode;
        phone = phoneNum;
        id = 0;
    }
    
    /**
     * implicit constructor.
     */
    public Customer()
    {
        first = "";
        last = "";
        email = "";
        street = "";
        city = "";
        state = "";
        zip = 0;
        phone = "";
        id = 0;
    }
    
    /**
     * returns the id of the Customer
     * @return the id
     */
    public int getId()
    {
    	return id;
    }
    
    /**
     * sets the id of the Customer
     * @param customerID the customer ID
     */
    public void setId(int customerID)
    {
    	id = customerID;
    }
    
    /**
     * returns the first name of the customer.
     * @return the first name
     */
    public String getFirst()
    {
        return first;
    }
    
    /**
     * returns the last name of the customer.
     * @return the last name
     */
    public String getLast()
    {
        return last;
    }
    
    /**
     * returns the email address of the customer.
     * @return the email address
     */
    public String getEmail()
    {
        return email;
    }
    
    /**
     * returns the street address.
     * @return the street address
     */
    public String getStreet()
    {
        return street;
    }
    
    /**
     * returns the city.
     * @return the city
     */
    public String getCity()
    {
        return city;
    }
    
    /**
     * returns the state.
     * @return the state abbreviation
     */
    public String getState()
    {
        return state;
    }
    
    /**
     * returns the zip code.
     * @return the zip code
     */
    public int getZip()
    {
        return zip;
    }
    
    /**
     * returns the phone number.
     * @return the phone number
     */
    public String getPhone()
    {
        return phone;
    }
}
