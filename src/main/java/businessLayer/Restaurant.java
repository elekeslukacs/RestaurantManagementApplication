package businessLayer;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import dataLayer.RestaurantSerializator;
import presentationLayer.ChefFrame;

public class Restaurant implements RestaurantProcessing, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HashSet<MenuItem> menu;
	private HashMap<Order, ArrayList<MenuItem>> orders;
	private RestaurantSerializator ser ;
	private ArrayList<ChefFrame> observer;
	

	public Restaurant() {
		this.ser = new RestaurantSerializator();
		this.menu = ser.deserializeHash();
		this.orders = new HashMap<Order, ArrayList<MenuItem>>();
		this.observer = new ArrayList<ChefFrame>();
	}
	
	public void createItem(MenuItem item) {
		assert item != null;
		this.menu.add(item);
		ser.serializeHash(this.menu);
	}
	
	public void deleteItem(MenuItem item) {
		assert item != null;
		this.menu.remove(item);
		ser.serializeHash(this.menu);
	}
	
	public void editItem(MenuItem itemOld, MenuItem itemNew) {
		assert itemOld != null;
		assert itemNew != null;
		this.menu.remove(itemOld);
		this.menu.add(itemNew);
		ser.serializeHash(this.menu);
	}
	
	public void createOrder(Order order, ArrayList<MenuItem> items) {
		assert order != null;
		assert items.size() != 0;
		this.orders.put(order,  items);
		notifyAllObservers(order);
	}
	
	public int computePriceForOrder(Order order) {
		assert order != null;
		ArrayList<MenuItem> items = new ArrayList<MenuItem>();
		items = this.orders.get(order);
		int computedPrice = 0;
		for(MenuItem item : items) {
			computedPrice += item.computePrice();
		}
		return computedPrice;
	}
	
	public String generateBill(Order order) {
		assert order != null;
		ArrayList<MenuItem> items = new ArrayList<MenuItem>();
		items = this.orders.get(order);
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		StringBuilder sb = new StringBuilder();
		sb.append("Order details: " + order.getOrderID() + "\r\n");
		sb.append("\r\nTable: " + order.getTable());
		sb.append("\r\nDate: " + dateFormat.format(order.getDate()));
		for(MenuItem item : items) {
			sb.append("\r\n" + item.getName() + ": " + item.computePrice());
		}
		sb.append("\r\nTotal price: " + computePriceForOrder(order));
		
		return sb.toString();
	}

	public HashSet<MenuItem> getMenu() {
		return menu;
	}

	public void setMenu(HashSet<MenuItem> menu) {
		this.menu = menu;
	}

	public HashMap<Order, ArrayList<MenuItem>> getOrders() {
		return orders;
	}

	public void setOrders(HashMap<Order, ArrayList<MenuItem>> orders) {
		this.orders = orders;
	}
	
	public void attach(ChefFrame frame) {
		this.observer.add(frame);
	}
	
	public void notifyAllObservers(Order o) {
		for(ChefFrame c : observer ) {
			c.update(o, this.orders.get(o));
		}
	}
	
}
