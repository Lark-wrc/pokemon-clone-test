import java.util.*;

/**
 * 
 */

public class Item {
    
    String name;
    String description;
    boolean takable;
    boolean oneUse;
    boolean container;
    boolean dropable;
    Item[] storage;
    
    //takeable objects
    public Item(String n, String d, boolean t, boolean o) {
        
    name = n;
		description = d;
		dropable = t;
		oneUse = o;
    }
    
    //simple objects, things that exist for discriptions.
    public Item(String n, String d) {
        
    name = n;
		description = d;
		takable = false;
    }
    
    //simple objects that store things.
    public Item(String n, String d, int c)
    {
      name = n;
      description = d;
      container = true;
      storage = new Item[c];
    }

	//accessor
	public String name() {
		
		return name;
	}
	
	public String description() {
	
		return description;
	}
}