import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * The table will run the five card stud
 *
 * @author [Ethan Wanchick]
 * @version [Version]
 */
public class Table
{
    // instance variables - player, dealer, a "pot" of money holding all the bets, the bet, and a Scanner to get input
    private int pot;
    private int bet;
    private Player player;
    private Dealer dealer;
    private boolean fold;
    /**
     * Constructor for objects of class Table
     */
    public Table()
    {
        // Initialize the pot to 0, the player as a new player object, and create the dealer with 5x the money of the player
        pot = 0;
        player = new Player();
        dealer = new Dealer(2500);
    }

    /**
     * Accessor for the pot of money being bet
     * Used for unit testing
     */
    public int getPot()
    {
        return pot;
    }

    /**
     * Accessor for the pot of money being bet
     * Used for unit testing
     */
    public void setPot(int p)
    {
        if (p >= 0)
        {
            pot = p;
        }
        else
        {
            pot = 0;
        }
    }

    /*
     * Used only for unit testing
     */
    protected void setBet(int b)
    {
        bet = b;
    }

    /**
     * Return the player
     */
    public Player getPlayer()
    {
        return player;
    }

    /**
     * Return the dealer 
     */
    public Dealer getDealer()
    {
        return dealer;
    }

    /**
     * Plays five card stud, each round goes till the player has been dealt their last card or they fold.
     */
    public void play()
    {
        System.out.println("Time for a new Game");
        while(player.stash > 0 && dealer.stash > 0){
            int i = 0;
            clearScreen();
            getAnte();
            Card tCard = dealer.deal();
            dealer.receiveCard(tCard, false);
            tCard = dealer.deal();
            dealer.receiveCard(tCard, true);
            tCard = dealer.deal();
            player.receiveCard(tCard, true);
            tCard = dealer.deal();
            player.receiveCard(tCard, true);           
            System.out.println(player.toString());
            System.out.println(dealer.toString());
            getBets();
            if(bet == 0){
            roundEnd();
        }
            while(i < 3 && fold != true){
                //second through fifth round
                clearScreen();
                tCard = dealer.deal();
                dealer.receiveCard(tCard, true);
                tCard = dealer.deal();
                player.receiveCard(tCard, true);
                System.out.println(player.toString());
                System.out.println(dealer.toString());
                getBets();
                i++;
                 if(bet == 0){
            roundEnd();
        }
            }
            if(fold == true){
                System.out.print("You folded!");
                player.setScore(0);
                dealer.scoreHand();
                scoreGame();
            }
            else{
                dealer.showAllCards();
                System.out.println(player.toString());
                System.out.println(dealer.toString());
                player.scoreHand();
                dealer.scoreHand();
                scoreGame();
            }

        }
        System.out.print("Game over: ");
        if(player.stash <= 0){
            System.out.println("You lose try again?");
        }
        else if(dealer.stash <= 0){
            System.out.println("You win! Congrats! try again?");
        }
    }

    /*
     * Get a valid bet from the player, and match it or go all-in by the dealer
     * It will not accept bets greater than the players stash.
     */
    public int getBets() throws InputMismatchException
    {
        // Get the bet for this hand from the player. 
        //  then take from player's stash and put in the pot
        Scanner scanner = new Scanner(System.in);
        int tbet;
        bet = -1;
        System.out.println("Place your bet!: Type zero if you wish to check/fold");
        while(bet == -1 || bet >= player.getStash() || bet < 0) {
            try{
                bet = scanner.nextInt();

            }
            catch(InputMismatchException e){
                System.err.println("Must be a int.");
                scanner = new Scanner(System.in);
                bet = -1;
            }
            if(bet > player.getStash()){
                System.out.println("You cant bet that!: Try something lower");
                System.out.println(player.toString());
                System.out.println(dealer.toString());
            }
            if(bet < 0){
            System.out.println("You cant bet that!: Must be at least zero");
            scanner = new Scanner(System.in);
            bet = -1;
            }
            else if(bet > 0 && bet <= player.getStash()){
                pot = pot + bet;
                player.stash = player.stash - bet;
            }
            else if(bet == 0){
            System.out.println("The player checks");
            }
        }
        // Dealer will match the bet or go all-in if not enough
        if(dealer.stash > bet){
            pot = pot + bet;
            dealer.stash = dealer.stash - bet;
        }
        else if(dealer.stash < bet){
            bet = dealer.stash;
            pot = pot + bet;
            dealer.stash = 0;
        }
        tbet = bet;
        return tbet;
    }

    /*
     * Score the game based on the two player's hands and declare a winner
     * This would normally be private, but it is public for the unit testing
     */
    public void scoreGame()
    {

        // Compare both player's score and declare winner.  Award pot to winner, clear both player's hands, and reset the pot to 0
        // If there is a tie (called a Push), return the money to both players.  Be careful to check if one player went all in before returning money

        if(player.scoreHand() > dealer.scoreHand()){
            player.stash = player.stash + pot;
            pot = 0;
            System.out.println("You won this hand!");
        }
        else if(player.scoreHand() < dealer.scoreHand()){
            dealer.stash = dealer.stash + pot;
            pot = 0;
            System.out.println("The dealer won this hand!");
        }
        else if(player.scoreHand() == dealer.scoreHand()){
            dealer.stash = dealer.stash + bet;
            pot = pot - bet;
            player.stash = player.stash + pot;
            pot = 0;
            System.out.println("Draw!");
        }
        player.clearHand();
        dealer.clearHand();
        bet = 0;
        dealer.resetDeck();
    }

    /**
     * This method cycles between the two player, assigning them as the big and little ante each turn.
     */
    public void getAnte(){
        int playerAnte = 5;
        int dealerAnte = 10;

        player.stash = player.stash - playerAnte;
        dealer.stash = dealer.stash - dealerAnte;
        pot = pot + playerAnte + dealerAnte;
        if(playerAnte == 5){
            playerAnte = 10;
        }
        else if(playerAnte == 10){
            playerAnte = 5;
        }
        if(dealerAnte == 5){
            dealerAnte = 10;
        }
        else if(dealerAnte == 10){
            dealerAnte = 5;
        }
    }

    /*
     * Pause for 2 seconds
     */
    private static void pause()
    {
        try
        {
            Thread.sleep(2000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }

    /*
     * Clear the screen by running the system console's cls/clear command
     */
    private static void clearScreen()
    {  
        // This approach is a bad practice, because it relies on an external command that may not be there or may be corrupted.  It's easy though
        try
        {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows"))
            {
                Runtime.getRuntime().exec("cls");
            }
            else
            {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (final Exception e)
        {
            // System.err.println(e.getMessage()); // Causes error in IDE
        }
    }

    /**
     * Runs at the end of every round, asks the player if they want to fold if it is not the last round.
     */
    private boolean roundEnd(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Would you like to fold? Yes or No?");
        String scn = scanner.nextLine();
        while(player.handSize() != 5 && scn.toLowerCase().equals("yes") && scn.toLowerCase().equals("y") &&
        scn.toLowerCase().equals("no") && scn.toLowerCase().equals("n")){
            if(player.handSize() == 5){
                System.out.println("Time for the showdown!");
                fold = false;
                return fold;
            }
            else if(scn.toLowerCase().equals("yes") || scn.toLowerCase().equals("y")){
                player.setScore(0);
                scoreGame();
                fold = true;
                return fold;
            }
            else if (scn.toLowerCase().equals("no") || scn.toLowerCase().equals("n")){
                System.out.println("Time for the next set of cards.");
                fold = false;
                return fold;
            }
            else{
                System.out.println("YES OR NO?!?!?!?!?");
            }
        }
        return fold;
    }
}
