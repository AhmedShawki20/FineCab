package java_app_cab_int;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("unused")
public class Start extends JFrame {

	private static final long serialVersionUID = -5615378493303868923L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					Start frame = new Start();
					frame.setVisible(true);
//
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Start() {

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 374);
		setUndecorated(true);
		setLocationRelativeTo(null);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 215, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ===== To Make Focus on Unused Button =====
		JButton dummyButton = new JButton();
		dummyButton.setFocusable(true); // Allow this component to receive initial focus
		dummyButton.setBounds(0, 0, 1, 1); // Position it outside visible area
		contentPane.add(dummyButton);
		dummyButton.requestFocusInWindow(); // Set initial focus to the dummy button
		// ================
		JLabel lblLogo = new JLabel("FineCab");
		lblLogo.setFont(new Font("DejaVu Serif", Font.PLAIN, 50));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setBounds(0, 0, 600, 374);
		contentPane.add(lblLogo);

		JLabel label = new JLabel("―――――――――――――――――――");
		label.setBounds(187, 196, 250, 30);
		contentPane.add(label);

		JLabel label_1 = new JLabel("―――――――――――――――――――");
		label_1.setBounds(187, 145, 250, 30);
		contentPane.add(label_1);

		// ----------------------------------------------
		//  Button to exit the Frame 
		// ----------------------------------------------
		JButton GoButton = new JButton();
		GoButton.setVerticalAlignment(SwingConstants.TOP);
		// Auto close the window and bring the main_page 
		GoButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Java_app_cab_int main_page_1 = new Java_app_cab_int();
				main_page_1.setVisible(true);
				setVisible(false);
				dispose();
			}
		});
//		GoButton.setFont(new Font("DejaVu Serif", Font.PLAIN, 28));
		GoButton.setBounds(428, 280, 160, 60);
		GoButton.setContentAreaFilled(false); // to make it looking like a Label - step 1
		GoButton.setBorderPainted(false); // to make it looking like a Label - step 2
		GoButton.setOpaque(false); // to make it looking like a Label - step 3
		GoButton.setIcon(new ImageIcon(new ImageIcon("/java_app_cab_int/src/pic/Spinner.gif").getImage())); // Spinner
		getContentPane().add(GoButton);
		// ---------------------- ---------------------------------------------------
		// Timer to close the window after 1.5 seconds and simulate button click
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				GoButton.doClick(); // Simulate the Go button click
			}
		});
		timer.setRepeats(false); // Ensure the timer only runs once
		timer.start(); //  begins the countdown for the timer
		// ---------------------- ---------------------------------------------------

	}

}
