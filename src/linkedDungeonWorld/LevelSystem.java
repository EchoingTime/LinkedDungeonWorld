package linkedDungeonWorld;
/**
 * LevelSystem interface - Contains the abstract methods levelUp and gainXp which will be used
 * in both the Hero and Monster classes. 
 * @author Dante Anzalone
 * @version 2023-09 (4.29.0)
 */
public interface LevelSystem 
{
	void levelUp (); 	// Will update once required experience has been gained
	void gainXp (); 	// Required experience will increase by 50 per level (begins at 50 for level 1)
}
