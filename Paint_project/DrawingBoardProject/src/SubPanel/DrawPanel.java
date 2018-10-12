package SubPanel;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.Serializable;
import java.util.Stack;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Frame.MainFrame;
import SubFrame.RightInternalFrame;
import figure.Circle;
import figure.Eraser;
import figure.Figure;
import figure.Line;
import figure.Pen;
import figure.Rectangle;
import figure.Text;
import figure.Triangle;
import information.Information;

public class DrawPanel extends JPanel implements MouseMotionListener, MouseListener, Serializable{
	
	static int LinePotX=0;
	static int LinePotY=0;
	private String filepath = null;
	private Vector<Figure> figureSet = new Vector<Figure>();
	private Stack<Vector<Figure>> figureStack = new Stack<Vector<Figure>>();
	private Stack<Vector<Figure>> figureCancelStack = new Stack<Vector<Figure>>();
	
	private int dragStartX,dragStartY;
	//여기서 스택은 피규를 타입으로 갖는 스택이 아니고 ~~~~ 피규어 타입의 '벡터를 스택에 쌓는다'***figurestack은 undo, redo를 구현하기 위해 이전상태를 저장하는 기능을 갖음
	//생성자가 이 패널을 현재 패널상태에 집어넣음(Information 클래스 안으로 들어감)
	//figureset 얘 없으면 버퍼에 정보는 계속 저장되지만 그림이 안그려짐
	
	//mousedragged 이벤트로 아래 스태이터스바 나타낼수있음, 도형 이동시키기, 리사이즈
	//mousedragged 에서 위의 세개와 채우기 이외에는 무언가를 화면에 추가하는 것이어서 
	
	//mousemoved 이벤트로 아래 스태이터스바 나타낼수있음
	//mouseclicked 이벤트로 움직이기 기능을 쓰기위한 dragstart좌표 받아오기기능/ paint기능
	
	//mousereleased 이벤트로 마우스 버튼을 놓을때 오른쪽 리스트에 도형을 추가하는 기능/ 이 클래스 stack에 객체정보를 저장
	
	/* 상당히 중요한 부분이므로 따로씀
	 * mousepressed 이벤트 부분을 보면 '페인트','움직이기' 가 아닌 else의 경우에  drawFigureFunc()이라는 함수를 호출함. 말그대로 도형을 그리는 함수임
	 * 이 함수의 정의 부분을 보면 현재 그리기 모드가 무엇인지에 따라서 각 케이스에 해당하는 도형을 '''figureset vector'''에 추가한다.
	 * 더 자세히 보면 figureset에 추가하기 전에 마우스 위치의 좌표값을 인자로 각 도형을 생성하는데 이떄 도형객체를 생성할때 생성자에서 그 마우스 좌표값을 
	 */
	
	//벡터인자를 받아 figureset을 통째로 바꿔버리는 기능
	//figureset vector를 반환하는 기능
	//figureset에 vector를 통째로 붙여넣는 기능 / figure객체 하나만 붙여넣는 기능
	
	//figureset과 figureStack을 동시에 싹 날려버리는 기능
	//화면 왼쪽 스택창에서 redo와 undo 기능을 위한 각각의 함수가 있음
	public DrawPanel()
	{
		Information.setCurrentpanel(this);
		this.setBackground(new Color(255,255,255));
		setVisible(true);
		this.setFocusable(true);
		this.addMouseMotionListener(this);
		this.addMouseListener(this);
	
	}
	
	@Override
	public void paintComponent(Graphics g)
	{	
		Graphics2D g2 = (Graphics2D)g;
		super.paintComponent(g2);
	
		for(Figure current:figureSet)
		{
			current.drawFigure(g2);
	
		}
			
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		MainFrame.getInstance().setBottomLabel("X : "+e.getX()+" Y : "+e.getY());	
		
		if(Information.getCurrentMode()==Information.MODE_MOVE)
		{	
			if( Information.getCurrentFigure()!=null)
			{
				int moveX=e.getX()-dragStartX;
				int moveY=e.getY()-dragStartY;
				Information.getCurrentFigure().moveTo(moveX, moveY);
				dragStartX=e.getX();
				dragStartY=e.getY();
				repaint();
			}
			
			
		}
		else if(Information.getCurrentMode()==Information.MODE_PAINT)
		{
			//이걸 해줘야 페인트 모드일때 리사이즈가 안됨
		}
		
		
		else if(Information.getCurrentMode()==Information.MODE_RESIZE)
		{
			if(Information.getCurrentFigure()!=null)
			{
				
				Information.getCurrentFigure().calcFigure(e.getX(), e.getY());
				repaint();
			}
		}
		else
		{
			Figure current = figureSet.lastElement();
			//figureSet.remove(figureSet.lastElement());
			drawCurrentFigureFunc(e, current);//이 함수 안에 repaint()를 하고 있음
		}
		
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		MainFrame.getInstance().setBottomLabel("X : "+e.getX()+" Y : "+e.getY());	
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		Information.setCurrentpanel(this);
		RightInternalFrame.getInstance().setListItems(figureSet);
		if(Information.getCurrentMode()==Information.MODE_MOVE)
		{	
			if(Information.getCurrentFigure()!=null)
			{	
				dragStartX=e.getX();
				dragStartY=e.getY();
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Error : Cant' find figure","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		else if(Information.getCurrentMode()==Information.MODE_PAINT)
		{
			
			if(Information.getCurrentFigure()!=null)
			{	
				Information.getCurrentFigure().setColor(Information.getCurrentColor());
				repaint();
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Error : Cant' find figure","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		//changeCursor();
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		MainFrame.getInstance().setBottomLabel("Out of Frame ");
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		Information.setCurrentpanel(this);
	
		if(Information.getCurrentMode()==Information.MODE_MOVE)
		{	
			if(Information.getCurrentFigure()!=null)
			{	
				dragStartX=e.getX();
				dragStartY=e.getY();
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Error : Cant' find figure","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		else if(Information.getCurrentMode()==Information.MODE_PAINT)
		{
			
			if(Information.getCurrentFigure()!=null)
			{	
				Information.getCurrentFigure().setColor(Information.getCurrentColor());
				repaint();
			}
			else
			{
				JOptionPane.showMessageDialog(null,"Error : Cant' find figure","ERROR",JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
		else
		{
			drawFigureFunc(e);

		}
		
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

		RightInternalFrame.getInstance().setListItems(figureSet);
		
		figureStack.push((Vector<Figure>)figureSet.clone());

		figureCancelStack.clear();

	}
	
	
	
	private void drawFigureFunc(MouseEvent e)
	{
		int mode=Information.getCurrentMode();
		switch(mode)
		{
		case Information.MODE_PEN: 
			Pen pens = new Pen(e.getX(),e.getY());
			figureSet.addElement(pens);
			break;
		
		case Information.MODE_DRAW_REC: 
			Rectangle rec = new Rectangle(e.getX(),e.getY(),0,0);
			figureSet.addElement(rec);
			break;
		case Information.MODE_DRAW_CIRCLE:
			Circle circle = new Circle(e.getX(),e.getY(),0,0);
			figureSet.addElement(circle);
			break;
		case Information.MODE_DRAW_LINE:
			Line line = new Line(e.getX(),e.getY(),e.getX(),e.getY());
			figureSet.addElement(line);
			break;
		case Information.MODE_DRAW_TRIANGLE:
			Triangle triangle = new Triangle(e.getX(),e.getY());
			figureSet.addElement(triangle);
			break;
		case Information.MODE_ERASE:
			Eraser eraser = new Eraser(e.getX(),e.getY());
			figureSet.addElement(eraser);
			break;
		case Information.MODE_TEXT:
			String textData=null;
			if(textData==null || textData.equals("")) textData=JOptionPane.showInputDialog(null,"텍스트를 입력해 주세요",JOptionPane.OK_OPTION);
			if(textData==null || textData.equals("")) return;
			
			Text text = new Text(e.getX(),e.getY(),textData);
			figureSet.addElement(text);
			RightInternalFrame.getInstance().setListItems(figureSet);
			break;
		default : return;
		}
		repaint();
	}
	

	public void drawCurrentFigureFunc(MouseEvent e, Figure temp)
	{
		temp.calcFigure(e.getX(), e.getY());
		repaint();
	}


	public void changeVector(Vector<Figure> vector)
	{
		figureSet=vector;
	}
	public Vector<Figure> getVector()
	{
		return figureSet;
	}
	
	public void addVector(Vector<Figure> addData)
	{
		figureSet.addAll(addData);//벡터를 통째로 붙여넣기 위해 addAll
		RightInternalFrame.getInstance().setListItems(figureSet);
		
		figureStack.push((Vector<Figure>)figureSet.clone());	
		figureCancelStack.clear();
		repaint();
	}
			
	public void addVector(Figure addData)
	{
		figureSet.add((Figure) addData.clone());//figure객체 하나만 붙여넣으니 add
		
		RightInternalFrame.getInstance().setListItems(figureSet);
		figureStack.push((Vector<Figure>)figureSet.clone());	
		figureCancelStack.clear();
		repaint();
	}
	
	public void  clearFigure()
	{
		figureSet.clear();
		figureStack.clear();
		figureCancelStack.clear();
		RightInternalFrame.getInstance().setListItems(figureSet);
		repaint();
	}
	
	public void deleteFigure(int idx)
	{
		figureStack.push((Vector<Figure>)figureSet.clone());	
		figureCancelStack.clear();
		figureSet.remove(idx);
		RightInternalFrame.getInstance().setListItems(figureSet);
		repaint();
	}
	
	public void popStackTrace()//undo할때
	{
		if(figureStack.isEmpty())
		{
			
			figureCancelStack.push((Vector<Figure>)figureSet.clone());
			figureSet.clear();
			repaint();
			RightInternalFrame.getInstance().setListItems(figureSet);
			JOptionPane.showMessageDialog(null,"Error : Cant' find More Action","ERROR",JOptionPane.ERROR_MESSAGE);

		}
		else
		{
			
			figureCancelStack.push((Vector<Figure>)figureSet.clone());		

			
		
			if(figureSet.equals(figureStack.peek())) figureStack.pop();
			if(!figureStack.empty())
			{
				figureSet=(Vector<Figure>)figureStack.peek().clone();
				figureStack.pop();
			}
			else
			{
				figureSet.clear();
			}
			
			
			RightInternalFrame.getInstance().setListItems(figureSet);
			repaint();
		}
		
	}
	public void popStackCaneStack()//redo 할때
	{
		if(figureCancelStack.isEmpty())
		{
			JOptionPane.showMessageDialog(null,"Error : Cant' find More Action","ERROR",JOptionPane.ERROR_MESSAGE);
			return;
		}
		else
		{	
			
			figureStack.push((Vector<Figure>)figureSet.clone());
			figureSet=(Vector<Figure>)figureCancelStack.pop().clone();

			RightInternalFrame.getInstance().setListItems(figureSet);
			repaint();
		}
	
		
	}

	
	
	
}
