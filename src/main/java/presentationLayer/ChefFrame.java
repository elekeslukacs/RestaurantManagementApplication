package presentationLayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLayer.MenuItem;
import businessLayer.Order;

import javax.swing.JTextArea;

public class ChefFrame extends JFrame {

	private JPanel contentPane;
	private JTextArea textArea;
	//private Restaurant restaurant;

	/**
	 * Create the frame.
	 */
	public ChefFrame() {
		setTitle("Chef");
	//	this.restaurant = restaurant;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 716, 523);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textArea = new JTextArea();
		textArea.setBounds(50, 29, 604, 415);
		contentPane.add(textArea);
		//this.setVisible(true);
	}
	
	public void addTextOrder(String s) {
		this.textArea.append(s + "\n");
	}
	
	public void clearTextOrder() {
		this.textArea.setText("");
	}
	
	public void update(Order o, ArrayList<MenuItem> menuItems) {
		//ArrayList<MenuItem> items = this.restaurant.getOrders().get(o);
		ArrayList<MenuItem> items = menuItems;
		clearTextOrder();
		addTextOrder("New Order for table " + o.getTable() + " order number is " +o.getOrderID());
		for(MenuItem it: items) {
			addTextOrder(it.getName());
		}
	}
}
