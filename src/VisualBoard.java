import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class VisualBoard implements ActionListener{

	public JFrame frame;
	public JPanel boardPanel;
	public JButton[][] boardButtons;
	public Board chessBoard = new Board();
	
	public Color whiteSquare = Color.decode("#e2e6ca");
	public Color blackSquare = Color.decode("#21788a");
	
	private HashMap<Character, ImageIcon> iconMap;
	
	public String move = "";
	
	public VisualBoard() {
		
		frame = new JFrame("Duck Chess");
		boardPanel = new JPanel(new GridLayout(8, 8));
		boardButtons = new JButton[8][8];
		
		loadPiece();
		initializeBoardButtons();
		
		frame.add(boardPanel);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
	
	private ImageIcon loadIcon(String pieceName) {
		return new ImageIcon(getClass().getResource("/chessPieces/"+pieceName));
	}
	
	private void loadPiece() {
		iconMap = new HashMap<>();
		
		iconMap.put('b', loadIcon("Chess_bdt60.png"));
		iconMap.put('B', loadIcon("Chess_blt60.png"));
		iconMap.put('k', loadIcon("Chess_kdt60.png"));
		iconMap.put('K', loadIcon("Chess_klt60.png"));
		iconMap.put('n', loadIcon("Chess_ndt60.png"));
		iconMap.put('N', loadIcon("Chess_nlt60.png"));
		iconMap.put('p', loadIcon("Chess_pdt60.png"));
		iconMap.put('P', loadIcon("Chess_plt60.png"));
		iconMap.put('q', loadIcon("Chess_qdt60.png"));
		iconMap.put('Q', loadIcon("Chess_qlt60.png"));
		iconMap.put('r', loadIcon("Chess_rdt60.png"));
		iconMap.put('R', loadIcon("Chess_rlt60.png"));
		iconMap.put('D', loadIcon("rubber-duck.png"));
		iconMap.put('+', null);
	}
	
	private void initializeBoardButtons() {
		for(int row = 0; row < 8; row++)
			for(int col = 0; col < 8; col++) {
				JButton button = new JButton();
				char piece = chessBoard.board[row][col];
				button.setIcon(iconMap.get(piece));
				
				if((row + col) % 2 == 0)
					button.setBackground(whiteSquare);
				else
					button.setBackground(blackSquare);
				
				button.putClientProperty("num", row);
                button.putClientProperty("lett", col);
				
				button.addActionListener(this);
				
				boardButtons[row][col] = button;
				boardPanel.add(button);
			}
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        
        int num = 8 - (int) clickedButton.getClientProperty("num");
        char lett = (char) ('a' + (int) clickedButton.getClientProperty("lett"));
        move += "" + lett + num + '-';
        
        chessBoard.makeMove(move);
        updateVisualBoard();
        chessBoard.makeDuckMove(move);
        updateVisualBoard();
        
        if(chessBoard.isValid(move) || move.length() > 8)
        	move = "";
        
        System.out.println(move);
    }
	
	public void updateVisualBoard() {
		for(int row = 0; row < 8; row++)
			for(int col = 0; col < 8; col++) {
				char piece = chessBoard.board[row][col];
				boardButtons[row][col].setIcon(iconMap.get(piece));
			}
	}
	
	@Override
	public String toString() {
		return move;
	}
	
	
}
