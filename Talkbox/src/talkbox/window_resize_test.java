package talkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.*;

public class window_resize_test extends JFrame implements ComponentListener{

	public static void main(String[] args)
	
	{
		// TODO Auto-generated method stub

		new window_resize_test();
		
		
		
	}

	
	JButton test;
	
	public window_resize_test()
	{
		
		this.setSize(1500,1000);
		
		test = new JButton("YO");
		this.setLayout(null);
		this.setBackground(new Color(50));
		this.add(test);
		test.setSize(40,40);
		test.setLocation(500, 500);
		this.setVisible(true);
		this.addComponentListener(this);
		
	}

	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentMoved(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentResized(ComponentEvent arg0) {
		
		
		 
		 Rectangle window_size = this.getBounds();
		//this.remove(test);
		test.setSize(window_size.width / 5, window_size.height /5 );
		test.setLocation(window_size.width /2 , window_size.height / 2 );
		//this.add(test);
			
		//this.getContentPane().repaint();
		//this.getContentPane().revalidate();
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void componentShown(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
}
