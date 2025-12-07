
public class Bishop {

	public static boolean isValid(String move, Board board) {
		
		if(board.colorPiece(board.board[board.fromNumber(move)][board.fromLetter(move)]) != board.whoseTurn())
			return false;
		
		if(board.fromNumber(move)-board.fromLetter(move) == board.toNumber(move)-board.toLetter(move)) {
			
			if(board.fromNumber(move) < board.toNumber(move)) {
				for(int i=1; board.fromNumber(move)+i <= board.toNumber(move); i++) {
					char from = board.board[board.fromNumber(move)+i][board.fromLetter(move)+i];
					if(from != '+') {
						if(board.whoseTurn() == board.colorPiece(from))
							return false;
						if(from == 'D')
							return false;
						if(board.fromNumber(move)+i != board.toNumber(move) && board.notColorPiece(from) == board.whoseTurn())
							return false;
					}
				}
				return true;
				}
			if(board.fromNumber(move) > board.toNumber(move)) {
				for(int i=1; board.fromNumber(move)-i >= board.toNumber(move); i++) {
					char from = board.board[board.fromNumber(move)-i][board.fromLetter(move)-i];
					if(from != '+') {
						if(board.whoseTurn() == board.colorPiece(from))
							return false;
						if(from == 'D')
							return false;
						if(board.fromNumber(move)-i != board.toNumber(move) && board.notColorPiece(from) == board.whoseTurn())
							return false;
					}
				}
			}
			return true;
		}
		if(board.fromNumber(move)-(7-board.fromLetter(move)) == board.toNumber(move)-(7-board.toLetter(move))) {
			
			if(board.fromNumber(move) < board.toNumber(move))
				for(int i=1; board.fromNumber(move)+i <= board.toNumber(move); i++) {
					char from = board.board[board.fromNumber(move)+i][board.fromLetter(move)-i];
					if(from != '+') {
						if(board.whoseTurn() == board.colorPiece(from))
							return false;
						if(from == 'D')
							return false;
						if(board.fromNumber(move)+i != board.toNumber(move) && board.notColorPiece(from) == board.whoseTurn())
							return false;
					}
				}
			if(board.fromNumber(move) > board.toNumber(move))
				for(int i=1; board.fromNumber(move)-i >= board.toNumber(move); i++) {
					char from = board.board[board.fromNumber(move)-i][board.fromLetter(move)+i];
					if(from != '+') {
						if(board.whoseTurn() == board.colorPiece(from))
							return false;
						if(from == 'D')
							return false;
						if(board.fromNumber(move)-i != board.toNumber(move) && board.notColorPiece(from) == board.whoseTurn())
							return false;
					}
				}
		}
		return true;
	}
}
