public class Set {
	
	Player playerOne;
	Player playerTwo;
	Player winner;
	int playerOnePoints;
	int playerTwoPoints;
	boolean tieBreak;
	boolean tieBreakStarted;
	
	public Set(Player playerOne, Player playerTwo) {
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
	}
	
	public Player getPlayerOne() {
		return playerOne;
	}
	
	public void setPlayerOne(Player playerOne) {
		this.playerOne = playerOne;
	}
	
	public Player getPlayerTwo() {
		return playerTwo;
	}
	
	public void setPlayerTwo(Player playerTwo) {
		this.playerTwo = playerTwo;
	}
	
	public int getPlayerOnePoints() {
		return playerOnePoints;
	}
	
	public void setPlayerOnePoints(int playerOnePoints) {
		this.playerOnePoints = playerOnePoints;
	}
	
	public int getPlayerTwoPoints() {
		return playerTwoPoints;
	}
	
	public void setPlayerTwoPoints(int playerTwoPoints) {
		this.playerTwoPoints = playerTwoPoints;
	}
	
	public boolean isTieBreak() {
		return tieBreak;
	}
	
	public void setTieBreak(boolean tieBreak) {
		this.tieBreak = tieBreak;
	}
	
	public boolean isTieBreakStarted() {
		return tieBreakStarted;
	}
	
	public void setTieBreakStarted(boolean tieBreakStarted) {
		this.tieBreakStarted = tieBreakStarted;
	}
	
	
	
	public Player getWinner() {
		return winner;
	}
	
	public void setWinner(Player winner) {
		this.winner = winner;
	}
	@Override
	public String toString() {
		return String.format("Object Set Score: %d - %d", this.playerOnePoints, this.playerTwoPoints);
	}

}