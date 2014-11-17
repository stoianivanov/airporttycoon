package airport;

public class AirportTycoonDemo {

	public static void main(String[] args) {
		
		FileConverter converter = new FileConverter();
		Airport airport = new Airport(converter);
		airport.runAirport();
	}
	
}
