package java_app_cab_int;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

public class RideReservation extends JFrame {

	private static final long serialVersionUID = 4265092214505806845L;
	private JPanel contentPane;
	private JTextField txtCurrentLocation;
	private JTextField txtWhereTo;
	private JButton btnNewButton_1;
	private JLabel lblTheDestinition;
	private JButton btnTripStatus;
	public JLabel lblValid;
	public static int globalTripId ;

	// Cross Page Variables
	public int tripIdStatus;
	public static String customerEmailThisPage;
	private JTextField txtPrice;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					RideReservation frame = new RideReservation(customerEmailThisPage);
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
	public RideReservation(String customerEmailThisPage) {
		this.customerEmailThisPage = customerEmailThisPage;
		// -----------------------------------------------------------
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 601);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 0));
		setUndecorated(true);
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		// ===== To Make Focus on Unused Button =====
		JButton dummyButton = new JButton();
		dummyButton.setFocusable(true); // Allow this component to receive initial focus
		dummyButton.setBounds(0, 0, 1, 1); // Position it outside visible area
		contentPane.add(dummyButton);
		dummyButton.requestFocusInWindow(); // Set initial focus to the dummy button
		// =====
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.setFont(new Font("DejaVu Serif", Font.BOLD, 18));
		btnNewButton.setBounds(532, 549, 156, 40);
		btnNewButton.setContentAreaFilled(false); // to make it looking like a Label - step 1
		btnNewButton.setBorderPainted(false); // to make it looking like a Label - step 2
		btnNewButton.setOpaque(false);
		btnNewButton.addActionListener((event) -> System.exit(0)); // to do Exit
		contentPane.add(btnNewButton);

		// ==========================
		lblValid = new JLabel("");
		lblValid.setHorizontalAlignment(SwingConstants.CENTER);
		lblValid.setFont(new Font("DejaVu Serif", Font.PLAIN,22));
		lblValid.setBounds(230, 458, 300, 68);
		contentPane.add(lblValid);
		// ==========================
		JButton btnBack = new JButton("< Back");
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// ----- ------------------------------- -----
				// ----- Back to Customer Sign-in Window -----
				// ----- ------------------------------- -----
				CustomerSignIn theSigninCustomerBack = new CustomerSignIn();
				theSigninCustomerBack.setVisible(true);
				setVisible(false);
				// dispose();
				// -------------------------------------------
			}
		});
		btnBack.setOpaque(false);
		btnBack.setFont(new Font("DejaVu Serif", Font.BOLD, 18));
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setBounds(387, 549, 156, 40);
		contentPane.add(btnBack);

		txtCurrentLocation = new JTextField("Current Location", 22);
		// Add focus listener to clear default text
		txtCurrentLocation.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtCurrentLocation.getText().equals("Current Location")) {
					txtCurrentLocation.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtCurrentLocation.getText().isEmpty()) {
					txtCurrentLocation.setText("Current Location");
				}
			}
		});
		txtCurrentLocation.setBackground(UIManager.getColor("TabbedPane.selected"));
		txtCurrentLocation.setHorizontalAlignment(SwingConstants.CENTER);
		txtCurrentLocation.setFont(new Font("DejaVu Serif", Font.PLAIN, 22));
		txtCurrentLocation.setBounds(160, 157, 383, 63);
		contentPane.add(txtCurrentLocation);
		txtCurrentLocation.setColumns(10);

		txtWhereTo = new JTextField("Where to?", 22);
		// Add focus listener to clear default text
		txtWhereTo.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtWhereTo.getText().equals("Where to?")) {
					txtWhereTo.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtWhereTo.getText().isEmpty()) {
					txtWhereTo.setText("Where to?");
				}
			}
		});
		txtWhereTo.setBackground(UIManager.getColor("TabbedPane.selected"));
		txtWhereTo.setHorizontalAlignment(SwingConstants.CENTER);
		txtWhereTo.setFont(new Font("DejaVu Serif", Font.PLAIN, 22));
		txtWhereTo.setColumns(10);
		txtWhereTo.setBounds(160, 262, 383, 63);
		contentPane.add(txtWhereTo);

		btnNewButton_1 = new JButton("Next >");
		btnNewButton_1.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String currentLocation = txtCurrentLocation.getText();
				String whereTo = txtWhereTo.getText();
				String price = txtPrice.getText();

				try {

					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabreservationsystem",
							"ahmedshawki", "nopass"); // viewed password ??
					// ------------------------------------------------
					String queryCustomerId = "SELECT id FROM customer WHERE email = ? ;";
					PreparedStatement pstmtCustomerId = con.prepareStatement(queryCustomerId);
					pstmtCustomerId.setString(1, customerEmailThisPage);

					ResultSet rsCustomerId = pstmtCustomerId.executeQuery();

					int customerId = 0;
					while(rsCustomerId.next()){
						customerId =customerId + rsCustomerId.getInt("id");

					}
					// ------------------------------------------------
					String queryCustomerTripValid = "SELECT trips_id FROM customer WHERE email = ? ;";
					PreparedStatement pstmtCustomerTripValid = con.prepareStatement(queryCustomerTripValid);
					pstmtCustomerTripValid.setString(1, customerEmailThisPage);

					ResultSet rsCustomerTripValid = pstmtCustomerTripValid.executeQuery();

					Integer customerTripValid = 0;
					while(rsCustomerTripValid.next()){
						customerTripValid = customerTripValid + rsCustomerTripValid.getInt("trips_id");

					}
					if (customerTripValid == null || customerTripValid == 0) {
						// ------------------------------------------------

						String insertionTrip = "INSERT INTO trips (destination, where_to, price, status_trip) VALUES (?,?,?,?);";
						// --------- Create a PreparedStatement ---------
						PreparedStatement pstmtTripInfo = con.prepareStatement(insertionTrip);
						// -------- Set the email parameter value --------
						pstmtTripInfo.setString(1, currentLocation);
						pstmtTripInfo.setString(2, whereTo);
						pstmtTripInfo.setString(3, price);
						pstmtTripInfo.setString(4, "Waiting");
						// ------------------------------------------------
						// ------------------  Execute  -------------------
						pstmtTripInfo.executeUpdate();
						// ------------------------------------------------
						String queryTripId = "SELECT id FROM trips ORDER BY id DESC LIMIT 1;";
						PreparedStatement pstmtTripId = con.prepareStatement(queryTripId);
						// --- --- --- --- --- find the trip id --- --- --- --- --- ---
						ResultSet rsTripId = pstmtTripId.executeQuery();
						// ------------------------------------------------
						int tripId = 0;
						while (rsTripId.next()) {
							tripId = tripId + rsTripId.getInt("id");
						}
						globalTripId = tripId;
						// -------- link the trip with the user --------
						// --------- Query ----------
						String customerTrip = "UPDATE customer SET trips_id = ? WHERE email = ?;";
						// --------- Create a PreparedStatement ---------
						PreparedStatement pstmtCustomerTrip = con.prepareStatement(customerTrip);
						// -------------- Set the Parameters -------------
						pstmtCustomerTrip.setInt(1, globalTripId);
						pstmtCustomerTrip.setString(2, customerEmailThisPage);
						pstmtCustomerTrip.executeUpdate();


						// ----------------------------------------------------

						try {
							// ----- ------------------------ -----
							// ----- open Choosing car Window -----
							// ----- ------------------------ -----
							ChooseCar chosseCarRide = new ChooseCar(globalTripId, customerEmailThisPage);
							chosseCarRide.setVisible(true);
							setVisible(false);
							// dispose();
							// ------------------------------------

						} catch (Exception e) {
						}
					}else{
						lblValid.setBackground(new Color(230, 20, 0));
						lblValid.setText("You hava a Trip!");
					}

					con.close();
				} catch (Exception ex) {
					ex.printStackTrace();

				}

			}
		});
		btnNewButton_1.setBounds(521, 458, 143, 68);
		contentPane.add(btnNewButton_1);

		lblTheDestinition = new JLabel("Set The Destination");
		lblTheDestinition.setHorizontalAlignment(SwingConstants.CENTER);
		lblTheDestinition.setFont(new Font("DejaVu Serif", Font.BOLD, 34));
		lblTheDestinition.setBounds(133, 52, 410, 77);
		contentPane.add(lblTheDestinition);

		btnTripStatus = new JButton("Trip Status");
		btnTripStatus.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {

					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabreservationsystem",
							"ahmedshawki", "nopass"); // viewed password ??
					// --------- Customer Trip Id ------------
					String queryCustomerTripInfo = "SELECT trips_id FROM customer WHERE email = ?";
					// ----- Create a PreparedStatement -----
					PreparedStatement pstmtCustomerTripId = con.prepareStatement(queryCustomerTripInfo);
					// ----- Set the parameters value -----
					pstmtCustomerTripId.setString(1, customerEmailThisPage);
					// ----- Execute the Query -----
					ResultSet rsCustomerTripId = pstmtCustomerTripId.executeQuery();

					int tripIdStatusSub = 0; // trips_id from DRIVER that match the same trips_id from CUSTOMER

					// -----------------------------------------
					while (rsCustomerTripId.next()) {
						tripIdStatusSub = tripIdStatusSub + rsCustomerTripId.getInt("trips_id");
					}
					tripIdStatus = tripIdStatusSub;
					// --------- Trip Status --------
					String queryTripStatus= "SELECT status_trip FROM trips WHERE id = ? ;";
					PreparedStatement pstmtTripStatus = con.prepareStatement(queryTripStatus);
					pstmtTripStatus.setInt(1, tripIdStatus);
					ResultSet rsTripStatus = pstmtTripStatus.executeQuery();

					String acceptStatus = "" ;
					while (rsTripStatus.next()) {
						acceptStatus = acceptStatus + rsTripStatus.getString("status_trip");
					}
					// ================= Query Driver Related to this Trip ===================
					String queryDriverId  = "SELECT driver_id FROM trips WHERE id = ? ;";
					PreparedStatement pstmtDriverId = con.prepareStatement(queryDriverId);
					pstmtDriverId.setInt(1, tripIdStatus);
					ResultSet rsDriverId = pstmtDriverId.executeQuery();

					int driverId = 0 ;
					while (rsDriverId.next()) {
						driverId = driverId + rsDriverId.getInt("driver_id");
					}
					// ======================================================================

					try {
						// ----- ------------------- -----
						// ----- Go to the Trip Page -----
						// ----- ------------------- -----
						String tripStatusAcception = "";
						String diverName = "" ;
						if (acceptStatus.equals("Waiting")) {
							tripStatusAcception = "No Answer Yet!";
						} else if (acceptStatus.equals("Refused")) {
							tripStatusAcception = "Driver Refused";
						} else if (acceptStatus.equals("Accepted")) {
							tripStatusAcception = "Driver Accepted";
						} else {
							tripStatusAcception = "No Trip";
						}
//

						TripPage theTripPage = new TripPage(tripIdStatus, customerEmailThisPage,tripStatusAcception,driverId);
						theTripPage.setVisible(true);
						setVisible(false);
						// dispose();
						// -
					} catch (Exception e) {
						throw new RuntimeException(e);
					}
					con.close();


				}catch (Exception e){

				}
              // ------------------------------
			}
		});
		btnTripStatus.setFont(new Font("DejaVu Serif", Font.PLAIN, 18));
		btnTripStatus.setBounds(40, 494, 143, 68);
		contentPane.add(btnTripStatus);
		
		txtPrice = new JTextField("Price L.E.", 10);
		// Add focus listener to clear default text
		txtPrice.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (txtPrice.getText().equals("Price L.E.")) {
					txtPrice.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (txtPrice.getText().isEmpty()) {
					txtPrice.setText("Price L.E.");
				}
			}
		});
		txtPrice.setHorizontalAlignment(SwingConstants.CENTER);
		txtPrice.setFont(new Font("DejaVu Serif", Font.PLAIN, 22));
		txtPrice.setBackground(UIManager.getColor("SplitPane.dividerFocusColor"));
		txtPrice.setBounds(160, 367, 383, 63);
		contentPane.add(txtPrice);
	}
}
