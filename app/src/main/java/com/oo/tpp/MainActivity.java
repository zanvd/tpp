package com.oo.tpp;
import android.hardware.Camera;
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
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

	private Button poezija, slike, zemljevid;
	private TextView textView;
	private BroadcastReceiver broadcastReceiver;
	public double[] dist;
	public int stPoezije =0;
	public int stSlike = 0;

	//boolean locationFound = false;
	/*
	private Camera mCamera;
	private CameraPreview mPreview;
	*/

	@Override
	protected void onResume() {
		super.onResume();
		//System.out.println("ok");


		if(broadcastReceiver == null){
			//System.out.println("ok2");
			broadcastReceiver = new BroadcastReceiver() {
				@Override
				public void onReceive(Context context, Intent intent) {
					//System.out.println("ok4");
					//lokacija 1
					dist[0] = distance(intent.getDoubleExtra("latitude", 0),intent.getDoubleExtra("longitude", 0),45.650106, 13.767817);
					//lokacija 2
					dist[1] = distance(intent.getDoubleExtra("latitude", 0),intent.getDoubleExtra("longitude", 0),45.652605, 13.765500);
					//lokacija 3
					dist[2] = distance(intent.getDoubleExtra("latitude", 0),intent.getDoubleExtra("longitude", 0),45.648145, 13.772070);
					//lokacija 4
					dist[3] = distance(intent.getDoubleExtra("latitude", 0),intent.getDoubleExtra("longitude", 0),45.641021, 13.748276);
					//lokacija 5
					dist[4] = distance(intent.getDoubleExtra("latitude", 0),intent.getDoubleExtra("longitude", 0),45.649177, 13.771725);
					//lokacija 6
					dist[5] = distance(intent.getDoubleExtra("latitude", 0),intent.getDoubleExtra("longitude", 0),45.641048, 13.760923);
					//lokacija 7
					dist[6] = distance(intent.getDoubleExtra("latitude", 0),intent.getDoubleExtra("longitude", 0),45.654261, 13.775756);
					//lokacija 8
					dist[7] = distance(intent.getDoubleExtra("latitude", 0),intent.getDoubleExtra("longitude", 0),45.654371, 13.785728);
					//lokacija 9
					dist[8] = distance(intent.getDoubleExtra("latitude", 0),intent.getDoubleExtra("longitude", 0),45.641048, 13.760923);
					//System.out.println("IZVEDENO1");

					if(dist[0] < 0.010 ) {
						poezija.setEnabled(true);
						slike.setEnabled(true);
						textView.setText("Lokacija:\nPiazza Unità D'Italia - Glavni mestni trg");
						stPoezije = 4;
						stSlike = 0;

						if (poetry.vsebina != null) {
							poetry.vsebina.setText(poetry.poezije[4]);
							poetry.stPoezije = stPoezije;
						}
						poetry.stPoezije = stPoezije;
						pictures.imagesID=stSlike;
					}
					else if(dist[1] < 0.010 ) {
						poezija.setEnabled(true);
						slike.setEnabled(true);
						stPoezije = 1;
						stSlike = 1;
						textView.setText("Lokacija:\nMolo San Carlo - Pomol sv. Karla");
						if (poetry.vsebina != null) {
							poetry.vsebina.setText(poetry.poezije[1]);
							poetry.stPoezije = stPoezije;
						}
						pictures.imagesID=stSlike;

					}
					else if (dist[2] < 0.010){
						poezija.setEnabled(true);
						slike.setEnabled(true);
						stPoezije = 2;
						stSlike = 2;
						textView.setText("Lokacija:\nIl colle di San Giusto - Grič Sv. Justa");
						if (poetry.vsebina != null) {
							poetry.vsebina.setText(poetry.poezije[2]);
							//System.out.println("IZVEDENO");
						}
						pictures.imagesID=stSlike;

					}
					else if(dist[3] < 0.010){
						poezija.setEnabled(true);
						slike.setEnabled(true);
						stPoezije = 3;
						stSlike = 3;
						textView.setText("Lokacija:\nPunto Franco - Tržaško pristanišče");
						if (poetry.vsebina != null) {
							poetry.vsebina.setText(poetry.poezije[3]);
							//System.out.println("IZVEDENO");
						}
						pictures.imagesID=stSlike;
					}
					else if(dist[4] < 0.010 ) {
						poezija.setEnabled(true);
						slike.setEnabled(true);
						stPoezije = 4;
						stSlike = 4;
						textView.setText("Lokacija:\nCittavecchia - Stari del mesta");
						if (poetry.vsebina != null) {
							poetry.vsebina.setText(poetry.poezije[4]);
						}
						pictures.imagesID=stSlike;

					}
					else if(dist[5] < 0.010 ) {
						poezija.setEnabled(true);
						slike.setEnabled(true);
						stPoezije = 4;
						stSlike = 5;
						textView.setText("Lokacija:\nPonterosso");
						if (poetry.vsebina != null) {
							poetry.vsebina.setText(poetry.poezije[4]);
						}
						pictures.imagesID=stSlike;
					}
					else if(dist[6] < 0.010 ) {
						poezija.setEnabled(true);
						slike.setEnabled(true);
						stPoezije = 4;
						stSlike = 6;
						textView.setText("Lokacija:\nPiazza Oberdan - Trg Oberdan");
						if (poetry.vsebina != null) {
							poetry.vsebina.setText(poetry.poezije[4]);
						}
						pictures.imagesID=stSlike;
					}
					else if(dist[7] < 0.010 ) {
						poezija.setEnabled(true);
						slike.setEnabled(true);
						stPoezije = 4;
						stSlike = 7;
						textView.setText("Lokacija:\nGiardino Pubblico - Ljudski vrt");
						if (poetry.vsebina != null) {
							poetry.vsebina.setText(poetry.poezije[4]);
						}
						pictures.imagesID=stSlike;
					}
					else {
						textView.setText("Oddaljeni ste : " + Math.round(minDist(3)) +"m od najbližje lokacije." );
						poezija.setEnabled(false);
						slike.setEnabled(false);
					}



				}
			};
            System.out.println(broadcastReceiver.getClass());
		}
		System.out.println(broadcastReceiver.getClass());
        try {
            registerReceiver(broadcastReceiver,new IntentFilter("location_update"));
        } catch (Exception e) {
            System.out.println("Not registered.");
        }

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		/*
		if(mCamera != null)
			mCamera.release();
		*/
		if(broadcastReceiver != null){
			unregisterReceiver(broadcastReceiver);
		}
	}

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		dist = new double[9];
		poezija = (Button) findViewById(R.id.button);
		slike = (Button) findViewById(R.id.button2);
        zemljevid = (Button) findViewById(R.id.button6);
		textView = (TextView) findViewById(R.id.textView);


		poezija.setOnClickListener(new View.OnClickListener(){
				@Override
			public void onClick(View v){
					Intent intent = new Intent(MainActivity.this, poetry.class);
					Bundle b = new Bundle();
					b.putInt("stPoetije", stPoezije);
					intent.putExtras(b);
					startActivity(intent);
				}
		});

		slike.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent = new Intent(MainActivity.this, pictures.class);
				Bundle b = new Bundle();
				b.putInt("stSlike", stSlike);
				intent.putExtras(b);
				startActivity(intent);

			}
		});

		poezija.setEnabled(false);
		slike.setEnabled(false);

        zemljevid.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);

            }
        });

		 if(!runtime_permissions()) {
			 Intent i = new Intent(getApplicationContext(), GPS_Service.class);
			 startService(i);
			/*

			 mCamera = getCameraInstance();

			 // Create our Preview view and set it as the content of our activity.
			 mPreview = new CameraPreview(this, mCamera);

			 FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
			 preview.addView(mPreview);
			 */
		 }
	}


	/*
		private boolean runtime_permissions() {
		if(Build.VERSION.SDK_INT > 23 && 
				ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) 
						!= PackageManager.PERMISSION_GRANTED && 
				ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) 
						!= PackageManager.PERMISSION_GRANTED &&
				ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
						!= PackageManager.PERMISSION_GRANTED)	{
			requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.CAMERA },100 );

			return true;

		}
		return false;
	}*/

	private boolean runtime_permissions() {
		if(Build.VERSION.SDK_INT > 23 &&
				ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
						!= PackageManager.PERMISSION_GRANTED &&
				ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
						!= PackageManager.PERMISSION_GRANTED )	{
			requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},100 );

			return true;

		}
		return false;
	}

	/*
	public void OnRequestPermissionsResult(int requestCode, @NonNull String [] permissions, @NonNull int[] grantResults){
		super.onRequestPermissionsResult(requestCode, permissions,grantResults);
		if(requestCode == 100){
			if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED){
				Intent i = new Intent(getApplicationContext(), GPS_Service.class);
				startService(i);
				mCamera = getCameraInstance();
				// Create our Preview view and set it as the content of our activity.
				mPreview = new CameraPreview(this, mCamera);
				FrameLayout preview = (FrameLayout) findViewById(R.id.camera_preview);
				preview.addView(mPreview);
			}
			else {
				runtime_permissions();
			}
		}
	}*/

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

	private double distance(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1))
				* Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1))
				* Math.cos(deg2rad(lat2))
				* Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		return (dist);
	}

	private double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private double rad2deg(double rad) {
		return (rad * 180.0 / Math.PI);
	}

	private double minDist(int max){
		double min= dist[0];
		for(int i=1; i<max; i++) {
			if(dist[i] < min){
				min = dist[i];
			}
		}
		return min*1000;
	}
	/*
	private boolean checkCameraHardware(Context context) {
		if (context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA)){
			// this device has a camera
			return true;
		} else {
			// no camera on this device
			return false;
		}
	}

	public static Camera getCameraInstance(){
		System.out.println("getCameraInstance - OK");
		Camera c = null;
		try {
			Camera.open(Camera.CameraInfo.CAMERA_FACING_BACK); // attempt to get a Camera instance
		}
		catch (Exception e){
			// Camera is not available (in use or does not exist)
			System.out.println("CAMERA IS NOT AVAILABLE");
		}
		if(c == null)
			System.out.println("CAMERA JE NULL");
		return c; // returns null if camera is unavailable
	}*/


}
