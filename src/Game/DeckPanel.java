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
    
    public DeckPanel(ArrayList theDeck) {
        
        deck = theDeck;
        
        
    }
    
    public void drawCards(int pairs) {
          // Put all the used cards back into the deck, and shuffle it into
          // a random order.
        for ( int i = 51; i > 0; i-- ) {
            
            int rand = (int)(Math.random()*(i+1));
            
            Object temp = deck.get(i);
            
            deck.set(i, deck.get(rand));
            deck.set(rand, temp);
        }
        
        
        
    }
    
}
