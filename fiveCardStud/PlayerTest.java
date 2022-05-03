import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class PlayerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class PlayerTest
{
    /**
     * Default constructor for test class PlayerTest
     */
    public PlayerTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
    
    @Test
    public void playerConstructorTest()
    {
        // Arrange
        Player p;
        
        // Act
        p  = new Player();
        
        // Assert
        assertEquals(p.getStash(), 500);
    }
    
    // receiveCard test
    @Test
    public void playerReceiveCardTest()
    {
        // Arrange
        Player p1 = new Player();
        Player p2 = new Player();
        
        // Act
        p1.receiveCard(new Card(Suit.Clubs, 12, true, "Queen"), true);
        p2.receiveCard(new Card(Suit.Clubs, 12, true, "Queen"), false);
        
        // Assert - scoreHand is a subtle way to see if there is a card in the hand
        assertEquals(p1.getHand().get(0).toString(), "Queen of Clubs");
        assertEquals(p2.getHand().get(0).toString(), "Hidden Card"); // Only scores cards that are not hidden
    }
    

    
    // get/set stash test
    @Test
    public void playerManageStashTest()
    {
        // Arrange
        Player p = new Player();
        
        // Act
        p.setStash(1000);
        
        // Assert
        assertEquals(p.getStash(), 1000);
    }

    // clear hand test
    @Test
    public void playerClearHandTest()
    {
        // Arrange
        Player p1 = new Player();
        Player p2 = new Player();
        
        // Act - both receive the same cards.  p2 will be empty after clear hand, while p1 will not
        p1.receiveCard(new Card(Suit.Clubs, 12, true, "Queen"), true);
        p1.receiveCard(new Card(Suit.Clubs, 14, true, "Ace"), true);
        
        p2.receiveCard(new Card(Suit.Clubs, 12, true, "Queen"), true);
        p2.receiveCard(new Card(Suit.Clubs, 14, true, "Ace"), true);
        
        p2.clearHand();
        // Assert
        assertEquals(p1.handSize(), 2);
        assertEquals(p2.handSize(), 0);
    }
    
    // show all cards test
    @Test
    public void playerShowAllCardsTest()
    {
        // Arrange
        Player p1 = new Player();
        
        // Act - both receive the same cards.  p2 will be empty after clear hand, while p1 will not
        p1.receiveCard(new Card(Suit.Clubs, 12, true, "Queen"), false);
        
        boolean cardIsHidden =(p1.getHand().get(0).toString() == "Hidden Card"); // Score of the Queen is skipped because it is hidden 

        p1.showAllCards();

        boolean cardIsVisible = (p1.getHand().get(0).toString() != "Hidden Card"); // Score of Queen is now returned because all cards are visible       
        System.out.println(p1.getHand().get(0).toString());
        // Assert
        assertTrue(cardIsHidden);
        assertTrue(cardIsVisible);
    }
    @Test
    public void royalFlushTest()
    {
    Player p1 = new Player();
    p1.receiveCard(new Card(Suit.Clubs,12,true,"Queen"),true);
    p1.receiveCard(new Card(Suit.Clubs,14,true,"Ace"),true);
    p1.receiveCard(new Card(Suit.Clubs,11,true,"Jack"),true);
    p1.receiveCard(new Card(Suit.Clubs,10,true,"Ten"),true);
    p1.receiveCard(new Card(Suit.Clubs,13,true,"King"),true);
    assertTrue(p1.isRoyalFlush());
    }
     @Test
    public void straightFlushTest()
    {
    Player p1 = new Player();
    p1.receiveCard(new Card(Suit.Clubs,12,true,"Queen"),true);
    p1.receiveCard(new Card(Suit.Clubs,9,true,"Nine"),true);
    p1.receiveCard(new Card(Suit.Clubs,11,true,"Jack"),true);
    p1.receiveCard(new Card(Suit.Clubs,10,true,"Ten"),true);
    p1.receiveCard(new Card(Suit.Clubs,13,true,"King"),true);
    assertTrue(p1.isStraightFlush());
    }
    @Test
    public void FourSTest()
    {
    Player player = new Player();
    player.receiveCard(new Card(Suit.values()[1], 14, true, "Ace"), true);
    player.receiveCard(new Card(Suit.values()[2], 14, true, "Ace"), true);
    player.receiveCard(new Card(Suit.values()[3], 14, true, "Ace"), true);
    player.receiveCard(new Card(Suit.values()[0], 14, true, "Ace"), true);
    player.receiveCard(new Card(Suit.values()[1], 2, true, "2"), true);
    assertTrue(player.is4s());
    }
    @Test
    public void fullHouseTest()
    {
    Player player = new Player();
    player.receiveCard(new Card(Suit.values()[1], 14, true, "Ace"), true);
    player.receiveCard(new Card(Suit.values()[2], 14, true, "Ace"), true);
    player.receiveCard(new Card(Suit.values()[3], 14, true, "Ace"), true);
    player.receiveCard(new Card(Suit.values()[0], 2, true, "2"), true);
    player.receiveCard(new Card(Suit.values()[1], 2, true, "2"), true);
    assertTrue(player.isFullHouse());
    }
    @Test
    public void threeSTest()
    {
    Player player = new Player();
    player.receiveCard(new Card(Suit.values()[1], 14, true, "Ace"), true);
    player.receiveCard(new Card(Suit.values()[2], 14, true, "Ace"), true);
    player.receiveCard(new Card(Suit.values()[3], 14, true, "Ace"), true);
    player.receiveCard(new Card(Suit.values()[0], 4, true, "4"), true);
    player.receiveCard(new Card(Suit.values()[1], 2, true, "2"), true);
    assertTrue(player.is3s());
    }
    @Test
    public void twoTwoSTest()
    {
    Player player = new Player();
    player.receiveCard(new Card(Suit.values()[1], 14, true, "Ace"), true);
    player.receiveCard(new Card(Suit.values()[2], 14, true, "Ace"), true);
    player.receiveCard(new Card(Suit.values()[3], 4, true, "4"), true);
    player.receiveCard(new Card(Suit.values()[0], 4, true, "4"), true);
    player.receiveCard(new Card(Suit.values()[1], 2, true, "2"), true);
    assertTrue(player.is22s());
    }
    @Test
    public void twoSTest()
    {
    Player player = new Player();
    player.receiveCard(new Card(Suit.values()[1], 14, true, "Ace"), true);
    player.receiveCard(new Card(Suit.values()[2], 14, true, "Ace"), true);
    player.receiveCard(new Card(Suit.values()[3], 6, true, "6"), true);
    player.receiveCard(new Card(Suit.values()[0], 4, true, "4"), true);
    player.receiveCard(new Card(Suit.values()[1], 2, true, "2"), true);
    assertTrue(player.is2s());
}
}