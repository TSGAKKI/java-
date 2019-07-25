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
	 public static final int MARGIN=0;//�߾�
	   public static final int GRID_SPAN=35;//������
	   public static final int ROWS=20;//��������
	   public static final int COLS=20;//��������
	   public static  int QI=0;
	   private int t1=0,t2=0;
	   Color colortemp;//������ɫ
	   boolean isBlack=true;
	   
	    boolean gameOver=false;
	   
	//����ļ��
	private int space;
	//���̵�������
	private int grids;
	//���ӵİ뾶
	private int radius;
	//�����ϵ�����
	//chess[][]=0ʱ����ʾ������������
	//chess[][]=1ʱ����ʾ�����ϷŰ�ɫ����
	//chess[][]=2ʱ����ʾ�����Ϸź�ɫ����
	private int[][] chess;
	//��ǰ���ӵ���ɫ
	//1Ϊ���壬2Ϊ����
	private int currColor=1;
	
	private JMenuBar chessMenuBar=new JMenuBar();
	private JMenu menu=new JMenu("����");
	private JMenuItem startMenuItem=new JMenuItem("��ʼ");
	private JMenuItem exitMenuItem=new JMenuItem("�˳�");
	 int chessCount;
	 private  Point[] chessList=new Point[(ROWS+1)*(COLS+1)];//��ʼÿ������Ԫ��Ϊnull
	public ChessPanel(int space,int grids) {
		Component that=this;
		//���ñ���ɫ�Լ���С
		
		setBackground(Color.gray);
		
		setSize(space*grids, space*grids);
		chessMenuBar.add(menu);
		menu.add(startMenuItem);
		menu.add(exitMenuItem);
		this.addMouseListener(new MouseAdapter() {//��Ƶ����Χ��������¼�
			
			int r=10;
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
//				t1=new precise(e.getX()).getT();
//				t2=new precise(e.getY()).getT();
//				System.out.println(e.getX()+" "+e.getY());
//				t1=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
//				t2=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
				 //��Ϸ����ʱ����������
				   //if(gameOver) return;
				   
				   String colorName=isBlack?"����":"����";
				   
				   //�������������λ��ת������������
				   System.out.println(e.getX()+" "+e.getY());
				   t1=(e.getX()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
				   t2=(e.getY()-MARGIN+GRID_SPAN/2)/GRID_SPAN;
				
				   
				   //���������ⲻ����
				   if(t1<0||t1>ROWS||t2<0||t2>COLS)
					   return;
				   
				   //���x��yλ���Ѿ������Ӵ��ڣ�������
				   //if(findChess(xIndex,yIndex))return;
				   
				   //���Խ���ʱ�Ĵ���
				   Point ch=new Point(t1,t2,isBlack?Color.black:Color.white);
				   chessList[chessCount++]=ch;
				    repaint();//֪ͨϵͳ���»���
				  
				   
				   //���ʤ���������ʾ��Ϣ�����ܼ�������
				   
				   if(isWin()){
					   String msg=String.format("��ϲ��%sӮ�ˣ�", colorName);
					   JOptionPane.showMessageDialog(that, msg);
					   gameOver=true;
				   }
				   isBlack=!isBlack;
			}
		});		
	}
	//�滭
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		//������
		int i=1,n=30,m=30;
		while(i<ROWS) {
	g.setColor(Color.black);   //��0��0��λ�û���һ��20*20����ɫ����
	g.drawLine(i*GRID_SPAN, 0, i*GRID_SPAN, 600);
	g.drawLine(0, i*GRID_SPAN, 600, i*GRID_SPAN);
	i++;
		}
		//������
		
		  for( i=0;i<chessCount;i++){
			   //;/���񽻲��x��y����
			   int xPos=chessList[i].getX()*GRID_SPAN+MARGIN;
			   int yPos=chessList[i].getY()*GRID_SPAN+MARGIN;
			   g.setColor(chessList[i].getColor());//������ɫ
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
		       //������һ�����ӵĺ���ο�
			   
			   if(i==chessCount-1){//��������һ������
				   g.setColor(Color.red);
				   g.drawRect(xPos-Point.DIAMETER/2, yPos-Point.DIAMETER/2,
					           34, 35);
			   }
		   }
		
	}
	//��ò˵�����������������
	public JMenuBar getChessMenuBar() {
		return chessMenuBar;
	}
	
	 
	   private boolean isWin(){
		   int continueCount=1;//�������ӵĸ���
		  
		   //��������Ѱ��
		   for(int x=t1-1;x>=0;x--){
			   Color c=isBlack?Color.black:Color.white;
			   if(getChess(x,t2,c)!=null){
				   continueCount++;
			   }else
				   break;
		   }
	      //������Ѱ��
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
	       
	       //������һ����������
	       //��������
	       for(int y=t2-1;y>=0;y--){
	    	   Color c=isBlack?Color.black:Color.white;
	    	   if(getChess(t1,y,c)!=null){
	    		   continueCount++;
	    	   }else
	    		   break;
	       }
	       //��������Ѱ��
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
	       
	       
	       //������һ�������������б��
	       //����Ѱ��
	       for(int x=t1+1,y=t2-1;y>=0&&x<=COLS;x++,y--){
	    	   Color c=isBlack?Color.black:Color.white;
	    	   if(getChess(x,y,c)!=null){
	    		   continueCount++;
	    	   }
	    	   else break;
	       }
	       //����Ѱ��
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
	       
	       
	       //������һ�������������б��
	       //����Ѱ��
	       for(int x=t1-1,y=t2-1;x>=0&&y>=0;x--,y--){
	    	   Color c=isBlack?Color.black:Color.white;
	    	   if(getChess(x,y,c)!=null)
	    		   continueCount++;
	    	   else break;
	       }
	       //����Ѱ��
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
