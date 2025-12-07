
public class King {

	public static boolean isValid(String move, Board board) {
		
		if(board.colorPiece(board.board[board.fromNumber(move)][board.fromLetter(move)]) != board.whoseTurn())
			return false;
		
		char to = board.board[board.toNumber(move)][board.toLetter(move)];
		
		if(board.colorPiece(to) == board.whoseTurn() || to == 'D')
			return false;
		
		int toN = Math.abs(board.fromNumber(move)-board.toNumber(move));
		int toL = Math.abs(board.fromLetter(move)-board.toLetter(move));
		
		if((toN == 1 && toL == 1) || (toN == 1 && toL == 0) || (toN == 0 && toL == 1))
			return true;
		if(castlinValid(move, board))
			return true;
		
		return false;
	}
	
	public static boolean castlinValid(String move, Board board) {
		if(board.currentKingMoved())
			return false;
		int toN = board.toNumber(move), toL = board.toLetter(move);
		char to = board.board[toN][toL];
		
		if(board.whoseTurn() == "black") {
			if(toN == 0 && toL == 6 && to == '+' && board.board[0][5] == '+') {
				board.setBlackShortCastlin(true);
				return true;
			}
			if(toN == 0 && toL == 2 && to == '+' && board.board[0][3] == '+') {
				board.setBlackLongCastlin(true);
				return true;
			}
			return false;
		}
		else {
			if(toN == 7 && toL == 6 && to == '+' && board.board[7][5] == '+') {
				board.setWhiteShortCastlin(true);
				return true;
			}
			if(toN == 7 && toL == 2 && to == '+' && board.board[7][3] == '+') {
				board.setWhiteLongCastlin(true);
				return true;
			}
			return false;
		}
	}
}
