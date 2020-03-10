package presentationLayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JTable;

import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;
import businessLayer.MenuItem;
import businessLayer.Order;
import businessLayer.Restaurant;
import businessLayer.RestaurantProcessing;

public class Controller implements RestaurantProcessing {
	private Restaurant restaurant;
	private MainFrame main;
	private AdminMainFrame adminFrame;
	private WaiterMainFrame waiterFrame;
	private ChefFrame chef;
	private ButtonListener button;
	private ArrayList<MenuItem> selectedItems = new ArrayList<MenuItem>();
	private int orderCounter = 0;
	private ArrayList<Order> orders = new ArrayList<Order>();
	
	public Controller(){
		this.main = new MainFrame();
		this.adminFrame = new AdminMainFrame();
		this.waiterFrame = new WaiterMainFrame();
		this.restaurant = new Restaurant();
		//this.chef = new ChefFrame(restaurant);
		this.chef = new ChefFrame();
		restaurant.attach(chef);
		this.button = new ButtonListener();
		this.main.addListener(button);
		this.adminFrame.addListener(button);
		this.waiterFrame.addListener(button);
		adminFrame.setTable(createTable(restaurant.getMenu()));
		waiterFrame.setTable(createTable(restaurant.getMenu()));
	}
	
	private String fileNameCreator(Order o) {
		String name ="";
		name = "order"  + o.getOrderID() + o.getTable() +".txt";
		return name;
	}
	
	private JTable createTable(HashSet<MenuItem> items) {
		if(items.size() == 0) {
			return null;
		}
		//this.restaurant = serializator.deserializeRestaurant();
		String[] columnNames = new String[2];
		columnNames[0] = "Name";
		columnNames[1] = "Price";
		
		String content[][] = new String[items.size()][2];
		int i = 0;
		for(MenuItem item : items) {
			content[i][0] = item.getName();
			content[i][1] = "" + item.computePrice();
			i++;
		}
		JTable result = new JTable(content, columnNames);
		return result;
	}
	
	public void createItem(MenuItem item) {
		this.restaurant.createItem(item);
		//this.serializator.serializeRestaurant(this.restaurant);
		adminFrame.setTable(createTable(restaurant.getMenu()));
		waiterFrame.setTable(createTable(restaurant.getMenu()));
	}
	
	public void deleteItem(MenuItem item) {
		this.restaurant.deleteItem(item);
		//this.serializator.serializeRestaurant(this.restaurant);
		adminFrame.setTable(createTable(restaurant.getMenu()));
		waiterFrame.setTable(createTable(restaurant.getMenu()));
	}
	
	public void editItem(MenuItem itemOld, MenuItem itemNew) {
		this.restaurant.editItem(itemOld, itemNew);
		//this.serializator.serializeRestaurant(this.restaurant);
		adminFrame.setTable(createTable(restaurant.getMenu()));
		waiterFrame.setTable(createTable(restaurant.getMenu()));
	}
	
	public void createOrder(Order order, ArrayList<MenuItem> items) {
		//this.serializator.serializeRestaurant(this.restaurant);
		this.restaurant.createOrder(order, items);
	}
	
	public int computePriceForOrder(Order order) {
		int price = this.restaurant.computePriceForOrder(order);
		this.waiterFrame.addTextBill("Total price is " + price + " RON.");
		return price;
	}
	
	public String generateBill(Order order) {
		String bill = this.restaurant.generateBill(order);
		return bill;
	}
	
	class ButtonListener implements ActionListener {

		public void actionPerformed(ActionEvent event) {
			int price;
			int table;
			String name;
			MenuItem newItem;
			MenuItem oldItem;
			MenuItem editedItem;
			Order order;
			CompositeProduct composite = new CompositeProduct("");
			switch(event.getActionCommand()) {
			case"openadmin":
				adminFrame.setVisible(true);
				break;
			case "openwaiter":
				waiterFrame.setVisible(true);
				break;
			case "openchef":
				chef.setVisible(true);
				break;
			case "addadmin" :
				price = adminFrame.getPrice();
				name = adminFrame.getNameBase();
				createItem(new BaseProduct(price, name));
				break;
			case "addcomposite" :
				name = adminFrame.getNameComposite();
				newItem = new CompositeProduct(name);
				composite = new CompositeProduct(name);
				for(MenuItem item : selectedItems) {
					composite.addBaseProduct(item);
				}
				composite.computePrice();
				createItem(composite);
				selectedItems.clear();
				adminFrame.setLabelAdded("New Composite product ");
				break;
			case "addbase":
				selectedItems.add(adminFrame.getSelectedItem(restaurant.getMenu()));
				adminFrame.setLabelAdded(adminFrame.getSelectedItem(restaurant.getMenu()).getName());
				break;
			case "delete":
				deleteItem(adminFrame.getSelectedItem(restaurant.getMenu()));
				break;
			case "delete1":
				deleteItem(adminFrame.getSelectedItem(restaurant.getMenu()));
				break;
			case "edit":
				name = adminFrame.getSelectedItem(restaurant.getMenu()).getName();
				price = adminFrame.getSelectedItem(restaurant.getMenu()).computePrice();
//				adminFrame.setNameTxtField(name);
//				adminFrame.setPriceTxtField(Integer.toString(price));
				oldItem = new BaseProduct(price, name);
				editedItem = new BaseProduct(adminFrame.getPrice(), adminFrame.getNameBase());
				editItem(oldItem, editedItem);
				break;
			case "edit1":
				CompositeProduct oldProduct = (CompositeProduct) adminFrame.getSelectedItem(restaurant.getMenu());
				//adminFrame.setNameCompositeField(oldProduct.getName());
				name = adminFrame.getNameComposite();
				CompositeProduct editedProduct = new CompositeProduct(name);
				editedProduct.setItems(oldProduct.getItems());
				editedProduct.computePrice();
				editItem(oldProduct, editedProduct);
				break;
			case "addselected":
				newItem = waiterFrame.getSelectedItem(restaurant.getMenu());
				waiterFrame.addTextOrder(newItem.getName());
				selectedItems.add(newItem);
				break;
			case "placeorder":
				table = waiterFrame.getTableOrder();
				order = new Order(orderCounter, table);
				orderCounter++;
				orders.add(order);
				ArrayList<MenuItem> orderItems = new ArrayList<>();
				for(MenuItem item : selectedItems) {
					orderItems.add(item);
				}
				restaurant.createOrder(order, orderItems);
				selectedItems.clear();
				waiterFrame.clearTextOrder();
				break;
			case "computeprice":
				table = waiterFrame.getTableForBill();
				Order orderForBill = null;
				for(Order o : orders) {
					if(o.getTable() == table) {
						orderForBill = o;
						break;
					}
				}
				PrintWriter writer;
				try {
					writer = new PrintWriter(fileNameCreator(orderForBill), "UTF-8");
					writer.print(restaurant.generateBill(orderForBill));
					writer.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				
				waiterFrame.addTextBill(restaurant.generateBill(orderForBill));
				restaurant.getOrders().remove(orderForBill);
				orders.remove(orderForBill);
				
				break;
				
			}
		}
		
	}
	
	
	
	
	
	
}
