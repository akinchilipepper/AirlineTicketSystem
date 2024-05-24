package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

import java.text.SimpleDateFormat;
import com.raven.datechooser.DateChooser;

import operations.UserOperations;

public class UserRegisterGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField nameField;
    private JTextField mailField;
    private JTextField surnameField;
    private JTextField telNumberField;
    private JTextField birthdateField;
    private JPasswordField passwordField;
    private JPasswordField passwordRepField;
    private JLabel nameLabel;
    private JLabel surnameLabel;
    private JLabel mailLabel;
    private JLabel telnumberLabel;
    private JLabel genderLabel;
    private JLabel passwordLabel;
    private JLabel passwordRepLabel;
    private JLabel birthdateLabel;
    private JLabel backLabel;
    private DateChooser chDate;
    private JButton registerButton;
    private ButtonGroup genderButtonGroup;
    private JRadioButton maleRadioButton;
    private JRadioButton femaleRadioButton;

    public UserRegisterGUI() {
    	Image appIcon = new ImageIcon(this.getClass().getResource("/images/appIconPlane.png")).getImage();
    	setIconImage(appIcon);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setTitle("Kayıt Ol");

        contentPane = new JPanel();
        contentPane.setBackground(new Color(38, 38, 38));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        nameLabel = new JLabel("AD");
        nameLabel.setForeground(new Color(255, 255, 255));
        nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
        nameLabel.setBounds(221, 136, 56, 21);
        contentPane.add(nameLabel);

        nameField = new JTextField();
        nameField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        nameField.setBounds(221, 161, 228, 27);
        contentPane.add(nameField);
        nameField.setColumns(10);

        surnameLabel = new JLabel("SOYAD");
        surnameLabel.setForeground(new Color(255, 255, 255));
        surnameLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
        surnameLabel.setBounds(522, 136, 94, 21);
        contentPane.add(surnameLabel);

        mailField = new JTextField();
        mailField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        mailField.setColumns(10);
        mailField.setBounds(221, 226, 228, 27);
        contentPane.add(mailField);

        mailLabel = new JLabel("E-POSTA");
        mailLabel.setForeground(new Color(255, 255, 255));
        mailLabel.setBackground(new Color(255, 255, 255));
        mailLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
        mailLabel.setBounds(221, 201, 151, 21);
        contentPane.add(mailLabel);

        surnameField = new JTextField();
        surnameField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        surnameField.setColumns(10);
        surnameField.setBounds(522, 161, 228, 27);
        contentPane.add(surnameField);

        telNumberField = new JTextField();
        telNumberField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        telNumberField.setColumns(10);
        telNumberField.setBounds(522, 226, 228, 27);
        contentPane.add(telNumberField);

        telnumberLabel = new JLabel("TELEFON NUMARA");
        telnumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
        telnumberLabel.setForeground(new Color(255, 255, 255));
        telnumberLabel.setBounds(522, 201, 193, 21);
        contentPane.add(telnumberLabel);

        passwordLabel = new JLabel("PAROLA");
        passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
        passwordLabel.setForeground(new Color(255, 255, 255));
        passwordLabel.setBounds(221, 266, 80, 21);
        contentPane.add(passwordLabel);

        passwordRepLabel = new JLabel("PAROLA TEKRAR");
        passwordRepLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
        passwordRepLabel.setForeground(new Color(255, 255, 255));
        passwordRepLabel.setBounds(522, 266, 154, 21);
        contentPane.add(passwordRepLabel);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        passwordField.setColumns(10);
        passwordField.setBounds(221, 291, 228, 27);
        contentPane.add(passwordField);

        genderLabel = new JLabel("CİNSİYET");
        genderLabel.setForeground(new Color(255, 255, 255));
        genderLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
        genderLabel.setBounds(522, 331, 86, 21);
        contentPane.add(genderLabel);

        birthdateLabel = new JLabel("DOĞUM TARİHİ");
        birthdateLabel.setForeground(Color.WHITE);
        birthdateLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
        birthdateLabel.setBounds(221, 331, 142, 21);
        contentPane.add(birthdateLabel);

        passwordRepField = new JPasswordField();
        passwordRepField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        passwordRepField.setColumns(10);
        passwordRepField.setBounds(522, 291, 228, 27);
        contentPane.add(passwordRepField);

        birthdateField = new JTextField();
        birthdateField.setFont(new Font("Tahoma", Font.PLAIN, 17));
        birthdateField.setColumns(10);
        birthdateField.setBounds(221, 356, 228, 27);

        chDate = new DateChooser();
        chDate.setTextField(birthdateField);
        chDate.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        contentPane.add(birthdateField);
        birthdateField.setText("");

        maleRadioButton = new JRadioButton("ERKEK");
        maleRadioButton.setForeground(new Color(255, 255, 255));
        maleRadioButton.setBackground(new Color(38, 38, 38));
        maleRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        maleRadioButton.setBounds(522, 358, 103, 23);
        contentPane.add(maleRadioButton);

        femaleRadioButton = new JRadioButton("KADIN");
        femaleRadioButton.setForeground(new Color(255, 255, 255));
        femaleRadioButton.setBackground(new Color(38, 38, 38));
        femaleRadioButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
        femaleRadioButton.setBounds(647, 359, 103, 21);
        contentPane.add(femaleRadioButton);

        genderButtonGroup = new ButtonGroup();
        genderButtonGroup.add(maleRadioButton);
        genderButtonGroup.add(femaleRadioButton);

        registerButton = new JButton("KAYDOL");
        registerButton.setBackground(new Color(255, 255, 255));
        registerButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        registerButton.setBounds(426, 437, 130, 27);
        registerButton.addActionListener((ActionEvent e) -> {
            String password = new String(passwordField.getPassword());
            String passwordRep = new String(passwordRepField.getPassword());
            if (nameField.getText().isEmpty() || surnameField.getText().isEmpty()
                    || telNumberField.getText().isEmpty() || genderButtonGroup.isSelected(null)
                    || birthdateField.getText().isEmpty() || mailField.getText().isEmpty()
                    || password.isEmpty() || passwordRep.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Lütfen tüm alanları doldurunuz!");
                return;
            }
            
            if (!password.equals(passwordRep)) {
                JOptionPane.showMessageDialog(null, "Parolalar eşleşmiyor! Lütfen kontrol ediniz");
                return;
            }
            
            if(password.length() < 8) {
            	JOptionPane.showMessageDialog(null, "Parola uzunluğu en az 8 karakter olmalı");
                return;
            }
            
            if(!mailField.getText().contains("@gmail.com") && !mailField.getText().contains("@icloud.com") && !mailField.getText().contains("@hotmail.com")) {
            	JOptionPane.showMessageDialog(null, "Lütfen e-Postanızı doğru formatta giriniz");
                return;
            }

            if (UserOperations.mailCheck(mailField.getText())) {
                JOptionPane.showMessageDialog(null, "Bu e-posta zaten kullanılıyor! Lütfen farklı bir e-posta deneyiniz");
                return;
            }

            boolean status = UserOperations.addUser(nameField.getText(), surnameField.getText(), telNumberField.getText(),
                    mailField.getText(), getGenderSelection(), birthdateField.getText(),
                    password);
            if (status) {
                JOptionPane.showMessageDialog(null, "Kullanıcı Eklendi");
            } else {
                JOptionPane.showMessageDialog(null, "Bir hata oluştu. Lütfen daha sonra tekrar deneyin");
            }
        });
        contentPane.add(registerButton);

        backLabel = new JLabel("GERİ");
        backLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
        backLabel.setForeground(new Color(255, 255, 255));
        backLabel.setBounds(28, 30, 48, 21);
        backLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new UserLoginGUI().setVisible(true);
            }

        });
        contentPane.add(backLabel);

        setContentPane(contentPane);
    }

    public String getGenderSelection() {
        if (maleRadioButton.isSelected()) {
            return "Erkek";
        } else if (femaleRadioButton.isSelected()) {
            return "Kadın";
        } else {
            return null;
        }
    }
}
