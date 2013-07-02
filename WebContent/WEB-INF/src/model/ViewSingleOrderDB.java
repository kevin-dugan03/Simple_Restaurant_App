package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import objects.Customer;
import objects.Order;
import objects.Pizza;

public class ViewSingleOrderDB {
	
	private String firstName;
	private String lastName;
	private String phoneNum;
	Order order = new Order(0, 0, 0.00);
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet result = null;
	
	/**
	 * Constructor for single orderDB object
	 * @param first customer first name
	 * @param last customer last name
	 * @param phone customer phone number
	 */
	public ViewSingleOrderDB(String first, String last, String phone)
	{
		firstName = first;
		lastName = last;
		phoneNum = phone;
	}
	
	/**
	 * Gets the current order
	 * @return the current order
	 * @throws SQLException
	 */
	public Order getOrder() throws SQLException
	{
    	String orderNum = "SELECT COUNT(*) FROM APP.OrderTable";
    	String getCustomer = "SELECT * FROM APP.CustomerTable WHERE firstName=?" +
			"AND lastName=? AND phone=?";
    	String insertOrder = "INSERT INTO APP.OrderTable (customerID, orderPrice)" +
    			"VALUES (?,?)";
    	String getPizza = "SELECT * FROM APP.PizzaTable WHERE orderID=?";
    	String getSize = "SELECT sizeDescription FROM APP.SizeTable WHERE sizeID=?";
    	String getCrust = "SELECT crustDescription FROM APP.CrustTable WHERE crustID=?";
    	String getToppings = "SELECT toppingCode FROM APP.ToppingsTable WHERE pizzaID=?";
    	String getToppingName = "SELECT toppingDescription FROM APP.ToppingsReference WHERE toppingID=?";
    	String getCurrentOrderPrice = "SELECT orderPrice from APP.OrderTable WHERE orderID=?";
    	String updatePrice = "UPDATE APP.OrderTable SET orderPrice=? WHERE orderID=?";
    	
		int orderNumber = 0;
		int custNum = 0;
		ArrayList<Integer> pid = new ArrayList<Integer>();
    	ArrayList<Integer> oid = new ArrayList<Integer>();
    	ArrayList<Integer> size = new ArrayList<Integer>();
    	ArrayList<Integer> crust = new ArrayList<Integer>();
    	ArrayList<Double> price = new ArrayList<Double>();
		String sizeDesc = "";
		String crustDesc = "";
		ArrayList<Integer> toppingIDs = new ArrayList<Integer>();
    	ArrayList<String> tops = new ArrayList<String>();
    	String[] toppingsList = new String[0];
    	double pizzaPrice = 0.00;
    	double orderPrice = 0.00;

		try
        {
			InitialContext ctx = new InitialContext();
        	DataSource data = (DataSource) ctx.lookup("java:comp/env/jdbc/PizzaRestaurantDB");
        	conn = data.getConnection();
            
            ps = conn.prepareStatement(orderNum);       
            result = ps.executeQuery();
            
            //Put all the basic order information into the hashmap
            while (result.next())
            {
            	orderNumber = result.getInt(1) + 1;
            }

            ps = conn.prepareStatement(getCustomer);
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, phoneNum);
            result = ps.executeQuery();
            
            
            while (result.next())
            {
            	custNum = result.getInt(1);
            	order = new Order(orderNumber, custNum, 0.00);
            	order.setCustomer(new Customer(result.getString(2), result.getString(3),
            			result.getString(4), result.getString(5), result.getString(6),
            			result.getString(7), result.getInt(8), result.getString(9)));
            }
            
            ps = conn.prepareStatement(insertOrder);
            ps.setInt(1, custNum);
            ps.setDouble(2, 0.00);
            ps.executeUpdate();
            
            ps = conn.prepareStatement(getPizza);
            ps.setInt(1, orderNumber);
            result = ps.executeQuery();
            
            while (result.next())
            {
            	pid.add(result.getInt(1));
            	oid.add(result.getInt(2));
            	size.add(result.getInt(3));
            	crust.add(result.getInt(4));
            	price.add(result.getDouble(5));
            }
            
            for (int i = 0; i < pid.size(); i++)
            {
            	//get the size description of the pizza
            	ps = conn.prepareStatement(getSize);
                ps.setInt(1, size.get(i));
                result = ps.executeQuery();
                
                while (result.next())
                {
                	sizeDesc = result.getString(1);
                }
                
                //get the crust description of the pizza
                ps = conn.prepareStatement(getCrust);
                ps.setInt(1, crust.get(i));
                result = ps.executeQuery();
                
                while (result.next())
                {
                	crustDesc = result.getString(1);
                }
                
                //get the toppings IDs for each pizza
                ps = conn.prepareStatement(getToppings);
                ps.setInt(1, pid.get(i));
                result = ps.executeQuery();
                
                while (result.next())
                {
                	toppingIDs.add(result.getInt(1));
                }
                
                //for each topping ID, get the description of each
                for (int tid : toppingIDs)
                {
                	ps = conn.prepareStatement(getToppingName);
                	ps.setInt(1, tid);
                	result = ps.executeQuery();
                	
                	while (result.next())
                	{
                		tops.add(result.getString(1));
                	}
                }
                
                //convert the list of toppings to an array in order to pass to Pizza object
                toppingsList = (String[]) tops.toArray(new String[tops.size()]);
                
                //create a new Pizza object with the info, add it to the current order
                
                Pizza pizza = new Pizza(sizeDesc, crustDesc, toppingsList);
                pizzaPrice = price.get(i);
                pizza.setPrice(pizzaPrice);
                order.addPizza(pizza);
                order.addToPrice(pizzaPrice);
                
                ps = conn.prepareStatement(getCurrentOrderPrice);
                ps.setInt(1, orderNumber);
                result = ps.executeQuery();
                
                while (result.next())
                {
                	orderPrice = result.getInt(1) + pizzaPrice;
                }
                
                ps = conn.prepareStatement(updatePrice);
                ps.setDouble(1, orderPrice);
                ps.setInt(2, orderNumber);
                ps.executeUpdate();
                
                //reset the toppings list
                toppingsList = new String[0];
                toppingIDs.clear();
                tops.clear();
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
        
        return order;
	}
}
