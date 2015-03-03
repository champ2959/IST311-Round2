/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Erik Galloway, Nahom, Mark
 */
public class DeckPanel extends JPanel implements ActionListener {
    
    ArrayList deck;
    ArrayList<Card> gameCards = new ArrayList<Card>();  // much easier to work with cards in the deck if we make this an arrayList of Cards
    int round;
    int pairs = 2;
    private ImageIcon cardImage;
    boolean cardFlipped = false;
    boolean nextRound = false;
    
    int correctCards = 0;
    
    Card firstCard;
    Card secondCard;
    
    Timer time;
    int cardReset = 0;
    int t = 0;
    
    public DeckPanel(ArrayList theDeck, int theRound, Timer t) throws IOException {
        
        super();
        
        int layoutRows = 2;
        int cols = 4;
        
        // The game round were in
        round = theRound;
        
        // The Deck of 52 cards 
        deck = theDeck;
        
         if (round > 1) {
             
            pairs = pairs + 4;
            
            if (round == 2)  {
                pairs = 4;
            }
            layoutRows = ((pairs * 2) / 4);
            cols = 6;
        }
        

        setLayout(new GridLayout(layoutRows, cols, 2, 10));
                
        time = t;
        
        time.addActionListener(this);
        
        // The cards we'll use for this round
        gameCards = new ArrayList();
        
        // Shuffe the whole deck of cards
        shuffleCards(true);
        
        // If were past the first round we will increase
        // the number of pairs we have by 2 (add 4 cards)
        // to the gameCards array (deck)
        
        
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
        
       
        
        // Add the cards to the deck panel
        addCards();
        
        
    }  // end constructor
    
     public void addCards() throws IOException {
        
        for(int i = 0; i < gameCards.size(); i++) {
            
            if (round > 1 && ((i % 4 == 0) || (i == 0))) {
                
                this.add(new JLabel(""));
                
            }
            
            else if (round == 1 && (i == 0 || i == 2)) {
                
                this.add(new JLabel(""));
               
                
            }
            
                      
            gameCards.get(i).addActionListener(this);
          
            this.add(gameCards.get(i));
            
            if (round == 1 && (i == 1 || i == 3) ) {
                
                this.add(new JLabel(""));
                
                
            }
            else if (round > 1 && ((i + 1) % 4 == 0)) {

                this.add(new JLabel(""));

            }
            
        }
        
    }  // end addCards()
    
    public final Card cloneCard(Card c) throws IOException {
        
        Card clone = new Card(c.value, c.suit, true);

        return clone;
    }
    
    public final void shuffleCards(boolean wholeDeck) {
               
        if (wholeDeck == false) {

            for (int i = ((pairs * 2) - 1); i > 0; i--) {
                
                int rand = (int)(Math.random()*(i+1));
            
                Card temp = (Card) gameCards.get(i);
            
                gameCards.set(i, gameCards.get(rand));
                      
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
        
    }  // end shuffle cards

    public void compareCards(Card btn) {
        
        for (Card gameCard : gameCards) {
            
            /**
             * If the card clicked == the current card inside of the loop
             */
            if (btn.getCardAsString().equals(gameCard.getCardAsString()) && btn.isClone == gameCard.isClone) {
               
                // Flip the card clicked
                gameCard.flipCard(); 
                
                /**
                 * Do we have a car already turned over?
                 */
                if (cardFlipped == true && firstCard != null) {
                    
                    /**
                     * Does the second card clicked = our first card turned over?
                     * If it does set our first card to null and changed the cardFlipped var
                     */
                    if (btn.getCardAsString().equals(firstCard.getCardAsString())) {
                        firstCard.cardDone = true;
                        gameCard.cardDone = true;
                        Game.App.game.p1.scorePanel.clickCount++;
                        Game.App.game.p1.scorePanel.clicksLabel.setText("Clicks: " + Game.App.game.p1.scorePanel.clickCount);                   
                        correctCards = correctCards + 2;
                        
                        if (correctCards == gameCards.size()) {
                            
                            nextRound = true;
                            gameCards.clear();
                            break;
                            
                        }
                        
                        firstCard = null;
                        cardFlipped = false;
                       
                   }                 
                   else {
                                
                        secondCard = gameCard;
                        cardFlipped = false;
                       
                   }                 
                }
                else {
                    
                    firstCard = gameCard;
                    
                    cardFlipped = true;
                
                }
               
            }
               
            gameCard.removeActionListener(this);
        }
        
        this.removeAll();

        try {

            this.addCards();
        
        } 
        catch (IOException ex) {
        
            Logger.getLogger(DeckPanel.class.getName()).log(Level.SEVERE, null, ex);
        
        }
               
    }


    @Override
    public void actionPerformed(ActionEvent e) {
    
        Object obj = e.getSource();
        
        if (obj == time) {
            // i'm using this to update the time, we probably should be using t, or remove the t from this panel, and just use the timer count on the score panel
            Game.App.game.p1.scorePanel.timeCount++;
            Game.App.game.p1.scorePanel.timeLabel.setText("Time: " + Game.App.game.p1.scorePanel.timeCount);
         
            t++;
        
        }      
        
        if (obj == time && secondCard != null) {
            
            cardReset++; 
            
            if (cardReset > 1 && secondCard != null) {
                
                time.stop();
                Game.App.game.p1.scorePanel.clickCount++;
                Game.App.game.p1.scorePanel.clicksLabel.setText("Clicks: " + Game.App.game.p1.scorePanel.clickCount);
                for (Card gameCard : gameCards) {
                    
                    if ((gameCard == firstCard || gameCard == secondCard) && gameCard.isFaceUp && gameCard.cardDone == false) {
                        
                        gameCard.flipCard();
                        
                        firstCard = (gameCard == firstCard) ? null : firstCard;
                        
                        secondCard = (gameCard == secondCard) ? null : secondCard;
                        
                    }
                    
                }
                
                cardReset = 0;
                
                time.start();
                
            }
            
        }
        else if(obj != time) {
    
            Card btn = (Card) obj;
            
            if (btn.cardDone == false && btn.isFaceUp == false) {

                this.compareCards(btn);
       
            }
        }
        validate();
        repaint();
                           
    }    
}
