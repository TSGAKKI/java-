package ppt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.LayoutManager;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class ChessPanel extends JPanel {
	
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
	
	public ChessPanel(int space,int grids) {
		//���ñ���ɫ�Լ���С
		setBackground(Color.YELLOW);
		setSize(space*grids, space*grids);
		chessMenuBar.add(menu);
		menu.add(startMenuItem);
		menu.add(exitMenuItem);
		
		
		
	}
	//�滭
	@Override
	protected void paintComponent(Graphics arg0) {
		// TODO Auto-generated method stub
		super.paintComponent(arg0);
		//������
		
		//������
		
	}
	//��ò˵�����������������
	public JMenuBar getChessMenuBar() {
		return chessMenuBar;
	}
	
	
	
	
	

}
