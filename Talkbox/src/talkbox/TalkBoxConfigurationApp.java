package talkbox;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.*;

public class TalkBoxConfigurationApp extends JFrame implements ActionListener , ListSelectionListener
{

	private JPanel selection_screen; 
	private JPanel preset_editor;
	private DefaultListModel<Preset> preset_library;
	private DefaultListModel<String> preset_library_list;
	private DefaultListModel<Preset> active_presets;
	private DefaultListModel<String> active_preset_names;
	private JList<String> j_preset_library;
	private JList<String> j_active_presets;
	private JScrollPane j_p_preset_library;
	private JScrollPane j_p_active_presets;
	private JButton preset_add;
	private JButton preset_remove;
	private JButton preset_create;
	private int selection_index;
	private int remove_index;
	private int window_width;
	private int window_height;
	private Dimension screen_size;
	
	private ArrayList<JButton> activeButtons = new ArrayList<JButton>();
	private ArrayList<Expression> newButtons;
	
	
	
	
	public static void main(String[] args)
	
	{
		TalkBoxConfigurationApp app = new TalkBoxConfigurationApp();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public TalkBoxConfigurationApp()
	{
		
		
		selection_index = -1;
		remove_index = -1;
		Toolkit tk =  Toolkit.getDefaultToolkit();
		screen_size = tk.getScreenSize();
		window_width = ((int)(screen_size.width * 0.8));
		window_height = ((int) (screen_size.height * 0.8));
		this.setSize(window_width,window_height);
		this.setLocation(((int)(screen_size.width *0.1)),((int) (screen_size.height * 0.1)));
		selection_screen_set_up();
		preset_editor_set_up();
		this.setVisible(true);
		
	}

	

	@Override
	public void actionPerformed(ActionEvent eve) { 
		if (eve.getSource() == preset_add)
		{
			preset_add_click();
		}
		
		if (eve.getSource() == preset_remove)
		{
			preset_remove_click();
		}
		
		
		if (eve.getSource() == preset_create)
		{
			switch_panels();
		}
		
		for(int i = 0; i < activeButtons.size(); i++)
		{
			if(eve.getSource() == activeButtons.get(i)) 
			{
				newButtons.get(i).PlayAudio();			
			}
			
		}
		
		// TODO Auto-generated method stub
		
		
	}


	

	@Override
	public void valueChanged(ListSelectionEvent eve)
	{
		if (eve.getSource() == j_preset_library)
		{
			selection_index = j_preset_library.getSelectedIndex();
			preview_panel();
		}
		
		if(eve.getSource() == j_active_presets)
		{
			remove_index = j_active_presets.getSelectedIndex();
		}
	
		
				
	}
		// TODO Auto-generated method stub
		
	
	private void selection_screen_set_up()
	{
//		JTextField toggles = new JTextField("toggle number");
//		toggles.setEditable(true);
//		toggles.setVisible(true);
//		toggles.addActionListener(this);
//		selection_screen.add(toggles);
//		toggles.setSize(200, 200);
//		toggles.setLocation(400, 400);
		
		
		preset_library = new DefaultListModel<Preset>();
	    active_presets = new DefaultListModel<Preset>();
	    preset_library_list = new DefaultListModel<String>();
	    active_preset_names = new DefaultListModel<String>();
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
//	    preset_library.addElement("preset 2");
//	    preset_library.addElement("preset 3");
//	    preset_library.addElement("preset 4");
//	    preset_library.addElement("preset 5");
//	    preset_library.addElement("preset 6");
//	    preset_library.addElement("preset 7");
//	    preset_library.addElement("preset 8");
//	    preset_library.addElement("preset 9");
//	    preset_library.addElement("preset 10");
//	    preset_library.addElement("preset 11");
//	    preset_library.addElement("preset 12");
//	    preset_library.addElement("preset 13");
//	    preset_library.addElement("preset 14");
//	    preset_library.addElement("preset 15");
//	    preset_library.addElement("preset 16");
//	    preset_library.addElement("preset 17");
//	    preset_library.addElement("preset 18");
//	    preset_library.addElement("preset 19");
//	    preset_library.addElement("preset 20");
//	    preset_library.addElement("preset 21");
//	    preset_library.addElement("preset 22");
//	    preset_library.addElement("preset 23");
//	    preset_library.addElement("preset 23");
//	    preset_library.addElement("preset 24");
//	    preset_library.addElement("preset 25");
//	    preset_library.addElement("preset 26");
//	    preset_library.addElement("preset 27");
//	    preset_library.addElement("preset 28");
//	    preset_library.addElement("preset 29");
//	    preset_library.addElement("preset 30");
//	    preset_library.addElement("preset 31");
//	    preset_library.addElement("preset 32");
//	    preset_library.addElement("preset 33");
//	    preset_library.addElement("preset 34");
//	    preset_library.addElement("preset 35");
//	    preset_library.addElement("preset 36");
//	    preset_library.addElement("preset 37");
//	    preset_library.addElement("preset 38");
//	    preset_library.addElement("preset 39");
//	    preset_library.addElement("preset 40");
//	    preset_library.addElement("preset 41");
	    
	    j_preset_library = new JList<String>(preset_library_list);
		j_active_presets = new JList<String>(active_preset_names);
		j_p_preset_library = new JScrollPane(j_preset_library);
		j_p_active_presets = new JScrollPane(j_active_presets);
		selection_screen = new JPanel();
		selection_screen.setLayout(null);
		preset_editor = new JPanel();
		selection_screen.add(j_p_preset_library);
		selection_screen.add(j_p_active_presets);
		selection_screen.setSize(window_width, window_height);
		j_p_preset_library.setSize( (window_width * 2 / 5 ),(window_height * 3 / 5 ) );
		j_p_active_presets.setSize( (window_width * 2 / 5 ),(window_height * 3 / 5 ) );
		j_p_preset_library.setLocation(0, 0);
		j_p_active_presets.setLocation((window_width * 3 / 5 ), 0);
		j_preset_library.setFont(new Font("Arial", Font.PLAIN, 30));
		j_active_presets.setFont(new Font("Arial", Font.PLAIN, 30));
		
		preset_add = new JButton("Add Preset");
		preset_remove = new JButton("Remove Preset");
		preset_create = new JButton("Create Preset");	
		selection_screen.add(preset_add);
		selection_screen.add(preset_remove);
		selection_screen.add(preset_create); 	
		preset_add.setSize((window_width / 8 ), (window_height / 14));
		preset_remove.setSize((window_width / 8 ), (window_height / 14));
		preset_create.setSize((window_width / 8 ), (window_height / 14));
		preset_add.setLocation((window_width / 25 * 11),(0  +  (window_height / 12) ));
		preset_remove.setLocation((window_width / 25 * 11),((window_height / 8) + (window_height / 12)));
		preset_create.setLocation((window_width / 25 * 11) ,((window_height / 4) + (window_height / 12)));	
		preset_add.setFont(new Font("Ariel", Font.BOLD, 35));
		preset_remove.setFont(new Font ("Ariel", Font.BOLD, 35));
		preset_create.setFont(new Font("Ariel", Font.BOLD, 35));
		preset_add.addActionListener(this);
		preset_remove.addActionListener(this);
		preset_create.addActionListener(this);
		
		j_preset_library.addListSelectionListener(this);
		j_active_presets.addListSelectionListener(this);
		JLabel pre_lib = new JLabel("Preset Library");
		JLabel act_pre = new JLabel("Active Presets");
		pre_lib.setFont(new Font("Ariel", Font.PLAIN, 40));
		act_pre.setFont(new Font("Ariel", Font.PLAIN, 40));
		pre_lib.setLocation( 0 , (window_height * 3 / 5 ) );
		act_pre.setLocation((window_width * 4 /5),( window_height * 3 /5));
		pre_lib.setSize((window_width * 2 /5) ,( window_height /18) );
		act_pre.setSize((window_width * 2 /5) ,( window_height /18) );
		selection_screen.add(pre_lib);
		selection_screen.add(act_pre);
		
		this.getContentPane().add(selection_screen);
		
		
	}

	private void preset_editor_set_up()
	{
		// TODO Auto-generated method stub
		
		
	}
	
	
	// helper functions for events
	
	private void preset_add_click() 
	{
		// TODO Auto-generated method stub
	
		if(selection_index != -1)
		{
			if (active_presets.contains(preset_library.get(selection_index)) == false)
			{	
			
				active_presets.addElement(preset_library.get(selection_index));
				active_preset_names.addElement(preset_library_list.getElementAt(selection_index));
			
			}
			
		}
		
	}
	
	private void preset_remove_click()
	{
		// TODO Auto-generated method stub
		if (remove_index != -1)
		{
		active_presets.remove(remove_index);
		active_preset_names.remove(remove_index);
		}
	}
	
	private void switch_panels() 
	{
		this.getContentPane().removeAll();
		this.getContentPane().add(preset_editor);
		this.getContentPane().revalidate();
		//this.getContentPane().add(preset_editor);
	}
	private void preview_panel()
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
			
			JButton Jb = new JButton(b.GetName());
			Icon img = new ImageIcon(b.GetIconPath());
			Jb.setIcon(img);
			
			activeButtons.add(Jb);
			
			Jb.setSize((window_width * 5 / (6 * newButtons.size()) ), (window_height / 5));
			Jb.setLocation(((window_width + 18400 * newButtons.indexOf(b)) / (12 * newButtons.size())) , (165 + (window_height / 2 ) ));
			Jb.addActionListener(this);
			
			this.selection_screen.add(Jb);	
			
		}
		this.selection_screen.updateUI();
		
		}
	}
	
	
	
	

	
	

}
