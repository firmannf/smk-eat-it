package com.eat.it;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;

public class MainActivity extends SherlockFragmentActivity {

	//var declaration
	String[] tabNames = {"Home","Resto","Fav"};
	ViewPager mPager;
	ActionBar mActionBar;
    Tab tab;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//set layout
        
        //set the actionbar
        mActionBar = getSupportActionBar();
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        mActionBar.setIcon(android.R.color.transparent);
        
        //add pager in activity_main to mPager
        mPager = (ViewPager) findViewById(R.id.pager);
        
        //add fragment manager
        FragmentManager fm = getSupportFragmentManager();
        
        //add ViewPager listener
        ViewPager.SimpleOnPageChangeListener vpPageChangeListner = new ViewPager.SimpleOnPageChangeListener(){
        	@Override
            public void onPageSelected(int position) {
                // Find the ViewPager Position
                super.onPageSelected(position);
                mActionBar.setSelectedNavigationItem(position);
            }
        };
        
        //set mPager listener
        mPager.setOnPageChangeListener(vpPageChangeListner);
        
      //set view pager adapter
      FragPagerAdapter fpa = new FragPagerAdapter(fm);
      mPager.setAdapter(fpa);
      
      //add tab listener
      ActionBar.TabListener tl = new ActionBar.TabListener() {
		
		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			mPager.setCurrentItem(tab.getPosition());
		}
		
		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub
			
		}
      };
	
      //add tab to actionbar
      for (int i = 0; i < 3; i++) {
          tab = mActionBar.newTab();
          tab.setText(tabNames[i]);
          tab.setTabListener(tl);
          mActionBar.addTab(tab);
      }  
    }    
    
    //add search
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(Menu.NONE,Menu.NONE,1,"Search")
            .setIcon(R.drawable.ic_action_search)
            .setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
        return true;
    }
}