
import java.util.ArrayList;
import java.util.List;

public class Match {

	private List<Player> players = null;
	
	private boolean gameStarted = false;
	private boolean isDeuced = false;
	private boolean isDeuceBreaker = false;
	private Player advantagePlayer = null;
	private Player winGamePlayer = null;
	private Set playersSet = null;
	
	public Match(Player playerOne, Player playerTwo) {
		getPlayers().add(playerOne);
		getPlayers().add(playerTwo);
		playersSet = new Set(playerOne, playerTwo);
	}
	
	
	public void gameSet() {
			gameStarted = false;
	}

	
	private void inititializeGame() {
		gameStarted = true;
		advantagePlayer = null;
		winGamePlayer = null;
		isDeuceBreaker = false;
		isDeuced = false;
		Player playerOne = ((Player)getPlayers().get(0));
		Player playerTwo = ((Player)getPlayers().get(1));
		setScore(playerOne, 0);
		setScore(playerTwo, 0);
	}
	
	
	private void setScore(Player player, int score) {
		player.setScore(score);
	}
	
	
	private void setPoint(Player player, int score) {
		Player playerOne = ((Player)getPlayers().get(0));
		if (player.equals(playerOne))
			getPlayersSet().setPlayerOnePoints(score);
		else
			getPlayersSet().setPlayerTwoPoints(score);
	}
	
	
	public List<Player> getPlayers() {
		return this.players == null ? this.players = new ArrayList<Player>() : this.players;
	}
	
	
	public Set getPlayersSet() {
		return this.playersSet;
	}
	
	
	private int getScore(Player player) {
		return player.getScore();
	}
	
	
	private int getPoint(Player player) {
		Player playerOne = ((Player)getPlayers().get(0));

		return player.equals(playerOne) ?  getPlayersSet().getPlayerOnePoints() : getPlayersSet().getPlayerTwoPoints();
	}
	
	public String score() {
		Player playerOne = ((Player)getPlayers().get(0));
		Player playerTwo = ((Player)getPlayers().get(1));
		String initScore = String.format("%d-%d", playersSet.getPlayerOnePoints(), playersSet.getPlayerTwoPoints());
		if (!gameStarted)
			return initScore;
		else if (!isDeuced && !isDeuceBreaker && winGamePlayer==null) {
			return String.format("%s, %d-%d",initScore,
					getScore(playerOne),getScore(playerTwo));
		} else  if (isDeuced && !isDeuceBreaker){
			return String.format("%s, %s",initScore,
					"Deuce");
		} else if (winGamePlayer==null){
			return String.format("%s, Advantage %s",initScore,
					advantagePlayer.getName()); 	} else if (playersSet.getWinner()!=null)  {
					return String.format("%s, Winner %s", initScore, playersSet.getWinner().getName());
		} else {
			return String.format("%s",initScore); 	}
	}
	
	private void validateScore() {
		Player playerOne = ((Player)getPlayers().get(0));
		Player playerTwo = ((Player)getPlayers().get(1));
		int playerOneScore = getScore(playerOne);
		int playerTwoScore = getScore(playerTwo);
		
		int playerOnePoint = getPoint(playerOne);
		int playerTwoPoint = getPoint(playerTwo);
		
		isDeuced = false;
		playersSet.setTieBreak(false);
		
		validateGameWinner(playerOne, playerTwo, playerOneScore, playerTwoScore, playerOnePoint, playerTwoPoint );
		validateDeuce(playerOne, playerTwo, playerOneScore, playerTwoScore); 
		validateSetWinner(playerOne, playerTwo);

	}

	private void validateSetWinner(Player playerOne, Player playerTwo) {
		int playerOnePoint;
		int playerTwoPoint;
			playerOnePoint = getPoint(playerOne);
	 	playerTwoPoint = getPoint(playerTwo);
	 	if (playerOnePoint==playerTwoPoint && playerOnePoint == 6) {
	 		playersSet.setTieBreak(true);
	 	}else if (playerOnePoint>=7 || playerTwoPoint>=7){
	 		if (playerOnePoint-playerTwoPoint>=1)
	 			playersSet.setWinner(playerOne);
	 		else if (playerTwoPoint-playerOnePoint>=1)
	 			playersSet.setWinner(playerTwo);
	 	}
	}

	private void validateGameWinner(Player playerOne, Player playerTwo, int playerOneScore, int playerTwoScore,
			int playerOnePoint, int playerTwoPoint) {
		if (!playersSet.isTieBreakStarted()) {
			if (playerOneScore >= 40 && playerTwoScore>=40) {
				if (playerOneScore - playerTwoScore >= 2)
					winGamePlayer = playerOne;
				else  if (playerTwoScore - playerOneScore >= 2)
					winGamePlayer = playerTwo;
			} else if (playerOneScore > 40 && playerTwoScore < 40) {
				winGamePlayer = playerOne;
			} else if (playerOneScore < 40 && playerTwoScore > 40) {
				winGamePlayer = playerTwo;
			}
		} else {
			if (playerOneScore==12 || playerTwoScore==12) {
				if (playerOneScore>playerTwoScore) {
					winGamePlayer = playerOne;
				}else if (playerTwoScore>playerOneScore) {
					winGamePlayer = playerTwo;
				}
			} else if (playerOneScore>=7 || playerTwoScore>=7) {
				if (playerOneScore-playerTwoScore>=2) {
					winGamePlayer = playerOne;
				}else if (playerTwoScore-playerOneScore>=2) {
					winGamePlayer = playerTwo;
				}
			}
		}
		
		if (winGamePlayer!=null) {
			int points = 0;
			if (winGamePlayer.equals(playerOne)) 
				points=getPlayersSet().getPlayerOnePoints();
			else 
				points=getPlayersSet().getPlayerTwoPoints();
			setPoint(winGamePlayer,points+1);
		}
	}
	
	private void validateDeuce(Player playerOne, Player playerTwo, int playerOneScore, int playerTwoScore) {
		if (playerOneScore >= 40 && playerTwoScore>=40 && playerOneScore == playerTwoScore) {
					setScore(playerOne, 40);
			setScore(playerTwo, 40);
			isDeuced = true;
			isDeuceBreaker = false;
			advantagePlayer = null;
		} else if (playerOneScore >= 40 && playerTwoScore>=40 && playerOneScore != playerTwoScore) {
			if (playerOneScore>playerTwoScore)
				advantagePlayer = playerOne;
			else 
				advantagePlayer = playerTwo;
		}
	}
	
	public void pointWonBy(Player player) {
		if (!gameStarted) 
			inititializeGame();
		
		int score = getScore(player);
		if (!isDeuced && !isDeuceBreaker && !playersSet.isTieBreak()) {
			score = score==30?40:score+15;
		} else if (!playersSet.isTieBreak()){
			isDeuceBreaker = true;
			score += 1; 	} else {
			playersSet.setTieBreakStarted(true); 
			score += 1; 	}
		player.setScore(score);
		validateScore();
		
	}

}