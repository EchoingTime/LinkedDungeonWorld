package linkedDungeonWorld;
import java.util.Random;
import java.util.Scanner;
/**
 * LinkedRoom Class - A class that is responsible for adding rooms containing the exit room, monster rooms, items/event rooms, and the
 * boss room. Keeps track of the amount of rooms entered, creates the rooms with assignments of either monster, room, item, or boss, and
 * a method that allows the hero to enter a room and fight a monster or leave, generating a whole new dungeon filled with 10 rooms. 
 * If the hero dies, then the program terminates but if he lives, the game continues until this truth is false.
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 * @referenced https://www.sanfoundry.com/java-program-implement-circular-doubly-linked-list/ | Completed the project in single linked list and used a reference to convert it over to doubly circular linked list |
 */
public class LinkedRoom 
{
	private Room head;			// Declares the head of the room list
	private Room tail;			// Declares the tail of the room list
	private int size;			// Declares the amount of current rooms
	private int roomsEntered;	// Declares the tracker for amount of rooms entered
	
	/**
	 * LinkedRoom Constructor 
	 */
	public LinkedRoom ()
	{
		this.head = null;
		this.tail = null;
		roomsEntered = 0;
		size = 0;
	}
	
	/**
	 * addRoom Method - Responsible for creating the exit room
	 * @referenced See reference in class description | Converted addRoom methods from single linked list to doubly circular linked list |
	 */
	public void addRoom (String data, int choice) // Exit
	{
		Room newRoom;
		newRoom = new Room (data, choice);
		
		if (head == null)
		{
			newRoom.setNext(newRoom);		// Referenced portion
			newRoom.setPrevious(newRoom);
			head = newRoom;
			tail = head;
		}
		else
		{
			newRoom.setPrevious(tail);		// Referenced portion
			tail.setNext(newRoom);
			head.setPrevious(newRoom);
			newRoom.setNext(head);
			tail = newRoom;
		}
		size++;
	}
	
	/**
	 * addRoom Method - Contains a room that holds the Monster with a Hero, along with the room name and choice (0)
	 * @referenced See reference in class description | Converted addRoom methods from single linked list to doubly circular linked list |
	 */
	public void addRoom (Hero hero, String data, int choice, Monster monster) // With Monster
	{
		Room newRoom;		
		newRoom = new Room (hero, data, choice, monster);
		
		if (head == null)
		{
			newRoom.setNext(newRoom);		// Referenced portion
			newRoom.setPrevious(newRoom);
			head = newRoom;
			tail = head;
		}
		else
		{
			newRoom.setPrevious(tail);		// Referenced portion
			tail.setNext(newRoom);
			head.setPrevious(newRoom);
			newRoom.setNext(head);
			tail = newRoom;
		}
		size++;
	}
	
	/**
	 * addRoom Method - Contains a room that holds a DungeonObjects object, which is either an item or event, along with a Hero, room name, and choice (1)
	 * @referenced See reference in class description | Converted addRoom methods from single linked list to doubly circular linked list |
	 */
	public void addRoom (Hero hero, String data, int choice, DungeonObjects obj) // With Object
	{
		Room newRoom;
		newRoom = new Room (hero, data, choice, obj);
		
		if (head == null)
		{
			newRoom.setNext(newRoom);		// Referenced portion
			newRoom.setPrevious(newRoom);
			head = newRoom;
			tail = head;
		}
		else
		{
			newRoom.setPrevious(tail);		// Referenced portion
			tail.setNext(newRoom);
			head.setPrevious(newRoom);
			newRoom.setNext(head);
			tail = newRoom;
		}
		size++;
	}
	
	/**
	 * setSize Method - Manipulates the global variable size which is the current room size in the LinkedList
	 * @param setIt - Integer setIt being saved into the size global variable
	 */
	public void setSize (int setIt)
	{
		size = setIt;
	}
	
	/**
	 * getSize Method - Retrieves the current size of rooms in the LinkedList
	 * @return size - The current size of rooms
	 */
	public int getSize ()
	{
		return size;
	}
	
	/**
	 * getRooms Method - Retrieves the amount of rooms that the Hero has entered and passed before dying
	 * @return roomsEntered - Amount of rooms entered 
	 */
	public int getRooms ()
	{
		return roomsEntered;
	}
	
	/**
	 * enter Method - Responsible for having the hero enter the various rooms, go through a new dungeon,
	 * call Battle class to fight a monster, interact or respond to an event, or sadly dies. The parameter 
	 * choice is responsible for keeping track of which room the user has picked for their Hero to travel through 
	 * (0 as left, 1 as right), 2 as exit, and 3 as the boss room). 
	 * @param roomPick - The user's choice of room
	 * @referenced See reference in class description | Converted enter method from single linked list to doubly circular linked list |
	 */
	public void enter (int roomPick)
	{
		Room aRoom, newConnection;
		DungeonObjects obj;
		Battle battle;
		
		aRoom = head;
		newConnection = head;
		obj = new DungeonObjects();
		battle = new Battle ();
		
		if (roomPick == 0) // Going through the left door 
		{
			if (head.getChoice() == 0) // Room has a data integer value set to 0, which represents object and events inside a room!
			{
				System.out.printf("%nYou walk in and then...%n");
				obj.generateObject(head.getHero()); // 
			}
			else // choice == 1 (monster is lurking within)
			{
				battle.battle(head.getMonster(), head.getHero(), roomsEntered);
			}
			roomsEntered++;
			
			head = head.getNext();	// Referenced portion
			head.setPrevious(tail);
			tail.setNext(head);
			
			System.out.printf("%nNext two doors are: ");
			System.out.printf("%s and %s%n", aRoom.getNext().getData(), aRoom.getNext().getNext().getData());
		}
		else if (roomPick == 1) // User picked to go right
		{
			if (aRoom.getNext().getChoice() == 0) // Gets the second door and compares it to choices (0 for items and 1 for monster)
			{
				System.out.printf("%nYou found a room without a monster, but wait!%n");
				obj.generateObject(aRoom.getNext().getHero());
			}
			else // choice == 1 (monster)
			{
				battle.battle(aRoom.getNext().getMonster(), aRoom.getNext().getHero(), roomsEntered);
			}
			
			roomsEntered++;
			for (int left = 0; left < roomPick - 1; left++) // Removes the second room by setting access from first door to third
			{
				aRoom = aRoom.getNext(); // door to the left of the current door
			}
			for (int left = 0; left < roomPick; left++)
			{
				newConnection = newConnection.getNext(); // door to the right of the current door
			}
			
			aRoom.next = newConnection.getNext(); // left door equals right door, current door gets collected by the Java garbage collector
			
			System.out.printf("%nNext two doors are: ");
			System.out.printf("%s and %s%n", aRoom.getNext().getData(), aRoom.getNext().getNext().getData());
		}
		else if (roomPick == 2) // Can only be accessed via Play class and its restrictions for user input. Clears the rooms to get ready for a new createRooms call
		{
			roomsEntered++;
			System.out.printf("%nYou have entered into the light, but wait, you hear laughter... Turns out you are right back in another dungeon%n");
			clear();
		}
		else // if (roomPick == 3) Has the boss lurking within
		{
			battle.battleBoss(aRoom.getNext().getMonster(), aRoom.getNext().getHero(), roomsEntered); 

			aRoom.getNext().getMonster().displayStats(aRoom.getNext().getMonster().getName()); // Will always be the door after the exit
			aRoom.getNext().getHero().displayStatsFight();
			
			roomsEntered++;
		}
		size--;
	}
	
	/**
	 * createRooms Method - Room Construction | ROOMS WILL BE RESTRICTED TO A SIZE OF 10 -> (left or right)x4 + Boss and Exit |
	 * Uses a while loop to create rooms and random value to either put an item or event inside a room or a monster inside, which
	 * also uses a random value to assign the various monsters to rooms that accept monster parameter
	 * @param hero - Hero object, the user's hero
	 * @param devil - Monster object, a devil
	 * @param zombie - Monster object, a zombie
	 * @param gnoll - Monster object, a gnoll
	 * @param demon - Monster object, a demon
	 * @param witch - Monster object, a witch
	 * @param spider - Monster object, a spider
	 * @param object - DungeonObjects object, an item or event
	 */
	public void createRooms (Hero hero, Monster devil, Monster zombie, Monster gnoll, Monster demon, Monster witch, Monster spider, DungeonObjects object) 
	{
		Random rand;
		int choice;			// 0 for items and events or 1 for monsters
		int monster;		// 0 for a zombie, 1 for a gnoll, 2 for a demon, 3 for a witches, and 4 for a spider
		boolean z, g, d, w, s; // boolean variables to ensure that a monster is not picked more than once
		
		z = false;
		g = false;
		d = false;
		w = false;
		s = false;
		
		rand = new Random();

		while (size < 10) // Creates 10 rooms
		{
			if (getSize() == 8) // Exit room
			{
				addRoom("The Light", 2);
			}
			else if (getSize() == 9) // Steel door, the boss room with the devil
			{
				addRoom(hero, "Steel Door", 3, devil);
			}
			else // Choices of 0 or 1 for all doors 1 through 8
			{
				choice = rand.nextInt(2); // Monster (1) or Object (0)
				if (choice == 0)
				{
					addRoom(hero, "A Strange Room", choice, object);
				}
				else // if (choice == 1)
				{
					monster = rand.nextInt(5);
				
					if (monster == 0 && z != true)
					{
						z = true;
						addRoom(hero, "A Strange Room", choice, zombie);
					}
					else if (monster == 1 && g != true)
					{
						g = true;
						addRoom(hero, "A Strange Room", choice, gnoll);
					}
					else if (monster == 2 && d != true)
					{
						d = true;
						addRoom(hero, "A Strange Room", choice, demon);
					}
					else if (monster == 3 && w != true)
					{
						w = true;
						addRoom(hero, "A Strange Room", choice, witch);
					}
					else if (monster == 4 && s != true)
					{
						s = true;
						addRoom(hero, "A Strange Room", choice, spider);
					}
					else
					{
						addRoom(hero, "A Strange Room", choice, devil); // Very BAD RNG, devil should be up to two fights!
					}
				}
			}
		}
	}
	
	/**
	 * displayRooms Method - Displays the rooms remaining in the dungeon
	 */
	public void displayRooms ()
	{
		System.out.printf("%nThere are a total of %s rooms remaining! Here is an overview true soul%nTake care, the first door you see is the left door!%n", size);
		Room displaying;
		displaying = head;
		
		do
		{
			System.out.printf("| %s |", displaying.getData());
			displaying = displaying.getNext();
		}
		while (displaying != head);
		System.out.printf("%n");
	}
	
	/**
	 * inspectRoom Method - Allows the user to inspect what might be inside a room
	 * @param choice - Either 8, left door, or 9, right door
	 */
	public void inspectRoom (int choice)
	{
		Room inspect;
		inspect = head;
		
		if (choice == 8) // left door
		{
			System.out.printf("%n%s%n", inspect.toStringInspect());
		}
		else // choice == 9 | right door |
		{
			System.out.printf("%n%s%n", inspect.getNext().toStringInspect());
		}
	}
	
	/**
	 * runGame Method - Responsible for creating, processing and running the game
	 */
	public void runGame()
	{
		CreateHero hero;
		CreateMonsters zombie, gnoll, demon, witch, spider, devil;
		DungeonObjects object;
		Scanner user;
		
		int choice;
		boolean correct, untilPlayerDies;
		
		choice = -1;
	
		// Creates the hero
		hero = new CreateHero (0, 0, 0, 0, 0, 0); 
		hero.setName();
		hero.setStats();
		
		// Creates the monsters
		zombie = new CreateMonsters ("ZOMBIE", 5, 11, 1, 5, 25, hero.getLevel());	// 0
		gnoll = new CreateMonsters ("GNOLL", 50, 12, 2, 15, 25, hero.getLevel()); 	// 1
		demon = new CreateMonsters ("DEMON", 15, 15, 5, 25, 25, hero.getLevel());	// 2
		witch = new CreateMonsters ("WITCH", 10, 20, 1, 5, 25, hero.getLevel()); 	// 3
		spider = new CreateMonsters ("SPIDER", 1, 19, 1, 11, 25, hero.getLevel()); 	// 4
		devil = new CreateMonsters ("DEVIL", 100, 25, 15, 15, 25, hero.getLevel());	// 5 (BOSS)
		
		// Initializes the DungeonObjects object, object, the items and events
		object = new DungeonObjects();
		
		// Creates the scanner for user input
		user = new Scanner (System.in);
		
		untilPlayerDies = false;
		while (untilPlayerDies == false) // To keep the program running, untilPlayerDies will never become true. The program ends once hero dies, which is decided in the LinkedRoom class (system terminate)
		{
			setSize(0); // Begins with a size of 0
			createRooms(hero, devil, zombie, gnoll, demon, witch, spider, object); // Creates the rooms with the hero, monsters, and items/events
			
			while (getSize() > 2 && getSize() != 3) // While loop one, makes sure that doors 1 through 7 are only available for the user to access
			{
				if (hero.getLevel() > devil.getLevel()) // If the hero has a higher level then the devil (or any monster) then every monster levels up
				{
					devil.gainXp();
					zombie.gainXp();
					gnoll.gainXp();
					demon.gainXp();
					witch.gainXp();
					spider.gainXp();
				}
				
				correct = false;
				while (correct == false) // Makes sure that the user selects either 0 and 1
				{
					System.out.printf("%n%s, choose wisely!%nDisplay Stats (4)%nView all Rooms (5)%nGo through left door (0)%nGo through right door (1)%nInspect left door (8)%nInspect right door (9)%nEXIT (10)%nYour pick: ", hero.getName());
					choice = user.nextInt();
					
					if (choice == 0 || choice == 1)
					{
						correct = true;
					}
					else if (choice == 4)
					{
						hero.displayStats(hero.getName(), hero.getClassName());
					}
					else if (choice == 5)
					{
						displayRooms();
					}
					else if (choice == 8) // Inspect 0, left door
					{
						inspectRoom(8);
					}
					else if (choice == 9) // Inspect 1, right door
					{
						inspectRoom(9);
					}
					else if (choice == 10)
					{
						System.out.printf("%nENDING THE GAME, YOUR HERO PERISHES%n");
						System.exit(0);
					}
					else
					{
						System.out.printf("%nTry again%n");
					}
				}
				
				System.out.printf("%nEntering door...%n");
				enter(choice);
			}
			
			while (getSize() == 3) // While loop two, if the room size is 3 then only room 0 (left) and 2 (exit) are available to the user
			{
				if (hero.getLevel() > devil.getLevel()) // If the hero has a higher level then the devil (or any monster) then every monster levels up
				{
					devil.gainXp();
					zombie.gainXp();
					gnoll.gainXp();
					demon.gainXp();
					witch.gainXp();
					spider.gainXp();
				}
				
				correct = false;
				while (correct == false) // Makes sure that the user selects either 0 and 2
				{
					System.out.printf("%n%s, choose wisely!%n %nDisplay Stats (4)%nView all Rooms (5)%nGo through left door (0)%nGo through right door (2)%nInspect left door (8)%nInspect right door (9)%nEXIT (10)%nYour pick: ", hero.getName());
					choice = user.nextInt();
					
					if (choice == 0 || choice == 2)
					{
						correct = true;
					}
					else if (choice == 4)
					{
						hero.displayStats(hero.getName(), hero.getClassName());
					}
					else if (choice == 5)
					{
						displayRooms();
					}
					else if (choice == 8)
					{
						inspectRoom(8);
					}
					else if (choice == 9)
					{
						inspectRoom(9);
					}
					else if (choice == 10)
					{
						System.out.printf("%nENDING THE GAME, YOUR HERO PERISHES%n");
						System.exit(0);
					}
					else
					{
						System.out.printf("%nTry again%n");
					}
				}
				
				System.out.printf("%nEntering door...%n");
				enter(choice);
			}
			
			correct = false;
			while (getSize() >= 0) // Final while loop, if the size equals 2 then there is only room 2 (exit) and 3 (boss) left
			{
				if (hero.getLevel() > devil.getLevel()) // If the hero has a higher level then the devil (or any monster) then every monster levels up
				{
					devil.gainXp();
					zombie.gainXp();
					gnoll.gainXp();
					demon.gainXp();
					witch.gainXp();
					spider.gainXp();
				}
				System.out.printf("%n%s, my dear true soul, you have made it to the end! Will you like to leave or fight the boss?%n2 (leave) or 3 (boss). Oh, I almost forgot! You can also pick 4 (view your stats), 5 (display map), Inspect left door (8), or Inspect right door (9)%n", hero.getName());
				while (correct == false) // Makes sure that the user selects either 2 (exit) or 3 (boss)
				{
					System.out.printf("%nEscape out of this dungeon! (2)%nTest your strength against the mysterious monster overlord (3)%nDisplay Stats (4)%n View all Rooms (5)%nInspect left door (8)%nInspect right door (9)%nEXIT (10)%nYour pick: ", hero.getName());
					choice = user.nextInt();
					
					if (choice == 2)
					{
						correct = true;
						System.out.printf("%nLeaving the dungeon...%n");
						setSize(0);
					}
					else if (choice == 3)
					{
						correct = true;
						System.out.printf("%nEntering the steel doors%n");
					}
					else if (choice == 4)
					{
						hero.displayStats(hero.getName(), hero.getClassName());
					}
					else if (choice == 5)
					{
						displayRooms();
					}
					else if (choice == 8)
					{
						inspectRoom(8);
					}
					else if (choice == 9)
					{
						inspectRoom(9);
					}
					else if (choice == 10)
					{
						System.out.printf("%nENDING THE GAME, YOUR HERO PERISHES%n");
						System.exit(0);
					}
					else
					{
						System.out.printf("%nTry again%n");
					}
				}
				enter(choice);
			}
		}
	}
	
	/**
	 * clear Method - Sets head to null, removing reference to all remaining rooms, and size to 0, because there are now 0 rooms
	 */
	public void clear ()
	{
		head = null;
		size = 0;
	}
}
