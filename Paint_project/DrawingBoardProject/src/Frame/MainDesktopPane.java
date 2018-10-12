package Frame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

import SubFrame.RightInternalFrame;
import SubFrame.CenterPanel;
import information.Information;

public class MainDesktopPane extends JDesktopPane{
	private HashMap<String, CenterPanel> drawInternalFrameSet;
	private RightInternalFrame rightDownInternalFrame;
	private CenterPanel centerPanel;
	private ImageIcon backgrountIcon;

	private static MainDesktopPane instance;
	
	
	public static MainDesktopPane getInstance()
	{
		if(instance==null)
			instance = new MainDesktopPane();
		return instance;
	}
		
	
	
	public MainDesktopPane()
	{
		Information.setCurrentMainDesktopPane(this);
		this.changeCursor();
		drawInternalFrameSet= new HashMap<String, CenterPanel>();

		Color background = new Color(255,255,255);
		this.setBackground(background);
		this.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		
		backgrountIcon = new ImageIcon("resource/ui_1_01.png");
		

		centerPanel = new CenterPanel();
		rightDownInternalFrame = RightInternalFrame.getInstance();
		

		this.add(rightDownInternalFrame);
		this.add(centerPanel);
	}
	
	public static void setBack(Color color){
		instance.setBackground(color);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		int width = getWidth();
		int height = getHeight();
		rightDownInternalFrame.setLocation(width-300,0);
		centerPanel.setLocation(0,0);
	}

	public void addDrawFrameToSet(String name, CenterPanel newFrame)
	{
		drawInternalFrameSet.put(name, newFrame);
	}
	
	public CenterPanel getDrawFrame()
	{
		return CenterPanel.getInstance();
	}
	
	public void changeCursor()
	{	  
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		Image image;
		
		switch(Information.getCurrentMode())
		{
		case Information.MODE_DRAW_REC : image = toolkit.getImage("resource/mouse/draw.png"); break;
		case Information.MODE_DRAW_TRIANGLE : image = toolkit.getImage("resource/mouse/draw.png"); break;
		case Information.MODE_DRAW_CIRCLE : image = toolkit.getImage("resource/mouse/draw.png"); break;
		case Information.MODE_DRAW_LINE : image = toolkit.getImage("resource/mouse/draw.png"); break;
		case Information.MODE_ERASE : image = toolkit.getImage("resource/mouse/Eraser.png"); break;
		case Information.MODE_TEXT : image = toolkit.getImage("resource/mouse/text.png"); break;
		case Information.MODE_MOVE : image = toolkit.getImage("resource/mouse/hand.png"); break;
		case Information.MODE_PEN : image = toolkit.getImage("resource/mouse/pen.png"); break;
		case Information.MODE_PAINT: image = toolkit.getImage("resource/mouse/Brush.png"); break;
		case Information.MODE_RESIZE :image = toolkit.getImage("resource/mouse/resize.png"); break;
		default : image = toolkit.getImage("resource/mouse/normal.png"); break;
		}
		
		Cursor c = toolkit.createCustomCursor(image , new Point(15,15), "img");
		this.setCursor (c);
	}
	
	@Override
		public synchronized void addKeyListener(KeyListener l) {
			super.addKeyListener(l);
		}
}
