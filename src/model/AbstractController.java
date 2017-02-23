/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

import model.UniqueObject;

/**
 * Abstract controller class.
 * 
 * @author Steven Smith
 * @version 1.0
 */
public abstract class AbstractController<T extends UniqueObject> {

	/** Deserializes the default collection at instantiation. */
	@SuppressWarnings("unchecked")
	protected AbstractController() {
		myList = deserializeFromDisk(getClass().getSimpleName() + "_LIST", new HashMap<String, T>().getClass());
		if (myList == null) {
			myList = new HashMap<String, T>();
			serializeListToDisk();
		}
	}

	/** The collection of unique items, as a HashMap. */
	protected HashMap<String, T> myList;
	
	/**
	 * Serializes an object and writes it to the disk.
	 * <p>The file written will match the pattern "KEYNAME_CLASSNAME.ser"</p>
	 * 
	 * @param theKey the key name associated with the object
	 * @param theObject the object to serialize
	 * @return true if the operation completed successfully
	 */
	public static final <E extends Serializable> boolean serializeToDisk(final String theKey, final E theObject) {
		boolean succeeded;
		try {
			final File file = new File(theKey + "_" + theObject.getClass().getSimpleName() + ".ser");
			if (!file.exists()) {
				file.createNewFile();
			}
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.writeObject(theObject);
			oos.close();
			succeeded = true;
		} catch (final Exception theException) {
			succeeded = false;
		}
		return succeeded;
	}
	
	/**
	 * Deserializes an object from disk that corresponds to a key value.
	 * <p>The file must exist and match the pattern "KEYNAME_CLASSNAME.ser"</p>
	 * 
	 * @param theKey the key name
	 * @param theClass the class of the object to deserialize 
	 * @return the deserialized object or null if deserialization failed
	 */
	@SuppressWarnings("unchecked")
	protected
	static final <E extends Serializable> E deserializeFromDisk(final String theKey, final Class<E> theClass) {
		E theObject;
		try {
			ObjectInputStream os = new ObjectInputStream(
					new FileInputStream(theKey + "_" + theClass.getSimpleName() + ".ser"));
			theObject = (E) os.readObject();
			os.close();
		} catch (Exception e) {
			theObject = null;
		}
		return theObject;
	}

	/**
	 * Adds a unique item to the collection.
	 * 
	 * @param theListItem the unique item
	 */
	protected final void add(final T theListItem) {
		myList.put(theListItem.getKey(), theListItem);
		serializeListToDisk();
	}

	/** Clears all persistent data. */
	public void clear() {
		myList = new HashMap<String, T>();
		serializeListToDisk();
	}
	
	/** Serializes the default collection to the disk. */
	private void serializeListToDisk() {
		serializeToDisk(getClass().getSimpleName() + "_LIST", myList);
	}

}
