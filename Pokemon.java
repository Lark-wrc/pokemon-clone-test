import java.util.*;
/**
 * The pokemon class is the first object in the project.
 * It creates, using the database class pokedex, a pokemon
 * based off two factors, the pokemon's dex number and its
 * level. After that it generates unique stats and stores
 * a unique pokemon. A pokemon generated at the end of 
 * the constructor will have all it's fields filled, and
 * be ready for a battle. It can be used to generate wild
 * pokemon or trainer pokemon. 
 * This class is damn near complete. It still has a few 
 * missing fields though, due to them not being used in 
 * the battle class as of yet.
 * 
 * ToDo:
 * status effect storage, access, mutate
 * leveling up method
 * ev, xp gain
 * 
 * @author Bill Clark
 * @version 0.1
 */




public class Pokemon
{
    /**The effort values the pokemon has.
     * inital zeros.*/
    private int[] ev = {0, 0, 0, 0, 0, 0};
    
    /**The indiviual values of the pokemon. generated in
     *	 constructor.*/
    private int[] iv;
    
    /**The base stats for the pokemon's species.*/
    private int[] base;
    
    /** The stats of a pokemon at the level it's at. */
    private int[] stats;
    
    /**The move list of moves to learn naturally. 
     *	 hashed level learned, Move object.*/
    private HashMap<Integer, Move> moveList;
    
    /**An array of the moves the pokemon has now.*/
    private Move[] moves = new Move[4];
    
    /**The modifications to stats from the nature.*/ 
    private double[] natureStats;
    
    /**Pokemon's nickname.*/
    private String nick;
    
    /**pokemon's current level.*/
    private int level;
    
    /**pokemon's pokedex number.*/
    private int dexN;
    
    /**pokemon's type. */
    private int[] type;
    
    /** Pokemon's type name */
    private String typeName;
    
    /**Pokemon's nature.*/
    private String nature;
    
    /**The oldest move index in the move[]*/
    private int oldestMove = 0;
    
    /**The count for move auto generation. */
    private int moveCount = 0;
    
    /**pokemon's species name.*/
    private String name;
    
    /**pokemon's current HP value.*/
    private int currentHP;
    
    
    
    /**
     * Creates a pokemon object. The constructor will
     * create a pokedex object, then use the two
     * parameters to generate a pokemon from the stats
     * obtained from the pokedex. All values will be
     * filled at the end of the constructor. 
     * 
     * @param dexNumber pokedex number of the pokemon to create.
     * @param lv level of the pokemon to create.
     */
    public Pokemon(int dexNumber, int lv) {
        
        //create a pokedex to get info from.
        Pokedex dexter = new Pokedex();
        
        base = dexter.getBaseStats(dexNumber);
        iv = genIVs();
        level = lv;
        nature = dexter.getRandNature();
        natureStats = dexter.getNatureStats(nature);
        stats = genStats();
        currentHP = stats[0];
        dexN = dexNumber;
        typeName = dexter.getTypeName(dexNumber);
        type = dexter.getType(dexNumber);
        name = dexter.getName(133);
        
        moveList = dexter.getMoveList(dexNumber);
        populateMoveList();
    }
    
    
    
    //Generator methods for creating and modding stats/moves.
    
    /**
     * Generates Indiviual values for the pokemon randomly.
     * This method creates the unique IV values for the
     * pokemon. It is only called in construction.
     * 
     * @return  The indiviual values in an int[]
     */
    private int[] genIVs() {
        
        int[] ret = new int[6];
        
        //generation loop
        for(int x = 0; x < 6; x++)
        {
            Random rand = new Random();
            int y = rand.nextInt(32);
            ret[x] = y;
        }
        return ret;
    }
    
    /**
     * Generate the stat values using IVs, EVs, and base stats.
     * This method calculates the pokemons stats, which happens
     * on construction as well as when the pokemon levels.
     * calculations are done as doubles in the method. 
     * 
     * note: while this class could be in the Cal class, it's
     * here due to the number of variables required to move as
     * well as the cal class wasn't written till this was already
     * implemented.
     * 
     * @return  int[] of the generated stats of the pokemon
     */
    private int[] genStats() {
        
        //generate HP first.
        int t = (iv[0] + (2 *base[0]) + (ev[0] / 4)) * level;
        int f = t / 100;
        int hp = f + 10;
        
        //generate ATK, DEF, SPATK, SPDEF, SPD
        int tt = (iv[1] + (2 *base[1]) + (ev[1] / 4)) * level;
        int ff = (tt / 100) +5;
        int atk = (int)((double)ff * natureStats[1]);
        
        int e = (iv[2] + (2 *base[2]) + (ev[2] / 4)) * level;
        int d = (e / 100) +5;
        int def = (int)((double)d * natureStats[2]);
        
        int ee = (iv[3] + (2 *base[3]) + (ev[3] / 4)) * level;
        int dd = (ee / 100) +5;
        int spatk = (int)((double)dd * natureStats[3]);
        
        int w = (iv[4] + (2 *base[4]) + (ev[4] / 4)) * level;
        int s = (w / 100) +5;
        int spdef = (int)((double)s * natureStats[4]);
        
        int ww = (iv[5] + (2 *base[5]) + (ev[5] / 4)) * level;
        int ss = (ww / 100) +5;
        int spd = (int)((double)ss * natureStats[5]);
        
        int[] ret = {hp, atk, def, spatk, spdef, spd};
        return ret;
    }
    
    /**
     * Calls the required methods and does the math to increase
     * the pokemon's level. 
     * 
     * FLAG:: UNIMPLEMENTED
     */
    private void levelup() {
        
        
    }
    
    
    /**
     * Populates the pokemon's current moves with the highest
     * moves available to it. It uses the moveList hashmap 
     * from the pokedex class to build an array of moves 
     * currently learned, for accessablity.
     * 
     * FLAG:: This can technically leave a pokemon with no 
     * attack moves. This needs to be fixed, or proven possible
     * in the original.
     */
    private void populateMoveList() {
        
        for(int x = -2; x <= level; x++)
        {
            Move mov = moveList.get(x);
            if(mov != null)
            {
                if(moveCount < 4)
                {
                    moves[moveCount] = mov;
                    moveCount++;
                    
                }
                else 
                {
                    moves[oldestMove] = mov;
                    oldestMove++;
                    if(oldestMove > 3)
                    {
                        oldestMove = 1;
                    }
                }
            }
        }
    }
    
    
    
    //Accessors
    
    /**
     * Accessor for the name of the pokemon. If the pokemon
     * has a nickname, that is returned instead.
     * 
     * @return String name, or nickname, of the pokemon.
     */
    public String getName() {
        
        if(nick != null)
        {
            return nick;
        }
        else return name;
    }
    
    /**
     * Accessor for a toString representation of the move list.
     * 
     * FLAG:: This is currently unused. It got reworked for UI
     * purposes. That section can either be rewritten 
     * (if neccessary) or this can be removed. It's still here
     * because it may be useful in the game functions.
     * 
     * @return String representation of the moves[]
     */
    public String movesTS() {
        
        String ret = "";
        for(int x = 0; x < 4; x++) 
        {
            ret = ret + moves[x].getName() + " ";
        }
        return ret;
    }
    
    /**
     * Accessor for the move[] of the pokemon.
     * 
     * @return The pokemon's move[]
     */
    public Move[] getMoves() {
        
        return moves;
    }
    
    /**
     * Multi Accessor for the stats of a pokemon.
     * 
     * note: This is an experiment, condensing the number 
     * of accessors in the class by providing a search
     * parameter. It works, as of 0.1.
     * 
     * @param i stat to be returned.
     * @return The integer stat value.
     */
    public int getStat(String i) {
        switch(i) {
            case "hp":
                return stats[0];
            case "attack": 
                return stats[1];
            case "defense":
                return stats[2];
            case "special attack":
                return stats[3];
            case "special defense":
                return stats[4];
            case "speed":
                return stats[5];
        }
        return 0;
    }
    
    /**
     * Accessor for the level of the pokemon.
     * 
     * @return Int of the pokemons level
     */
    public int getLevel() {
        
        return level;
    }
    
    /**
     * Accessor for the pokemon's current HP.
     * 
     * @return integer of the pokemon's current health.
     */
    public int getCurrentHP() {
        
        return currentHP;
    }
    
    /**
     * Accessor for the PP of a pokemon's move.
     * This is a chain method, only used when a method
     * can't access the move itself. 
     * 
     * @param move The int index of the move in the moves[]
     * @return the int PP of the specifed move.
     */
    public int getPP(int move) {
        
        return moves[move].getPP();
    }
    
    
    
    //Mutators
    
    /**
     * Mutator to change a pokemon's currentHP value.
     * 
     * @param ne New HP int value.
     */
    public void setHP(int ne) {
        
        currentHP = ne;
    }
    
    /**
     * Mutator to subtract one from the PP of a move.
     * This is a chain method, only used when a method
     * can't access the move itself.
     * 
     * @param y The int index of the move in moves[]
     */
    public void minusPP(int y) {
        
        moves[y].minusPP();
    }
    
    /**
     * Mutator to set a pokemon's nickname.
     * This method isn't called in the constructor, or 
     * anywhere in battle. It's useless now, but 
     * is here anyway. It will be used on capture,
     * if that's ever implemented.
     * 
     * @param nickname The String nickname the user gives.
     */
    public void nickname(String nickname) {
        
        nick = nickname;
    }
    
    /**
     * Mutator to change a pokemon's stat manually.
     * This method is used for stat damage moves. It shouldn't
     * be called for leveling up.
     * 
     * @param i String name of the stat to modify.
     * @param v The int amount to modify the stat to.
     */ 
    public void setStat(String i, int v) {
        
        switch(i) {
            case "hp":
                stats[0] = v;
                break;
            case "attack": 
                stats[1] = v;
                break;
            case "defense":
                stats[2] = v;
                break;
            case "special attack":
                stats[3] = v;
                break;
            case "special defense":
                stats[4] = v;
                break;
            case "speed":
                stats[5] = v;
                break;
        }
    }
    
}
