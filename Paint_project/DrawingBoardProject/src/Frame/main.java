package Frame;

import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import information.Information;
public class main {
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainFrame.getInstance().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainFrame.getInstance().setSize(Information.PROGRAM_WIDTH,Information.PROGRAM_HEIGHT);
		MainFrame.getInstance().setVisible(true);
		
		
	}

}
