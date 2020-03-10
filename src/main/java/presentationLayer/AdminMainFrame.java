package presentationLayer;

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

public class AdminMainFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtName;
	private JTextField txtPrice;
	private JTextField txtName_1;
	private JButton btnAdd;
	private	JButton btnAddBase;
	private JButton btnAddComposite;
	private JButton btnDelete;
	private JButton btnDelete_1;
	private JButton btnEdit;
	private JButton btnEdit_1;
	//private JButton btnFinish;
	private JScrollPane scrollPane;
	private JLabel lblAdded;

	/**
	 * Create the frame.
	 */
	public AdminMainFrame() {
		setTitle("Admin");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1012, 549);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(638, 35, 338, 393);
		contentPane.add(scrollPane);
		
		JLabel lblAddBaseProduct = new JLabel("Add Base Product");
		lblAddBaseProduct.setBounds(12, 34, 123, 25);
		contentPane.add(lblAddBaseProduct);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(12, 85, 56, 16);
		contentPane.add(lblName);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setBounds(12, 126, 56, 16);
		contentPane.add(lblPrice);
		
		txtName = new JTextField();
		txtName.setBounds(73, 82, 116, 22);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(73, 123, 116, 22);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		btnAdd = new JButton("Add ");
		btnAdd.setActionCommand("addadmin");
		btnAdd.setBounds(12, 172, 97, 25);
		contentPane.add(btnAdd);
		
		JLabel lblAddCompositeProduct = new JLabel("Add Composite Product");
		lblAddCompositeProduct.setBounds(12, 252, 166, 30);
		contentPane.add(lblAddCompositeProduct);
		
		txtName_1 = new JTextField();
		txtName_1.setBounds(73, 309, 116, 22);
		contentPane.add(txtName_1);
		txtName_1.setColumns(10);
		
		JLabel lblName_1 = new JLabel("Name");
		lblName_1.setBounds(12, 312, 56, 16);
		contentPane.add(lblName_1);
		
		lblAdded = new JLabel("");
		lblAdded.setBounds(245, 322, 195, 48);
		contentPane.add(lblAdded);
		
		btnAddBase = new JButton("Add Base");
		btnAddBase.setActionCommand("addbase");;
		btnAddBase.setBounds(12, 383, 97, 25);
		contentPane.add(btnAddBase);
		
		btnAddComposite = new JButton("Add Composite");
		btnAddComposite.setActionCommand("addcomposite");
		btnAddComposite.setBounds(151, 383, 150, 25);
		contentPane.add(btnAddComposite);
		
		btnDelete = new JButton("Delete");
		btnDelete.setActionCommand("delete");
		btnDelete.setBounds(151, 172, 97, 25);
		contentPane.add(btnDelete);
		
		btnDelete_1 = new JButton("Delete");
		btnDelete_1.setActionCommand("delete1");
		btnDelete_1.setBounds(354, 383, 97, 25);
		contentPane.add(btnDelete_1);
		
		btnEdit = new JButton("Edit");
		btnEdit.setActionCommand("edit");
		btnEdit.setBounds(304, 172, 97, 25);
		contentPane.add(btnEdit);
		
		btnEdit_1 = new JButton("Edit");
		btnEdit_1.setActionCommand("edit1");
		btnEdit_1.setBounds(505, 383, 97, 25);
		contentPane.add(btnEdit_1);

		//this.setVisible(true);
	}
	
	public void addListener(ActionListener l) {
		btnAdd.addActionListener(l);
		btnAddBase.addActionListener(l);
		btnAddComposite.addActionListener(l);
		btnDelete.addActionListener(l);
		btnDelete_1.addActionListener(l);
		btnEdit.addActionListener(l);
		btnEdit_1.addActionListener(l);
	}
	
	public void setTable(JTable newTable) {
		this.table = newTable;
		scrollPane.setViewportView(table);
		repaint();
		revalidate();
	}
	
	public int getPrice() {
		try {
			int num = Integer.parseInt(this.txtPrice.getText());
			return num;
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Wrong format");
			throw new NumberFormatException("not number");
		}
	}
	
	public String getNameBase() {
		return this.txtName.getText();
	}
	
	public String getNameComposite() {
		return this.txtName_1.getText();
	}
	
	public MenuItem getSelectedItem(HashSet<MenuItem> items) {
		int row = this.table.getSelectedRow();
		String name = this.table.getValueAt(row, 0).toString();
		int price = Integer.parseInt(this.table.getValueAt(row, 1).toString());
		MenuItem item = new BaseProduct(price, name);
		//CompositeProduct product = new CompositeProduct(name);
		//product.setPrice(price);
		
		if(items.contains(item)) {
//			setNameTxtField(item.getName());
//			setPriceTxtField(Integer.toString(item.computePrice()));
			return item;
		}
		else {
			for(MenuItem it: items) {
				if(it.getName().equals(name)) {
//					setNameCompositeField(it.getName());
					return it;
				}
			}
		}
		return null;
	}
	
	public void setLabelAdded(String s) {
		this.lblAdded.setText(s + " added.");
	}
	
	public void setNameTxtField(String s) {
		this.txtName.setText(s);
	}
	
	public void setPriceTxtField(String s) {
		this.txtPrice.setText(s);
	}
	
	public void setNameCompositeField(String s) {
		this.txtName_1.setText(s);
	}
}
