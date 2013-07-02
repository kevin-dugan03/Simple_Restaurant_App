package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import objects.Pizza;
import objects.Size;
import objects.Topping;
/**
 * Adds a pizza to the database.
 * @author KDugan
 * @version 1.0
 */
public class AddPizzaDB
{
    private Pizza pizza;
    private Size size;
    private int nextOrder;
    private int crustID;
    private int getPizzaID;
    private double toppingPrice;
    private double sizePrice;
    ArrayList<Topping> topping;
    
    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet result = null;
    
    public AddPizzaDB(Pizza pza)
    {
        pizza = pza;
        size = null;
        nextOrder = 0;
        crustID = 0;
        getPizzaID = 0;
        toppingPrice = 0.00;
        sizePrice = 0.00;
        topping = new ArrayList<Topping>();
    }
    
    /**
     * Gets the required information and adds a pizza to the order.
     * @throws SQLException
     */
    public void addToOrder() throws SQLException
    {
        //SQL statements to run
        String orderNum = "SELECT COUNT(*) FROM APP.OrderTable";
        String getSize = "SELECT sizeID, sizePrice FROM APP.SizeTable WHERE " +
        		"sizeDescription=?";
        String getCrust = "SELECT crustID FROM APP.CrustTable WHERE crustDescription=?";
        String addToOrder = "INSERT INTO APP.PizzaTable (orderID, sizeID, crustID, " +
        		"pizzaPrice) VALUES (?,?,?,?)";
        String pizzaID = "SELECT COUNT(*) FROM APP.PizzaTable";
        String[] toppingList = pizza.getToppings();        
        String getToppingID = "SELECT toppingID, toppingPrice FROM APP.ToppingsReference " +
        		"WHERE toppingDescription=?";         
        String insertToppings = "INSERT INTO APP.ToppingsTable (pizzaID, toppingCode) " +
        		"VALUES (?,?)";       
        String updatePizza = "UPDATE APP.PizzaTable SET pizzaPrice=? WHERE pizzaID=?";
        

        //connect to the DB and run the SQL statements
        try
        {
        	InitialContext ctx = new InitialContext();
        	DataSource data = (DataSource) ctx.lookup("java:comp/env/jdbc/PizzaRestaurantDB");
        	conn = data.getConnection();
            
            //get the next order number
            ps = conn.prepareStatement(orderNum);           
            result = ps.executeQuery();
            
            while (result.next())
            {
                nextOrder = result.getInt(1) + 1;
            }
            
            //get the sizeID of the pizza
            ps = conn.prepareStatement(getSize);
            ps.setString(1, pizza.getSize());
            result = ps.executeQuery();
            
            while (result.next())
            {
            	size = new Size(result.getInt(1), pizza.getSize(), result.getDouble(2));
            	sizePrice = result.getDouble(2);
            }
            
            //get the crustID of the pizza
            ps = conn.prepareStatement(getCrust);
            ps.setString(1, pizza.getCrust());
            result = ps.executeQuery();
            
            while (result.next())
            {
                crustID = result.getInt(1);
            }            
            
            //add the pizza to the current order
            ps = conn.prepareStatement(addToOrder);
            ps.setInt(1, nextOrder);
            ps.setInt(2, size.getID());
            ps.setInt(3, crustID);
            ps.setDouble(4, 0.00);
            ps.executeUpdate();
            
            //get the pizza's ID for toppings update
            ps = conn.prepareStatement(pizzaID);
            result = ps.executeQuery();
            
            while (result.next())
            {
                getPizzaID = result.getInt(1);
            }
            
            if (toppingList != null)
            {
            	//get a list of the topping IDs to update the table with
                for (int i = 0; i < toppingList.length; i++)
                {
                    ps = conn.prepareStatement(getToppingID);
                    ps.setString(1, toppingList[i]);
                    result = ps.executeQuery();
                    
                    while (result.next())
                    {
                        topping.add(new Topping(getPizzaID, result.getInt(1)));
                        toppingPrice = toppingPrice + result.getDouble(2);
                    }                        
                }       
                
                //update the toppings table with the topping code and pizzaID
                for (Topping value : topping)
                {
                    ps = conn.prepareStatement(insertToppings);
                    ps.setInt(1, getPizzaID);
                    ps.setInt(2, value.getToppingID());
                    ps.executeUpdate();
                }
                
                
                ps = conn.prepareStatement(updatePizza);
                double price = toppingPrice + sizePrice;
                ps.setDouble(1, price);
                ps.setInt(2, getPizzaID);
                ps.executeUpdate();
                
                pizza.setPrice(price);
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
    }
}
