package ppt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class ChessPanel extends JPanel {
	
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
	
	public ChessPanel(int space,int grids) {
		//设置背景色以及大小
		setBackground(Color.YELLOW);
		setSize(space*grids, space*grids);
		chessMenuBar.add(menu);
		menu.add(startMenuItem);
		menu.add(exitMenuItem);
		
		
		
	}
	//绘画
	@Override
	protected void paintComponent(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paintComponent(arg0);
		//画网格
		
		//画棋子
		
	}
	//获得菜单栏，供主函数调用
	public JMenuBar getChessMenuBar() {
		return chessMenuBar;
	}
	
	
	
	
	

}
