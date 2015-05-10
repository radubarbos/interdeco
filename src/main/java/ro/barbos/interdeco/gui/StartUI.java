package ro.barbos.interdeco.gui;

import javax.swing.*;

/**
 * Created by radu on 4/23/2015.
 */
public class StartUI {
    /**
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {


            public void run() {
                new MainFrame();
            }
        });

    }
}
