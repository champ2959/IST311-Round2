/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.List;
import javax.swing.JButton;

/**
 *
 * @author Erik Galloway
 */
public class Card extends JButton {
    
    public final static int SPADES = 0;
    public final static int HEARTS = 1;
    public final static int DIAMONDS = 2;
    public final static int CLUBS = 3;
    
    public final static int ACE = 1;
    public final static int JACK = 11;
    public final static int QUEEN = 12;
    public final static int KING = 13;
    
    // The suit of the card
    public int suit;
    // The card value
    public int value;
    // The deck
    public List deck;
    
    // The number of cards
    public int cardCount = 8;
    
    // Set the sprite image coordinates
    public int xLoc = 0;
    public int yLoc = 0;
    public int faceDownX = 596;
    public int faceDownY = 566;
    
    public boolean isFaceUp = false;
    
    public boolean isClone = false;
    
    public Card(int val, int theSuit) {
        
        value = val;
        
        suit = theSuit;     
        
        setSpriteLocation();
        
    }
    
    
    public String getCardAsString() {
        
        String str = this.getValueAsString()+ " " + this.getSuit();
     
        return str;
    }
    
    /**
     * 
     * @return string The suit as a string value
     */
    public String getSuit(){
        
        String str = "";
        
        switch (suit) {
            
            case SPADES: str = "Spades";
                break;
            case HEARTS: str = "Hearts";
                break;
            case DIAMONDS: str = "Diamonds";
                break;
            case CLUBS: str = "Clubs";
                break;
        }
        
        return str;
        
    }
    
     public String getValueAsString() {
        
        // Return a String representing the card's value.
        // If the card's value is invalid, "??" is returned.
        String str = "";
        
        switch ( value ) {
           case 1:   str = "Ace";
               break;
           case 2:   str = "2";
               break;
           case 3:   str = "3";
               break;
           case 4:   str = "4";
               break;
           case 5:   str = "5";
               break;
           case 6:   str = "6";
               break;
           case 7:   str = "7";
               break;
           case 8:   str = "8";
               break;
           case 9:   str = "9";
               break;
           case 10:  str = "10";
               break;
           case 11:  str = "Jack";
               break;
           case 12:  str = "Queen";
               break;
           case 13:  str = "King";
               break;
           default:  str =  "?";
        }
        
        return str;
        
     }
     
     public void setSpriteLocation() {
    
         switch (suit) {
            
            case SPADES: yLoc = 0;
                break;
            case HEARTS: yLoc = 139;
                break;
            case DIAMONDS: yLoc = 278;
                break;
            case CLUBS: yLoc = 418;
                break;
            default:
                yLoc = 566;
        }
         
        switch ( value ) {
           case 1:   xLoc = 1160;
               break;
           case 2:   xLoc = 32;
               break;
           case 3:   xLoc = 126;
               break;
           case 4:   xLoc = 220;
               break;
           case 5:   xLoc = 314;
               break;
           case 6:   xLoc = 408;
               break;
           case 7:   xLoc = 502;
               break;
           case 8:   xLoc = 596;
               break;
           case 9:   xLoc = 690;
               break;
           case 10:  xLoc = 784;
               break;
           case 11:  xLoc = 878;
               break;
           case 12:  xLoc = 972;
               break;
           case 13:  xLoc = 1066;
               break;
           default:  xLoc =  596;
        }
         
     }
        
}
