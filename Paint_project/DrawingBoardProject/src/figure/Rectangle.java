package figure;

import java.awt.Graphics2D;

import information.Information;

public class Rectangle extends RecType{

	
	
	public Rectangle(int startX, int startY,int width,int height)
	{
		super(startX,startY,width,height);

		
	}

	
	@Override
	public void drawFigure(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(figureColor);
		g.fillRect(X,Y,width,height);
	}



	@Override
	public String toString() {
		return "Rectangle [width=" + width + ", height=" + height + "]";
	}

	@Override
	 public Figure clone() { 
			Rectangle obj = new Rectangle(0,0,0,0);
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
