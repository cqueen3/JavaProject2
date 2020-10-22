// Corey Queen, 8 AM Java, Project 1
package project2;

import java.util.Scanner;
import java.util.Vector;

public class ProjectTester {

public static void main(String[] args) {
	Scanner scan = new Scanner(System.in);
	CTList newList = new CTList("./Project2/Infected_List.txt"); // Reads file, uploads any existing contacts
	
	Person test = new Person(); // Created to add first person to list
	
	test.setFn("Terry");
	test.setLn("Jones");
	test.setSchoolID("1839302");
	test.setLivesCampus("false");
	test.setInQuarantine("false");
	test.setQSD("September 11");
	test.setCloseContacts("12344321 12345678");
	
	newList.add(test); // Add function adds new contacts to the lis
	
	//newList.ToString(); // First line, should print only Terry Jones Upon first run. Multiple runs will print entire list
	System.out.println("\n \n");
	
	Person best = new Person();
	best.setSchoolID("849231");
	best.setFn("Jonny");
	best.setLn("Mater");
	best.setLivesCampus("true");
	best.setInQuarantine("true");
	best.setQSD("N/A");
	best.setCloseContacts("623456 23456");
	
	newList.add(best);
	
	Person jest = new Person();
	jest.setSchoolID("11223344");
	jest.setFn("Bailey");
	jest.setLn("Smith");
	jest.setLivesCampus("false");
	jest.setInQuarantine("true");
	jest.setQSD("Tuesday 11 September");
	jest.setCloseContacts("54326 828492");
	
	newList.add(jest);
	//newList.ToString(); // Now list should contain all 3 contacts that have been manually added. 
	//System.out.println("\n \n");
	
	//System.out.println("\nBefore removal: " + newList.doesContain("11223344")); // A boolean to test whether or not a person with that ID exists
	
	//newList.remove("11223344"); // The person to be removed should be Bailey Smith, as their ID is the input string
	
	//System.out.println("\nAfter removal: " + newList.doesContain("11223344")); 
	//newList.ToString();
	//System.out.println("\n \n");
	
	newList.addContact("1839302", "820652"); // Should add contact 820652 to Terry Jones
	//newList.ToString();
	
	
	Person find_p_test = newList.findPerson("1839302"); // Test to see if the new assigned variable has the properties of the Person of same ID.
	//System.out.println("Should read :Terry Jones: " + find_p_test.getFn() + " " + find_p_test.getLn());
	
	find_p_test = newList.findPerson("849231"); // Test to see if the new assigned variable has the properties of the Person of same ID.
	//System.out.println("Should read :Jonny Mater: " + find_p_test.getFn() + " " + find_p_test.getLn());
	
	newList.writeFile(); // This writes a file to the text file above, also ends program
}
	
}
