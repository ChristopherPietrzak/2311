package talkbox;

import java.awt.*;
//import java.awt.color.ColorSpace;
//import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;


public class SelectionScreenSetup {
	
	//Jpanels
	private JPanel selection_screen; 

	//SelectionScreen visual Components

	DefaultListModel<String> preset_library_list;

	DefaultListModel<String> active_preset_names;
	JList<String> j_preset_library;
	JList<String> j_active_presets;
	JScrollPane j_p_preset_library;
	JScrollPane j_p_active_presets;
	JButton preset_add;
	JButton preset_remove;
	JButton preset_create;
	private JSpinner spinner;
	ArrayList<JButton> activeButtons = new ArrayList<JButton>();	
	JLabel pre_lib = new JLabel("Preset Library");
	JLabel act_pre = new JLabel("Active Presets");
	JLabel toggle_label = new JLabel("Toggle Buttons");
	
	//SelectionScreen Nonvisual Components
	TalkBoxConfigurationApp talkBox;
	DefaultListModel<Preset> preset_library;
	DefaultListModel<Preset> active_presets;
	int selection_index;
	int remove_index;
	ArrayList<Expression> newButtons;
	GridBagLayout editor_layout;
	Rectangle windowSize = new Rectangle(talkBox.getBounds());
	
	public SelectionScreenSetup(TalkBoxConfigurationApp tbca)
	{
		
	selection_screen = new JPanel();
	talkBox = tbca;
    
	preset_library = new DefaultListModel<Preset>();
    active_presets = new DefaultListModel<Preset>();
    preset_library_list = new DefaultListModel<String>();
    active_preset_names = new DefaultListModel<String>();		
	
    if(preset_library.size() <= 0)
    {  	
    	// in the actual version, values will be in a file and not hard coded
    	Preset emotions = new Preset(5, "Emotions");
    	emotions.AddButton("angry", "I'm feeling angry.wav", "angry.png");
    	emotions.AddButton("bored", "I'm feeling bored.wav", "bored.png");
    	emotions.AddButton("exited", "I'm feeling excited.wav", "excited.png");
    	emotions.AddButton("happy", "I'm feeling happy.wav", "happy.png");
    	emotions.AddButton("sad", "I'm feeling sad.wav", "sad.png");
    
    	Preset weather = new Preset(6, "Weather");
    	
    	weather.AddButton(new Expression());
    	weather.AddButton(new Expression());
    	weather.AddButton(new Expression());
    	weather.AddButton(new Expression());
    	weather.AddButton(new Expression());
    	weather.AddButton(new Expression());
    
    	Preset preset_0 = new Preset(1, "Preset_0");
    	preset_0.AddButton(new Expression());
    
    	Preset preset_1 = new Preset(10, "Preset_1");
    	preset_1.AddButton(new Expression());
    	preset_1.AddButton(new Expression());
    	preset_1.AddButton(new Expression());
    	preset_1.AddButton(new Expression());
    	preset_1.AddButton(new Expression());
    	preset_1.AddButton(new Expression());
    	preset_1.AddButton(new Expression());
    	preset_1.AddButton(new Expression());
    	preset_1.AddButton(new Expression());
    	preset_1.AddButton(new Expression());
    
    	preset_library.addElement(emotions);
    	preset_library.addElement(weather);
    	preset_library.addElement(preset_0);
    	preset_library.addElement(preset_1);
    	preset_library_list.addElement(emotions.GetName());
    	preset_library_list.addElement(weather.GetName());
    	preset_library_list.addElement(preset_0.GetName());
    	preset_library_list.addElement(preset_1.GetName());
    
    } 
    
  //used for controlling the files and named sections of the jscroller
    j_preset_library = new JList<String>(preset_library_list);
 	j_active_presets = new JList<String>(active_preset_names);
 	j_p_preset_library = new JScrollPane(j_preset_library);
 	j_p_active_presets = new JScrollPane(j_active_presets);
    
	selection_screen.setLayout(null);
	
	selection_screen.add(j_p_preset_library);
	selection_screen.add(j_p_active_presets);
	selection_screen.setSize(talkBox.window_width, talkBox.window_height);
	
	j_preset_library.setFont(new Font("Arial", Font.PLAIN, 30));
	j_active_presets.setFont(new Font("Arial", Font.PLAIN, 30));
	preset_add = new JButton("Add Preset");
	preset_remove = new JButton("Remove Preset");
	preset_create = new JButton("Create Preset");	
	selection_screen.add(preset_add);
	selection_screen.add(preset_remove);
	selection_screen.add(preset_create); 	
	
	preset_add.setFont(new Font("Ariel", Font.BOLD, 35));
	preset_remove.setFont(new Font ("Ariel", Font.BOLD, 35));
	preset_create.setFont(new Font("Ariel", Font.BOLD, 35));
	preset_add.addActionListener(talkBox);
	preset_remove.addActionListener(talkBox);
	preset_create.addActionListener(talkBox);
	
	j_preset_library.addListSelectionListener(talkBox);
	j_active_presets.addListSelectionListener(talkBox);
	pre_lib.setFont(new Font("Ariel", Font.PLAIN, 40));
	act_pre.setFont(new Font("Ariel", Font.PLAIN, 40));
	selection_screen.add(pre_lib);
	selection_screen.add(act_pre);

	toggle_label.setFont(new Font("Ariel", Font.PLAIN, 28));
	selection_screen.add(toggle_label);
	
	String[] num = {"1","2","3","4","5","6","7","8","9","10"};
	SpinnerListModel numberList = new SpinnerListModel(num);
	spinner = new JSpinner(numberList);
	spinner.setName("Toggles");
	
	spinner.setFont(new Font("Ariel", Font.PLAIN, 40));
	spinner.addChangeListener(talkBox);
	selection_screen.add(spinner);
	
	putComponents();
	talkBox.getContentPane().add(selection_screen);
	
	updateTBCA();
	}
	/**
	 * updates the information in the selectionscreen to the information in the TBCA if changes have been made.
	 */
	public void updateSS()
	{
		this.preset_library = talkBox.preset_library;
		this.preset_library_list = talkBox.preset_library_list;
		this.active_presets = talkBox.active_presets;
		this.active_preset_names = talkBox.active_preset_names;
		this.j_preset_library = talkBox.j_preset_library;
		this.j_active_presets = talkBox.j_active_presets;
		this.j_p_preset_library = talkBox.j_p_preset_library;
		this.j_p_active_presets = talkBox.j_p_active_presets;
		this.preset_add = talkBox.preset_add;
		this.preset_remove = talkBox.preset_remove;
		this.preset_create = talkBox.preset_create;
		this.selection_index = talkBox.selection_index;
		this.remove_index = talkBox.remove_index;
		this.activeButtons = talkBox.activeButtons;
		this.newButtons = talkBox.newButtons;
		this.editor_layout = talkBox.editor_layout;
		
	}
	
	public void updateTBCA()
	{
		talkBox.preset_library = this.preset_library;
		talkBox.preset_library_list = this.preset_library_list;
		talkBox.active_presets = this.active_presets;
		talkBox.active_preset_names = this.active_preset_names;
		talkBox.j_preset_library = this.j_preset_library;
		talkBox.j_active_presets = this.j_active_presets;
		talkBox.j_p_preset_library = this.j_p_preset_library;
		talkBox.j_p_active_presets = this.j_p_active_presets;
		talkBox.preset_add = this.preset_add;
		talkBox.preset_remove = this.preset_remove;
		talkBox.preset_create = this.preset_create;
		talkBox.selection_index = this.selection_index;
		talkBox.remove_index = this.remove_index;
		
		talkBox.activeButtons = this.activeButtons;
		talkBox.newButtons = this.newButtons;
		talkBox.editor_layout = this.editor_layout;
		
	}
	public void preview_panel()
	{
	
		if(activeButtons.isEmpty() == false)
		{
			for(JButton Jb : activeButtons)
			{
				selection_screen.remove(Jb);
			}
			activeButtons.clear();
		}
		
		
		
		if(selection_index != -1) 
		{
		Preset bar = preset_library.getElementAt(selection_index);
		newButtons = bar.ReturnButtons();
		
		
		for(Expression b : newButtons)
		{	
			Icon img = new ImageIcon(b.GetIconPath());
			JButton Jb = new JButton(b.GetName());
			Jb.setIcon(img);
			Jb.setVerticalTextPosition(SwingConstants.NORTH);
			Jb.setHorizontalTextPosition(SwingConstants.CENTER);
			Jb.setFont(new Font("Ariel", Font.BOLD, 22));
			activeButtons.add(Jb);
			
			Jb.setSize((talkBox.window_width * 5 / (6 * newButtons.size()) ), (talkBox.window_height / 5));
			Jb.setLocation(((talkBox.window_width + 18100 * newButtons.indexOf(b)) / (12 * newButtons.size())) , (165 + (talkBox.window_height / 2 ) ));
			Jb.addActionListener(talkBox);
			
			selection_screen.add(Jb);	
			
		}
		selection_screen.updateUI();
		updateTBCA();
		
		}
	}
	private int gridX(int x)
 	{
		double ratio  = ((double) x) / ((double) 2000);
		windowSize = new Rectangle (talkBox.getBounds());
		double xPosition = ratio * ((double) windowSize.getWidth());
		return ((int) xPosition);
	}
	private int gridY(int y)
	{
		double ratio  = ((double) y) / ((double) 1000);
		windowSize = new Rectangle (talkBox.getBounds());
		double yPosition = ratio * ((double) windowSize.getHeight());  
		return ((int) yPosition);
	}
	private void putComponents()
	{
		j_p_preset_library.setSize( gridX(800), gridY(600) );
		j_p_active_presets.setSize( gridX(800), gridY(600) );
		j_p_preset_library.setLocation( gridX(0), gridY(0) );
		j_p_active_presets.setLocation( gridX(1200), gridY(0) );
		
		preset_add.setSize( gridX(200) , gridY(200));
		preset_remove.setSize( gridX(200) , gridY(200) );
		preset_create.setSize( gridX(200) , gridY(200) );
		preset_add.setLocation( gridX(500) , gridY(700));
		preset_remove.setLocation( gridX(500) , gridY(700));
		preset_create.setLocation( gridX(500) , gridY(700));
		
		pre_lib.setLocation( 0 , (talkBox.window_height * 3 / 5 ) );
		act_pre.setLocation((talkBox.window_width * 3 / 5),( talkBox.window_height * 3 /5));
		pre_lib.setSize((talkBox.window_width * 2 /5) ,( talkBox.window_height /18) );
		act_pre.setSize((talkBox.window_width * 2 /5) ,( talkBox.window_height /18) );
		
		toggle_label.setLocation((int) (talkBox.window_width * 0.43), (int) (talkBox.window_height * 0.45));
		toggle_label.setSize((int)(talkBox.window_width * 0.43) ,( talkBox.window_height /18) );
		
		spinner.setSize(talkBox.window_width * 1 / 8, talkBox.window_height * 1 / 10);
		spinner.setLocation((int) (talkBox.window_width * 0.43),( talkBox.window_height * 1 /2));
	
	}
}