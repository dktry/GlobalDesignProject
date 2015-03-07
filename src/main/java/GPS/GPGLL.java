package GPS;

public class GPGLL {
	
	public double latitude;
	public String latitude_direction;
	public double longitude;
	public String longitude_direction;
	public String time;
	public String status;
	
	
	
	public GPGLL(double latitude, String latitude_direction, double longitude, String longitude_direction, String time, String status) {
		this.latitude = latitude;
		this.latitude_direction = latitude_direction;
		this.longitude = longitude;
		this.longitude_direction = longitude_direction;
		this.time = time;
		this.status = status;
	}
	
	public String toString() {
		return "latitude = " + this.latitude + "\n" + "longitude = " + this.longitude;
		
				
				
	}
	
	

}
