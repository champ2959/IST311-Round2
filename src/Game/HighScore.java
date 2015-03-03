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
import java.io.FileOutputStream;
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
    
     public HighScore(String theName, int theScore){
            
            super();
            setBackground(new Color(255, 0, 83));

            scores = new ArrayList<Score>();
            name = theName;
            score = theScore;
            
            addScore();
            
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
         try{
            outputStream = new ObjectOutputStream(new FileOutputStream(SCORE_FILE));
            outputStream.writeObject(scores);            
        }
        catch(FileNotFoundException e){
            System.out.println(" ");
        }
        catch(IOException e){
            System.out.println("_");
        }
        finally {
            try{
                    if (outputStream != null){
                        outputStream.flush();
                        outputStream.close();
                    }
        } catch (IOException e){
                System.out.println(",");
                    }
        }
    }
    
    public String getHighScoreString(){
        
        String highScoreString = " ";
        int max = 10;
        
        
        scores = getScores();
        
        int i = 0;
        int x = scores.size();
        if(x > max){
            x = max;
        }
         while (i < x) {
             highScoreString += (i + 1) + ".\t" + scores.get(i).getName() + 
                     ".\t\t" + scores.get(i).getScore() + "\n";
             i++;
                    
        }
       return highScoreString;
    }
    
}


