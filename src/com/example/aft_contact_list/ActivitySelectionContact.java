package com.example.aft_contact_list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class ActivitySelectionContact extends ListActivity {
	
	ListView list;
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Stub de la méthode généré automatiquement
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_selection_contact);
		
		list = getListView();
				
		ArrayList<Map<String, Object>> _data = getListContactPhone();
		
		String[] _from = new String[]{"txt_name","txt_phone","ckb_selection"};
		int[] _to = new int[]{R.id.txt_name,R.id.txt_phone,R.id.ckb_selection};
		
		SimpleAdapter adapter = new SimpleAdapter(this,_data,R.layout.activity_contact_list,_from,_to);
		
		list.setAdapter(adapter);
		
	}
	
	public ArrayList<Map<String, Object>> getListContactPhone()
	{		
		
		ArrayList<Map<String, Object>> _data = new ArrayList<Map<String,Object>>();
		HashMap<String, Object> _item;
				
	
		Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,null,null, null);
		while (phones.moveToNext())
		{
		  String _name = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
		  String _phone = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
		 
		  _item = new HashMap<String,Object>();
		  _item.put("txt_name",_name);
		  _item.put("txt_phone",_phone);	
		  _item.put("ckb_selection",false);	
			
		  _data.add(_item);
		  
		}
		phones.close();
	
		return _data;
		
	}
	
	
}
