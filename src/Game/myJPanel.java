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
import java.io.IOException;
import javax.swing.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Erik Galloway, Mark, Nahom
 */
public class myJPanel extends JPanel implements ActionListener {
    ArrayList<Card> deck;
    Card card;
    DeckPanel deckPanel;
    ScorePanel scorePanel;
    boolean correct;
    int t = 0;
    Timer time;
    JButton start, jb, cardButton;
    JLabel welcome, nameLabel;
    JTextField nameInput;
    String userName;
    int userScore = 0;
    int delay = 400;
    
    HighScore highScore;
    
    public myJPanel() throws IOException {  // why do we need to throw an IOException?
        super();
        setBackground(new Color(255, 0, 83));
        
        setLayout(null);
        Font f1 = new Font("Arial", Font.PLAIN,28); 
        Font f2 = new Font("Arial", Font.PLAIN,12);
        welcome = new JLabel(" Card Game ");
        welcome.setFont(f1);
        welcome.setBounds(210, 40, 225, 30);
        
        this.createCards();
 
        
        correct = false;
        
        card.setBounds(0, 60, 640, 330);
        
        time = new Timer(delay, this);
        
        deckPanel = new DeckPanel(deck, 1, time);
        scorePanel = new ScorePanel();
        
        nameLabel = new JLabel("Your Name: ");
        nameLabel.setBounds(180, 140, 80, 30);
        nameLabel.setForeground(Color.white);
        
        nameInput = new JTextField("");
        nameInput.setColumns(50);
        nameInput.setBounds(250, 140, 130, 30);
        
        start = new JButton("Start");
        start.setBounds(250, 180, 130, 40);   start.setFont(f2);
        start.setSize(130, 40);
        start.addActionListener(this);
       
        add(welcome);
        add(nameLabel);
        add(nameInput);
        add(start);
        
        deckPanel.setBounds(0, 0, 640, 440);
        
        
       // add(deckPanel);
    }  // end constructor

    public void createCards() throws IOException {
        
        deck = new ArrayList();//deck = new ArrayList<>();  changed this, just needed to remove angle brackets
        
        // The number of cards created
        int cardCount = 0;      
        
        for (int suits = 0; suits <= 3; suits++) {
            
            for (int value = 1; value <= 13; value++) {
                
                card = new Card(value, suits, false);
                
                deck.add(cardCount, card);
                
                cardCount++;
            }
            
        }
        
    }
    
    public void actionPerformed(ActionEvent e) {
    //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        Object obj = e.getSource();
        if (obj == start){
            
            userName = nameInput.getText();
            
            if (userName.length() == 0 || userName.equals("You forgot your Name")) {
                
                nameInput.setText("You forgot your Name");
                return;
            }
            // if we started the game remove the start/welcome
            remove(start);
            remove(nameInput);
            remove(nameLabel);
            remove(welcome);
                       
          
            
            setLayout(new BorderLayout());
            // add the deckPanel (game)
            add(deckPanel, BorderLayout.CENTER);
            add(scorePanel, BorderLayout.SOUTH);
            // validate so the Panel checks what to add/remove
            validate();
            // repaint the screen
            repaint();
            
            time.start();
            
        }
        
        if (obj == time) {
           
            
            
            if (deckPanel.nextRound == true) {
                
               int round = deckPanel.round + 1; 
               
               time.stop();
                userScore = userScore + Game.App.game.p1.scorePanel.timeLeft;
                Game.App.game.p1.scorePanel.timeLeft = round * 30;
                
                if(round == 4) {
                    
                    highScore = new HighScore(userName, userScore);
                    remove(deckPanel);
                    add(highScore);
                    validate();
                    repaint();
                    //System.out.println(highScore.getHighScoreString());
                    return;
                }
                
                
                remove(deckPanel);
                
                deck.clear();
            
                try {
                    
                    this.createCards();
                    
                } 
                catch (IOException ex) {
                
                    Logger.getLogger(myJPanel.class.getName()).log(Level.SEVERE, null, ex);
                
                }
                
                    
                deckPanel = null;
                
                try {
                    deckPanel = new DeckPanel(deck, round, time);
                } catch (IOException ex) {
                    Logger.getLogger(myJPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                add(deckPanel, BorderLayout.CENTER);
                
                deckPanel.setBounds(0, 0, 640, 440);
                time.restart();
                // validate so the Panel checks what to add/remove
                validate();
            
                // repaint the screen
                repaint();
                
                
            
            
            }
       
        } 
                    
    }
}
