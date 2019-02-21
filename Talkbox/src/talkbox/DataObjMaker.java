package talkbox;

import java.io.*;
import java.nio.file.Paths;
public class DataObjMaker {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
		
		String [][] audio_array = {{"I'm feeling angry.wav","I'm feeling bored.wav","I'm feeling happy.wav"}};
		String [][] icon_array = {{"angry.png", "bored.png", "happy.png"}};
		
		
		
		TalkBoxDataObject temp_obj = new TalkBoxDataObject(3,1,6, Paths.get(""), audio_array, icon_array );
		
		FileOutputStream output_file = new FileOutputStream("DataObject.tbc");
		ObjectOutputStream output_obj = new ObjectOutputStream(output_file);
		output_obj.writeObject(temp_obj);
		output_obj.close();
		output_file.close();
		System.out.println("Success");
			}
		
		catch (IOException exce)
		{
			
			System.out.println("output file exception triggered");
		}
	}
}
