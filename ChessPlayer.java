package ppt;

import java.awt.HeadlessException;

import javax.swing.JFrame;

public class ChessPlayer extends JFrame {
	
	private int space;
	private int grids;
	//�������
	private ChessPanel chessPanel;
	
	public ChessPlayer(String arg0,int space,int grids) throws HeadlessException {
		super(arg0);
		this.space=space;
		this.grids=grids;
		chessPanel=new ChessPanel(space, grids);
		add(chessPanel);
		setJMenuBar(chessPanel.getChessMenuBar());
		setSize(600, 600);
		//��ֹ��������
		setResizable(false);
		setVisible(true);
		//�ر�
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ChessPlayer("����", 20, 30);
	}

}
