package linkedDungeonWorld;
/**
 * Battle Class - Responsible for passing parameters into the methods, the monster, hero, and amount of roomsEntered,
 * and battling the two against one another. If the monster falls, then the hero gains experience and gold, but if
 * the hero falls, the program terminates after printing how many rooms the hero has survived
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class Battle 
{
	private boolean fighting; // If a monster or hero still stands then true, false if one falls
	private int encounter;	// Keeps track of the fighting turns, (Example: monster attacks then hero)
	/**
	 * battle Method - For fighting monsters that are not the boss
	 * @param monster - Monster object 
	 * @param hero - Hero object
	 * @param roomsEntered - Amount of rooms that the hero has entered
	 */
	public void battle (Monster monster, Hero hero, int roomsEntered)
	{
		encounter = 1; // Initializes the integer encounter as value 1
		fighting = true;

		monster.displayStats(monster.getName()); 	// Displays the first linked room's monster's statistics
		hero.displayStatsFight();					// Displays the first linked room's hero's statistics 
		
		while (fighting == true) // Fighting is still ongoing if equaled to true
		{
			// Seeing who goes first and then alternating between monster and hero
			if (monster.getSpeed() >= hero.getSpeed() && encounter == 1) // Due to player entering the dungeon without knowing if there is a monster, if monster has same speed as hero then the monster goes first (Surprise element!)
			{
				monster.attack(hero);
			}
			else if (monster.getSpeed() < hero.getSpeed() && encounter == 1)
			{
				hero.attack(monster);
			}
			else if (encounter % 2 == 0) // Monster attacked first, so now hero goes
			{
				hero.attack(monster);
			}
			else
			{
				monster.attack(hero);
			}
			// Checks to see if monster or hero has fallen
			if (monster.getLife() <= 0)
			{
				System.out.printf("%nYOU HAVE SLAYED THE %s, you have gained the %s's gold and experience%n", monster.getName(), monster.getName());
				hero.setGold(hero.getGold() + monster.getGold());
				hero.gainXp();
				fighting = false;
			}
			else if (hero.getLife() <= 0) // Hero has died, roomsEntered will be printed and program terminates 
			{
				roomsEntered++;
				System.out.printf("%n%s HAS FALLEN AFTER ENTERING A TOTAL OF %s ROOMS | GAME OVER%n", hero.getName(), roomsEntered);
				fighting = false;
				System.exit(0); // GAME OVER
			}
			else
			{
				encounter++;
			}
		}
	}
	
	/**
	 * battleBoss Method - For fighting the boss
	 * @param monster - Monster object 
	 * @param hero - Hero object
	 * @param roomsEntered - Amount of rooms that the hero has entered
	 */
	public void battleBoss (Monster monster, Hero hero, int roomsEntered)
	{
		LinkedRoom clear;
		
		encounter = 1;
		fighting = true;
		clear = new LinkedRoom();
		
		monster.displayStats(monster.getName()); // Will always be the door after the exit
		hero.displayStatsFight();
		
		while (fighting == true)
		{
			if (monster.getSpeed() >= hero.getSpeed() && encounter == 1) // Due to player entering the dungeon without knowing if there is a monster, if monster has same speed as hero then the monster goes first (Surprise element!)
			{
				monster.attack(hero);
			}
			else if (monster.getSpeed() < hero.getSpeed() && encounter == 1)
			{
				hero.attack(monster);
			}
			else if (encounter % 2 == 0) // Monster attacked first, so now hero goes
			{
				hero.attack(monster);
			}
			else
			{
				monster.attack(hero);
			}
			
			if (monster.getLife() <= 0)
			{
				System.out.printf("%nYOU HAVE SLAYED A %s, you have gained the %s's gold and experience%n", monster.getName(), monster.getName());
				hero.setGold(hero.getGold() + monster.getGold());
				hero.gainXp();
				fighting = false;
			}
			else if (hero.getLife() <= 0)
			{
				roomsEntered++;
				System.out.printf("%n%s HAS FALLEN TO THE DEVIL OVERLORD AFTER ENTERING A TOTAL OF %s ROOMS | GAME OVER%n", hero.getName(), roomsEntered);
				fighting = false;
				System.exit(0); // GAME OVER
			}
			else
			{
				encounter++;
			}
			clear.clear();
		}
	}
}
