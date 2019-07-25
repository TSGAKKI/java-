package huaban;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RadialGradientPaint;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ChessPanel extends JPanel  {
	 public static final int MARGIN=0;//边距
	   public static final int GRID_SPAN=35;//网格间距
	   public static final int ROWS=20;//棋盘行数
	   public static final int COLS=20;//棋盘列数
	   public static  int QI=0;
	   private int t1=0,t2=0;
	   Color colortemp;//棋子颜色
	   boolean isBlack=true;
	   
	    boolean gameOver=false;
	   
	//网格的间距
	private int space;
	//棋盘的网格数
	private int grids;
	//棋子的半径
	private int radius;
	//棋盘上的棋子
	//chess[][]=0时，表示网格上无棋子
	//chess[][]=1时，表示网格上放白色棋子
	//chess[][]=2时，表示网格上放黑色棋子
	private int[][] chess;
	//当前棋子的颜色
	//1为黑棋，2为白棋
	private int currColor=1;
	
	private JMenuBar chessMenuBar=new JMenuBar();
	private JMenu menu=new JMenu("操作");
	private JMenuItem startMenuItem=new JMenuItem("开始");
	private JMenuItem exitMenuItem=new JMenuItem("退出");
	 int chessCount;
	 private  Point[] chessList=new Point[(ROWS+1)*(COLS+1)];//初始每个数组元素为null
	public ChessPanel(int space,int grids) {
		Component that=this;
		//设置背景色以及大小
		
		setBackground(Color.gray);
		
		setSize(space*grids, space*grids);
		chessMenuBar.add(menu);
		menu.add(startMenuItem);
		menu.add(exitMenuItem);
		this.addMouseListener(new MouseAdapter() {//设计点击范围触发点击事件
			
			int r=10;
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
//				t1=new precise(e.getX()).getT();
//				t2=new precise(e.getY()).getT();
//				System.out.println(e.getX()+" "+e.getY());
//				t1=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
//				t2=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
				 //游戏结束时，不再能下
				   //if(gameOver) return;
				   
				   String colorName=isBlack?"黑棋":"白棋";
				   
				   //将鼠标点击的坐标位置转换成网格索引
				   System.out.println(e.getX()+" "+e.getY());
				   t1=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
				   t2=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
				
				   
				   //落在棋盘外不能下
				   if(t1<0||t1>ROWS||t2<0||t2>COLS)
					   return;
				   
				   //如果x，y位置已经有棋子存在，不能下
				   //if(findChess(xIndex,yIndex))return;
				   
				   //可以进行时的处理
				   Point ch=new Point(t1,t2,isBlack?Color.black:Color.white);
				   chessList[chessCount++]=ch;
				    repaint();//通知系统重新绘制
				  
				   
				   //如果胜出则给出提示信息，不能继续下棋
				   
				   if(isWin()){
					   String msg=String.format("恭喜，%s赢了！", colorName);
					   JOptionPane.showMessageDialog(that, msg);
					   gameOver=true;
				   }
				   isBlack=!isBlack;
			}
		});		
	}
	//绘画
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		//画网格
		int i=1,n=30,m=30;
		while(i<ROWS) {
	g.setColor(Color.black);   //（0，0）位置绘制一个20*20的绿色方块
	g.drawLine(i*GRID_SPAN, 0, i*GRID_SPAN, 600);
	g.drawLine(0, i*GRID_SPAN, 600, i*GRID_SPAN);
	i++;
		}
		//画棋子
		
		  for( i=0;i<chessCount;i++){
			   //;/网格交叉点x，y坐标
			   int xPos=chessList[i].getX()*GRID_SPAN+MARGIN;
			   int yPos=chessList[i].getY()*GRID_SPAN+MARGIN;
			   g.setColor(chessList[i].getColor());//设置颜色
			   g.fillOval(xPos-Point.DIAMETER/2, yPos-Point.DIAMETER/2,
					           Point.DIAMETER, Point.DIAMETER);
			//   g.drawImage(shadows, xPos-Point.DIAMETER/2, yPos-Point.DIAMETER/2, Point.DIAMETER, Point.DIAMETER, null);
			   colortemp=chessList[i].getColor();
			   if(colortemp==Color.black){
				   RadialGradientPaint paint = new RadialGradientPaint(xPos-Point.DIAMETER/2+25, yPos-Point.DIAMETER/2+10, 20, new float[]{0f, 1f}
	               , new Color[]{Color.WHITE, Color.BLACK});
	               ((Graphics2D) g).setPaint(paint);
	               ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	               ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
	 
			   }
			   else if(colortemp==Color.white){
				   RadialGradientPaint paint = new RadialGradientPaint(xPos-Point.DIAMETER/2+25, yPos-Point.DIAMETER/2+10, 70, new float[]{0f, 1f}
	               , new Color[]{Color.WHITE, Color.BLACK});
	               ((Graphics2D) g).setPaint(paint);
	               ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	               ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT);
	 
			   }
			 
			   Ellipse2D e = new Ellipse2D.Float(xPos-Point.DIAMETER/2, yPos-Point.DIAMETER/2, 34, 35);
			   ((Graphics2D) g).fill(e);
		       //标记最后一个棋子的红矩形框
			   
			   if(i==chessCount-1){//如果是最后一个棋子
				   g.setColor(Color.red);
				   g.drawRect(xPos-Point.DIAMETER/2, yPos-Point.DIAMETER/2,
					           34, 35);
			   }
		   }
		
	}
	//获得菜单栏，供主函数调用
	public JMenuBar getChessMenuBar() {
		return chessMenuBar;
	}
	
	 
	   private boolean isWin(){
		   int continueCount=1;//连续棋子的个数
		  
		   //横向向西寻找
		   for(int x=t1-1;x>=0;x--){
			   Color c=isBlack?Color.black:Color.white;
			   if(getChess(x,t2,c)!=null){
				   continueCount++;
			   }else
				   break;
		   }
	      //横向向东寻找
	       for(int x=t1+1;x<=COLS;x++){
		      Color c=isBlack?Color.black:Color.white;
		      if(getChess(x,t2,c)!=null){
			     continueCount++;
		      }else
			     break;
	       }
	       if(continueCount>=5){
		         return true;
	       }else 
		   continueCount=1;
	       
	       //继续另一种搜索纵向
	       //向上搜索
	       for(int y=t2-1;y>=0;y--){
	    	   Color c=isBlack?Color.black:Color.white;
	    	   if(getChess(t1,y,c)!=null){
	    		   continueCount++;
	    	   }else
	    		   break;
	       }
	       //纵向向下寻找
	       for(int y=t2+1;y<=ROWS;y++){
	    	   Color c=isBlack?Color.black:Color.white;
	    	   if(getChess(t2,y,c)!=null)
	    	       continueCount++;
	           else
	    	      break;
	       
	       }
	       if(continueCount>=5)
	    	   return true;
	       else
	    	   continueCount=1;
	       
	       
	       //继续另一种情况的搜索：斜向
	       //东北寻找
	       for(int x=t1+1,y=t2-1;y>=0&&x<=COLS;x++,y--){
	    	   Color c=isBlack?Color.black:Color.white;
	    	   if(getChess(x,y,c)!=null){
	    		   continueCount++;
	    	   }
	    	   else break;
	       }
	       //西南寻找
	       for(int x=t1-1,y=t2+1;x>=0&&y<=ROWS;x--,y++){
	    	   Color c=isBlack?Color.black:Color.white;
	    	   if(getChess(x,y,c)!=null){
	    		   continueCount++;
	    	   }
	    	   else break;
	       }
	       if(continueCount>=5)
	    	   return true;
	       else continueCount=1;
	       
	       
	       //继续另一种情况的搜索：斜向
	       //西北寻找
	       for(int x=t1-1,y=t2-1;x>=0&&y>=0;x--,y--){
	    	   Color c=isBlack?Color.black:Color.white;
	    	   if(getChess(x,y,c)!=null)
	    		   continueCount++;
	    	   else break;
	       }
	       //东南寻找
	       for(int x=t1+1,y=t2+1;x<=COLS&&y<=ROWS;x++,y++){
	    	   Color c=isBlack?Color.black:Color.white;
	    	   if(getChess(x,y,c)!=null)
	    		   continueCount++;
	    	   else break;
	       }
	       if(continueCount>=5)
	    	   return true;
	       else continueCount=1;
	       
	       return false;
	     }
	
	private Point getChess(int xIndex,int yIndex,Color color){
		   for(Point p:chessList){
			   if(p!=null&&p.getX()==xIndex&&p.getY()==yIndex
					   &&p.getColor()==color)
				   return p;
		   }
		   return null;
	   }
	
	
	

}
