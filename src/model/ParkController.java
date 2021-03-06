/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package model;

import java.util.ArrayList;

import model.Park;

/**
 * Park model class.
 * @author Steven Smith
 * @author Trinh Nguyen 
 * @version 1.0
 */
public class ParkController extends AbstractController<Park> {
	
	/**
	 * Adds a park to the database.
	 * @param thePark the park to be added
	 */
	public void addPark(final Park thePark) {
		add(thePark);
	}
	
	/**
	 * Returns a park with a specific name.
	 * @param theName the name of a park
	 * @return the park that has the specified name
	 */
	public Park getParkByName(final String theName) {
		return myList.get(theName);
	}
	
	/**
	 * Returns all parks.
	 * @return all parks
	 */
	public ArrayList<Park> getAllParks() {
		return new ArrayList<Park>(myList.values());
	}

}
