package talkbox;
import java.util.*;

public class Expressions 
{
	private int buttonNum;
	private int audioButtonNum;
	private ArrayList<Button> buttonList;
	
	public Expressions(int inButtonNum)//, int inAudioButtonNum)
	{
		buttonNum = inButtonNum;
		//audioButtonNum = inAudioButtonNum;
		buttonList = new ArrayList<Button>();
		audioButtonNum = buttonList.size();
	}
	public void setButtonNum(int input)
	{
		buttonNum = input;
	}
	public void setAudioButtonNum(int input)
	{
		audioButtonNum = input;
	}
	public int getButtonNum()
	{
		return buttonNum;
	}
	public int getAudioButtonNum()
	{
		return buttonList.size(); //audioButtonNum;
	}
	public void AddButton(Button input)
	{
		buttonList.add(input);
	}
	public void AddButton(String name, String audioFile, String iconFile) 
	{
		buttonList.add(new Button(name, audioFile, iconFile));
	}
	public void RemoveButton(Button input)
	{
		buttonList.remove(input);
	}
	public ArrayList<Button> ReturnButtons()
	{
		return buttonList;		
	}
	

}
