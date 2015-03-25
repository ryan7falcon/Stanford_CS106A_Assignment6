import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import acm.util.ErrorException;

public class FileToListOfLines {
	
	/**Constructor: opens a file, reads every line and stores it into an ArrayList*/
	public  FileToListOfLines (String filename){
		
		BufferedReader rd = openFile(filename);
		list = new ArrayList<String>();
		int i=0;
		try {
			while (true)
			{
				list.add(rd.readLine());
				if (list.get(i++) == null)
				{
					list.remove(i-1);
					break;
				}
			}
			rd.close();
		}
		catch (IOException ex)
		{ 
			throw new ErrorException(ex);
		}
		
		
	}
	/**gets a list of lines
	 * @return list ArrayList<String>*/
	public ArrayList<String> getList()
	{
		return list;
	}
	
	
	private ArrayList<String> list;
	private BufferedReader openFile(String filename)
	{
		BufferedReader rd = null;
		
			
			try {
				rd = new BufferedReader(new FileReader(filename));
			}
			catch (IOException ex) {
				throw new ErrorException("that file doesn't exists");
			}
		
		return rd;
	}
	
}
