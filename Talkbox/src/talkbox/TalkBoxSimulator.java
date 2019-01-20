
package talkbox;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class TalkBoxSimulator extends JFrame implements ActionListener {
	private String names[] = {"Happy", "Sad", "Bored", "Frustrated", "Excited"};
	private JButton controls[];
	private JLabel i1, i2, i3, i4, i5;
	
	public static void main(String[] args) {
		TalkBoxSimulator simDemo = new TalkBoxSimulator();
		simDemo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public TalkBoxSimulator() {
		Container container = getContentPane();
		JPanel icons = new JPanel();
		icons.setLayout(new GridLayout(1,5));
		i1.setIcon(new ImageIcon("happy.png"));
		i2.setIcon(new ImageIcon("sad.png"));
		i3.setIcon(new ImageIcon("bored.png"));
		i4.setIcon(new ImageIcon("angry.png"));
		i5.setIcon(new ImageIcon("excited.png"));
		icons.add(i1);
		icons.add(i2);
		icons.add(i3);
		icons.add(i4);
		icons.add(i5);
		container.add(icons);
		JPanel buttons = new JPanel();
		buttons.setLayout(new GridLayout(1,5));
		controls = new JButton[names.length];
		for (int counter = 0; counter < controls.length; counter++) {
			controls[counter] = new JButton(names[counter]);
			controls[counter].addActionListener(this);
			buttons.add(controls[counter]);
		}
		container.add(buttons);
		
		Toolkit tk =  Toolkit.getDefaultToolkit();
		
		Dimension screensize = tk.getScreenSize();
		
		this.setSize(((int)(screensize.width * 0.8)),((int) (screensize.height * 0.8)));
		this.setLocation(((int)(screensize.width * 0.1)),((int) (screensize.height * 0.1)));
		this.setVisible(true);
		
		
	}
	
	public void playSound(String clipName) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(clipName).getAbsoluteFile());
			Clip clip = AudioSystem.getClip();
			clip.open(audioInputStream);
			clip.start();
		} catch(Exception e) {
			System.out.println("Clip error");
		}
	}
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == controls[0]) {
			
		}
	}
	

}
