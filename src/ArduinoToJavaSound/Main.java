package ArduinoToJavaSound;
import java.util.Scanner;
import com.fazecast.jSerialComm.*;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		
		SerialPort availablePorts[] = SerialPort.getCommPorts();	//Array with all Ports
		SerialPort port;	//chosen Port
		int chosenPort;		//index for availablePorts
		int input;					//Serial Input		
		Scanner scan = new Scanner(System.in); //For User inputs
		Scanner data; 				//for Serial data Input
		MusicLoader wow = new MusicLoader("res/wow.wav", -5f); //erstellt Objekte für die  Sonds
		MusicLoader ok = new MusicLoader("res/ok.wav", -10f);   //erstellt Objekte für die  Sonds
		
		
		//Port auswahl
		System.out.println("Wählen sie ein Port aus:");
		
		for (int i = 0; i < availablePorts.length; i++) 
		{
			System.out.println((i+1) + ". " + availablePorts[i].getSystemPortName());	//getSystemPort... gibt sowas wie COM1 zurück
		}		
		chosenPort = scan.nextInt();
		System.out.println("Deine Auswahl: " + chosenPort + ". " + availablePorts[chosenPort-1].getSystemPortName()); // z.b. Deine Auswhal: 1. COM1
		
		//Port setup
		
		port = SerialPort.getCommPort(availablePorts[chosenPort-1].getSystemPortName());	//initalisiert den Port
		port.setComPortParameters(9600, 8, 1, 0);	//Arduino standart
		port.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);	//sets Timouts
		data = new Scanner(port.getInputStream());	//konfiguriet den Serial scanner
		
		if(port.openPort()) {
			Thread.sleep(4000); //openPort() braucht Zeit!!
			System.out.println("\n\nPort offen\n\n");
		}else {
			System.out.println("faild to open Port");
		}
		
		//Empfangen schleife
		while(data.hasNextLine()) {
			input = Integer.parseInt(data.nextLine()); //speichert empfangenden wert use Serial.println(); in Arduino code
			
			if(input == 1) {		//spielt ein Ton ab jenach dem welcher Taster gedrückt wurde
				System.out.println("Button 1");
				wow.play();
			}else if(input == 2) {
				System.out.println("Button 2");
				ok.play();
			}
		}
		
	}
	
	

}
