package figure;

import java.awt.BasicStroke;
import java.awt.Graphics2D;

import javax.swing.JOptionPane;

import information.Information;

public class Circle extends RecType{
	
	
	public Circle(int startX, int startY,int width,int height)
	{
		super(startX,startY,width,height);
	}
	
	@Override
	public void drawFigure(Graphics2D g) {
		g.setColor(figureColor);
		
		g.fillOval(X, Y, width, height);
		
	}

	@Override
	public String toString() {
		return "Circle [width=" + width + ", height=" + height + "]";
	}

	@Override
	 public Figure clone() { 
		Circle obj = new Circle(0,0,0,0);
		obj.startX=startX;
		obj.startY=startY;
		obj.width=width;
		obj.height=height;
		obj.figureColor=figureColor;
		obj.X=X;
		obj.Y=Y;
			
		return obj;
	}
	
}
