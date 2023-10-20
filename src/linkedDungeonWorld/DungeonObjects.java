package linkedDungeonWorld;
import java.util.Random;

import java.util.Scanner;
/**
 * DungeonObjects Class - Responsible for keeping the objects that
 * the hero can interact or get impacted with, such as healing food or damaging trap (spikes),
 * tool (sharpener) to increase attack or lucky escape from spider webs, but wearing down sword, 
 * new armor or damaged armor via survival of a trap, speed drinks or a trap (mud) that slows hero down, 
 * and a gold tracker which increases or decreases gold, if user wants to buy any health or strength
 * drinks
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class DungeonObjects 
{
	private int randomEvent; // The number generated (9 item or event possibilities)
	
	/**
	 * generateObject Method - Will generate a random event 0 through 8, either
	 * positively or negatively impacting the player
	 * @param hero - The player's hero
	 */
	public void generateObject (Hero hero)
	{
		Scanner userInput;
		Random rand;
		String choice;
		boolean run;
		rand = new Random();
		
		run = true;
		userInput = new Scanner (System.in);
		
		randomEvent = rand.nextInt(9);
		
		if (randomEvent == 0)
		{
			hero.setLife(hero.getLife() + 5); // Blessed 5 health
			System.out.printf("%nYou ate a blessed apple! (Gained 5 health | You now have %s health) %n", hero.getLife());
		}
		else if (randomEvent == 1)
		{
			hero.setLife(hero.getLife() - 5); // Bled for 5 health
			System.out.printf("%nYou have stepped on a spike trap! (Bled for 5 health | You now have %s health)%n", hero.getLife());
		}
		else if (randomEvent == 2)
		{
			hero.setAttack(hero.getAttack() + 5); // Sharpened sword gaining 5 attack damage
			System.out.printf("%nYou have found a grindstone! (You sharpen your sword (Gained 5 attack | You now have %s attack damage)%n", hero.getAttack());

		}
		else if (randomEvent == 3)
		{
			hero.setAttack(hero.getAttack() - 5); // Sword lost some edge, lost 5 attack damage
			System.out.printf("%nYou got stuck in some thick spider webs! (After hacking away at the webs, your sword lost some edge, loosing 5 attack | You now have %s attack damage)%n", hero.getAttack());
		}
		else if (randomEvent == 4)
		{
			hero.setDefense(hero.getDefense() + 5); // You found a new pair of armor, gaining 5 defense
			System.out.printf("%nYou have found a new armor! (You gained 5 defense | You now have a defense of %s)%n", hero.getDefense());
		}
		else if (randomEvent == 5)
		{
			hero.setDefense(hero.getDefense() - 5); // You stepped on a trap, but thankfully your armor took the impact and not your face, lost 5 defense
			System.out.printf("%nYou have stepped on a trap which launched a boulder at you! (Your armor took the blow instead of your face! Lost 5 defense | You now have a defense of %s)%n", hero.getDefense());
		}
		else if (randomEvent == 6)
		{
			hero.setSpeed(hero.getSpeed() + 5); // You found blessed water, gaining 5 speed
			System.out.printf("%nYou have found some blessed water! (You gained 5 speed | You know have a speed of %s)%n", hero.getSpeed());
		}
		else if (randomEvent == 7)
		{
			hero.setSpeed(hero.getSpeed() - 5); // You found stepped in a mud trap, intended to weaken your reflects, lost 5 speed
			System.out.printf("%nYou have stepped in some thick mud left out for you and intended to weaken your reflects! (You lost 5 speed | You now have a speed of %s)%n", hero.getSpeed());
		}
		else // if (randomEvent == 8)
		{
			System.out.printf("%nYou have found a troll's shop! In these dungeons, the Troll only offers health and strength potions!%n"
					+ "A potion costs 25 gold (grants you 6 health) or a strength potion costing 50 gold! (grants you 6 more attack damage)"
					+ "%nType either a capital H (Health) or S (Strength) to buy, and L (Leave) to leave the shop%n"
					+ "%nBe careful, asking the troll for something you do not have the money for will end poorly for you.");
			if (hero.getGold() >= 25)
			{
				while (hero.getGold() >= 25 && run == true)
				{
					System.out.printf("%nPick! (H, S, or L) %n");
					choice = userInput.nextLine();
					
					if (choice.equals("L"))
					{
						System.out.printf("%nHappy killings *Troll smiles, pleased*%n");
						run = false;
					}
					else if (choice.equals("H"))
					{
						hero.setGold(hero.getGold() - 25);
						hero.setLife(hero.getLife() + 6);
						System.out.printf("Pleasure doing business with you! (Your current gold is %s and new health is %s%n", hero.getGold(), hero.getLife());
					}
					else if (choice.equals("S") && hero.getGold() >= 50)
					{
						hero.setGold(hero.getGold() - 50);
						hero.setAttack(hero.getAttack() + 6);
						System.out.printf("Pleasure doing business with you! (Your current gold is %s and new attack damage is %s%n", hero.getGold(), hero.getAttack());
					}
					else
					{
						System.out.printf("%nYou did not listen... Troll SCREAMS and yanks 25 gold from your hands and kicks you out fron the shop!%n");
						hero.setGold(hero.getGold() - 25);
						run = false;
					}
				}
			}
			else
			{
				System.out.printf("%nWait! You have no gold that I can see! Get out of my shop and die!%n");
			}
		}
	}
}
