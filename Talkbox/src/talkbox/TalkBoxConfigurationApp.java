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
	private DefaultListModel<Expressions> preset_library;
	private DefaultListModel<Expressions> active_presets;
	private JList<Expressions> j_preset_library;
	private JList<Expressions> j_active_presets;
	private JScrollPane j_p_preset_library;
	private JScrollPane j_p_active_presets;
	private JButton preset_add;
	private JButton preset_remove;
	private JButton preset_create;
	private JButton preset_preview;
	private int selection_index;
	private int remove_index;
	private int window_width;
	private int window_height;
	private Dimension screen_size;
	
	
	
	
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
		
		if (eve.getSource() == preset_preview)
		{
			preview_panel();
		}
		
		// TODO Auto-generated method stub
		
		
	}


	

	@Override
	public void valueChanged(ListSelectionEvent eve)
	{
		if (eve.getSource() == j_preset_library)
		{
			selection_index = j_preset_library.getSelectedIndex();
		}
		
		if(eve.getSource() == j_active_presets)
		{
			remove_index = j_active_presets.getSelectedIndex();
		}
		
		
				
	}
		// TODO Auto-generated method stub
		
	
	private void selection_screen_set_up()
	{
		preset_library = new DefaultListModel<Expressions>();
	    active_presets = new DefaultListModel<Expressions>();
	    // in the actual version, values will be in a file and not hard coded
	    Expressions emotions = new Expressions(5);
	    emotions.AddButton("angry", "angry.png", "I'm feeling angry.wav");
	    emotions.AddButton("bored", "bored.png", "I'm feeling bored.wav");
	    emotions.AddButton("exited", "exited.png", "I'm feeling exited.wav");
	    emotions.AddButton("happy", "happy.png", "I'm feeling happy.wav");
	    emotions.AddButton("sad", "sad.png", "I'm feeling sad.wav");
	  
	    Expressions weather = new Expressions(0);
	    
	    preset_library.addElement(emotions);
	    preset_library.addElement(weather);
//	    preset_library.addElement("preset 0");
//	    preset_library.addElement("preset 1");
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
	    
	    j_preset_library = new JList<Expressions>(preset_library);
		j_active_presets = new JList<Expressions>(active_presets);
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
		j_p_active_presets.setLocation((window_width * 3 /5 ), 0);
		j_preset_library.setFont(new Font("Arial", Font.PLAIN, 30));
		j_active_presets.setFont(new Font("Arial", Font.PLAIN, 30));
		
		preset_add = new JButton("Add Preset");
		preset_remove = new JButton("Remove Preset");
		preset_create = new JButton("Create Preset");
		preset_preview = new JButton("Preset Preview");
		
		selection_screen.add(preset_add);
		selection_screen.add(preset_remove);
		selection_screen.add(preset_create); 
		selection_screen.add(preset_preview); 
		
		preset_add.setSize((window_width / 8 ), (window_height / 12));
		preset_remove.setSize((window_width / 8 ), (window_height / 12));
		preset_create.setSize((window_width / 8 ), (window_height / 12));
		preset_preview.setSize((window_width / 8 ), (window_height / 12));
		
		preset_add.setLocation((window_width / 25 * 11),(0  +  (window_height / 16) ));
		preset_remove.setLocation((window_width / 25 * 11),((window_height / 8) + (window_height / 12)));
		preset_create.setLocation((window_width / 25 * 11) ,((window_height / 4) + (window_height / 12)));
		preset_preview.setLocation((window_width / 25 * 11) ,((window_height * 2 / 5) + (window_height / 12)));
		
		preset_add.setFont(new Font("Ariel", Font.BOLD, 35));
		preset_remove.setFont(new Font ("Ariel", Font.BOLD, 35));
		preset_create.setFont(new Font("Ariel", Font.BOLD, 35));
		preset_preview.setFont(new Font("Ariel", Font.BOLD, 35));
		
		preset_add.addActionListener(this);
		preset_remove.addActionListener(this);
		preset_create.addActionListener(this);
		preset_preview.addActionListener(this);
		
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
			
			}
			
		}
		
	}
	
	private void preset_remove_click()
	{
		// TODO Auto-generated method stub
		if (remove_index != -1)
		{
		active_presets.remove(remove_index);
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
		if(remove_index != -1) 
		{
		Expressions bar = active_presets.getElementAt(remove_index);
		ArrayList<Button> barButtons = bar.ReturnButtons();
		for(Button b : barButtons)
		{
			JButton Jb = new JButton(b.GetName());
			Jb.setIcon(b.GetIcon());
			Jb.setSize((window_width / 7 ), (window_height / 7));
			Jb.setLocation((window_width - (290 * (barButtons.indexOf(b) + 1)) ),(190 + (window_height / 2 ) ));
			Jb.addActionListener(this);
			selection_screen.add(Jb);	
		}
		selection_screen.updateUI();
		}
	}
	

	
	

}
