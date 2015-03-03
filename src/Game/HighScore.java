/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Game;
import javax.swing.JPanel;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author nyz5034
 */
public class HighScore extends JPanel {
     
    ArrayList<Score> scores;
    private static final String SCORE_FILE = "Scores.dat";
    
    ObjectOutputStream outputStream;
    ObjectInputStream inputStream;
    
    String name;
    int score;
    
     public HighScore( String theName, int theScore){
            
            super();
            setBackground(new Color(255, 0, 83));

            scores = new ArrayList<Score>();
            name = theName;
            score = theScore;
            
     }
     
    public ArrayList<Score> getScores() {
        loadScoreFile();
        sort();
        return scores;
    }
    
    private void sort() {
     
    
}
    public void loadScoreFile(){
        try{
            inputStream = new ObjectInputStream(new FileInputStream(SCORE_FILE));
            scores = (ArrayList<Score>) inputStream.readObject();
            
        }
        catch(FileNotFoundException e){
            System.out.println(" ");
        }
        catch(IOException e){
            System.out.println("_");
        }
        catch(ClassNotFoundException e){
            System.out.println("..");
        }
        finally {
            try{
                    if(outputStream != null){
                        outputStream.flush();
                        outputStream.close();
                    }
        } catch (IOException e){
                System.out.println(",");
                    }
        }
        
    }
    
    public void addScore(){
        loadScoreFile();
        scores.add(new Score(name, score));
        updateScoreFile();
        
    
    }
    
    public void updateScoreFile(){
        
    }
}


