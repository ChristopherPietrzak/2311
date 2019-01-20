
package talkbox;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class TalkBoxSimulator extends JFrame implements ActionListener {
	private String names[] = {"Happy", "Sad", "Bored", "Angry", "Excited"};
	private JButton controls[];
	
	public static void main(String[] args) {
		TalkBoxSimulator simDemo = new TalkBoxSimulator();
		simDemo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public TalkBoxSimulator() {
		Container container = getContentPane();
		JPanel sim = new JPanel();
		sim.setLayout(new GridLayout(2,5));
		JLabel i1 = new JLabel(new ImageIcon("happy.png"));
		JLabel i2 = new JLabel(new ImageIcon("sad.png"));
		JLabel i3 = new JLabel(new ImageIcon("bored.png"));
		JLabel i4 = new JLabel(new ImageIcon("angry.png"));
		JLabel i5 = new JLabel(new ImageIcon("excited.png"));
		sim.add(i1);
		sim.add(i2);
		sim.add(i3);
		sim.add(i4);
		sim.add(i5);
		controls = new JButton[names.length];
		for (int counter = 0; counter < controls.length; counter++) {
			controls[counter] = new JButton(names[counter]);
			controls[counter].addActionListener(this);
			sim.add(controls[counter]);
		}
		container.add(sim);
		
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
	
	// replace .mp3 files with whatever sound file is appropriate
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == controls[0]) {
			playSound("I'm feeling happy.wav");
		} else if (event.getSource() == controls[1]) {
			playSound("I'm feeling sad.wav");
		} else if (event.getSource() == controls[2]) {
			playSound("I'm feeling bored.wav");
		} else if (event.getSource() == controls[3]) {
			playSound("I'm feeling angry.wav");
		} else if (event.getSource() == controls[4]) {
			playSound("I'm feeling excited.wav");
		}
	}
}
