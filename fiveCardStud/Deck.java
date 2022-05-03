import java.util.ArrayList;
import java.util.Collections;
import java.util.*;
/**
 * A class to represent a deck of deck
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Deck
{
    // Declare a private ArrayList of deck
    private ArrayList<Card> deck;
    
    /**
     * Constructor for objects of class Deck
     */
    public Deck()
    {
        // Loop through the Suit values
        // Inside that loop, loop through the numbers 2 - 15
        // Build a card using the outer loop's suit
        // and using this loop's index for the value.  Everything from 10 - King has a value of 10, and Ace has a value of 11
        // Put this newly created card in the deck ArrayList
        // Call the static method Collections.shuffle and pass it the deck ArrayList.  This will shuffle the deck for you
        this.deck = new ArrayList<Card>();
        int Value = -1;
        String Name = " ";
        Suit tsuit = Suit.Clubs;
        boolean visibility = false;
        for(Suit suit : Suit.values()){
            
            for(int index = 2; index < 15; index++){      
                if (index <= 10){
                    Value = index;
                    Name = (String.valueOf(index));
                    
                }
                else if (index == 11){
                    Value = index;
                    Name = "Jack";
                    
                } 
                else if (index ==12){
                   Value = index;
                   Name = "Queen";
                    
                }
                else if (index == 13){
                    Value = index;
                    Name = "King";
                    
                }
                else {
                    Value = 14;
                    Name = "Ace";
                    
                }
                //System.out.println(TempCard);
                //System.out.println(TempCard.getValue());
                Card TempCard = new Card(suit,Value,visibility,Name);
                this.deck.add(TempCard);
                Collections.shuffle(deck);
            }
        }
    }
    
    
    /**
     * Deal a card from the deck
     * @return A Card from the deck
     */
    public Card deal()
    {
        // Call the remove method on the deck ArrayList, always using index number 0, and return this card

        Card DrawnCard = deck.get(0);
        deck.remove(0);
        return DrawnCard;
    }

    /**
     * Report on the size of the remaining deck in the deck
     * @return The number of deck left in the deck
     */
    public int cardsLeftInDeck()
    {
        // Return the size of the deck collection
        
        return deck.size();
    }
}
