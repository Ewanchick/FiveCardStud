/**
 * Have the Dealer extend Player
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Dealer extends Player
{
    // Create a private property of a Deck
    private Deck deck;
    /**
     * Constructor for objects of class Dealer
     */
    public Dealer(int stash)
    {
        // Call the parent class' constructor with the name "Dealer" and the stash provided as a parameter
        // Initialize the Deck property by calling its constructor
        super();
        this.name = "dealer";
        this.stash = stash;
         deck = new Deck();
    }

    /**
     * deal will return a card from the deck
     * @return Card a card from the deck
     */
    public Card deal()
    {
        // Deal a card from the deck and return it.
    
       Card card = deck.deal(); 
        return card;
    }
    
    /**
     * resetDeck replaces the deck with a new shuffled 52 card deck
     */
    public void resetDeck()
    {
        // Initialize the deck as a new instance of the Deck to reset it to 52 random cards again.
        deck = new Deck();
    }
}