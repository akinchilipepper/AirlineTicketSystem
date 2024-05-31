package system;

import javax.swing.SwingUtilities;

import view.UserLoginGUI;

public class Main {

    public static void main(String[] args) {
    	System.setProperty("sun.java2d.uiScale", "1.0"); // Delete this row if your system scale is %100
    	SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserLoginGUI().setVisible(true);
            }
        });
    }
}