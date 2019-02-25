package talkbox;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.util.*;

public class TalkBoxConfigurationApp extends JFrame implements ActionListener , ListSelectionListener, ChangeListener
{

	//global objects
	int window_width;
	int window_height;
	Dimension screen_size;
	
	// objects for the selection screen
	JPanel selection_screen; 
	
	DefaultListModel<Preset> preset_library;
	DefaultListModel<String> preset_library_list;
	DefaultListModel<Preset> active_presets;
	DefaultListModel<String> active_preset_names;
	JList<String> j_preset_library;
	JList<String> j_active_presets;
	JScrollPane j_p_preset_library;
	JScrollPane j_p_active_presets;
	JButton preset_add;
	JButton preset_remove;
	JButton preset_create;
	int selection_index;
	int remove_index;

	private JSpinner spinner;

	ArrayList<JButton> activeButtons = new ArrayList<JButton>();
	ArrayList<Expression> newButtons;
	GridBagLayout editor_layout;
	
	private SelectionScreenSetup slscsu;
	// objects for the preset editor
	private JPanel preset_editor;
	GridBagLayout sel_layout; 
	JPanel preset_selector;
	GridBagLayout preset_view_layout;
	JPanel preset_view;
	GridBagLayout exp_layout;
	JPanel exp_editor;
	// preset selector objects
	JList<String> editor_preset_selector;
	JButton create_new_preset;
	JButton save_current_preset;
	JButton delete_current_preset;

    // preset viewer objects
	JLabel current_preset;
	JButton previous_preset;
	JButton next_preset;

	
	// expression editor objects
	JLabel exp_pic;
	JComboBox<String> browse_photos;
	JButton upload_photo;
	JButton record_audio;
	JButton play_audio;
	JButton reset;
	JButton save_changes;
	JComboBox<String> browse_exp;
	
	
	
	
	
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
		
		slscsu = new SelectionScreenSetup(this);
		preset_editor_set_up();
		this.setVisible(true);
		
	}

	@Override
	public void stateChanged(ChangeEvent eve)
	{
		
		
		if(eve.getSource() == spinner)
		{
			System.out.println("listener functioning");
			int location = Integer.parseInt((String)spinner.getValue());
			System.out.println(location);
			toggle_select(location);
			
		}
		
		
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
			slscsu.updateSS();
			slscsu.preview_panel();
		}
		
		if(eve.getSource() == j_active_presets)
		{
			remove_index = j_active_presets.getSelectedIndex();
			slscsu.updateSS();
		}
	
		
				
	}
		// TODO Auto-generated method stub
		
	
	//private void selection_screen_set_up()
	//{	
	//}

	private void preset_editor_set_up()
	{

	    window_width = this.getBounds().width;
	    window_height = this.getBounds().height;
		editor_layout = new GridBagLayout();
		preset_editor = new JPanel(editor_layout);
		preset_editor.setBackground(Color.orange);
	    sel_layout = new GridBagLayout(); 
	    preset_selector = new JPanel(sel_layout);
	    preset_view_layout = new GridBagLayout();
	    preset_view = new JPanel(preset_view_layout);
	    exp_layout = new GridBagLayout();
	    exp_editor = new JPanel(exp_layout);
	    preset_selector.setBackground(Color.LIGHT_GRAY );
	    preset_view.setBackground(Color.white);
	    exp_editor.setBackground(Color.cyan);
	    
	    
	    GridBagConstraints constraints = new GridBagConstraints();
	    constraints.fill = GridBagConstraints.BOTH;
	    constraints.weightx = 0.33;
	    constraints.weighty = 0.66;
	    constraints.gridx = 0;
	    constraints.gridy = 0;
	    constraints.gridwidth = 1;
	    constraints.gridheight = 3;
	    preset_editor.add(preset_selector , constraints );
	   
	    
	    constraints = new GridBagConstraints();
	    constraints.fill = GridBagConstraints.BOTH;
	    constraints.weightx = 0.66;
	    constraints.weighty = 0.66;
	    constraints.gridx = 1;
	    constraints.gridy = 0;
	    constraints.gridwidth = 2;
	    constraints.gridheight = 3;
	    preset_editor.add(exp_editor, constraints);
	   
	    
	    constraints = new GridBagConstraints();
	    constraints.fill = GridBagConstraints.BOTH;
	    constraints.weightx = 1;
	    constraints.weighty = 0.33;
	    constraints.gridx = 0;
	    constraints.gridwidth = 3;
	    constraints.gridheight = 2;
	    preset_editor.add(preset_view, constraints);

	    
	    // preset selector setup
	    
	    editor_preset_selector = new JList<String>(preset_library_list);
	    constraints = new GridBagConstraints();
	    constraints.fill = GridBagConstraints.BOTH;
	    constraints.gridx = 0;
	    constraints.gridy = 0;
	    constraints.weightx = 0.5;
	    constraints.weighty = 1;
	    constraints.gridwidth = 1;
	    constraints.gridheight = 3;
	    preset_selector.add(editor_preset_selector, constraints);
	    
	    create_new_preset = new JButton("Create New Preset");
	    constraints = new GridBagConstraints();
	    constraints.fill = GridBagConstraints.BOTH;
	    constraints.gridx = 1;
	    constraints.gridy = 0;
	    constraints.weightx = 0.5;
	    constraints.weighty = 0.33;
	    constraints.gridwidth = 1;
	    preset_selector.add(create_new_preset, constraints);
	    
	    
	    save_current_preset = new JButton("Save Current Preset");
	    constraints = new GridBagConstraints();
	    constraints.fill = GridBagConstraints.BOTH;
	    constraints.gridx = 1;
	    constraints.gridy = 1;
	    constraints.weightx = 0.5;
	    constraints.weighty = 0.33;
	    constraints.gridwidth = 1;
	    preset_selector.add(save_current_preset, constraints);
	    
	    
	    delete_current_preset = new JButton("Delete Current Preset");
	    constraints = new GridBagConstraints();
	    constraints.fill = GridBagConstraints.BOTH;
	    constraints.gridx = 1;
	    constraints.gridy = 2;
	    constraints.weightx = 0.5;
	    constraints.weighty = 0.33;
	    constraints.gridwidth = 1;
	    preset_selector.add(delete_current_preset, constraints);
	    
	    
	    
	    
	    
	    
	    
	    
	    // preset viewer setup
	    
	    current_preset = new  JLabel("Current Preset : Emotions") ;
	    constraints = new GridBagConstraints();
	    constraints.fill = GridBagConstraints.BOTH;
	    constraints.gridx = 0;
	    constraints.gridy = 0;
	    constraints.weightx = 0.9;
	    constraints.weighty = 0.2;
	    constraints.gridwidth = 10;
	    constraints.gridheight = 1;
	    preset_view.add(current_preset, constraints);
	    
	    previous_preset = new  JButton("Previous Preset");
	    constraints = new GridBagConstraints();
	    constraints.fill = GridBagConstraints.BOTH;
	    constraints.gridx = 10;
	    constraints.gridy = 1;
	    constraints.weightx = 0.1;
	    constraints.weighty = 0.2;
	    constraints.gridwidth = 1;
	    constraints.gridheight = 1;
	    preset_view.add(previous_preset, constraints);
	    
	    next_preset = new  JButton("Next Preset");
	    constraints = new GridBagConstraints();
	    constraints.fill = GridBagConstraints.BOTH;
	    constraints.gridx = 11;
	    constraints.gridy = 1;
	    constraints.weightx = 0.1;
	    constraints.weighty = 0.2;
	    constraints.gridwidth = 1;
	    constraints.gridheight = 1;
	    preset_view.add(next_preset, constraints);
	    
	    
	    
	    
	    
	    
	    
	    // expression editor setup
	    
	    
	    exp_pic = new JLabel(new ImageIcon(new Expression("bored", "I'm feeling bored.wav", "bored.png").GetIconPath())); 
	    exp_pic.setBackground(Color.BLACK);
	    constraints = new GridBagConstraints();
	    constraints.fill = GridBagConstraints.BOTH;
	    constraints.gridx = 0;
	    constraints.gridy = 0;
	    constraints.weightx = 0.4;
	    constraints.weighty = 0.7;
	    constraints.gridwidth = 2;
	    constraints.gridheight = 3;
	    exp_editor.add(exp_pic, constraints);
	    
	    browse_photos = new  JComboBox<String>() ;
	    constraints = new GridBagConstraints();
	    constraints.fill = GridBagConstraints.BOTH;
	    constraints.gridx = 0;
	    constraints.gridy = 3;
	    constraints.weightx = 0.5;
	    constraints.weighty = 0.3;
	    constraints.gridwidth = 1;
	    constraints.gridheight = 3;
	    exp_editor.add(browse_photos, constraints);
	    

	    upload_photo = new  JButton("Upload Photo") ;
	    constraints = new GridBagConstraints();
	    constraints.fill = GridBagConstraints.BOTH;
	    constraints.gridx = 1;
	    constraints.gridy = 3;
	    constraints.weightx = 0.5;
	    constraints.weighty = 0.3;
	    constraints.gridwidth = 1;
	    constraints.gridheight = 3;
	    exp_editor.add(upload_photo, constraints);
	    
	    
	    record_audio = new  JButton("Record Audio") ;
	    constraints = new GridBagConstraints();
	    constraints.fill = GridBagConstraints.BOTH;
	    constraints.gridx = 2;
	    constraints.gridy = 0;
	    constraints.weightx = 0.5;
	    constraints.weighty = 0.5;
	    constraints.gridwidth = 1;
	    constraints.gridheight = 3;
	    exp_editor.add(record_audio, constraints);
	    
	    
	    play_audio = new  JButton("Play Audio") ;
	    constraints = new GridBagConstraints();
	    constraints.fill = GridBagConstraints.BOTH;
	    constraints.gridx = 3;
	    constraints.gridy = 0;
	    constraints.weightx = 0.5;
	    constraints.weighty = 0.5;
	    constraints.gridwidth = 1;
	    constraints.gridheight = 3;
	    exp_editor.add(play_audio, constraints);
	    
	    
	    browse_exp = new JComboBox<String>() ;
	    constraints = new GridBagConstraints();
	    constraints.fill = GridBagConstraints.BOTH;
	    constraints.gridx = 2;
	    constraints.gridy = 3;
	    constraints.weightx = 1;
	    constraints.weighty = 0.3;
	    constraints.gridwidth = 2;
	    exp_editor.add(browse_exp, constraints);
	    
	    
	    save_changes = new  JButton("Save Changes") ;
	    constraints = new GridBagConstraints();
	    constraints.fill = GridBagConstraints.BOTH;
	    constraints.gridx = 2;
	    constraints.gridy = 4;
	    constraints.weightx = 1;
	    constraints.weighty = 0.3;
	    constraints.gridwidth = 2;
	    exp_editor.add(save_changes, constraints);
	    
	    
	    reset = new  JButton("Reset") ;
	    constraints = new GridBagConstraints();
	    constraints.fill = GridBagConstraints.BOTH;
	    constraints.gridx = 2;
	    constraints.gridy = 5;
	    constraints.weightx = 1;
	    constraints.weighty = 0.3;
	    constraints.gridwidth = 2;
	    exp_editor.add(reset, constraints);
	    
	    
	    
	    
	    
	    
	    
	    
	    
		
				
		
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
			Icon img = new ImageIcon(b.GetIconPath());
			JButton Jb = new JButton(b.GetName());
			Jb.setIcon(img);
			Jb.setVerticalTextPosition(SwingConstants.NORTH);
			Jb.setHorizontalTextPosition(SwingConstants.CENTER);
			Jb.setFont(new Font("Ariel", Font.BOLD, 22));
			activeButtons.add(Jb);
			
			Jb.setSize((window_width * 5 / (6 * newButtons.size()) ), (window_height / 5));
			Jb.setLocation(((window_width + 18100 * newButtons.indexOf(b)) / (12 * newButtons.size())) , (165 + (window_height / 2 ) ));
			Jb.addActionListener(this);
			
			this.selection_screen.add(Jb);	
			
		}
		this.selection_screen.updateUI();
		
		}
	}
	public void toggle_select(int toggleNum)
	{
		int QuickToggles;
//		try
//		{
//			toggleNum = (int) spinner.getValue();
//		}
//		catch(Exception e)
//		{
//			System.out.println("error converting spinner values to ints");
//		}
		for(QuickToggles = 0; QuickToggles < toggleNum; QuickToggles++)
		{
			Toggle Jt = new Toggle(active_presets.getElementAt(QuickToggles));
			System.out.println(active_presets.getElementAt(QuickToggles));
			System.out.println("added one preset");
		}
		ArrayList<Preset> in = new ArrayList<Preset>();
		for(int i = QuickToggles; i < active_presets.size(); i++)
		{
			in.add(active_presets.getElementAt(i));
		}
		
		
		
		
	}

	
	

	
	
}
	

	
	