package talkbox;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class PresetEditor {
	
	
    private JPanel preset_editor;
    private TalkBoxConfigurationApp talkbox_config_app;
	
	// preset selector objects
	JLabel preset_library;
    JButton back_to_previous;
    JList<String> editor_preset_selector;
	JButton create_new_preset;
	JButton save_current_preset;
	JButton delete_current_preset;

    // preset viewer objects
	JLabel current_preset;
	JButton previous_preset;
	JButton next_preset;

	
	// expression editor objects
	JLabel browse_photos_label;
	JLabel browse_exp_label;
	JLabel exp_pic;
	JComboBox<String> browse_photos;
	JButton upload_photo;
	JButton record_audio;
	JButton play_audio;
	JButton reset;
	JButton save_changes;
	JComboBox<String> browse_exp;
	
	
	
	public PresetEditor(TalkBoxConfigurationApp tbca)
	
	{
	
		preset_editor = new JPanel(null);
		talkbox_config_app = tbca;
		
		// preset selector objects
		
		preset_library = new JLabel("Browse Preset Library: ");
		preset_editor.add(preset_library);
		
		back_to_previous = new JButton("Back To Previous Screen");
		preset_editor.add(back_to_previous);
		
		editor_preset_selector = new JList<String>();
		preset_editor.add(editor_preset_selector);
		
		create_new_preset = new JButton("Create new preset");
		preset_editor.add(create_new_preset);
		
		save_current_preset = new JButton("Save current preset");
		preset_editor.add(save_current_preset);
		
		delete_current_preset = new JButton("delete current preset");
		preset_editor.add(delete_current_preset);
		
	    // preset viewer objects
		current_preset = new JLabel("Current preset : 0");
		preset_editor.add(current_preset);
		
		previous_preset = new JButton("Previous");
		preset_editor.add(previous_preset);
		
		next_preset = new JButton ("Next");
		preset_editor.add(next_preset);

		
		// expression editor objects
		browse_photos_label = new JLabel("Browse Photo Library: ");
		preset_editor.add(browse_photos_label);
		
		browse_exp_label = new JLabel("Browse Expression Library");
		preset_editor.add(browse_exp_label);
		
		exp_pic = new JLabel(new ImageIcon("exp_load.png"));
		preset_editor.add(exp_pic);
		
		browse_photos = new JComboBox<String>();
		preset_editor.add(browse_photos);
		
		upload_photo = new JButton("Upload Photo");
		preset_editor.add(upload_photo);
		
		record_audio = new JButton ("Record Audio");
		preset_editor.add(record_audio);
		
		play_audio = new JButton("Play Audio");
		preset_editor.add(play_audio);
		
		reset = new JButton("reset");
		preset_editor.add(reset);
		
		save_changes = new JButton("save changes to preset");
		preset_editor.add(save_changes);
		
		browse_exp = new JComboBox<String>();
		preset_editor.add(browse_exp);
		
		
		set_component_sizes();
		
		
		
		
		
	}
	
	// converts grid coordinates into a x position on the current window size
	private int grid_x(int x)
 	{
	
		double ratio  = ((double) x) / ((double) 2000);
		
		Rectangle window_size = new Rectangle (talkbox_config_app.getBounds());
		
		double x_position = ratio * ((double) window_size.getWidth());
		
		
		return ((int) x_position);
		
	}
	
	
	private int grid_y(int y)
	{
		 
		double ratio  = ((double) y) / ((double) 1000);
		
		Rectangle window_size = new Rectangle (talkbox_config_app.getBounds());
		
		double y_position = ratio * ((double) window_size.getHeight());  
		
		
		return ((int) y_position);
		
		
	}
	
	// This method Dynamically resizes the components upon window size change
	// to be called when a window size change event is triggered
	public void set_component_sizes()
	{
		
		Rectangle window_size = talkbox_config_app.getBounds();
		preset_editor.setSize(window_size.width , window_size.height);
		preset_editor.setLocation(0,0);
		
		// preset selector objects
		 	
		 back_to_previous.setSize( grid_x(630), grid_y(110) );
		 back_to_previous.setLocation( grid_x(0), grid_y(0) );
		
		 preset_library.setSize(grid_x(350),grid_y(35) );
		 preset_library.setLocation(grid_x(0),grid_y(165));
		 
		 editor_preset_selector.setSize( grid_x(350), grid_y(400) );
		 editor_preset_selector.setLocation( grid_x(0), grid_y(200) );
		 
		 create_new_preset.setSize( grid_x(270), grid_y(90) );
		 create_new_preset.setLocation( grid_x(360), grid_y(200) );
		 
		 save_current_preset.setSize( grid_x(270), grid_y(90) );
		 save_current_preset.setLocation( grid_x(360), grid_y(350) );
		 
		 delete_current_preset.setSize( grid_x(270), grid_y(90) );
		 delete_current_preset.setLocation( grid_x(360), grid_y(510) );

	    // preset viewer objects
		 current_preset.setSize( grid_x(1200), grid_y(50) );
		 current_preset.setLocation( grid_x(0), grid_y(610) );
		 
		 previous_preset.setSize( grid_x(200), grid_y(150) );
		 previous_preset.setLocation( grid_x(1500), grid_y(740) );
		 
		 next_preset.setSize( grid_x(200), grid_y(150) );
		 next_preset.setLocation( grid_x(1740), grid_y(740) );

		
		// expression editor objects
		 exp_pic.setSize( grid_x(440), grid_y(350) );
		 exp_pic.setLocation( grid_x(870), grid_y(0) );
		 
		 browse_photos_label.setSize(grid_x(440), grid_y(40));
		 browse_photos_label.setLocation(grid_x(870),grid_y(400));
		 
		 browse_photos.setSize( grid_x(440), grid_y(75) );
		 browse_photos.setLocation( grid_x(870), grid_y(440) );
		 
		 upload_photo.setSize( grid_x(440), grid_y(75) );
		 upload_photo.setLocation( grid_x(870), grid_y(525) );
		 
		 record_audio.setSize( grid_x(180), grid_y(140) );
		 record_audio.setLocation( grid_x(1520), grid_y(120) );
		 
		 play_audio.setSize( grid_x(180), grid_y(140) );
		 play_audio.setLocation( grid_x(1740), grid_y(120) );
		 
		 browse_exp_label.setSize(grid_x(470), grid_y(35));
		 browse_exp_label.setLocation(grid_x(1480), grid_y(365));
		 
		 browse_exp.setSize( grid_x(470), grid_y(60) );
		 browse_exp.setLocation( grid_x(1480), grid_y(400) );
		 
		 save_changes.setSize( grid_x(470), grid_y(60) );
		 save_changes.setLocation( grid_x(1480), grid_y(470) );
		 
		 reset.setSize( grid_x(470), grid_y(60) );
		 reset.setLocation( grid_x(1480), grid_y(540) );
		
		
	}
	
	
	public JPanel preset_editor_panel()
	{
		return preset_editor;
	}

}
