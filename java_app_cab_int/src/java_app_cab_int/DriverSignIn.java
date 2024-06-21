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

public class DriverSignIn extends JFrame {

	private static final long serialVersionUID = -1253373471499442614L;
	private JPanel contentPane;
	private JTextField DriverEmail;
	private JPasswordField DriverPassword;
	private JLabel lblResult;
	public String emailDriverGlobal = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					DriverSignIn frame = new DriverSignIn();
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
	public DriverSignIn() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 460);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("E-mail");
		lblNewLabel.setFont(new Font("DejaVu Serif", Font.PLAIN, 22));
		lblNewLabel.setBounds(59, 155, 92, 25);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("DejaVu Serif", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(59, 261, 111, 25);
		contentPane.add(lblNewLabel_1);
		// ----- Exit -----
		JButton ExitProgramButton = new JButton("Exit");
		ExitProgramButton.setFont(new Font("DejaVu Serif", Font.BOLD, 18));
		ExitProgramButton.setBounds(571, 423, 117, 25);
		ExitProgramButton.setContentAreaFilled(false); // to make it looking like a Label - step 1
		ExitProgramButton.setBorderPainted(false); // to make it looking like a Label - step 2
		ExitProgramButton.setOpaque(false);
		ExitProgramButton.addActionListener((event) -> System.exit(0)); // to do Exit
		contentPane.add(ExitProgramButton);
  		// ------------------------
		JButton BackMainPageButton = new JButton("Back");
		BackMainPageButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// ------------------------------
				// ----- Back to Main Page -----
				// ------------------------------
				Java_app_cab_int main_page = new Java_app_cab_int();
				main_page.setVisible(true);
				setVisible(false);
				// dispose();
			}
		});
		BackMainPageButton.setFont(new Font("DejaVu Serif", Font.BOLD, 18));
		BackMainPageButton.setBounds(442, 423, 117, 25);
		BackMainPageButton.setContentAreaFilled(false); // to make it looking like a Label - step 1
		BackMainPageButton.setBorderPainted(false); // to make it looking like a Label - step 2
		BackMainPageButton.setOpaque(false);
		contentPane.add(BackMainPageButton);
		// -------------------------
		DriverEmail = new JTextField();
		DriverEmail.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		DriverEmail.setBounds(264, 148, 319, 41);
		contentPane.add(DriverEmail);
		DriverEmail.setColumns(10);

		DriverPassword = new JPasswordField();
		DriverPassword.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		DriverPassword.setBounds(264, 245, 319, 41);
		contentPane.add(DriverPassword);
		ExitProgramButton.setOpaque(false);

		JButton btnNewButton_3 = new JButton("Sign-in");
		btnNewButton_3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String emailCustomerLocal = DriverEmail.getText();

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabreservationsystem",
							"ahmedshawki", "nopass"); // viewed password ??

					// ----- Query the Driver in DB -----
					String queryCheckDriverValid = "SELECT passwd FROM driver WHERE email = ?;";
					// ----- Create a PreparedStatement -----
					PreparedStatement pstmtDriverEmail = con.prepareStatement(queryCheckDriverValid);
					// ----- Set the email parameter value -----
					pstmtDriverEmail.setString(1, emailCustomerLocal);
					// ----- Execute the Query -----
					ResultSet rsDriverPasswd = pstmtDriverEmail.executeQuery();
					//
					String correct_passwd = "";
					while (rsDriverPasswd.next()) {
						correct_passwd = correct_passwd + rsDriverPasswd.getString("passwd");
					}
					// ------ get the salt ------
					String queryDriverSalt = "SELECT driver_value FROM salt_driver WHERE driver_id = (SELECT id FROM driver WHERE email = ?) ;";
					PreparedStatement pstmtDriverSalt = con.prepareStatement(queryDriverSalt);
					pstmtDriverSalt.setString(1,emailCustomerLocal);
					ResultSet rsDriverSalt = pstmtDriverSalt.executeQuery() ;


					String driverSalt = "";

					while(rsDriverSalt.next()){
						driverSalt = driverSalt + rsDriverSalt.getString("driver_value");
					}
					// ------ Append the Salt with the Passwd ------

					String passwdDriverLocal = new String(DriverPassword.getPassword());
					String SaltedPasswdWithoutHashing = passwdDriverLocal + driverSalt;
					String hashedPasswdDriverLocal = hashPassword(SaltedPasswdWithoutHashing);

					// ----- Close the ResultSet and PreparedStatement -----
					rsDriverSalt.close();
					rsDriverPasswd.close();
					pstmtDriverSalt.close();
					pstmtDriverEmail.close();
					// --------------------------

					if (hashedPasswdDriverLocal.equals(correct_passwd)) {
						lblResult.setForeground(Color.blue);
						lblResult.setText("Correct !");
						emailDriverGlobal = emailCustomerLocal;
						// ----------------------------------
						// ---- open main driver Window -----
						// ----------------------------------
						Orders order_main = new Orders(emailDriverGlobal);
						order_main.setVisible(true);
						setVisible(false);
						// dispose();
						// ----------------------------------
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
		btnNewButton_3.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		btnNewButton_3.setBounds(534, 359, 117, 41);
		ExitProgramButton.setContentAreaFilled(false); // to make it looking like a Label - step 1
		ExitProgramButton.setBorderPainted(false); // to make it looking like a Label - step 2
		ExitProgramButton.setOpaque(false);
		contentPane.add(btnNewButton_3);
		// ------------------------------------------------------------
		JButton btnNewButton_3_1 = new JButton("Sign-up");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// ----- --------------------------- -----
				// ----- Go to Sign-up Driver Window -----
				// ----- --------------------------- -----
				DriverSignUp driver_signup = new DriverSignUp();
				driver_signup.setVisible(true);
				setVisible(false);
				// dispose();
			}
		});
		btnNewButton_3_1.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		btnNewButton_3_1.setBounds(345, 359, 117, 41);
		contentPane.add(btnNewButton_3_1);
		// -------------------------------------------------------------
		lblResult = new JLabel("--------------------");
		lblResult.setForeground(new Color(255, 215, 0));
		lblResult.setHorizontalAlignment(SwingConstants.CENTER);
		lblResult.setFont(new Font("DejaVu Serif", Font.PLAIN, 33));
		lblResult.setBounds(254, 305, 305, 56);
		contentPane.add(lblResult);

		JLabel lblNewLabel_2 = new JLabel("Login Driver");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("DejaVu Serif", Font.PLAIN, 36));
		lblNewLabel_2.setBounds(137, 36, 426, 79);
		contentPane.add(lblNewLabel_2);
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
}
