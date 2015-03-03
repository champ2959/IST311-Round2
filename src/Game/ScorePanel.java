/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author markhancharik
 */
public class ScorePanel extends JPanel{
    
   
    int clickCount = 0;
    int timeCount = 0;
    int timeLeft = 30;
    JLabel clicksLabel;
    JLabel timeLabel;
    
    
    
    
    public ScorePanel(){
        
        super();
       // System.out.println("Round :" + Game.App.game.p1.deckPanel.round);
        //timeLeft = Game.App.game.p1.deckPanel.round * 30;
        clicksLabel = new JLabel("Total Score: ");// + Game.App.game.p1.userScore);
        timeLabel = new JLabel("Time Left: " + timeLeft);
        add(clicksLabel);
        add(timeLabel);
        
    }
            
    
}
