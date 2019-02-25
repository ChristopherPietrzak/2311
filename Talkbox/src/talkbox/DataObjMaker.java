package talkbox;

import java.io.*;
import java.nio.file.Paths;
public class DataObjMaker {

	public static void main(String[] args) {

		String [][] audio_array = {{"I'm feeling happy.wav","I'm feeling sad.wav"},
				{"I'm feeling angry.wav", "I'm feeling bored.wav"}};
		String [][] icon_array = {{"happy.png", "sad.png"}, {"angry.png", "bored.png"}};
		String [][] label_array = {{"Happy", "Sad"}, {"Angry", "Bored"}};
		String[] preset_array = {"Emotions1", "Emotions2"};

		TalkBoxDataObject temp_obj = new TalkBoxDataObject(2,2,4, "", audio_array, icon_array, label_array, preset_array);

		try {
			FileOutputStream fileOut = new FileOutputStream("talkbox.tbc");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(temp_obj);
			out.close();
			fileOut.close();
			System.out.printf("Serialized data is saved successfully");
		} catch (IOException i) {
			i.printStackTrace();
		}
	}
}