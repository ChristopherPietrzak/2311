package talkbox;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.*;

public class TalkBoxDataObject implements java.io.Serializable, TalkBoxConfiguration {

	public TalkBoxDataObject(int num_audio_buttons, int num_audio_sets, int total_num_buttons , 
			String file_path , String[][] file_names_audio, String[][] file_names_icon, String[][] button_labels,
			String[] presetN) { 
		n_a_b = num_audio_buttons;
		n_a_s = num_audio_sets;
		t_n_b = total_num_buttons;
		fpath = file_path;
		names_audio = file_names_audio;
		names_icon = file_names_icon;	
		labels = button_labels;
		presetNames = presetN;
	}

	private int n_a_b;
	private int n_a_s;
	private int t_n_b;
	private String fpath;
	private String [][] names_audio;
	private String [][] names_icon;
	private String [][] labels;
	private String[] presetNames;

	public int getNumberOfAudioButtons() {
		return n_a_b;
	}

	public int getNumberOfAudioSets() {
		return n_a_s;
	}

	public int getTotalNumberOfButtons() {
		return t_n_b;
	}

	public Path getRelativePathToAudioFiles() {
		return Paths.get(fpath);
	}

	public String[][] getAudioFileNames() {
		return names_audio;
	}

	public String[][] getIconFileNames() {
		return names_icon;
	}
	
	public String[][] getLabels() {
		return labels;
	}
	
	public String[] getPresetNames() {
		return presetNames;
	}
}
