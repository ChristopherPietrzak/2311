package talkbox;
import java.util.*;

public class Preset 
{
	private int buttonNum;
	private ArrayList<Expression> buttonList;
	
	public Preset(int inButtonNum)
	{
		buttonNum = inButtonNum;
		buttonList = new ArrayList<Expression>(buttonNum);
	}
	public void setButtonNum(int input)
	{
		buttonNum = input;
	}
	public int getButtonNum()
	{
		return buttonNum;
	}
	public int getAudioButtonNum()
	{
		return buttonList.size(); //audioButtonNum;
	}
	public void AddButton(Expression input)
	{
		buttonList.add(input);
	}
	public void AddButton(String name, String audioFile, String iconFile) 
	{
		buttonList.add(new Expression(name, audioFile, iconFile));
	}
	public void RemoveButton(Expression input)
	{
		buttonList.remove(input);
	}
	public ArrayList<Expression> ReturnButtons()
	{
		return buttonList;		
	}
	

}
