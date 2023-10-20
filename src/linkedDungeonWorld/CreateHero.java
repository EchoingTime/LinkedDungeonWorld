package linkedDungeonWorld;
import java.util.Scanner;
/**
 * CreateHero Class - The subclass to the Hero superclass and allows a user to use a Scanner
 * and input a desired Hero name and the Hero's class (out of three classes unless 
 * the user gets the punishment class for not following directions). The subclass has methods
 * to set the Hero's statistics, once a class is picked, and methods to set name, class name, and 
 * retrieve the name and class name.
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class CreateHero extends Hero
{
	private Scanner userInput;			// Declaring a Scanner class object
	private String name, className;		// Declaring the Hero's name via user and className that the user will pick
	
	/**
	 * CreateHero Constructor - Accepts parameters so the superclass' global variables can be accessed
	 * Has temporary name and className for narrative and initializes the Scanner object
	 */
	public CreateHero (int life, int attack, int defense, int speed, int gold, int experience) 
	{
		super (life, attack, defense, speed, gold, experience);
		name = "Traveler";
		className = "Lost Soul";
		userInput = new Scanner (System.in);
	}
	
	/**
	 * setName Method - Allows the user to create the Hero's name via user input
	 */
	public void setName ()
	{		
		System.out.printf("Hello %s, what is your name? ", name);
		name = userInput.nextLine();
		setNameMain(name); // To be saved in the superclass
	}
	
	/**
	 * setClassName Method - Sets the className of the Hero
	 * @param className - Hero's class
	 */
	public void setClassName (String className)
	{
		this.className = className;
	}
	
	/**
	 * setStats Method - Responsible for assigning the Hero its statistics via a chosen class
	 */
	public void setStats ()
	{
		String selection;
		System.out.printf("Greetings %s, I am your guardian. You, my friend, are a %s.%nType your Hero's class EXACTLY as you see it: "
				+ "Fighter, Knight, or Ranger%n", name, className); 
		selection = userInput.nextLine();
		
		if (selection.equals("Fighter"))
		{
			setClassName("Fighter");
			fighter();
		}
		else if (selection.equals("Knight"))
		{
			setClassName("Knight");
			knight();
		}
		else if (selection.equals("Ranger"))
		{
			setClassName("Ranger");
			ranger();
		}
		else // Referenced from -Elden-Ring-
		{
			setClassName("Wretch");
			System.out.printf("%nWhat are you doing! You were supposed to enter the class as you saw it... This is not good, you are now getting the class wretch, I am so sorry!%n");
			wretch();
		}
	}
	
	/**
	 * getName Method - Returns the name of the Hero
	 * @return name - Hero's name
	 */
	public String getName ()
	{
		return name;
	}
	
	/**
	 * getClassName Method - Returns the Hero's class
	 * @return className - Hero's class
	 */
	public String getClassName ()
	{
		return className;
	}
	
	/**
	 * fighter Method - Creates the Hero's class | Fighter
	 */
	public void fighter ()
	{
		setLife(15);	// 10 / 20 ABOVE AVERAGE
		setAttack(20);	// 20 / 20 GREAT
		setDefense(10);	// 10 / 20 AVERAGE
		setSpeed(15);	// 15 / 20 ABOVE AVERAGE
		
		setGold(25); 
		setExperience(0);
	}
	
	/**
	 * knight Method - Creates the Hero's class | Knight
	 */
	public void knight ()
	{
		setLife(20);	// 15 / 20 ABOVE AVERAGE
		setAttack(10);	// 10 / 20 AVERAGE
		setDefense(20);	// 20 / 20 GREAT
		setSpeed(10);	// 10 / 20 AVERAGE
		
		setGold(75);
		setExperience(0);
	}
	
	/**
	 * ranger Method - Creates the Hero's class | Ranger
	 */
	public void ranger ()
	{
		setLife(12);	// 10 / 20 SLIGHTLY ABOVE AVERAGE
		setAttack(15);	// 15 / 20 ABOVE AVERAGE
		setDefense(10); // 10 / 20 AVERAGE
		setSpeed(20);	// 20 / 20 GREAT
		
		setGold(50); 
		setExperience(0);
	}
	
	/**
	 * wretch Method - Creates the Hero's class | Wretch 
	 * A consequence for not following the directions
	 */
	public void wretch ()
	{
		setLife(10); 	// 10 / 20 AVERAGE
		setAttack(10); 	// 10 / 20 AVERAGE
		setDefense(8); // 8 / 20 SLIGHTLY BELOW AVERAGE
		setSpeed(5); 	// 5 / 20 BELOW AVERAGE
		
		setGold(-25); 	// Your fault!
		setExperience(-50);
	}
}
