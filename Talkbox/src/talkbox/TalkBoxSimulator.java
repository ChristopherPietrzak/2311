
package talkbox;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class TalkBoxSimulator extends JFrame implements ActionListener, TalkBoxConfiguration {
	private JButton controls[][];
	private JButton controlsSingle[];
	private JButton	toggles[];
	private JLabel icons[];
	private CardLayout cardManager;
	private JPanel deck;
	private int lastToggle;
	private boolean morePresetsThanToggles;
	private String[] presets;
	private String[][] audioFiles;
	private int activePreset = 0;
	private Path filePath;

	public static void main(String[] args) {
		// choosing input .tbc file
		boolean filechosen = false;
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"TalkBox Configuration Files", "tbc");
		chooser.setFileFilter(filter); // filtering for only .tbc files
		int returnVal = chooser.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			filechosen = true;
			System.out.println("You chose to open this file: " +
					chooser.getSelectedFile().getName());
		}
		if (filechosen == true) { // loads in the .tbc file to the simulator
			TalkBoxSimulator simDemo = new TalkBoxSimulator(chooser.getSelectedFile().getName());
			simDemo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
	}

	public TalkBoxSimulator(String inFile) {
		// reading in and deserializing talkbox config object
		TalkBoxDataObject tbdo = new TalkBoxDataObject(0, 0, 0, null, null, null, null, null);
		try {
			FileInputStream fileIn = new FileInputStream(inFile);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			tbdo = (TalkBoxDataObject) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("TalkBoxDataObject class not found");
			c.printStackTrace();
			return;
		}
		// preliminary setups
		Container container = getContentPane();
		
		deck = new JPanel();
		
		cardManager = new CardLayout();
		
		deck.setLayout(cardManager);
		
		audioFiles = tbdo.getAudioFileNames();
		
		filePath = tbdo.getRelativePathToAudioFiles();
		
		JButton[][] controls = new JButton[tbdo.getNumberOfAudioSets()][tbdo.getNumberOfAudioButtons()];
		
		presets = tbdo.getPresetNames();
		
		int numToggles = tbdo.getTotalNumberOfButtons() - tbdo.getNumberOfAudioButtons();
		
		if (tbdo.getNumberOfAudioSets() - numToggles > 0) {
			morePresetsThanToggles = true;
		} else {
			morePresetsThanToggles = false;
		}
		
		// toggle setups, only valid for more than 1 preset
		if (tbdo.getNumberOfAudioSets() > 1) {
			if (morePresetsThanToggles == true) {
				JPanel togglePanel = new JPanel();
				togglePanel.setLayout(new GridLayout(numToggles, 1));
				toggles = new JButton[numToggles];
				lastToggle = numToggles - 1;
				for (int a = 0; a < numToggles - 1; a++) {
					toggles[a] = new JButton(presets[a]);
					toggles[a].addActionListener(this);
					togglePanel.add(toggles[a]);
				}
				toggles[lastToggle] = new JButton("Next");
				toggles[lastToggle].addActionListener(this);
				togglePanel.add(toggles[lastToggle]);

				container.add(togglePanel, BorderLayout.EAST);
			} else {
				JPanel togglePanel = new JPanel();
				togglePanel.setLayout(new GridLayout(numToggles, 1));
				toggles = new JButton[numToggles];
				lastToggle = numToggles - 1;
				for (int b = 0; b < numToggles; b++) {
					toggles[b] = new JButton(presets[b]);
					toggles[b].addActionListener(this);
					togglePanel.add(toggles[b]);
				}
				container.add(togglePanel, BorderLayout.EAST);
			}
		}
		
		// icon and audio button setup for more than 1 preset
		if (tbdo.getNumberOfAudioSets() > 1) {
			JPanel[] cards = new JPanel[50];
			for (int i = 0; i < tbdo.getNumberOfAudioSets(); i++) {
				cards[i] = new JPanel();
				cards[i].setLayout(new GridLayout(2, tbdo.getNumberOfAudioButtons()));
				icons = new JLabel[tbdo.getNumberOfAudioButtons()];
				for (int b = 0; b < icons.length; b++) {
					icons[b] = new JLabel(new ImageIcon(filePath + tbdo.getIconFileNames()[i][b])); // need to add filepath
					cards[i].add(icons[b]);
				}

				controls[i] = new JButton[tbdo.getNumberOfAudioButtons()];
				for (int counter = 0; counter < controls.length; counter++) {
					System.out.println(counter);
					controls[i][counter] = new JButton(tbdo.getLabels()[i][counter]);
					final int fcounter = counter;
					controls[i][counter].addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							playSound(filePath + audioFiles[activePreset][fcounter]);
						}
					});
					cards[i].add(controls[i][counter]);
				}
				deck.add(cards[i], tbdo.getPresetNames()[i]);
			}
			container.add(deck);
		} else {
			// creating audio buttons for just 1 preset
			JPanel sim = new JPanel();
			sim.setLayout(new GridLayout(2, tbdo.getNumberOfAudioButtons()));
			System.out.println("2 by " + tbdo.getNumberOfAudioButtons());
			icons = new JLabel[tbdo.getNumberOfAudioButtons()];
			for (int b = 0; b < icons.length; b++) {
				icons[b] = new JLabel(new ImageIcon(filePath + tbdo.getIconFileNames()[0][b]));
				sim.add(icons[b]);
			}

			controlsSingle = new JButton[tbdo.getNumberOfAudioButtons()];
			for (int counter = 0; counter < controlsSingle.length; counter++) {
				controlsSingle[counter] = new JButton(tbdo.getLabels()[0][counter]);
				final int fcounter = counter;
				controlsSingle[counter].addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						playSound(filePath + audioFiles[0][fcounter]);
					}
				});
				sim.add(controlsSingle[counter]);
			}
			container.add(sim);
		}

		// window setup
		Toolkit tk =  Toolkit.getDefaultToolkit();

		Dimension screensize = tk.getScreenSize();

		this.setSize(((int)(screensize.width * 0.8)),((int) (screensize.height * 0.8)));
		this.setLocation(((int)(screensize.width * 0.1)),((int) (screensize.height * 0.1)));
		this.setVisible(true);

	}
	
	// audio playing function
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

	// action handling for toggles
	public void actionPerformed(ActionEvent event) {
		if (presets.length > 1) {
			if (morePresetsThanToggles == true) {
				if (event.getSource() == toggles[lastToggle]) {
					cardManager.next(deck);
					if (++activePreset >= presets.length) {
						activePreset = 0;
					}
					System.out.println("the active preset is " + activePreset);
				} else {
					for (int i = 0; i < lastToggle; i++) {
						if (event.getSource() == toggles[i]) {
							cardManager.show(deck, presets[i]);
							activePreset = i;
						}
					}
				}
			} else if (morePresetsThanToggles == false) {
				for (int i = 0; i < toggles.length; i++) {
					if (event.getSource() == toggles[i]) {
						cardManager.show(deck, presets[i]);
						activePreset = i;
						System.out.println("active preset is now " + activePreset);
					}
				}
			}
		}
	}

	public int getNumberOfAudioButtons() {
		return 0;
	}

	public int getNumberOfAudioSets() {
		return 0;
	}

	public int getTotalNumberOfButtons() {
		return 0;
	}

	public Path getRelativePathToAudioFiles() {
		return null;
	}

	public String[][] getAudioFileNames() {
		return null;
	}

	public String[][] getIconFileNames() {
		return null;
	}

	public String[][] getLabels() {
		return null;
	}

	public String[] getPresetNames() {
		return null;
	}
}
