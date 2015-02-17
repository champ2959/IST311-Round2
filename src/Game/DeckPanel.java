/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.paint.Color;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author emg5478
 */
public class DeckPanel extends JPanel {
    
    ArrayList deck;
    ArrayList gameCards;
    int round;
    int pairs = 8;
    private ImageIcon cardImage;
    BufferedImage img;
    JButton cardButton;
    boolean cardFlipped = false;
    
    public DeckPanel(ArrayList theDeck, int theRound) throws IOException {
        
        super();
        
        int layoutRows = 2;
        
        if (round > 1) {
        
            pairs = pairs + (2 * round);
            layoutRows = ((pairs * 2) / 4);
        }

        setLayout(new GridLayout(layoutRows, 6, 10, 10));
        
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
        
        // Set the card sprite (clean up messy code later)
        img = ImageIO.read(getClass().getResource("cards.png"));
        
        // Add the cards to the deck panel
        addCards();
        
    }
    
     public void addCards() throws IOException {
        
        for(int i = 0; i < gameCards.size(); i++) {
            
            if (i == 0 || (i + 1) % pairs == 1) {
                
                this.add(new JLabel(""));
                
            }
            Card c = (Card) gameCards.get(i);
           
            cardButton = new JButton();
            
            // Change to false, I set it to true to see the images
            if (cardFlipped == true) {
                
                cardImage = new ImageIcon(img.getSubimage(c.faceDownX, c.faceDownY, 87, 134));
                
            }
            else {  
 
                cardImage = new ImageIcon(img.getSubimage(c.xLoc, c.yLoc, 87, 134));
            
            }
            
            cardButton.setIcon(cardImage);
            cardButton.setOpaque(false);
            cardButton.setContentAreaFilled(false);
            cardButton.setBorderPainted(false);
            
           
            this.add(cardButton);
            
            if ((i + 1) % pairs == 0) {
                this.add(new JLabel(""));
            }
        }
        
    }
    
    public final Card cloneCard(Card c) {
        
        Card clone = new Card(c.value, c.suit);

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
        
    }
    
}
