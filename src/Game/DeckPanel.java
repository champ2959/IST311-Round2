/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author emg5478
 */
public class DeckPanel extends JPanel {
    
    ArrayList deck;
    ArrayList gameCards;
    int round;
    int pairs = 4;
    
    public DeckPanel(ArrayList theDeck, int theRound) {
        
        // The Deck of 52 cards 
        deck = theDeck;
        
        // The game round were in
        round = theRound;
        
        // The cards we'll use for this round
        gameCards = new ArrayList<>();
        
        // Shuffe the whole deck of cards
        shuffleCards(true);
        
        // If were past the first round we will increase
        // the number of pairs we have by 2 (add 4 cards)
        // to the gameCards array (deck)
        if (round > 1) {
        
            pairs = pairs + (2 * round);
            
        }
        
        // Add the card to our gameCard list        
        for (int i = 0; i < pairs; i++) {
            
            gameCards.add(i, (Card) deck.get(i));
            
        }
        
        // Clone the cards we will need
        for (int i = 0; i < pairs; i++) {
            
            int cloneIndex = (pairs + i);
            
            Card cardToClone = (Card) gameCards.get(i);
            
            gameCards.add(cloneIndex, cloneCard(cardToClone));
        }
        
        // Shuffle the game cards to be
        // placed randomly on the game board
        shuffleCards(false);
        
       // Card c = (Card) gameCards.get(0);
        
       // System.out.println(c.toString());
    }
    
    public Card cloneCard(Card c) {
        
        Card clone = new Card(c.value, c.suit);

        return clone;
    }
    
    public void shuffleCards(boolean wholeDeck) {
               
        if (wholeDeck == false) {

            for (int i = ((pairs * 2) - 1); i > 0; i--) {
                
                int rand = (int)(Math.random()*(i+1));
            
                Card temp = (Card) gameCards.get(i);
            
                gameCards.set(i, deck.get(rand));
            
                gameCards.set(rand, temp);
                
            }
            
        }
        else {
    
            for (int i = 51; i > 0; i--) {
            
                int rand = (int)(Math.random()*(i+1));
            
                Card temp = (Card) deck.get(i);
            
                deck.set(i, deck.get(rand));
            
                deck.set(rand, temp);
            }
        
        }
        
        
    }
    
}
