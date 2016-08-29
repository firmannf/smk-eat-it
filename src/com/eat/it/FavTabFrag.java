package com.eat.it;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockListFragment;

public class FavTabFrag extends SherlockListFragment{

	DatabaseAdapter da;
	SimpleCursorAdapter dataAdapter;
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		da = new DatabaseAdapter(getActivity());
	    da.open();
	    Cursor c = da.FetchbyFav();
	    String[] columns = new String[] {
			    DatabaseAdapter.Row_photo,
			    DatabaseAdapter.Row_resto,
			    DatabaseAdapter.Row_variant,
		};
	    
	    int[] to = new int[] {
	    		R.id.photo,
	    	    R.id.resto,
	    	    R.id.address
	    };
	    
	    dataAdapter = new SimpleCursorAdapter(
	    		getActivity().getBaseContext(), R.layout.listlayout,
	    	    c,
	    	    columns,
	    	    to,
	    	    0);
	    
	    setListAdapter(dataAdapter);
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}
}