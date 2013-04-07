package com.example.aft_contact_list;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class ActivityResult extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Stub de la méthode généré automatiquement
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_result);
		
		//Getting the parameters 
		Bundle _param = getIntent().getExtras();
		
		//Getting the array in the bundle
		String[] _result = _param.getStringArray("selectedItems");
		
		//Getting the ListView
		ListView _lsv_result = (ListView)findViewById(R.id.lsv_result);
		
		//Declare a new adapter
		ArrayAdapter<String> _adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, _result);
			
		//Initialize the ListView with the adapter
		_lsv_result.setAdapter(_adapter);
				
	}

	
	
}
