package java_app_cab_int;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TripPage extends JFrame {

	private static final long serialVersionUID = -5817051389672630482L;
	private JPanel contentPane;
	public static Integer tripIdStatus;
	public static String customerEmailThisPage;
	public static String acceptStatus;
	public static int driverId;
	public static String driverNameGlobal;
	public static String tripStatusGlobal;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					TripPage frame = new TripPage(tripIdStatus,customerEmailThisPage,acceptStatus,driverId);
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
	public TripPage(Integer tripIdStatus, String customerEmailThisPage,String acceptStatus,int driverId) {
		//
		this.tripIdStatus = tripIdStatus;
		this.customerEmailThisPage = customerEmailThisPage;
		this.acceptStatus = acceptStatus;
		this.driverId = driverId;
		//
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		setUndecorated(true);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDriverName = new JLabel("Driver Name :");
		lblDriverName.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblDriverName.setBounds(60, 128, 153, 25);
		contentPane.add(lblDriverName);

		JLabel lblDriverNameInfo = new JLabel("",JLabel.CENTER);
		lblDriverNameInfo.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblDriverNameInfo.setBounds(284, 128, 250, 25);
		contentPane.add(lblDriverNameInfo);


		JLabel lblStatus = new JLabel("Status :");
		lblStatus.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblStatus.setBounds(60, 189, 117, 40);
		contentPane.add(lblStatus);

		JLabel lblStatusInfo = new JLabel("",JLabel.CENTER);
		lblStatusInfo.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblStatusInfo.setBounds(284, 189, 250, 40);
		contentPane.add(lblStatusInfo);
		// =========== # =========== # ============

		JLabel lblDriverPhone = new JLabel("Driver Phone :");
		lblDriverPhone.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		lblDriverPhone.setBounds(60, 250, 200, 40);
		contentPane.add(lblDriverPhone);

		JComboBox<String> comboBoxPhone = new JComboBox<>();
		comboBoxPhone.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		comboBoxPhone.setBackground(new Color(255, 215, 0));
		comboBoxPhone.setBounds(284, 250, 250, 40);
		contentPane.add(comboBoxPhone);

		// =========== # ============ # ============

		JLabel lblDone = new JLabel("",JLabel.CENTER);
		lblDone.setFont(new Font("DejaVu Serif", Font.PLAIN, 23));
		lblDone.setBounds(50, 400, 550, 40);
		contentPane.add(lblDone);
		//
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabreservationsystem",
					"ahmedshawki", "nopass"); // viewed password ??
			// -------------------------
			// -------------------------
			String queryTripStatus = "SELECT status_trip FROM trips WHERE id = (SELECT trips_id FROM customer WHERE email = ? );";
			PreparedStatement pstmtTripStatus = con.prepareStatement(queryTripStatus) ;
			pstmtTripStatus.setString(1,customerEmailThisPage);
			ResultSet rsTripStatus = pstmtTripStatus.executeQuery();

			String tripStatus = "";
			while (rsTripStatus.next()){
				tripStatus = tripStatus + rsTripStatus.getString("status_trip");
			}
			tripStatusGlobal = tripStatus;

			// ===== Driver Name =====
			String queryDriverName = "SELECT CONCAT(first_name,\" \",last_name) AS full_name FROM driver WHERE trips_id = ? ;" ;
			// ----- Create a PreparedStatement -----
			PreparedStatement pstmtDriverName = con.prepareStatement(queryDriverName);
			// ----- Set the parameters value -----
			pstmtDriverName.setInt(1, tripIdStatus);
			// ----- Execute the Query -----
			ResultSet rsDriverName = pstmtDriverName.executeQuery();

			String driverName = "" ;
			while (rsDriverName.next()) {
				driverName = driverName + rsDriverName.getString("full_name");
			}
			// ======================== Driver Phone ========================
			String queryDriverPhone = "SELECT phone FROM driver_phone WHERE phone_id = (SELECT id FROM driver WHERE trips_id = ?);" ;
			PreparedStatement pstmtDriverPhone = con.prepareStatement(queryDriverPhone);
			pstmtDriverPhone.setInt(1,tripIdStatus );
			ResultSet rsDriverPhone = pstmtDriverPhone.executeQuery();
			// ---- Flush the ComboBox -------
			comboBoxPhone.removeAllItems();
			// -------------------------------
			String driverPhone = "";
			// ======================================
			if (driverName.isEmpty()) {
				lblDriverNameInfo.setBackground(new Color(230, 230, 250));
				lblDriverNameInfo.setForeground(new Color(0, 0, 0));
				lblDriverNameInfo.setText("No Driver!");
				lblDriverNameInfo.setBackground(new Color(230, 230, 250));
				lblDriverNameInfo.setForeground(new Color(0, 0, 0));
				lblStatusInfo.setText(acceptStatus);
				DefaultComboBoxModel<String> model4 = (DefaultComboBoxModel<String>) comboBoxPhone.getModel();
				if (model4.getIndexOf("Empty") == -1) {
					comboBoxPhone.addItem("Empty");
				}
			}else{
				lblDriverNameInfo.setBackground(new Color(230, 230, 250));
				lblDriverNameInfo.setForeground(new Color(0, 0, 0));
				lblDriverNameInfo.setText(driverName);
				lblDriverNameInfo.setBackground(new Color(230, 230, 250));
				lblDriverNameInfo.setForeground(new Color(0, 0, 0));
				lblStatusInfo.setText(acceptStatus);
				// ----------
				while (rsDriverPhone.next()) {
					driverPhone = rsDriverPhone.getString("phone");
					DefaultComboBoxModel<String> model4 = (DefaultComboBoxModel<String>) comboBoxPhone.getModel();
					if (model4.getIndexOf(driverPhone) == -1) {
						comboBoxPhone.addItem(driverPhone);
					}
//
				}
			}
			driverNameGlobal = driverName;
			// +++++++++++++++++++++++++++++++++++++

			pstmtTripStatus.close();
			con.close();
		}catch (Exception e){

		}
		// ==
		JButton btnDoneTrip = new JButton("Finished");
		btnDoneTrip.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		btnDoneTrip.setBounds(180, 330, 117, 40);
		btnDoneTrip.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabreservationsystem",
							"ahmedshawki", "nopass"); // viewed password ??
					// -------------------------
					String queryTripStatus = "SELECT status_trip FROM trips WHERE id = (SELECT trips_id FROM customer WHERE email = ? );";
					PreparedStatement pstmtTripStatus = con.prepareStatement(queryTripStatus) ;
					pstmtTripStatus.setString(1,customerEmailThisPage);
					ResultSet rsTripStatus = pstmtTripStatus.executeQuery();

					String tripStatus = "";
					while (rsTripStatus.next()){
						tripStatus = tripStatus + rsTripStatus.getString("status_trip");
					}

					if(tripStatus.equals("Accepted") && !(driverNameGlobal.isEmpty())){
						// ===================== Update The Trip =========================
						String updateTripFinished = "UPDATE trips SET status_trip = ? WHERE id = (SELECT trips_id FROM customer WHERE email = ? );";
						PreparedStatement pstmtTripFinished = con.prepareStatement(updateTripFinished);
						pstmtTripFinished.setString(1,"Trip Finished");
						pstmtTripFinished.setString(2,customerEmailThisPage);
						pstmtTripFinished.executeUpdate();
						// ==============================================================
						lblDone.setBackground(new Color(230, 230, 250));
						lblDone.setForeground(new Color(50, 160, 0));
						lblDone.setText("Trip Finished!");
						// ==============================================================
						String updateCustomerTrip = "UPDATE customer SET trips_id = ? WHERE email = ? ;";
						PreparedStatement pstmtCustomerTrip = con.prepareStatement(updateCustomerTrip);
						pstmtCustomerTrip.setNull(1,Types.INTEGER);
						pstmtCustomerTrip.setString(2,customerEmailThisPage);
						pstmtCustomerTrip.executeUpdate();
						// ===============================================================
					} else if (driverNameGlobal.isEmpty() && tripStatus.equals("Accepted")) {
						lblDone.setBackground(new Color(230, 230, 250));
						lblDone.setForeground(new Color(190, 10, 30));
						lblDone.setText("Failed Trip Reservation, please cancel!");
						
//					} else if (tripStatus.equals("Trip Finished")) {
//						lblDone.setBackground(new Color(230, 230, 250));
//						lblDone.setForeground(new Color(50, 160, 0));
//						lblDone.setText("Trip already Finished!");
					} else{
						lblDone.setBackground(new Color(230, 230, 250));
						lblDone.setForeground(new Color(190, 10, 30));
						lblDone.setText("Wrong, trip not finished yet!");
					}
				} catch (ClassNotFoundException ex) {
					throw new RuntimeException(ex);
				} catch (SQLException ex) {
					throw new RuntimeException(ex);
				}


			}
		});
		contentPane.add(btnDoneTrip);
		// ==
		JButton btnBack = new JButton("< Back");
		btnBack.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		btnBack.setBounds(320, 460, 117, 25);
		btnBack.setOpaque(false);
		btnBack.setContentAreaFilled(false);
		btnBack.setBorderPainted(false);
		btnBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// ----- ----------------------------- -----
				// ----- Back to Driver Sign-in Window -----
				// ----- ----------------------------- -----
				RideReservation the_ride_back = new RideReservation(customerEmailThisPage);
				the_ride_back.setVisible(true);
				setVisible(false);
				// dispose();
				// -------------------------------------------
			}
		});
		contentPane.add(btnBack);
		// ========================================================================

        JButton btnExit = new JButton("Exit");
		btnExit.setBounds(460, 460, 117, 25);
		btnExit.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		btnExit.setContentAreaFilled(false); // to make it looking like a Label - step 1
		btnExit.setBorderPainted(false); // to make it looking like a Label - step 2
		btnExit.setOpaque(false); // to make it looking like a Label - step 3
		btnExit.addActionListener((event) -> System.exit(0)); // to do Exit
		contentPane.add(btnExit);

		JButton btnFlush = new JButton("Cancel");
		btnFlush.setFont(new Font("DejaVu Serif", Font.PLAIN, 20));
		btnFlush.setBounds(380, 330, 117, 40);
		btnFlush.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// ----- ----------------------------- -----
				if (!(tripStatusGlobal.equals("Refused"))) {
					try {
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabreservationsystem",
								"ahmedshawki", "nopass"); // viewed password ??
						// -------------------------
						String updateCustomerTrip = "UPDATE customer SET trips_id = ? WHERE email = ? ;";
						// ----- Create a PreparedStatement -----
						PreparedStatement pstmtCustomerTrip = con.prepareStatement(updateCustomerTrip);
						// ----- Set the parameters value -----
						pstmtCustomerTrip.setNull(1, Types.INTEGER); // Set trip id to null
						pstmtCustomerTrip.setString(2, customerEmailThisPage);
						// ----- Execute the Query -----
						pstmtCustomerTrip.executeUpdate();
						//---------------------------------
						lblDriverNameInfo.setBackground(new Color(230, 230, 250));
						lblDriverNameInfo.setForeground(new Color(200, 15, 0));
						lblDriverNameInfo.setText("No Driver!");
						lblStatusInfo.setBackground(new Color(230, 230, 250));
						lblStatusInfo.setForeground(new Color(200, 15, 0));
						lblStatusInfo.setText("No Trip");
						comboBoxPhone.removeAllItems();
						// ----------------------------------------
						if (!(tripIdStatus == 0 || tripIdStatus == null)) {
//						String updateDriverTrip = "UPDATE driver SET trips_id = ? WHERE trips_id = ? ;";
//						// ----- Create a PreparedStatement -----
//						PreparedStatement pstmtDriverTrip = con.prepareStatement(updateDriverTrip);
//						// ----- Set the parameters value -----
//						pstmtDriverTrip.setNull(1, Types.INTEGER); // Set trip id to null
//						pstmtDriverTrip.setInt(2, tripIdStatus);
//						// ----- Execute the Query -----
//						pstmtDriverTrip.executeUpdate();
//						// ==========
//						pstmtDriverTrip.close();
							// ============================= Update Trips ===============================
							if (!(driverId == 0)) {
								String updateTripStatus = "UPDATE trips SET status_trip = ? WHERE id = ? ;";
								// ----- Create a PreparedStatement -----
								PreparedStatement pstmtTripStatus = con.prepareStatement(updateTripStatus);
								// ----- Set the parameters value -----
								pstmtTripStatus.setString(1, "Customer Cancelled"); // Set trip status to cancel
								pstmtTripStatus.setInt(2, tripIdStatus);
								// ----- Execute the Query -----
								pstmtTripStatus.executeUpdate();
								// ==========
								pstmtTripStatus.close();
							} else {
								String updateTripStatus = "UPDATE trips SET status_trip = ? WHERE id = ? ;";
								// ----- Create a PreparedStatement -----
								PreparedStatement pstmtTripStatus = con.prepareStatement(updateTripStatus);
								// ----- Set the parameters value -----
								pstmtTripStatus.setString(1, null); // Set trip status to null
								pstmtTripStatus.setInt(2, tripIdStatus);
								// ----- Execute the Query -----
								pstmtTripStatus.executeUpdate();
								// ==========
								pstmtTripStatus.close();
							}

						}
						// ====================================================
						lblDone.setBackground(new Color(230, 230, 250));
						lblDone.setForeground(new Color(50, 160, 0));
						lblDone.setText("Done");
						pstmtCustomerTrip.close();
						con.close();

					} catch (Exception e) {

					}
					// -------------------------------------------
				}else{
					try{
						Class.forName("com.mysql.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cabreservationsystem",
								"ahmedshawki", "nopass"); // viewed password ??
						// -------------------------
						String updateCustomerTrip = "UPDATE customer SET trips_id = ? WHERE email = ? ;";
						// ----- Create a PreparedStatement -----
						PreparedStatement pstmtCustomerTrip = con.prepareStatement(updateCustomerTrip);
						// ----- Set the parameters value -----
						pstmtCustomerTrip.setNull(1, Types.INTEGER); // Set trip id to null
						pstmtCustomerTrip.setString(2, customerEmailThisPage);
						// ----- Execute the Query -----
						pstmtCustomerTrip.executeUpdate();
						//---------------------------------
						lblDriverNameInfo.setBackground(new Color(230, 230, 250));
						lblDriverNameInfo.setForeground(new Color(200, 15, 0));
						lblDriverNameInfo.setText("No Driver!");
						lblStatusInfo.setBackground(new Color(230, 230, 250));
						lblStatusInfo.setForeground(new Color(200, 15, 0));
						lblStatusInfo.setText("No Trip");
						comboBoxPhone.removeAllItems();
						// ----------------------------------------
						// ====================================================
						lblDone.setBackground(new Color(230, 230, 250));
						lblDone.setForeground(new Color(50, 160, 0));
						lblDone.setText("Done");
						pstmtCustomerTrip.close();
						con.close();
					}catch (Exception ex){

					}
				}
			}
		});
		contentPane.add(btnFlush);


		JLabel lblNewLabel = new JLabel("Trip Acceptance");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("DejaVu Serif", Font.BOLD, 30));
		lblNewLabel.setBounds(150, 40, 287, 50);
		contentPane.add(lblNewLabel);

	}
}
