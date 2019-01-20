package talkbox;

import java.nio.file.Path;

public class TalkBoxDataObject implements TalkBoxConfiguration {

	
	public 	TalkBoxDataObject(int num_audio_buttons, int num_audio_sets, int total_num_buttons , Path rel_path , String [][] file_names) 

	{ 
	
	n_a_b = num_audio_buttons;
	n_a_s = num_audio_sets;
	t_n_b = total_num_buttons;
	path = rel_path;
	names = file_names;
	
		
		
	}
	
	private int n_a_b;
	private int n_a_s;
	private int t_n_b;
	private Path path;
	private String [][] names;
	
	
	
	
	
	@Override
	public int getNumberOfAudioButtons() {
		// TODO Auto-generated method stub
		return n_a_b;
	}

	@Override
	public int getNumberOfAudioSets() {
		// TODO Auto-generated method stub
		return n_a_s;
	}

	@Override
	public int getTotalNumberOfButtons() {
		// TODO Auto-generated method stub
		return t_n_b;
	}

	@Override
	public Path getRelativePathToAudioFiles() {
		// TODO Auto-generated method stub
		return path;
	}

	@Override
	public String[][] getAudioFileNames() {
		// TODO Auto-generated method stub
		return names;
	}

}
