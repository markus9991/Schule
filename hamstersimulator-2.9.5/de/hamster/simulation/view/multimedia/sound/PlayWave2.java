package de.hamster.simulation.view.multimedia.sound;


import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

public class PlayWave2 extends Thread {

	private String filename;
	private final int EXTERNAL_BUFFER_SIZE = 524288;
		
	public PlayWave2(String file) {
		filename = file;
	}
	
	public void run() {
		 
		File soundFile = new File(this.filename);
		if (!soundFile.exists()) {
			System.err.println("nicht gefunden: " + filename);
			return;
		}
 
		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
			return;
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}
 
		AudioFormat format = audioInputStream.getFormat();
		SourceDataLine auline = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
 
		try {
			auline = (SourceDataLine) AudioSystem.getLine(info);
			auline.open(format);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
			return;
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
 
		FloatControl rate = (FloatControl) auline.getControl(FloatControl.Type.SAMPLE_RATE);
		
		rate.setValue(rate.getValue() * 5f);
 
		

		auline.start();
		int nBytesRead = 0;
		byte[] abData = new byte[EXTERNAL_BUFFER_SIZE];
 
		try {
			while (nBytesRead != -1) {
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if (nBytesRead >= 0)
					auline.write(abData, 0, nBytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			auline.drain();
			auline.close();
		}
 
	}
	
}
