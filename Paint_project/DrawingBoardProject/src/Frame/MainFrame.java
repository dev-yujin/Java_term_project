package Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import SubFrame.BottomLabel;
import SubFrame.CenterPanel;
import SubFrame.LeftPanel;
import SubFrame.TopMenu;
import SubFrame.TopPanel;
import information.Information;

public class MainFrame extends JFrame{
	
	private LeftPanel leftPanel;
	private BottomLabel bottomLabel;
	private TopPanel topPanel;
	private TopMenu menuBar;
	private static MainFrame instance;
	
	
	public static MainFrame getInstance()
	{
		if(instance==null)
			instance = new MainFrame(Information.PROGRAM_NAME);
		return instance;
		
	}
	public MainFrame(String name)
	{
	
		super(name);
		
		leftPanel = new LeftPanel();
		bottomLabel = new BottomLabel("Start");
		topPanel = new TopPanel();
			
		this.add(leftPanel, BorderLayout.WEST);
		this.add(bottomLabel, BorderLayout.SOUTH);
		this.add(topPanel,BorderLayout.NORTH);
		
		menuBar = new TopMenu();
		this.setJMenuBar(menuBar);
		this.add(MainDesktopPane.getInstance());
	}
	public void addDrawFrame(String name){
		CenterPanel newFrame = new CenterPanel();
		newFrame.setLocation(0,0);
		newFrame.setSize(1000,800);
		MainDesktopPane.getInstance().addDrawFrameToSet(name, newFrame);
		MainDesktopPane.getInstance().add(newFrame);
	}
	

	public void setBottomLabel(String text)
	{
		Color color =Information.getCurrentColor();
		String mode = Information.getCurrentModeToString();
		bottomLabel.setText("현재 모드 : "+ mode+"   현재 좌표 : "+ text +"    R : "+ color.getRed()+" G : "+ color.getGreen()+" B : "+ color.getBlue());
	}
	
	public void setBottomLabel(Color color)
	{
		String mode = Information.getCurrentModeToString();
		bottomLabel.setText("현재 모드 : "+ mode+"   현재 좌표 : Out of frame"+ "    R : "+ color.getRed()+" G : "+ color.getGreen()+" B : "+ color.getBlue());
	}

	
}
