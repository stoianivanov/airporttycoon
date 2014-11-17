package airport;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

	public class FileConverter {

		private static final String FILE_NAME_INPUT = "src/trafficSheet.txt";
		private static final String FILE_NAME_OUTPUT = "src/output.txt";
		private Queue<String> messages ;
		private AirportInfo airportInfo;
		private PrintWriter writer;
		
		public FileConverter() {
				
				try {
					InputStream file= new FileInputStream(FILE_NAME_INPUT);
					Scanner sc = new Scanner(file);
					
					Integer runwayCapacity = sc.nextInt();
					Integer airplanesCapacity = sc.nextInt();
					Integer movementTime = sc.nextInt();
					
					airportInfo= new AirportInfo(runwayCapacity, airplanesCapacity, movementTime);
					
					messages = new LinkedList<String>();
					String str1 = sc.nextLine();
					while(sc.hasNext()){
						String str = sc.nextLine();
						initQueue(str);
					}
					writer = new PrintWriter(FILE_NAME_OUTPUT);
					
					file.close();
					sc.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
		}
		
		public void fileOutput(String s){
			
				writer.println(s);
				writer.flush();
				
			
		}
		private void initQueue(String message){
			
			messages.add(message);
		}
		public Queue<String> getMessage(){
			return messages;
		}
		public AirportInfo getAirportInfo(){		
			return airportInfo;
		}
		class AirportInfo {
			private Integer runwayCapacity ; 
			private Integer airplanesCapacity;
			private Integer movementTime; 
			
			public Integer getRunwayCapacity(){
				return runwayCapacity;
			}
			public Integer getAirplaneCapacity(){
				return airplanesCapacity;
			}
			public void setRunwayCapacity(Integer runwayCapacity){
				this.runwayCapacity=runwayCapacity;
			}
			public void setAirplanesCapacity(Integer airplanesCapacity){
				this.airplanesCapacity=airplanesCapacity;
			}
			public Integer getMovementTime() {
				return movementTime;
			}
			public void setMovementTime(Integer movementTime) {
				this.movementTime = movementTime;
			}
			public AirportInfo(Integer runwayCapacity, Integer airplanesCapacity, Integer movementTime) {
				this.runwayCapacity = runwayCapacity;
				this.airplanesCapacity = airplanesCapacity;
				this.movementTime = movementTime;
			}
		}
	}
