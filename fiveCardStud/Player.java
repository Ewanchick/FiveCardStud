import java.util.ArrayList;
import java.util.Comparator;
import java.util.*;
/**
 * The Player class represents a Blackjack player in the game
 *
 * @author Charles Almond
 * @version 2021.05.24.01
 */
public class Player
{
    // Private instance variables - Declare an ArrayList of Cards called hand, a name(String), and a stash(int)
    private ArrayList<Card> hand;
    protected String name;
    protected int stash;
    private int score;
    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        // initialise instance variables - A new ArrayList, set the name to "Player",
        //   And call setStash to use its validation, with the initial value of 500
        this.hand = new ArrayList<>();
        name = "Player";
        setStash(500);
        score = 0;
    }

    /**
     * Overloaded constructor for custom name and amount of money to bet with
     */
    public Player(String name, int stash)
    {
        // Initialize instance variables - A new ArrayList for the hand, but now set the name and initial stash
        ArrayList<Card> hand = new ArrayList();
        this.name = name;
        this.stash = stash;
    }

    /**
     * Get a card from the dealer
     * @param card A card from the deck of cards
     * @param visibility Set the card face-up (true) or face-down (false)
     */
    public void receiveCard(Card card, boolean visibility)
    {
        // If the visibility is true, call the card's show method and then add it to the hand
        // Otherwise, call the card's hide method and then add it to the hand
        Card tCard = card;
        if(visibility == true)
        {
            tCard.show();
            hand.add(tCard);
        }
        else if (visibility == false){ 
            tCard.hide();
            hand.add(tCard);           
        }

    }

    /**
     * Set the amount of money the player has available to bet, must be >= 0
     */
    public void setStash(int stash)
    {
        // Validate that the stash is greater than or equal to zero before assigning it.
        if(stash < 0){
            System.out.println("You need to have money to play!! Get Lost!");
        }
        else
        {
            this.stash = stash;
        }
    }

    /**
     * Get the amount of money the player has available to bet
     * @return The amount of money the player has to bet
     */
    public int getStash()
    {
        return stash;
    }

    /**
     * Clear the player's hand, for use after one round of Blackjack
     */
    public void clearHand()
    {
        // Remove all of the cards from the hand
        hand = new ArrayList();
    }

    /**
     * Scores the hand based off of the type of hand that the player has, as well as what cards
     * make up said hand. while it is ordered from most to least specialized.
     */
    public int scoreHand()
    {        
        Collections.sort(hand);
        if(hand.size() != 5){
        score = 0;
        return score;
        }
        else if(isRoyalFlush()){
            score = 9000000 + hand.get(4).getValue();
            return score;
        }
        else if(isStraightFlush()){
            score = 8000000 + hand.get(4).getValue();
            return score;
        }
        else if(is4s()){
            score = 7000000 + hand.get(2).getValue();
        }
        else if(isFullHouse()){
            score = 6000000 + hand.get(2).getValue();
            return score;
        }
        else if(isFlush()){
            score = 5000000 + hand.get(4).getValue();
            return score;
        }
        else if(isStraight()){
            score = 4000000 + hand.get(4).getValue();
            return score;
        }
        else if(is3s()){
            int val;
            if (hand.get(0).getValue() == hand.get(1).getValue() &&
            hand.get(2).getValue() == hand.get(3).getValue())
                val = 14*14*hand.get(2).getValue() + 14*hand.get(0).getValue() + hand.get(4).getValue();
            else if ( hand.get(0).getValue() == hand.get(1).getValue() &&
            hand.get(3).getValue() == hand.get(4).getValue()){
                val = 14*14*hand.get(3).getValue() + 14*hand.get(0).getValue() + hand.get(2).getValue();
            }
            else {
                val = 14*14*hand.get(3).getValue() + 14*hand.get(1).getValue() + hand.get(0).getValue();
            }
            score = 2000000 + val;
            return score;
        }
        else if(is2s()){
            int val;
            if( hand.get(0).getValue() == hand.get(1).getValue()){
                val = 14*14*14*hand.get(0).getValue() + hand.get(2).getValue() + 
                14*hand.get(3).getValue() + 14*14*hand.get(4).getValue();
            }
            else if(hand.get(1).getValue() == hand.get(2).getValue()){
                val = 14*14*14*hand.get(1).getValue() + hand.get(0).getValue() + 
                14*hand.get(3).getValue() + 14*14*hand.get(4).getValue();
            }
            else if(hand.get(2).getValue() == hand.get(3).getValue()){
                val = 14*14*14*hand.get(2).getValue() + hand.get(0).getValue() + 
                14*hand.get(1).getValue() + 14*14*hand.get(4).getValue();
            }
            else{
                val = 14*14*14*hand.get(3).getValue() + hand.get(0).getValue() + 
                14*hand.get(1).getValue() + 14*14*hand.get(2).getValue();
            }
            score = 2000000 + val;
            return score;
        }
        else{
            int val;
            val = hand.get(0).getValue() + 14*hand.get(1).getValue() + 14*13*hand.get(2).getValue() +
            14*14*14*hand.get(3).getValue() + 14*14*14*14*hand.get(4).getValue();
            score = 1000000 + val;
            return score;
        }
        return score;
    }

    /**
     * Flips all cards face-up
     */
    public void showAllCards()
    {
        // Loop over every card in the hand and call the card's show method to set its visibility
        for(int counter = 0; counter < hand.size(); counter ++){
            Card tCard = hand.get(counter);
            tCard.show();
        }
    }
    /**
     * Checks to see if the players hand contains a flush.
     */
    public boolean isFlush(){
        if(hand.size() != 5){
            return false;
        }
        else{
            Comparator<Card> cmp = Comparator.comparing(Card::getSuit);
            Collections.sort(hand,cmp);
            Card fCard = hand.get(0);
            Card lCard = hand.get(4);
            return(fCard.getSuit() == lCard.getSuit());
        }
    }
    /**
     * Checks to see if the players hand contains a straight
     */
    public boolean isStraight(){
        Collections.sort(hand);
        if(hand.size() != 5){
            return false;
        }
        if(hand.get(4).getValue() == 14){
            boolean a = hand.get(0).getValue() == 2 && hand.get(1).getValue() == 3 
                && hand.get(2).getValue() == 4 && hand.get(3).getValue() == 5;
            boolean b = hand.get(0).getValue() == 10 && hand.get(1).getValue() == 11
                && hand.get(2).getValue() == 12 && hand.get(3).getValue() == 13;
            return (a || b);
        }
        else{
            int testRank = hand.get(0).getValue() + 1;
            for(int i = 1; i < 5; i++){
                if (hand.get(i).getValue() != testRank){
                    return false;
                }
                testRank ++;
            }
            return true;
        }
    }
    /**
     * Checks to see if the players hand contains a straight flush
     */
    public boolean isStraightFlush(){
        if(isStraight() == true && isFlush() == true){
            return true;
        }
        else{
            return false;
        }
    };
    /**
     * Checks to see if the players hand contains a royal flush
     */
    public boolean isRoyalFlush(){
        if(isStraight() == true && isFlush() == true && hand.get(4).getValue() == 14){
            return true;
        }
        else return false;
    }
    /**
     * Checks to see if the players hand contains a four of a kind
     */
    public boolean is4s(){
        boolean a1;
        boolean a2;
        if(hand.size() != 5){
            return false;
        }
        {
        Collections.sort(hand);
        a1 = hand.get(0).getValue() == hand.get(1).getValue() &&
        hand.get(1).getValue() == hand.get(2).getValue() &&
        hand.get(2).getValue() == hand.get(3).getValue();
        a2 = hand.get(1).getValue() == hand.get(2).getValue() &&
        hand.get(2).getValue() == hand.get(3).getValue() &&
        hand.get(3).getValue() == hand.get(4).getValue();
        return( a1 || a2);
    }
}
    /**
     * Checks to see if the players hand contains a full house
     */
    public boolean isFullHouse(){
        boolean a1;
        boolean a2;
        if(hand.size() != 5){
            return false;
        }
        else{
        Collections.sort(hand);
        a1 = hand.get(0).getValue() == hand.get(1).getValue() &&
        hand.get(1).getValue() == hand.get(2).getValue() &&
        hand.get(3).getValue() == hand.get(4).getValue();
        a2 = hand.get(0).getValue() == hand.get(1).getValue() &&
        hand.get(2).getValue() == hand.get(3).getValue() &&
        hand.get(3).getValue() == hand.get(4).getValue();
        return( a1 || a2);
    }
}
    /**
     * Checks to see if the players hand contains a set of 3
     */
    public boolean is3s(){
        boolean a1;
        boolean a2;
        boolean a3;  
        if(hand.size() != 5){
            return false;
        }
        else if (is4s() || isFullHouse()){
            return false;
        }
        else{
            Collections.sort(hand);
            a1 = hand.get(0).getValue() == hand.get(1).getValue() &&
            hand.get(1).getValue() == hand.get(2).getValue();
            a2 = hand.get(1).getValue() == hand.get(2).getValue() &&
            hand.get(2).getValue() == hand.get(3).getValue();
            a3 = hand.get(2).getValue() == hand.get(3).getValue() &&
            hand.get(3).getValue() == hand.get(4).getValue();
            return( a1 || a2 || a3);
        }
    }
    /**
     * Checks to see if the players hand contains two pairs
     */
    public boolean is22s(){
        boolean a1;
        boolean a2;
        boolean a3;
        if(hand.size() != 5){
            return false;
        }
        else if(is4s() || isFullHouse() || is3s()){
            return false;
        }
        else{
            Collections.sort(hand);
            a1 = hand.get(0).getValue() == hand.get(1).getValue() &&
            hand.get(2).getValue() == hand.get(3).getValue();
            a2 = hand.get(0).getValue() == hand.get(1).getValue() &&
            hand.get(3).getValue() == hand.get(4).getValue();
            a3 = hand.get(1).getValue() == hand.get(2).getValue() &&
            hand.get(3).getValue() == hand.get(4).getValue();
            return (a1 || a2 || a3);
        }
    }
    /**
     * Checks to see if the players hand contains a pair 
     */
    public boolean is2s(){
        boolean a1;
        boolean a2;
        boolean a3;
        boolean a4;
        if(hand.size() != 5){
            return false;
        }
        else if(is4s() || isFullHouse() || is3s() || is22s()){
            return false;
        }
        else{
            Collections.sort(hand);
            a1 = hand.get(0).getValue() == hand.get(1).getValue();
            a2 = hand.get(1).getValue() == hand.get(2).getValue();
            a3 = hand.get(2).getValue() == hand.get(3).getValue();
            a4 = hand.get(3).getValue() == hand.get(4).getValue();
            return(a1 || a2 || a3 || a4 );
        }
    }

    /*
     * Return a string representing the player and the amount of money they have available
     */
    @Override
    public String toString()
    {
        String player = name + " has $" + stash + "\n";
        for(Card c : hand)
        {
            player += c.toString() + "\n";
        }
        return player;
    }
    public void setScore(int newScore){
    score = newScore;
    }
    public int handSize(){
    return hand.size();
    }
    public void sortHand(){
    Collections.sort(hand);
    }
    public ArrayList<Card> getHand(){
    return hand;
    }
}