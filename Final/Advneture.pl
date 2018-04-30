/* Text based adventure game. For Artificial Intelligence class.
   Even though it is a texted based adventure game I will implement in AI type behavior for the opponents.
   By: Austin Zaoni

   To start game input the command start. (with the period)
*/

/* Defining the structure of the different "variables" I will be using later on  */
:- dynamic at/2, i_am_at/1, alive/1.
:- retractall(at(_,_)), retractall(i_am_at(_)), retractall(alive(_)).


/* starting location */

i_am_at(field).


/*  Describing the different connections between locations   */

path(field, n, village).
path(field, w, cave).
path(field, s, castle).

path(village, n, tavern).
path(village, w, store).
path(village, s, field).
path(village, e, village_center).
path(tavern, s, village).
path(store, e, village).
path(village_center, w, village).

path(cave, e, field).

path(castle, n, field).
path(castle, e, kitchen).
path(kitchen, w, castle).
path(castle, s, armory) :- at(key, in_hand).
path(castle, s, armory) :-
  write('The door appears to be locked.'), nl,
  fail.
path(armory, n, castle).
path(castle, w, throne_room).
path(castle, u, upstairs).
path(castle, d, basement).

path(upstairs, d, castle).
path(upstairs, s, library).
path(library, n, upstairs).
path(upstairs, n, bedroom).

path(bedroom, n, balcony).
path(balcony, s, bedroom).
path(bedroom, e, dresser).
path(dresser, w, bedroom).
path(bedroom, w, bed).
path(bed, e, bedroom).
path(bedroom, s, upstairs).

path(basement, u, castle).
path(basement, s, cage) :- at(bronze_key, in_hand).
path(basement, s, cage) :-
  write('The cage is locked.'), nl,
  fail.
path(cage, n, basement).
path(basement, w, table).
path(table, e, basement).

/* General instrucitons of game and the beginning of the game */

instructions :-
  nl, write('Please enter command to decide what you want to do.'), nl,
  write('Available commands are(make sure to put in periods):'), nl,
  write('north. south. west. east. up. down.  -- to go in that direction.'), nl,
  write('take(item).                          -- to take available item.'), nl,
  write('drop(item).                          -- to drop available items'), nl,
  write('observe.                             -- look around at settings.'), nl,
  write('talk.                                -- talk to people around you.'), nl,
  write('instructions.                        -- show this message again.'), nl,
  write('halt.                                -- to exit game.'), nl, nl.

start :-
  instructions,
  look.

/* go function is called it will see if the call matches any valid directions to move and change your location if it can */

go(Direction) :-
  i_am_at(Here),
  path(Here, Direction, There),
  retract(i_am_at(Here)),
  assert(i_am_at(There)),
  look, !.

go(_) :-
  write('You cannot go that way'), nl.

/* When choosing a direction go will be called to go that direction */

north :- go(n).
south :- go(s).
west :- go(w).
east :- go(e).
up :- go(u).
down :- go(d).

/* looking around the current location to describe surroundings */

look :-
  i_am_at(Location),
  describe(Location), nl.

/* Descriptions of all locations */

describe(field) :-
  write('You are in the middle of a field of wild flowers. To the north'), nl,
  write('you see in the distance smoke rising from a village. To the west'), nl,
  write('you see a towering mountain that appears to have a cavernous entrance.'), nl,
  write('To the south you see a castle with towering spires made entirely'), nl,
  write('of black stone.'), nl.

describe(village) :-
  write('As you look around the entrance of the village to the north'), nl,
  write('you spot a local tavern. To the west you see a store selling'), nl,
  write('everything from bread to swords. To the east you see what appears'), nl,
  write('to be the center of the village many people wandering around.'), nl,
  write('You look to the south and you see the familiar field of wild flowers.'), nl.

describe(tavern) :-
  write('As you enter the tavern you see the establishment quite lively'), nl,
  write('with the bustle of the people trying to get thier lunch. You'), nl,
  write('remember that you do not actually have any coin so you realize'), nl,
  write('your only option is to go back south to go back into the village.'), nl.

describe(store) :-
  write('As you enter the store you see some of the finest weaponry that'), nl,
  write('can be purchased in this fine village. You notice local baker unloading'), nl,
  write('freshly baked bread and pastries. As you continue to admire all the'), nl,
  write('wonderous things available to purchase you remember you do not have any'), nl,
  write('coin to purchase anything. Your only option is to go back east to the'), nl,
  write('village.'), nl.

describe(village_center) :-
  write('As you enter the village center you see local farmers set up with stalls'), nl,
  write('selling their harvest to the other locals. You see an old man next to'), nl,
  write('a fountain staring at you. You could also go back to the entrance of '), nl,
  write('the village to the west.'), nl.

describe(cave) :-
  write('As you get closer to the entrance of the cave you see that there has'), nl,
  write('been a cave in of rocks a little bit inside the cave making it impossible'), nl,
  write('to enter. You glance back to the east to the field of wild flowers you just'), nl,
  write('came from.'), nl.

describe(castle) :-
  write('You enter the castle and you first see a grand staircase heading back up'), nl,
  write('and down. To the east you smell food from what you would guess to be the kitchen'), nl,
  write('To the south you a door with a sword and shield on it, you guess that to'), nl,
  write('be the armory. To the west is a beautiful and grand set of doors which'), nl,
  write('looks to be the throne room. You then feel a cool breeze reminding you'), nl,
  write('of the field of wild flower to the north.'), nl.

describe(armory) :-
  write('You open the lock into the armory and see great long sword hanging on the'), nl,
  write('wall. You also can return to the castle entrance to the north.'), nl.

describe(kitchen) :-
  write('You enter the kitchen and see beautiful and delicate pastries of varying'), nl,
  write('sizes, shapes, and smells. You can also return to the castle entrance to'), nl,
  write('the west.'), nl.

describe(throne_room) :-
  write('As you push open and walk through the huge doors you see a man all in gold'), nl,
  write('cloth lounging on the throne. You continue to walk through the doors and as you'), nl,
  write('do you hear the great doors slam shut behind you.'), nl.

describe(upstairs) :-
  write('At the top of the stiars you see to the north what appears to be an entrance to'), nl,
  write('a bedroom. To the south you spot a library brimming with what must be priceless'), nl,
  write('knowledge. You also see the stairs leading back down to the castle entrance.'), nl.

describe(library) :-
  write('As you enter the library you the dust that must have been accumlating from'), nl,
  write('the disuse of these books as you can tell from the layer over all books.'), nl,
  write('As you continue to look around you continue to see dust on all books, except'), nl,
  write('one. You can also leave through the north back to the upstairs.'), nl.

describe(bedroom) :-
  write('You enter the bedroom and looking straight north you see a balcony looking out'), nl,
  write('onto the field of wild flowers. To the east side of the room you spot a '), nl,
  write('dresser. On the west side you see a large bed. To the south you see the main'), nl,
  write('upstairs room.'), nl.

describe(balcony) :-
  write('As you walk onto the black, stone balcony you are taken by the beauty and expanse'), nl,
  write('of the field of wild flowers that you started this grand journey across. In the'), nl,
  write('distance you can see smoke rising from the village. A cool breeze begins presenting'), nl,
  write('you with the tempting floral aroma of the expansive field in front of you. As you'), nl,
  write('come back to reality you remember the bedroom to the south of you.'), nl.

describe(bed) :-
  write('You walk up the the monstrous bed with fine golden silk covering it. You are now'), nl,
  write('begining to feel the strain of your travels and how much this adventure has been'), nl,
  write('wearing you down. The bed looks quite welcoming. You could also return to the center'), nl,
  write('of the bedroom to the east.'), nl.

describe(dresser) :-
  write('As you walk up to the perfectly crafted mahogany dresser you see a large steel key'), nl,
  write('laying on top. You can also return to the center of the bedroom.'), nl.

describe(basement) :-
  write('As you descend down into the basement to begin to here hoarse voices quietly calling'), nl,
  write('out for help. At reaching the bottom of the stairs you see a cage to the south of the'), nl,
  write('room with a man inside. On the west side of the room you see a large table with many'), nl,
  write('different instruments on it. You can also back up the stairs.'), nl.

describe(table) :-
  write('On the table you see different crude instruments covered in slightly dried blood.'), nl,
  write('You also see a bronze key lying on the table. You can also return back to the center'), nl,
  write('of the basement.'), nl.

descibe(cage) :-
  write('As you approach the cage with the brozne key the man inside begins to cry knowing that'), nl,
  write('he has been saved. You unlock the cage for the man. He stares at you expectantly. You'), nl,
  write('can also return to the center of the basement.'), nl.
