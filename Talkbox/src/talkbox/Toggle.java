package talkbox;
import java.util.*;

public class Toggle {
	
	private boolean QuickToggle;
	private Preset OnePre;
	private ArrayList<Preset> swapThru;
	
	//private int expressionNum;
	
	public Toggle(Preset inPre)
	{
		QuickToggle = true;
		OnePre = inPre;
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
		OnePre = swapThru.get(0);
		swapThru.clear();
		
	}

	public void MakeFullToggle(ArrayList<Preset> inPresets)
	{
		QuickToggle = false;
		swapThru.add(OnePre);
		for(Preset p : inPresets)
		{
			if(p != OnePre)
			{
				swapThru.add(p);
			}
			
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
