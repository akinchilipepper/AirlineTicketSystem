package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;

import model.Flight;
import model.Seat;
import model.User;
import operations.SeatOperations;
import operations.TicketOperations;

import java.awt.GridLayout;
import java.awt.Image;

public class TicketBookingPane extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	

	public TicketBookingPane(Flight flight, User user, MainGUI mainGUI) {
		Image appIcon = new ImageIcon(this.getClass().getResource("/images/appIconPlane.png")).getImage();
    	setIconImage(appIcon);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 750, 450);
		setTitle("Bilet Rezervasyonu");
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel seatChoosingLabel = new JLabel("KOLTUK SEÇİMİ");
		seatChoosingLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		seatChoosingLabel.setHorizontalAlignment(SwingConstants.CENTER);
		seatChoosingLabel.setBounds(59, 312, 97, 19);
		contentPane.add(seatChoosingLabel);

		JLabel flightInfoLabel = new JLabel("UÇUŞ DETAYLARI");
		flightInfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		flightInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		flightInfoLabel.setBounds(294, 36, 143, 22);
		contentPane.add(flightInfoLabel);

		Seat[] seats = SeatOperations.getSeats(flight);
		String[] seatNumbers = new String[seats.length];
		for (int i = 0; i < seatNumbers.length; i++) {
			seatNumbers[i] = seats[i].getKoltuknumarasi() + "-" + seats[i].getKoltukturu();
		}
		JComboBox<String> cboxSeats = new JComboBox<>();
		cboxSeats.setBackground(new Color(255, 255, 255));
		cboxSeats.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboxSeats.setBounds(10, 342, 200, 30);
		cboxSeats.setModel(new DefaultComboBoxModel<>(seatNumbers));
		contentPane.add(cboxSeats);

		JButton ticketBookingButton = new JButton("BİLET AL");
		ticketBookingButton.addActionListener((ActionEvent e) -> {
			int secim = JOptionPane.showConfirmDialog(ticketBookingButton,
					"Bu biletin fiyatı : " + flight.getBiletFiyati() + " TL\nSatın almak istediğinize emin misiniz?");
			if (secim == 0) {
				int selectedSeatRow = cboxSeats.getSelectedIndex();
				Seat seat = seats[selectedSeatRow];
				boolean status = TicketOperations.ticketBooking(flight, user, seat);
				
				if(status) {
					JOptionPane.showMessageDialog(null, "Bilet Alma İşleminiz Gerçekleştirildi");
					dispose();
					mainGUI.setTicketsTableModel();
					mainGUI.validate();
					mainGUI.repaint();
				} else {
					JOptionPane.showMessageDialog(null, "Bilet Alma İşleminiz Gerçekleştirilemedi");
				}
			}
		});
		ticketBookingButton.setBackground(new Color(38, 38, 38));
		ticketBookingButton.setForeground(new Color(255, 255, 255));
		ticketBookingButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		ticketBookingButton.setBounds(522, 337, 200, 35);
		contentPane.add(ticketBookingButton);

		JPanel panel = new JPanel();
		panel.setBounds(0, 109, 732, 185);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(1, 0, 0, 0));

		JPanel panel_1 = new JPanel();
		panel.add(panel_1);
		panel_1.setLayout(null);

		JLabel originAirportLabel = new JLabel("");
		originAirportLabel.setBounds(47, 85, 150, 20);
		panel_1.add(originAirportLabel);
		originAirportLabel.setHorizontalAlignment(SwingConstants.CENTER);
		originAirportLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		originAirportLabel.setText(flight.getKalkisyeri());

		JLabel departureLabel = new JLabel("KALKIŞ");
		departureLabel.setBounds(96, 20, 52, 19);
		panel_1.add(departureLabel);
		departureLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		departureLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel departureTimeLabel = new JLabel("");
		departureTimeLabel.setBounds(47, 115, 150, 20);
		panel_1.add(departureTimeLabel);
		departureTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		departureTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		departureTimeLabel.setText(flight.getKalkisTarihi() + " " + flight.getKalkisZamani());

		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBackground(new Color(0, 0, 0));
		separator.setBounds(242, 0, 2, 200);
		panel_1.add(separator);
		separator.setOrientation(SwingConstants.VERTICAL);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2);
		panel_2.setLayout(null);

		JLabel arrivalLabel = new JLabel("VARIŞ");
		arrivalLabel.setBounds(96, 20, 52, 19);
		panel_2.add(arrivalLabel);
		arrivalLabel.setHorizontalAlignment(SwingConstants.CENTER);
		arrivalLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel arrivalAirportLabel = new JLabel("");
		arrivalAirportLabel.setBounds(47, 85, 150, 20);
		panel_2.add(arrivalAirportLabel);
		arrivalAirportLabel.setHorizontalAlignment(SwingConstants.CENTER);
		arrivalAirportLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		arrivalAirportLabel.setText(flight.getVarisyeri());

		JLabel arrivalTimeLabel = new JLabel("");
		arrivalTimeLabel.setBounds(47, 115, 150, 20);
		panel_2.add(arrivalTimeLabel);
		arrivalTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		arrivalTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		arrivalTimeLabel.setText(flight.getVarisTarihi() + " " + flight.getVarisZamani());

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(242, 0, 2, 200);
		panel_2.add(separator_1);
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setForeground(Color.BLACK);
		separator_1.setBackground(Color.BLACK);

		JPanel panel_3 = new JPanel();
		panel.add(panel_3);
		panel_3.setLayout(null);

		JLabel flightLabel = new JLabel("UÇUŞ");
		flightLabel.setBounds(101, 20, 41, 19);
		panel_3.add(flightLabel);
		flightLabel.setHorizontalAlignment(SwingConstants.CENTER);
		flightLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));

		JLabel flightNumberLabel = new JLabel("UÇUŞ NO : ");
		flightNumberLabel.setBounds(32, 85, 180, 20);
		panel_3.add(flightNumberLabel);
		flightNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
		flightNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		flightNumberLabel.setText(flightNumberLabel.getText() + " " + flight.getUcusNo());

		JLabel planeNameLabel = new JLabel("UÇAK MODELİ : ");
		planeNameLabel.setBounds(12, 115, 220, 20);
		panel_3.add(planeNameLabel);
		planeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		planeNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		planeNameLabel.setText(planeNameLabel.getText() + " " + flight.getUcak());

		JLabel lblNewLabel = new JLabel("FİYAT");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(340, 312, 51, 18);
		contentPane.add(lblNewLabel);

		JLabel ticketPriceLabel = new JLabel("");
		ticketPriceLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ticketPriceLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ticketPriceLabel.setBounds(336, 342, 59, 22);
		contentPane.add(ticketPriceLabel);
		ticketPriceLabel.setText(flight.getBiletFiyati() + " TL");
	}
}
