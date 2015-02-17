/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;

import java.awt.*;
import java.io.IOException;
import javax.swing.*;
/**
 *
 * @author nyz5034
 */
public class myJFrame extends JFrame {
    
    public myJFrame() throws IOException{
        super("Game");
        getContentPane().setLayout(new BorderLayout());
      
        myJPanel p1 = new myJPanel();
    
    
    	getContentPane().add(p1,"Center");
		

        setDefaultCloseOperation(EXIT_ON_CLOSE);
	setSize (650, 480);
	setVisible(true);
    }

}
