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

public class TalkBoxConfigurationApp extends JFrame implements ActionListener , ListSelectionListener, ChangeListener, ComponentListener
{

	//global objects
	int window_width;
	int window_height;
	Dimension screen_size;
	
	// objects for the selection screen
	public JPanel mainScreen; 
	
	// These lists represent the model of the config app 
	// only the preset editor should be allowed to make changes
	public DefaultListModel<Preset> preset_library;
	public DefaultListModel<String> preset_library_list;
	public DefaultListModel<Expression> exp_library;
 	
	// this list represent selected presets for the device 
	// only the selection screen should edit these
	public DefaultListModel<Preset> active_presets;
	public DefaultListModel<String> active_preset_names;
	public JList<String> j_preset_library;
	public JList<String> j_active_presets;
	public JScrollPane j_p_preset_library;
	public JScrollPane j_p_active_presets;
	public JButton preset_add;
	public JButton preset_remove;
	public JButton preset_create;
	public int selection_index;
	public int remove_index;
	
	

	private JSpinner spinner;

	ArrayList<JButton> activeButtons = new ArrayList<JButton>();
	ArrayList<Expression> newButtons;
	GridBagLayout editor_layout;
	
	SelectionScreenSetup slscsu;
	// objects for the preset editor
	PresetEditor preset_editor;
	
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
		preset_editor = new PresetEditor(this);
		this.setVisible(true);
		this.addComponentListener(this);
		
	}

	
	@Override
	public void componentHidden(ComponentEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent e) 
	{

		preset_editor.set_component_sizes();
		slscsu.putComponents();
		
	}

	@Override
	public void componentShown(ComponentEvent e)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

	
	
}
	

	
	