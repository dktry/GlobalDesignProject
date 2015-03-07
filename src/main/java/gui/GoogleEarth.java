package gui;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserFactory;

import javax.swing.*;
import java.awt.*;


public class GoogleEarth {

    public static void run(){
        final Browser browser = BrowserFactory.create();
        
        JPanel toolBar = new JPanel();


        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.add(browser.getView().getComponent(), BorderLayout.CENTER);
        frame.add(toolBar, BorderLayout.NORTH);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        final String dir = System.getProperty("user.dir");
        browser.loadURL("file://" + dir + "/src/main/static/templates/earth.html");

    }


}
