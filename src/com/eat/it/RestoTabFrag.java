package com.eat.it;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.actionbarsherlock.app.SherlockListFragment;

public class RestoTabFrag extends SherlockListFragment{
	//String[] food = new String[]{"Resto 1","Resto 2","Resto 3","Resto 4","Resto 5"};
	//String[] variant = new String[]{"123","456","789","123","456"};
	//String[] price = new String[]{"RP.123","RP.1234","RP.1235","RP.1236","RP.1237"};
	//private int img = R.drawable.noimage;
	DatabaseAdapter da;
	SimpleCursorAdapter dataAdapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		da = new DatabaseAdapter(getActivity());
	    da.open();
	   // da.deleteAllData();
	    //da.insertSomeData();
	    Cursor cursor = da.fetchAllData();
	    
	    String[] columns = new String[] {
			    DatabaseAdapter.Row_photo,
			    DatabaseAdapter.Row_resto,
			    DatabaseAdapter.Row_variant
		};
	    
	    int[] to = new int[] {
	    		R.id.photo,
	    	    R.id.resto,
	    	    R.id.address
	    };
	    	 
	    dataAdapter = new SimpleCursorAdapter(
	    		getActivity().getBaseContext(), R.layout.listlayout,
	    	    cursor,
	    	    columns,
	    	    to,
	    	    0);
	    
	    setListAdapter(dataAdapter);
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
		getListView().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long id) {
				// TODO Auto-generated method stub
					da = new DatabaseAdapter(getActivity());
					da.open();
					Cursor cursor = da.fetchAllData();
					cursor.moveToPosition(position);	
					Intent i = new Intent(v.getContext(), ResultRestoActivity.class);
					String _id = cursor.getString(cursor.getColumnIndexOrThrow("_id"));
					String res = cursor.getString(cursor.getColumnIndexOrThrow("resto"));
					String loc = cursor.getString(cursor.getColumnIndexOrThrow("location"));
					String phone = cursor.getString(cursor.getColumnIndexOrThrow("phone"));
					String variant = cursor.getString(cursor.getColumnIndexOrThrow("variant"));
					String menu = cursor.getString(cursor.getColumnIndexOrThrow("menu"));
					String pri = cursor.getString(cursor.getColumnIndexOrThrow("price"));
					String lat = cursor.getString(cursor.getColumnIndexOrThrow("latitude"));
					String longi = cursor.getString(cursor.getColumnIndexOrThrow("longitude"));
					String photo = cursor.getString(cursor.getColumnIndexOrThrow("photo"));
					String var = cursor.getString(cursor.getColumnIndexOrThrow("fav"));
					i.putExtra("id", _id);
					i.putExtra("food", res);
					i.putExtra("loc", loc);
					i.putExtra("phone", phone);
					i.putExtra("variant", variant);
					i.putExtra("menu", menu);
					i.putExtra("price", pri);
					i.putExtra("latitude", lat);
					i.putExtra("longitude", longi);
					i.putExtra("fav", var);
					i.putExtra("photo", photo);
					startActivity(i);
					da.close();
			}
		});
		
	}
}
