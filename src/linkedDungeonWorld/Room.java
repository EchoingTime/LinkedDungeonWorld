package linkedDungeonWorld;
/**
 * Room Class - Responsible for creating three types of Rooms, a exit room accepting two parameters, 
 * string data and integer choice, a object room, which accepts four parameters, a Hero object, string data, integer
 * choice, and DungeonObjects object, and a monster room, accepting four parameters, a Hero object, string data,
 * integer choice, and a Monster object. Fills the rooms with either object, with a choice of 0, or a monster, with a choice 
 * of 1 or 3, and a exit, with a choice of 2.
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 * @referenced https://www.sanfoundry.com/java-program-implement-circular-doubly-linked-list/ | Completed the project in single linked list and used a reference to convert it over to doubly circular linked list |
 */
public class Room 
{
	private String data;		// Declares the name of the room
	
	private Hero hero;			// Declares the Hero object
	private Monster monster;	// Declares the Monster object
	private int choice;			// Declares the integer choice, with integers 0, 1, 2, or 3
	
	Room next;				// Declares the Room object, next, responsible for getting the next room
	private Room previous;	// Declares the room object, previous, responsible for getting a previous room 
	
	/**
	 * Room Constructor - Default
	 */
	public Room ()
	{
		next = null;
		previous = null;
		data = null;
	}
	
	/**
	 * Room Constructor - Exit room accepting two parameters, String data and integer choice
	 */
	public Room (String data, int choice)
	{
		this.data = data;
		this.choice = choice;
	}
	
	/**
	 * Room Constructor - Object Holder room accepting four parameters, Hero object, String data, integer choice, and DungeonObjects object
	 */
	public Room (Hero hero, String data, int choice, DungeonObjects obj)
	{
		this.hero = hero;
		this.data = data;
		this.choice = choice;
	}
	
	/**
	 * Room Constructor - Monster Holder room accepting four parameters, Hero object, String data, integer choice, and Monster object
	 */
	public Room (Hero hero, String data, int choice, Monster monster)
	{
		this.hero = hero;
		this.monster = monster;
		this.data = data;
		this.choice = choice;
	}
	
	/**
	 * setNext Method - Links a room to another
	 * @referenced See reference in class description
	 * @param next - A Room object 
	 */
	public void setNext (Room next)
	{
		this.next = next;
	}
	
	/**
	 * setPrevious Method - Assigns a previous room to null
	 * @referenced See reference in class description
	 * @param previous - A Room object 
	 */
	public void setPrevious (Room previous)
	{
		this.previous = previous;
	}
	
	/**
	 * getNext Method - Functions as an accessory for the next Room
	 * @return next - The next room
	 */
	public Room getNext ()
	{
		return next;
	}
	
	/**
	 * getPrevious Method - Functions as an accessory for the previous Room
	 * @return previous - The previous room
	 */
	public Room getPrevious ()
	{
		return previous;
	}
	
	/**
	 * getData Method - Retrieves the Room's name which possibilities are Steel Door, Exit, or A Strange Door
	 * @return data - A String containing a room name
	 */
	public String getData ()
	{
		return data;
	}	
	
	/**
	 * toStringInspect Method - Used to inspect a room before going in, choice type 0, 1, 2, or 3
	 * @return String - Contains String data plus integer choice
	 */
	public String toStringInspect ()
	{
		if (choice == 0)
		{
			return data + ", contains an object of some sort!";
		}
		else if (choice == 1)
		{
			return data + ", contains a monster of some sort!";
		}
		else if (choice == 2)
		{
			return data + ", the path out of this dark place!";
		}
		else
		{
			return data + ", contains the boss that you should slay!";
		}
	}	
	
	/**
	 * getChoice Method - Retrieves the choice of the door, 0 represents an item object, 1 represents a monster object, 2
	 * the exit, or 3 containing the boss monster object
	 * @return choice - Returns an integer 0, 1, 2, or 3
	 */
	public int getChoice ()
	{
		return choice;
	}
	
	/**
	 * getMonster Method - Used to retrieve the Monster object that will be used in the LinkedRoom class
	 * @return monster - Monster object
	 */
	public Monster getMonster ()
	{
		return monster;
	}
	
	/**
	 * getHero Method - Used to retrieve the Hero object that will be used in the LinkedRoom class
	 * @return hero - Hero object
	 */
	public Hero getHero ()
	{
		return hero;
	}
}
