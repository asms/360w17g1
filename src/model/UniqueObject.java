/*
 * TCSS 360: Group 1
 * Assignment: Deliverable 2
 */
package model;

/**
 * An interface that defines an object as unique by defining a unique key value.
 * @author Steven Smith
 * @version 1.0
 */
public interface UniqueObject {
	
	/**
	 * Returns a unique key value.
	 * @return a string value which is unique to the object
	 */
	public String getKey();
}
