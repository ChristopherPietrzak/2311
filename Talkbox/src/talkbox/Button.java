package talkbox;
import java.io.File;
import javax.swing.*;
import java.util.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Button {
	
	private String name;
	private String audioFile; //= AudioSystem.getAudioInputStream(new File(clipName).getAbsoluteFile());
	private String iconFile;
	
	public Button(String inName, String inAudioFile, String inIconFile)
	{
		name = inName;
		audioFile = inAudioFile;
		iconFile = inIconFile;
	}
	public String GetName()
	{	
		return name;
	}
	public String GetAudio()
	{
		return audioFile;
	}
	public ImageIcon GetIcon()
	{
		return new ImageIcon(iconFile);
	}
	public void SetName(String input)
	{
		this.name = input;
	}
	public void SetIcon(String input)
	{
		this.iconFile = input;
	}
	public void SetAudio(String input)
	{
		this.audioFile = input;
	}
	public void PlayAudio()
	{
		try 
		{
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(this.audioFile).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		}
		catch(Exception e) 
		{
			System.out.println("Clip error");
		}
	}
	

}
