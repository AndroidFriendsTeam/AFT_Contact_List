package com.example.aft_contact_list;

import java.util.ArrayList;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;

public class ContactListActivity extends ListActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
				
		//listContactPhone();
		
		String[] data = {"tata","toto","titi"};
				
		ArrayAdapter<String> _adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
		
		setListAdapter(_adapter);
		
	}

	public void listContactPhone()
	{		
		
		ArrayList<String> _listContact = new ArrayList<String>();
			
		Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
		while (phones.moveToNext())
		{
		  String name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
		  String phone = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
		 
		  _listContact.add(name+"/"+phone);	
		  
		}
		phones.close();
		
		ArrayAdapter<String> _adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,_listContact);
		
		setListAdapter(_adapter);
		
		
	}
	
}
