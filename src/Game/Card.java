/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.List;
import java.util.Vector;
import javax.swing.JButton;

/**
 *
 * @author nyz5034
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
    private int suit;
    // The card value
    private int value;
    // The deck
    public List deck;
    
    // The number of cards
    public int cardCount = 8;
    
    public Card(int val, int theSuit) {
        
        value = val;
        
        suit = theSuit;       
        
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
    
}
