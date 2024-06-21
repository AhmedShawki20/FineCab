package java_app_cab_int;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CustomerSignUp extends JFrame {

	private static final long serialVersionUID = -374219048101362919L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_Sex;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel lblResult;
	private JComboBox<String> comboBoxSex;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					CustomerSignUp frame = new CustomerSignUp();
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
	public CustomerSignUp() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Exit");
		btnNewButton.setFont(new Font("DejaVu Serif", Font.BOLD, 18));
		btnNewButton.setBounds(771, 563, 117, 25);
		btnNewButton.setContentAreaFilled(false); // to make it looking like a Label - step 1
		btnNewButton.setBorderPainted(false); // to make it looking like a Label - step 2
		btnNewButton.setOpaque(false); // to make it looking like a Label - step 3
		btnNewButton.addActionListener((event) -> System.exit(0)); // to do Exit
		contentPane.add(btnNewButton);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// ----- ------------------------------- -----
				// ----- Back to Customer Sign-in Window -----
				// ----- ------------------------------- -----
				CustomerSignIn signinCustomerPageBack = new CustomerSignIn();
				signinCustomerPageBack.setVisible(true);
				setVisible(false);
				// dispose();
				// -------------------------------------------
			}
		});
		btnBack.setFont(new Font("DejaVu Serif", Font.BOLD, 18));
		btnBack.setBounds(642, 563, 117, 25);
		btnBack.setContentAreaFilled(false); // to make it looking like a Label - step 1
		btnBack.setBorderPainted(false); // to make it looking like a Label - step 2
		btnBack.setOpaque(false); // to make it looking like a Label - step 3
		contentPane.add(btnBack);

		JLabel lblUsername = new JLabel("First Name");
		lblUsername.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		lblUsername.setBounds(81, 215, 117, 25);
		contentPane.add(lblUsername);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		lblLastName.setBounds(81, 295, 117, 25);
		contentPane.add(lblLastName);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		lblEmail.setBounds(81, 362, 117, 25);
		contentPane.add(lblEmail);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		lblPassword.setBounds(81, 448, 117, 25);
		contentPane.add(lblPassword);

		JLabel lblSex = new JLabel("Sex");
		lblSex.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		lblSex.setBounds(510, 215, 117, 25);
		contentPane.add(lblSex);

		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		lblDateOfBirth.setBounds(510, 295, 117, 25);
		contentPane.add(lblDateOfBirth);

		JLabel lblUsername_1 = new JLabel("Phone");
		lblUsername_1.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		lblUsername_1.setBounds(510, 362, 117, 25);
		contentPane.add(lblUsername_1);

		textField = new JTextField();
		textField.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		textField.setBounds(227, 212, 232, 30);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		passwordField.setBounds(227, 445, 232, 30);
		contentPane.add(passwordField);

		JButton btnSignUp = new JButton("Sign Up");
		btnSignUp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String firstName_enter = textField.getText();
				// make the first letter is caps
				String firstNameEnter = firstName_enter.substring(0, 1).toUpperCase() + firstName_enter.substring(1).toLowerCase();
				String passwd_enter = new String(passwordField.getPassword());
				String lastName_enter = textField_1.getText();
				// make the first letter is caps
				String lastNameEnter = lastName_enter.substring(0, 1).toUpperCase() + lastName_enter.substring(1).toLowerCase();
				String email_enter = textField_2.getText();
//				String sex_enter = textField_Sex.getText();
				String sex_enter = comboBoxSex.getSelectedItem().toString();
				String birthDate_enter = textField_4.getText();
				String phone_enter = textField_5.getText();
				String hashedPasswd_enter ;
				String saltCustomerValue = generateRandomSalt() ;
				String SaltedPasswdWithoutHashing = passwd_enter + saltCustomerValue;
				hashedPasswd_enter = hashPassword(SaltedPasswdWithoutHashing);

				if (!(firstName_enter.isEmpty() || passwd_enter.isEmpty() || lastName_enter.isEmpty() ||
						email_enter.isEmpty() || sex_enter.isEmpty() || birthDate_enter.isEmpty() || phone_enter.isEmpty() )) {
					try {
						// --------- Connect the Server ---------
						Class.forName("com.mysql.jdbc.Driver");
						// --------- DB credentials ---------
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabreservationsystem",
								"ahmedshawki", "nopass"); // viewed password ??
						// -------- Query --------
						String insertionNewCustomer = "INSERT INTO customer (first_name,last_name,email,gender,b_date,phone,passwd) VALUES (?,?,?,?,?,?,?);";
						// ------ prepared statement ------
						PreparedStatement pstmtCustomerInfo = con.prepareStatement(insertionNewCustomer);
						// ------  ----------
						String queryCustomerEmail = "SELECT COUNT(*) FROM customer WHERE email =? ;";
						PreparedStatement pstmtCustomerEmail = con.prepareStatement(queryCustomerEmail) ;
						pstmtCustomerEmail.setString(1,email_enter);
						ResultSet rsCustomerEmail = pstmtCustomerEmail.executeQuery();

						int customerEmailValid = 0 ;
						while (rsCustomerEmail.next()){
							customerEmailValid = customerEmailValid + rsCustomerEmail.getInt(1);
						}
						if (customerEmailValid == 0) {
							try {

								// --------- Set the parameters to the Statement --------
								pstmtCustomerInfo.setString(1, firstNameEnter);
								pstmtCustomerInfo.setString(2, lastNameEnter);
								pstmtCustomerInfo.setString(3, email_enter);
								if (sex_enter.equals("Male") || sex_enter.equals("male") || sex_enter.equals("M") || sex_enter.equals("m")) {
									pstmtCustomerInfo.setString(4, "m");
								} else if (sex_enter.equals("Female") || sex_enter.equals("female") || sex_enter.equals("F") || sex_enter.equals("f")) {
									pstmtCustomerInfo.setString(4, "f");
								} else {
									pstmtCustomerInfo.setString(4, "N/A");
								}
								pstmtCustomerInfo.setString(5, birthDate_enter);
								pstmtCustomerInfo.setString(6, phone_enter);
								pstmtCustomerInfo.setString(7, hashedPasswd_enter);

								// -------- Execute to DB --------
								int rowsInserted = pstmtCustomerInfo.executeUpdate();
								// =====================================================================================
								// ============================= Update Customer Salt Table ============================
								// =====================================================================================
								String updateCustomerSalt = "INSERT INTO salt_customer (customer_value,customer_id) VALUES (?,(SELECT id FROM customer WHERE email = ? )); ";
								PreparedStatement pstmtCustomerSalt = con.prepareStatement(updateCustomerSalt);
								pstmtCustomerSalt.setString(1, saltCustomerValue);
								pstmtCustomerSalt.setString(2, email_enter);
								pstmtCustomerSalt.executeUpdate();
								// ===================================================================================

								if (rowsInserted > 0) {
									lblResult.setBackground(new Color(230, 230, 250));
									lblResult.setForeground(new Color(0, 128, 0));
									lblResult.setText("Done! Back to Signin");
								} else {
									lblResult.setBackground(new Color(230, 230, 250));
									lblResult.setForeground(Color.RED);
									lblResult.setText("Wrong");
								}
//						lblResult.setBackground(new Color(230, 230, 250));
//						lblResult.setForeground(new Color(0, 128, 0));
//						lblResult.setText("Done!");

							} catch (Exception e) {
								lblResult.setBackground(new Color(230, 230, 250));
								lblResult.setForeground(Color.RED);
								lblResult.setText("SomeThing missed!");

							}
						}else{
							lblResult.setBackground(new Color(230, 230, 250));
							lblResult.setForeground(Color.RED);
							lblResult.setText("Email is Used!");
						}
						pstmtCustomerInfo.close();
						con.close();
					} catch (Exception ex) {
						lblResult.setBackground(new Color(230, 230, 250));
						lblResult.setForeground(Color.RED);
						lblResult.setText("Connection error!");
						ex.printStackTrace();
					}

				}else{
					lblResult.setBackground(new Color(230, 230, 250));
					lblResult.setForeground(Color.RED);
					lblResult.setText("SomeThing missed!");
				}
			}
		});
		btnSignUp.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		btnSignUp.setBounds(702, 480, 117, 42);
		contentPane.add(btnSignUp);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		textField_1.setColumns(10);
		textField_1.setBounds(227, 292, 232, 30);
		contentPane.add(textField_1);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		textField_2.setColumns(10);
		textField_2.setBounds(227, 368, 232, 30);
		contentPane.add(textField_2);

		// ____________
		comboBoxSex = new JComboBox<>();
		comboBoxSex.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		comboBoxSex.setBackground(new Color(255, 215, 0));
		comboBoxSex.setBounds(642, 212, 232, 30);
		comboBoxSex.addItem("Male");
		comboBoxSex.addItem("Female");
		contentPane.add(comboBoxSex);
		// ____________
//		textField_Sex = new JTextField();
//		textField_Sex.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
//		textField_Sex.setColumns(10);
//		textField_Sex.setBounds(642, 212, 232, 30);
//		contentPane.add(textField_Sex);

		textField_4 = new JTextField();
		textField_4.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		textField_4.setColumns(10);
		textField_4.setBounds(642, 292, 232, 30);
		contentPane.add(textField_4);

		textField_5 = new JTextField();
		textField_5.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		textField_5.setColumns(10);
		textField_5.setBounds(642, 368, 232, 30);
		contentPane.add(textField_5);

		JLabel lblYearmonthday = new JLabel("Ex: year-month-day");
		lblYearmonthday.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		lblYearmonthday.setBounds(642, 324, 199, 15);
		contentPane.add(lblYearmonthday);

		lblResult = new JLabel("-------");
		lblResult.setBackground(new Color(230, 230, 250));
		lblResult.setForeground(new Color(255, 215, 0));
		lblResult.setFont(new Font("DejaVu Serif", Font.PLAIN, 30));
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setBounds(346, 487, 344, 40);
		contentPane.add(lblResult);

		JLabel lblPhoto = new JLabel("");
		lblPhoto.setHorizontalAlignment(SwingConstants.CENTER);
		lblPhoto.setBounds(46, 12, 795, 156);
		contentPane.add(lblPhoto);
		//
		lblPhoto.setIcon(new ImageIcon(new ImageIcon(
				"/java_app_cab_int/src/pic/taxi-lamp-yellow-black-car-accessory-taxi-lamp-yellow-black-car-accessory-vector-d-illustration-isolated-white-background-153883947.png")
				.getImage().getScaledInstance(480, 280, Image.SCALE_DEFAULT)));

		//
		setUndecorated(true);
		setLocationRelativeTo(null);

	}
	// Function to Hash the Passwd
	public static String hashPassword(String targetPassword) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] hash = md.digest(targetPassword.getBytes());
			StringBuilder hexString = new StringBuilder();
			for (byte b : hash) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
	// ---- Generate The Salt ----
	public static String generateRandomSalt() {
		final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder sb = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < 16; i++) {
			int index = random.nextInt(chars.length());
			sb.append(chars.charAt(index));
		}
		return sb.toString();
	}


}
