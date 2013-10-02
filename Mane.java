import java.util.*;
import java.io.*;

/**
 * 
 */

public class Mane {
    
    boolean quit = false;
    Input in = new Input();
    Command cmd = new Command();
    Player brendan = new Player();
    ArrayList<Room> world = new ArrayList<Room>();
    
    
    
    
    public static void main(String[] args) {
        
        Mane mane = new Mane();
        mane.start();
    }
    
    public void start() {
        
        buildRooms();
        System.out.println(world.get(1).se());
        brendan.currentRoom(world.get(0));
        brendan.checkStoryFlags();
        
        while(!quit)
        {
            brendan.currentRoom().printDescription();
            String[] usrin = in.parser();
            if(cmd.legalFirst(usrin))
            {
                if(cmd.legalRoomCommand(brendan.currentRoom().useableCmd()))
                {
                    cmd.process(brendan);
                }
                else
                {
                    System.out.println("You can't do that here.");
                    System.out.println("");
                }
            }
            else 
            {
                System.out.println("Unrecognized. Try again.");
                System.out.println("");
            }
            brendan.checkStoryFlags();
        }
        
    }
    
    
    public void buildRooms() {
        
        Scanner rscan;
        File rooms = new File("rooms.txt");
        try {
            rscan = new Scanner(rooms);
        }
        catch(FileNotFoundException e) {
            rscan = new Scanner(System.in);
        }
        
        while(rscan.hasNextLine())
        {
            String l = rscan.nextLine();
            String[] r = l.split("@");
            
			if(r.length > 3)
			{
			  //put a switch here for r[3] to see which scan to use.
				boolean t = Boolean.parseBoolean(r[6]);
				Item i = new Item(r[4], r[5]);
				world.add(new Room (r[0], r[1], r[2], r[3], i));
			}
			else
			{
				Room temp = new Room(r[0], r[1], r[2]);
				world.add(temp);
			}
        }
        
        Scanner escan;
        File exits = new File("exits.txt");
        try {
            escan = new Scanner(exits);
        }
        catch(FileNotFoundException e) {
            escan = new Scanner(System.in);
        }
        
        escan.useDelimiter("@");
        int counter = -1;
        while(escan.hasNextLine())
        {
            String in = escan.nextLine();
            String[] fin = in.split("@");
            int from = Integer.parseInt(fin[0]);
            int to = Integer.parseInt(fin[2]);
            
            world.get(from).setExit(fin[1], world.get(to));
        }
        
        Scanner iscan;
        File ite = new File("items.txt");
        try {
            iscan = new Scanner(ite);
        }
        catch(FileNotFoundException e) {
            iscan = new Scanner(System.in);
        }
        
        while(iscan.hasNextLine())
		{
			String l = iscan.nextLine();
			String[] r = l.split("@");
			if(r.length == 3)
			{
			  int from = Integer.parseInt(r[0]);
			  world.get(from).addItem(new Item(r[1], r[2]));
			}
		}
    }

    public void updateBrendan(Player p) {
        
        brendan = p;
    }
}