package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

public class DesignerInfoGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public DesignerInfoGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 300);
		setLocationRelativeTo(null);
		
		Image appIcon = new ImageIcon(this.getClass().getResource("/images/appIconPlane.png")).getImage();
    	setIconImage(appIcon);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Adı: Muhammed Musa");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(27, 51, 175, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblSoyadAkn = new JLabel("Soyadı: Akın");
		lblSoyadAkn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSoyadAkn.setBounds(27, 81, 175, 30);
		contentPane.add(lblSoyadAkn);
		
		JLabel lblNewLabel_3 = new JLabel("Numarası: 22612014");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(27, 111, 175, 30);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Bölümü: Bilgisayar Programcılığı");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(27, 141, 200, 30);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_1 = new JLabel("e-Postası: musaakin890@gmail.com");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(27, 171, 233, 30);
		contentPane.add(lblNewLabel_1);
		
		ImageIcon originalIcon = new ImageIcon(UserLoginGUI.class.getResource("/images/student.png"));

        Image originalImage = originalIcon.getImage();
        Image resizedImage = originalImage.getScaledInstance(180, 180, Image.SCALE_SMOOTH);

        ImageIcon resizedIcon = new ImageIcon(resizedImage);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(resizedIcon);
		lblNewLabel_2.setBounds(336, 39, 175, 176);
		contentPane.add(lblNewLabel_2);
	}
}
