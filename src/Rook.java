
public class Rook {

	public static boolean isValid(String move, Board board) {
		
		if(board.colorPiece(board.board[board.fromNumber(move)][board.fromLetter(move)]) != board.whoseTurn())
			return false;
		
		if(board.fromLetter(move) == board.toLetter(move)) {
			if(board.fromNumber(move)<board.toNumber(move)) {
				for(int i=1; board.fromNumber(move)+i <= board.toNumber(move); i++) {
					char from = board.board[board.fromNumber(move)+i][board.fromLetter(move)];
					if(from != '+') {
						if(board.whoseTurn() == board.colorPiece(from))
							return false;
						if(from == 'D')
							return false;
						if(from != board.toNumber(move) && board.notColorPiece(from) == board.whoseTurn())
							return false;
					}
				}
			}
			else {
				for(int i=1; board.fromNumber(move)-i >= board.toNumber(move); i++) {
					char from = board.board[board.fromNumber(move)-i][board.fromLetter(move)];
					if(from != '+') {
						if(board.whoseTurn() == board.colorPiece(from))
							return false;
						if(from == 'D')
							return false;
						if(from != board.toNumber(move) && board.notColorPiece(from) == board.whoseTurn())
							return false;
					}	
				}
			}
			return true;
		}
		
		if(board.fromNumber(move) == board.toNumber(move)) {
			if(board.fromLetter(move)<board.toNumber(move)) {
				for(int i=1; board.fromLetter(move)+i <= board.toLetter(move); i++) {
					char from = board.board[board.fromNumber(move)][board.fromLetter(move)+i];
					if(from != '+') {
						if(board.whoseTurn() == board.colorPiece(from))
							return false;
						if(from == 'D')
							return false;
						if(from != board.toNumber(move) && board.notColorPiece(from) == board.whoseTurn())
							return false;
					}	
				}
			}
			else {
				for(int i=1; board.fromLetter(move)-i <= board.toLetter(move); i++) {
					char from = board.board[board.fromNumber(move)][board.fromLetter(move)-i];
					if(from != '+') {
						if(board.whoseTurn() == board.colorPiece(from))
							return false;
						if(from == 'D')
							return false;
						if(from != board.toNumber(move) && board.notColorPiece(from) == board.whoseTurn())
							return false;
					}	
				}
			}
			return true;
		}
					
		return false;
	}
}
