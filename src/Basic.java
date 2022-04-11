import com.fazecast.jSerialComm.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
public class Basic {

	public static void main(String[] args) throws IOException, InterruptedException{
		//setup Serial Communication
		SerialPort sPort = SerialPort.getCommPort("COM5");
		sPort.setComPortParameters(9600, 8, 1, 0);
		sPort.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
		
		
		//Variables
		String msg ="1234";			//Message
		byte[] b = msg.getBytes(StandardCharsets.US_ASCII);		//Message in byte array
		
		
		
		if(sPort.openPort()) {		//openPort öffnet Port braucht Zeit!!! mind. 3sek
			Thread.sleep(4000);
			System.out.println("Port is open!");
			
			sPort.getOutputStream().write(b);	//Writes message as byte[]
			sPort.getOutputStream().flush();
			Thread.sleep(1000);
		}
		
		if(sPort.closePort()) {
			System.out.println("Port is closed");
		}
	}

}
