/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package model;

import java.util.HashMap;

/**
 * Abstract controller class.
 * @author Steven Smith
 * @version 1.0
 */
public abstract class AbstractController<T extends UniqueObject> {
	
	/**
	 * Deserializes the collection at instantiation.
	 */
	protected AbstractController() {
		deserializeFromDisk();
	}
	
	/**
	 * The collection of unique items, as a HashMap.
	 */
	protected HashMap<String, T> myList;
	
	/**
	 * Serializes the collection and writes it to a file.
	 */
	private final void serializeToDisk() {
		//TODO: Serialize myList to file.
	}
	
	/**
	 * Deserializes the collection from a file.
	 */
	private final void deserializeFromDisk() {
		//TODO: Deserialize list from file to myList.
		myList = new HashMap<String, T>(); // Temporary placeholder
	}
	
	/**
	 * Adds a unique item to the collection.
	 * @param theListItem the unique item
	 */
	protected final void add(final T theListItem) {
		myList.put(theListItem.getKey(), theListItem);
		serializeToDisk();
	}
	
	// Functionality not used in the user stories.
	/*
	protected final void remove(final T theListItem) {
		myList.remove(theListItem);
		serializeToDisk();
	}
	*/
	
}
