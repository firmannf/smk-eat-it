package com.eat.it;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;

public class ResultRestoActivity extends SherlockActivity{
	
	//Declare variable
	TextView txtfood,txtdetail,txt,txt1;
	String resto,f1,f2,f3,loc,phone,variant,menu,lati,longi,photo;
	ImageView img2,img1,imgphoto;
	int a,fav,photos;
	double latitudeee,longitudeee;
	DatabaseAdapter da;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_review1);
		
		//call database adapter
		da = new DatabaseAdapter(this);
		getSupportActionBar().setIcon(android.R.color.transparent);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		//find item on activity_review1
		txtfood = (TextView) findViewById(R.id.txtresto);
		txtdetail = (TextView) findViewById(R.id.txtmenu);
		txt = (TextView) findViewById(R.id.textVie);
		img1 = (ImageView) findViewById(R.id.imageView1);
		txt1 = (TextView) findViewById(R.id.textabc);
		img2 = (ImageView) findViewById(R.id.imageView2);
		imgphoto = (ImageView) findViewById(R.id.photo);
		//get intent
		Intent i = getIntent();

		f3 = i.getStringExtra("id");
		resto = i.getStringExtra("food");
		loc= i.getStringExtra("loc");
		phone= i.getStringExtra("phone");
		variant= i.getStringExtra("variant");
		menu= i.getStringExtra("menu");
		f2 = i.getStringExtra("price");
		lati= i.getStringExtra("latitude");
		longi= i.getStringExtra("longitude");
		f1 = i.getStringExtra("fav");
		photo= i.getStringExtra("photo");
		
		photos = Integer.valueOf(photo);
		imgphoto.setImageResource(photos);
		txtdetail.setText(loc);
		txt.setText(f2);
		txt1.setText(menu);
		
		//set checkbox from db
		fav = Integer.valueOf(f1);
		if(fav==1){
			CheckBox cb = (CheckBox) findViewById(R.id.checkBox1);
			cb.setChecked(true);
		}
		
		CheckBox cb = (CheckBox) findViewById(R.id.checkBox1);
		
		//set checkbox listener
		cb.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean checked = ((CheckBox) v).isChecked();
				
					if (checked){
						da.open();
						int a = Integer.valueOf(f3);
						da.fav(a);
						Cursor cursor = da.fetchAllData();
						cursor.moveToFirst();
						String b = cursor.getString(cursor.getColumnIndexOrThrow("fav"));
						String c = cursor.getString(cursor.getColumnIndexOrThrow("resto"));
						Log.d("updating :", b+ c);
						Toast.makeText(ResultRestoActivity.this, "adding fav", Toast.LENGTH_SHORT).show();
						da.close();
					}
					
		            else{
		        		da.open();
						int a = Integer.valueOf(f3);
		            	da.unfav(a);
		            	Cursor cursor = da.fetchAllData();
						cursor.moveToFirst();
						String b = cursor.getString(cursor.getColumnIndexOrThrow("fav"));
						String c = cursor.getString(cursor.getColumnIndexOrThrow("resto"));
						Log.d("updating :", b+ c);
						Toast.makeText(ResultRestoActivity.this, "removing fav", Toast.LENGTH_SHORT).show();
						da.close();
		            }
			}
		});
		
		this.setTitle(resto);
		txtfood.setText(resto);
		
		latitudeee = Double.valueOf(lati);
		longitudeee = Double.valueOf(longi);
		
		final double latitude = latitudeee;
		final double longitude = longitudeee;

		LocationManager lManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		Criteria crit = new Criteria();
		String towers = lManager.getBestProvider(crit, true);
		final Location userCurrentLocation = lManager.getLastKnownLocation(towers);
		
		img1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(userCurrentLocation != null){
				     double lat = userCurrentLocation.getLatitude();
				     double longi = userCurrentLocation.getLongitude();

				     Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr="+lat+","+longi+"&daddr="+latitude+","+longitude));
				     startActivity(intent);

				}else{
				    Toast.makeText(ResultRestoActivity.this, "Cannot find your location", Toast.LENGTH_LONG).show();
				}
			}
		});
		
		img2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent sharingIntent = new Intent(Intent.ACTION_SEND);
				sharingIntent.setType("text/html");
				sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("<p>This is the text that will be shared.</p>"));
				startActivity(Intent.createChooser(sharingIntent,"Share using"));
			}
		});
	}
}