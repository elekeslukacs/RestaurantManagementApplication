package presentationLayer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnAdministrator;
	private JButton btnWaiter;
	private JButton btnChef;
	
	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setTitle("Restaurant");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 523, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnAdministrator = new JButton("Administrator");
		btnAdministrator.setActionCommand("openadmin");
		btnAdministrator.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnAdministrator.setBounds(142, 58, 194, 96);
		contentPane.add(btnAdministrator);
		
		btnWaiter = new JButton("Waiter");
		btnWaiter.setActionCommand("openwaiter");
		btnWaiter.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnWaiter.setBounds(142, 207, 194, 96);
		contentPane.add(btnWaiter);
		
		btnChef = new JButton("Chef");
		btnChef.setActionCommand("openchef");
		btnChef.setFont(new Font("Tahoma", Font.PLAIN, 24));
		btnChef.setBounds(142, 361, 194, 84);
		contentPane.add(btnChef);
		
		this.setVisible(true);
	}
	
	public void addListener(ActionListener l) {
		this.btnAdministrator.addActionListener(l);
		this.btnChef.addActionListener(l);
		this.btnWaiter.addActionListener(l);
	}
}
