package system;

import javax.swing.SwingUtilities;

import view.UserLoginGUI;

public class Main {

    public static void main(String[] args) {
    	System.setProperty("sun.java2d.uiScale", "1.0");
    	SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserLoginGUI().setVisible(true);
            }
        });
    }
}