package linkedDungeonWorld;
/**
 * Hero Class - The superclass of CreateHero, and is responsible for keeping track of the Hero's
 * current life, attack damage, defense, speed, gold, levels, and experience. Has methods
 * to increase levels and experience, attack monsters, and display Hero's statistics. Also has methods
 * to retrieve the global variables except for neededXp and final integers.
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class Hero implements LevelSystem
{
	private int life, attack, defense, speed, gold, level, experience; // Declaring global variables for a Hero
	private int neededXp; // Declaring a global variable to keep track of the experience requirements for every level

	private String name; // Hero's name
	
	final int GAINED_EXPERIENCE = 25; 	// User gains 25 experience after every monster kill
	final int LEVEL_UP = 2; 			// The set amount of points for the increase in life, attack, defense, and speed. 
	
	/**
	 * Hero Constructor - Initializes global variables and sets the defaults of level and required experience
	 */
	public Hero (int life, int attack, int defense, int speed, int gold, int experience)
	{
		this.life = life;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.gold = gold;
		this.experience = experience;
		
		setLevel(1); // All heros MUST begin at level one in this game
		neededXp = 50;
	}
	
	/**
	 * setLife Method - Manipulates the current health of the Hero
	 * @param life - Hero's health
	 */
	public void setLife (int life) 
	{
		this.life = life;
	}
	
	/**
	 * setAttack Method - Manipulates the current attack damage of the Hero
	 * @param attack - Hero's attack damage 
	 */
	public void setAttack (int attack) 
	{
		this.attack = attack;
	}
	
	/**
	 * setDefense Method - Manipulates the current defense of the Hero
	 * @param defense - Hero's defense
	 */
	public void setDefense (int defense) 
	{
		this.defense = defense;
	}
	
	/**
	 * setSpeed Method - Manipulates the current speed of the Hero
	 * @param speed - Hero's speed
	 */
	public void setSpeed (int speed) 
	{
		this.speed = speed;
	}
	
	/**
	 * setGold Method - Manipulates the current gold that the Hero has
	 * @param gold - Hero's gold 
	 */
	public void setGold (int gold) 
	{
		this.gold = gold;
	}
	
	/**
	 * setLevel Method - Manipulates the current level of the Hero
	 * @param level - Hero's level
	 */
	public void setLevel (int level) 
	{
		this.level = level;
	}
	
	/**
	 * setExperience Method - Manipulates the current experience of the Hero
	 * @param experience - Hero's experience
	 */
	public void setExperience (int experience) 
	{
		this.experience = experience;
	}
	
	/**
	 * setNameMain Method - Saves the name declared by the user via subclass
	 * @param name - Hero's name
	 */
	public void setNameMain (String name)
	{
		this.name = name;
	}

	/**
	 * getLife Method - Retrieves Hero's remaining health
	 * @return life - The life of the Hero
	 */
	public int getLife() 
	{
		return life;
	}

	/**
	 * getAttack Method - Retrieves the attack damage of the Hero
	 * @return attack - Hero's current attack damage
	 */
	public int getAttack() 
	{
		return attack;
	}

	/**
	 * getDefense Method - Retrieves the defense of the Hero
	 * @return defense - Hero's current damage
	 */
	public int getDefense() 
	{
		return defense;
	}

	/**
	 * getSpeed Method - Retrieves the speed of the Hero
	 * @return speed - Hero's current speed
	 */
	public int getSpeed() 
	{
		return speed;
	}

	/**
	 * getGold Method - Retrieves the gold that the Hero has
	 * @return gold - Hero's current amount of gold
	 */
	public int getGold() 
	{
		return gold;
	}

	/**
	 * getLevel Method - Retrieves the Hero's level
	 * @return level - Hero's current level
	 */
	public int getLevel() 
	{
		return level;
	}

	/**
	 * getExperience Method - Retrieves the Hero's experience
	 * @return experience - Hero's current experience
	 */
	public int getExperience() 
	{
		return experience;
	}
	
	/**
	 * getName Method - Retrieves the Hero's name
	 * @return name - Hero's name
	 */
	public String getName ()
	{
		return name;
	}
	
	@Override
	/**
	 * levelUp Method - Overrides the abstract method in the interface LevelSystem
	 * and is responsible for leveling up the Hero if gainXp method is called
	 */
	public void levelUp ()
	{
		System.out.printf("%nYOU HAVE LEVELED UP%n");
		level++; 				// LEVEL UP
		life += LEVEL_UP; 		// Life increased!
		attack += LEVEL_UP; 	// Attack increased!
		defense += LEVEL_UP; 	// Defense increased!
		speed += LEVEL_UP; 		// Speed increased!
		neededXp = 50 * level;	// Needed Experience has gone up
		experience = 0; 		// Experience reset
	}
	
	@Override
	/**
	 * gainXp Method - Overrides the abstract method in the interface LevelSystem
	 * and is responsible for adding experience and leveling up the Hero if experience 
	 * equals 50 times level of the Hero
	 */
	public void gainXp ()
	{		
		experience = experience + GAINED_EXPERIENCE; 
		
		if (experience >= neededXp) // Experience has equaled or gone over needed experience
		{
			levelUp (); // LEVEL UP - levelUp method is called
		}
	}
	
	/**
	 * attack Method - Hero has attacked a monster. If monster's defense is greater than Hero's attack damage,
	 * then no damage has been done
	 * @param monster - Object from the class Monster
	 */
	public void attack (Monster monster)
	{
		int damage;
					
		damage = attack - monster.getDefense();
		if (damage < 0)
		{
			damage = 1;
		}
		
		monster.setLife(monster.getLife() - damage);
	}
	
	/**
	 * displayStats Method - Displays the hero's statistics along with it's name and class
	 * (Fighter, Knight, Ranger, or Wretch) from the subclass
	 * @param name - Hero's name
	 * @param className - The Hero's class title
	 */
	public void displayStats (String name, String className)
	{
		System.out.printf("%n%s (%s | LVL %s) %nHero Statistics --> "
				+ "| Health: %s | Attack: %s | Defense: %s | Speed: %s | Gold: %s | Experience: %s out of %s |%n" , name, className, level, life, attack, defense, speed, gold, experience, neededXp);
	}
	
	/**
	 * displayStats Method Overload - Displays the hero's statistics without accepting it's name and class as parameters
	 */
	public void displayStats ()
	{
		System.out.printf("%n%s's Statistics --> "
				+ "| Level: %s | Health: %s | Attack: %s | Defense: %s | Speed: %s | Gold: %s | Experience: %s out of %s |%n" , name, level, life, attack, defense, speed, gold, experience, neededXp);
	}
	
	/**
	 * displayStats Method - Displays the hero's statistics without accepting it's name and class as parameters but formatted for fights
	 */
	public void displayStatsFight ()
	{
		System.out.printf("%nAGAINST %s (LVL %s) %n"
				+ "| Health: %s | Attack: %s | Defense: %s | Speed: %s | Gold: %s |%n" , name, level, life, attack, defense, speed, gold);
	}
}
