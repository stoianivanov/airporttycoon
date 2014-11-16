package airport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Queue;
import java.util.Scanner;

public class FileConvert {

	public class FileConverter {

		private static final String FILE_NAME="trafficSheet";
		private Queue<String> messages ;
		private AirportInfo airportIfno;
		
		public FileConverter() {
				
				try {
					InputStream file= new FileInputStream(FILE_NAME);
					Scanner sc= new Scanner(file);
					Integer runwayCapacity=sc.nextInt();
					Integer airplanesCapacity=sc.nextInt();
					airportIfno= new AirportInfo(runwayCapacity, airplanesCapacity);
					while(sc.hasNext()){
						String str=sc.nextLine();
						initQueue(str);
					}
					file.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
		}
		
		private  void initQueue(String message){
			
			messages.add(message);
		}
		public Queue<String> getMessage(){
			return messages;
		}
		public AirportInfo getAirportInfo(){		
			return airportIfno;
		}
		class AirportInfo {
			private Integer runwayCapacity ; 
			private Integer airplanesCapacity;
			
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
			public AirportInfo(Integer runwayCapacity, Integer airplanesCapacity) {
				this.runwayCapacity = runwayCapacity;
				this.airplanesCapacity = airplanesCapacity;
			}
		}
	}
}
