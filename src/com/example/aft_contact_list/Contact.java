package com.example.aft_contact_list;

//A supprimer

public class Contact {

	private String familyName, givenName, displayName, phoneNumber, id_Contact, id_PhoneNumber;
	
	public Contact(String familyName, String givenName, String displayName,
			String phoneNumber, String id_Contact, String id_PhoneNumber) {
		super();
		this.familyName = familyName;
		this.givenName = givenName;
		this.displayName = displayName;
		this.phoneNumber = phoneNumber;
		this.id_Contact = id_Contact;
		this.id_PhoneNumber = id_PhoneNumber;
	}

	public Contact(String familyName, String givenName, String displayName,
			String id_Contact) {
		super();
		this.familyName = familyName;
		this.givenName = givenName;
		this.displayName = displayName;
		this.id_Contact = id_Contact;
	}

	public Contact(String familyName, String givenName, String phoneNumber) {
		super();
		this.familyName = familyName;
		this.givenName = givenName;
		this.phoneNumber = phoneNumber;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getId_Contact() {
		return id_Contact;
	}

	public void setId_Contact(String id_Contact) {
		this.id_Contact = id_Contact;
	}

	public String getId_PhoneNumber() {
		return id_PhoneNumber;
	}

	public void setId_PhoneNumber(String id_PhoneNumber) {
		this.id_PhoneNumber = id_PhoneNumber;
	}
	
	public String ToString()
	{
		return this.displayName;
	}

}
