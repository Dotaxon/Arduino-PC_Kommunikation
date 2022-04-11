import com.fazecast.jSerialComm.*;
import java.io.IOException;
import java.util.Scanner;

public class ArduinoToJava {
	public static void main(String[] args) throws IOException, InterruptedException{
		
		SerialPort availablePorts[] = SerialPort.getCommPorts();	//array with all available Ports listed
		SerialPort port;			//used Port for communication
		int selectedPort;		//User selectedPort index of availablePorts[]


		Scanner scan = new Scanner(System.in);
		Scanner data; //reads Data from port;
		
		
		
		// Port selection
		System.out.println("Select a Port");
		
		for (int i = 1; i < availablePorts.length; i++) {			
			System.out.println(i + ". " + availablePorts[i].getSystemPortName()); //.getSystemPort.. gibt z.b. COM5 zurück
		}	
		
		selectedPort = scan.nextInt();
		System.out.println("You selected port: " + selectedPort + "(" + availablePorts[selectedPort].getSystemPortName() + ")");
		
		
		//Port setup
		port = SerialPort.getCommPort(availablePorts[selectedPort].getSystemPortName());
		port.setComPortParameters(9600, 8, 1, 0);	//Default for Arduino
		port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);
		
		data = new Scanner(port.getInputStream());  //setups Scanner
		
		
		//Open Port
		if(port.openPort()) { 
			Thread.sleep(4000); //openPort() needs time to open Port
			System.out.println("\n\nPort is open\n\n");
		}else {
			System.out.println("Failed to Open Port");
			System.exit(0);
		}
		
		//Receive Message		
		//receiving Loop
		while(data.hasNextLine()) {
			System.out.println(data.nextLine());

		}
		System.out.println("ende");
		
		
		if(!port.closePort()) {
			System.out.println("Failed to close Port");
		}
	}
}
