package talkbox;
import java.io.File;
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
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("audioFile").getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
			
		}
		catch(Exception e) 
		{
			if(e.getClass() == UnsupportedAudioFileException.class)
				System.out.println("Unsupported audio file");
			if(e.getClass() == IOException.class)
				System.out.println("IOException");
			if(e.getClass() == LineUnavailableException.class)
				System.out.println("line unavailable");
			else
				System.out.println("Unspecified error");
		}
	}
	

}
