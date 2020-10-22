package project2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeSet;
import java.util.Vector;

public class CTList {

	
	private String fileName;
	private ArrayList<Person> CT_list = new ArrayList<Person>();

	public CTList() // Constructor with no file parameter (unused)
	{
		fileName = "";
		CT_list = new ArrayList<Person>();
	}
	
	
	public CTList(String file) { // Constructor: Creates CT list based on file entered
		fileName = file;
		CT_list = new ArrayList<Person>();
		readFile();
	}
	
	
	public void setFileName(String fn) {
		fileName = fn;
	}
	
	public void add(Person RHS) { // Adds person to set
		Iterator<Person> temp = CT_list.iterator();
		boolean flag = false; // The following is to test and make sure the ID isn't being duplicated. If it is, nothing added
		while(temp.hasNext()) {
			Person j = temp.next();
				String i = RHS.getSchoolID();
				if (j.getSchoolID().equals(i))
					flag = true;
		}
		if (flag == false) // If ID was not matched, we add
			CT_list.add(RHS);			
	}
	
	public void remove(String s) { // Removes users based on school ID
		Iterator temp = CT_list.iterator();
		int ind = 0;
		while(temp.hasNext()) { //Iterates through each CT_list object to find specified ID. If not found, returns null
			Person use = (Person) temp.next();
			if (use.getSchoolID().equals(s)) {
				ind = CT_list.indexOf(use);
				break;
			}
		}
		CT_list.remove(ind);
		}
	public void removeAll() { // Removes all entries from text list before creating a new text list from data
		CT_list.removeAll(CT_list);
	}

	public Person findPerson(String s) { // Returns value of person with the school ID as an Identifier
		Iterator<Person> temp = CT_list.iterator();
		while(temp.hasNext()) { //Iterates through each CT_list object to find specified ID. If not found, returns null
			Person use = temp.next();
			String c = use.getSchoolID();
			if (c.equals(s) == true)
				return use;
		}
		System.out.println("Not found");
		return null;
	}
	
	public boolean doesContain(String s) { // Boolean that searches to see if people are in CT_list based on school ID
		Iterator<Person> temp = CT_list.iterator();
		while (temp.hasNext()){
			Person test = temp.next();
			if (test.getSchoolID().equals(s))
				return true;
		}
		return false;
	}
	
	public Iterator<Person> iterator(){ // Iterator class, returns an iterator
		return CT_list.iterator();
	}
	
	
	public void addContact(String ID, String s) // Adds contact to person with specified ID.
	{
		Iterator<Person> iter = CT_list.iterator();
		for (int i = 0; i < CT_list.size(); i++) {
			Person test = CT_list.get(i);
			if (test.getSchoolID().equals(ID) == true) {
				iter.next().AddContact(s);
				break;
			}
			iter.next(); //There is probably an easier way, but this is the only way I could get it to work.
			}
	}
	
	public void ToString() {
		Iterator<Person> iter = CT_list.iterator();
		while (iter.hasNext()) {
			Person temp = iter.next();
			System.out.println("Name: " + temp.getFn() + " " + temp.getLn() + " Lives on Campus: " + temp.getLivesCampus() + " Is in quarantine?: " + temp.getInQuarantine() + " Quarantine Start date: " + temp.getQSD() + " Close contacts: " + temp.getCloseContacts());
		}
	}
	// SchoolID, First name, Last name, lives on campus, in_quarantine, quarantine_start_date, close contacts list
	
	private void readFile () {
		BufferedReader lineReader = null;
		try {
			FileReader fr = new FileReader(fileName);
			lineReader = new BufferedReader(fr);
			String line = null;
			while ((line = lineReader.readLine())!=null) {
			String[] tokens = line.split(",");
			String SID = tokens[0];
			String FN = tokens[1];
			String LN = tokens[2];
			String inf = tokens[3];
			String inq = tokens[4];
			String qsd = tokens[5];
			String cont = tokens[6];
			Person ind = new Person(SID, FN, LN, inf, inq, qsd, cont);
			CT_list.add(ind);
			}			
		} catch (Exception e) {
			System.err.println("there was a problem with the file reader, try different read type.");
					}
	} // end of readFile method	
	
	public void writeFile () {
		// overloaded method: this calls doWrite with file used to read data
		// use this for saving data between runs
		doWrite(fileName);
	} // end of writeFile method
//
	public void writeFile(String altFileName) {
		// overloaded method: this calls doWrite with different file name 
		// use this for testing write
		doWrite(altFileName);		
	}// end of writeFile method
////	
	private void doWrite(String fn) {
		// this method writes all of the data in the persons array to a file
		Iterator iter = CT_list.iterator();
		try
		{
			FileWriter fw = new FileWriter(fn);
			BufferedWriter myOutfile = new BufferedWriter(fw);			
			
			for (int i = 0; i < CT_list.size(); i++) {
				Person person = (Person) iter.next();
				myOutfile.write (person.getSchoolID() + ",");
				myOutfile.write (person.getFn() + ",");
				myOutfile.write (person.getLn() + ",");
				myOutfile.write (person.getLivesCampus() + ",");
				myOutfile.write (person.getInQuarantine() + ",");
				myOutfile.write (person.getQSD() + ",");
				myOutfile.write (person.getCloseContacts() + ",\n");
					
			}
			myOutfile.flush();
			myOutfile.close();
		}
		catch (Exception e) {
			e.printStackTrace();
			System.err.println("Didn't save to " + fn);
		}
	}
}