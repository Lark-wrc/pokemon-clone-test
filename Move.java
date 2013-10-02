/*
/**
 * The move class is a storage class for the moves a pokemon
 * can use. It consists mostly of accessors. The only thing
 * of note is that there are two constructors, one for damage
 * moves and another for stat modifying moves. 
 * 
 * 
 * ToDo:
 * figure out how the FUCK to do special moves, like whirlwind.
 * 
 * @author Bill Clark
 * @version 0.1
 */
public class Move
{
    /** A string containing the name of the move. */
    String name;
    
    /** The int power of the move. */
    int power;
    
    /** The type of the move */
    String type;
    
    /** The move classification, as a char */
    char classification;
    
    /** The accuracy value of the move. */
    double accuracy;
    
    /** The PP of the move */
    int pp;
    
    /** the stat modified in a stat modifying move. */
    String statToMod;
    
    /** the amount to modify a stat per use. */
    int amntToMod;
    
    int maxPP;
    
    /**
     * Constructs a damaging move. It has dump variables in the
     * values that are used for stat mod moves.
     * 
     * FLAG:: NEEDS A SECOND TYPE FIELD
     * 
     * @param n the name of the move.
     * @param p the base power of the move.
     * @param t the type of the move.
     * @param c classification (physical, special, status...)
     * @param a accuracy of the move.
     * @param pe the PP value of the move.
     */
    public Move(String n, int p, String t, char c, double a, int pe) {
        
        name = n;
        power = p;
        type = t;
        classification = c;
        accuracy = a;
        pp = pe;
        maxPP = pe;
        
        statToMod = "";
        amntToMod = 0;
    }
    
    /**
     * Constructs a stat modifying move. It has a dump value for 
     * power, which stat modders don't use. Classification is auto
     * assigned to type Status.
     * 
     * FLAG:: NEEDS A SECOND TYPE FIELD
     * 
     * @param n the name of the move.
     * @param s the stat of the move being modified.
     * @param l amount the stat is modded on the stat is modded.
     * @param t the type of the move.
     * @param a accuracy of the move.
     * @param pe the PP value of the move.
     */
    public Move(String n, String s, int l, String t, double a, int pe) {
        
        name = n;
        statToMod = s;
        amntToMod = l;
        type = t;
        classification = 'T';
        accuracy = a;
        pp = pe;
        maxPP = pe;
        power = 0;
    }
    
    
    //Accessors
    
    /**
     * Accessor for the name of the move.
     * 
     * @return String that is the name of the move.
     */
    public String getName() {
        
        return name;
    }
    
    /**
     * Accessor for the classification of the move.
     * 
     * @return character value of the classification of move.
     */
    public char getC() {
        
        return classification;
    }
    
    /**
     * Accessor for the power of the move.
     * 
     * @return The int power value for the move.
     */
    public int getPower() {
        
        return power;
    }
    
    /**
     * Accessor for the PP of the move.
     * 
     * @return the int PP of the move.
     */
    public int getPP() {
        
        return pp;
    }
    
    /**
     * Accessor for the accuracy of the move.
     * 
     * @return the double accuracy of the move.
     */
    public double getAcc() {
        
        return accuracy;
    }
    
    /**
     * Accessor for the stat to modify.
     * 
     * @return returns the string name of the stat to mod.
     */
    public String getStatToMod() {
        
        return statToMod;
    }
    
    /**
     * Accessor for the amount to modify.
     * 
     * @return Returns the int level amount to modify the stat.
     */
    public int getAmntToMod() {
        
        return amntToMod;
    }
    //Mutators
    
    /**
     * Mutator that subtracts one from the PP of a move.
     * called in move use calculations.
     */
    public void minusPP() {
        
        pp--;
    }
    
    
}
