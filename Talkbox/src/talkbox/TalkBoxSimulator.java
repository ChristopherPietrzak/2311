
package talkbox;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.*;

public class TalkBoxSimulator extends JFrame
{
	

	public static void main(String[] args)
	
	{
		// TODO Auto-generated method stub
		new TalkBoxSimulator();
					
	}
	
	private TalkBoxDataObject config_info; 
	
	public TalkBoxSimulator()
	{
		
		Toolkit tk =  Toolkit.getDefaultToolkit();
		
		Dimension screensize = tk.getScreenSize();
		
		this.setSize(((int)(screensize.width * 0.8)),((int) (screensize.height * 0.8)));
		this.setLocation(((int)(screensize.width * 0.1)),((int) (screensize.height * 0.1)));
		this.setVisible(true);
		
		
	}
	

}
