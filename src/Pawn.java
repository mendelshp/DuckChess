
public class Pawn {

	public static boolean isValid(String move, Board board) {
		
		if(board.colorPiece(board.board[board.fromNumber(move)][board.fromLetter(move)]) != board.whoseTurn())
			return false;
		
		if(!isGoingForward(move, board)) return false;
		
		char to = board.board[board.toNumber(move)][board.toLetter(move)];
		
		if(to == '+') {
			if(board.fromLetter(move) != board.toLetter(move)) 
				return enPassantValid(move, board);

			if(inStartingSquare(move, board) && Math.abs(board.fromNumber(move)-board.toNumber(move)) <= 2)
				return true;
			if(Math.abs(board.fromNumber(move)-board.toNumber(move)) == 1)
				return true;
		}
		else 
			return Math.abs(board.fromNumber(move)-board.toNumber(move)) == 1 &&
					Math.abs(board.fromLetter(move)-board.toLetter(move)) == 1;
		
		return false;
	}
	
	private static boolean inStartingSquare(String move, Board board) {
		if(board.whoseTurn() == "black") {
			if(board.fromNumber(move) == 1) 
				return true;
		}
		else if(board.fromNumber(move) == 6)
			return true;
		return false;
	}
	
	private static boolean isGoingForward(String move, Board board) {
		if(board.whoseTurn() == "black") {
			if(board.fromNumber(move)-board.toNumber(move) < 0) 
				return true;
		}
		else if(board.fromNumber(move)-board.toNumber(move) > 0) 
			return true;
		return false;
	}
	
	public static boolean enPassantValid(String move, Board board) {
		if(board.previousMove() == null) return false;
		return board.fromNumber(move) == board.toNumber(board.previousMove()) &&
				Math.abs(board.fromLetter(move)-board.toLetter(board.previousMove())) == 1 &&
				board.toLetter(move) == board.toLetter(board.previousMove()) &&
				board.toNumber(move) == (board.whoseTurn() == "white" ? 2 : 5);
	}
	
	public static boolean promotion(String move, Board board) {
		char p = board.board[board.fromNumber(move)][board.fromLetter(move)];
		if(p != 'p' && p != 'P') return false;
		int endNum = board.whoseTurn() == "white" ? 0 : 7;
		if(board.toNumber(move) != endNum)
			return false;
		char prom = move.charAt(6);
		if(board.colorPiece(prom) != board.whoseTurn())
			prom = (char) (prom <= 'Z' && prom >= 'A' ? prom-'A'+'a' : prom-'a'+'A');
		move = move.substring(0, 6) + prom + move.substring(prom+1);
		switch (prom){
		case 'r' : 
		case 'R' :
		case 'n' :
		case 'N' :
		case 'q' :
		case 'Q' :
		case 'b' :
		case 'B' : return true;
		default:
			return false;
		}
	}
}
