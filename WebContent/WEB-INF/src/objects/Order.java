package objects;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Represents an order.
 * @author KDugan
 *
 */
public class Order implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int orderID;
    private int customerId;
    private double orderPrice;
    private Customer customer;
    private ArrayList<Pizza> pizzas;
    
    /**
     * Constructor builds order info.
     * @param id The order ID number
     * @param custID The customer who placed the order
     * @param price The price of the order
     */
    public Order(int id, int custID, double price)
    {
        orderID = id;
        customerId = custID;
        orderPrice = price;  
        customer = new Customer();
        pizzas = new ArrayList<Pizza>();
    }
    
    /**
     * Implicit constructor sets default values.
     */
    public Order()
    {
        orderID = 0;
        customerId = 0;
        orderPrice = 0.00;  
        customer = new Customer();
        pizzas = new ArrayList<Pizza>();
    }
    
    /**
     * returns order id
     * @return the order ID
     */
    public int getID()
    {
        return orderID;
    }
    
    /**
     * returns customer ID.
     * @return the customer ID
     */
    public int getCustomerId()
    {
        return customerId;
    }
    
    /**
     * returns the order's price.
     * @return the price of the order
     */
    public double getPrice()
    {
        return orderPrice;
    }
    
    /**
     * add to the price.
     */
    public void addToPrice(double pizzaPrice)
    {
    	orderPrice += pizzaPrice;
    }
    
    /**
     * set customer.
     */
    public void setCustomer(Customer cust)
    {
    	customer = cust;
    }
    
    /**
     * get the customer.
     * @return the Customer
     */
    public Customer getCustomer()
    {
    	return customer;
    }
    
    /**
     * adds a pizza to the order
     */
    public void addPizza(Pizza pizza)
    {
    	pizzas.add(pizza);
    }
    
    /**
     * returns the list of pizzas
     * @return list of pizzas in the order
     */
    public ArrayList<Pizza> getPizzas()
    {
    	return pizzas;
    }
}
