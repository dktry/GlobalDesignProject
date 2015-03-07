package connect;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;

public class USBComm {
	SerialPort serialPort;
	public BufferedReader portReader;
	String port = "COM4"; //place the right COM port here, OS dependent
	
	public USBComm() throws IOException, PortInUseException {
		
		//Check that the USB port exists and is recognized:
		Enumeration<?> portList = CommPortIdentifier.getPortIdentifiers();
		boolean portFound = false;
		CommPortIdentifier portId = null;
		while (portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				//log.info(portId.getName());
				System.out.println(portId.getName());
				if (portId.getName().equals(port)) {
					System.out.println("Found port: " + port);
					portFound = true;
					break;
				} 
			} 
		} 
		if (!portFound) 
			throw new IOException("port " + port + " not found.");
		System.out.println("USB port opening...");
		serialPort = (SerialPort) portId.open("USBCommunicator", 2000);
		portReader = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
		/*while (true){
			try {
				String line = portReader.readLine();
				System.out.println(line);
			} catch(IOException e) {  
				System.out.println("readline error");
			}
		}*/
	}


    public BufferedReader getPortReader() {
        return portReader;
    }

	
	public static void main(String[] args) {
		try {
			USBComm usbcomm = new USBComm();
		} catch (IOException | PortInUseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}