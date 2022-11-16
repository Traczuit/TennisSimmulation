

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestPlay {
	
	Match tennis = null;
	Player playerOne = null;
	Player playerTwo = null;
	
	
	private void setupAssertPlayers() {
		playerOne = new Player();playerOne.setName("Rodel");
		playerTwo = new Player();playerTwo.setName("Dhey");
		tennis = new Match(playerOne, playerTwo);
		assertEquals("0-0", tennis.score());
		assertEquals(playerOne.getName(), ((Player)tennis.getPlayers().get(0)).getName());
		assertEquals(playerTwo.getName(), ((Player)tennis.getPlayers().get(1)).getName());
	}

	
	private void assertDeuce(String points) {
		tennis.pointWonBy(playerTwo);tennis.pointWonBy(playerOne);
		tennis.pointWonBy(playerOne);tennis.pointWonBy(playerOne);
		tennis.pointWonBy(playerTwo);
		assertEquals(String.format("%s, 40-30",points), tennis.score());
		tennis.pointWonBy(playerTwo);
		assertEquals(String.format("%s, Deuce",points), tennis.score());
	}
	
	
	private void assertSecondDeuce(String points) {
		tennis.pointWonBy(playerOne);
		assertEquals(String.format("%s, Advantage Rodel",points), tennis.score());
		tennis.pointWonBy(playerTwo);
		assertEquals(String.format("%s, Deuce",points), tennis.score());
	}

	// Players Check
	@Test
    public void matchPlay01_PlayerCheck() {
		setupAssertPlayers();
	}
	
	// Score
	@Test
    public void matchPlay02_PlayerOneScore() {
		setupAssertPlayers();
		tennis.pointWonBy(playerOne);
		assertEquals("0-0, 15-0", tennis.score());
	}
	
	// Score
	@Test
    public void matchPlay03_PlayerTwoScore() {
		setupAssertPlayers();
		tennis.pointWonBy(playerTwo);
		assertNotEquals("0-0, 15-0", tennis.score());
	}
	
	// Score
	@Test
    public void matchPlay04_BothPlayerScore() {
		setupAssertPlayers();
		tennis.pointWonBy(playerTwo);
		tennis.pointWonBy(playerOne);
		assertEquals("0-0, 15-15", tennis.score());
	}
	
	// a Deuce
	@Test
    public void matchPlay05_Duece() {
		setupAssertPlayers();
		assertDeuce("0-0");
	}
	
	// an Advantage
	@Test
    public void matchPlay06_PlayerOneAdvantage() {
		setupAssertPlayers();
		assertDeuce("0-0");
		tennis.pointWonBy(playerOne);
		assertEquals("0-0, Advantage Rodel", tennis.score());
	}
	
	// Player Two Game Win
	@Test
    public void matchPlay07_PlayerTwoSet() {
		setupAssertPlayers();
		tennis.pointWonBy(playerTwo);
		tennis.pointWonBy(playerTwo);
		assertEquals("0-0, 0-30", tennis.score());
		tennis.pointWonBy(playerTwo);
		tennis.pointWonBy(playerOne);
		assertEquals("0-0, 15-40", tennis.score());
		tennis.pointWonBy(playerTwo);
		assertEquals("0-1", tennis.score());
	}
	
	// a Deuce Win
	@Test
    public void matchPlay08_PlayerOneDeuceWin() {
		setupAssertPlayers();
		assertDeuce("0-0");
		tennis.pointWonBy(playerOne);
		assertEquals("0-0, Advantage Rodel", tennis.score());
		tennis.pointWonBy(playerOne);
		assertEquals("1-0", tennis.score());
	}

	// a Double Deuce
	@Test
    public void matchPlay09_DoubleDeuce() {
		setupAssertPlayers();
		assertDeuce("0-0");
		assertSecondDeuce("0-0");
	}
	
	// Test Case Winning a Double Deuce
	@Test
    public void matchPlay10_DoubleDeucePlayerTwoWin() {
		setupAssertPlayers();
		assertDeuce("0-0");
		assertSecondDeuce("0-0");
		tennis.pointWonBy(playerTwo);
		assertEquals("0-0, Advantage Dhey", tennis.score());
		tennis.pointWonBy(playerTwo);
		assertEquals("0-1", tennis.score());
	}
	
	// Test Case Winning a Double Deuce
	@Test
    public void matchPlay11_DoubleDeucePlayerOneWin() {
		setupAssertPlayers();
		assertDeuce("0-0");
		assertSecondDeuce("0-0");
		tennis.pointWonBy(playerOne);
		assertEquals("0-0, Advantage Rodel", tennis.score());
		tennis.pointWonBy(playerOne);
		assertEquals("1-0", tennis.score());
	}
	
	// Test Case Winning a Double Deuce and Another Deuce by each player
	@Test
    public void matchPlay12_DoubleDeuceTiedOneOne() {
		setupAssertPlayers();
		assertDeuce("0-0");
		assertSecondDeuce("0-0");
		tennis.pointWonBy(playerTwo);
		assertEquals("0-0, Advantage Dhey", tennis.score());
		tennis.pointWonBy(playerTwo);
		assertEquals("0-1", tennis.score());
		tennis.gameSet();
		assertDeuce("0-1");
		tennis.pointWonBy(playerOne);tennis.pointWonBy(playerOne);
		assertEquals("1-1", tennis.score());
		
	}
	
	
	private void winGame(Player player, String points) {
		tennis.pointWonBy(player);tennis.pointWonBy(player);
		tennis.pointWonBy(player);tennis.pointWonBy(player);
		assertEquals(String.format("%s",points),tennis.score());
	}
	
	
	private void tieBreakGame() {
		playerOneInLead();
		winGame(playerTwo,"6-6");tennis.gameSet();
	}

	
	private void playerOneInLead() {
		winGame(playerOne,"1-0");tennis.gameSet();
		winGame(playerTwo,"1-1");tennis.gameSet();
		winGame(playerTwo,"1-2");tennis.gameSet();
		winGame(playerTwo,"1-3");tennis.gameSet();
		winGame(playerOne,"2-3");tennis.gameSet();
		winGame(playerOne,"3-3");tennis.gameSet();
		winGame(playerOne,"4-3");tennis.gameSet();
		winGame(playerTwo,"4-4");tennis.gameSet();
		winGame(playerTwo,"4-5");tennis.gameSet();
		winGame(playerOne,"5-5");tennis.gameSet();
		winGame(playerOne,"6-5");tennis.gameSet();
	}
	
	// Test Case to get first score in Game #12
	@Test
	public void matchPlay13_TieBreaker() {
		setupAssertPlayers();
		tieBreakGame();
		
		tennis.pointWonBy(playerOne);
		assertEquals("6-6, 1-0", tennis.score());
	}

	// Test Case win Game 12
	@Test
	public void matchPlay14_PlayerOneWins() {
		setupAssertPlayers();
		playerOneInLead();
		winGame(playerOne,"7-5, Winner Rodel");tennis.gameSet();
	}
	
	// Test Case to exhaust to Game #13 Tie Break and take the score to 2
	@Test
	public void matchPlay15_TieBreaker2() {
		setupAssertPlayers();
		tieBreakGame();
		
		tennis.pointWonBy(playerOne);
		tennis.pointWonBy(playerOne);
		assertEquals("6-6, 2-0", tennis.score());
	}

	// Test Case to exhaust to Game #13 Tie Break and take the score to 6
	@Test
	public void matchPlay16_TieBreaker3() {
		setupAssertPlayers();
		tieBreakGame();
		
		tennis.pointWonBy(playerOne);
		tennis.pointWonBy(playerOne);tennis.pointWonBy(playerOne);
		tennis.pointWonBy(playerOne);tennis.pointWonBy(playerOne);
		tennis.pointWonBy(playerOne);
		assertEquals("6-6, 6-0", tennis.score());
	}
	
	// Test Case to exhaust to Game #13 Tie Break and race to 7
	@Test
	public void matchPlay17_TieBreaker4() {
		setupAssertPlayers();
		tieBreakGame();
		
		tennis.pointWonBy(playerOne);
		tennis.pointWonBy(playerOne);tennis.pointWonBy(playerOne);
		tennis.pointWonBy(playerOne);tennis.pointWonBy(playerOne);
		tennis.pointWonBy(playerOne);tennis.pointWonBy(playerOne);
		assertEquals("7-6, Winner Rodel", tennis.score());
	}
	
	// Test Case to exhaust to Game #13 Tie Break and race to 12
	@Test
	public void matchPlay18_TieBreaker5() {
		setupAssertPlayers();
		tieBreakGame();
		
		tennis.pointWonBy(playerOne);
		tennis.pointWonBy(playerOne);tennis.pointWonBy(playerOne);
		tennis.pointWonBy(playerOne);tennis.pointWonBy(playerOne);
		tennis.pointWonBy(playerOne);
		assertEquals("6-6, 6-0", tennis.score());
		tennis.pointWonBy(playerTwo);
		tennis.pointWonBy(playerTwo);tennis.pointWonBy(playerTwo);
		tennis.pointWonBy(playerTwo);tennis.pointWonBy(playerTwo);
		tennis.pointWonBy(playerTwo);
		assertEquals("6-6, 6-6", tennis.score());
		tennis.pointWonBy(playerOne);tennis.pointWonBy(playerTwo);
		tennis.pointWonBy(playerOne);tennis.pointWonBy(playerTwo);
		tennis.pointWonBy(playerOne);tennis.pointWonBy(playerTwo);
		tennis.pointWonBy(playerOne);tennis.pointWonBy(playerTwo);
		assertEquals("6-6, 10-10", tennis.score());
		tennis.pointWonBy(playerOne);tennis.pointWonBy(playerTwo);
		assertEquals("6-6, 11-11", tennis.score());
		tennis.pointWonBy(playerTwo);
		assertEquals("6-7, Winner Dhey", tennis.score());
	}
}