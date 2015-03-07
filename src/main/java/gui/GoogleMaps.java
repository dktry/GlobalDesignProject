package gui;

import GPS.GPGLL;
import GPS.Parser;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.BrowserFactory;
import connect.USBComm;
import gnu.io.PortInUseException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class GoogleMaps{
	
	static USBComm usbcomm = null;
	static Browser browser;

    private static JFrame frame;

    public static final int MIN_ZOOM = 0;
    public static final int MAX_ZOOM = 21;

    /**
     * In map.html file default zoom value is set to 4.
     */
    private static int zoomValue = 4;


    public static void updateJSFor(GPGLL gpgll_data) {
        String marker_meta = "['GPGLL', "+gpgll_data.latitude/100.0+", "+gpgll_data.longitude/100.0+"]";
        String marker_info = "['<h3>Received Time</h3>" +
                "<p>"+ gpgll_data.time +"</p>']";

        System.out.println(marker_meta);
        System.out.println(marker_info);

        browser.executeJavaScript("new updatePositionsFor("+ marker_meta + ", "+ marker_info +")");
    }


    public static void start() {
        browser = BrowserFactory.create();

        JButton zoomInButton = new JButton("Zoom In");
        zoomInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (zoomValue < MAX_ZOOM) {
                    browser.executeJavaScript("map.setZoom(" + ++zoomValue + ")");
                }
            }
        });

        JButton zoomOutButton = new JButton("Zoom Out");
        zoomOutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (zoomValue > MIN_ZOOM) {
                    browser.executeJavaScript("map.setZoom(" + --zoomValue + ")");
                }
            }
        });
        
        
        JButton connectButton = new JButton(">>>connect<<<");
        connectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {   
//            	try {
//					usbcomm = new USBComm();
//				} catch (IOException | PortInUseException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//            	if (usbcomm != null) {
//                    Runnable run = new Runnable() {
//                        @Override
//                        public void run() {
//                            while (true){
//                                try {
//                                    String line = usbcomm.getPortReader().readLine();
//                                    System.out.println(line);
//                                    GPGLL gpgllData = Parser.gpgllParser(line);
//                                    GoogleMaps.updateJSFor(gpgllData);
//                                } catch(IOException e) {
//                                    System.out.println("readline error");
//                                }
//                            }
//                        }
//                    };
//                    new Thread(run).start();
//            	}


                Runnable run = new Runnable() {
                    @Override
                    public void run() {
                        Parser parser = new Parser();
                        ArrayList<GPGLL> gpgll = (ArrayList<GPGLL>) parser.parser();
                        for (GPGLL gpgllData : gpgll) {
                            String marker_meta = "['GPGLL', "+gpgllData.latitude/100.0+", "+gpgllData.longitude/100.0+"]";
                            String marker_info = "['<h3>Received Time</h3>" +
                                    "<p>"+ gpgllData.time +"</p>']";

                            System.out.println(marker_meta);
                            System.out.println(marker_info);

                            browser.executeJavaScript("new updatePositionsFor("+ marker_meta + ", "+ marker_info +")");

                            try {
                                Thread.sleep(1000);                 //1000 milliseconds is one second.
                            } catch(InterruptedException ex) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
                };
                new Thread(run).start();
            }
        });

        JPanel toolBar = new JPanel();
        toolBar.add(zoomInButton);
        toolBar.add(zoomOutButton);
        toolBar.add(connectButton);

        frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.add(browser.getView().getComponent(), BorderLayout.CENTER);
        frame.add(toolBar, BorderLayout.NORTH);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //browser.loadURL("http://maps.google.com");

        final String dir = System.getProperty("user.dir");
        browser.loadURL("file://" + dir + "/src/main/static/templates/map.html");
    }
}
