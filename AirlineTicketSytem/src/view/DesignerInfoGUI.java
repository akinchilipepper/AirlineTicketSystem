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
		lblNewLabel.setBounds(179, 52, 175, 30);
		contentPane.add(lblNewLabel);
		
		JLabel lblSoyadAkn = new JLabel("Soyadı: Akın");
		lblSoyadAkn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSoyadAkn.setBounds(179, 92, 175, 30);
		contentPane.add(lblSoyadAkn);
		
		JLabel lblNewLabel_3 = new JLabel("Numarası: 22612014");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setBounds(179, 132, 175, 30);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Bölümü: Bilgisayar Programcılığı");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_4.setBounds(179, 172, 200, 30);
		contentPane.add(lblNewLabel_4);
	}
}
