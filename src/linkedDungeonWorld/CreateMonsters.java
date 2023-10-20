package linkedDungeonWorld;
/**
 * CreateMonster Class - The subclass to the Monster superclass. The subclass has a
 * constructor to set the Monster's statistics and a single method to set the name.
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class CreateMonsters extends Monster
{
	private String monster; // Declaring the global variable of the monster's name
	
	/**
	 * CreateMonsters Constructor - Accepts parameters so the superclass' global variables can be accessed
	 */
	public CreateMonsters (String monster, int life, int attack, int defense, int speed, int gold, int level) 
	{
		super (life, attack, defense, speed, gold, level);
		this.monster = monster;
		setName(this.monster);
	}
	
	/**
	 * getName Method - Returns the name of the Monster
	 * @return name - Monster's name
	 */
	public String getName ()
	{
		return monster;
	}
}
