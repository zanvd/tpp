package com.oo.tpp;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Color;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {
	private final String TAG = "tpp";

	private ImageButton buttonPoetry, buttonPicture, buttonMap;
	private HorizontalScrollView scrollPoetry, scrollPicture;
	private LinearLayout layoutPoetry, layoutPicture;
	private BroadcastReceiver broadcastReceiver;
	boolean locationFound = false;
	private Camera camera;
	private CameraPreview cameraPreview;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Initialize buttons.
		buttonPoetry = (ImageButton) findViewById(R.id.button_poetry);
		buttonPoetry.setVisibility(View.INVISIBLE);
		buttonPicture = (ImageButton) findViewById(R.id.button_picture);
		buttonPicture.setVisibility(View.INVISIBLE);
		buttonMap = (ImageButton) findViewById(R.id.button_map);

		// Initialize picture scroll.
		scrollPicture = (HorizontalScrollView) findViewById(R.id.scroll_picture);
		scrollPicture.setVisibility(View.INVISIBLE);
		layoutPicture = (LinearLayout) findViewById(R.id.layout_picture);

		// Initialize poetry scroll.
		scrollPoetry = (HorizontalScrollView) findViewById(R.id.scroll_poetry);
		scrollPoetry.setVisibility(View.INVISIBLE);
		layoutPoetry = (LinearLayout) findViewById(R.id.layout_poetry);

		// Set button on click listeners.

		buttonMap.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View v){
				Intent intent = new Intent(MainActivity.this, MapsActivity.class);
				startActivity(intent);

			}
		});

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

		// Initialize camera.
		this.initializeCamera();

		// Initialize broadcast receiver.
		this.initializeBroadcastReceiver();
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
				Resources.getSystem().getDisplayMetrics().widthPixels - 20,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		layoutParams.setMargins(10, 0, 10, 0);

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

		layoutParams.setMargins(10, 0, 10, 0);
		scrollView.setLayoutParams(layoutParams);

		TextView textView = new TextView(getApplicationContext());
		StringBuilder text = new StringBuilder();
		BufferedReader reader = null;
		try {
			 reader = new BufferedReader(
					new InputStreamReader(getAssets().open(fileName)));

			String line;
			while ((line = reader.readLine()) != null) {
				text.append(line);
				text.append('\n');
			}
		} catch (IOException e) {
			Log.d(TAG, e.getMessage());
		}

		if (reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
				Log.d(TAG, e.getMessage());
			}
		}

		textView.setText(text);
		textView.setBackgroundColor(Color.parseColor("#A4000000"));
		textView.setPadding(10, 10, 10, 10);
		LinearLayout.LayoutParams textLayout = new LinearLayout.LayoutParams(
				Resources.getSystem().getDisplayMetrics().widthPixels - 20,
				LinearLayout.LayoutParams.WRAP_CONTENT);
		textView.setLayoutParams(textLayout);
		textView.setGravity(Gravity.CENTER);

		scrollView.addView(textView);

		return scrollView;
	}

	@Override
	protected void onResume () {
		super.onResume();
		if(broadcastReceiver == null){
			this.initializeBroadcastReceiver();
		}

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

	private void initializeBroadcastReceiver () {
		broadcastReceiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				// Retrieve locations index.
				int locationIndex = new Location().getLocationIndex(
						intent.getDoubleExtra("latitude", 0),
						intent.getDoubleExtra("longitude", 0));
				Log.d(TAG, "Geo location: " + intent.getDoubleExtra("latitude", 0) + " " + intent.getDoubleExtra("longitude", 0));
				Log.d(TAG, "Location Index: " + locationIndex);
				if (locationIndex != -1) {
					// Load poetry.
					String[] fileNames = new Location().getPoetries(1);
					for (String fileName : fileNames) {
						layoutPoetry.addView(getPoetryView(fileName));
					}
					// Check if there is any poetry and show the button.
					if (fileNames.length > 0)
						buttonPoetry.setVisibility(View.VISIBLE);
					else
						buttonPoetry.setVisibility(View.INVISIBLE);

					// Load images.
					Integer[] images = new Location().getImages(1);
					for (Integer image : images) {
						layoutPicture.addView(getImageView(image));
					}
					// Check if there are any images and show the button.
					if (images.length > 0)
						buttonPicture.setVisibility(View.VISIBLE);
					else
						buttonPicture.setVisibility(View.INVISIBLE);
				} else {
					// Hide all content if shown.
					if (buttonPoetry.getVisibility() == View.VISIBLE)
						buttonPoetry.setVisibility(View.INVISIBLE);

					if (buttonPicture.getVisibility() == View.VISIBLE)
						buttonPicture.setVisibility(View.INVISIBLE);

					if (scrollPoetry.getVisibility() == View.VISIBLE)
						scrollPoetry.setVisibility(View.INVISIBLE);

					if (scrollPicture.getVisibility() == View.VISIBLE)
						scrollPicture.setVisibility(View.INVISIBLE);
				}
			}
		};
		try {
			registerReceiver(broadcastReceiver,new IntentFilter("location_update"));
		} catch (Exception e) {
			Log.d(TAG, "Failed to register the receiver: " + e.getMessage());
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
						!= PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED)	{
			requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION,  Manifest.permission.CAMERA },100 );

			return true;

		}
		return false;
	}


	public void OnRequestPermissionsResult (int requestCode, @NonNull String [] permissions, @NonNull int[] grantResults){
		super.onRequestPermissionsResult(requestCode, permissions,grantResults);
		if(requestCode == 100){
			if(grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED && grantResults[2] == PackageManager.PERMISSION_GRANTED){
				Intent i = new Intent(getApplicationContext(), GPS_Service.class);
				startService(i);
			}
			else {
				runtime_permissions();
			}
		}
	}

}
