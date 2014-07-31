Vector Defense
====

Solo Entry.
2D Side Scrolling Shmup for WSI TAFE Mt Druitt, 2014 Competition on 25/06/2014


How to Play
==================

'ESC'		Key to Quit Game.
'W,A,S,D' 	Keys to Move.
		OR
'U,L,D,R'	Arrow Keys to Move
'Space' 	Hold Key to Fire effectively.

Avoid/Destroy enemies - Red/Blue Squares.
Collect Power-ups - Yellow/Cyan Squares.
You have a little Cyan shield, it wont last long.

Survive...


Changes
==================
v0.1.5-Alpha build.9;
 - Changed shooting to autofire now.
 - Changed the way the miniBoss works, and added the blue version.
 - Increased the shield strengh of the red miniBoss.
 - Slowed down the weapon fire rate buff, and increased the amount of powerups required to level up cannon.
 - Added a Speed Burst ability, activated via the [SPACE] key, has cooldown (Short currently).
 - Updates to the Instructions Loop.
 
v0.1.5-Alpha build.8;
 - Fixed bug where player dies but shield still draws.
 - Fixed some enemies not spawning at the correct size.
 - Cleaned up the way the player is drawn to the screen.
 - Added ability to level up cannon and increase shots.
 - Added big red miniBoss.
 - tweeked enemies speeds, and powerup chance.
 - Started work on the background but got side tracked.
 
v0.1.4-Alpha build.7;
 - Fixed shield not powering up again after being shot down, even after picking up power-up!
 - Fixed player taking damage from picking up power-ups.
 - Increased chance of a shield power-up spawning.
 - Refactored a bunch of classes to allow easier reading, editing, expanding.

v0.1.3-Alpha build.6;
 - Fixed static pointers to things that should be static, like Screen Height/Width.
 - Added several checks to remove late game lagg, like bullets/enemies off the screen, etc.
 - Marginally increased the size of the Power-up, Little easier to get now.
 - Elongated the Blue enemies, make them stand out now.
 - Added a new Power-up! Regenerate dem Shields! - very rare to come across, to rare even.
 
v0.1.3-Alpha build.5;
 - Fixed Versioning.
 - Added the ability to use W,A,S,D or the UP, DOWN, LEFT, RIGHT Arrows to move the ship.

v0.1.3-Alpha build.4;
 - Bullets from both the player and enemies, ignore/go through power-ups (Sort of a bug since power-ups should be ignored).
 - Bullets from the enemies stop when hitting another enemy, but does not damage the receiving enemy.
 -- Minor Optimization for late-game, when frame rate gets munched and also probably to hard for player, lol.
 - Added a few things to the TODO List.


TODO LIST
==================
 - Indicator for Player Shield.
 - More veried enemies!
 - State Machine for player Weapon/s
 - Game States and stash the instruction loop into that, maybe add game over/restart, etc.
 - Background!
 - Add indicator for speed burst cooldown/ready/etc?
 
 
 BUG LIST
 ==================
 - Intermitent Wierd bug when moving right and firing, as bullets near the edge of the screen they change formation?
 - 
 - 
 - 
 - 
 - 
 
 
 
 