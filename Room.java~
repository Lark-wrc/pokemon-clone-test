import java.util.*;
import java.io.*;

/**
 * 
 */

public class Room {
    
    String name;
    ArrayList<Item> items = new ArrayList<Item>();
    String description;
    String validCmd;
    HashMap<String, Room> exits = new HashMap<String, Room>();
	HashMap<String, Item> itemLock = new HashMap<String, Item>();
    
    public Room(String n, String vc, String d) {
        
        name = n;
        description = d;
        validCmd = vc;
    }
    
    public Room(String n, String vc, String d, String e, Item i) {
        
        name = n;
        description = d;
        validCmd = vc;
		itemLock.put(e, i);
    }
    
    public void printDescription() {
        
        System.out.println(description);
        System.out.println();
    }
    
    public void setExit(String i, Room r) {
        
        exits.put(i, r);
    }
    
    public int se() 
    {
        return exits.size();
    }
    
    public boolean hasItemLock(String e) {
		
		if(itemLock.containsKey(e)) return true;
		else return false;
	}
	
	public boolean openItemLock(String e, Item k) {
		
		if(k.name().equals(itemLock.get(e).name())) return true;
		else return false;
	}
	
    //accessors
    public String name() {
        
        return name;
    }
    
    public String useableCmd() {
        
        return validCmd;
    }
    
    public HashMap getRoomExits() {
        
        return exits;
    }
    
    public ArrayList getItems() {
        
        return items;
    }
    
    
    //mutators
    public void name(String n) {
        
        name = n;
    }

	public void addItem(Item i) {
		
		items.add(i);
	}
}