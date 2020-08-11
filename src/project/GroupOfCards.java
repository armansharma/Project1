/**
 * SYST 17796 Project Winter 2019 Base code.
 * Students can modify and extend to implement their game.
 * Add your name as a modifier and the date!
 */
package project;
import java.util.Random;

/**
 * A concrete class that represents any grouping of cards for a Game.
 * HINT, you might want to subclass this more than once.
 * The group of cards has a maximum size attribute which is flexible for reuse.
 * @author dancye
 * 
 */
public class GroupOfCards 
{
    // Declaring the variables
    public static int size;
    private Random card;
    public String colour;
    private String face;
    public int value;
  
    // A contructor with two arguments 
    public GroupOfCards(int givenSize, String givenColour)
    {
        colour=givenColour;
        size = givenSize;
    }
    
     // A no argument Constructor 
    // Generating a random Group of Cards.
    public GroupOfCards(){
        card = new Random();
        size = card.nextInt(28);
        if (size >=15){
            size -=15;
            card = new Random();
            switch(card.nextInt(4))
            {
                case 1: colour = "Red";
                case 2: colour = "Yellow";
                case 3: colour = "Blue";
                case 4: colour = "Green";
                break;
            }
            // when a wild card comes 
            
            if(size >=13){
                colour = "none";
            }
        }
    }
    
     //A method for getting the face of the card     
    public String getFace(){
        face = "[";
        if(!"none".equals(colour)){
            face += this.colour;
            
        }
            // Switch case for getting the face 
            switch(GroupOfCards.size){
                default: face += String.valueOf(GroupOfCards.size); 
                break;
            case 11: face += "Skip"; 
                break;
            case 12: face += "Reverse"; 
                break;
            case 13: face += "Draw 2"; 
                break; 
            case 14: face += "Wild Draw 4"; 
                break;
        }
        face += "]";
        return face;
            } 
    
    // A method to Check if we can place the card over the previous one
    public boolean canPlace(GroupOfCards g, String c)
    {
        if (this.colour.equals(c))
            return true;
        else if (this.value == g.value)
            return true;
        else if ("none".equals(this.colour))
            return true;
        else 
            return false;
    }
        
}