#Javamon (or something like that) 
Basically a project I did when bored december of my first semester. Still in the process of learning java, I was like 'how would pokemon be made in java' walking back to my dorm. Then i wrote this mess.
Javamon, as it could be called, will never be worked on again, because it's a mess of java code asides from the pokemon class (which I rather liked iirc). It's not terribly designed, but needs alot of refactoring which is not something i'm very motivated to do.

Pokemon isn't mine, this was just an exercise in learning java. Just as a disclaimer.

##Functions
*Create a Pokemon via a Pokedex database and have it as a modular object that can be leveled and used. 
*Battle with two pokemon, though only basic stat changes and damage moves work. 
*There's some frame work for a terminal game in the package, for a text based pokemon. It doesn't work all that well.
*The project has javadocs.

##Usage
`java preGameBattleGen`

Of course, that's just for a fight between two level 5~ish eevee's, but it shows the implementation of the battle class. No other pokemon exist, and only the first four moves do for Eevee. 

##To-Do
This are subject to a random urge to work on the project again.
*Reorganize classes into packages.
*Create a database for the Pokedex to use, allowing for more than one pokemon. (More like, create a html parser for bulbapedia to make the database.)
*Redo Battle again, as it doesn't accommodate alot of special moves, like fly.
*Classes contain To-Do in their own Documentation.

##Changelog (Iffy at best)
*	3.0?	Not anywhere near done, but a TextADV framework so one can play a game, not just battle two Eevees.
*	2.0	Pokemon can be battled with using the battle class. 
*	1.0	Pokemon can be generated with a Pokedex, and are entirely modular.


##Class summaries (In what would be packages)
*Battle Classes
**Battle - Meat and bones. Holds a battle between two pokemon. Poorly written, cause that's alot more complicated than it sounds at first.
**Cal - Does some calculations, mostly the nasty long ones.
**DamnCal - Does damage calculations.
**Move - A variable holder for moves. Doesn't do much.
**Input - Does input for moves, user selections, and so on.
**preGameBattleGen - Runs a fight between two Eevee's, mostly so I could test it.
*Pokemon Classes
**Pokedex - A database/file reader class that has methods for accessing the 'pokedex' for making new Pokemon.
**Pokemon - Contains a pokemon, with all stats and attributes as required. Best implemented, only missing a few things.
*TextADV Classes
**Command - implemented because of the example I was working from. Contains... commands.
**Items - roughly a modular class for items found that may be decoration or usable.
**Player - Contains the user's information. Most of what would need to be 'saved' is here.
**Reader - Reads from text files to add things to the game.
**Room - Rooms in the textADV. 
**Scanner - Another implementation of Reader.
