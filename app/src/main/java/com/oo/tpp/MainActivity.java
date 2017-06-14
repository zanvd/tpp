package com.oo.tpp;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
	private final String TAG = "tpp";

	private Button buttonPoetry, buttonPicture;
	private HorizontalScrollView scrollPoetry, scrollPicture;
	private BroadcastReceiver broadcastReceiver;
	boolean locationFound = false;
	private Camera camera;
	private CameraPreview cameraPreview;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Initialize camera.
		this.initializeCamera();

		// Initialize buttons.
		buttonPoetry = (Button) findViewById(R.id.button_poetry);
		buttonPicture = (Button) findViewById(R.id.button_picture);

		// Initialize picture scroll.
		scrollPicture = (HorizontalScrollView) findViewById(R.id.scroll_picture);
		scrollPicture.setVisibility(View.INVISIBLE);

		LinearLayout layoutPicture = (LinearLayout) findViewById(R.id.layout_picture);
		Integer[] images = new Location().getImages(2);
		for (Integer image : images) {
			layoutPicture.addView(getImageView(image));
		}

		// Initialize poetry scroll.
		scrollPoetry = (HorizontalScrollView) findViewById(R.id.scroll_poetry);
		scrollPoetry.setVisibility(View.INVISIBLE);

		LinearLayout layoutPoetry = (LinearLayout) findViewById(R.id.layout_poetry);
		String[] fileNames = new Location().getPoetries(2);
		for (String fileName : fileNames) {
			layoutPoetry.addView(getPoetryView(fileName));
		}

		// Set button on click listeners.
		buttonPoetry.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick (View v) {
				if (scrollPoetry.getVisibility() == View.VISIBLE)
					scrollPoetry.setVisibility(View.INVISIBLE);
				else {
					scrollPoetry.setVisibility(View.VISIBLE);
					scrollPicture.setVisibility(View.INVISIBLE);
				}
			}
		});

		buttonPicture.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick (View v) {
				if (scrollPicture.getVisibility() == View.VISIBLE)
					scrollPicture.setVisibility(View.INVISIBLE);
				else {
					scrollPicture.setVisibility(View.VISIBLE);
					scrollPoetry.setVisibility(View.INVISIBLE);
				}
			}
		});

		if(!runtime_permissions()) {
			Intent i = new Intent(getApplicationContext(), GPS_Service.class);
			startService(i);
		}
	}

	/**
	 * Load images to horizontal scroll.
	 *
	 * @param image Integer
	 * @return ImageView
	 */
	private ImageView getImageView (Integer image) {
		ImageView imageView = new ImageView(getApplicationContext());

		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);

		layoutParams.setMargins(0, 0, 10, 0);
		imageView.setLayoutParams(layoutParams);

		imageView.setImageResource(image);

		return imageView;
	}

	/**
	 * Load poetry in vertical scroll to horizontal scroll.
	 *
	 * @param fileName String
	 * @return ScrollView
	 */
	private ScrollView getPoetryView (String fileName) {
		ScrollView scrollView = new ScrollView(getApplicationContext());

		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.WRAP_CONTENT,
				LinearLayout.LayoutParams.WRAP_CONTENT);

		layoutParams.setMargins(0, 0, 10, 0);
		scrollView.setLayoutParams(layoutParams);

		File file = new File("drawable", fileName);
		TextView textView = new TextView(getApplicationContext());
		try {
			textView.setText(new Scanner(file).useDelimiter("\\Z").next());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		scrollView.addView(textView);

		return scrollView;
	}

	@Override
	protected void onResume () {
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
//						textView.setText("Lokacija najdena");
						//startActivity(new Intent(MainActivity.this, menu.class));
					} else {
//						textView.setText("Ne nahajate se na območju tržaške pesniške poti,");
					}
				}

				}
			};
		}
		registerReceiver(broadcastReceiver,new IntentFilter("location_update"));

		// Re-initialize camera.
		if (this.camera == null)
			this.initializeCamera();
	}

	@Override
	protected void onPause () {
		super.onPause();
		// Release the camera.
		if (this.camera != null) {
			this.camera.setPreviewCallback(null);
			this.cameraPreview.getHolder().removeCallback(this.cameraPreview);
			this.camera.release();
			this.camera = null;
		}
	}

	@Override
	protected void onDestroy () {
		super.onDestroy();
		if(broadcastReceiver != null){
			unregisterReceiver(broadcastReceiver);
		}
		// Release the camera.
		if (this.camera != null) {
			this.camera.stopPreview();
			this.camera.release();
			this.camera = null;
		}
		// Release the preview.
		if (this.cameraPreview != null) {
			this.cameraPreview.destroyDrawingCache();
			this.cameraPreview = null;
		}
	}

	/**
	 * Initialize back facing camera and show it on the activity.
	 */
	private void initializeCamera () {
		// Check if this device has a camera.
		if (!CameraHelper.checkCameraHardware(this)) {
			Log.d(TAG, "Camera not found on this device.");
			// TODO: handle this exception.
		} else {

		}

		try {
			// Get back facing camera.
			this.camera = CameraHelper.getCameraInstance();
			// TODO: handle exception when camera is null.

			// Create preview view and set is as the content of our activity.
			this.cameraPreview = new CameraPreview(this, this.camera);
			FrameLayout preview = (FrameLayout) findViewById(R.id.layout_camera);
			preview.addView(this.cameraPreview);

		} catch (Exception e) {
			Log.d("tpp", "Error initializing camera: " + e.getMessage());
		}
	}

	/**
	 * Request permissions during runtime.
	 *
	 * @return boolean
	 */
	private boolean runtime_permissions () {
		// TODO: request camera permissions.
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


	public void OnRequestPermissionsResult (int requestCode, @NonNull String [] permissions, @NonNull int[] grantResults){
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
