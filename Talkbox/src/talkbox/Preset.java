package talkbox;
import java.util.*;

public class Preset 
{
	private int buttonNum;
	private ArrayList<Expression> buttonList;
	private String name;
	
	public Preset(int inButtonNum, String inName)
	{
		buttonNum = inButtonNum;
		buttonList = new ArrayList<Expression>(buttonNum);
		name = inName;
	}
	public void setButtonNum(int input)
	{
		buttonNum = input;
	}
	public int getButtonNum()
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
	public String GetName()
	{
		return name;
	}
	public void SetName(String input)
	{
		name = input;
	}
	public ArrayList<Expression> ReturnButtons()
	{
		return buttonList;		
	}
	
	public Expression getButtonAt(int index)
	{
		return buttonList.get(index);
	}
	

}
