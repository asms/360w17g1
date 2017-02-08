/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Abstract controller class.
 * 
 * @author Steven Smith
 * @version 1.0
 */
public abstract class AbstractController<T extends UniqueObject> {

	public class UniqueObjectTest {

	}

	/**
	 * Deserializes the collection at instantiation.
	 */
	protected AbstractController() {
		if (null == myList){
			myList = new  HashMap<String, T>();
		}
		// deserializeFromDisk();
	}

	public static final String SRC_DIR = "/";

	/**
	 * The collection of unique items, as a HashMap.
	 */
	protected HashMap<String, T> myList;

	/**
	 * Serializes the collection and writes it to a file.
	 */
	private final void serializeToDisk() {
		try {
			FileOutputStream fos = new FileOutputStream("hashmap.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(myList);
			oos.close();
			fos.close();
			System.out.printf("Serialized HashMap data is saved in hashmap.ser");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

	}

	/**
	 * Deserializes the collection from a file.
	 */
	private final void deserializeFromDisk() {
		
	      try
	      {
	         FileInputStream fis = new FileInputStream("hashmap.ser");
	         ObjectInputStream ois = new ObjectInputStream(fis);
	         myList = (HashMap) ois.readObject();
	         ois.close();
	         fis.close();
	      }catch(IOException ioe)
	      {
	         ioe.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Class not found");
	         c.printStackTrace();
	         return;
	      }
	}

	/**
	 * Adds a unique item to the collection.
	 * 
	 * @param theListItem
	 *            the unique item
	 */
	protected final void add(final T theListItem) {
		myList.put(theListItem.getKey(), theListItem);
		System.out.println("HERE");
		serializeToDisk();
	}

	// Functionality not used in the user stories.
	/*
	 * protected final void remove(final T theListItem) {
	 * myList.remove(theListItem); serializeToDisk(); }
	 */

}
