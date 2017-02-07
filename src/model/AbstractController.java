/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package model;

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
		deserializeFromDisk();
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
		// For each elemet in the hashmap
		for (Map.Entry<String, HashMap> entry : myList.entrySet()) {
//			String key = myList.getKey();
//			HashMap value = myList.getValue();
//			String outFileName = key + ".ser";
//			try {
//				FileOutputStream fileOut = new FileOutputStream(SRC_DIR
//						+ outFileName);
//				ObjectOutputStream out = new ObjectOutputStream(fileOut);
//				out.writeObject(e);
//				out.close();
//				fileOut.close();
//				System.out.printf("Serialized data is saved in " + SRC_DIR
//						+ outFileName);
//			} catch (IOException i) {
//				i.printStackTrace();
//			}
		}
	}

	/**
	 * Deserializes the collection from a file.
	 */
	private final void deserializeFromDisk() {
		// Look at all the files in the dir
//		File folder = new File(SRC_DIR);
//		File[] listOfFiles = folder.listFiles();
//
//		for (File file : listOfFiles) {
//			if (file.isFile()) {
//				String key = file.getName();
//				User u = null;
//				try {
//					FileInputStream fileIn = new FileInputStream(
//							SCR_DIR + key);
//					ObjectInputStream in = new ObjectInputStream(fileIn);
//					u = (User) in.readObject();
//					//Adds the object and name to list
//					myList.put(key, u);
//					in.close();
//					fileIn.close();
//				} catch (IOException i) {
//					i.printStackTrace();
//					return;
//				} catch (ClassNotFoundException c) {
//					System.out.println("User class not found");
//					c.printStackTrace();
//					return;
//				}
//			}
//		}
		// TODO: Deserialize list from file to myList.
		myList = new HashMap<String, T>(); // Temporary placeholder
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

	// Functionality not used in the user stories.
	/*
	 * protected final void remove(final T theListItem) {
	 * myList.remove(theListItem); serializeToDisk(); }
	 */

}
