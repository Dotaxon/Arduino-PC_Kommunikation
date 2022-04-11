package ArduinoToJavaSound;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;


public class MusicLoader {
	private File soundDatei;  //Hier wird die SoundDatei gespeichert
	private Clip clip;
	private float volume;	//Lauts�rke leise z.b. -10f lauter 10f
	
	public MusicLoader(String filepath, float volume) {
		soundDatei = new File(filepath); 
		this.volume = volume;
	}
	
	
	public void play() {		
		try 
		{
			clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(soundDatei));
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);	//erstelt ein Objekt mit dem man unteranderem die Laut
																										//st�rke kontolieren kann
			gainControl.setValue(volume);	//hier wird die Lautst�rke ge�ndert
			clip.start();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
