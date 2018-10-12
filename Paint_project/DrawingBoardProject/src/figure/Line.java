package figure;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

import information.Information;

public class Line extends Figure{

	
	private int endX,endY;
	private int startX,startY;
	private int strokeSize=1;
	
	
	public Line(int startX, int startY,int endX,int endY)
	{
		super();
		this.startX=startX;
		this.startY=startY;
		this.endX=endX;
		this.endY=endY;
		strokeSize=Information.getLineSize();
	}
	
	
	@Override
	public void setFigureSize(int endX, int endY) {
		// TODO Auto-generated method stub
		this.endX=endX;
		this.endY=endY;
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		int deltaX = endX-startX;
		int deltaY = endY-startY;
		
		double size = Math.sqrt(Math.pow(deltaX, 2)+Math.pow(deltaY, 2));
		return (int)size;
	}

	@Override
	public void drawFigure(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setStroke(new BasicStroke(strokeSize,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g.setColor(figureColor);
		g.drawLine(startX, startY, endX, endY);
	}

	@Override
	public void calcFigure(int currentX, int currentY) {
		// TODO Auto-generated method stub
		setFigureSize(currentX,currentY);
	}
	
	public void moveTo(int curX,int curY)
	{
		startX+=curX;
		startY+=curY;
		endX+=curX;
		endY+=curY;
	}




	@Override
	public String toString() {
		return  "Line "
				+ "[startX=" + startX + ", startY=" + startY+" endX=" + endX + ", endY=" + endY + "]";
	}
	
	@Override
	 public Figure clone() { 
			Line obj = new Line(0,0,0,0);			
			obj.endX=endY;
			obj.endY=endY;
			obj.startX=startX;
			obj.startY=startY;
			obj.strokeSize=strokeSize;
			return obj;
	    }


}
