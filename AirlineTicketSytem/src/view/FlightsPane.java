package view;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.Color;

import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import model.Flight;
import model.User;

public class FlightsPane extends JFrame {

    private static final long serialVersionUID = 1L;
    private JTable table;
    private JPanel contentPane;
    private Flight[] flights;
    private User user;
    private MainGUI mainGUI;

    public FlightsPane(Flight[] flights, User user, MainGUI mainGUI) {
        this.flights = flights;
        this.user = user;
        this.mainGUI = mainGUI;
        Image appIcon = new ImageIcon(this.getClass().getResource("/images/appIconPlane.png")).getImage();
    	setIconImage(appIcon);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 950, 600);
        setTitle("Uçuşlar");
        
        contentPane = new JPanel();
        contentPane.setBackground(new Color(38, 38, 38));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);

        Object[][] objects = new Object[flights.length][5];

        for (int i = 0; i < flights.length; i++) {
            objects[i][0] = flights[i].getKalkisyeri();
            objects[i][1] = flights[i].getVarisyeri();
            objects[i][2] = flights[i].getKalkisTarihi();
            objects[i][3] = flights[i].getKalkisZamani();
        }

        String[] columnNames = {"Kalkış", "Varış", "Uçuş Tarihi", "Kalkış Saaati", "Bilet"};
        DefaultTableModel model = new DefaultTableModel(objects, columnNames) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }
        };

        table = new JTable(model);
        table.getColumn("Bilet").setCellRenderer(new ButtonRenderer());
        table.getColumn("Bilet").setCellEditor(new ButtonEditor(new JCheckBox()));
        table.setFont(new Font("Tahoma", Font.PLAIN, 14));
        table.setRowHeight(30);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 17));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 127, 912, 416);
        contentPane.add(scrollPane);

        JLabel flightsLabel = new JLabel("UÇUŞLAR");
        flightsLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
        flightsLabel.setForeground(new Color(255, 255, 255));
        flightsLabel.setBounds(416, 50, 100, 29);
        contentPane.add(flightsLabel);

        setContentPane(contentPane);
    }

    class ButtonRenderer extends JButton implements TableCellRenderer {

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
            setText("Bilet Al");
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {

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
                new TicketBookingPane(flights[selectedRow], user, mainGUI).setVisible(true);
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
