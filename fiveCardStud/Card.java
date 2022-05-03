import java.util.Comparator;
import java.util.*;
/**
 * Card represents a standard playing card from a 52 card deck
 *
 * @author Charles Almond
 * @version 2020.06.18.04
 */
public class Card implements Comparable<Card>
{
    // private instance variables - Declare a suit (Suit), value (int), visible (boolean), and name (String)
    private Suit suit;
    private int value;
    private boolean visible;
    private String name;
    /**
     * Constructor for objects of class Card 
     * @param suit The suit of the card (Clubs, Spades, Diamonds, Hearts)
     * @param value The point value of the card, between 1 and 11, inclusive
     * @param visible The setting to show or hide the card when dealt, change with show() or hide()
     * @param name The name of the card
     */
    public Card(Suit suit, int value, boolean visible, String name) 
    {
        // Assign properties, and ensure the value is between 1 and 11, inclusive.  
        this.suit = suit;
        this.value = value;
        this.name = name;
        this.visible = visible;
    }

    /**
     * Get the card's suit
     * @return The card's suit
     */
    public Suit getSuit()
    {
        return suit;
    }

    /**
     * Get the card's point value
     * @return A number 2 - 11 of the card's point value
     */
    public int getValue()
    {
        return value;
    }

    /**
     * Get the card's name
     * @return The name of the card ("2 - 10, Jack, Queen, King, or Ace")
     */
    public String getName()
    {
        return name;
    }

    /**
     * Report if the card is facec-up or face-down
     * @return true if the card is face-up, false if face-down
     */
    public boolean isVisible()
    {
        return visible;
    }

    /**
     * Set the card as visible, so the front of the card is shown
     */
    public void show()
    {
        visible = true;
    }

    /**
     * Set the card as hidden, so the back of the card is shown
     */
    public void hide()
    {
        visible = false;
    }

    /*
     * Display the front or back of the card, based on the visible field's value
     */
    @Override
    public String toString()
    {
        // If the card is visible, display the card's name of suit (ex: Queen of Spades)
        // If the card is not visible, return the string literal "Hidden Card"
        String lname;
        if (visible == true){
        lname = ( name + " of " + suit);
        return lname;
        }
        else {
        lname = ("Hidden Card");
        return lname;
        }
        
    }
    public void setValue(int v)
    {       
       this.value = v;
    }
    public void setName(String x)
    {
        this.name = x;
    }
    @Override
    public int compareTo(Card card2){
    if(this.getValue() > card2.getValue()){
    return 1;
}
    else if (this.getValue() < card2.getValue()){
    return -1;
    }
    else{
    return 0;
}
    }

}