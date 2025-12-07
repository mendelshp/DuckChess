
public class Board {

	public char[][] board = {
			{'r','n','b','q','k','b','n','r'},
			{'p','p','p','p','p','p','p','p'},
			{'+','+','+','+','+','+','+','+'},
			{'+','+','+','+','+','+','+','+'},
			{'+','+','+','+','+','+','+','+'},
			{'+','+','+','+','+','+','+','+'},
			{'P','P','P','P','P','P','P','P'},
			{'R','N','B','Q','K','B','N','R'}
	};
	private String whoseTurn = "white";
	private String duckPosition;
	private String previousMove;
	private char previousPiece;
	
	private boolean blackShortCastlin = false;
	private boolean blackLongCastlin = false;
	private boolean whiteShortCastlin = false;
	private boolean whiteLongCastlin = false;
	
	private boolean whiteKingMoved = false;
	private boolean blackKingMoved = false;
	
	private boolean whiteRookShortMoved = false;
	private boolean whiteRookLongMoved = false;
	
	private boolean blackRookShortMoved = false;
	private boolean blackRookLongMoved = false;
	
	private boolean gameFinished = false;
	
	public void printBoard() {
		for(int i=0; i<8; i++) {
			for(char each : board[i])
				System.out.print(each+" ");
			System.out.println();
		}
	}
	
	public void makeMove(String move) {
		if(!isValid(move))
			return;
		setGameFinished(move);
		if(Pawn.enPassantValid(move, this)) 
			board[toNumber(previousMove)][toLetter(previousMove)] = '+';
		
		else if(King.castlinValid(move, this)){
			if(blackShortCastlin) {
				board[0][7] = '+';
				board[0][5] = 'r';
			}
			else if(blackLongCastlin) {
				board[0][0] = '+';
				board[0][3] = 'r';
			}
			else if(whiteShortCastlin) {
				board[7][7] = '+';
				board[7][5] = 'R';
			}
			else{
				board[7][0] = '+';
				board[7][3] = 'R';
			}
			setKingMove(move);
		}
		else setRookMove(move);
		
		previousPiece = board[toNumber(move)][toLetter(move)];
		board[toNumber(move)][toLetter(move)] = board[fromNumber(move)][fromLetter(move)];
		board[fromNumber(move)][fromLetter(move)] = '+';
		if(Pawn.promotion(move, this))
			board[toNumber(move)][toLetter(move)] = move.charAt(6);
	}
	
	public void makeDuckMove(String move) {
		if(!isDuckValid(move)) {
			if(move.length()<=6) return;
			board[fromNumber(move)][fromLetter(move)] = board[toNumber(move)][toLetter(move)];
			board[toNumber(move)][toLetter(move)] = previousPiece;
			return;
		}
		if(duckPosition != null)
			board[8-(duckPosition.charAt(1)-'0')][duckPosition.charAt(0)-'a'] = '+';
		board[8-(move.charAt(7)-'0')][move.charAt(6)-'a'] = 'D';
		duckPosition = Pawn.promotion(move, this) ? move.substring(8) : move.substring(6); // e7-e8=q,h3
		
		previousMove = move;
		
		if(whoseTurn == "white")
			whoseTurn = "black";
		else
			whoseTurn = "white";
	}
	
	public int fromLetter(String move) {
		return move.charAt(0)-'a';
	}
	public int fromNumber(String move) {
		return 8-(move.charAt(1)-'0');
	}
	
	public int toLetter(String move) {
		return move.charAt(3)-'a';
	}
	public int toNumber(String move) {
		return 8-(move.charAt(4)-'0');
	}
	
	public String duckPosition() {
		return duckPosition;
	}
	
	public String previousMove() {
		return previousMove;
	}
	
	public String whoseTurn() {
		return whoseTurn;
	}
	
	public String colorPiece(char piece) {
		if('a'<=piece && piece<='z')
			return "black";
		else if('A'<=piece && piece<='Z')
			return "white";
		return null;
	}
	public String notColorPiece(char piece) {
		if('a'<=piece && piece<='z')
			return "white";
		else if('A'<=piece && piece<='Z')
			return "black";
		return null;
	}
	
	public void setBlackShortCastlin(boolean blackShortCastlin) {
		this.blackShortCastlin = blackShortCastlin;
		blackLongCastlin = false;
		whiteShortCastlin = false;
		whiteLongCastlin = false;
	}
	
	public void setBlackLongCastlin(boolean blackLongCastlin) {
		this.blackLongCastlin = blackLongCastlin;
		blackShortCastlin = false;
		whiteShortCastlin = false;
		whiteLongCastlin = false;
	}
	
	public void setWhiteShortCastlin(boolean whiteShortCastlin) {
		this.whiteShortCastlin = whiteShortCastlin;
		blackShortCastlin = false;
		blackLongCastlin = false;
		whiteLongCastlin = false;
	}
	
	public void setWhiteLongCastlin(boolean whiteLongCastlin) {
		this.whiteLongCastlin = whiteLongCastlin;
		blackShortCastlin = false;
		blackLongCastlin = false;
		whiteShortCastlin = false;
	}
	
	public boolean whiteRookShortMoved() {
		return whiteRookShortMoved;
	}
	
	public boolean whiteRookLongMoved() {
		return whiteRookLongMoved;
	}
	
	public boolean blackRookShortMoved() {
		return blackRookShortMoved;
	}
	
	public boolean blackRookLongMoved() {
		return blackRookLongMoved;
	}
	
	private void setRookMove(String move) {
		char piece = board[fromNumber(move)][fromLetter(move)];
		if(piece != 'r' && piece != 'R') return;
	
		switch (move.substring(0, 2)){
		case "h1": 
			whiteRookShortMoved = true;
			break;
		case "a1":
			whiteRookLongMoved = true;
			break;
		case "h8":
			blackRookShortMoved = true;
			break;
		case "a8":
			blackRookLongMoved = true;
			break;
		}
	}
	
	public boolean whiteKingMoved() {
		return whiteKingMoved;
	}
	
	public boolean blackKingMoved() {
		return blackKingMoved;
	}
	
	public boolean currentKingMoved() {
		if(whoseTurn == "white")
			return whiteKingMoved;
		return blackKingMoved;
	}
	
	private void setKingMove(String move) {
		char piece = board[fromNumber(move)][fromLetter(move)];
		if(piece != 'k' && piece != 'K') return;
		
		if(piece == 'k')
			blackKingMoved = true;
		else
			whiteKingMoved = true;
	}
	
	public boolean isValid(String move) {
		if(gameFinished) return false;
		try {
			char piece = board[fromNumber(move)][fromLetter(move)];
			if(piece == 'r' || piece == 'R') 
				return Rook.isValid(move, this);
			
			else if(piece == 'b' || piece == 'B') 
				return Bishop.isValid(move, this);
			
			else if(piece == 'q' || piece == 'Q') 
				return Rook.isValid(move, this) || Bishop.isValid(move, this);
			
			else if(piece == 'n' || piece == 'N') 
				return Knight.isValid(move, this);
			
			else if(piece == 'p' || piece == 'P')
				return Pawn.isValid(move, this);
			else
				return King.isValid(move, this);
				
		} catch (Exception e) {
			return false;
		}	
	}
	
	public boolean isDuckValid(String move) {
		return move.length() > 6 && board[8-(move.charAt(7)-'0')][move.charAt(6)-'a'] == '+';
	}
	
	public void setGameFinished(String move) {
		char pieac = board[toNumber(move)][toLetter(move)];
		if(pieac == 'k' || pieac == 'K')
			gameFinished = true;
	}
	
	public boolean gameFinished() {
		return gameFinished;
	}
	
}
