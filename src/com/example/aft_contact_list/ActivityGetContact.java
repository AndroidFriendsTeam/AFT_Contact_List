package com.example.aft_contact_list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ActivityGetContact extends Activity implements OnClickListener {

	Button _btn_ok;
	ListView _lsv_contact;
	ArrayAdapter<String> _adapter;
	Contact[] Contact_All;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Stub de la méthode généré automatiquement
		super.onCreate(savedInstanceState);

		//Link with the layout
		setContentView(R.layout.activity_get_contact);

		//initialisation of the members of the class
		findViewById();

		//initialisation of array of contact
		Contact_All = getListContact();
		
		//On rempli un tableau de string pour l'adapter
		ArrayList<String> _contacts = new ArrayList<String>();
				
		for(Contact c : Contact_All){
			_contacts.add(c.ToString());
		}
		
		//initialisation of the adapter
		this._adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,_contacts.toArray(new String[_contacts.size()]));
		
		//initialise the choice mode of the ListView 
		this._lsv_contact.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

		//initialise the ListView with the adapter
		this._lsv_contact.setAdapter(this._adapter);

		//initialise a listener on the ListView for get the clic
		this._btn_ok.setOnClickListener(this);

	}


	private void findViewById(){
		this._btn_ok = (Button)findViewById(R.id.btn_ok);
		this._lsv_contact = (ListView)findViewById(R.id.lsv_contact);
	}
		
	public String[] getListContactPhone()
	{		

		ArrayList<String> _contacts = new ArrayList<String>();

		Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
		while (phones.moveToNext())
		{
			String _name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
			String _phone = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

			_contacts.add(_name + ";" + _phone);

		}
		phones.close();

		return _contacts.toArray(new String[_contacts.size()]);

	}

	public Contact[] getListContact()
	{
		ArrayList<Contact> _contacts = new ArrayList<Contact>();	

		String whereName = ContactsContract.Data.MIMETYPE + " = ?";
		String[] whereNameParams = new String[] { ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE };
		Cursor nameCur = getContentResolver().query(ContactsContract.Data.CONTENT_URI, null, whereName, whereNameParams, ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME);
		while (nameCur.moveToNext()) {
			
			String given = nameCur.getString(nameCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME));
			String family = nameCur.getString(nameCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME));
			String display = nameCur.getString(nameCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME));
			String contact_id = nameCur.getString(nameCur.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.CONTACT_ID));
			
			if(display != null){
				Contact _contact = new Contact(family,given,display,contact_id);
				_contacts.add(_contact);
			};
						
		}
		nameCur.close();

		return _contacts.toArray(new Contact[_contacts.size()]);
	}



	@Override
	public void onClick(View v) {

		int _pos;
		String[] _result;

		//Getting an array with the checked contacts
		SparseBooleanArray _contact_checked = this._lsv_contact.getCheckedItemPositions();

		//Fill a array of String with the selected items
		ArrayList<String> _selected_items = new ArrayList<String>();

		//Loop checked contact for fill the array of items
		for(int i = 0 ; i < _contact_checked.size() ; i++){
			//Item position in adapter
			_pos = _contact_checked.keyAt(i);

			//if contact is checked add in the array of items
			if(_contact_checked.valueAt(i))
				_selected_items.add(this._adapter.getItem(_pos));			
		}

		//Initialise the size of the array result
		_result = new String[_selected_items.size()];

		//Loop the array of items for fill the array result
		for(int i = 0 ; i < _selected_items.size() ; i++){
			_result[i] = _selected_items.get(i);
		}

		//Initialise a new Intent
		Intent _intent_result = new Intent(getApplicationContext(), ActivityResult.class);

		//Create a bundle object to put in parameter of the intent
		Bundle _param = new Bundle();
		_param.putStringArray("selectedItems", _result);

		//Add the bundle into the intent
		_intent_result.putExtras(_param);

		//Start the result activity
		startActivity(_intent_result);


	}

}
