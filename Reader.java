import java.util.*;
import java.io.*;

/**
 * 
 */

public class Reader {
    
    File game;
    Scanner scan;
    
    
    
    public Reader(File file) {
        
        game = file;
        try {
            scan = new Scanner(file);
        }
        catch(FileNotFoundException e) {
            
        }
    }
    
    public String[] getNextLine() {
        
        String i = scan.nextLine();
        String[] ret = i.split("@");
        
        return ret;
    }
}