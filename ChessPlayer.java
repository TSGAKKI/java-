package huaban;

import java.awt.HeadlessException;

import javax.swing.JFrame;

public class ChessPlayer extends JFrame {
	
	private int space;
	private int grids;
	//∆Â≈Ã√Ê∞Â
	private ChessPanel chessPanel;
	
	public ChessPlayer(String arg0,int space,int grids) throws HeadlessException {
		super(arg0);
		this.space=space;
		this.grids=grids;
		chessPanel=new ChessPanel(space, grids);
		
		setJMenuBar(chessPanel.getChessMenuBar());
		setSize(705, 770);
		//Ω˚÷π¥∞ø⁄¿≠…Ï
		setResizable(false);
		setVisible(true);add(chessPanel);
		//πÿ±’
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ChessPlayer("∆Â≈Ã", 20, 30);
	}

}
