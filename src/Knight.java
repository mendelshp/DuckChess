
public class Knight {

	public static boolean isValid(String move, Board board) {
		
		if(board.colorPiece(board.board[board.fromNumber(move)][board.fromLetter(move)]) != board.whoseTurn())
			return false;
		
		if(Math.abs(board.fromLetter(move)-board.toLetter(move)) == 2 && Math.abs(board.fromNumber(move)-board.toNumber(move))==1) {
			char to = board.board[board.toNumber(move)][board.toLetter(move)];
			if(board.whoseTurn() == board.colorPiece(to) || to == 'D')
				return false;
			return true;
		}
		if(Math.abs(board.fromLetter(move)-board.toLetter(move)) == 1 && Math.abs(board.fromNumber(move)-board.toNumber(move))==2) {
			char to = board.board[board.toNumber(move)][board.toLetter(move)];
			if(board.whoseTurn() == board.colorPiece(to) || to == 'D')
				return false;
			return true;
		}
		
		return false;
	}
}
