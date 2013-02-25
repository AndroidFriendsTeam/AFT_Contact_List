package com.example.aft_contact_list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityContactList extends ListActivity implements OnItemClickListener {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getListView().setOnItemClickListener(this);
		
		ArrayList<Map<String, Object>> _data = getListContactPhone();
				
		String[] _from = new String[]{"txt_name","txt_phone","ckb_selection"};
		int[] _to = new int[]{R.id.txt_name,R.id.txt_phone,R.id.ckb_selection};
		
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
				case R.id.ckb_selection : ((CheckBox)view).setChecked((Boolean)data);
				break;
				}
								
				return true;
			}
		});
		
		
		setListAdapter(adapter);
		//listContactPhone();
		
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

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Toast.makeText(this, "tot", Toast.LENGTH_SHORT).show();
		
	}

		
}
