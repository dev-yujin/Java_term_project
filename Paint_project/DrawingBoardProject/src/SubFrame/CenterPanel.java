package SubFrame;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import Frame.MainDesktopPane;
import SubPanel.DrawPanel;
import information.Information;

public class CenterPanel extends DrawPanel {
	private DrawPanel draw;
	private static CenterPanel instance=null;
		public static CenterPanel getInstance()
		{
			if(instance==null)
				instance = new CenterPanel();
			return instance;
		}
		public CenterPanel()
		{
			setSize(1000,800);
			setBackground(Color.WHITE);
			
			this.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
				
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					changeCursor();//그림판 창안에 들어가면 커서가 바뀌게 설정
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			
			this.setFocusable(true);
			
			setVisible(true);
		}
		public void  replacePanel(DrawPanel panel)
		{
			draw.changeVector(panel.getVector());
			repaint();
		}
	
		public void changeCursor()
		{	  
			Image image;

			
			switch(Information.getCurrentMode())
			{
			case Information.MODE_DRAW_REC : image = Information.getToolkit().getImage("resource/mouse/draw.png"); break;
			case Information.MODE_DRAW_TRIANGLE : image = Information.getToolkit().getImage("resource/mouse/draw.png"); break;
			case Information.MODE_DRAW_CIRCLE : image = Information.getToolkit().getImage("resource/mouse/draw.png"); break;
			case Information.MODE_DRAW_LINE : image = Information.getToolkit().getImage("resource/mouse/draw.png"); break;
			case Information.MODE_ERASE : image = Information.getToolkit().getImage("resource/mouse/Eraser.png"); break;
			case Information.MODE_TEXT : image = Information.getToolkit().getImage("resource/mouse/text.png"); break;
			case Information.MODE_MOVE : image = Information.getToolkit().getImage("resource/mouse/hand.png"); break;
			case Information.MODE_PEN : image = Information.getToolkit().getImage("resource/mouse/pen.png"); break;
			case Information.MODE_PAINT: image = Information.getToolkit().getImage("resource/mouse/Brush.png"); break;
			case Information.MODE_RESIZE :image = Information.getToolkit().getImage("resource/mouse/resize.png"); break;
			default : image = Information.getToolkit().getImage("resource/mouse/normal.png"); break;
			}
			Cursor c = Information.getToolkit().createCustomCursor(image , new Point(15,15), "img");
			this.setCursor (c);
		}
}
