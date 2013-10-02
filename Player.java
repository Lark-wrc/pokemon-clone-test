import java.util.*;
import java.io.*;

/**
 * To be the storage class for the player's things, gear, whatnot. 
 * It will be more accessed in the gameplay functions than battle 
 * methods, so it has been left unimplemented.
 * Hopefully, it will also serve as the save file for the game,
 * allowing for simple(ha) saving on the code end.
 * 
 * @author Bill Clark
 * @version 0.1
 */
public class Player
{
    //fields.
    int storyIndex;
    boolean toQuitStory = false;
	ArrayList<Item> inventory = new ArrayList<Item>();

    
    //make the file object with the game text.
    File file = new File("game.txt");   
    Reader game = new Reader(file);
	
    Room current;
    
    //Constructor for Player class. Player is a database class that holds player's team, items, and any other information that needs to be stored long term.
    public Player() {
        
        inventory.add(new Item("Potion", "A Pokemon heath restorative. Cures 20 points of damage.", true, true));
    }

    //Story flags method. If it's a story flag, it'll run the flag just hit.
    public void checkStoryFlags() {
        
        if(storyIndex == 0 && current.name().equals("Brendan's Bedroom"))
        {
            readStoryEvent();
        }
        if(storyIndex == 1 && current.name().equals("Condo Kitchen"))
		{
			readStoryEvent();
		}
    }
    
    public String[] processCmdArray(String[] in) {
        
        String[] ret = in;
        
        if(in[1].equals("wait"))
        {
			
			Scanner input = new Scanner(System.in);
			System.out.print("||");
			String i = input.nextLine();
        }
        if(in[1].equals("end"))
        {
            toQuitStory = true;
            storyIndex(1);
        }
        
        
        return ret;
    }
    
    public void readStoryEvent() {
        
        toQuitStory = false;
        
        while(!toQuitStory)
        {
            String[] line = game.getNextLine();
            if(line[0].equals("cmd"))
            {
                processCmdArray(line);
            }
            
            
            else
            {
                for(String x: line) System.out.println(x);
                System.out.println();
            }
        }
    }
    
    
    //accessors
    public int storyIndex() {
		
		return storyIndex;
	}
    
    public Room currentRoom() {
        
        return current;
    }
   
	public ArrayList getInventory() {
		
		return inventory;
	}
   
	public String inventoryToString() {
		
		String ret = "";
		for(Item x: inventory)
		{
			
			ret = ret + x.name() + " ";
		}
		
		return ret;
	}
   
    //mutators
    public void storyIndex(int i) {
		
		storyIndex = storyIndex + i;
		
	}

    public void currentRoom(Room r) {
        
        current = r;
    }
}
