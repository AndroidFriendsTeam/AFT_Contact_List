package com.example.aft_contact_list;

import java.util.ArrayList;

import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{

	private static final int PICK_CONTACT = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.btn_select_contact).setOnClickListener(this);
		findViewById(R.id.btn_list_contact).setOnClickListener(this);
		findViewById(R.id.btn_select_contact_2).setOnClickListener(this);
		findViewById(R.id.btn_select_contact_3).setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Stub de la m�thode g�n�r� automatiquement
		switch (arg0.getId())
		{
		case R.id.btn_select_contact : selectContact();
			break;
		case R.id.btn_list_contact : openContactList();
			break;
		case R.id.btn_select_contact_2: openContactList2();
			break;
		case R.id.btn_select_contact_3: openContactList3();
			break;
		default :
		}
	}
	
	private void openContactList() {
	
		Intent _intent = new Intent(this,ActivityContactList.class);
		startActivityForResult(_intent,0);
		
	}
	
	private void openContactList2() {
		
		Intent _intent = new Intent(this,ActivitySelectionContact.class);
		startActivityForResult(_intent,1);
		
	}
	
	private void openContactList3() {

		Intent _intent = new Intent(this,ActivityGetContact.class);
		startActivity(_intent);

	}

	public void selectContact()
	{
		Intent _intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
		startActivityForResult(_intent,PICK_CONTACT);
		
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		switch(requestCode)
		{
		case PICK_CONTACT :
			if(resultCode == Activity.RESULT_OK)
			{				
				  Uri contactData = data.getData();
			        Cursor c =  managedQuery(contactData, null, null, null, null);
			        if (c.moveToFirst()) {
			          String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
			          Toast.makeText(this,name,Toast.LENGTH_SHORT).show();
			        }
			}
			break;
		
			
		}
		
	}
	
}
