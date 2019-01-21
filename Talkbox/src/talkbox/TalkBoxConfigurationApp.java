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
	private DefaultListModel<String> preset_library;
	private DefaultListModel<String> active_presets;
	private JList<String> j_preset_library;
	private JList<String> j_active_presets;
	private JScrollPane j_p_preset_library;
	private JScrollPane j_p_active_presets;
	private JButton preset_add;
	private JButton preset_remove;
	private JButton preset_create;
	private int selection_index;
	private int remove_index;
	boolean test;
	
	
	
	public static void main(String[] args)
	
	{
		TalkBoxConfigurationApp app = new TalkBoxConfigurationApp();
		app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//conflict
		
		

	}
	
	public TalkBoxConfigurationApp()
	{
		test = false;
		selection_index = -1;
		remove_index = -1;
		Toolkit tk =  Toolkit.getDefaultToolkit();
		
		Dimension screensize = tk.getScreenSize();
		int window_width = ((int)(screensize.width * 0.8));
		int window_height = ((int) (screensize.height * 0.8));
		this.setSize(window_width,window_height);
		this.setLocation(((int)(screensize.width * 0.1)),((int) (screensize.height * 0.1)));
		
		preset_library = new DefaultListModel<String>();
	    active_presets = new DefaultListModel<String>();
	    // in the actual version, values will be in a file and not hard coded
	    preset_library.addElement("emotions");
	    preset_library.addElement("weather");
	    preset_library.addElement("preset 0");
	    preset_library.addElement("preset 1");
	    preset_library.addElement("preset 2");
	    preset_library.addElement("preset 3");
	    preset_library.addElement("preset 4");
	    preset_library.addElement("preset 5");
	    preset_library.addElement("preset 6");
	    preset_library.addElement("preset 7");
	    preset_library.addElement("preset 8");
	    preset_library.addElement("preset 9");
	    preset_library.addElement("preset 10");
	    preset_library.addElement("preset 11");
	    preset_library.addElement("preset 12");
	    preset_library.addElement("preset 13");
	    preset_library.addElement("preset 14");
	    preset_library.addElement("preset 15");
	    preset_library.addElement("preset 16");
	    preset_library.addElement("preset 17");
	    preset_library.addElement("preset 18");
	    preset_library.addElement("preset 19");
	    preset_library.addElement("preset 20");
	    preset_library.addElement("preset 21");
	    preset_library.addElement("preset 22");
	    preset_library.addElement("preset 23");
	    preset_library.addElement("preset 23");
	    preset_library.addElement("preset 24");
	    preset_library.addElement("preset 25");
	    preset_library.addElement("preset 26");
	    preset_library.addElement("preset 27");
	    preset_library.addElement("preset 28");
	    preset_library.addElement("preset 29");
	    preset_library.addElement("preset 30");
	    preset_library.addElement("preset 31");
	    preset_library.addElement("preset 32");
	    preset_library.addElement("preset 33");
	    preset_library.addElement("preset 34");
	    preset_library.addElement("preset 35");
	    preset_library.addElement("preset 36");
	    preset_library.addElement("preset 37");
	    preset_library.addElement("preset 38");
	    preset_library.addElement("preset 39");
	    preset_library.addElement("preset 40");
	    preset_library.addElement("preset 41");
	    
	    j_preset_library = new JList<String>(preset_library);
		j_active_presets = new JList<String>(active_presets);
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
		selection_screen.add(preset_add);
		selection_screen.add(preset_remove);
		selection_screen.add(preset_create);  
		preset_add.setSize((window_width / 7 ), (window_height / 12));
		preset_remove.setSize((window_width / 7 ), (window_height / 12));
		preset_create.setSize((window_width / 7 ), (window_height / 12));
		preset_add.setLocation((window_width / 25 * 11),(0  +  (window_height / 12) ));
		preset_remove.setLocation((window_width / 25 * 11),((window_height / 6) + (window_height / 12)));
		preset_create.setLocation((window_width / 25 * 11) ,((window_height / 3) + (window_height / 12)));
		preset_add.setFont(new Font("Ariel", Font.BOLD, 35));
		preset_remove.setFont(new Font ("Ariel", Font.BOLD, 35));
		preset_create.setFont(new Font("Ariel", Font.BOLD, 35));
		preset_add.addActionListener(this);
		preset_remove.addActionListener(this);
		preset_create.addActionListener(this);
		j_preset_library.addListSelectionListener(this);
		j_active_presets.addListSelectionListener(this);
		this.add(selection_screen);
		this.setVisible(true);
		
	}

	
	
	
	
	@Override
	public void actionPerformed(ActionEvent eve) { 
		if (eve.getSource() == preset_add)
		{
			if(selection_index != -1)
			{
				
				active_presets.addElement(preset_library.get(selection_index));
			
				
			}
		}
		
		if (eve.getSource() == preset_remove)
		{
			if (remove_index != -1)
			{
			active_presets.remove(remove_index);
			}
		
		}
		
		
		if (eve.getSource() == preset_create)
		{
			
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
			int i = 0;
			remove_index = j_active_presets.getSelectedIndex();
		}
		
		
				
	}
		// TODO Auto-generated method stub
		
	
	

	

}
