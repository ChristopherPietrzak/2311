package talkbox;
import java.awt.*;
import java.awt.color.ColorSpace;
import java.awt.event.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream; 
import javax.sound.sampled.AudioSystem; 
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.TargetDataLine;
import javax.sound.sampled.UnsupportedAudioFileException; 


public class PresetEditor implements ActionListener, ListSelectionListener

{
	
	
    private JPanel preset_editor;
    private TalkBoxConfigurationApp talkbox_config_app;
	
	// preset selector objects
	JLabel preset_library;
    JButton back_to_previous;
    
    JList<String> preset_selector_jlist;
    JScrollPane editor_preset_selector;
    
	JButton create_new_preset;
	JButton delete_current_preset;
	int preset_list_index;
	
	

    // preset viewer objects
	JLabel current_preset_indicator;
	JButton previous_preset;
	JButton next_preset;
	JLabel step_1;
	ArrayList<JButton> edit_button_list;
	ArrayList<JLabel> preset_view_image_list;
	boolean first_selection;

	
	// expression editor objects
	ImageIcon step_2;
	JLabel exp_pic;
	JButton upload_photo;
	JButton record_audio;
	JButton play_audio;
	JButton reset;
	JButton save_changes;
	boolean is_exp_initialized;
	Clip current_audio;
	AudioInputStream audio_input_stream;
	TargetDataLine data_line;
	AudioFormat audio_format;
	DataLine.Info info;
	OutputStream audio_output_stream;
	AudioInputStream recording_input_stream;
	String wave_name;
	
	
	 // data model objects
	 
	 Preset current_preset;
	 Expression current_exp;
	 Expression current_exp_copy;
	 String current_image;
	 int  preset_image_index;
	 
	
	
	public PresetEditor(TalkBoxConfigurationApp tbca) 
	
	{
	
		preset_editor = new JPanel(null);
		talkbox_config_app = tbca;
		
		// preset selector objects
		
		preset_library = new JLabel("Browse Preset Library: ");
		preset_editor.add(preset_library);
		
		back_to_previous = new JButton("Back To Previous Screen");
		preset_editor.add(back_to_previous);
		back_to_previous.addActionListener(this);
	
		
		
		//preset_selector_jlist = new JList<String>(talkbox_config_app.preset_library_list);
		//editor_preset_selector = new JScrollPane(preset_selector_jlist);
		//preset_editor.add(editor_preset_selector);
		//preset_selector_jlist.addListSelectionListener(this);
		
		preset_selector_jlist = new JList<String>(talkbox_config_app.slscsu.preset_library_list);
		editor_preset_selector = new JScrollPane(preset_selector_jlist);
		preset_editor.add(editor_preset_selector);
		preset_selector_jlist.addListSelectionListener(this);
		
	
		
		create_new_preset = new JButton("Create new preset");
		preset_editor.add(create_new_preset);
		create_new_preset.addActionListener(this);
		//create_popup = new JOptionPane("Create A New Preset");
		
		
		delete_current_preset = new JButton("delete current preset");
		preset_editor.add(delete_current_preset);
		delete_current_preset.addActionListener(this);
		//delete_popup = new JOptionPane("Delete Current Preset");
		
	    // preset viewer objects
		current_preset_indicator = new JLabel("Current preset : 0");
		preset_editor.add(current_preset_indicator);
		
		previous_preset = new JButton("Previous");
		preset_editor.add(previous_preset);
		previous_preset.addActionListener(this);
		
		next_preset = new JButton ("Next");
		preset_editor.add(next_preset);
		next_preset.addActionListener(this);
		
		step_1 = new JLabel(new ImageIcon("step_1.png"));
		preset_editor.add(step_1);
		
		edit_button_list = new ArrayList<JButton>();
		
		preset_view_image_list  = new ArrayList<JLabel>();
		
		

		
		// expression editor objects
		
		step_2 = new ImageIcon("exp_load.png");
		
		
		exp_pic = new JLabel(step_2);
		preset_editor.add(exp_pic);
		
		upload_photo = new JButton("Upload Photo");
		preset_editor.add(upload_photo);
		upload_photo.addActionListener(this);
		
		record_audio = new JButton ("Record Audio");
		preset_editor.add(record_audio);
		record_audio.addActionListener(this);
		
		play_audio = new JButton("Play Audio");
		preset_editor.add(play_audio);
		play_audio.addActionListener(this);
		
		reset = new JButton("reset");
		preset_editor.add(reset);
		reset.addActionListener(this);
		
		save_changes = new JButton("save changes to preset");
		preset_editor.add(save_changes);
		save_changes.addActionListener(this);
		wave_name = "test7.wav";
		
		audio_format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED , 44100, 16, 2, 4, 44100, false);
		
		info = new DataLine.Info(TargetDataLine.class,audio_format);
		try
		{
			data_line = (TargetDataLine) AudioSystem.getLine(info);
			data_line.open(audio_format);
			audio_output_stream = new FileOutputStream("Test2.wav");
			
		} 
		
		catch (LineUnavailableException e) 
		{
			e.printStackTrace();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		data_line.isOpen();
		
		// data model initialization
		current_preset = null;
		current_exp = null;
		
		
		preset_list_index = -1;
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

		 delete_current_preset.setSize( grid_x(270), grid_y(90) );
		 delete_current_preset.setLocation( grid_x(360), grid_y(320) );

	    // preset viewer objects
		 current_preset_indicator.setSize( grid_x(1200), grid_y(50) );
		 current_preset_indicator.setLocation( grid_x(0), grid_y(610) );
		 
		 previous_preset.setSize( grid_x(200), grid_y(150) );
		 previous_preset.setLocation( grid_x(1500), grid_y(740) );
		 
		 next_preset.setSize( grid_x(200), grid_y(150) );
		 next_preset.setLocation( grid_x(1740), grid_y(740) );
		 
		 step_1.setSize(grid_x(1400), grid_y(300));
		 step_1.setLocation( grid_x(0) , grid_y(680));
		 
		 

		
		// expression editor objects
		 exp_pic.setSize( grid_x(440), grid_y(350) );
		 exp_pic.setLocation( grid_x(980), grid_y(100) );

		 upload_photo.setSize( grid_x(440), grid_y(75) );
		 upload_photo.setLocation( grid_x(980), grid_y(455) );
		 
		 record_audio.setSize( grid_x(180), grid_y(140) );
		 record_audio.setLocation( grid_x(1520), grid_y(120) );
		 
		 play_audio.setSize( grid_x(180), grid_y(140) );
		 play_audio.setLocation( grid_x(1740), grid_y(120) );
		 
		 
		 save_changes.setSize( grid_x(470), grid_y(60) );
		 save_changes.setLocation( grid_x(1480), grid_y(400) );
		 
		 reset.setSize( grid_x(470), grid_y(60) );
		 reset.setLocation( grid_x(1480), grid_y(470) );
		 
		 
		
		
	}
	
	
	public JPanel preset_editor_panel()
	{
		return preset_editor;
	}
	
	
	private void switch_to_selector_panel()
	{
		talkbox_config_app.getContentPane().removeAll();
		talkbox_config_app.getContentPane().add(talkbox_config_app.slscsu.selectionScreenPanel());
		talkbox_config_app.getContentPane().repaint();
		talkbox_config_app.getContentPane().revalidate();
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e)
	{
	
		if(e.getSource() == create_new_preset )
		{
			create_new_preset_input();
		}
		
		if (e.getSource() == delete_current_preset)
		{
			delete_current_preset_action();
		}
		
		
		if (e.getSource() == back_to_previous )
		{
			switch_to_selector_panel();
		}
		
		if(e.getSource() == previous_preset)
		{
			
			switch_to_previous_preset();
			
		}
		
		if (e.getSource() == next_preset)
		{
			
			switch_to_next_preset();
			
		}
		
		
		for (int i = 0; i < edit_button_list.size(); i++ )
		{
			
			if( e.getSource() == edit_button_list.get(i))
			{
			    
				edit_exp(current_preset.getButtonAt(i));
				preset_image_index = i;
				
			}
			
		}
		
		
		if(e.getSource() == reset )
		{
			reset_expression();
		}
		
		
		if(e.getSource() == save_changes)
		{
			save_expression();
		}
		
		
		if(e.getSource() == upload_photo)
		{
			if (current_exp != null)
				{
					upload_photo_action();
				}
		}
		
		if(e.getSource() == play_audio)
		{
			play_audio_action();
		}
		if (e.getSource() == record_audio)
		{
			record_audio_action();
		}
		
	}

	private void record_audio_action()
	{
		if(current_exp != null)
			{boolean timer_on = true;
			long start_time = System.currentTimeMillis();
			data_line.start();
			recording_input_stream = new AudioInputStream(data_line);
			File wav_file = new File(wave_name);
		
			while(timer_on)
			{
			
			
			
		    
				try 
				{
					AudioSystem.write(recording_input_stream, AudioFileFormat.Type.WAVE, wav_file);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				}
				System.out.println("success");
			
				if (((System.currentTimeMillis() - start_time) / 1000) > 6)
				{
					timer_on = false;
					data_line.stop();
				}
			
			}
			System.out.print("Sucess");
		
			try 
			{
				audio_input_stream = AudioSystem.getAudioInputStream(new File (wave_name));
				current_audio = AudioSystem.getClip();
				current_audio.open(audio_input_stream);
			} 
		
		
			catch (UnsupportedAudioFileException | IOException e) 
			{
				e.printStackTrace();
			}
		
			catch (LineUnavailableException e)
			{
				e.printStackTrace();
			}
		
		}
	}

	private void play_audio_action()
	{
		if (current_exp != null)
			{
			current_audio.start();
			}
		
	}

	private void upload_photo_action() 
	{
		
		
		JFileChooser fc = new JFileChooser();
		String[] image_formats = { "jpeg","JPEG", "png", "PNG", "gif", "GIF"};
		FileNameExtensionFilter image_filter = new FileNameExtensionFilter("Acceptable file Formats" , image_formats);
		fc.addChoosableFileFilter(image_filter);
		
		if(fc.showOpenDialog(upload_photo) == JFileChooser.APPROVE_OPTION )
		{
			// to do find out how to write custom names for the files take input from user
			// see if the formats can be determined automatically

			String image_name = fc.getSelectedFile().getAbsolutePath();
			String new_image_name = "./Photos/" + System.currentTimeMillis() + image_name.substring((image_name.length() - 4));
			
		     try 
		     {
				Files.copy( Paths.get(fc.getSelectedFile().getAbsolutePath()), Paths.get(new_image_name), StandardCopyOption.REPLACE_EXISTING);
				exp_pic.setIcon(new ImageIcon(new_image_name));
				current_image = new_image_name;
				preset_editor.repaint();
				preset_editor.revalidate();
		     } 
		     
		     catch (IOException e)
		     {
				System.out.println("you messed up");
		     }
			// to do format
			//set image icon path, change the copy current exp
			
		}
		
	}

	private void save_expression()
	{
		if (current_exp != null)
		{
			current_exp.SetIconPath(current_image);
			preset_view_image_list.get(preset_image_index).setIcon(new ImageIcon(current_image));
			// to do add the change for the audio
			preset_editor.repaint();
			preset_editor.revalidate();
		}
	}

	private void reset_expression() 
	{
		
		if(current_exp != null)
		{
			//to do add the reset for the audio
			current_image = current_exp.GetIconPath();
			current_exp_copy = current_exp;
			exp_pic.setIcon(new ImageIcon(current_exp.GetIconPath()));
			
			try 
			{
				audio_input_stream = AudioSystem.getAudioInputStream(new File (wave_name));
				current_audio = AudioSystem.getClip();
				current_audio.open(audio_input_stream);
			} 
			
			
			catch (UnsupportedAudioFileException | IOException e) 
			{
				e.printStackTrace();
			}
			
			catch (LineUnavailableException e)
			{
				e.printStackTrace();
			}
		}
		
		
	}

	private void delete_current_preset_action() 
	{

		if(preset_selector_jlist.getSelectedIndex() == -1)
		{
			JOptionPane.showMessageDialog(preset_editor, "Cannot delete a preset as there is no preset currrently selected");
		}
	    
		else
		{
			if (preset_selector_jlist.getSelectedIndex() < 4)
			{
				JOptionPane.showMessageDialog(preset_editor, "Error you cannot delete default presets, you may however delete any user created presets");
			}
			
			else 
			{
				int selection  = 	JOptionPane.showConfirmDialog(preset_editor, "Are you sure you want to delete this preset?");
	     
				if (selection == 0 )
				{
					// this if statement stops the deletion of default presets that came with software
					talkbox_config_app.preset_library.remove(preset_selector_jlist.getSelectedIndex());
					talkbox_config_app.preset_library_list.remove(preset_selector_jlist.getSelectedIndex());
					preset_selector_jlist.clearSelection();
					//preset_selector_jlist.setSelectedIndex(0);
					resetView();
				
				}
			}
	    
		}
		
		
	}

	private void create_new_preset_input() 
	{
	
		String new_preset_name = null;
	    int num_exps = -1;
		boolean name_passed_checks = false;
		//this method will check if the input is non empty and does not already
		//exists if yes to both then it will create a new preset with that name
		// will also ask for the amount of buttons for the preset
		// cancel(null value) will cancel the operation
		
		
		while (name_passed_checks == false)
		{
	
			new_preset_name = JOptionPane.showInputDialog("Please enter the new preset name");
		   
			if (new_preset_name == null)
			{
				name_passed_checks = true;
			}
			else 
			
			{
				boolean empty_input = new_preset_name.equals("");
				if(empty_input)
				{
					JOptionPane.showMessageDialog( preset_editor , "Empty names are not allowed, Please enter a non empty name");
				}
				
				boolean exists = false;
				
		   
				for (int i = 0; i < talkbox_config_app.slscsu.preset_library_list.size(); i++)
				{
					if(new_preset_name.equals(talkbox_config_app.slscsu.preset_library_list.get(i)))
					{
		    		exists = true;
		    		JOptionPane.showMessageDialog( preset_editor , "Name already exists, please input a new name");
					}
				}
		    		
				if ((!empty_input) && (!exists))
				{
					name_passed_checks = true;
				}
			}
			
		
			
		
			
			
		}
		
		// an array holding the numbers 1-20 as strings
		String[] numbers;
		numbers = new String[20];
		for(int i = 0; i < 20; i++)
		{
			numbers[i] = Integer.toString(i+1);
		}
		
		
		if(new_preset_name != null)
		{
	
			String x = (String) JOptionPane.showInputDialog(preset_editor,"Please select the number of audio buttons", "Create preset options",JOptionPane.PLAIN_MESSAGE, null,numbers,"1");
			num_exps = Integer.parseInt(x);

		}
		
		
		// if the input is okay then finally create the new preset
		if (new_preset_name != null && num_exps != -1)
		{
			create_preset_action(new_preset_name, num_exps);
		}
		
		
		
		
	}

	
	private void create_preset_action(String new_preset_name, int num_exps) 
	
	{
	
			Preset temp = new Preset(num_exps , new_preset_name);
			for (int i = 0; i < num_exps; i++)
			{
				temp.AddButton(new Expression("Blank", null, "exp_empty.png"));
			
			}
			
			talkbox_config_app.slscsu.preset_library.addElement(temp);
			talkbox_config_app.slscsu.preset_library_list.addElement(temp.GetName());
			preset_selector_jlist.setSelectedIndex(talkbox_config_app.slscsu.preset_library_list.getSize() - 1);
			preset_view();
			
			
			
			
			
	}

	@Override
	public void valueChanged(ListSelectionEvent e)
	
	{
		
		if (e.getSource() == preset_selector_jlist )
		{
			preset_view();
		}
		
		
		
	}

	private void preset_view()
	{
		
		exp_pic.setIcon(step_2);
		if (current_preset == null)
		{
			preset_editor.remove(step_1);
		}
		
		for( int i = 0; i < edit_button_list.size(); i++)
		{
			
			preset_editor.remove(edit_button_list.get(i));
			preset_editor.remove(preset_view_image_list.get(i));
	
		}
		
		current_preset_indicator.setText("Current Preset:      " + preset_selector_jlist.getSelectedValue());
		
		edit_button_list = new ArrayList<JButton>();
		preset_view_image_list = new ArrayList<JLabel>();
		
		current_preset = talkbox_config_app.slscsu.preset_library.get( preset_selector_jlist.getSelectedIndex());
		
		
		for (int i = 0; i < current_preset.getButtonNum(); i++)
		{
			JButton current_button = new JButton("Edit");
			JLabel current_image = new JLabel (new ImageIcon(current_preset.getButtonAt(i).GetIconPath()));
			edit_button_list.add(current_button);
			current_button.addActionListener(this);
			preset_view_image_list.add(current_image);
			
			preset_editor.add(current_button);
			preset_editor.add(current_image);
			
		
		    int image_y_dimension = 200;
		    if ((1400 / current_preset.getButtonNum()) < (image_y_dimension))
		    		{
		    		image_y_dimension =( (1400 / current_preset.getButtonNum()));
		    		}
			
			current_image.setSize(grid_x(1400 / current_preset.getButtonNum()), grid_y(image_y_dimension));
			current_image.setLocation(grid_x((1400 /current_preset.getButtonNum() * i )+ 20),grid_y(680));
			
			current_button.setSize(grid_x(1400 / current_preset.getButtonNum()), grid_y(50));
			current_button.setLocation(grid_x((1400/ current_preset.getButtonNum() * i)+ 20 ), grid_y(900));
			
		}
		
		preset_editor.repaint();
		preset_editor.revalidate();
		
	}
	
	public void resetView()
	{
		for( int i = 0; i < edit_button_list.size(); i++)
		{
			
			preset_editor.remove(edit_button_list.get(i));
			preset_editor.remove(preset_view_image_list.get(i));
	
		}
		
		
		
		preset_editor.add(step_1);
		exp_pic.setIcon(step_2);
		
		 step_1.setSize(grid_x(1400), grid_y(300));
		 step_1.setLocation( grid_x(0) , grid_y(680));
		 
		 current_preset = null;
		 current_exp = null;
		 
		 // to do reset the expression view as well
		
	}
	
	public void edit_exp(Expression exp)
	{
		
		// to do add the the initialization for the current audio
		current_exp = exp;
		current_image = current_exp.GetIconPath();
		exp_pic.setIcon(new ImageIcon(current_exp.GetIconPath()));
		try 
		{
			audio_input_stream = AudioSystem.getAudioInputStream(new File (exp.GetAudioPath()));
			current_audio = AudioSystem.getClip();
			current_audio.open(audio_input_stream);
		} 
		
		
		catch (UnsupportedAudioFileException | IOException e) 
		{
			e.printStackTrace();
		}
		
		catch (LineUnavailableException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void switch_to_previous_preset()
	{
		if (current_preset != null)
		{
			int index = preset_selector_jlist.getSelectedIndex();
			index-- ;
			
			if(index < 0 && preset_selector_jlist.getModel().getSize() > 0)
			{
				index = ((preset_selector_jlist.getModel().getSize())- 1);
			}
		
			preset_selector_jlist.setSelectedIndex(index);
			preset_view();
			exp_pic.setIcon(step_2);
			current_exp = null;
	 
		}
		 
	}
	 
	 public void switch_to_next_preset()
	 {
		 if (current_preset != null)
			{
				int index = preset_selector_jlist.getSelectedIndex();
				index++ ;
				
				if(index >= preset_selector_jlist.getModel().getSize() && preset_selector_jlist.getModel().getSize() > 0)
				{
					index = 0;
				}
			
				preset_selector_jlist.setSelectedIndex(index);
				preset_view();
				exp_pic.setIcon(step_2);
				current_exp = null;
			
		 
			}
		 
		 
		 
	 }
	
	
}
