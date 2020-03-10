package businessLayer;

import java.util.ArrayList;
import java.util.HashSet;

public interface RestaurantProcessing {
	/**
	 * @pre item != null
	 * @param item
	 */
	public void createItem(MenuItem item);
	
	/**
	 * @pre item != null
	 * @param item
	 */
	public void deleteItem(MenuItem item);
	
	/**
	 * @pre itemOld != null
	 * @pre itemNew != null
	 * 
	 * @param itemOld
	 * @param itemNew
	 */
	public void editItem(MenuItem itemOld, MenuItem itemNew);
	
	/**
	 * @pre order  != null
	 * @pre items.size() != 0
	 * 
	 * @param order
	 * @param items
	 */
	public void createOrder(Order order, ArrayList<MenuItem> items);
	
	/**
	 * @pre order != null
	 * @param order
	 * @return
	 */
	public int computePriceForOrder(Order order);
	
	/**
	 * @pre order != null
	 * @param order
	 * @return
	 */
	public String generateBill(Order order);	
}
