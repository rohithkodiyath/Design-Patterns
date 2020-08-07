/**
 * 
 */
package template;

/**
 * @author rohith
 *
 */
public abstract class HouseTemplate {
	
	public void buildHouse() {
		makeFoundation();
		makeWalls();
		makeCeiling();
	}
	
	abstract void makeFoundation();
	abstract void makeWalls();
	abstract void makeCeiling();
	
}
