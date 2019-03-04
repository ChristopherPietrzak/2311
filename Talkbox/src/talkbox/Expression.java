package talkbox;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.*;
import java.util.*;
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.AudioFormat;
//import javax.sound.sampled.Clip;
//import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.*;

public class Expression {
	
	private String name;
	private String audioFile; //= AudioSystem.getAudioInputStream(new File(clipName).getAbsoluteFile());
	private String iconFile;
	
	public Expression()
	{
		name = "Error";
	}
	public Expression(String inName, String inAudioFile, String inIconFile)
	{
		name = inName;
		audioFile = inAudioFile;
		iconFile = inIconFile;
	}
	public String GetName()
	{	
		return name;
	}
	public String GetAudioPath()
	{
		return audioFile;
	}
	public String GetIconPath()
	{
		return iconFile; //new ImageIcon(iconFile);
	}
	public void SetName(String input)
	{
		this.name = input;
	}
	public void SetIconPath(String input)
	{
		this.iconFile = input;
	}
	public void SetAudioPath(String input)
	{
		this.audioFile = input;
	}
	public void PlayAudio()
	{
		try 
		{
			AudioInputStream ain = AudioSystem.getAudioInputStream(new File(audioFile).getAbsoluteFile());
			
			Clip clip = AudioSystem.getClip();
			System.out.println(ain.getFormat());
			AudioFormat F = ain.getFormat();
			System.out.println(F.getSampleSizeInBits());
			
			//the reason this fails on windows is because windows cannot run 24bit audio files which is what all of are .wav files are
			//mac and linux dont have this problem as they can run 24bit audio files.
			//ive changed the preset files to 16 bit, but we need to ensure new uploaded files will work, or have a safety.
			
			clip.open(ain);
			clip.start();
			ain.close();
			wait(100);
			//clip.close();
			
		}
		catch(Exception e) 
		{
			if(e.getClass() == UnsupportedAudioFileException.class)
				System.out.println("Unsupported audio file");
			else if(e.getClass() == IOException.class)
				System.out.println("IOException");
			else if(e.getClass() == LineUnavailableException.class)
				System.out.println("Line Unavailable");
			else if(e.getClass() == FileNotFoundException.class)
				System.out.println("No such file, FNFE");
			else
				System.out.println("Unspecified error");
		}
	}
	

}
