package com.eat.it;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragPagerAdapter extends FragmentPagerAdapter {

	final int PAGE_COUNT = 3;
	
	public FragPagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		Bundle data = new Bundle();
		switch(arg0) {
		
		//call HomeTabFrag.java
		case 0:
			HomeTabFrag hometabfrag = new HomeTabFrag();
			data.putInt("current_page", arg0+1);
			hometabfrag.setArguments(data);
			return hometabfrag;
			
		//call RestoTabFrag.java
		case 1:
			RestoTabFrag restotabfrag = new RestoTabFrag();
			data.putInt("current_page", arg0+2);
			restotabfrag.setArguments(data);
			return restotabfrag;
			
		//call FavTabFrag.java
		case 2:
			FavTabFrag favtabfrag = new FavTabFrag();
			data.putInt("current_page", arg0+3);
			favtabfrag.setArguments(data);
			return favtabfrag;
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return PAGE_COUNT;
	}
	
}
