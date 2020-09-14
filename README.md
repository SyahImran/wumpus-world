# wumpus-world

Graphical version of the game Wumpus World.

Game consists of a 10x10 map, with an additional area below the map. The area below the map is used to show the player’s inventory (arrow & gold) and messages.

How the game works:
The player must venture into a dark cave to find treasure. He takes with him a bow and a single arrow.
In the cave there are pits that will kill the player if he/she were to fall into them. Although the player will not be able to see the pits, due to the cave being dark, he will feel a breeze when he is near a pit.
In addition to the pits, there is a wumpus in the cave. A wumpus is a smelly monster that will eat the player when he/she stumbles into it. The player will know he/she is near a wumpus due to its stench.
The player may only fire his arrow one time. If the arrow hits the wumpus it will die and the player will hear a scream.
The player will only be able to see squares he/she is on or has been to in the past. To win the game the player must navigate the cave, retrieve the treasure and then climb up the ladder.

Controls:
- ‘w’: Moves the player up one square
- ‘s’: Moves the player down one square
- ‘a’: Moves the player left one square
- ‘d’: Moves the player right one square
- ‘i’: Shoots upward (only works if you have an arrow)
- ‘k’: Shoots downward (only works if you have an arrow)
- ‘j’: Shoots left (only works if you have an arrow)
- ‘l’: Shoots right (only works if you have an arrow)
- ‘c’: Climbs the ladder (only works if you have the gold)
- ‘p’: Picks up the gold (only when on the square with the gold)
- ‘n’: Creates a new game (only works after you win or die)
- '*': Toggles on/off cheat mode (When on, there will be no fog of war)

Messages: 
- You feel a breeze (when near a pit)
- You smell a stench (when near the Wumpus, near dead Wumpus or on a dead Wumpus)
- You see a glimmer (when on the gold)
- You bump into a ladder (when on the ladder)
- You fell down a pit to your death (when on a pit)
- You are eaten by the Wumpus (When on the Wumpus)
- You hear a scream (On the turn you kill the Wumpus)
