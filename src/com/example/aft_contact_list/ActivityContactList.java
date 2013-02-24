package com.example.aft_contact_list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ActivityContactList extends ListActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			
		ArrayList<Map<String, Object>> _data = new ArrayList<Map<String,Object>>();
		
		HashMap<String, Object> _item1 = new HashMap<String,Object>();
		_item1.put("txt_name","nom 1 ");
		_item1.put("txt_phone","phone 1 ");
		
		HashMap<String, Object> _item2 = new HashMap<String,Object>();
		_item2.put("txt_name","nom 2 ");
		_item2.put("txt_phone","phone 2 ");
		
		_data.add(_item1);
		_data.add(_item2);
		
		String[] _from = new String[]{"txt_name","txt_phone"};
		int[] _to = new int[]{R.id.txt_name,R.id.txt_phone};
		
		SimpleAdapter adapter = new SimpleAdapter(this,_data,R.layout.activity_contact_list,_from,_to);
				
		adapter.setViewBinder(new SimpleAdapter.ViewBinder() {
	
			@Override
			public boolean setViewValue(View view, Object data,
					String textRepresentation) {
				
				switch(view.getId())
				{
				case R.id.txt_name : ((TextView)view).setText((String)data);
				break;
				case R.id.txt_phone : ((TextView)view).setText((String)data);
				break;
				}
								
				return true;
			}
		});
		
		setListAdapter(adapter);
		//listContactPhone();
		
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
