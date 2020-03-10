package presentationLayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import businessLayer.BaseProduct;
import businessLayer.CompositeProduct;
import businessLayer.MenuItem;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

public class WaiterMainFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtTable;
	private JTextField txtTable_1;
	private JScrollPane scrollPane;
	private JButton btnAddSelected;
	private JTextArea textArea;
	private JButton btnPlaceOrder;
	private JButton btnComputePrice;
	private JTextArea textArea_1;
	
	/**
	 * Create the frame.
	 */
	public WaiterMainFrame() {
		setTitle("Waiter");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1002, 778);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
//		table = new JTable();
//		table.setBounds(493, 37, 432, 451);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(493, 37, 432, 451);
		contentPane.add(scrollPane);
		
		JLabel lblNewOrder = new JLabel("New Order");
		lblNewOrder.setBounds(28, 36, 121, 50);
		contentPane.add(lblNewOrder);
		
		JLabel lblTable = new JLabel("Table");
		lblTable.setBounds(28, 86, 68, 34);
		contentPane.add(lblTable);
		
		txtTable = new JTextField();
		txtTable.setBounds(100, 92, 116, 22);
		contentPane.add(txtTable);
		txtTable.setColumns(10);
		
		btnAddSelected = new JButton("Add Selected");
		btnAddSelected.setActionCommand("addselected");
		btnAddSelected.setBounds(25, 158, 145, 39);
		contentPane.add(btnAddSelected);
		
		textArea = new JTextArea();
		textArea.setBounds(28, 224, 260, 142);
		contentPane.add(textArea);
		
		btnPlaceOrder = new JButton("Place Order");
		btnPlaceOrder.setActionCommand("placeorder");
		btnPlaceOrder.setBounds(28, 404, 168, 50);
		contentPane.add(btnPlaceOrder);
		
		JLabel lblComputePrice = new JLabel("Compute Price");
		lblComputePrice.setBounds(28, 506, 103, 27);
		contentPane.add(lblComputePrice);
		
		JLabel lblNewLabel = new JLabel("Table");
		lblNewLabel.setBounds(23, 563, 73, 34);
		contentPane.add(lblNewLabel);
		
		txtTable_1 = new JTextField();
		txtTable_1.setBounds(81, 569, 51, 22);
		contentPane.add(txtTable_1);
		txtTable_1.setColumns(10);
		
		btnComputePrice = new JButton("Compute Price");
		btnComputePrice.setActionCommand("computeprice");
		btnComputePrice.setBounds(174, 568, 153, 34);
		contentPane.add(btnComputePrice);
		
		textArea_1 = new JTextArea();
		textArea_1.setBounds(480, 550, 445, 153);
		JScrollPane sp = new JScrollPane(textArea_1);
		sp.setBounds(480, 550, 445, 153);
		contentPane.add(sp);
		//this.setVisible(true);
	}
	
	public void addListener(ActionListener l) {
		btnAddSelected.addActionListener(l);
		btnComputePrice.addActionListener(l);
		btnPlaceOrder.addActionListener(l);
	}
	public JTable getTable() {
		return this.table;
	}
	
	public void setTable(JTable newTable) {
		this.table = newTable;
		scrollPane.setViewportView(table);
		repaint();
		revalidate();
	}
	
	public int getTableOrder() {
		try {
			int num = Integer.parseInt(this.txtTable.getText());
			return num;
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Wrong format");
			throw new NumberFormatException("not number");
		}
	}
	
	public int getTableForBill() {
		try {
			int num = Integer.parseInt(this.txtTable_1.getText());
			return num;
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Wrong format");
			throw new NumberFormatException("not number");
		}
	}
	
	public MenuItem getSelectedItem(HashSet<MenuItem> items) {
		int row = this.table.getSelectedRow();
		String name = this.table.getValueAt(row, 0).toString();
		int price = Integer.parseInt(this.table.getValueAt(row, 1).toString());
		MenuItem item = new BaseProduct(price, name);
		CompositeProduct product = new CompositeProduct(name);
		product.setPrice(price);
		
		if(items.contains(item)) {
			return item;
		}
		else {
			for(MenuItem it: items) {
				if(it.getName().equals(name)) {
					return it;
				}
			}
		}
		return null;
	}
	
	public void addTextOrder(String s) {
		this.textArea.append(s + "\n");
	}
	
	public void clearTextOrder() {
		this.textArea.setText("");
	}
	
	public void addTextBill(String s) {
		this.textArea_1.setText(s);
	}
}
