package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;

import java.text.SimpleDateFormat;
import com.raven.datechooser.DateChooser;

import model.Flight;
import model.Ticket;
import model.User;
import model.Airport;

import operations.AirportOperations;
import operations.FlightOperations;

public class MainGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panel_1;
    private JButton ticketButton;
    private JButton checkInButton;
    private JButton flightStatusButton;
    private JButton userInfoButton;
    private JLabel lblBack;
    private JTable table;
    private JPanel userInfoPane = null;
    private JPanel ticketBookingPane = null;
    private JPanel userTicketsPane = null;
    private JPanel flightStatusPane = null;
    private JLayeredPane layeredPane = null;
    private DateChooser chDate;
    private JTextField txtDate;
    private Ticket[] tickets;

    public MainGUI(User user, Ticket[] tickets) {
    	this.tickets = tickets;
    	
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        panel_1 = new JPanel();
        panel_1.setBounds(0, 0, 500, 753);
        panel_1.setBackground(new Color(38, 38, 38));
        panel_1.setLayout(null);
        
        JLabel userIconLabel = new JLabel("");
        ImageIcon originalIcon = new ImageIcon(UserLoginGUI.class.getResource("/images/User.png"));
        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(125, 125, Image.SCALE_SMOOTH);
        userIconLabel.setIcon(new ImageIcon(resizedImage));
        userIconLabel.setBounds(187, 100, 125, 125);
        panel_1.add(userIconLabel);

        ticketButton = new JButton("Bilet Al");
        ticketButton.setBackground(new Color(255, 255, 255));
        ticketButton.setBounds(112, 440, 275, 35);
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
        checkInButton.setBounds(112, 490, 275, 35);
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
        flightStatusButton.setBounds(112, 540, 275, 35);
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
        userInfoButton.setBounds(112, 590, 275, 35);
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
        lblWelcome.setBounds(192, 268, 115, 30);
        panel_1.add(lblWelcome);

        JLabel lblUsername = new JLabel("Sayın, ");
        lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
        lblUsername.setForeground(new Color(255, 255, 255));
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblUsername.setBounds(109, 308, 282, 22);
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
        userTicketsPane.setBackground(new Color(255, 255, 255));
        userTicketsPane.setLayout(null);

        userInfoPane = new JPanel();
        userInfoPane.setLayout(null);
        userInfoPane.setBackground(new Color(128, 255, 255));

        //---------------------------------------- TICKET BOOKING PAGE ---------------------------------------------
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
        cboxOrigin.setBounds(204, 200, 275, 30);
        ticketBookingPane.add(cboxOrigin);

        JLabel lblNereden = new JLabel("NEREDEN?");
        lblNereden.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNereden.setBounds(294, 170, 94, 20);
        ticketBookingPane.add(lblNereden);

        JLabel lblNereye = new JLabel("NEREYE?");
        lblNereye.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNereye.setBounds(294, 260, 94, 20);
        ticketBookingPane.add(lblNereye);

        JComboBox<String> cboxArrival = new JComboBox<>();
        cboxArrival.setBackground(new Color(255, 255, 255));
        cboxArrival.setFont(new Font("Tahoma", Font.PLAIN, 15));
        cboxArrival.setModel(new DefaultComboBoxModel<>(airportNames));
        cboxArrival.setSelectedItem(null);
        cboxArrival.setBounds(204, 290, 275, 30);
        ticketBookingPane.add(cboxArrival);

        txtDate = new JTextField();
        txtDate.setFont(new Font("Tahoma", Font.PLAIN, 14));
        txtDate.setBounds(204, 388, 275, 30);

        chDate = new DateChooser();
        chDate.setTextField(txtDate);
        chDate.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        txtDate.setText("");
        ticketBookingPane.add(txtDate);

        layeredPane = new JLayeredPane();
        layeredPane.setBounds(500, 0, 683, 753);
        layeredPane.setLayout(new CardLayout(0, 0));
        layeredPane.add(ticketBookingPane);
        layeredPane.add(userTicketsPane);
        layeredPane.add(flightStatusPane);
        layeredPane.add(userInfoPane);

        layeredPane.setLayer(ticketBookingPane, 1);

        JLabel lblDate = new JLabel("TARİH");
        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblDate.setBounds(313, 358, 57, 20);
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
        flySearchButton.setBounds(191, 502, 300, 40);
        ticketBookingPane.add(flySearchButton);
        //-------------------------------------------------------------------------------------------------------
        
        
        
        
        //---------------------------------------- MY TICKETS PAGE ----------------------------------------------
        layeredPane.setLayer(userTicketsPane, 2);
        
        Object[][] objects = new Object[tickets.length][4];
        
        for(int i = 0; i < tickets.length; i++) {
        	objects[i][0] = tickets[i].getFlight().getKalkisyeri();
        	objects[i][1] = tickets[i].getFlight().getVarisyeri();
        	objects[i][2] = tickets[i].getFlight().getKalkisTarihi();
        }
        
        String[] columnNames = {"Kalkış","Varış","Kalkış Tarihi","Biletimi Görüntüle"};
        DefaultTableModel model = new DefaultTableModel(objects,columnNames) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 3;
			}
        	
        };
        
        table = new JTable(model);
        table.getColumn("Biletimi Görüntüle").setCellRenderer(new ButtonRenderer());
        table.getColumn("Biletimi Görüntüle").setCellEditor(new ButtonEditor(new JCheckBox()));
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(30);
        
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 17));
        
        JScrollPane ticketsScrollPane = new JScrollPane(table);
        ticketsScrollPane.setBounds(11, 308, 660, 435);
        userTicketsPane.add(ticketsScrollPane);
        
        JLabel myTicketsLabel = new JLabel("BİLETLERİM");
        myTicketsLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        myTicketsLabel.setBounds(276, 94, 130, 25);
        userTicketsPane.add(myTicketsLabel);
        // --------------------------------------------------------------------------------------------------------
        
        
        
        layeredPane.setLayer(flightStatusPane, 3);
        layeredPane.setLayer(userInfoPane, 4);

        contentPane.add(panel_1);
        contentPane.add(layeredPane);
        setContentPane(contentPane);
    }
    
    class ButtonRenderer extends JButton implements TableCellRenderer{
		private static final long serialVersionUID = 1L;
		
		public ButtonRenderer() {
			setOpaque(true);
		}
		
		@Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            setBackground(new Color(38, 38, 38));
            setForeground(new Color(255, 255, 255));
            setText("Bilet Bilgileri");
            return this;
        }
    }
    
    class ButtonEditor extends DefaultCellEditor{
		private static final long serialVersionUID = 1L;

		protected JButton button;

        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(e -> fireEditingStopped());
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            if (isSelected) {
                button.setForeground(table.getSelectionForeground());
                button.setBackground(table.getSelectionBackground());
            } else {
                button.setForeground(table.getForeground());
                button.setBackground(table.getBackground());
            }
            isPushed = true;
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            if (isPushed) {
            	int selectedRow = table.getSelectedRow();
                new TicketInfoPane(tickets[selectedRow]).setVisible(true);
            }
            isPushed = false;
            return label;
        }

        @Override
        public boolean stopCellEditing() {
            isPushed = false;
            return super.stopCellEditing();
        }

        @Override
        protected void fireEditingStopped() {
            super.fireEditingStopped();
        }
    }
}
