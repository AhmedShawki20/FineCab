package java_app_cab_int;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

@SuppressWarnings("unused")
public class Java_app_cab_int extends JFrame {

	private static final long serialVersionUID = 1L;

	// private Image img_logo = new
	// ImageIcon(Java_app_cab_int.class.getResource("pic/taxi_service_advertising_yellow_black_squares_flat_car_6832612.webp")).getImage().getScaledInstance(90,
	// 90, Image.SCALE_SMOOTH);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Java_app_cab_int frame = new Java_app_cab_int();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Java_app_cab_int() {
		getContentPane().setBackground(new Color(255, 215, 0));
		getContentPane().setLayout(null);
		//
		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setHorizontalAlignment(SwingConstants.LEFT);
		lblWelcome.setBounds(316, 12, 210, 93);
		lblWelcome.setFont(new Font("DejaVu Serif", Font.PLAIN, 40));
		getContentPane().add(lblWelcome);
		//
		//
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("DejaVu Serif", Font.BOLD, 20));
		btnExit.setBounds(422, 310, 142, 52);
		btnExit.setContentAreaFilled(false); // to make it looking like a Label - step 1
		btnExit.setBorderPainted(false); // to make it looking like a Label - step 2
		btnExit.setOpaque(false); // to make it looking like a Label - step 3
		btnExit.addActionListener((event) -> System.exit(0)); // to make exit action
		//
		JButton btnNewButton = new JButton("Customer");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// -----------------------------------------
				// ----- open Customer Sign-in Window -----
				// -----------------------------------------
				CustomerSignIn SignInCustomerMain = new CustomerSignIn();
                SignInCustomerMain.setVisible(true);
				setVisible(false);
				// dispose();
			}
		});
		btnNewButton.setBounds(352, 222, 224, 60);
		btnNewButton.setFont(new Font("DejaVu Serif", Font.PLAIN, 28));
		btnNewButton.setActionCommand("Customer");
		getContentPane().add(btnNewButton);
		//
		JButton btnNewButton_1 = new JButton("Driver");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// ----- --------------------------- -----
				// ----- open Driver Sign-in Window -----
				// ----- --------------------------- -----
				DriverSignIn SignInDriverMain = new DriverSignIn();
                SignInDriverMain.setVisible(true);
				setVisible(false);
				// dispose();
				// ---------------------------------------
			}
		});
		btnNewButton_1.setBounds(352, 139, 224, 60);
		btnNewButton_1.setFont(new Font("DejaVu Serif", Font.PLAIN, 28));
		btnNewButton_1.setActionCommand("Driver");
		getContentPane().add(btnNewButton_1);
		getContentPane().add(btnExit);
		//
		JLabel lblPhotoholder = new JLabel("");
		lblPhotoholder.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhotoholder.setBounds(12, 12, 322, 350);
		getContentPane().add(lblPhotoholder);
        //
		lblPhotoholder.setIcon(new ImageIcon(new ImageIcon("/java_app_cab_int/src/pic/Taxi-Logo-PNG-High-Quality-Image.png").getImage().getScaledInstance(280, 280, Image.SCALE_DEFAULT)));
		setBackground(new Color(50, 205, 50));
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 374);
		setUndecorated(true);
		setLocationRelativeTo(null);
	}
}
