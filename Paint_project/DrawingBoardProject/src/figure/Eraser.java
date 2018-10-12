package figure;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

import figure.PenType.PenTypeTracePoint;
import information.*;

public class Eraser extends PenType{



	public Eraser(int firstX, int firstY)
	{
		
		super(firstX,firstY);
	}

	@Override
	public void drawFigure(Graphics2D g) {
		
		g.setStroke(new BasicStroke(35,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND));
		g.setColor(new Color(255,255,255));
		for(PenTypeTracePoint current : points)
		{
			g.drawLine(current.prePoints.x, current.prePoints.y, current.LastPoints.x, current.LastPoints.y);
		}
		
	}

	@Override
	public String toString() {
		return "Eraser";
	}

	@Override
	 public Figure clone() { 
			Eraser obj = new Eraser(0,0);			
			obj.preX=preX;
			obj.preY=preY;
			obj.points=(ArrayList<PenTypeTracePoint>)points.clone();
			return obj;
	    }

}
