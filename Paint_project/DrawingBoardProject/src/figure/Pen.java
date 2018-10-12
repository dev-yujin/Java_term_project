package figure;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.util.ArrayList;

import figure.PenType.PenTypeTracePoint;
import information.*;

public class Pen extends PenType{
	

	private int strokeSize=1;
	
	public Pen(int firstX, int firstY)
	{
		
		super(firstX,firstY);
		strokeSize=Information.getLineSize();
	}

	@Override
	public void drawFigure(Graphics2D g) {
		// TODO Auto-generated method stub
		
		g.setStroke(new BasicStroke(strokeSize,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g.setColor(figureColor);
		for(PenTypeTracePoint current : points)
		{
			g.drawLine(current.prePoints.x, current.prePoints.y, current.LastPoints.x, current.LastPoints.y);
		}
		
	}

	@Override
	public String toString() {
		return "Pen [figureColor=" + figureColor + "]";
	}

	@Override
	 public Figure clone() { 
			Pen obj = new Pen(0,0);			
			obj.preX=preX;
			obj.preY=preY;
			obj.points=(ArrayList<PenTypeTracePoint>)points.clone();
			return obj;
	    }
	
}

