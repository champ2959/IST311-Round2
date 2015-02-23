/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Erik Galloway, Nahom, Mark
 */
public class DeckPanel extends JPanel implements ActionListener{
    
    ArrayList deck;
    ArrayList gameCards;
    int round;
    int pairs = 2;
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
        gameCards = new ArrayList();
        
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
        
    }  // end constructor
    
     public void addCards() throws IOException {
        
        for(int i = 0; i < gameCards.size(); i++) {
            
            if (i == 0 || (i + 1) % pairs == 1) {
                
                this.add(new JLabel(""));
                
            }
            Card c = (Card) gameCards.get(i);
           
            cardButton = new JButton();
            
            // Change to false, I set it to true to see the images
            if (cardFlipped == false) {
                
                cardImage = new ImageIcon(img.getSubimage(c.faceDownX, c.faceDownY, 87, 134));
                
            }
            else {  
 
                cardImage = new ImageIcon(img.getSubimage(c.xLoc, c.yLoc, 87, 134));
            
            }
            
            cardButton.setIcon(cardImage);
            cardButton.setOpaque(false);
            cardButton.setContentAreaFilled(false);
            cardButton.setBorderPainted(false);
            cardButton.addActionListener(this);
           
            this.add(cardButton);
            
            if ((i + 1) % pairs == 0) {
                this.add(new JLabel(""));
            }
        }
        
    }  // end addCards()
    
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
        
    }  // end shuffle cards

    
    public void showCards(){
        // trying to move this into global scope for the deck
        for(int i = 0; i < gameCards.size(); i++){
        Card c = (Card) gameCards.get(i);
            if (cardFlipped == false) {
                
                cardImage = new ImageIcon(img.getSubimage(c.faceDownX, c.faceDownY, 87, 134));
                
            }
            else {  
 
                cardImage = new ImageIcon(img.getSubimage(c.xLoc, c.yLoc, 87, 134));
            
            }
        }    
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
    Object obj = e.getSource();
    
    for(int i = 0; i < gameCards.size(); i++){
        if (obj == gameCards.get(i)){
          if(!cardFlipped){
            cardFlipped = true; 
            showCards();
          }else{
             cardFlipped = false; 
             showCards();
          }  
            
        }
    }
    }  // end action listener
    
}
