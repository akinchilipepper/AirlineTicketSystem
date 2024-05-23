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
import operations.TicketOperations;
import operations.UserOperations;

import java.awt.event.ActionListener;
import javax.swing.JPasswordField;

public class MainGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panel_1;
    private JButton ticketButton;
    private JButton checkInButton;
    private JButton flightStatusButton;
    private JButton userInfoButton;
    private JButton changePasswordCommitButton;
    private JButton changeMailCommit;
    private JButton changeTelNumberCommit;
    private JButton changeTelNumberButton;
    private JButton changeMailButton;
    private JLabel lblBack;
    private JLabel newPasswordLabel;
    private JLabel newPasswordRepLabel;
    private JTable table;
    private JPanel userInfoPane = null;
    private JPanel ticketBookingPane = null;
    private JPanel userTicketsPane = null;
    private JPanel flightStatusPane = null;
    private JLayeredPane layeredPane = null;
    private DateChooser chDate;
    private JTextField txtDate;
    private JTextField flightNumberField;
    private User user;
    private Ticket[] tickets;
    private JTextField nameField;
    private JTextField surnameField;
    private JTextField telephoneField;
    private JTextField mailField;
    private JTextField birthdateField;
    private JPasswordField passwordField;
    private JButton changePasswordButton;
    private JButton changeMailCancel;
    private JButton changeTelCancel;
    private JButton changePasswordCancel;
    private JPasswordField newPasswordField;
    private JPasswordField newPasswordRepField;

    public MainGUI(User user) {
    	
    	this.user = user;
    	
    	Image appIcon = new ImageIcon(this.getClass().getResource("/images/appIconPlane.png")).getImage();
    	setIconImage(appIcon);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setTitle("Flaai");

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
        checkInButton.setBounds(112, 500, 275, 35);
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
        flightStatusButton.setBounds(112, 560, 275, 35);
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
        userInfoButton.setBounds(112, 620, 275, 35);
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
        flightStatusPane.setBackground(new Color(255, 255, 255));
        flightStatusPane.setLayout(null);

        userTicketsPane = new JPanel();
        userTicketsPane.setBackground(new Color(255, 255, 255));
        userTicketsPane.setLayout(null);

        userInfoPane = new JPanel();
        userInfoPane.setLayout(null);
        userInfoPane.setBackground(new Color(255, 255, 255));

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
                new FlightsPane(flights, user, this).setVisible(true);
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
        
        
        table = new JTable();
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(30);
        
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 17));
        
        setTicketsTableModel();
        
        JScrollPane ticketsScrollPane = new JScrollPane(table);
        ticketsScrollPane.setBounds(11, 308, 660, 435);
        userTicketsPane.add(ticketsScrollPane);
        
        JLabel myTicketsLabel = new JLabel("BİLETLERİM");
        myTicketsLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        myTicketsLabel.setBounds(276, 94, 130, 25);
        userTicketsPane.add(myTicketsLabel);
        // --------------------------------------------------------------------------------------------------------
        
        
        
        layeredPane.setLayer(flightStatusPane, 3);
        
        JLabel flightQueryLabel = new JLabel("UÇUŞ SORGULAMA");
        flightQueryLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        flightQueryLabel.setBounds(258, 137, 167, 25);
        flightStatusPane.add(flightQueryLabel);
        
        JLabel flightNumberLabel = new JLabel("UÇUŞ NUMARASI");
        flightNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        flightNumberLabel.setBounds(268, 295, 139, 25);
        flightStatusPane.add(flightNumberLabel);
        
        flightNumberField = new JTextField();
        flightNumberField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        flightNumberField.setBounds(204, 330, 275, 35);
        flightStatusPane.add(flightNumberField);
        flightNumberField.setColumns(10);
        
        JButton flightQueryButton = new JButton("SORGULA");
        flightQueryButton.setForeground(new Color(255, 255, 255));
        flightQueryButton.setBackground(new Color(38, 38, 38));
        flightQueryButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String flightNumber = flightNumberField.getText();
        		String flightStatus = FlightOperations.getFlightStatus(flightNumber);
        		JOptionPane.showMessageDialog(null, flightStatus);
        	}
        });
        flightQueryButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        flightQueryButton.setBounds(216, 579, 250, 35);
        flightStatusPane.add(flightQueryButton);
        layeredPane.setLayer(userInfoPane, 4);
        
        JLabel nameLabel = new JLabel("ADINIZ");
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        nameLabel.setBounds(49, 99, 87, 20);
        userInfoPane.add(nameLabel);
        
        nameField = new JTextField();
        nameField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        nameField.setColumns(10);
        nameField.setBounds(49, 129, 215, 30);
        nameField.setText(user.getAd());
        nameField.setEditable(false);
        userInfoPane.add(nameField);
        
        JLabel surnameLabel = new JLabel("SOYADINIZ");
        surnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        surnameLabel.setBounds(401, 99, 87, 20);
        userInfoPane.add(surnameLabel);
        
        surnameField = new JTextField();
        surnameField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        surnameField.setColumns(10);
        surnameField.setBounds(401, 129, 215, 30);
        surnameField.setText(user.getSoyad());
        surnameField.setEditable(false);
        userInfoPane.add(surnameField);
        
        JLabel telephoneLabel = new JLabel("TELEFON NUMARANIZ");
        telephoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        telephoneLabel.setBounds(50, 273, 164, 20);
        userInfoPane.add(telephoneLabel);
        
        telephoneField = new JTextField();
        telephoneField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        telephoneField.setColumns(10);
        telephoneField.setBounds(50, 303, 215, 30);
        telephoneField.setText(user.getTelno());
        telephoneField.setEditable(false);
        userInfoPane.add(telephoneField);
        
        changeTelNumberButton = new JButton("DEĞİŞTİR");
        changeTelNumberButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		changeTelNumberCommit.setVisible(true);
        		changeTelNumberButton.setVisible(false);
        		changeTelCancel.setVisible(true);
        		telephoneField.setEditable(true);
        		telephoneField.setText("");
        	}
        });
        changeTelNumberButton.setForeground(new Color(255, 255, 255));
        changeTelNumberButton.setBackground(new Color(38, 38, 38));
        changeTelNumberButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        changeTelNumberButton.setBounds(295, 303, 215, 30);
        userInfoPane.add(changeTelNumberButton);
        
        JLabel mailLabel = new JLabel("E-POSTANIZ");
        mailLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        mailLabel.setBounds(49, 380, 164, 20);
        userInfoPane.add(mailLabel);
        
        mailField = new JTextField();
        mailField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        mailField.setColumns(10);
        mailField.setBounds(49, 410, 215, 30);
        mailField.setText(user.getE_posta());
        mailField.setEditable(false);
        userInfoPane.add(mailField);
        
        JLabel birthdateLabel = new JLabel("DOĞUM TARİHİNİZ");
        birthdateLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        birthdateLabel.setBounds(49, 169, 150, 20);
        userInfoPane.add(birthdateLabel);
        
        birthdateField = new JTextField();
        birthdateField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        birthdateField.setColumns(10);
        birthdateField.setBounds(49, 199, 215, 30);
        birthdateField.setText(user.getDogumtarihi());
        birthdateField.setEditable(false);
        userInfoPane.add(birthdateField);
        
        JLabel passwordLabel = new JLabel("PAROLANIZ");
        passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        passwordLabel.setBounds(49, 486, 164, 20);
        userInfoPane.add(passwordLabel);
        
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        passwordField.setBounds(49, 516, 215, 30);
        passwordField.setText(user.getParola());
        passwordField.setEditable(false);
        userInfoPane.add(passwordField);
        
        JLabel userInfoLabel = new JLabel("KULLANICI BİLGİLERİ");
        userInfoLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        userInfoLabel.setBounds(240, 31, 203, 20);
        userInfoPane.add(userInfoLabel);
        
        changeMailButton = new JButton("DEĞİŞTİR");
        changeMailButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		changeMailCommit.setVisible(true);
        		changeMailButton.setVisible(false);
        		changeMailCancel.setVisible(true);
        		mailField.setEditable(true);
        		mailField.setText("");
        	}
        });
        changeMailButton.setForeground(new Color(255, 255, 255));
        changeMailButton.setBackground(new Color(38, 38, 38));
        changeMailButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        changeMailButton.setBounds(294, 410, 215, 30);
        userInfoPane.add(changeMailButton);
                
        newPasswordLabel = new JLabel("YENİ PAROLANIZ");
        newPasswordLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        newPasswordLabel.setBounds(49, 563, 164, 20);
        newPasswordLabel.setVisible(false);
        userInfoPane.add(newPasswordLabel);
        
        newPasswordRepLabel = new JLabel("YENİ PAROLA TEKRAR");
        newPasswordRepLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        newPasswordRepLabel.setBounds(295, 563, 193, 20);
        newPasswordRepLabel.setVisible(false);
        userInfoPane.add(newPasswordRepLabel);
        
        changePasswordCommitButton = new JButton("ONAYLA");
        changePasswordCommitButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String oldPassword = new String(passwordField.getPassword());
        		String newPassword = new String(newPasswordField.getPassword());
        		String newPasswordRep = new String(newPasswordRepField.getPassword());
        		
        		if(oldPassword.isEmpty() || newPassword.isEmpty() || newPasswordRep.isEmpty()) {
        			JOptionPane.showMessageDialog(null, "Parola alanları boş olamaz");
        		} else if(!oldPassword.equals(user.getParola())) {
        			JOptionPane.showMessageDialog(null, "Mevcut parolanız doğru değil");
        		} else if(!newPassword.equals(newPasswordRep)) {
        			JOptionPane.showMessageDialog(null, "Parolalar eşleşmiyor");
        		} else if(newPassword.length() < 8) {
        			JOptionPane.showMessageDialog(null, "Parola uzunluğu en az 8 karakter olmalı");
        		} else if(oldPassword.equals(newPassword)) {
        			JOptionPane.showMessageDialog(null, "Yeni parolanız eski parolanız ile aynı olamaz");
        		} else {
        			int choice = JOptionPane.showConfirmDialog(null, "Şifrenizi değiştirmeyi onaylıyor musunuz");
        			if(choice == 0) {
        				boolean result = UserOperations.updatePassword(user, newPassword);
        				if(result) {
        					JOptionPane.showMessageDialog(null, "Şifreniz değiştirildi");
        					user.setParola(newPassword);
        					newPasswordLabel.setVisible(false);
        	        		newPasswordRepLabel.setVisible(false);
        	        		newPasswordField.setVisible(false);
        	        		newPasswordRepField.setVisible(false);
        	        		changePasswordCommitButton.setVisible(false);
        	        		changePasswordButton.setVisible(true);
        	        		changePasswordCancel.setVisible(false);
        	        		passwordField.setEditable(false);
        				} else {
        					JOptionPane.showMessageDialog(null, "Şifreniz değiştirilemedi");
        					newPasswordLabel.setVisible(false);
        	        		newPasswordRepLabel.setVisible(false);
        	        		newPasswordField.setVisible(false);
        	        		newPasswordRepField.setVisible(false);
        	        		changePasswordCommitButton.setVisible(false);
        	        		changePasswordButton.setVisible(true);
        	        		changePasswordCancel.setVisible(false);
        	        		passwordField.setEditable(false);
        				}
        			} 
        		}
        	}
        });
        changePasswordCommitButton.setForeground(Color.WHITE);
        changePasswordCommitButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        changePasswordCommitButton.setBackground(new Color(38, 38, 38));
        changePasswordCommitButton.setBounds(49, 645, 215, 30);
        changePasswordCommitButton.setVisible(false);
        userInfoPane.add(changePasswordCommitButton);
        
        changeMailCommit = new JButton("ONAYLA");
        changeMailCommit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String mail = mailField.getText();
        		
        		if(!mail.contains("@gmail.com")) {
        			JOptionPane.showMessageDialog(null, "e_Postanızı doğru formatta girmediniz");
        			telephoneField.setText("");
        		}
        		else if(mail.equals(user.getE_posta())) {
        			JOptionPane.showMessageDialog(null, "Girdiğiniz e_posta eski e_postanızdan farklı olmalıdır");
        			telephoneField.setText("");
        		} else {
        			boolean result = UserOperations.updateMail(user, mail);
        			if(result) {
            			JOptionPane.showMessageDialog(null, "e-Postanız değiştirildi");
            			user.setE_posta(mail);
            			changeMailCommit.setVisible(false);
                		changeMailButton.setVisible(true);
                		changeMailCancel.setVisible(false);
                		mailField.setEditable(false);
        			} else {
            			JOptionPane.showMessageDialog(null, "e-Postanız değiştirilemedi");
            			changeMailCommit.setVisible(false);
                		changeMailButton.setVisible(true);
                		changeMailCancel.setVisible(false);
                		mailField.setEditable(false);
        			}
        		}
        	}
        });
        changeMailCommit.setForeground(Color.WHITE);
        changeMailCommit.setFont(new Font("Tahoma", Font.PLAIN, 14));
        changeMailCommit.setBackground(new Color(38, 38, 38));
        changeMailCommit.setBounds(295, 409, 215, 30);
        changeMailCommit.setVisible(false);
        userInfoPane.add(changeMailCommit);
        
        changeTelNumberCommit = new JButton("ONAYLA");
        changeTelNumberCommit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String telNumber = telephoneField.getText();
        		
        		if(telNumber.length() < 11) {
        			JOptionPane.showMessageDialog(null, "Telefon numaranız 11 haneli olmalıdır");
        			telephoneField.setText("");
        		}
        		else if(telNumber.equals(user.getTelno())) {
        			JOptionPane.showMessageDialog(null, "Girdiğiniz telefon numarası eski numaranızdan farklı olmalıdır");
        			telephoneField.setText("");
        		} else {
        			boolean result = UserOperations.updateTelNumber(user, telNumber);
        			if(result) {
            			JOptionPane.showMessageDialog(null, "Telefon numaranız değiştirildi");
            			user.setTelno(telNumber);
            			changeTelNumberCommit.setVisible(false);
                		changeTelNumberButton.setVisible(true);
                		changeTelCancel.setVisible(false);
                		telephoneField.setEditable(false);
        			} else {
            			JOptionPane.showMessageDialog(null, "Telefon numaranız değiştirilemedi");
            			changeTelNumberCommit.setVisible(false);
                		changeTelNumberButton.setVisible(true);
                		changeTelCancel.setVisible(false);
                		telephoneField.setEditable(false);
        			}
        		}
        	}
        });
        changeTelNumberCommit.setForeground(Color.WHITE);
        changeTelNumberCommit.setFont(new Font("Tahoma", Font.PLAIN, 14));
        changeTelNumberCommit.setBackground(new Color(38, 38, 38));
        changeTelNumberCommit.setBounds(295, 302, 215, 30);
        changeTelNumberCommit.setVisible(false);
        userInfoPane.add(changeTelNumberCommit);
        
        changePasswordButton = new JButton("DEĞİŞTİR");
        changePasswordButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		newPasswordLabel.setVisible(true);
        		newPasswordRepLabel.setVisible(true);
        		newPasswordField.setVisible(true);
        		newPasswordRepField.setVisible(true);
        		changePasswordCommitButton.setVisible(true);
        		changePasswordButton.setVisible(false);
        		changePasswordCancel.setVisible(true);
        		passwordField.setEditable(true);
        		passwordField.setText("");
        	}
        });
        changePasswordButton.setForeground(Color.WHITE);
        changePasswordButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
        changePasswordButton.setBackground(new Color(38, 38, 38));
        changePasswordButton.setBounds(294, 516, 215, 30);
        userInfoPane.add(changePasswordButton);
        
        changePasswordCancel = new JButton("İPTAL ET");
        changePasswordCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		newPasswordField.setText("");
        		newPasswordRepField.setText("");
        		newPasswordLabel.setVisible(false);
        		newPasswordRepLabel.setVisible(false);
        		newPasswordField.setVisible(false);
        		newPasswordRepField.setVisible(false);
        		changePasswordCommitButton.setVisible(false);
        		changePasswordButton.setVisible(true);
        		changePasswordCancel.setVisible(false);
        		passwordField.setEditable(false);
        		passwordField.setText(user.getParola());
        	}
        });
        changePasswordCancel.setForeground(new Color(255, 255, 255));
        changePasswordCancel.setBackground(new Color(38, 38, 38));
        changePasswordCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        changePasswordCancel.setBounds(295, 645, 215, 30);
        changePasswordCancel.setVisible(false);
        userInfoPane.add(changePasswordCancel);
        
        changeMailCancel = new JButton("İPTAL ET");
        changeMailCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		changeMailCommit.setVisible(false);
        		changeMailButton.setVisible(true);
        		changeMailCancel.setVisible(false);
        		mailField.setText(user.getE_posta());
        		mailField.setEditable(false);
        	}
        });
        changeMailCancel.setForeground(new Color(255, 255, 255));
        changeMailCancel.setBackground(new Color(38, 38, 38));
        changeMailCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        changeMailCancel.setBounds(295, 450, 215, 30);
        changeMailCancel.setVisible(false);
        userInfoPane.add(changeMailCancel);
        
        changeTelCancel = new JButton("İPTAL ET");
        changeTelCancel.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		changeTelNumberCommit.setVisible(false);
        		changeTelNumberButton.setVisible(true);
        		changeTelCancel.setVisible(false);
        		telephoneField.setText(user.getTelno());
        		telephoneField.setEditable(false);
        	}
        });
        changeTelCancel.setForeground(new Color(255, 255, 255));
        changeTelCancel.setBackground(new Color(38, 38, 38));
        changeTelCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
        changeTelCancel.setBounds(295, 343, 215, 30);
        changeTelCancel.setVisible(false);
        userInfoPane.add(changeTelCancel);
        
        newPasswordField = new JPasswordField();
        newPasswordField.setBounds(49, 593, 215, 30);
        newPasswordField.setVisible(false);
        userInfoPane.add(newPasswordField);
        
        newPasswordRepField = new JPasswordField();
        newPasswordRepField.setBounds(295, 593, 215, 30);
        newPasswordRepField.setVisible(false);
        userInfoPane.add(newPasswordRepField);

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
                new TicketInfoPane(tickets[selectedRow], MainGUI.this).setVisible(true);
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
    
    public void setTicketsTableModel(){
    	tickets = TicketOperations.getTickets(user);
    	
    	Object[][] objects = new Object[tickets.length][4];
        
        for(int i = 0; i < tickets.length; i++) {
        	objects[i][0] = tickets[i].getFlight().getKalkisyeri();
        	objects[i][1] = tickets[i].getFlight().getVarisyeri();
        	objects[i][2] = tickets[i].getFlight().getKalkisTarihi();
        }
        
        String[] columnNames = {"Kalkış","Varış","Kalkış Tarihi","Biletimi Görüntüle"};
        DefaultTableModel model = new DefaultTableModel(objects, columnNames) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 3;
			}
        	
        };
        
        table.setModel(model);
        table.getColumn("Biletimi Görüntüle").setCellRenderer(new ButtonRenderer());
        table.getColumn("Biletimi Görüntüle").setCellEditor(new ButtonEditor(new JCheckBox()));
    }
}
