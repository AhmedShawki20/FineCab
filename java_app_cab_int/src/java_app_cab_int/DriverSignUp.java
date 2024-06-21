package java_app_cab_int;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class DriverSignUp extends JFrame {

	private static final long serialVersionUID = -5477924746007397915L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JPasswordField passwordField;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JLabel lblResult;
	private JTextField textField_12;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					DriverSignUp frame = new DriverSignUp();
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
	public DriverSignUp() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 599);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnExit = new JButton("Exit");
		btnExit.setBounds(771, 562, 117, 25);
		btnExit.setFont(new Font("DejaVu Serif", Font.BOLD, 18));
		btnExit.setContentAreaFilled(false); // to make it looking like a Label - step 1
		btnExit.setBorderPainted(false); // to make it looking like a Label - step 2
		btnExit.setOpaque(false); // to make it looking like a Label - step 3
		btnExit.addActionListener((event) -> System.exit(0)); // to do Exit
		contentPane.add(btnExit);

		JButton btnBack = new JButton("< Back");
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// ----- ----------------------------- -----
				// ----- Back to Driver Sign-in Window -----
				// ----- ----------------------------- -----
				DriverSignIn signinDriverPageBack = new DriverSignIn();
				signinDriverPageBack.setVisible(true);
				setVisible(false);
				// dispose();
				// -------------------------------------------
			}
		});
		btnBack.setBounds(642, 562, 117, 25);
		btnBack.setFont(new Font("DejaVu Serif", Font.BOLD, 18));
		btnBack.setContentAreaFilled(false); // to make it looking like a Label - step 1
		btnBack.setBorderPainted(false); // to make it looking like a Label - step 2
		btnBack.setOpaque(false); // to make it looking like a Label - step 3
		contentPane.add(btnBack);
		//
		JLabel lblUsername = new JLabel("First Name");
		lblUsername.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		lblUsername.setBounds(71, 58, 110, 30);
		contentPane.add(lblUsername);
		//
		JLabel lblNewLabel = new JLabel("Last Name");
		lblNewLabel.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		lblNewLabel.setBounds(71, 115, 110, 30);
		contentPane.add(lblNewLabel);
		//
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		lblPassword.setBounds(71, 234, 100, 30);
		contentPane.add(lblPassword);
		//
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		lblPhone.setBounds(71, 384, 100, 30);
		contentPane.add(lblPhone);
		//
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		lblDateOfBirth.setBounds(71, 296, 153, 30);
		contentPane.add(lblDateOfBirth);
		//
		JLabel lblCarModel = new JLabel("Car Type");
		lblCarModel.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		lblCarModel.setBounds(491, 58, 100, 30);
		contentPane.add(lblCarModel);
		//
		JLabel lblNewLabel_1 = new JLabel("Car Model");
		lblNewLabel_1.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(491, 115, 117, 30);
		contentPane.add(lblNewLabel_1);
		//
		JLabel lblNewLabel_2 = new JLabel("Car Version Released");
		lblNewLabel_2.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(494, 321, 182, 30);
		contentPane.add(lblNewLabel_2);

		textField = new JTextField();
		textField.setFont(new Font("DejaVu Serif", Font.PLAIN, 22));
		textField.setBounds(307, 59, 148, 30);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("DejaVu Serif", Font.PLAIN, 22));
		textField_1.setBounds(307, 116, 148, 30);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("DejaVu Serif", Font.PLAIN, 22));
		textField_2.setBounds(307, 297, 148, 30);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("DejaVu Serif", Font.PLAIN, 22));
		textField_3.setBounds(681, 322, 148, 30);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setFont(new Font("DejaVu Serif", Font.PLAIN, 22));
		textField_4.setBounds(681, 116, 148, 30);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		textField_5 = new JTextField();
		textField_5.setFont(new Font("DejaVu Serif", Font.PLAIN, 22));
		textField_5.setBounds(681, 250, 148, 30);
		contentPane.add(textField_5);
		textField_5.setColumns(10);

		JLabel lblCarColor = new JLabel("Car Color");
		lblCarColor.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		lblCarColor.setBounds(491, 249, 100, 30);
		contentPane.add(lblCarColor);

		JLabel lblCarMaxSpeed = new JLabel("Car Max. Speed ");
		lblCarMaxSpeed.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		lblCarMaxSpeed.setBounds(494, 176, 153, 30);
		contentPane.add(lblCarMaxSpeed);

//		JLabel lblNewLabel_3 = new JLabel("Car Photo");
//		lblNewLabel_3.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
//		lblNewLabel_3.setBounds(491, 432, 100,30);
//		contentPane.add(lblNewLabel_3);

		textField_6 = new JTextField();
		textField_6.setFont(new Font("DejaVu Serif", Font.PLAIN, 22));
		textField_6.setBounds(681, 59, 148, 30);
		contentPane.add(textField_6);
		textField_6.setColumns(10);

		textField_7 = new JTextField();
		textField_7.setFont(new Font("DejaVu Serif", Font.PLAIN, 22));
		textField_7.setBounds(681, 177, 148, 30);
		contentPane.add(textField_7);
		textField_7.setColumns(10);

		JLabel lblKmh = new JLabel("Km/h");
		lblKmh.setBounds(836, 177, 34, 28);
		contentPane.add(lblKmh);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(307, 262, 148, 14);
		contentPane.add(progressBar);
		//
		passwordField = new JPasswordField();
		passwordField.setBounds(307, 235, 148, 30);

		passwordField.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				int passLength = passwordField.getPassword().length;
				progressBar.setValue(passLength);

				if (passLength < 5) {
					progressBar.setForeground(Color.red);
				} else if (passLength >= 5 && passLength < 10) {
					progressBar.setForeground(Color.yellow);
				} else if (passLength >= 10) {
					progressBar.setForeground(Color.green);
				}

			}
		});

		contentPane.add(passwordField);

		JLabel lblE = new JLabel("Email");
		lblE.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		lblE.setBounds(71, 176, 100, 30);
		contentPane.add(lblE);

		textField_8 = new JTextField();
		textField_8.setFont(new Font("DejaVu Serif", Font.PLAIN, 22));
		textField_8.setColumns(10);
		textField_8.setBounds(307, 176, 148, 30);
		contentPane.add(textField_8);

		textField_9 = new JTextField();
		textField_9.setFont(new Font("DejaVu Serif", Font.PLAIN, 22));
		textField_9.setColumns(10);
		textField_9.setBounds(307, 385, 148, 30);
		contentPane.add(textField_9);

		textField_10 = new JTextField();
		textField_10.setFont(new Font("DejaVu Serif", Font.PLAIN, 22));
		textField_10.setColumns(10);
		textField_10.setBounds(307, 442, 148, 30);
		contentPane.add(textField_10);

		textField_11 = new JTextField();
		textField_11.setFont(new Font("DejaVu Serif", Font.PLAIN, 22));
		textField_11.setColumns(10);
		textField_11.setBounds(307, 499, 148, 30);
		contentPane.add(textField_11);

		JLabel lblPhoneoptional = new JLabel("Phone (Optional)");
		lblPhoneoptional.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		lblPhoneoptional.setBounds(71, 441, 176, 30);
		contentPane.add(lblPhoneoptional);

		JLabel lblPhoneoptional_1 = new JLabel("Phone (Optional)");
		lblPhoneoptional_1.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		lblPhoneoptional_1.setBounds(71, 498, 153, 30);
		contentPane.add(lblPhoneoptional_1);

		JButton btnSignup = new JButton("Sign-Up");
		btnSignup.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String firstName_enter = textField.getText();
				// to make the first letter in caps
				String firstNameEnter = firstName_enter.substring(0, 1).toUpperCase() + firstName_enter.substring(1).toLowerCase();
				String lastName_enter = textField_1.getText();
				// to make the first letter in caps
				String lastNameEnter = lastName_enter.substring(0, 1).toUpperCase() + lastName_enter.substring(1).toLowerCase();
				String birthDateEnter = textField_2.getText();
				String emailEnter = textField_8.getText();
				String passwdEnter = new String(passwordField.getPassword());
				String phoneEnter = textField_9.getText();
				String phoneSecondEnter = textField_10.getText();
				String phoneThirdEnter = textField_11.getText();
				String carType_enter = textField_6.getText();
				String carTypeEnter = carType_enter.substring(0,1).toUpperCase() + carType_enter.substring(1).toLowerCase();
				String carModelEnter = textField_4.getText();
				String carReleasedVersionEnter = textField_3.getText();
				String carColor_enter = textField_5.getText();
				String carColorEnter = carColor_enter.substring(0,1).toUpperCase() + carColor_enter.substring(1).toLowerCase();
				String carBodyTypeEnter = textField_12.getText();
				String carMaxSpeedEnter = textField_7.getText();
//				String hashedPasswdEnter = hashPassword(passwdEnter);
				String hashedPasswdEnter ;
				String saltDriverValue = generateRandomSalt() ;
				String SaltedPasswdWithoutHashing = passwdEnter + saltDriverValue;
				hashedPasswdEnter = hashPassword(SaltedPasswdWithoutHashing);

                if (!(firstNameEnter.isEmpty() || lastNameEnter.isEmpty() || birthDateEnter.isEmpty() || emailEnter.isEmpty() || passwdEnter.isEmpty()
						||phoneEnter.isEmpty() ||carColorEnter.isEmpty() || carModelEnter.isEmpty() || carTypeEnter.isEmpty() ||
						carReleasedVersionEnter.isEmpty()||carBodyTypeEnter.isEmpty()||carMaxSpeedEnter.isEmpty() )) {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabreservationsystem",
								"ahmedshawki", "nopass"); // viewed password ??
						// -------------- Query Car --------------
						String insertionNewDriverCar = "INSERT INTO car (type,model,version,color,body_type,speed) VALUES (?,?,?,?,?,?);";
						// -------------- Query Driver--------------
						String insertionNewDriver = "INSERT INTO driver (first_name,last_name,b_date,rate_review,email,passwd,car_id) VALUES (?,?,?,?,?,?,?);";
						// -------------- Query Driver's Phones --------------
						String insertionDriverPhone = "INSERT INTO driver_phone (phone_id,phone) VALUES (?,?);";
						// -------------- --------------
						PreparedStatement pstmtDriverCarInfo = con.prepareStatement(insertionNewDriverCar);
						PreparedStatement pstmtDriverInfo = con.prepareStatement(insertionNewDriver);
						PreparedStatement pstmtDriverPhoneInfo = con.prepareStatement(insertionDriverPhone);

						// -------------- --------------
						String queryDriverEmail = "SELECT COUNT(*) FROM driver WHERE email =? ;";
						PreparedStatement pstmtDriverEmail = con.prepareStatement(queryDriverEmail) ;
						pstmtDriverEmail.setString(1,emailEnter);
						ResultSet rsDriverEmail = pstmtDriverEmail.executeQuery();

						int driverEmailValid = 0 ;
						while (rsDriverEmail.next()){
							driverEmailValid = driverEmailValid + rsDriverEmail.getInt(1);
						}

						if(driverEmailValid == 0) {
							//
							try {
								// ----------------------------------------------------------
								// --------- Set the parameters to the Car Statements --------
								pstmtDriverCarInfo.setString(1, carTypeEnter);
								pstmtDriverCarInfo.setString(2, carModelEnter);
								pstmtDriverCarInfo.setString(3, carReleasedVersionEnter);
								pstmtDriverCarInfo.setString(4, carColorEnter);
								pstmtDriverCarInfo.setString(5, carBodyTypeEnter);
								pstmtDriverCarInfo.setString(6, carMaxSpeedEnter + " km/h");
								// ----------------------------------------------------------
								pstmtDriverCarInfo.executeUpdate(); // Execute
								// ----------------------------------------------------------
								// Query to find the id of the car to link it with the driver table
								ResultSet rsCarId = pstmtDriverCarInfo.executeQuery("SELECT id FROM car ORDER BY id DESC LIMIT 1;");
								// ----------------------------------------------------------
								int carId = 0;
								while (rsCarId.next()) {
									carId = carId + rsCarId.getInt("id");
								}
								// -----------------------------------------------------------
								// --------- Set the Parameters to the Driver Statements ----------
								pstmtDriverInfo.setString(1, firstNameEnter);
								pstmtDriverInfo.setString(2, lastNameEnter);
								pstmtDriverInfo.setString(3, birthDateEnter);
								pstmtDriverInfo.setNull(4, Types.INTEGER); // For review the driver
								pstmtDriverInfo.setString(5, emailEnter);
								pstmtDriverInfo.setString(6, hashedPasswdEnter);
								pstmtDriverInfo.setInt(7, carId);
								// ---------------- Execute ---------------
								pstmtDriverInfo.executeUpdate(); // Execute
								// ------------------------------------------
								// --- Query to find the id of the driver to link it with the driver phone table ---
								ResultSet rsDriverId = pstmtDriverInfo.executeQuery("SELECT id FROM driver ORDER BY id DESC LIMIT 1;");
								// ----------------------------------------------------------------
								int driverId = 0;
								while (rsDriverId.next()) {
									driverId = driverId + rsDriverId.getInt("id");
								}
								// ---------------------------------------------------------------
								// =====================================================================================
								// ============================= Update Customer Salt Table ============================
								// =====================================================================================
								String updateDriverSalt = "INSERT INTO salt_driver (driver_value,driver_id) VALUES (?,(SELECT id FROM driver WHERE email = ? )); ";
								PreparedStatement pstmtDriverSalt = con.prepareStatement(updateDriverSalt);
								pstmtDriverSalt.setString(1, saltDriverValue);
								pstmtDriverSalt.setString(2, emailEnter);
								pstmtDriverSalt.executeUpdate();
								// ===================================================================================
								// ---------------------------------------------------------------
								// ------ Set the Parameters to the Driver Phone Statements ------
								pstmtDriverPhoneInfo.setInt(1, driverId);
								pstmtDriverPhoneInfo.setString(2, phoneEnter);
								// ------ Execute -------
								pstmtDriverPhoneInfo.executeUpdate();
								// ---------------------------------------------------------------
								if (!phoneSecondEnter.isEmpty()) {
									pstmtDriverPhoneInfo.setInt(1, driverId);
									pstmtDriverPhoneInfo.setString(2, phoneSecondEnter);
									pstmtDriverPhoneInfo.executeUpdate();
								} else {
									// nothing
								}
								if (!phoneThirdEnter.isEmpty()) {
									pstmtDriverPhoneInfo.setInt(1, driverId);
									pstmtDriverPhoneInfo.setString(2, phoneThirdEnter);
									pstmtDriverPhoneInfo.executeUpdate();
								} else {
									// nothing
								}
//
								lblResult.setBackground(new Color(230, 230, 250));
								lblResult.setForeground(new Color(0, 128, 0));
								lblResult.setText("Done! Back to Signin");

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
						pstmtDriverCarInfo.close();
						pstmtDriverInfo.close();
						pstmtDriverPhoneInfo.close();
						con.close();
					}
//

					catch (Exception ex) {

					}
				}else{
					lblResult.setBackground(new Color(230, 230, 250));
					lblResult.setForeground(Color.RED);
					lblResult.setText("SomeThing missed!");
				}

			}
		});
		btnSignup.setFont(new Font("DejaVu Serif", Font.PLAIN, 21));
		btnSignup.setBounds(753, 475, 122, 42);
		contentPane.add(btnSignup);

		lblResult = new JLabel("Result");
		lblResult.setForeground(new Color(255, 215, 0));
		lblResult.setFont(new Font("Symbola", Font.PLAIN, 25));
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setBounds(493, 474, 242, 40);
		contentPane.add(lblResult);

		JLabel lblExYearmonthday = new JLabel("Ex: year-month-day");
		lblExYearmonthday.setFont(new Font("DejaVu Serif", Font.BOLD, 15));
		lblExYearmonthday.setBounds(307, 329, 148, 15);
		contentPane.add(lblExYearmonthday);

		JLabel lblNewLabel_3_1 = new JLabel("Car Body Type");
		lblNewLabel_3_1.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		lblNewLabel_3_1.setBounds(491, 384, 131, 30);
		contentPane.add(lblNewLabel_3_1);

		textField_12 = new JTextField();
		textField_12.setFont(new Font("DejaVu Serif", Font.PLAIN, 22));
		textField_12.setColumns(10);
		textField_12.setBounds(681, 384, 148, 30);
		contentPane.add(textField_12);

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
