import java.util.ArrayList;

import java.util.*;

/*
 * File: NameSurferDataBase.java
 * -----------------------------
 * This class keeps track of the complete database of names.
 * The constructor reads in the database from a file, and
 * the only public method makes it possible to look up a
 * name and get back the corresponding NameSurferEntry.
 * Names are matched independent of case, so that "Eric"
 * and "ERIC" are the same names.
 */

public class NameSurferDataBase implements NameSurferConstants {
	
/* Constructor: NameSurferDataBase(filename) */
/**
 * Creates a new NameSurferDataBase and initializes it using the
 * data in the specified file.  The constructor throws an error
 * exception if the requested file does not exist or if an error
 * occurs as the file is being read.
 */
	public NameSurferDataBase(String filename) {
		//Loads file
		file = new FileToListOfLines (filename);
		map = new HashMap <String, NameSurferEntry>();
		//Maps list of lines with names
		for(String line: file.getList())
		{
			if(line!= null)
			{
				NameSurferEntry entry = new NameSurferEntry(line);
				map.put(entry.getName().toLowerCase(), entry);
			}
		}
		
	}
	
/* Method: findEntry(name) */
/**
 * Returns the NameSurferEntry associated with this name, if one
 * exists.  If the name does not appear in the database, this
 * method returns null.
 */
	public NameSurferEntry findEntry(String name) {
		

		if(map.get(name.toLowerCase())!= null)
			return map.get(name.toLowerCase());
		else 
			return null;
	
		//entry = new NameSurferEntry(data.get(0));

	}
	
	private FileToListOfLines file;
	private HashMap <String, NameSurferEntry> map;
}

