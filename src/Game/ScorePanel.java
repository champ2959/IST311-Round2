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
    JLabel clicksLabel = new JLabel("Clicks: " + clickCount);
    JLabel timeLabel = new JLabel("Time: " + timeCount);
    
    
    
    
    public ScorePanel(){
        
        super();
        add(clicksLabel);
        add(timeLabel);
        
    }
            
    
}
