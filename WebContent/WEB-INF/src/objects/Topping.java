package objects;

import java.io.Serializable;

/**
 * Represents a particular topping.
 * @author K Dugan
 *
 */
public class Topping implements Serializable{

	private static final long serialVersionUID = 1L;
	private int pizzaID;
	private int toppingID;
	
	/**
	 * Constructor for a topping object
	 * @param pID pizza ID the topping is ordered on.
	 * @param tID topping ID of the topping itself.
	 */
	public Topping(int pID, int tID)
	{
		pizzaID = pID;
		toppingID = tID;
	}
	
	/**
	 * Returns the pizza ID number
	 * @return pizza ID
	 */
	public int getPizzaID()
	{
		return pizzaID;
	}
	
	/**
	 * Returns the topping ID number
	 * @return the topping ID
	 */
	public int getToppingID()
	{
		return toppingID;
	}
}
