package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import objects.Customer;
import objects.Order;
import objects.Pizza;

public class ViewAllOrdersDB {

	ArrayList<Order> orders;
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet result = null;
    
    public ViewAllOrdersDB()
    {
        orders = new ArrayList<Order>();
    }
    
    public ArrayList<Order> getAll() throws SQLException
    {
    	String getOrder = "SELECT * FROM APP.OrderTable";
    	String getCustomer = "SELECT * FROM APP.CustomerTable WHERE customerID=?";
    	String getPizza = "SELECT * FROM APP.PizzaTable WHERE orderID=?";
    	String getSize = "SELECT sizeDescription FROM APP.SizeTable WHERE sizeID=?";
    	String getCrust = "SELECT crustDescription FROM APP.CrustTable WHERE crustID=?";
    	String getToppings = "SELECT toppingCode FROM APP.ToppingsTable WHERE pizzaID=?";
    	String getToppingName = "SELECT toppingDescription FROM APP.ToppingsReference WHERE toppingID=?";
    	String updatePrice = "UPDATE APP.OrderTable SET orderPrice=? WHERE orderID=?";
    	
    	ArrayList<Integer> pid = new ArrayList<Integer>();
    	ArrayList<Integer> oid = new ArrayList<Integer>();;
    	ArrayList<Integer> size = new ArrayList<Integer>();;
    	ArrayList<Integer> crust = new ArrayList<Integer>();;
    	ArrayList<Double> price = new ArrayList<Double>();;
    	String sizeDesc = "";
    	String crustDesc = "";
    	ArrayList<Integer> toppingIDs = new ArrayList<Integer>();
    	ArrayList<String> tops = new ArrayList<String>();
    	String[] toppingsList = new String[0];
    	
    	try
        {
    		InitialContext ctx = new InitialContext();
        	DataSource data = (DataSource) ctx.lookup("java:comp/env/jdbc/PizzaRestaurantDB");
        	conn = data.getConnection();
            
            ps = conn.prepareStatement(getOrder);       
            result = ps.executeQuery();
            
            //Put all the orders into the hashmap
            while (result.next())
            {
            	Order ord =  new Order(result.getInt(1), 
            			result.getInt(2), result.getDouble(3));
            	orders.add(ord);
            }

            //go through each order, get the customer and pizza orders for display
            for(Order ord : orders)
            {
            	//get customer
            	int id = ord.getCustomerId();
            	
            	ps = conn.prepareStatement(getCustomer);
            	ps.setInt(1, id);
                result = ps.executeQuery();
                
                //find the customer for each order, update the hashMap
                while (result.next())
                {
                	Customer customer = new Customer(result.getString(2), result.getString(3),
                			result.getString(4), result.getString(5), result.getString(6),
                			result.getString(7), result.getInt(8), result.getString(9));
                	
                	//store the customer data into the proper order
                	ord.setCustomer(customer);
                }
                
                //get pizza data for each order
                ps = conn.prepareStatement(getPizza);
                ps.setInt(1, ord.getID());
                result = ps.executeQuery();
                
                //load the pizza codes into lists
                while (result.next())
                {
                	pid.add(result.getInt(1));
                	oid.add(result.getInt(2));
                	size.add(result.getInt(3));
                	crust.add(result.getInt(4));
                	price.add(result.getDouble(5));
                }
                
                //for each pizza, extract the size, crust, and toppings from database
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
                    ord.addPizza(pizza);
                    
                    //reset the toppings list
                    toppingsList = new String[0];
                    toppingIDs.clear();
                    tops.clear();
                }
                
                //reset the lists containing all the pizzas in the order
                pid.clear();
                oid.clear();
                size.clear();
                crust.clear();
                price.clear();
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
        
        return orders;
    }
}
