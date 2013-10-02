import java.util.*;
/**
 * This class is a database class. When called, it will read from a database
 * file (that is NOT IMPLEMENTED) the varius information needed to generate a
 * pokemon object. It has no other purpose than returning large amounts of 
 * information. 
 * This class does not work as intended yet, as it doesn't read from any 
 * database file. The information for the test pokemon eevee is generated in
 * the constructor.
 * 
 * FLAG:: Nature is miss implimented, as it has one nature, not the entire set.
 * this requires changing the rand generation in getRandNature as well as the 
 * construction, and declaration.
 * 
 * ToDo:
 * make work as intended.
 * check over moves. Feels like it's going to crap out when properly built.
 * 
 * DISCLAIMER:
 * all method documentation is going to be about the future implementation of 
 * the class. It is not accurate to how it works now.
 * 
 * @author Bill Clark 
 * @version 0.1
 */
public class Pokedex
{
    /** A database of basestats for pokemon species. hashed dex number, 
     *	 int[] of the stats.*/
    HashMap<Integer, int[]> baseStats = new HashMap<Integer, int[]>();
    
    /** A database for getting the type of a pokemon. hashed dex number,
     *	 String type.*/
    HashMap<Integer, String> typeName = new HashMap<Integer, String>();
    
    /** A database for getting the type number of a pokemon, hashed
     * dex number, type number. */
    HashMap<Integer, int[]> type = new HashMap<Integer, int[]>();
    
    /** A database of nature stat modifications for a pokemon.
     * hashed String name of nature, int[] of the stats. */
    HashMap<String, double[]> natureStats = new HashMap<String, double[]>();
    
    /** A String[] of Nature names */
    String[] nature = new String[1];
    
    /** A database of hashmaps with the moves a species can learn.
     *	 hashed with dex number, hashmap.*/
    HashMap<Integer, HashMap> moveList = new HashMap<Integer, HashMap>();
    
    /** A database of the moves a pokemon knows. hashed level learned,
     *	 Move object.*/
    HashMap<Integer, Move> moveDict = new HashMap<Integer, Move>();
    
    /** Database, hashed dex number, String, of species names. */
    HashMap<Integer, String> names = new HashMap<Integer, String>();
    
    
    
    /**
     * The constructor calls to multiple methods that populate all the field
     * databases. 
     */
    public Pokedex() {
        
        int[] i = { 55, 55, 50, 45, 65, 55 };
        
        //set Eevee basestats
        baseStats.put(133, i);
        
        //set Eevee typeName
        typeName.put(133, "Normal");
        
        //set Eevee type
        int[] z = {0, -1};
        type.put(133, z);
        
        //add Eevee name
        names.put(133, "Eevee");
        
        //add to natureStats Jolly, and add to the nature[].
        double[] ii = {1, 1, 1, 0.9, 1, 1.1};
        natureStats.put("Jolly", ii);
        nature[0] = "Jolly";
        
        buildMoves();
        
    }
    
    
    
    //Database building methods. Which are not reading from anything as there is nothing.
    
    /**
     * This database building method builds the moveDict of a pokemon, which
     * is the list of moves a pokemon will learn via leveling. 
     */
    public void buildMoves() {
        
        Move tackle = new Move("Tackle", 35, "Normal", 'P', 100, 35);
        Move tailWhip = new Move("Tail Whip", "defense", -1, "Normal", 100, 30);
        Move helpingHand = new Move("Helping Hand", 0, "Normal", 'K', 100, 20);
        Move sandAttack = new Move("Sand Attack", "accuracy", -1, "Ground", 100, 20);
        Move growl = new Move("Growl", "attack", -1, "Normal", 100, 40);
        
        //build move list
        moveDict.put(-1, tackle);
        moveDict.put(0, tailWhip);
        moveDict.put(1, helpingHand);
        moveDict.put(8, sandAttack);
        moveDict.put(16, growl);
        
        //add moveDict to movelist
        moveList.put(133, moveDict);
    }
    
    
    
    //Accessors.
    
    /**
     * Accessor for the base stats of the specified pokemon.
     * 
     * @param dexN the dex number of the pokemon who's base stats you need.
     * @return int[] Int array of base stats.
     */
    public int[] getBaseStats(int dexN) {
        
        int[] ret = baseStats.get(133);
        return ret;
    }
    
    /**
     * Accessor that gets a random nature for pokemon generation.
     * 
     * @return a string value for accessing the nature hashmap.
     */
    public String getRandNature() {
        
        Random rand = new Random();
        int a = rand.nextInt(1);
        return nature[a];
    }
    
    /**
     * Accessor for the stat modifications the pokemon's nature will make.
     * 
     * @param i the name of the nature the pokemon has.
     * @return The double[] of changes the nature makes to a pokemon's stats.
     */
    public double[] getNatureStats(String i) {
        
        double[] ret = natureStats.get(i);
        return ret;
    }
    
    /**
     * Accessor for the type of a pokemon that's dex number is supplied.
     * 
     * FLAG:: THIS NEEDS TO CHANGE FOR MULTI TYPE POKEMON.
     * 
     * @param dexNumber dex number of the pokemon you want the type of.
     * @return A string that is the pokemon's type.
     */
    public String getTypeName(int dexNumber) {
        
        String ret = typeName.get(dexNumber);
        return ret;
    }
    
    /**
     * Returns the int value associated with a pokemon's type.
     * 
     * @param dexNumber The pokedex number of the pokemon you want.
     * @return The int value associated with the pokemon's type.
     */
    public int[] getType(int dexNumber) {
        
        return type.get(133);
    }
    
    /**
     * Accessor that gets the moveList of a pokemon who's number is supplied.
     * 
     * @param dexNumber dex number of the pokemon whose moves you want.
     * @return A hashmap with keys of int level gaining the Move value.
     */
    public HashMap<Integer, Move> getMoveList(int dexNumber) {
        
        return moveList.get(dexNumber);
    }
    
    /**
     * Accessor that returns the name of a pokemon by dex number.
     * 
     * @param dexN dex number of the pokemon you want
     * @return String name of the pokemon with the supplied dex number.
     */
    public String getName(int dexN) {
        
        return names.get(dexN);
    }
}
