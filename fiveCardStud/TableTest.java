

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class TableTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class TableTest
{   
    private Table table;
    private Player player;
    private Dealer dealer;
    /**
     * Default constructor for test class TableTest
     */
    public TableTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
        table= new Table();
        player = table.getPlayer();
        dealer = table.getDealer();
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
        table = null;
        player = null;
        dealer = null;
    }
    @Test
    public void scorePlayerGameTest(){
    //arranage
    int playerStartStash = player.getStash();
    int dealerStartStash = dealer.getStash();
    player.receiveCard(new Card(Suit.values()[1], 14, true, "Ace"), true);
    player.receiveCard(new Card(Suit.values()[2], 14, true, "Ace"), true);
    player.receiveCard(new Card(Suit.values()[3], 14, true, "Ace"), true);
    player.receiveCard(new Card(Suit.values()[0], 14, true, "Ace"), true);
    player.receiveCard(new Card(Suit.values()[1], 2, true, "2"), true);
    dealer.receiveCard(new Card(Suit.values()[1], 10, true, "10"), true);
    dealer.receiveCard(new Card(Suit.values()[2], 4, true, "4"), true);
    dealer.receiveCard(new Card(Suit.values()[3], 14, true, "Ace"), true);
    dealer.receiveCard(new Card(Suit.values()[0], 12, true, "Queen"), true);
    dealer.receiveCard(new Card(Suit.values()[1], 2, true, "2"), true);
    //act
     player.setStash(player.getStash() - 10);
        dealer.setStash(dealer.getStash() - 10);
        table.setPot(20);
        dealer.showAllCards();
        player.scoreHand();
        dealer.scoreHand();
        table.scoreGame();
    //assert
    assertTrue(player.getStash() == (playerStartStash + 10));
    }
     @Test
    public void scoreDealerGameTest(){
    //arranage
    int playerStartStash = player.getStash();
    int dealerStartStash = dealer.getStash();
    player.receiveCard(new Card(Suit.values()[1], 11, true, "Jack"), true);
    player.receiveCard(new Card(Suit.values()[2], 8, true, "8"), true);
    player.receiveCard(new Card(Suit.values()[3], 6, true, "6"), true);
    player.receiveCard(new Card(Suit.values()[0], 14, true, "Ace"), true);
    player.receiveCard(new Card(Suit.values()[1], 2, true, "2"), true);
    dealer.receiveCard(new Card(Suit.values()[1], 10, true, "10"), true);
    dealer.receiveCard(new Card(Suit.values()[2], 10, true, "10"), true);
    dealer.receiveCard(new Card(Suit.values()[3], 14, true, "Ace"), true);
    dealer.receiveCard(new Card(Suit.values()[0], 14, true, "Ace"), true);
    dealer.receiveCard(new Card(Suit.values()[1], 2, true, "2"), true);
    //act
     player.setStash(player.getStash() - 10);
        dealer.setStash(dealer.getStash() - 10);
        table.setPot(20);
        dealer.showAllCards();
        player.scoreHand();
        dealer.scoreHand();
        table.scoreGame();
    //assert
    assertTrue(dealer.getStash() == (dealerStartStash + 10));
    }
    @Test
    public void scoreGamePushAllInTest()
    {
        // Arrange
        player.setStash(500);
        dealer.setStash(100);
        
        int playerStartStash = player.getStash();
        int dealerStartStash = dealer.getStash();
        
        player.receiveCard(new Card(Suit.values()[1], 14, true, "Ace"), true);
        player.receiveCard(new Card(Suit.values()[1], 6, true, "6"), true);
        player.receiveCard(new Card(Suit.values()[1], 2, true, "2"), true);
        player.receiveCard(new Card(Suit.values()[0], 14, true, "Ace"), true);
        player.receiveCard(new Card(Suit.values()[2], 8, true, "8"), true);
        dealer.receiveCard(new Card(Suit.values()[2], 14, true, "Ace"), false);
        dealer.receiveCard(new Card(Suit.values()[2], 6, true, "6"), true);
        dealer.receiveCard(new Card(Suit.values()[2], 2, true, "2"), true);
        dealer.receiveCard(new Card(Suit.values()[3], 14, true, "Ace"), true);
        player.receiveCard(new Card(Suit.values()[1], 8, true, "8"), true);

        // Act - dealr goes all-in because can't match 200
        player.setStash(player.getStash() - 200);
        dealer.setStash(0);
        //dealers bet
        table.setBet(100);
        table.setPot(300);
        dealer.showAllCards();
        table.scoreGame();

        // Assert
        assertTrue(player.getStash() == playerStartStash);
        assertTrue(dealer.getStash() == dealerStartStash);
        assertTrue(table.getPot() == 0);
    } 
}
