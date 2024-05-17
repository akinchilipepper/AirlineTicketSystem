package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;

import java.awt.Font;
import java.awt.Color;

import model.Ticket;
import operations.TicketOperations;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TicketInfoPane extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public TicketInfoPane(Ticket ticket, MainGUI mainGUI) {
    	setTitle("Biletim");

        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 750, 450);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setForeground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel flightInfoLabel = new JLabel("UÇUŞ DETAYLARI");
        flightInfoLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        flightInfoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        flightInfoLabel.setBounds(294, 47, 143, 22);
        contentPane.add(flightInfoLabel);
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 101, 732, 200);
        contentPane.add(panel);
        panel.setLayout(new GridLayout(0, 3, 0, 0));
        
        JPanel panel_1 = new JPanel();
        panel.add(panel_1);
        panel_1.setLayout(null);
        
                JLabel departureLabel = new JLabel("KALKIŞ");
                departureLabel.setBounds(96, 20, 52, 19);
                panel_1.add(departureLabel);
                departureLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
                departureLabel.setHorizontalAlignment(SwingConstants.CENTER);
                
                        JLabel originAirportLabel = new JLabel("");
                        originAirportLabel.setBounds(47, 80, 150, 20);
                        panel_1.add(originAirportLabel);
                        originAirportLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        originAirportLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                        originAirportLabel.setText(ticket.getFlight().getKalkisyeri());
                        
                                JLabel departureTimeLabel = new JLabel("");
                                departureTimeLabel.setBounds(47, 110, 150, 20);
                                panel_1.add(departureTimeLabel);
                                departureTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                departureTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                                departureTimeLabel.setText(ticket.getFlight().getKalkisTarihi() + " " + ticket.getFlight().getKalkisZamani());
                                
                                JSeparator separator = new JSeparator();
                                separator.setBackground(new Color(0, 0, 0));
                                separator.setForeground(new Color(0, 0, 0));
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
                        arrivalAirportLabel.setBounds(47, 80, 150, 20);
                        panel_2.add(arrivalAirportLabel);
                        arrivalAirportLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        arrivalAirportLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                        arrivalAirportLabel.setText(ticket.getFlight().getVarisyeri());
                        
                                JLabel arrivalTimeLabel = new JLabel("");
                                arrivalTimeLabel.setBounds(47, 110, 150, 20);
                                panel_2.add(arrivalTimeLabel);
                                arrivalTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                arrivalTimeLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                                arrivalTimeLabel.setText(ticket.getFlight().getVarisTarihi() + " " + ticket.getFlight().getVarisZamani());
                                
                                JSeparator separator_1 = new JSeparator();
                                separator_1.setBackground(new Color(0, 0, 0));
                                separator_1.setBounds(242, 0, 2, 200);
                                panel_2.add(separator_1);
                                separator_1.setOrientation(SwingConstants.VERTICAL);
                                separator_1.setForeground(Color.BLACK);
        
        JPanel panel_3 = new JPanel();
        panel.add(panel_3);
        panel_3.setLayout(null);
        
                JLabel flightLabel = new JLabel("UÇUŞ");
                flightLabel.setBounds(101, 20, 41, 19);
                panel_3.add(flightLabel);
                flightLabel.setHorizontalAlignment(SwingConstants.CENTER);
                flightLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
                
                        JLabel flightNumberLabel = new JLabel("UÇUŞ NO : ");
                        flightNumberLabel.setBounds(32, 80, 180, 20);
                        panel_3.add(flightNumberLabel);
                        flightNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
                        flightNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                        flightNumberLabel.setText(flightNumberLabel.getText() + " " + ticket.getFlight().getUcusNo());
                        
                                JLabel planeNameLabel = new JLabel("UÇAK MODELİ : ");
                                planeNameLabel.setBounds(12, 110, 220, 20);
                                panel_3.add(planeNameLabel);
                                planeNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
                                planeNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
                                planeNameLabel.setText(planeNameLabel.getText() + " " + ticket.getFlight().getUcak());
                                
                                JButton ticketDeleteButton = new JButton("BİLETİMİ İPTAL ET");
                                ticketDeleteButton.addActionListener(new ActionListener() {
                                	public void actionPerformed(ActionEvent e) {
                                		int choice = JOptionPane.showConfirmDialog(null, "Biletinizi iptal etmek istediğinize emin misiniz?");
                                		if(choice == 0) {
                                			TicketOperations.deleteTicket(ticket);
                                			mainGUI.setTicketsTableModel();
                                			mainGUI.validate();
                                			mainGUI.repaint();
                                			JOptionPane.showMessageDialog(null, "Biletiniz silindi");
                                			dispose();
                                		}
                                	}
                                });
                                ticketDeleteButton.setForeground(new Color(255, 255, 255));
                                ticketDeleteButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
                                ticketDeleteButton.setBackground(new Color(255, 0, 0));
                                ticketDeleteButton.setBounds(493, 328, 229, 41);
                                contentPane.add(ticketDeleteButton);
                                
                                JLabel lblPnr = new JLabel("PNR KODU :");
                                lblPnr.setFont(new Font("Tahoma", Font.PLAIN, 15));
                                lblPnr.setHorizontalAlignment(SwingConstants.CENTER);
                                lblPnr.setBounds(248, 336, 235, 29);
                                lblPnr.setText(lblPnr.getText() + " " + ticket.getPnr());
                                contentPane.add(lblPnr);
                                
                                JLabel lblKoltuk = new JLabel("KOLTUK : ");
                                lblKoltuk.setFont(new Font("Tahoma", Font.PLAIN, 15));
                                lblKoltuk.setHorizontalAlignment(SwingConstants.CENTER);
                                lblKoltuk.setBounds(10, 336, 235, 29);
                                lblKoltuk.setText(lblKoltuk.getText() + " " + ticket.getSeat().getKoltuknumarasi());
                                contentPane.add(lblKoltuk);
        
    }
}