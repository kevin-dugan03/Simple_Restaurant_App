package objects;

import java.io.Serializable;

/**
 * Represents a Pizza.
 * @author KDugan
 */
public class Pizza implements Serializable {

	private static final long serialVersionUID = 1L;
	private String size;
    private String crust;
    private String[] toppings;
    private double price;
    
    public Pizza(String finalSize, String finalCrust, String[] finalToppings)
    {
        size = finalSize;
        crust = finalCrust;
        toppings = finalToppings;
        price = 0.00;
    }
    
    /**
     * returns the size of the pizza.
     * @return
     */
    public String getSize()
    {
        return size;
    }
    
    /**
     * set the pizza size.
     */
    public void setSize(String name)
    {
    	size = name;
    }
    
    /**
     * returns the crust type of the pizza.
     * @return
     */
    public String getCrust()
    {
        return crust;
    }
    
    /**
     * set crust.
     */
    public void setCrust(String name)
    {
    	crust = name;
    }
    
    /**
     * returns a list of toppings.
     * @return
     */
    public String[] getToppings()
    {
        return toppings;
    }
    
    /**
     * adds a toppings.
     */
    public void setToppings(String[] tops)
    {
    	toppings = tops;
    }
    
    /**
     * set the price.
     */
    public void setPrice(double newValue)
    {
    	price = newValue;
    }
    
    /**
     * gets the price.
     */
    public double getPrice()
    {
    	return price;
    }
    
}
