package linkedDungeonWorld;
/**
 * Play Class - Plays the Linked Dungeon World! (Enters the linkedDungeonWorld package and it's classes
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public class Play 
{
	public static void main (String [] args)
	{
		LinkedRoom access;
		access = new LinkedRoom(); // Initializes LinkedRoom object, access

		access.runGame();
	}
}
