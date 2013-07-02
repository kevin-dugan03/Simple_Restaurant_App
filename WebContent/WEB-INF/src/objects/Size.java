package objects;

import java.io.Serializable;

/**
 * Represents a size for the pizza.
 * @author K Dugan
 *
 */
public class Size implements Serializable{

	private static final long serialVersionUID = 1L;
	private int sizeID;
	private String description;
	private double price;
	
	/**
	 * Constructor that sets the id, description, and cost of
	 * the size.
	 * @param id The id for the particular size
	 * @param desc The actual description of the size
	 * @param cost The price of the chosen size
	 */
	public Size(int id, String desc, double cost)
	{
		sizeID = id;
		description = desc;
		price = cost;
	}
	
	/**
	 * returns the size id of the pizza
	 * @return sizeID of the pizza
	 */
	public int getID()
	{
		return sizeID;
	}
	
	/**
	 * Returns the size description of the pizza.
	 * @return size description
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * Returns the price of the size.
	 * @return price of size.
	 */
	public double getPrice()
	{
		return price;
	}
	
}
