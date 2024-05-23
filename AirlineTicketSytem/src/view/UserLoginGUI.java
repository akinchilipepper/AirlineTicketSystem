package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.User;
import operations.UserOperations;

public class UserLoginGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JPanel panelRight;
    private JLabel dateLabel;
    private JLabel timeLabel;
    private JLabel attemptLabel;
    private JLabel emailLabel;
    private JTextField emailField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton confirmButton;
    private JPanel panelLeft;
    private int attempts = 3;

    public UserLoginGUI() {
    	Image appIcon = new ImageIcon(this.getClass().getResource("/images/appIconPlane.png")).getImage();
    	setIconImage(appIcon);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setTitle("Flaai");

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        panelRight = new JPanel();
        panelRight.setForeground(new Color(0, 0, 0));
        panelRight.setBounds(450, 0, 532, 553);
        panelRight.setBackground(new Color(38, 38, 38));
        panelRight.setLayout(null);

        dateLabel = new JLabel();
        dateLabel.setBackground(new Color(240, 240, 240));
        dateLabel.setHorizontalAlignment(SwingConstants.LEFT);
        dateLabel.setForeground(new Color(225, 222, 218));
        dateLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        dateLabel.setBounds(20, 30, 152, 40);
        panelRight.add(dateLabel);

        timeLabel = new JLabel();
        timeLabel.setBackground(new Color(240, 240, 240));
        timeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        timeLabel.setForeground(new Color(225, 222, 218));
        timeLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        timeLabel.setBounds(360, 30, 152, 40);
        panelRight.add(timeLabel);

        emailLabel = new JLabel("E-POSTA");
        emailLabel.setBackground(new Color(255, 255, 255));
        emailLabel.setForeground(new Color(255, 255, 255));
        emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        emailLabel.setBounds(226, 135, 80, 22);
        panelRight.add(emailLabel);

        emailField = new JTextField();
        emailField.setBackground(new Color(255, 255, 255));
        emailField.setForeground(new Color(0, 0, 0));
        emailField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        emailField.setBounds(116, 167, 300, 32);
        panelRight.add(emailField);

        passwordLabel = new JLabel("PAROLA");
        passwordLabel.setBackground(new Color(255, 255, 255));
        passwordLabel.setForeground(new Color(255, 255, 255));
        passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        passwordLabel.setBounds(229, 224, 74, 22);
        panelRight.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(255, 255, 255));
        passwordField.setForeground(new Color(0, 0, 0));
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        passwordField.setBounds(116, 256, 300, 32);
        panelRight.add(passwordField);

        confirmButton = new JButton("GİRİŞ");
        confirmButton.setForeground(new Color(0, 0, 0));
        confirmButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
        confirmButton.setBackground(new Color(255, 255, 255));
        confirmButton.setBounds(153, 340, 225, 50);

        confirmButton.addActionListener((ActionEvent e) -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (email.equals("") && password.equals("")) {
                JOptionPane.showMessageDialog(null, "E-Posta ve Parola alanı boş olamaz!");
            } else if (email.equals("")) {
                JOptionPane.showMessageDialog(null, "E-Posta alanı boş olamaz!");
            } else if (password.equals("")) {
                JOptionPane.showMessageDialog(null, "Parola alanı boş olamaz!");
            } else if(!email.contains("@gmail.com")) {
            	JOptionPane.showMessageDialog(null, "e-Postanızı doğru formatta giriniz");
            	emailField.setText("");
                passwordField.setText("");
            } else {
                Boolean isLoginSuccesful = UserOperations.login(email, password);
                if (isLoginSuccesful) {
                    JOptionPane.showMessageDialog(null, "Hoşgeldiniz");
                    User user = UserOperations.setUser(email); 
                    new MainGUI(user).setVisible(true);
                    dispose();
                } else {
                    attempts--;
                    if (attempts == 0) {
                        attemptLabel.setText(attempts + " DENEME HAKKINIZ KALDI");
                        JOptionPane.showMessageDialog(null, "Sistemden Çıkılıyor...");
                        dispose();
                    } else {
                        emailField.setText("");
                        passwordField.setText("");
                        JOptionPane.showMessageDialog(contentPane, "E-Posta Veya Parola Hatalı!", "Bilgilendirme",
                                JOptionPane.PLAIN_MESSAGE);
                        attemptLabel.setText(attempts + " DENEME HAKKINIZ KALDI");
                    }
                }
            }
        });

        getRootPane().setDefaultButton(confirmButton);
        panelRight.add(confirmButton);

        attemptLabel = new JLabel("3 DENEME HAKKINIZ KALDI");
        attemptLabel.setBackground(new Color(255, 255, 255));
        attemptLabel.setForeground(new Color(255, 255, 255));
        attemptLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        attemptLabel.setBounds(153, 409, 227, 22);
        panelRight.add(attemptLabel);

        panelLeft = new JPanel();
        panelLeft.setBackground(new Color(56, 56, 56));
        panelLeft.setBounds(0, 0, 450, 553);
        panelLeft.setLayout(null);

        ImageIcon originalIcon = new ImageIcon(UserLoginGUI.class.getResource("/images/plane.png"));

        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(225, 225, Image.SCALE_SMOOTH);

        ImageIcon resizedIcon = new ImageIcon(resizedImage);

        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(resizedIcon);
        imageLabel.setBackground(new Color(255, 255, 255));
        imageLabel.setBounds(112, 164, 225, 225);
        panelLeft.add(imageLabel);

        JLabel registerLabel = new JLabel("Hesap Oluştur");
        registerLabel.setForeground(new Color(255, 255, 255));
        registerLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        registerLabel.setBounds(211, 521, 111, 22);
        registerLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new UserRegisterGUI().setVisible(true);
            }

        });

        panelRight.add(registerLabel);

        JLabel lblCopyright = new JLabel("Copyright © 2024 Tüm Hakları Saklıdır");
        lblCopyright.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCopyright.setForeground(new Color(255, 255, 255));
        lblCopyright.setHorizontalAlignment(SwingConstants.CENTER);
        lblCopyright.setBounds(85, 523, 279, 20);
        panelLeft.add(lblCopyright);

        contentPane.add(panelLeft);
        contentPane.add(panelRight);

        setContentPane(contentPane);

        clock();
    }

    private void clock() {
        Thread clock = new Thread() {
            @Override
            public void run() {
                try {
                    while (true) {
                        LocalDate d = LocalDate.now();
                        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                        String tarih = d.format(dateFormat);

                        LocalTime t = LocalTime.now();
                        DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
                        String saat = t.format(timeFormat);

                        dateLabel.setText(tarih);
                        timeLabel.setText(saat);
                        sleep(1000);
                    }
                } catch (InterruptedException ex) {
                    Logger.getLogger(UserLoginGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        clock.start();
    }
}
