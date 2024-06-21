package java_app_cab_int;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.lang.model.type.NullType;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class Orders extends JFrame {

	private static final long serialVersionUID = 6378225936760702351L;
	private JPanel contentPane;
	public String status;
	public String drivername;
	// ----- ----- ----- ----- ----- ----- ----- ----- -----
	public static String emailDriverSignedIn;
	public int idDriverTrip;
	public int id_trip_here;
	public static String tripStatusGlobal;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
//			private String driverEmailThisPage;

			@Override
			public void run() {
				try {
					Orders frame = new Orders(emailDriverSignedIn);
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
	public Orders(String emailDriverSignedIn) {
		//
		this.emailDriverSignedIn = emailDriverSignedIn;
		//
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 601);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setUndecorated(true);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("Exit");
		btnNewButton.setFont(new Font("DejaVu Serif", Font.BOLD, 18));
		btnNewButton.setBounds(532, 549, 156, 40);
		btnNewButton.setContentAreaFilled(false); // to make it looking like a Label - step 1
		btnNewButton.setBorderPainted(false); // to make it looking like a Label - step 2
		btnNewButton.setOpaque(false);
		btnNewButton.addActionListener((event) -> System.exit(0)); // to do Exit
		contentPane.add(btnNewButton);
		// --------------------------------------
		// -------------- Back ------------------
		// --------------------------------------
		JButton backToSigninDriver = new JButton("< Back");
		backToSigninDriver.setOpaque(false);
		backToSigninDriver.setFont(new Font("DejaVu Serif", Font.BOLD, 18));
		backToSigninDriver.setContentAreaFilled(false);
		backToSigninDriver.setBorderPainted(false);
		backToSigninDriver.setBounds(387, 549, 156, 40);
		backToSigninDriver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// ----- ----------------------------- -----
				// ----- Back to Driver Sign-in Window -----
				// ----- ----------------------------- -----
				DriverSignIn theSigninDriverBack = new DriverSignIn();
				theSigninDriverBack.setVisible(true);
				setVisible(false);
				// dispose();
				// -------------------------------------------
			}
		});
		contentPane.add(backToSigninDriver);

		JLabel lblDriversOrders = new JLabel("Driver's Orders");
		lblDriversOrders.setHorizontalAlignment(SwingConstants.CENTER);
		lblDriversOrders.setFont(new Font("DejaVu Serif", Font.BOLD, 30));
		lblDriversOrders.setBounds(104, 22, 487, 59);
		contentPane.add(lblDriversOrders);

		JLabel lblNameOfThe = new JLabel("Name of the Client ");
		lblNameOfThe.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblNameOfThe.setBounds(72, 137, 205, 28);
		contentPane.add(lblNameOfThe);

		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblLocation.setBounds(72, 267, 191, 28);
		contentPane.add(lblLocation);

		JLabel lblDestination = new JLabel("Destination");
		lblDestination.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblDestination.setBounds(72, 337, 191, 28);
		contentPane.add(lblDestination);

		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblPhone.setBounds(72, 203, 191, 28);
		contentPane.add(lblPhone);

		JLabel lblCustomerName = new JLabel("Nothing");
		lblCustomerName.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblCustomerName.setBounds(400, 137, 191, 28);
		contentPane.add(lblCustomerName);

		JLabel lblCustomerPhone = new JLabel("Nothing");
		lblCustomerPhone.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblCustomerPhone.setBounds(400, 203, 191, 28);
		contentPane.add(lblCustomerPhone);

		JLabel lblCustomerTripLocation = new JLabel("Nothing");
		lblCustomerTripLocation.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblCustomerTripLocation.setBounds(400, 267, 191, 28);
		contentPane.add(lblCustomerTripLocation);

		JLabel lblCustomerTripDestination = new JLabel("Nothing");
		lblCustomerTripDestination.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblCustomerTripDestination.setBounds(400, 337, 191, 28);
		contentPane.add(lblCustomerTripDestination);

		lblCustomerName.setText("Nothing");
		lblCustomerPhone.setText("Nothing");
		lblCustomerTripLocation.setText("Nothing");
		lblCustomerTripDestination.setText("Nothing");

		// =========  ==========  =========
		JLabel lblStatusCompletion = new JLabel("",JLabel.CENTER);
		lblStatusCompletion.setFont(new Font("DejaVu Serif", Font.PLAIN, 25));
		lblStatusCompletion.setForeground(new Color(255, 215, 0));
		lblStatusCompletion.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatusCompletion.setBounds(50, 530, 400, 32);
		contentPane.add(lblStatusCompletion);
		// =========  ==========  =========
		JButton btnCantAccept = new JButton("Sorry I can't Accept");
		btnCantAccept.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		btnCantAccept.setBounds(50, 481, 234, 40);
		contentPane.add(btnCantAccept);

		JButton btnAccept = new JButton("Accept");
		btnAccept.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		btnAccept.setBounds(300, 481, 117, 40);
		contentPane.add(btnAccept);

		JButton btnFlush = new JButton("Flush");
		btnFlush.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		btnFlush.setBounds(550, 481, 117, 40);
		contentPane.add(btnFlush);

		// =========  =========  =========
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblPrice.setBounds(72, 408, 191, 28);
		contentPane.add(lblPrice);
		
		JLabel lblPriceStatus = new JLabel("Nothing");
		lblPriceStatus.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblPriceStatus.setBounds(400, 408, 191, 28);
		contentPane.add(lblPriceStatus);
		// .*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.
		// .*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.*.
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabreservationsystem",
					"ahmedshawki", "nopass"); // viewed password ??
			// ===================================================================
			// ----- Query the Trip in DB -----
			String queryTripId = "SELECT trips_id FROM driver WHERE email = ?;" ;
			// ----- Create a PreparedStatement -----
			PreparedStatement pstmtTripId = con.prepareStatement(queryTripId);
			// ----- Set the parameters value -----
			pstmtTripId.setString(1, emailDriverSignedIn);
			// ----- Execute the Query -----
			ResultSet rsTripId = pstmtTripId.executeQuery();

			String driverEmail = emailDriverSignedIn; // email from the sign in driver
			int idDriverTripSub = 0; // trips_id from DRIVER that match the same trips_id from CUSTOMER

			// -----------------------------------------
			while (rsTripId.next()) {
				idDriverTripSub = idDriverTripSub + rsTripId.getInt("trips_id");
			}
			idDriverTrip = idDriverTripSub ;
			// ================================== Select Trip Status ==================================
			String qureyTripStatus = "SELECT status_trip FROM trips WHERE id = ?;";
			PreparedStatement pstmtTripStatus = con.prepareStatement(qureyTripStatus);
			pstmtTripStatus.setInt(1,idDriverTrip);
			ResultSet rsTripStatus = pstmtTripStatus.executeQuery();
			
			String tripStatus = "";
			
			while(rsTripStatus.next()){
				tripStatus = tripStatus + rsTripStatus.getString("status_trip");
			}
			tripStatusGlobal = tripStatus ;
			// ================================== Customer Name ===================================
			// ----- Query the Customer Name in DB -----
			String queryCustomerName = "SELECT CONCAT(first_name,\" \",last_name) FROM customer WHERE trips_id = ?;" ;
			// ----- Create a PreparedStatement -----
			PreparedStatement pstmtCunstomerName = con.prepareStatement(queryCustomerName);
			// ----- Set the parameters value -----
			pstmtCunstomerName.setInt(1, idDriverTrip);
			// ----- Execute the Query -----
			ResultSet rsCustomerName = pstmtCunstomerName.executeQuery();

			String customerName = "" ;
			while (rsCustomerName.next()) {
				customerName = customerName + rsCustomerName.getString("CONCAT(first_name,\" \",last_name)");
			}


			// =================================  Customer Phone ====================================
			// ----- Query the Customer Phone in DB -----
			String queryCustomerPhone = "SELECT phone FROM customer WHERE trips_id = ?;" ;
			// ----- Create a PreparedStatement -----
			PreparedStatement pstmtCunstomerPhone = con.prepareStatement(queryCustomerPhone);
			// ----- Set the parameters value -----
			pstmtCunstomerPhone.setInt(1, idDriverTrip);
			// ----- Execute the Query -----
			ResultSet rsCustomerPhone = pstmtCunstomerPhone.executeQuery();


			String customerPhone = "" ;
			while (rsCustomerPhone.next()) {
				customerPhone = customerPhone + rsCustomerPhone.getString("phone");
			}
			// =====================================================================
			// =====================================================================
			// ----- Query the Customer Location in DB -----
			String queryLocation = "SELECT destination FROM trips WHERE id = ?;" ;
			// ----- Create a PreparedStatement -----
			PreparedStatement pstmtCustomerLocation = con.prepareStatement(queryLocation);
			// ----- Set the parameters value -----
			pstmtCustomerLocation.setInt(1, idDriverTrip);
			// ----- Execute the Query -----
			ResultSet rstCustomerLocation = pstmtCustomerLocation.executeQuery();


			String customerLocation = "" ;
			while (rstCustomerLocation.next()) {
				customerLocation = customerLocation + rstCustomerLocation.getString("destination");
			}
			// =====================================================================
			// =====================================================================
			// ----- Query the Customer Destination in DB -----
			String queryDestination = "SELECT where_to FROM trips WHERE id = ?;" ;
			// ----- Create a PreparedStatement -----
			PreparedStatement pstmtDestination = con.prepareStatement(queryDestination);
			// ----- Set the parameters value -----
			pstmtDestination.setInt(1, idDriverTrip);
			// ----- Execute the Query -----
			ResultSet rsDestination = pstmtDestination.executeQuery();


			String customerDestination = "" ;
			while (rsDestination.next()) {
				customerDestination = customerDestination + rsDestination.getString("where_to");
			}
			// =====================================================================
			// ----- Query the Trip Price in DB -----
			String queryPrice = "SELECT price FROM trips WHERE id = ?;" ;
			// ----- Create a PreparedStatement -----
			PreparedStatement pstmtPrice = con.prepareStatement(queryPrice);
			// ----- Set the parameters value -----
			pstmtPrice.setInt(1, idDriverTrip);
			// ----- Execute the Query -----
			ResultSet rsPrice = pstmtPrice.executeQuery();


			String tripPrice = "" ;
			while (rsPrice.next()) {
				tripPrice = tripPrice + rsPrice.getString("price");
			}


			// =====================================================================
			// ========= Display the Info =========
			if (tripStatusGlobal.equals("Waiting") ) {
				if (customerName.isEmpty()) {
					lblCustomerName.setText("Nothing");
				} else {
					lblCustomerName.setText(customerName);
				}
				// ((( )))
				if (customerPhone.isEmpty()) {
					lblCustomerPhone.setText("Nothing");
				} else {
					lblCustomerPhone.setText(customerPhone);
				}
				// ((( )))
				if (customerLocation.isEmpty()) {
					lblCustomerTripLocation.setText("Nothing");
				} else {
					lblCustomerTripLocation.setText(customerLocation);
				}
				// ((( )))
				if (customerDestination.isEmpty()) {
					lblCustomerTripDestination.setText("Nothing");
				} else {
					lblCustomerTripDestination.setText(customerDestination);
				}
				// ((( )))
				if (tripPrice.isEmpty()) {
					lblPriceStatus.setText("Nothing");
				} else {
					lblPriceStatus.setText(tripPrice + " L.E.");
				}
				
			} else if (tripStatusGlobal.equals("Customer Cancelled")) {
				lblCustomerName.setText("Customer Cancelled");
				lblCustomerPhone.setText("Customer Cancelled");
				lblCustomerTripLocation.setText("Customer Cancelled");
				lblCustomerTripDestination.setText("Customer Cancelled");
				lblPriceStatus.setText("Customer Cancelled");
				lblStatusCompletion.setText("Please Flush");

			} else if (tripStatusGlobal.equals("Accepted")) {
				lblCustomerName.setText("You Accepted");
				lblCustomerPhone.setText("You Accepted");
				lblCustomerTripLocation.setText("You Accepted");
				lblCustomerTripDestination.setText("You Accepted");
				lblPriceStatus.setText("You Accepted");
				lblStatusCompletion.setText("Accepted");

			}else{
				lblCustomerName.setText("Nothing");
				lblCustomerPhone.setText("Nothing");
				lblCustomerTripLocation.setText("Nothing");
				lblCustomerTripDestination.setText("Nothing");
				lblPriceStatus.setText("Nothing");
			}
			// =======================================================
			// =======================================================

			con.close();
		} catch (Exception e) {

		}
		btnCantAccept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tripStatusGlobal.equals("Waiting")) {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabreservationsystem",
								"ahmedshawki", "nopass"); // viewed password ??

						// ========== Check the Driver Trip ===========
						String queryValidTrip = "SELECT trips_id FROM driver WHERE email = ? ;";
						PreparedStatement pstmtValidTrip = con.prepareStatement(queryValidTrip);
						pstmtValidTrip.setString(1, emailDriverSignedIn);
						ResultSet rsValidTrip = pstmtValidTrip.executeQuery();


						int validTrip = 0;
						while (rsValidTrip.next()) {
							validTrip = validTrip + rsValidTrip.getInt("trips_id");
						}

						if (!(validTrip == 0)) {
							// ===== Delete the Trip =====
							String deleteTheTrip = "UPDATE driver SET trips_id = ? WHERE email = ?;";
							// ----- Create a PreparedStatement -----
							PreparedStatement pstmtDeleteTheTrip = con.prepareStatement(deleteTheTrip);
							// ----- Set the parameters value -----
							pstmtDeleteTheTrip.setNull(1, Types.INTEGER); // Set trip id to null
							pstmtDeleteTheTrip.setString(2, emailDriverSignedIn);
							// ----- Execute the Query -----
							pstmtDeleteTheTrip.executeUpdate();
							lblStatusCompletion.setBackground(new Color(230, 230, 250));
							lblStatusCompletion.setForeground(new Color(0, 128, 0));
							lblStatusCompletion.setText("Done!");
							//						stmt_to_del.executeUpdate("UPDATE driver SET trips_id = NULL WHERE email = \""+email_driver+" \";");
							//						stmt_to_del.executeUpdate("DELETE FROM trips WHERE id = \"" + id_trip_here + "\";");
							//						String statusLocal = "Sorry I can't Accept";
							//						status = statusLocal;
							lblCustomerName.setText("Cancelled");
							lblCustomerPhone.setText("Cancelled");
							lblCustomerTripLocation.setText("Cancelled");
							lblCustomerTripDestination.setText("Cancelled");
							lblPriceStatus.setText("Cancelled");
							// ============= Check the Customer trip ===============
							String queryCustomerValidEmail = "SELECT email FROM customer WHERE trips_id = ? ;";
							PreparedStatement pstmtCustomerValidEmail = con.prepareStatement(queryCustomerValidEmail);
							pstmtCustomerValidEmail.setInt(1, idDriverTrip);
							ResultSet rsCustomerValidEmail = pstmtCustomerValidEmail.executeQuery();
							String validCustomerEmail = "";
							while (rsCustomerValidEmail.next()) {
								validCustomerEmail = validCustomerEmail + rsCustomerValidEmail.getString("email");
							}
							// ===========================
							String updateTripStatus = "UPDATE trips SET status_trip = ? WHERE id = ?; ";
							PreparedStatement pstmtTripStatus = con.prepareStatement(updateTripStatus);
							pstmtTripStatus.setString(1, "Refused");
							pstmtTripStatus.setInt(2, idDriverTrip);
							pstmtTripStatus.executeUpdate();

//						String acceptStatus = "Refused" ;
//						TripPage tripAcceptance = new TripPage(idDriverTrip,validCustomerEmail,acceptStatus);
							lblStatusCompletion.setBackground(new Color(230, 230, 250));
							lblStatusCompletion.setForeground(new Color(0, 128, 0));
							lblStatusCompletion.setText("Cancellation Done!");
							pstmtCustomerValidEmail.close();
							pstmtDeleteTheTrip.close();
						} else {
							lblStatusCompletion.setBackground(new Color(230, 230, 250));
							lblStatusCompletion.setForeground(new Color(0, 128, 0));
							lblStatusCompletion.setText("No Trips!");
						}
						pstmtValidTrip.close();
						con.close();
					} catch (Exception e) {

					}
				}else{
					lblStatusCompletion.setBackground(new Color(230, 230, 250));
					lblStatusCompletion.setForeground(new Color(200, 10, 10));
					lblStatusCompletion.setText("Something wrong!");
				}
			}
		});
		// ============================== ACCEPT ====================================
		btnAccept.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (tripStatusGlobal.equals("Waiting")) {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabreservationsystem",
								"ahmedshawki", "nopass"); // viewed password ??

						// ============== Check the Driver Trip ===============
						String queryDriverValidTrip = "SELECT trips_id FROM driver WHERE email = ? ;";
						PreparedStatement pstmtDriverValidTrip = con.prepareStatement(queryDriverValidTrip);
						pstmtDriverValidTrip.setString(1, emailDriverSignedIn);
						ResultSet rsDriverValidTrip = pstmtDriverValidTrip.executeQuery();


						int validDriverTrip = 0;
						while (rsDriverValidTrip.next()) {
							validDriverTrip = validDriverTrip + rsDriverValidTrip.getInt("trips_id");
						}
						// ============= Check the Customer trip ===============
						String queryCustomerValidEmail = "SELECT email FROM customer WHERE trips_id = ? ;";
						PreparedStatement pstmtCustomerValidEmail = con.prepareStatement(queryCustomerValidEmail);
						pstmtCustomerValidEmail.setInt(1, validDriverTrip);
						ResultSet rsCustomerValidEmail = pstmtCustomerValidEmail.executeQuery();
						String validCustomerEmail = "";
						while (rsCustomerValidEmail.next()) {
							validCustomerEmail = validCustomerEmail + rsCustomerValidEmail.getString("email");
						}
						// ===========================
						String updateTripStatus = "UPDATE trips SET status_trip = ? WHERE id = ?; ";
						PreparedStatement pstmtTripStatus = con.prepareStatement(updateTripStatus);
						pstmtTripStatus.setString(1, "Accepted");
						pstmtTripStatus.setInt(2, validDriverTrip);
						pstmtTripStatus.executeUpdate();

						lblStatusCompletion.setBackground(new Color(230, 230, 250));
						lblStatusCompletion.setForeground(new Color(0, 128, 0));
						lblStatusCompletion.setText("Accept");

						pstmtDriverValidTrip.close();
						pstmtCustomerValidEmail.close();
						con.close();
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
				}else{
					lblStatusCompletion.setBackground(new Color(230, 230, 250));
					lblStatusCompletion.setForeground(new Color(200, 10, 10));
					lblStatusCompletion.setText("Can't Be Accepted!");
				}

			}
		});
		// ===============================================================================
		// ===============================================================================
		btnFlush.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabreservationsystem",
							"ahmedshawki", "nopass"); // viewed password ??
					// ---------------------------------------------------------------------------
					lblStatusCompletion.setText("");

					if (tripStatusGlobal.equals("Customer Cancelled")){
						String updateDriverTripFlush = "UPDATE driver SET trips_id = ? WHERE email = ?; ";
						PreparedStatement pstmtDriverTripFlush = con.prepareStatement(updateDriverTripFlush);
						pstmtDriverTripFlush.setNull(1,Types.INTEGER);
						pstmtDriverTripFlush.setString(2,emailDriverSignedIn);
						pstmtDriverTripFlush.executeUpdate();

						lblCustomerName.setText("Nothing");
						lblCustomerPhone.setText("Nothing");
						lblCustomerTripLocation.setText("Nothing");
						lblCustomerTripDestination.setText("Nothing");
						lblPriceStatus.setText("Nothing");

						lblStatusCompletion.setBackground(new Color(230, 230, 250));
						lblStatusCompletion.setForeground(new Color(0, 128, 0));
						lblStatusCompletion.setText("Flushed");
						pstmtDriverTripFlush.close();
					}
					else if(tripStatusGlobal.isEmpty()) {
						lblStatusCompletion.setBackground(new Color(230, 230, 250));
						lblStatusCompletion.setForeground(new Color(200, 10, 10));
						lblStatusCompletion.setText("Already Flushed!");
					}else{
						lblStatusCompletion.setBackground(new Color(230, 230, 250));
						lblStatusCompletion.setForeground(new Color(200, 10, 10));
						lblStatusCompletion.setText("you can't flush, accept or not!");
					}

					// === close ===
					con.close();

				}catch (Exception ex){

				}
			}
		});

	}
}
