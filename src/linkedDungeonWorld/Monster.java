package linkedDungeonWorld;
/**
 * Monster Class - The superclass of CreateMonster, and is responsible for keeping track of the Monster's
 * current life, attack damage, defense, speed, gold, and levels. Has methods to increase levels (if hero has 
 * leveled up), attack the Hero, and display Monster's statistics. Also has methods to retrieve the global 
 * variables except for final integers.
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class Monster implements LevelSystem
{
	private int life, attack, defense, speed, gold, level;
	private String name;
	
	final int LEVEL_UP = 5; 					// The set amount of points for the statistics life, attack, defense, and speed. 
	final int GAINED_EXPERIENCE = 50 * level; 	// Will always gain the required experience to level up due to leveling up with hero
	
	public Monster (int life, int attack, int defense, int speed, int gold, int level)
	{
		this.life = life;
		this.attack = attack;
		this.defense = defense;
		this.speed = speed;
		this.gold = gold;
		this.level = level;
		
		setLevel(1); // Will level up as the hero does
	}
	
	/**
	 * setLife Method - Manipulates the current health of the Monster
	 * @param life - Monster's health
	 */
	public void setLife (int life) 
	{
		this.life = life;
	}
	
	/**
	 * setAttack Method - Manipulates attack damage of the Monster
	 * @param attack - Monster's attack damage 
	 */
	public void setAttack (int attack) 
	{
		this.attack = attack;
	}
	
	/**
	 * setDefense Method - Manipulates the defense of the Monster
	 * @param defense - Monster's defense
	 */
	public void setDefense (int defense) 
	{
		this.defense = defense;
	}
	
	/**
	 * setSpeed Method - Manipulates the Monster's speed
	 * @param speed - Monster's speed
	 */
	public void setSpeed (int speed) 
	{
		this.speed = speed;
	}
	
	/**
	 * setGold Method - Manipulates the Monster's gold
	 * @param gold - Monster's gold 
	 */
	public void setGold (int gold) 
	{
		this.gold = gold;
	}
	
	/**
	 * setLevel Method - Manipulates the Monster's level
	 * @param level - Monster's level
	 */
	public void setLevel (int level) 
	{
		this.level = level;
	}
	
	/**
	 * setName Method - Manipulates the Monster's name and the 
	 * method is called in the CreateMonsters class
	 * @param name - Monster's name
	 */
	public void setName (String name)
	{
		this.name = name;
	}
	
	/**
	 * getLife Method - Retrieves Monster's remaining health
	 * @return life - Monster's current health
	 */
	public int getLife () 
	{
		return life;
	}

	/**
	 * getAttack Method - Retrieves the attack damage of the Monster
	 * @return attack - Monster's current attack damage
	 */
	public int getAttack () 
	{
		return attack;
	}

	/**
	 * getDefense Method - Retrieves the defense of the Monster
	 * @return defense - Monster's current damage
	 */
	public int getDefense () 
	{
		return defense;
	}

	/**
	 * getSpeed Method - Retrieves the speed of the Monster
	 * @return speed - Monster's current speed
	 */
	public int getSpeed () 
	{
		return speed;
	}

	/**
	 * getGold Method - Retrieves the Monster's gold
	 * @return gold - Monster's current gold
	 */
	public int getGold () 
	{
		return gold;
	}

	/**
	 * getLevel Method - Retrieves the Monster's level
	 * @return level - Monster's current level
	 */
	public int getLevel () 
	{
		return level;
	}
	
	/**
	 * getName Method - Retrieves the Monster's name
	 * @return name - Monster's name
	 */
	public String getName ()
	{
		return name;
	}
	
	@Override
	/**
	 * levelUp Method - Overrides the abstract method in the interface LevelSystem
	 * and is responsible for leveling up the monster if gainXp method has been called
	 */
	public void levelUp ()
	{
		level++;				// LEVEL UP
		life += LEVEL_UP; 		// Life increased!
		attack += LEVEL_UP;		// Attack increased!
		defense += LEVEL_UP;	// Defense increased!
		speed += LEVEL_UP;		// Speed increased!
	}
	
	@Override
	/**
	 * gainXp Method - Overrides the abstract method in the interface LevelSystem
	 * and is responsible for leveling up the Monster. Method is
	 * called when Hero has leveled up (no need for an experience variable)
	 */
	public void gainXp ()
	{
		levelUp();
	}
	
	/**
	 * attack Method - Monster has attacked the Hero. If hero's defense is greater than Monster's attack damage,
	 * then no damage has been done
	 * @param hero - Object from the class Hero
	 */
	public void attack (Hero hero)
	{
		int damage;
					
		damage = attack - hero.getDefense();
		if (damage < 0)
		{
			damage = 1;
		}
		
		hero.setLife(hero.getLife() - damage);
	}
	
	/**
	 * displayStats Method - Displays the monster's statistics along with it's name from the subclass
	 */
	public void displayStats (String name)
	{
		System.out.printf("%n%s (LVL %s) ENCOUNTERED | YOU FIGHT IT %n"
				+ "| Health: %s | Attack: %s | Defense: %s | Speed: %s | Gold: %s | " , name, level, life, attack, defense, speed, gold);
	}

}
