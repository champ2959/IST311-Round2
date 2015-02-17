/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.event.ActionListener;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import java.util.ArrayList;
/**
 *
 * @author nyz5034
 */
public class myJPanel extends JPanel implements ActionListener {
    ArrayList<Card> deck;
    Card card;
    DeckPanel deckPanel;
    boolean correct;
    int t = 0;
    Timer time;
    JButton start;
    JLabel welcome;
    int delay = 100;
    
    public myJPanel( )//( Card c1)
    {
        setBackground(new Color(255, 0, 83));
        
        setLayout(null);
        Font f1 = new Font("Arial", Font.PLAIN,28); 
        Font f2 = new Font("Arial", Font.PLAIN,12);
        welcome = new JLabel(" Card Game ");
        welcome.setFont(f1);
        welcome.setBounds(210, 40, 225, 30);
        
        this.createCards();
        
        
        deckPanel = new DeckPanel(deck, 1);
        
       // card = c1;
        correct = false;
        
        card.setBounds(0, 60, 640, 330);
        
        time = new Timer(delay, this);
        
        start = new JButton("Start");
        start.setBounds(250, 170, 130, 40);   start.setFont(f2);
        add(start);
    }

    public void createCards() {
        
        deck = new ArrayList<>();
        
        // The number of cards created
        int cardCount = 0;      
        
        for (int suits = 0; suits <= 3; suits++) {
            
            for (int value = 1; value <= 13; value++) {
                
                card = new Card(value, suits);
                
                deck.add(cardCount, card);
                
                cardCount++;
            }
            
        }
        
    }
    public void actionPerformed(ActionEvent e) {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Object obj = e.getSource();
        if (obj == start){
            add(card);
            t++;
            
        }
    }
}
