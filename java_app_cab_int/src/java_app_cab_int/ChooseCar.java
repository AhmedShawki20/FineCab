package java_app_cab_int;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JFormattedTextField;

public class ChooseCar extends JFrame {

	private static final long serialVersionUID = 544513485903444198L;
	private JPanel contentPane;
	private JComboBox<String> comboBox_car;
	private JComboBox<String> comboBox_model;
	private JComboBox<String> comboBox_version;
	private JLabel lblDriverName;
	private JLabel lblCarMaxSpeed;
	private JComboBox<String> comboBox_phone;
//	private static int globalTripId;
	String carTypeEnter = "" ;
	String carModelEnter = "" ;
	String carVersionEnter = "" ;

	int globalCarId;
	// Cross Page Variables
	public static int tripIdCar;
	public static String customerEmailCarPage;

	public static String driverStatusGlobal;
	public static String globalChosenDriverEmail;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
//			Private String customerEmailCarPage ;
			@Override
			public void run() {
				try {
					ChooseCar frame = new ChooseCar(tripIdCar,customerEmailCarPage);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ChooseCar(int tripIdCar, String customerEmailCarPage ) throws ClassNotFoundException {
		this.tripIdCar = tripIdCar ;
		this.customerEmailCarPage = customerEmailCarPage ;
		//v--------------------------------------------------
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 800);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 204, 0));
		setUndecorated(true);
		setLocationRelativeTo(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//
		comboBox_car = new JComboBox<>();
		comboBox_car.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		comboBox_car.setBounds(242, 113, 265, 62);
		comboBox_car.addItem("Choose Type");

		comboBox_model = new JComboBox<>();
		comboBox_model.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		comboBox_model.setBounds(242, 197, 265, 62);
		comboBox_model.addItem("Choose Model");

		comboBox_version = new JComboBox<>();
		comboBox_version.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		comboBox_version.setBounds(242, 276, 265, 62);
		comboBox_version.addItem("Choose Version");

		lblDriverName = new JLabel("",JLabel.CENTER);
		lblDriverName.setHorizontalAlignment(SwingConstants.CENTER);
		lblDriverName.setForeground(new Color(230, 230, 250));
		lblDriverName.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblDriverName.setBounds(234, 538, 238, 25 );
		contentPane.add(lblDriverName);

		lblCarMaxSpeed = new JLabel("",JLabel.CENTER);
		lblCarMaxSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarMaxSpeed.setForeground(new Color(245, 222, 179));
		lblCarMaxSpeed.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblCarMaxSpeed.setBounds(234, 500, 238, 25);
		contentPane.add(lblCarMaxSpeed);

		JLabel lblCAR = new JLabel("",JLabel.CENTER);
		lblCAR.setBounds(518, 115, 58, 60);
		contentPane.add(lblCAR);
		
		comboBox_phone = new JComboBox<>();
		comboBox_phone.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		comboBox_phone.setBackground(new Color(255, 215, 0));
		comboBox_phone.setBounds(234, 576, 238, 40);
		// =========  ==========  =========
		JLabel lblStatusCompletion = new JLabel("");
		lblStatusCompletion.setFont(new Font("DejaVu Serif", Font.PLAIN, 25));
		lblStatusCompletion.setForeground(new Color(255, 215, 0));
		lblStatusCompletion.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatusCompletion.setBounds(140, 685, 300, 32);
		contentPane.add(lblStatusCompletion);
		// =========  ==========  =========
		JButton btnApprove = new JButton("Approve");
		btnApprove.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		btnApprove.setBounds(415, 685, 125, 50);
		contentPane.add(btnApprove);
		// ================================================
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setOpaque(false);
		btnCancel.setFont(new Font("DejaVu Serif", Font.BOLD, 20));
		btnCancel.setContentAreaFilled(false);
		btnCancel.setBorderPainted(false);
		btnCancel.setBounds(100, 750, 173, 32);
		// ================================================
		JLabel lblCartype = new JLabel("",JLabel.CENTER);
		lblCartype.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblCartype.setHorizontalAlignment(SwingConstants.CENTER);
		lblCartype.setBounds(234, 426, 238, 25);
		contentPane.add(lblCartype);

		JLabel lblCarColor = new JLabel("",JLabel.CENTER);
		lblCarColor.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblCarColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblCarColor.setBounds(234, 467, 238, 25);
		contentPane.add(lblCarColor);

		JLabel lblStstus = new JLabel("",JLabel.CENTER);
		lblStstus.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblStstus.setHorizontalAlignment(SwingConstants.CENTER);
		lblStstus.setBounds(234, 630, 238, 25);
		contentPane.add(lblStstus);
		// ================================================
		//
		// ----------------------------------------------------------------------
		// ---------------- Load car types into comboBox_car --------------------
		// ----------------------------------------------------------------------
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabreservationsystem",
					"ahmedshawki", "nopass");
			// ----- Query -----
			String queryCarType = "SELECT DISTINCT type FROM car;";
			// ----- Create a PreparedStatement -----
			PreparedStatement pstmtCarType = con.prepareStatement(queryCarType);
			// -.--.---.----.-----.--------------
			ResultSet rsCarType = pstmtCarType.executeQuery();;
			while (rsCarType.next()) {
				String carTypeCombo = rsCarType.getString("type");
				comboBox_car.addItem(carTypeCombo);
			}
			
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// --------------------------------------------
		// ----------- Choose the Car Type ------------
		// --------------------------------------------
		// Action listener for comboBox_car
		comboBox_car.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					// $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
					lblCartype.setText("");
					lblCarColor.setText("");
					lblDriverName.setText("");
					lblCarMaxSpeed.setText("");
					lblStstus.setText("");
					lblStatusCompletion.setText("");
					comboBox_phone.removeAllItems();
					// Clear previous model selections
					comboBox_model.removeAllItems();
					comboBox_version.removeAllItems();

					carTypeEnter = comboBox_car.getSelectedItem().toString();
					// Load car models based on selected car type
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabreservationsystem",
							"ahmedshawki", "nopass");
					// ----- Query -----
					String queryCarModel = "SELECT model FROM car WHERE type = ? ;";
					// ----- Create a PreparedStatement -----
					PreparedStatement pstmtCarModel = con.prepareStatement(queryCarModel);
					// -------- Set the parameter -------
					pstmtCarModel.setString(1,carTypeEnter);
					// -----.----.--- ---.----.-----
					ResultSet rsCarModel = pstmtCarModel.executeQuery();

					while (rsCarModel.next()) {
						String carModelCombo = rsCarModel.getString("model");
						comboBox_model.addItem(carModelCombo);
					}

					con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		// ---------------------------------------------
		// ----------- Choose the Car Model ------------
		// ---------------------------------------------
		// Action listener for comboBox_model
		comboBox_model.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					// $$$$$$$$$$$$$$$$$$$$$$$$$$$
					lblStatusCompletion.setText("");
					comboBox_version.removeAllItems();


					carModelEnter = comboBox_model.getSelectedItem().toString();

					// Load car versions based on selected car type and model
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabreservationsystem",
							"ahmedshawki", "nopass");
					// ----- Query -----
					String queryCarVersion = "SELECT version FROM car WHERE type = ? AND model = ? ;";
					// ----- Create a PreparedStatement -----
					PreparedStatement pstmtCarVersion = con.prepareStatement(queryCarVersion);
					// -------- Set the parameter -------
					pstmtCarVersion.setString(1,carTypeEnter);
					pstmtCarVersion.setString(2, carModelEnter);
					// -----.----.--- ---.----.-----
					ResultSet rsCarVersion = pstmtCarVersion.executeQuery();


					while (rsCarVersion.next()) {
						String carVersionCombo = rsCarVersion.getString("version");
						comboBox_version.addItem(carVersionCombo);
					}
					//
					carVersionEnter = comboBox_version.getSelectedItem().toString();
					// $$$$$$$$$$$$$$$$$$$$$$$$$$$
					lblStatusCompletion.setText("");

					// ----- Query -----
					String queryChosenCarId = "SELECT id FROM car WHERE type = ? AND model = ? AND version = ? ;";
					// ----- Create a PreparedStatement -----
					PreparedStatement pstmtChosenCarId = con.prepareStatement(queryChosenCarId);
					// -------- Set the parameter -------
					pstmtChosenCarId.setString(1, carTypeEnter);
					pstmtChosenCarId.setString(2, carModelEnter);
					pstmtChosenCarId.setString(3, carVersionEnter);
					//
					ResultSet rsChosenCarId = pstmtChosenCarId.executeQuery(); //

					int ChosenCarId = 0;
					while (rsChosenCarId.next()) {
						ChosenCarId = ChosenCarId + rsChosenCarId.getInt("id");
					}
					globalCarId = ChosenCarId;
					// ---------------------------------------------
					// --- to get the Max Speed --- ---
					// ---------------------------------------------
					String queryMaxSpeed = "SELECT speed FROM car WHERE id = ?;";
					PreparedStatement pstmtCarMaxSpeed = con.prepareStatement(queryMaxSpeed);
					pstmtCarMaxSpeed.setInt(1, globalCarId);
					ResultSet rsCarMaxSpeed = pstmtCarMaxSpeed.executeQuery();
					String carMaxSpeed = "";
					while (rsCarMaxSpeed.next()) {
						carMaxSpeed = rsCarMaxSpeed.getString("speed");
					}
					lblCarMaxSpeed.setForeground(Color.BLACK);
					lblCarMaxSpeed.setText(carMaxSpeed);
					// -------------------------------------------------
					// --- --- --- --- to get the driver --- --- --- ---
					// -------------------------------------------------
					String queryChosenDriverName = "SELECT CONCAT(first_name,\" \",last_name) AS full_name FROM driver WHERE car_id = ?;" ;
					PreparedStatement pstmtChosenDriverName = con.prepareStatement(queryChosenDriverName);
					pstmtChosenDriverName.setInt(1, globalCarId);
					ResultSet rsChosenDriverName = pstmtChosenDriverName.executeQuery();
					String ChosenDriverName = "";
					while (rsChosenDriverName.next()) {
						ChosenDriverName = rsChosenDriverName.getString("full_name");
					}
					lblDriverName.setForeground(Color.BLACK);
					lblDriverName.setText(ChosenDriverName);
					// --------------------------------------------------------------
					// --------------------- get The Driver Email -------------------
					// --------------------------------------------------------------
					String queryChosenDriverEmail = "SELECT email FROM driver WHERE car_id = ?;" ;
					PreparedStatement pstmtChosenDriverEmail = con.prepareStatement(queryChosenDriverEmail);
					pstmtChosenDriverEmail.setInt(1, globalCarId);
					ResultSet rsChosenDriverEmail = pstmtChosenDriverEmail.executeQuery();
					String choseDriverEmail = "";
					while (rsChosenDriverEmail.next()) {
						choseDriverEmail = choseDriverEmail + rsChosenDriverEmail.getString("email");
					}
					globalChosenDriverEmail = choseDriverEmail;
					// -----------------------------------------------------------
					// ------------------- to get the phone ----------------------
					// -----------------------------------------------------------
					String queryChosenDriverPhone = "SELECT phone FROM driver_phone WHERE phone_id = (SELECT id FROM driver WHERE email = ?);" ;
					PreparedStatement pstmtChosenDriverPhone = con.prepareStatement(queryChosenDriverPhone);
					pstmtChosenDriverPhone.setString(1,choseDriverEmail );
					ResultSet rsChosenDriverPhone = pstmtChosenDriverPhone.executeQuery();
					// ---- Flush the ComboBox -------
					comboBox_phone.removeAllItems();
					// ---------------------------------------------------------------
					// ------------------ to get the Car Type ------------------------
					String queryCarType = "SELECT body_type FROM car WHERE id = ?;" ;
					PreparedStatement pstmtCarType = con.prepareStatement(queryCarType);
					pstmtCarType.setInt(1,globalCarId );
					ResultSet rsCarType = pstmtCarType.executeQuery();
					String carType = "";
					while (rsCarType.next()){
						carType = carType + rsCarType.getString("body_type");
					}
					lblCartype.setForeground(Color.BLACK);
					lblCartype.setText(carType);
					// ----------------------------------------------------------
					// ------------------ to get the Car Color ------------------
					String queryCarColor = "SELECT color FROM car WHERE id = ?;" ;
					PreparedStatement pstmtCarColor = con.prepareStatement(queryCarColor);
					pstmtCarColor.setInt(1,globalCarId );
					ResultSet rsCarColor = pstmtCarColor.executeQuery();
					String carColor = "";
					while (rsCarColor.next()){
						carColor = carColor + rsCarColor.getString("color");
					}
					lblCarColor.setForeground(Color.BLACK);
					lblCarColor.setText(carColor);
					// ----------------------------------------------------------
					String ChosenDriverPhone = "";
					while (rsChosenDriverPhone.next()) {
						ChosenDriverPhone = rsChosenDriverPhone.getString("phone");
						DefaultComboBoxModel<String> model4 = (DefaultComboBoxModel<String>) comboBox_phone.getModel();
						if (model4.getIndexOf(ChosenDriverPhone) == -1) {
							comboBox_phone.addItem(ChosenDriverPhone);
						}
//
					}
					// --- --- --- --- ---
					// ================= Query Status Related to this Trip ===================
					String queryDriverStatus  = "SELECT status_trip FROM trips WHERE id = (SELECT trips_id FROM driver WHERE email =  ? ) ;";
					PreparedStatement pstmtDriverStatus= con.prepareStatement(queryDriverStatus);
					pstmtDriverStatus.setString(1,choseDriverEmail );
					ResultSet rsDriverStatus = pstmtDriverStatus.executeQuery();

					String driverStatus = "" ;
					while (rsDriverStatus.next()) {
						driverStatus = driverStatus + rsDriverStatus.getString("status_trip");
					}

					driverStatusGlobal = driverStatus;

					if (driverStatusGlobal.equals("Waiting") || driverStatusGlobal.equals("Accepted")){
						lblStstus.setForeground(Color.BLACK);
						lblStstus.setText("Busy");
					}
					else if (driverStatusGlobal.equals("Refused")) {
						lblStstus.setForeground(Color.BLACK);
						lblStstus.setText("Availabe");
					}else {
						lblStstus.setForeground(Color.BLACK);
						lblStstus.setText("Availabe");
					}

					// ------------------------------------
				con.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

		// ================================================
		// ================================================


		btnApprove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {

					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabreservationsystem",
							"ahmedshawki", "nopass"); // viewed password ??
					// ================================================
					int finalTripId = tripIdCar;
					// ===========================================================
					// ===========================================================
					String queryCustomerTripValid = "SELECT customer_id FROM trips WHERE id = ? ;";
					PreparedStatement pstmtCustomerTripValid = con.prepareStatement(queryCustomerTripValid);
					pstmtCustomerTripValid.setInt(1, tripIdCar);

					ResultSet rsCustomerTripValid = pstmtCustomerTripValid.executeQuery();

					Integer customerTripValid = 0;
					while(rsCustomerTripValid.next()){
						customerTripValid = customerTripValid + rsCustomerTripValid.getInt("customer_id");
					}


					if (!(customerTripValid == 0 || customerTripValid == null)) {

						lblStatusCompletion.setBackground(new Color(230, 230, 250));
						lblStatusCompletion.setForeground(new Color(0, 128, 0));
						lblStatusCompletion.setText("Waiting a Respond");

					}else{
						if (driverStatusGlobal.equals("Waiting") || driverStatusGlobal.equals("Accepted")) {
							lblStatusCompletion.setBackground(new Color(230, 230, 250));
							lblStatusCompletion.setForeground(new Color(190, 22, 22));
							lblStatusCompletion.setText("Driver is Busy");

						} else if (driverStatusGlobal.equals("Refused")) {

							String queryCustomerId = "SELECT id FROM customer WHERE email = ?";
							PreparedStatement pstmtCustomerId = con.prepareStatement(queryCustomerId);
							pstmtCustomerId.setString(1, customerEmailCarPage);
							ResultSet rsCustomerId = pstmtCustomerId.executeQuery();

							int customerId = 0;
							while (rsCustomerId.next()) {
								customerId = customerId + rsCustomerId.getInt("id");
							}

							// =====================================================================
							String queryChosenDriverId = "SELECT id FROM driver WHERE car_id = ?";
							PreparedStatement pstmtChosenDriverId = con.prepareStatement(queryChosenDriverId);
							pstmtChosenDriverId.setInt(1, globalCarId);
							ResultSet rsChosenDriverId = pstmtChosenDriverId.executeQuery();

							int chosenDriverId = 0;
							while (rsChosenDriverId.next()) {
								chosenDriverId = chosenDriverId + rsChosenDriverId.getInt("id");
							}
							// ----------------------------------------------------------------------
							// ------------------ update Trips to add the Customer --------------------
							// ----------------------------------------------------------------------
							String updateTripsCustomer = "UPDATE trips SET customer_id = ? WHERE id = ?";
							PreparedStatement pstmtTripsCustomer = con.prepareStatement(updateTripsCustomer);
							pstmtTripsCustomer.setInt(1, customerId);
							pstmtTripsCustomer.setInt(2, finalTripId);
							pstmtTripsCustomer.executeUpdate();
							// ---------------------------------------------------------------
							// ------------------ Driver update Trips --------------------
							// ---------------------------------------------------------------
							String updateChosenDriverTripId = "UPDATE driver SET trips_id = ? WHERE id = ?";
							PreparedStatement pstmtChosenDriverTripId = con.prepareStatement(updateChosenDriverTripId);
							pstmtChosenDriverTripId.setInt(1, finalTripId);
							pstmtChosenDriverTripId.setInt(2, chosenDriverId);
							pstmtChosenDriverTripId.executeUpdate();
							// ----------------------------------------------------------------------
							// ------------------ update Trips to add the Driver --------------------
							// ----------------------------------------------------------------------
							String updateTripsDriver = "UPDATE trips SET driver_id = ? WHERE id = ?";
							PreparedStatement pstmtTripsDriver = con.prepareStatement(updateTripsDriver);
							pstmtTripsDriver.setInt(1, chosenDriverId);
							pstmtTripsDriver.setInt(2, finalTripId);
							pstmtTripsDriver.executeUpdate();
							lblStatusCompletion.setBackground(new Color(230, 230, 250));
							lblStatusCompletion.setForeground(new Color(0, 128, 0));
							lblStatusCompletion.setText("Done");
							// === === === === === === ===
						} else {

							String queryCustomerId = "SELECT id FROM customer WHERE email = ?";
							PreparedStatement pstmtCustomerId = con.prepareStatement(queryCustomerId);
							pstmtCustomerId.setString(1, customerEmailCarPage);
							ResultSet rsCustomerId = pstmtCustomerId.executeQuery();

							int customerId = 0;
							while (rsCustomerId.next()) {
								customerId = customerId + rsCustomerId.getInt("id");
							}
							// =====================================================================
							String queryChosenDriverId = "SELECT id FROM driver WHERE car_id = ?";
							PreparedStatement pstmtChosenDriverId = con.prepareStatement(queryChosenDriverId);
							pstmtChosenDriverId.setInt(1, globalCarId);
							ResultSet rsChosenDriverId = pstmtChosenDriverId.executeQuery();

							int chosenDriverId = 0;
							while (rsChosenDriverId.next()) {
								chosenDriverId = chosenDriverId + rsChosenDriverId.getInt("id");
							}
							// ----------------------------------------------------------------------
							// ------------------ update Trips to add the Customer --------------------
							// ----------------------------------------------------------------------
							String updateTripsCustomer = "UPDATE trips SET customer_id = ? WHERE id = ?";
							PreparedStatement pstmtTripsCustomer = con.prepareStatement(updateTripsCustomer);
							pstmtTripsCustomer.setInt(1, customerId);
							pstmtTripsCustomer.setInt(2, finalTripId);
							pstmtTripsCustomer.executeUpdate();
							// ---------------------------------------------------------------
							// ------------------ Driver update Trips --------------------
							// ---------------------------------------------------------------
							String updateChosenDriverTripId = "UPDATE driver SET trips_id = ? WHERE id = ?";
							PreparedStatement pstmtChosenDriverTripId = con.prepareStatement(updateChosenDriverTripId);
							pstmtChosenDriverTripId.setInt(1, finalTripId);
							pstmtChosenDriverTripId.setInt(2, chosenDriverId);
							pstmtChosenDriverTripId.executeUpdate();
							// ----------------------------------------------------------------------
							// ------------------ update Trips to add the Driver --------------------
							// ----------------------------------------------------------------------
							String updateTripsDriver = "UPDATE trips SET driver_id = ? WHERE id = ?";
							PreparedStatement pstmtTripsDriver = con.prepareStatement(updateTripsDriver);
							pstmtTripsDriver.setInt(1, chosenDriverId);
							pstmtTripsDriver.setInt(2, finalTripId);
							pstmtTripsDriver.executeUpdate();
							lblStatusCompletion.setBackground(new Color(230, 230, 250));
							lblStatusCompletion.setForeground(new Color(0, 128, 0));
							lblStatusCompletion.setText("Done");

						}
					}

					// ======================================================================
					con.close();
				} catch (Exception e) {

				}
			}
		});
		
		JFormattedTextField formattedTextField = new JFormattedTextField();
		formattedTextField.setBounds(0, 0, 5, 19);
		contentPane.add(formattedTextField);

		// ----- ----- ----- ----- ----- -----
		// ******************************************************************
		contentPane.add(comboBox_car);
		contentPane.add(comboBox_model);
		contentPane.add(comboBox_version);
		contentPane.add(comboBox_phone);
		//
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.setBounds(415, 750, 173, 32);
		btnNewButton.setOpaque(false);
		btnNewButton.setFont(new Font("DejaVu Serif", Font.BOLD, 20));
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setBorderPainted(false);
		btnNewButton.addActionListener((event) -> System.exit(0)); // to do Exit
		contentPane.add(btnNewButton);

		JButton btnBack = new JButton("Back");
		btnBack.setOpaque(false);
		btnBack.setFont(new Font("DejaVu Serif", Font.BOLD, 20));
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.setBounds(260, 750, 173, 32);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// ----- ----------------------------- -----
				// ----- Back to Driver Sign-in Window -----
				// ----- ----------------------------- -----
				RideReservation theRideReservationBack = new RideReservation(customerEmailCarPage);
				theRideReservationBack.lblValid.setForeground(new Color(200,45,0));
				theRideReservationBack.setVisible(true);
				setVisible(false);
				// dispose();
				// -----------------------------------------
			}
		});
		contentPane.add(btnBack);

		JLabel lblCatType = new JLabel("Cat Type");
		lblCatType.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblCatType.setBounds(44, 126, 164, 38);
		contentPane.add(lblCatType);

		JLabel lblCatModel = new JLabel("Cat Model");
		lblCatModel.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblCatModel.setBounds(44, 210, 173, 38);
		contentPane.add(lblCatModel);

		JLabel lblReleasedVersion = new JLabel("Released Version");
		lblReleasedVersion.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblReleasedVersion.setBounds(44, 289, 180, 38);
		contentPane.add(lblReleasedVersion);

		JLabel lblInformation = new JLabel("----------------- Information -----------------");
		lblInformation.setHorizontalAlignment(SwingConstants.CENTER);
		lblInformation.setFont(new Font("DejaVu Serif", Font.BOLD, 23));
		lblInformation.setBounds(22, 368, 549, 32);
		contentPane.add(lblInformation);

		JLabel lblCarMaxSpeed = new JLabel("Car Max. Speed");
		lblCarMaxSpeed.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblCarMaxSpeed.setBounds(43, 504, 165, 25);
		contentPane.add(lblCarMaxSpeed);

		JLabel lblCarDriver = new JLabel("Car Driver");
		lblCarDriver.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblCarDriver.setBounds(44, 541, 131, 32);
		contentPane.add(lblCarDriver);
		// --------------------------------------------------------------------------------------------------
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				// ----- ------------------------------------------------- -----
				// ----- Back to Driver Sign-in Window and Cancel the Trip -----
				// ----- ------------------------------------------------- -----

				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabreservationsystem",
							"ahmedshawki", "nopass"); // viewed password ??
					// ================================================

					String updateCustomerTrip = "UPDATE customer SET trips_id = ? WHERE email = ? ;" ;
					// ----- Create a PreparedStatement -----
					PreparedStatement pstmtCustomerTrip = con.prepareStatement(updateCustomerTrip);
					// ----- Set the parameters value -----
					pstmtCustomerTrip.setNull(1, Types.INTEGER); // Set trip id to null
					pstmtCustomerTrip.setString(2, customerEmailCarPage);
					// ----- Execute the Query -----
					pstmtCustomerTrip.executeUpdate();
					// -------------------------------------------------------------
					String queryTripStatus = "SELECT status_trip FROM trips WHERE id = ?;";
					PreparedStatement pstmtTripStatus = con.prepareStatement(queryTripStatus);
					pstmtTripStatus.setInt(1,tripIdCar);
					ResultSet rsTripStatus = pstmtTripStatus.executeQuery();

					String tripStatusCheck = "";
					while(rsTripStatus.next()){
						tripStatusCheck = tripStatusCheck + rsTripStatus.getString("status_trip");
					}
					// ===============================================================================
					// ==== to check if the driver cancelled the trip before the customer or not =====
					// ===============================================================================

					if(tripStatusCheck.equals("Waiting") || tripStatusCheck.equals("Accepted")){
						// ------------------------------------------------------
						String updateStatusTrip = "UPDATE trips SET status_trip = ? WHERE id = ? ;";
						// ----- Create a PreparedStatement -----
						PreparedStatement pstmtStatusTrip = con.prepareStatement(updateStatusTrip);
						// ----- Set the parameters value -----
						pstmtStatusTrip.setString(1, "Customer Cancelled"); // Set trip id to null
						pstmtStatusTrip.setInt(2, tripIdCar);
						// ----- Execute the Query -----
						pstmtStatusTrip.executeUpdate();
						// ------------------------------------------------------
						String queryCustomerId = "SELECT id FROM customer WHERE email = ?";
						PreparedStatement pstmtCustomerId = con.prepareStatement(queryCustomerId);
						pstmtCustomerId.setString(1, customerEmailCarPage);
						ResultSet rsCustomerId = pstmtCustomerId.executeQuery();

						int customerId = 0;
						while (rsCustomerId.next()) {
							customerId = customerId + rsCustomerId.getInt("id");
						}
						// --------------------------------------------------
						String updateCustomerToTrips = "UPDATE trips SET customer_id = ? WHERE id = ? ;";
						// ----- ----- Create a PreparedStatement ----- -----
						PreparedStatement pstmtCustomerToTrips = con.prepareStatement(updateCustomerToTrips);
						// ----- Set the parameters value -----
						pstmtCustomerToTrips.setInt(1, customerId); // Set trip id to null
						pstmtCustomerToTrips.setInt(2, tripIdCar);
						// ----- Execute the Query -----
						pstmtCustomerToTrips.executeUpdate();
						// --------------------------------------------------
						RideReservation theRideReservationBack = new RideReservation(customerEmailCarPage);
						theRideReservationBack.lblValid.setForeground(new Color(200, 45, 0));
						theRideReservationBack.lblValid.setText("Trip Cancelled!");
						theRideReservationBack.setVisible(true);
						setVisible(false);
					}else{
						RideReservation theRideReservationBack = new RideReservation(customerEmailCarPage);
						theRideReservationBack.lblValid.setForeground(new Color(200, 45, 0));
						theRideReservationBack.lblValid.setText("Driver Refused the Trip!");
						theRideReservationBack.setVisible(true);
						setVisible(false);
					}
					con.close();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
				// dispose();
				// -----------------------------------------
			}
		});
		contentPane.add(btnCancel);
		// --------------------------------------------------------------------------------------------------
		JLabel lblNewLabel = new JLabel("Choose a Car");
		lblNewLabel.setFont(new Font("DejaVu Serif", Font.BOLD, 34));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(22, 12, 542, 77);
		contentPane.add(lblNewLabel);

		JLabel lblDriverPhone = new JLabel("Driver Phone");
		lblDriverPhone.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblDriverPhone.setBounds(42, 585, 153, 25);
		contentPane.add(lblDriverPhone);
		
		JLabel lblCarType_1 = new JLabel("Car Type");
		lblCarType_1.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblCarType_1.setBounds(44, 426, 165, 25);
		contentPane.add(lblCarType_1);
		
		JLabel lblCarColor_1 = new JLabel("Car Color");
		lblCarColor_1.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblCarColor_1.setBounds(43, 463, 165, 25);
		contentPane.add(lblCarColor_1);
		
		JLabel lblDriverStatus = new JLabel("Driver Status");
		lblDriverStatus.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblDriverStatus.setBounds(43, 631, 165, 25);
		contentPane.add(lblDriverStatus);
		


	}
}
