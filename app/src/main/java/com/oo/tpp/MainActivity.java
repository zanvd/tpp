package com.oo.tpp;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	private Button poezija, slike;
	private TextView textView;
	private BroadcastReceiver broadcastReceiver;
	boolean locationFound = false;


	@Override
	protected void onResume() {
		super.onResume();
		if(broadcastReceiver == null){
			broadcastReceiver = new BroadcastReceiver() {
				@Override
				public void onReceive(Context context, Intent intent) {

				if (!locationFound) {
					if (intent.getDoubleExtra("longitude", 0) >= 14.3 &&
							intent.getDoubleExtra("longitude", 0) <= 14.7 &&
							intent.getDoubleExtra("latitude", 0) >= 46 &&
							intent.getDoubleExtra("latitude", 0) <= 46.1) {
						locationFound=true;
						textView.setText("Lokacija najdena");
						poezija.setVisibility(View.VISIBLE);
						slike.setVisibility(View.VISIBLE);
						//startActivity(new Intent(MainActivity.this, menu.class));
					} else {
						textView.setText("Ne nahajate se na območju tržaške pesniške poti,");
					}
				}

				}
			};
		}
		registerReceiver(broadcastReceiver,new IntentFilter("location_update"));
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(broadcastReceiver != null){
			unregisterReceiver(broadcastReceiver);
		}
	}

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		poezija = (Button) findViewById(R.id.button);
		slike = (Button) findViewById(R.id.button2);
		textView = (TextView) findViewById(R.id.textView);

		poezija.setOnClickListener(new View.OnClickListener(){
				@Override
			public void onClick(View v){
					startActivity(new Intent(MainActivity.this, poetry.class));

				}
		});

		slike.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				startActivity(new Intent(MainActivity.this, pictures.class));

			}
		});

		 if(!runtime_permissions()) {
			 Intent i = new Intent(getApplicationContext(), GPS_Service.class);
			 startService(i);
		 }
	}



		private boolean runtime_permissions() {
		if(Build.VERSION.SDK_INT > 23 && 
				ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) 
						!= PackageManager.PERMISSION_GRANTED && 
				ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) 
						!= PackageManager.PERMISSION_GRANTED)	{
			requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION },100 );

			return true;

		}
		return false;
	}


	public void OnRequestPermissionsResult(int requestCode, @NonNull String [] permissions, @NonNull int[] grantResults){
		super.onRequestPermissionsResult(requestCode, permissions,grantResults);
		if(requestCode == 100){
			if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
				Intent i = new Intent(getApplicationContext(), GPS_Service.class);
				startService(i);
			}
			else {
				runtime_permissions();
			}
		}
	}

}
