package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

import java.text.SimpleDateFormat;
import com.raven.datechooser.DateChooser;

import model.Flight;
import model.User;
import model.Airport;

import operations.AirportOperations;
import operations.FlightOperations;
import javax.swing.ImageIcon;

public class MainGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JPanel contentPane;
    private final JPanel panel_1;
    private final JButton ticketButton;
    private final JButton checkInButton;
    private final JButton flightStatusButton;
    private final JButton userInfoButton;
    private final JLabel lblBack;
    private JPanel userInfoPane = null;
    private JPanel ticketBookingPane = null;
    private JPanel userTicketsPane = null;
    private JPanel flightStatusPane = null;
    private JLayeredPane layeredPane = null;
    private final DateChooser chDate;
    private final JTextField txtDate;

    public MainGUI(User user) {
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        panel_1 = new JPanel();
        panel_1.setBounds(0, 0, 450, 553);
        panel_1.setBackground(new Color(38, 38, 38));
        panel_1.setLayout(null);

        ticketButton = new JButton("Bilet Al");
        ticketButton.setBackground(new Color(255, 255, 255));
        ticketButton.setBounds(87, 321, 275, 35);
        ticketButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ticketButton.addActionListener((ActionEvent e) -> {
            layeredPane.removeAll();
            layeredPane.add(ticketBookingPane);
            layeredPane.validate();
            layeredPane.repaint();
        });
        panel_1.add(ticketButton);

        checkInButton = new JButton("Biletlerim");
        checkInButton.setBackground(new Color(255, 255, 255));
        checkInButton.setBounds(87, 371, 275, 35);
        checkInButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        checkInButton.addActionListener((ActionEvent e) -> {
            layeredPane.removeAll();
            layeredPane.add(userTicketsPane);
            layeredPane.validate();
            layeredPane.repaint();
        });
        panel_1.add(checkInButton);

        flightStatusButton = new JButton("Uçuş Durumu");
        flightStatusButton.setBackground(new Color(255, 255, 255));
        flightStatusButton.setBounds(87, 421, 275, 35);
        flightStatusButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        flightStatusButton.addActionListener((ActionEvent e) -> {
            layeredPane.removeAll();
            layeredPane.add(flightStatusPane);
            layeredPane.validate();
            layeredPane.repaint();
        });
        panel_1.add(flightStatusButton);

        userInfoButton = new JButton("Kullanıcı Bilgileri");
        userInfoButton.setBackground(new Color(255, 255, 255));
        userInfoButton.setBounds(87, 471, 275, 35);
        userInfoButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        userInfoButton.addActionListener((ActionEvent e) -> {
            layeredPane.removeAll();
            layeredPane.add(userInfoPane);
            layeredPane.validate();
            layeredPane.repaint();
        });
        panel_1.add(userInfoButton);

        JLabel lblWelcome = new JLabel("Hoşgeldiniz");
        lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 22));
        lblWelcome.setForeground(new Color(255, 255, 255));
        lblWelcome.setBounds(167, 202, 115, 30);
        panel_1.add(lblWelcome);

        JLabel lblUsername = new JLabel("Sayın, ");
        lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsername.setForeground(new Color(255, 255, 255));
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblUsername.setBounds(84, 242, 282, 22);
        lblUsername.setText(lblUsername.getText() + user.getAd() + " " + user.getSoyad());
        panel_1.add(lblUsername);

        lblBack = new JLabel("ÇIKIŞ");
        lblBack.setForeground(new Color(255, 255, 255));
        lblBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblBack.setBounds(30, 30, 47, 22);
        lblBack.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new UserLoginGUI().setVisible(true);
            }
        });
        panel_1.add(lblBack);

        ticketBookingPane = new JPanel();
        ticketBookingPane.setLayout(null);
        ticketBookingPane.setBackground(new Color(255, 255, 255));

        flightStatusPane = new JPanel();
        flightStatusPane.setBackground(new Color(0, 128, 255));
        flightStatusPane.setLayout(null);

        userTicketsPane = new JPanel();
        userTicketsPane.setBackground(new Color(255, 0, 0));
        userTicketsPane.setLayout(null);

        userInfoPane = new JPanel();
        userInfoPane.setLayout(null);
        userInfoPane.setBackground(new Color(128, 255, 255));

        Airport[] airports = AirportOperations.getAirports();
        String[] airportNames = new String[airports.length];
        for (int i = 0; i < airports.length; i++) {
            airportNames[i] = airports[i].getHavaalani();
        }
        JComboBox<String> cboxOrigin = new JComboBox<>();
        cboxOrigin.setBackground(new Color(255, 255, 255));
        cboxOrigin.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cboxOrigin.setModel(new DefaultComboBoxModel<>(airportNames));
        cboxOrigin.setSelectedItem(null);
        cboxOrigin.setBounds(128, 150, 275, 30);
        ticketBookingPane.add(cboxOrigin);

        JLabel lblNereden = new JLabel("NEREDEN?");
        lblNereden.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNereden.setBounds(218, 120, 94, 20);
        ticketBookingPane.add(lblNereden);

        JLabel lblNereye = new JLabel("NEREYE?");
        lblNereye.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNereye.setBounds(218, 210, 94, 20);
        ticketBookingPane.add(lblNereye);

        JComboBox<String> cboxArrival = new JComboBox<>();
        cboxArrival.setBackground(new Color(255, 255, 255));
        cboxArrival.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cboxArrival.setModel(new DefaultComboBoxModel<>(airportNames));
        cboxArrival.setSelectedItem(null);
        cboxArrival.setBounds(128, 240, 275, 30);
        ticketBookingPane.add(cboxArrival);

        txtDate = new JTextField();
        txtDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtDate.setBounds(128, 338, 275, 30);

        chDate = new DateChooser();
        chDate.setTextField(txtDate);
        chDate.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        txtDate.setText("");
        ticketBookingPane.add(txtDate);

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(450, 0, 531, 553);
        layeredPane.setLayout(new CardLayout(0, 0));
        layeredPane.add(ticketBookingPane);
        layeredPane.add(userTicketsPane);
        layeredPane.add(flightStatusPane);
        layeredPane.add(userInfoPane);

        layeredPane.setLayer(ticketBookingPane, 1);

        JLabel lblDate = new JLabel("TARİH");
        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblDate.setBounds(237, 308, 57, 20);
        ticketBookingPane.add(lblDate);

        JButton flySearchButton = new JButton("UÇUŞ ARA");
        flySearchButton.setBackground(new Color(38, 38, 38));
        flySearchButton.setForeground(new Color(255, 255, 255));
        flySearchButton.addActionListener((ActionEvent e) -> {
            String source = cboxOrigin.getSelectedItem().toString();
            String arrival = cboxArrival.getSelectedItem().toString();
            String time = txtDate.getText();

            boolean isFlightFound = FlightOperations.isFlightAvailable(source, arrival, time);

            if (isFlightFound) {
                Flight[] flights = FlightOperations.getFlights(source, arrival, time);
                new FlightsPane(flights, user).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(null, "Aradığınız Kriterde Uçuş Bulunamadı");
            }
        });

        flySearchButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        flySearchButton.setBounds(115, 452, 300, 40);
        ticketBookingPane.add(flySearchButton);

        layeredPane.setLayer(userTicketsPane, 2);
        layeredPane.setLayer(flightStatusPane, 3);
        layeredPane.setLayer(userInfoPane, 4);

        contentPane.add(panel_1);
        
        JLabel userIconLabel = new JLabel("");
        ImageIcon originalIcon = new ImageIcon(UserLoginGUI.class.getResource("/images/User.png"));
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(125, 125, Image.SCALE_SMOOTH);
        userIconLabel.setIcon(new ImageIcon(resizedImage));
        userIconLabel.setBounds(162, 59, 125, 125);
        panel_1.add(userIconLabel);
        contentPane.add(layeredPane);
        setContentPane(contentPane);
    }
}
