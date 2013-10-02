import java.util.*;

/**
 * note: -1 is a game wide response for unrecongized.
 * 
 * Input is the class that handles input. It functions by taking user input 
 * (generated here, along with it's prompt) and comparing it to the list of possible options, then 
 * returning an int value associated with that command to be processed. The
 * various methods are called at different times in the program, limiting the
 * possible reponses. 
 * 
 * @author Bill Clark
 * @version 0.1
 * 
 */
public class Input
{
    /** A scanner used to read the terminal. */
    Scanner scan = new Scanner(System.in);
    
    /**
     * Constructor, unused.
     */
    public Input() {
        
        
    }
    
    
    
    //Function Methods.
    
    /**
     * This method is before commands in battle are processed. It contains 
     * checks for commands that aren't call specific, such as quit, and print
     * info on a pokemon. If a command matches, the method returns which on as
     * a negative value and ends the calling method with a return of the value 
     * this method returns. Then the general command is the one processed.
     * 
     * @param i The input from the user.
     * @return int The value of the command matched, or -1 for unrecongized.
     */
    public int genBatCommands(String i) {
        
        int ret;
        //switch case for checking commands.
        switch(i) 
        {
            case "quit": 
                ret = -2;
                break;
            case "info":
                ret = -3;
                break;
            default:
                ret = -1;
        }
        return ret;
    }
    
    /**
     * This method is called for a top level battle commands. It returns an 
     * int value to be processed, different depending on which command was 
     * entered. It calls to genBatCommands for general commands before 
     * processing it's own.
     * fight 0, item 1, pkmn 2, run 3.
     * 
     * @return The int associated with legal commands, else -1 for unknown.
     */
    public int battleOne() {
        
        int ret;
        
        //prints the prompt.
        System.out.println("1: Fight | 2: Item");
        System.out.println("3: PKMN  | 4: Run");
        System.out.println("What to do?");
        System.out.println("");
        System.out.print("> ");
        
        //scans line, and makes it lowercase for comparisons.
        String input = scan.nextLine();
        input = input.toLowerCase().trim();
        
        //checks input against genBatCommands incase of nonspecific input.
        int g = genBatCommands(input);
        
        if(g <= -2) return g;
        
        //begins the comparisons to the prompted commands.
        switch(input) 
        {
            case "fight": 
                ret = 0;
                break;
            case "item":
                ret = 1;
                break;
            case "pkmn":
                ret = 2;
                break;
            case "run":
                ret = 3;
                break;
            default:
                ret = -1;
        }
        
        //returns the selected input.
        return ret;
        
    }
    
    /**
     * This method prompts the user with the moves their pokemon can use, 
     * then returns the selection. It prompts with the moves in the array
     * parameter supplied, gets input, checks against genBatCommands, then
     * returns the index of the move that matches the players typed input.
     * 
     * @param moves An array of the pokemon's current moves.
     * @return int the index of the move selected.
     */
    public int playerMove(Move[] moves) {
        
        int ret;
        
        //print prompt
        String al, be, ze, om;
        if(moves[0] != null) al = moves[0].getName();
        else al = "";
        if(moves[1] != null) be = moves[1].getName();
        else be = "";
        if(moves[2] != null) ze = moves[2].getName();
        else ze = "";
        if(moves[3] != null) om = moves[3].getName();
        else om = "";
        
        
        System.out.println("1: " + al + " | 2: " + be);
        System.out.println("3: " + ze + " | 4: " + om);
        System.out.println("Which move?");
        System.out.println();
        System.out.print("> ");
        
        //get input and make lowercase.
        String input = scan.nextLine();
        input = input.toLowerCase().trim();
        
        //check genBatCommands.
        int g = genBatCommands(input);
        if(g <= -2) return g;
        
        //make lowercase move name strings.
        
        String m1 = al.toLowerCase();
        String m2 = be.toLowerCase();
        String m3 = ze.toLowerCase();
        String m4 = om.toLowerCase();
        
        //switch statement for returning the selected move.
        if(input.equals(m1))
        {
            ret = 0;
        }
        else if(input.equals(m2))
        {
            ret = 1;
        }
        else if(input.equals(m3))
        {
            ret = 2;
        }
        else if(input.equals(m4))
        {
            ret = 3;
        }
        else ret = -1;
        return ret;
    }
    
    public String[] parser() {
        
        System.out.print(">");
        String in = scan.nextLine();
        
        String[] ret = in.split(" ");
        return ret;
    }

	public Item selectItem(Player p) {
		
		ArrayList<Item> i = p.getInventory();
		if(i.size() > 0)
		{
			System.out.println(p.inventoryToString());
			System.out.println("");
			System.out.println("Select an Item.");
			System.out.print(">");
			String choice = scan.nextLine();
			
			for(Item f: i)
			{
				if(f.name().equals(choice.trim()))
				{
					return f;
				}
			}
			System.out.println("You don't have that.");
		}
		else System.out.println("You don't have any items.");
		return null;
	}

	public String compressCmd(String[] cmd) {
		
		String ret = "";
		for(int x = 1; x < cmd.length; x++)
		{
			ret = ret + cmd[x].trim() + " ";
		}
		return ret.trim();
	}
}
