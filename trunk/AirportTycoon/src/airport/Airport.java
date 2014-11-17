package airport;

import java.util.Map;
import java.util.TreeMap;


public class Airport {

	private static int MOVEMENT_TIME;
	private static int RUNWAYS_CAPACITY;
	private static int AIRPLANES_CAPACITY;
	private FileConverter data;
	private int currentAirportCapacity = 0;
	private String output;
	
	private Map<Integer, Integer> runways;
	
	Airport(FileConverter data) {
		RUNWAYS_CAPACITY = data.getAirportInfo().getRunwayCapacity();
		AIRPLANES_CAPACITY = data.getAirportInfo().getAirplaneCapacity();
		MOVEMENT_TIME = data.getAirportInfo().getMovementTime();
		
		this.data = data;
		runways = new TreeMap<Integer, Integer>(); 
		
	}
	
	public void runAirport(){
		
		initRunways();

		int key = keyOfMinValue();

		for (String s : data.getMessage()) {

			if(s.contains("landing")){
				int airplaneNumber = readAirplaneId(s);
				int airplaneFuel = readAirplaneFuel(s);
				
				if(airplaneFuel < runways.get(key) || currentAirportCapacity == AIRPLANES_CAPACITY){
					output = String.format("Airplane %d is redirected.", airplaneNumber);
					elapsedOneMinute();
				}else{
					output = String.format("Airplane %d lands on runway %d", airplaneNumber, key);
					minValueElapsedMinutes(runways.get(key));
					runways.put(key, MOVEMENT_TIME);
					
					elapsedOneMinute();
					++currentAirportCapacity;
				}
				
			}else if(s.contains("taking")){
				int airplaneNumber = readAirplaneId(s);
				output = String.format("Airplane %d takes off from runway %d.", airplaneNumber, key);
				runways.put(key, MOVEMENT_TIME);
				--currentAirportCapacity;
				elapsedOneMinute();
				
			}else{
				
				int closedRunwayNumber = readClosedRunwayId(s);
				runways.put(closedRunwayNumber, readTimeOfClosedRunway(s));
				output = String.format("Runway %d is closed for %d minutes.", key, runways.get(closedRunwayNumber));
				elapsedOneMinute();
			}
			
			data.fileOutput(output);
			key = keyOfMinValue();
		}
		System.out.println("Done.");
	}
	
	private void initRunways(){
		
		for(int i = 0; i < RUNWAYS_CAPACITY; i++){
			runways.put(i, 0);
		}
	}
	
	private int keyOfMinValue(){
		
		int currentKey = 0;
		int currentKeyMinValue = runways.get(currentKey);
		
		for(int i = 0; i < runways.size(); i++){
			
			if(runways.get(i) < currentKeyMinValue && runways.get(i) >= 0){
				currentKey = i;
				currentKeyMinValue = runways.get(currentKey);
			}
		}
		return currentKey;
	}
	
	private int readAirplaneId(String s){
		
		for(int i = 0; i < s.length(); i++){
			
			if(s.charAt(i) == ' '){
				String result = s.substring(0, i);
				return Integer.parseInt(result);
			}
		}
		return 0;
	}
	
	private int readAirplaneFuel(String s){
		
		for(int i = s.length() - 1; i >=0; --i){
			
			if(s.charAt(i) == ' '){
				
				String result = s.substring(i + 1);
				return Integer.parseInt(result);
			}
		}
		return 0;
	}
	
	private int readClosedRunwayId(String s){
		for(int i = 7; i < s.length(); i++){
			
			if(s.charAt(i) == ' '){
				String result = s.substring(7, i);
				return Integer.parseInt(result);
			}
		}
		return 0;
	}
	
	private int readTimeOfClosedRunway(String s){
		
		for(int i = s.length() - 1; i >=0; --i){
			
			if(s.charAt(i) == ' '){
				
				String result = s.substring(i + 1);
				return Integer.parseInt(result);
			}
		}
		return 0;
	}
	
	private void elapsedOneMinute(){
		
		for(int i = 0; i < runways.size(); ++i){

			if(runways.get(i) > 0){
				runways.put(i, runways.get(i) - 1);
			}
		}
	}
	
	private void minValueElapsedMinutes(int a){
		
		for(int i = 0; i < runways.size(); ++i){

			if(runways.get(i) > 0){
				runways.put(i, runways.get(i) - a);
			}
		}
	}
}
