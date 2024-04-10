package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JComboBox;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;

import model.Flight;
import model.Seat;
import model.User;
import operations.UserOperations;
import operations.SeatOperations;

public class TicketBookingPane extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPane;

    public TicketBookingPane(Flight flight, User user) {

        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 750, 450);
        contentPane = new JPanel();
        contentPane.setForeground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel seatChoosingLabel = new JLabel("KOLTUK SEÇİMİ");
        seatChoosingLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        seatChoosingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        seatChoosingLabel.setBounds(141, 249, 97, 19);
        contentPane.add(seatChoosingLabel);

        JLabel flightInfoLabel = new JLabel("UÇUŞ DETAYLARI");
        flightInfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        flightInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        flightInfoLabel.setBounds(294, 21, 143, 22);
        contentPane.add(flightInfoLabel);

        JSeparator separator = new JSeparator();
        separator.setBackground(new Color(0, 0, 0));
        separator.setOrientation(SwingConstants.VERTICAL);
        separator.setBounds(365, 78, 2, 128);
        contentPane.add(separator);

        JLabel departureLabel = new JLabel("KALKIŞ");
        departureLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        departureLabel.setHorizontalAlignment(SwingConstants.CENTER);
        departureLabel.setBounds(71, 78, 52, 19);
        contentPane.add(departureLabel);

        JLabel arrivalLabel = new JLabel("VARIŞ");
        arrivalLabel.setHorizontalAlignment(SwingConstants.CENTER);
        arrivalLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        arrivalLabel.setBounds(240, 78, 52, 19);
        contentPane.add(arrivalLabel);

        JLabel originAirportLabel = new JLabel("");
        originAirportLabel.setHorizontalAlignment(SwingConstants.CENTER);
        originAirportLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        originAirportLabel.setBounds(22, 121, 150, 20);
        originAirportLabel.setText(flight.getKalkisyeri());
        contentPane.add(originAirportLabel);

        JLabel arrivalAirportLabel = new JLabel("");
        arrivalAirportLabel.setHorizontalAlignment(SwingConstants.CENTER);
        arrivalAirportLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        arrivalAirportLabel.setBounds(190, 121, 150, 20);
        arrivalAirportLabel.setText(flight.getVarisyeri());
        contentPane.add(arrivalAirportLabel);

        JLabel departureTimeLabel = new JLabel("");
        departureTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        departureTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        departureTimeLabel.setBounds(22, 151, 150, 20);
        departureTimeLabel.setText(flight.getKalkisTarihi() + " " + flight.getKalkisZamani());
        contentPane.add(departureTimeLabel);

        JLabel arrivalTimeLabel = new JLabel("");
        arrivalTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        arrivalTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        arrivalTimeLabel.setBounds(190, 151, 150, 20);
        arrivalTimeLabel.setText(flight.getVarisTarihi() + " " + flight.getVarisZamani());
        contentPane.add(arrivalTimeLabel);

        JLabel flightLabel = new JLabel("UÇUŞ");
        flightLabel.setHorizontalAlignment(SwingConstants.CENTER);
        flightLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        flightLabel.setBounds(530, 78, 41, 19);
        contentPane.add(flightLabel);

        JLabel flightNumberLabel = new JLabel("UÇUŞ NO : ");
        flightNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        flightNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        flightNumberLabel.setBounds(462, 121, 180, 20);
        flightNumberLabel.setText(flightNumberLabel.getText() + " " + flight.getUcusNo());
        contentPane.add(flightNumberLabel);

        JLabel planeNameLabel = new JLabel("UÇAK MODELİ : ");
        planeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        planeNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        planeNameLabel.setBounds(440, 151, 220, 20);
        planeNameLabel.setText(planeNameLabel.getText() + " " + flight.getUcak());
        contentPane.add(planeNameLabel);

        Seat[] seats = SeatOperations.getSeats(flight);
        String[] seatNumbers = new String[seats.length];
        for (int i = 0; i < seatNumbers.length; i++) {
            seatNumbers[i] = seats[i].getKoltuknumarasi() + "-" + seats[i].getKoltukturu();
        }
        JComboBox<String> cboxSeats = new JComboBox<>();
        cboxSeats.setBackground(new Color(255, 255, 255));
        cboxSeats.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cboxSeats.setBounds(92, 279, 200, 30);
        cboxSeats.setModel(new DefaultComboBoxModel<>(seatNumbers));
        contentPane.add(cboxSeats);

        JButton ticketBookingButton = new JButton("BİLET AL");
        ticketBookingButton.addActionListener((ActionEvent e) -> {
            int selectedRow = cboxSeats.getSelectedIndex();
            Seat seat = seats[selectedRow];
            boolean status = UserOperations.ticketBooking(flight, user, seat);
            if (status) {
                JOptionPane.showMessageDialog(ticketBookingButton, "Bilet Alındı");
            } else {
                JOptionPane.showMessageDialog(ticketBookingButton, "Bir Hata oluştu. Lütfen daha sonra tekrar deneyin");
            }
        });
        ticketBookingButton.setBackground(new Color(38, 38, 38));
        ticketBookingButton.setForeground(new Color(255, 255, 255));
        ticketBookingButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        ticketBookingButton.setBounds(462, 277, 200, 35);
        contentPane.add(ticketBookingButton);
    }
}
