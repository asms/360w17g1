/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package model;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

/**
 * Abstract controller class.
 * 
 * @author Steven Smith
 * @version 1.0
 */
public abstract class AbstractController<T extends UniqueObject> {

	/**
	 * Deserializes the collection at instantiation.
	 */
	protected AbstractController() {
		if (!deserializeFromDisk()) {
			myList = new HashMap<String, T>();
			serializeToDisk();
		}
	}

	public static final String SRC_DIR = "/";

	/**
	 * The collection of unique items, as a HashMap.
	 */
	protected HashMap<String, T> myList;

	/**
	 * Serializes the collection and writes it to a file.
	 */
	protected final void serializeToDisk() {
		/*
		 * Serializes a hashmap using the name "hashmap_(class name).ser"
		 */
		try {
			FileOutputStream fos = new FileOutputStream("hashmap_" + getClass().getSimpleName() + ".ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(myList);
			oos.close();
			fos.close();
		} catch (IOException ioe) {
			// ioe.printStackTrace();
		}

	}

	/**
	 * Deserializes the collection from a file.
	 * 
	 * @return whether deserialization was successful
	 */
	private final boolean deserializeFromDisk() {
		boolean wasSuccessful = false;
		/*
		 * Deserializes a hashmap using the name "hashmap_(class name).ser"
		 */
		try {
			ObjectInputStream os = new ObjectInputStream(
					new FileInputStream("hashmap_" + getClass().getSimpleName() + ".ser"));
			myList = (HashMap<String, T>) os.readObject();
			os.close();
			wasSuccessful = true;
		} catch (IOException ioe) {
			// ioe.printStackTrace(); //TODO: Remove for prodcution
		} catch (ClassNotFoundException c) {
			// c.printStackTrace(); //TODO: Remove for prodcution
		}
		return wasSuccessful;
	}

	/**
	 * Adds a unique item to the collection.
	 * 
	 * @param theListItem
	 *            the unique item
	 */
	protected final void add(final T theListItem) {
		myList.put(theListItem.getKey(), theListItem);
		serializeToDisk();
	}

	/**
	 * Clears all persistent data.
	 */
	public void clear() {
		myList = new HashMap<String, T>();
		serializeToDisk();
	}

	/**
	 * Functionality not used in the user stories, so we throw an an Unsupported
	 * Operation Exception.
	 * 
	 * @param theListItem
	 */
	protected final void remove(final T theListItem) {
		throw new UnsupportedOperationException();
	}

}
