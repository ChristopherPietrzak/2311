package talkbox;
import java.util.*;

public class Toggle {
	
	private boolean QuickToggle;
	private ArrayList<Preset> swapThru;
	//private int expressionNum;
	
	public Toggle()
	{
	}
	public Toggle(ArrayList<Preset> presets)
	{
		QuickToggle = false;
		swapThru = presets; // (ArrayList<Preset>)
//		expressionNum = presets.
	}
	public void AddPreset(Preset inPreset)
	{
		if (QuickToggle == false)
			swapThru.add(inPreset);	
		else
			System.out.println("Error: Cannot add presets to a Quick Toggle");
	}
	public void RemovePreset(Preset inPreset)
	{
		if (QuickToggle == false)
			swapThru.remove(inPreset);	
		else
			System.out.println("Error: Cannot remove presets from a Quick Toggle");
	}
	public void MakeQuickToggle()
	{
		QuickToggle = true;
		for(int i = 1; i < swapThru.size(); i++) 
		{
			swapThru.remove(i);
		}
	}
	public void MakeFullToggle(ArrayList<Preset> inPresets)
	{
		QuickToggle = false;
		for(Preset p : inPresets)
		{
			swapThru.add(p);	
		}
	}
	public String GetToggleType()
	{
		if (QuickToggle == true)
		{
			return "Quick Toggle";
		}
		else
		{
			return "Full Toggle";
		}
		
	}
	
	

}
