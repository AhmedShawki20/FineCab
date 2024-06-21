package java_app_cab_int;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class CustomerSignIn extends JFrame {

	private static final long serialVersionUID = 6700077913033747588L;
	private JPanel contentPane;
	private JTextField CustomerEmail;
	private JPasswordField CustomerPassword;
	private JLabel lblResult;
	public static String globalEmailCustomer ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					CustomerSignIn frame = new CustomerSignIn();
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
	public CustomerSignIn() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 460);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setUndecorated(true);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ------ Exit --------

		JButton ExitProgramButton = new JButton("Exit");
		ExitProgramButton.setFont(new Font("DejaVu Serif", Font.BOLD, 18));
		ExitProgramButton.setBackground(new Color(255, 192, 203));
		ExitProgramButton.setBounds(571, 423, 117, 25);
		ExitProgramButton.setContentAreaFilled(false); // to make it looking like a Label - step 1
		ExitProgramButton.setBorderPainted(false); // to make it looking like a Label - step 2
		ExitProgramButton.setOpaque(false);
		ExitProgramButton.addActionListener((event) -> System.exit(0));
		contentPane.add(ExitProgramButton);

		//---------------------
		JButton BackMainPageButton = new JButton("Back");
		BackMainPageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// ----- ----------------- -----
				// ----- Back to Main Page -----
				// ----- ----------------- -----
				Java_app_cab_int main_page = new Java_app_cab_int();
				main_page.setVisible(true);
				setVisible(false);
				// dispose();
				// -----------------------------
			}
		});
		BackMainPageButton.setFont(new Font("DejaVu Serif", Font.BOLD, 18));
		BackMainPageButton.setBackground(new Color(255, 0, 0));
		BackMainPageButton.setBounds(418, 423, 117, 25);
		BackMainPageButton.setContentAreaFilled(false); // to make it looking like a Label - step 1
		BackMainPageButton.setBorderPainted(false); // to make it looking like a Label - step 2
		BackMainPageButton.setOpaque(false);
		contentPane.add(BackMainPageButton);

		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("DejaVu Serif", Font.PLAIN, 22));
		lblEmail.setBounds(67, 156, 109, 25);
		contentPane.add(lblEmail);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("DejaVu Serif", Font.PLAIN, 22));
		lblPassword.setBounds(67, 242, 109, 25);
		contentPane.add(lblPassword);

		CustomerEmail = new JTextField();
		CustomerEmail.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		CustomerEmail.setBounds(277, 149, 286, 42);
		contentPane.add(CustomerEmail);
		CustomerEmail.setColumns(10);

		JLabel lblLoginClient = new JLabel("Login Customer");
		lblLoginClient.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginClient.setFont(new Font("DejaVu Serif", Font.PLAIN, 36));
		lblLoginClient.setBounds(137, 36, 426, 79);
		contentPane.add(lblLoginClient);

		JButton SignInCustomerButton = new JButton("Sign-In");
		SignInCustomerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String emailCustomerLocal = CustomerEmail.getText();

				try {
					// --------- Connect the Server ---------
					Class.forName("com.mysql.jdbc.Driver");
					// --------- DB credentials ---------
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabreservationsystem",
							"ahmedshawki", "nopass"); // viewed password ??
					// ----- Query the Customer in DB -----
					String queryCheckCustomerValid = "SELECT passwd FROM customer WHERE email = ?;";
					// ----- Create a PreparedStatement -----
					PreparedStatement pstmtCustomerEmail = con.prepareStatement(queryCheckCustomerValid);
					// ----- Set the email parameter value -----
					pstmtCustomerEmail.setString(1, emailCustomerLocal);
					// ----- Execute the Query -----
					ResultSet rs_passwd = pstmtCustomerEmail.executeQuery();
					//
					String correct_passwd = "";
					while (rs_passwd.next()) {
						correct_passwd = correct_passwd + rs_passwd.getString("passwd");
					}
					// ------ get the salt ------
					String queryCustomerSalt = "SELECT customer_value FROM salt_customer WHERE customer_id = (SELECT id FROM customer WHERE email = ?) ;";
					PreparedStatement pstmtCustomerSalt = con.prepareStatement(queryCustomerSalt);
					pstmtCustomerSalt.setString(1,emailCustomerLocal);
					ResultSet rsCustomerSalt = pstmtCustomerSalt.executeQuery() ;


					String customerSalt = "";

					while(rsCustomerSalt.next()){
						customerSalt = customerSalt + rsCustomerSalt.getString("customer_value");
					}
					// ------ Append the Salt with the Passwd ------

					String passwdCustomerLocal = new String(CustomerPassword.getPassword());
					String SaltedPasswdWithoutHashing = passwdCustomerLocal + customerSalt;
					String hashedPasswordCustomerLocal = hashPassword(SaltedPasswdWithoutHashing);

					// ----- Close the ResultSet and PreparedStatement -----
					rsCustomerSalt.close();
					rs_passwd.close();
					pstmtCustomerEmail.close();
					pstmtCustomerSalt.close();
					// -----------------------
					if (hashedPasswordCustomerLocal.equals(correct_passwd)) {
						lblResult.setForeground(Color.blue);
						lblResult.setText("Correct !");
						globalEmailCustomer = emailCustomerLocal;
						// ----- ----------------------------- -----
						// ----- Go to Ride Reservation Window -----
						// ----- ----------------------------- -----
						RideReservation rideReservationPage = new RideReservation(globalEmailCustomer);
						rideReservationPage.setVisible(true);
						setVisible(false);
//						// dispose();
						// -----------------------------------------
					} else {
						lblResult.setForeground(Color.RED);
						lblResult.setText("Wrong !!");

					}
					con.close();
				} catch (Exception ex) {
					lblResult.setText(ex.getMessage());
				}
			}
		});
		SignInCustomerButton.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		SignInCustomerButton.setBounds(531, 340, 117, 42);
		contentPane.add(SignInCustomerButton);
		// -------------- Open Sign-Up ----------------
		JButton SignUpCustomerButton = new JButton("Sign-Up");
		SignUpCustomerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				CustomerSignUp signupCustomerPage = new CustomerSignUp();
				signupCustomerPage.setVisible(true);
				setVisible(false);
				// dispose();
			}

		});
		SignUpCustomerButton.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		SignUpCustomerButton.setBounds(351, 340, 117, 42);
		contentPane.add(SignUpCustomerButton);
		//
		CustomerPassword = new JPasswordField();
		CustomerPassword.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		CustomerPassword.setBounds(278, 234, 285, 43);
		contentPane.add(CustomerPassword);

		lblResult = new JLabel("--------------------------");
		lblResult.setForeground(new Color(255, 215, 0));
		lblResult.setFont(new Font("DejaVu Serif", Font.ITALIC, 27));
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setBounds(278, 289, 286, 42);
		contentPane.add(lblResult);
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

}
