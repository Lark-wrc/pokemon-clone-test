import java.util.*;
import java.io.*;

/**
 * 
 */

public class Command {
	
	ArrayList<String> commands = new ArrayList<String>();
	int lastClearedIndex = -1;
	String[] lastClearedCmd;
	Input in = new Input();
	
	public Command() {
		
		commands.add("go");
		commands.add("view");
		commands.add("check");
		commands.add("look");
		commands.add("play");
	}
	
	public boolean legalFirst(String[] s) {
		
		String comp = s[0];
		boolean valid = false;
		
		for(String x : commands)
		{
			if(x.equals(comp)) 
			{
				valid = true;
				lastClearedIndex = commands.indexOf(x);
				lastClearedCmd = s; 
			}
		}
		
		return valid;
	}
	
	public boolean legalRoomCommand(String v) {
		
		/**
		 * char reeses = v.charAt(lastClearedIndex);
		 *	if(reeses != 'F') return true;
		 *	else return false;
		 */
		return true;
	}
	
	public Player process(Player p) {
		
		Player ret = p;
		if(lastClearedCmd.length > 1)
		{
			switch(lastClearedIndex) 
			{
				case 0:
					ret = go(p);
					break;
				case 1:
					view(p);
					break;
				case 2:
					check(p.currentRoom());
					break;
				case 3:
					look(p.currentRoom());
					break;
				case 4:
					play(p.currentRoom());
					break;
			}
		}
		else if(lastClearedIndex == 1) view(p);
		else 
		{
			System.out.println("You need more information.");
			System.out.println("");
		}
		
		return ret;
	}
	
	public void pause() {
		
		Scanner scan = new Scanner(System.in);
		System.out.print("||");
		String i = scan.nextLine();
	}
		
	
	public Player go(Player p)
	{
		String secondToken = in.compressCmd(lastClearedCmd);
		HashMap<String, Room> exits = p.currentRoom().getRoomExits();
		
		boolean ok = false;
		
		if(exits.containsKey(secondToken))
		{
			if(p.currentRoom().hasItemLock(secondToken))
			{
				System.out.println("You need a key to go this way. Do you have one?");
				Item choice = in.selectItem(p);
				if(choice != null)
				{
					if(p.currentRoom().openItemLock(secondToken, choice))
					{
						System.out.println("You opened the door.");
						Room ro = exits.get(secondToken);
						p.currentRoom(ro);
					}
					else System.out.println("You can't open it with that.");
							   pause();
				}
			}
			else 
			{
				Room ro = exits.get(secondToken);
				p.currentRoom(ro);
			}
		}
		else
		{
			System.out.println("There's no exit that way.");
			System.out.println("");
		}
		return p;
	}
	
	public void view(Player p)
	{
		String secondToken;
		
		if(lastClearedCmd.length > 1)
		{
			secondToken = lastClearedCmd[1];
		}
		else
		{
			System.out.println("What do you want to view?");
			System.out.println("Options: items");
			String[] cookies = in.parser();
			secondToken = cookies[0];
		}
		
		switch(secondToken)
		{
			case "items":
				System.out.println(p.inventoryToString());
				System.out.println("");
				System.out.println("Get detailed info? y/n");
				String[] park = in.parser();
				String hershey = park[0];
				
				if(park[0].equals("y"))
				{
					System.out.println("");
					Item c = in.selectItem(p);
					if(c != null)
					{
						System.out.println(c.description());
					}
				}
				break;
				
			default:
				System.out.println("You can't check that.");
		}
		pause();
		System.out.println("");
		
	}
	
	public void check(Room c)
	{
		String secondToken = lastClearedCmd[1];
		secondToken = secondToken.toLowerCase();
		
		ArrayList<Item> arr = c.getItems();
		boolean fail = true;
		for(Item x : arr)
		{
			if(x.name().equals(secondToken))
			{
				System.out.println(x.description());
				System.out.println("");
				fail = false;
			}
		}
		if(fail) 
		{
			System.out.println("That thing isn't here.");
			System.out.println("");
		}
		pause();
		
	}
	
	public void look(Room c)
	{
		String secondToken = lastClearedCmd[1];
		
		HashMap<String, Room> exits = c.getRoomExits();
		if(secondToken.equals("here"))
		{
			c.printDescription();
		}
		
		else if(exits.containsKey(secondToken))
		{
			exits.get(secondToken).printDescription();
		}
		pause();
	}
	
	public void play(Room c)
	{
		String secondToken = lastClearedCmd[1];
		secondToken = secondToken.toLowerCase();
		
		ArrayList<Item> arr = c.getItems();
		boolean fail = true;
		for(Item x : arr)
		{
			if(x.name().equals(secondToken))
			{
				fail = false;
			}
		}
		if(!fail && secondToken.equals("wii"))
		{
			System.out.println("You played Wii. It wasn't that fun.");
			System.out.println("");
		}
		else if(!fail && secondToken.equals("wii"))
		{
			System.out.println("There isn't one to play.");
			System.out.println("");
		}
		else
		{
			System.out.println("You can't play that!");
			System.out.println("");
		}
		
		pause();
	}
}