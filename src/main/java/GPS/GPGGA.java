package GPS;


public class GPGGA {
	
	public String time;
	public float latitude;
	public String latitude_direction;
	public float longitude;
	public String longitude_direction;
	public int quality;
	
	
	public GPGGA(String time, float latitude, String latitude_direction, float longitude, String longitude_direction, int quality) {
		this.time = time;
		this.latitude = latitude;
		this.latitude_direction = latitude_direction;
		this.longitude = longitude;
		this.longitude_direction = longitude_direction;
		this.quality = quality;
	}
	
	public String toString() {
		return "time = " + this.time;
	}
	
}
