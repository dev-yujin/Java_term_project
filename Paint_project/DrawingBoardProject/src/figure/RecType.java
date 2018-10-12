package figure;

import java.awt.Graphics2D;
import java.util.ArrayList;

import figure.PenType.PenTypeTracePoint;

public abstract class RecType extends Figure{
	protected int width,height;
	protected int startX,startY;//말그대로 도형에 무슨짓을 하기전에 일단 초기값을 백업?하는 용도라 보면됨
	protected int X,Y;//ex fillRect(여기, 여기, , )즉 도형의 시작점?으로  moveTo함수에서 이 값을 바꿈으로서 도형의 크기는 유지한채로 이동하는것처럼 보일수 있는것

	
	
	public RecType(int startX, int startY,int width,int height)
	{
		super();
		X=this.startX=startX;
		Y=this.startY=startY;
		this.width=width;
		this.height=height;
	}
	
	
	@Override
	public void setFigureSize(int width, int height) {
		// TODO Auto-generated method stub
		this.width=width;
		this.height=height;
		
	}

	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return width*height;
	}

	@Override
	public abstract void drawFigure(Graphics2D g);

	@Override
	public void calcFigure(int currentX, int currentY) {//도형을 그릴때나 resize할때 매순간 '너비','높이'가 바뀌는것을 저장하기위한 함수
		// TODO Auto-generated method stub


		int wantWidth = currentX-startX;
		int wantHeight = currentY-startY;
		
		setFigureSize(Math.abs(wantWidth),Math.abs(wantHeight));
		
		if(wantWidth<0 && wantHeight <0)
		{
				X=startX+wantWidth;
				Y=startY+wantHeight;
		}
		else if(wantWidth<0 && wantHeight >=0)
		{
			
			X=startX+wantWidth;
		}
		else if(wantWidth>=0 && wantHeight <0)
		{
			Y=startY+wantHeight;
		
		}
		else if(wantWidth>=0 && wantHeight >=0)
		{
			X=startX; Y=startY;
		}
	}
	
	public void moveTo(int curX,int curY)
	{
		X+=curX;
		Y+=curY;
	}
	
	public abstract Figure clone();
}
