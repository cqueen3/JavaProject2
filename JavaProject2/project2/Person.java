package project2;

import java.util.*;
public class Person {

	private String schoolID; // School ID, also main method of searching through information
	private String fn; // First name
	private String ln; // Last name
	private String lives_campus; // Does the user live on campus?
	private String in_quarantine; // Is the current user in quarantine?
	private String quarantine_start_date; // String date that the user began quarantining
	private String close_contacts; // For close contacts
	
	
	// SchoolID, First name, Last name, lives on campus, in_quarantine, quarantine_start_date, close contacts list
	//NTC constructors
	public Person(){
		fn = "";
		ln = "";
		schoolID = "";
		lives_campus = null;
		in_quarantine = null;
		close_contacts= " ";
	}
	
	public Person(String sid, String fn, String ln, String lc, String iq, String qsd, String cc) { // Person constructor with all the necessary variables
		this.setSchoolID(sid);
		this.setFn(fn);
		this.setLn(ln);
		this.setLivesCampus(lc);
		this.setInQuarantine(iq);
		this.setQSD(qsd);
		this.setCloseContacts(cc);
	}
	
	
	public String getSchoolID() {
		return schoolID;
	}
	
	
	public void setSchoolID(String sch) {
		this.schoolID = sch;
	}
	
	
	public String getFn() {
		return fn;
	}
	public void setFn(String FN) {
		this.fn = FN;
	}
	
	
	public String getLn() {
		return this.ln;
	}
	public void setLn(String LN) {
		this.ln = LN;
	}
	
	public boolean Contains(String FN, String LN) { // Alternate method to compare Persons, with first and last name
		
		if (fn.equals(FN) == true)
		{
			if (ln.equals(LN) == true) {
				return true;
			} else
				return false;
		} else
			return false;
	}
	
	
	public String getLivesCampus() {
		return this.lives_campus;
	}
	public void setLivesCampus(String RHS) {
		this.lives_campus = RHS;
		}
	
	
	public String getInQuarantine() {
		return in_quarantine;
	}
	
	public void setInQuarantine(String RHS) {
		this.in_quarantine = RHS;
	}
	
	
	public String getQSD() {
		return quarantine_start_date;
	}
	public void setQSD(String RHS) {
		this.quarantine_start_date = RHS;
	}
	
	
	public String getCloseContacts() {
		return close_contacts;
	}
	public void setCloseContacts(String cc) {
		this.close_contacts= cc;
	}
	
	public void AddContact(String s) {
		if (this.close_contacts.contains(s) == false)
		this.close_contacts = close_contacts + " " + s;
	}

}
