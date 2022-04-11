import com.fazecast.jSerialComm.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UserInputToLCD {
	public static void main(String[] args) throws IOException, InterruptedException{
		
		SerialPort availablePorts[] = SerialPort.getCommPorts();	//array with all available Ports listed
		SerialPort port;			//used Port for communication
		int selectedPort;		//User selectedPort index of availablePorts[]
		String userMsg;	//Message
		byte userMsgByteArray[];
		Scanner scan = new Scanner(System.in);
		Scanner s = new Scanner(System.in);
		
		
		
		// Port selection
		System.out.println("Select a Port");
		
		for (int i = 1; i < availablePorts.length; i++) {			
			System.out.println(i + ". " + availablePorts[i].getSystemPortName());
		}	
		
		selectedPort = scan.nextInt();
		System.out.println("You selected port: " + selectedPort + "(" + availablePorts[selectedPort].getSystemPortName() + ")");
		
		//Port setup
		port = SerialPort.getCommPort(availablePorts[selectedPort].getSystemPortName());
		port.setComPortParameters(9600, 8, 1, 0);	//Default for Arduino
		port.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
		
		
		//Send messages
		if(port.openPort()) { 
			Thread.sleep(4000); //openPort() needs time to open Port
			System.out.println("\n\nPort is open\n\n");
			
			while(true)	{
				
				//inputs Message
				System.out.println("Geben sie ihre Nachricht ein:");
				userMsg = s.nextLine();
								
				//checks for exit
				if(userMsg.equals("exit")) {
					break;
				}
				
				
				
				//sends Message
				userMsgByteArray = userMsg.getBytes(StandardCharsets.US_ASCII); //converts string to byte array with ascii code
				port.getOutputStream().write(userMsgByteArray);
				port.getOutputStream().flush();
				
				Thread.sleep(1000);
				System.out.println("Message send! Wenn sie beenden wollen nutzen sie \"exit\" als nächste Nachricht"
						+ "\n\n--------------------------------");
				
			}
			
		}else {
			System.out.println("Faild to open Port");
		}if(!port.closePort()) {
			System.out.println("Faild to close Port");
		}
		
	}
}
