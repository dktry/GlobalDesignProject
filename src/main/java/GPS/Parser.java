package GPS;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class Parser {
	private List<GPGGA> gpgga = new ArrayList<GPGGA>();
	private List<GPGLL> gpgll = new ArrayList<GPGLL>();


	public List<GPGLL> parser() {
		Charset charset = Charset.forName("US-ASCII");
		Path filePath = Paths.get(System.getProperty("user.dir"), "/src/main/data/gps_data");
		try (BufferedReader reader = Files.newBufferedReader(filePath, charset)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		    	if (line.startsWith("$GPGGA")) {
		    		GPGGA current_gpggaData = Parser.gpggaParser(line);
		    		gpgga.add(current_gpggaData);
		    	}
		    	else if (line.startsWith("$GPGLL")) {
		    		GPGLL current_gpgllData = Parser.gpgllParser(line);
		    		System.out.println(current_gpgllData);
		    		gpgll.add(current_gpgllData);
		    	}
		        
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		return gpgll;
		
	}
	
	
	public static GPGGA gpggaParser(String line) {
		String[] parts = line.split(",");
		return new GPGGA(parts[1],
						Float.parseFloat(parts[2]), 
						parts[3], 
						Float.parseFloat(parts[4]), 
						parts[5], 
						Integer.parseInt(parts[6]));
	}
	
	
	public static GPGLL gpgllParser(String line) {
		String[] parts = line.split(",");
		return new GPGLL(Double.parseDouble(parts[1]),
						parts[2], 
						Double.parseDouble(parts[3]), 
						parts[4], 
						parts[5],
						parts[6]);
		
	}
	
	
	public static void main(String[] args) {
		Parser parser = new Parser();
		parser.parser();
	}
	
	

}
