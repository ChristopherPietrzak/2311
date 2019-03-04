package talkbox;
import java.awt.*;
import java.text.NumberFormat;
import java.awt.color.ColorSpace;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.NumberFormatter;

import java.util.*;

public class SelectionScreenSetup implements ActionListener , ListSelectionListener, ChangeListener, ComponentListener
{
	
	//Jpanels
	private JPanel selectionScreen; 

	//SelectionScreen visual Components

	JScrollPane toggleList = new JScrollPane();
	JList<Integer> toggleNumbers = new JList<Integer>();
	DefaultListModel<String> preset_library_list = new DefaultListModel<String>();
	DefaultListModel<String> active_preset_names = new DefaultListModel<String>();		
	JList<String> j_preset_library = new JList<String>(preset_library_list);
	JList<String> j_active_presets = new JList<String>(active_preset_names);
	JScrollPane j_p_preset_library = new JScrollPane(j_preset_library);
	JScrollPane j_p_active_presets = new JScrollPane(j_active_presets);
	private JButton preset_add;
	private JButton preset_remove;
	private JButton preset_create;
	private JFormattedTextField toggleNum;
	//private JSpinner spinner;
	ArrayList<JButton> activeButtons = new ArrayList<JButton>();	
	private JLabel pre_lib = new JLabel("Preset Library");
	private JLabel act_pre = new JLabel("Active Presets");
	private JLabel toggle_label = new JLabel("Toggle Buttons");
	
	//SelectionScreen Nonvisual Components
	private TalkBoxConfigurationApp talkBox;
	DefaultListModel<Preset> preset_library = new DefaultListModel<Preset>();
	DefaultListModel<Preset> active_presets = new DefaultListModel<Preset>();
	int selection_index;
	int remove_index;
	ArrayList<Expression> newButtons;
	GridBagLayout editor_layout;
	DefaultListModel<Toggle> toggleLib = new DefaultListModel<Toggle>();
	//Rectangle windowSize;
	
	public SelectionScreenSetup(TalkBoxConfigurationApp tbca)
	{
	
	selectionScreen = new JPanel(null);
	talkBox = tbca;
	
    
	//preset_library = new DefaultListModel<Preset>();
    //active_presets = new DefaultListModel<Preset>();
    //preset_library_list = new DefaultListModel<String>();
    //active_preset_names = new DefaultListModel<String>();		
	
    if(preset_library.size() <= 0)
    {  	
    	// in the actual version, values will be in a file and not hard coded
    	Preset emotions = new Preset(5, "Emotions");
    	emotions.AddButton("angry", "Im_Feeling_Angry.wav", "angry.png");
    	emotions.AddButton("bored", "Im_Feeling_Bored.wav", "bored.png");
    	emotions.AddButton("exited", "Im_Feeling_Excited.wav", "excited.png");
    	emotions.AddButton("happy", "Im_Feeling_Happy.wav", "happy.png");
    	emotions.AddButton("sad", "Im_Feeling_Sad.wav", "sad.png");
    
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
    //j_preset_library = new JList<String>(preset_library_list);
 	//j_active_presets = new JList<String>(active_preset_names);
 	//j_p_preset_library = new JScrollPane(j_preset_library);
 	//j_p_active_presets = new JScrollPane(j_active_presets);
    
	selectionScreen.setLayout(null);
	
	
	selectionScreen.add(toggleList);
	//toggleList.se;
	
	selectionScreen.add(j_p_preset_library);
	selectionScreen.add(j_p_active_presets);
	//selectionScreen.setSize(talkBox.window_width, talkBox.window_height);
	
	j_preset_library.setFont(new Font("Arial", Font.PLAIN, 30));
	j_active_presets.setFont(new Font("Arial", Font.PLAIN, 30));
	preset_add = new JButton("Add Preset");
	preset_remove = new JButton("Remove Preset");
	preset_create = new JButton("Create Preset");	
	selectionScreen.add(preset_add);
	selectionScreen.add(preset_remove);
	selectionScreen.add(preset_create); 	
	
	preset_add.setFont(new Font("Ariel", Font.BOLD, 35));
	preset_remove.setFont(new Font ("Ariel", Font.BOLD, 35));
	preset_create.setFont(new Font("Ariel", Font.BOLD, 35));
	preset_add.addActionListener(this);
	preset_remove.addActionListener(this);
	preset_create.addActionListener(this);
	
	j_preset_library.addListSelectionListener(this);
	j_active_presets.addListSelectionListener(this);
	pre_lib.setFont(new Font("Ariel", Font.PLAIN, 40));
	act_pre.setFont(new Font("Ariel", Font.PLAIN, 40));
	selectionScreen.add(pre_lib);
	selectionScreen.add(act_pre);

	toggle_label.setFont(new Font("Ariel", Font.PLAIN, 28));
	selectionScreen.add(toggle_label);
	
	toggleNum = new JFormattedTextField();
	
	NumberFormat format = NumberFormat.getInstance();
    NumberFormatter formatter = new NumberFormatter(format);
    formatter.setValueClass(Integer.class);
    formatter.setMinimum(0);
    formatter.setMaximum(Integer.MAX_VALUE);
    formatter.setAllowsInvalid(false);
    formatter.setCommitsOnValidEdit(true);
    
	toggleNum.setFont(new Font("Ariel", Font.PLAIN, 40));
	toggleNum.setName("Toggles");
	toggleNum.addActionListener(this);
	//toggleNum.addInputMethodListener(this);
	selectionScreen.add(toggleNum);
	
	putComponents();
	talkBox.getContentPane().add(selectionScreen);
	
	}
	private int gridX(int x)
 	{
		double ratio  = ((double) x) / ((double) 2000);
		Rectangle windowSize = new Rectangle (talkBox.getBounds());
		double xPosition = ratio * ((double) windowSize.getWidth());
		return ((int) xPosition);
	}
	private int gridY(int y)
	{
		double ratio  = ((double) y) / ((double) 1000);
		Rectangle windowSize = new Rectangle (talkBox.getBounds());
		double yPosition = ratio * ((double) windowSize.getHeight());  
		return ((int) yPosition);
	}
	public void putComponents()
	{
		Rectangle windowSize = talkBox.getBounds();
		selectionScreen.setSize(windowSize.width , windowSize.height);
		selectionScreen.setLocation(0,0);
		
		j_p_preset_library.setSize(gridX(775), gridY(600) );
		j_p_active_presets.setSize(gridX(775), gridY(600) );
		j_p_preset_library.setLocation(gridX(0), gridY(0) );
		j_p_active_presets.setLocation(gridX(1200), gridY(0) );
		
		preset_add.setSize( gridX(250) , gridY(125));
		preset_remove.setSize( gridX(250) , gridY(125) );
		preset_create.setSize( gridX(250) , gridY(125) );
		preset_add.setLocation( gridX(863) , gridY(25));
		preset_remove.setLocation( gridX(863) , gridY(165));
		preset_create.setLocation( gridX(863) , gridY(310));
		
		pre_lib.setLocation(gridX(0) , gridY(375));
		act_pre.setLocation(gridX(1200),gridY(375));
		pre_lib.setSize(gridX(500) ,gridY(500));
		act_pre.setSize(gridX(500) ,gridY(500));
		
		toggle_label.setLocation(gridX(863), gridY(425));
		toggle_label.setSize(gridX(250),gridY(100));
		
		toggleNum.setSize(gridX(250), gridY(100));
		toggleNum.setLocation(gridX(863),gridY(500));
		
		if(activeButtons.isEmpty() == false)
		{
			for(JButton Jb : activeButtons)
			{
				Jb.setSize( (int)(0.8 * gridX(2000)) / activeButtons.size(), gridY(250));
				Jb.setLocation( (int) (gridX(2000) * 0.05 / activeButtons.size()) + (gridX(2000) * activeButtons.indexOf(Jb)/  activeButtons.size() ) , gridY(660) );	
			}
		}
		
	
	}
	public void preview_panel()
	{
	
		if(activeButtons.isEmpty() == false)
		{
			for(JButton Jb : activeButtons)
			{
				selectionScreen.remove(Jb);
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
			
			//Jb.setSize((talkBox.window_width * 5 / (6 * newButtons.size()) ), (talkBox.window_height / 5));
			//Jb.setLocation(((talkBox.window_width + 18100 * newButtons.indexOf(b)) / (12 * newButtons.size())) , (165 + (talkBox.window_height / 2 ) ));
			
			Jb.setSize( (int)(0.8 * gridX(2000)) / newButtons.size(), gridY(250));
			Jb.setLocation( (int) (gridX(2000) * 0.05 / newButtons.size()) + (gridX(2000) * newButtons.indexOf(b)/  newButtons.size() ) , gridY(660) );
			
			
			Jb.addActionListener(this);
			
			selectionScreen.add(Jb);	
			
		}
		selectionScreen.updateUI();
		}
		}
//	@Override
//	public void stateChanged(ChangeEvent eve)
//	{
//		
//		
//		if(eve.getSource() == spinner)
//		{
//			System.out.println("listener functioning");
//			int location = Integer.parseInt((String)spinner.getValue());
//			System.out.println(location);
//			toggle_select(location);
//			
//		}
//		
//		
//	}
	
//	@Override
	//public void inputEvent(InputEvent eve)
	{
		//if(eve.getSource() == toggleNum)
		{
		//	toggleSelect(toggleNum.input);
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
			switch_to_editor_panel();
		}
		
		if (eve.getSource() == toggleNum)
		{
			toggleSelect(Integer.parseInt(toggleNum.getText()));
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
	
	private void switch_to_editor_panel() 
	{
		talkBox.getContentPane().removeAll();
		//talkBox.getContentPane().preset_editor.resetView();
		talkBox.getContentPane().add(talkBox.preset_editor.preset_editor_panel());
		talkBox.getContentPane().repaint();
		talkBox.getContentPane().revalidate();
	}

	public void toggleSelect(int toggleNum) throws IndexOutOfBoundsException
	{
		int QuickToggles;
		if (toggleNum <= active_presets.size())
		{
			for(QuickToggles = 0; QuickToggles < toggleNum; QuickToggles++)
			{
				Toggle Jt = new Toggle(active_presets.getElementAt(QuickToggles));
				System.out.println(active_presets.getElementAt(QuickToggles));
				System.out.println("added one preset");
				toggleLib.addElement(Jt);
			}
			
			ArrayList<Preset> in = new ArrayList<Preset>();
			
			for(int i = QuickToggles; i < active_presets.size(); i++)
			{
				in.add(active_presets.getElementAt(i));
			}
			Toggle FJt = new Toggle(in);
			toggleLib.addElement(FJt);
			
		}
		else
		{
			//errorPopup();
			throw new IndexOutOfBoundsException();
		}
		
		
	}
	public void errorPopup()
	{
		JFrame name = new JFrame();
		JButton ok = new JButton();
		ok.addActionListener(this);
		
		PopupMenu poppy = new PopupMenu("error");
		Dialog error = new Dialog(name, "Error");
		
		error.setVisible(true);
		error.add(poppy);
		error.setBounds(gridX(1000), gridY(500), gridX(400), gridY(200));
		name.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public JPanel selectionScreenPanel()
	{
		return selectionScreen;
	}
	
	/**
	 * updates the information in the selectionscreen to the information in the TBCA if changes have been made.
	 */
//	public void updateSS()
//	{
//		this.preset_library = talkBox.preset_library;
//		this.preset_library_list = talkBox.preset_library_list;
//		this.active_presets = talkBox.active_presets;
//		this.active_preset_names = talkBox.active_preset_names;
//		this.j_preset_library = talkBox.j_preset_library;
//		this.j_active_presets = talkBox.j_active_presets;
//		this.j_p_preset_library = talkBox.j_p_preset_library;
//		this.j_p_active_presets = talkBox.j_p_active_presets;
//		this.preset_add = talkBox.preset_add;
//		this.preset_remove = talkBox.preset_remove;
//		this.preset_create = talkBox.preset_create;
//		this.selection_index = talkBox.selection_index;
//		this.remove_index = talkBox.remove_index;
//		this.activeButtons = talkBox.activeButtons;
//		this.newButtons = talkBox.newButtons;
//		this.editor_layout = talkBox.editor_layout;
//		
//	}
//
//	public void updateTBCA()
//	{
//		talkBox.preset_library = this.preset_library;
//		talkBox.preset_library_list = this.preset_library_list;
//		talkBox.active_presets = this.active_presets;
//		talkBox.active_preset_names = this.active_preset_names;
//		talkBox.j_preset_library = this.j_preset_library;
//		talkBox.j_active_presets = this.j_active_presets;
//		talkBox.j_p_preset_library = this.j_p_preset_library;
//		talkBox.j_p_active_presets = this.j_p_active_presets;
//		talkBox.preset_add = this.preset_add;
//		talkBox.preset_remove = this.preset_remove;
//		talkBox.preset_create = this.preset_create;
//		talkBox.selection_index = this.selection_index;
//		talkBox.remove_index = this.remove_index;
//		
//		talkBox.activeButtons = this.activeButtons;
//		talkBox.newButtons = this.newButtons;
//		talkBox.editor_layout = this.editor_layout;
//		
//	}
	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentResized(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}