package figure;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.Arrays;

import information.Information;

public class Triangle extends Figure{
	
	private int[] xPoints ={0,0,0};
	private int[] yPoints ={0,0,0};
	
	
	public Triangle(int startX, int startY)
	{
		super();
		this.xPoints[0]=this.xPoints[1]=this.xPoints[2]=startX;
		this.yPoints[0]=this.yPoints[2]=this.yPoints[2]=startY;
	}
	
	
	
	
	@Override
	public void setFigureSize(int xPoint, int yPoint) {
		
		xPoints[2]=xPoint;
		yPoints[2]=yPoint;
		
		xPoints[1] = xPoints[2]-(xPoint-xPoints[0])*2;
		yPoints[1]=yPoint;
	}

	@Override
	public int getSize() {
		
		int width = Math.abs(xPoints[2]-xPoints[1]);
		int height = Math.abs(yPoints[0]-yPoints[1]);
		
		return width*height/2;
	}

	@Override
	public void drawFigure(Graphics2D g) {
		g.setColor(figureColor);
		g.fillPolygon(xPoints, yPoints, 3);
	}

	@Override
	public void calcFigure(int currentX, int currentY) {
		// TODO Auto-generated method stub
		setFigureSize(currentX,currentY);
	}
	
	public void moveTo(int curX,int curY)
	{
		xPoints[0]+=curX;
		xPoints[1]+=curX;
		xPoints[2]+=curX;
		
		yPoints[0]+=curY;
		yPoints[1]+=curY;
		yPoints[2]+=curY;		
	}


	@Override
	public String toString() {
		return "Triangle [xPoints=" + Arrays.toString(xPoints) + ", yPoints=" + Arrays.toString(yPoints) + "]";
	}
	
	
	@Override
	 public Figure clone() { 
			Triangle obj = new Triangle(0,0);
			obj.xPoints=xPoints.clone();
			obj.yPoints=yPoints.clone();
			return obj;
	    }
	
	
	
}
